package cz.mzk.editor.server;

import com.google.inject.Injector;
import cz.mzk.editor.server.config.EditorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rumanekm on 6.3.14.
 */
public class HtmlServletImpl  extends HttpServlet {

    private static final long serialVersionUID = 2531308251787968311L;

    @Inject
    private EditorConfiguration config;


    @Override
    public void init(ServletConfig config) throws ServletException {
        WebApplicationContextUtils
                .getRequiredWebApplicationContext(config.getServletContext())
                .getAutowireCapableBeanFactory()
                .autowireBean(this);
        super.init();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hostname", config.getHostname());
        request.setAttribute("enabledIdentities", config.getIdentityTypes());
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
