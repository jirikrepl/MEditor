package cz.mzk.editor.server.config;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rumanekm on 6/22/15.
 */
@Named
public class ContextInitializer implements InitializingBean {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(ContextInitializer.class);

    @Inject
    private EditorConfiguration configuration;

    @Override
    public void afterPropertiesSet() {
        String catalinaHome = System.getProperty("catalina.home");
        catalinaHome = "/usr/local/tomcat/";
        if (catalinaHome != null) {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();
            VelocityContext context = new VelocityContext();
            context.put("login", configuration.getDBLogin());
            context.put("host", configuration.getDBHost());
            context.put("password", configuration.getDBPassword());
            context.put("port", configuration.getDBPort());
            context.put("name", configuration.getDBPort());
            Template t = ve.getTemplate("context.xml");
            File contextFile =
                    new File(catalinaHome + File.separator + "conf" + File.separator + "Catalina" + File.separator
                            + "localhost" + File.separator + "meditor.xml");
            FileWriter writer = null;
            try {
                writer = new FileWriter(contextFile);
                t.merge(context, writer);
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }

        }

    }
}
