package cz.mzk.editor.server;

import com.google.inject.Injector;
import cz.mzk.editor.server.config.EditorConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
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
public class HtmlServletImpl  implements HttpRequestHandler {

    @Inject
    private EditorConfiguration config;


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("hostname", config.getHostname());
        request.setAttribute("enabledIdentities", config.getIdentityTypes());
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
