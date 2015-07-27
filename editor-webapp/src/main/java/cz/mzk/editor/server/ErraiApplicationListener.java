package cz.mzk.editor.server;


import com.google.gwt.rpc.server.Pair;
import org.apache.log4j.Logger;
import org.jboss.errai.bus.client.api.builder.DefaultRemoteCallBuilder;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.bus.client.api.messaging.MessageCallback;
import org.jboss.errai.bus.server.api.ServerMessageBus;
import org.jboss.errai.bus.server.io.RPCEndpointFactory;
import org.jboss.errai.bus.server.io.RemoteServiceCallback;
import org.jboss.errai.bus.server.io.ServiceInstanceProvider;
import org.jboss.errai.bus.server.service.ErraiService;
import org.jboss.errai.bus.server.service.ErraiServiceSingleton;
import org.jboss.errai.common.client.api.Assert;
import org.jboss.errai.common.client.framework.ProxyFactory;
import org.jboss.errai.config.rebind.ProxyUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by rumanekm on 7/20/15.
 */
@Component
public class ErraiApplicationListener implements ApplicationListener<ContextRefreshedEvent> {


    Logger logger = Logger.getLogger(ErraiApplicationListener.class);;

    boolean initialized = false;


    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(!initialized) {
            logger.info("Collecting beans...");

            final List<Pair<Class<?>, Object>> beans = new ArrayList<Pair<Class<?>, Object>>();
            for(Object type : event.getApplicationContext().getBeansWithAnnotation(org.jboss.errai.bus.server.annotations.Service.class).values()) {
                for(Class<?> intf : type.getClass().getInterfaces()) {
                    if(intf.isAnnotationPresent(org.jboss.errai.bus.server.annotations.Remote.class)) {
                        logger.info("Found " + type.getClass().getName() + " bean with remote " + intf.getName() + ".");
                        beans.add(new Pair<Class<?>, Object>(intf, type));
                    }
                }
            }


            logger.info("Collected " + beans.size() + " beans.");

            ErraiServiceSingleton.registerInitCallback(new ErraiServiceSingleton.ErraiInitCallback() {
                @Override
                public void onInit(ErraiService service) {
                    logger.info("Registering " + beans.size() + " beans.");
                    for (Pair<Class<?>, Object> pair : beans) {
                        logger.info("Registering " + pair.getA().getClass().getName() + " bean with remote " + pair.getB().getClass().getName() + ".");
                        register(service, pair.getA(), pair.getB());
                    }
                    logger.info("Registered.");
                }
            });


        }
    }

    /**
     * Method produced based on @link{org.jboss.errai.bus.server.service.ServiceProcessor}.
     *
     * @param service
     * @param remoteIntf
     * @param type
     */
    private void register(ErraiService service, final Class<?> remoteIntf, final Object svc) {
        final ServerMessageBus bus = ErraiServiceSingleton.getService().getBus();
        final Map<String, MessageCallback> epts = new HashMap<String, MessageCallback>();

        final ServiceInstanceProvider genericSvc = new ServiceInstanceProvider() {
            @Override
            public Object get(Message message) {
                return svc;
            }
        };

        // beware of classloading issues. better reflect on the actual instance
        for (Class<?> intf : svc.getClass().getInterfaces()) {
            for (final Method method : intf.getMethods()) {
                if (ProxyUtil.isMethodInInterface(remoteIntf, method)) {
                    epts.put(ProxyUtil.createCallSignature(intf, method), RPCEndpointFactory.createEndpointFor(genericSvc, method, bus));
                }
            }
        }

        bus.subscribe(remoteIntf.getName() + ":RPC", new RemoteServiceCallback(epts));

        // note: this method just exists because we want
        // AbstractRemoteCallBuilder to be package private.
        DefaultRemoteCallBuilder.setProxyFactory(Assert.notNull(new ProxyFactory() {
            @Override
            public <T> T getRemoteProxy(Class<T> proxyType) {
                throw new RuntimeException("There is not yet an available Errai RPC implementation for the server-side environment.");
            }
        }));
    }
}
