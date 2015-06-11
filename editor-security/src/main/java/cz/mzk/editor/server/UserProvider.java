package cz.mzk.editor.server;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DAOUtils;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.DAO.SecurityUserDAO;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by rumanekm on 28.3.14.
 */
@Component
public class UserProvider {

    /** The http session provider. */
    @Inject
    private HttpSession session;

    @Inject
    SecurityUserDAO securityUserDAO;


    public Long getUserId() throws DatabaseException {
        try {
            SecurityContext secContext =
                    (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
            EditorUserAuthentication authentication = null;
            if (secContext != null) authentication = (EditorUserAuthentication) secContext.getAuthentication();
            if (authentication != null) {
                return securityUserDAO.getUserId((String) authentication.getPrincipal(), authentication.getIdentityType(), true);
            } else {
                throw new DatabaseException(Constants.SESSION_EXPIRED_FLAG + URLS.ROOT()
                        + (URLS.LOCALHOST() ? URLS.LOGIN_LOCAL_PAGE : URLS.LOGIN_PAGE));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.toString());
        }
    }

    public boolean checkUserRightOrAll(Constants.EDITOR_RIGHTS right) {

        return checkUserRight(Constants.EDITOR_RIGHTS.ALL) || checkUserRight(right);
    }

    //TODO-MR: implement!
    private boolean checkUserRight(Constants.EDITOR_RIGHTS right) {
        return true;
    }
}
