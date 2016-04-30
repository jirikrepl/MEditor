package cz.mzk.editor.server;

import cz.mzk.editor.server.DAO.UserDaoNew;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by rumanekm on 28.3.14.
 */
public class UserProvider {

    /**
     * The http session provider.
     */
    @Inject
    private Provider<HttpSession> httpSessionProvider;

    @Inject
    UserDaoNew userDaoNew;

    public final Long getUserId() {
        SecurityContext secContext =
                (SecurityContext) httpSessionProvider.get().getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = secContext.getAuthentication();
        return userDaoNew.getUserIdFromPrincipal(authentication.getPrincipal().toString());
    }

    public final String getName() {
        return "test";
    }
}
