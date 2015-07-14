/*
 * Metadata Editor
 * @author Jiri Kremser
 * 
 * 
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Jiri Kremser (kremser@mzk.cz)
 * Moravian Library in Brno
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 
 */

package cz.mzk.editor.server.handler;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.inject.Named;
import javax.servlet.http.HttpSession;

import javax.inject.Inject;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.EditorUserAuthentication;
import cz.mzk.editor.server.URLS;
import cz.mzk.editor.server.util.UserProvider;
import org.apache.log4j.Logger;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.RecentlyModifiedItemDAO;
import cz.mzk.editor.server.config.EditorConfiguration;
import cz.mzk.editor.server.util.ServerUtils;
import cz.mzk.editor.shared.rpc.RecentlyModifiedItem;
import cz.mzk.editor.shared.rpc.action.GetRecentlyModifiedAction;
import cz.mzk.editor.shared.rpc.action.GetRecentlyModifiedResult;
import org.springframework.security.core.context.SecurityContext;

// TODO: Auto-generated Javadoc
/**
 * The Class GetRecentlyModifiedHandler.
 */
@Named
public class GetRecentlyModifiedHandler
        implements ActionHandler<GetRecentlyModifiedAction, GetRecentlyModifiedResult> {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger(GetRecentlyModifiedHandler.class.getPackage()
            .toString());

    /** The configuration. */
    private final EditorConfiguration configuration;

    /** The recently modified dao. */
    @Inject
    private RecentlyModifiedItemDAO recentlyModifiedDAO;

    /** The http session provider. */
    @Inject
    private HttpSession session;

    @Inject
    private UserProvider userProvider;

    /** The GetLockInformationHandler handler */
//    private final GetLockInformationHandler getLockInformationHandler;

    /** The dao utils. */
//    @Inject
//    private DAOUtils daoUtils;

    @Inject
    private ServerUtils serverUtils;

    /**
     * Instantiates a new gets the recently modified handler.
     * 
     * @param configuration
     *        the configuration
     * @param recentlyModifiedDAO
     *        the recently modified dao
     */
    @Inject
    public GetRecentlyModifiedHandler(final EditorConfiguration configuration,
                                      final RecentlyModifiedItemDAO recentlyModifiedDAO) {
        this.configuration = configuration;
        this.recentlyModifiedDAO = recentlyModifiedDAO;
        //this.getLockInformationHandler = new GetLockInformationHandler();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#execute(com
     * .gwtplatform.dispatch.shared.Action,
     * com.gwtplatform.dispatch.server.ExecutionContext)
     */
    @Override
    public GetRecentlyModifiedResult execute(final GetRecentlyModifiedAction action,
                                             final ExecutionContext context) throws ActionException {
        LOGGER.debug("Processing action: GetRecentlyModified");
        serverUtils.checkExpiredSession();

//        Injector injector = (Injector) session.getServletContext().getAttribute(Injector.class.getName());
//        injector.injectMembers(getLockInformationHandler);

        try {

            ArrayList<RecentlyModifiedItem> recItems = null;
            if (action.isForAllUsers()) {
                recItems = recentlyModifiedDAO.getItems(configuration.getRecentlyModifiedNumber(), null);
            } else {
                recItems =
                        recentlyModifiedDAO.getItems(configuration.getRecentlyModifiedNumber(),
                                                     getUserId(true));
            }

//            for (RecentlyModifiedItem item : recItems) {
//                LockInfo lockInfo =
//                        getLockInformationHandler.execute(new GetLockInformationAction(item.getUuid()),
//                                context).getLockInfo();
//                item.setLockInfo(lockInfo);
//            }
            return new GetRecentlyModifiedResult(recItems);
        } catch (DatabaseException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new ActionException(e);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new ActionException(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#getActionType
     * ()
     */
    @Override
    public Class<GetRecentlyModifiedAction> getActionType() {
        return GetRecentlyModifiedAction.class;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.gwtplatform.dispatch.server.actionhandler.ActionHandler#undo(com.
     * gwtplatform.dispatch.shared.Action,
     * com.gwtplatform.dispatch.shared.Result,
     * com.gwtplatform.dispatch.server.ExecutionContext)
     */
    @Override
    public void undo(GetRecentlyModifiedAction action,
                     GetRecentlyModifiedResult result,
                     ExecutionContext context) throws ActionException {
        // TODO Auto-generated method stub

    }

    protected Long getUserId(boolean closeCon) throws DatabaseException, SQLException {
        SecurityContext secContext =
                (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        EditorUserAuthentication authentication = null;
        if (secContext != null) authentication = (EditorUserAuthentication) secContext.getAuthentication();
        if (authentication != null) {
            return userProvider.getUserId();
        } else {
            throw new DatabaseException(Constants.SESSION_EXPIRED_FLAG + URLS.ROOT()
                    + (URLS.LOCALHOST() ? URLS.LOGIN_LOCAL_PAGE : URLS.LOGIN_PAGE));
        }
    }
}