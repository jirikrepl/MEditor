package cz.mzk.editor.server;

import com.google.inject.Injector;
import com.googlecode.flyway.core.Flyway;
import cz.mzk.editor.server.config.EditorConfiguration;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: Martin Rumanek
 * @version: 9.5.13
 */
public class FlywayListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(FlywayListener.class);

    @Inject
    private EditorConfiguration configuration;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
            WebApplicationContextUtils
                    .getRequiredWebApplicationContext(servletContextEvent.getServletContext())
                    .getAutowireCapableBeanFactory()
                    .autowireBean(this);


        Flyway flyway = new Flyway();
        String login = configuration.getDBLogin();
        String password = configuration.getDBPassword();
        String host = configuration.getDBHost();
        String port = configuration.getDBPort();
        String name = configuration.getDBName();
        flyway.setDataSource("jdbc:postgresql://" + host + ":" + port + "/" + name, login, password);
        flyway.setInitOnMigrate(true);
        flyway.migrate();

        LOGGER.info("DB schema has been updated to version: " + flyway.getTarget().toString());

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}