/*
 * Metadata Editor
 * 
 * Metadata Editor - Rich internet application for editing metadata.
 * Copyright (C) 2011  Matous Jobanek (matous.jobanek@mzk.cz)
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

package cz.mzk.editor.server.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.client.util.Constants.USER_IDENTITY_TYPES;
import cz.mzk.editor.server.jooq.tables.EditorUser;
import cz.mzk.editor.server.jooq.tables.LdapIdentity;
import cz.mzk.editor.server.jooq.tables.EditorUser;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;

/**
 * @author Matous Jobanek
 * @version Nov 15, 2012
 */
public class SecurityUserDAOImpl implements SecurityUserDAO {


    @Inject
    private DSLContext create;

    public Long getUserId(String identifier, USER_IDENTITY_TYPES type, boolean closeCon)
            throws DatabaseException, SQLException {

        long userId = -1;

        if (type.equals(USER_IDENTITY_TYPES.LDAP)) {
            EditorUser editorUser = EditorUser.EDITOR_USER;
            Record1<Integer> result = create.select(editorUser.ID).from(editorUser).join(LdapIdentity.LDAP_IDENTITY).onKey()
                    .where(LdapIdentity.LDAP_IDENTITY.IDENTITY.eq(identifier).and(editorUser.STATE.isTrue())).fetchOne();
            return new Long(result.value1());
        }

        return userId;

        //return super.getUsersId(identifier, type, closeCon);
    }

    /**
     * {@inheritDoc}
     */
    public String getName(Long userId) throws DatabaseException {
        return "medit";
        //return daoUtils.getName(userId);
    }

//    private long getUsersId(String identifier, USER_IDENTITY_TYPES type, boolean closeCon)
//            throws DatabaseException, SQLException {
//        PreparedStatement selectSt = null;
//        long userId = -1;
//        try {
//            StringBuffer sql = new StringBuffer("SELECT editor_user_id FROM (");
//            String tableIdentity = "";
//
//            switch (type) {
//                case OPEN_ID:
//                    tableIdentity = Constants.TABLE_OPEN_ID_IDENTITY;
//                    break;
//                case SHIBBOLETH:
//                    tableIdentity = Constants.TABLE_SHIBBOLETH_IDENTITY;
//                    break;
//                case LDAP:
//                    tableIdentity = Constants.TABLE_LDAP_IDENTITY;
//                    break;
//                default:
//                    return Constants.DEFAULT_SYSTEM_USERS.NON_EXISTENT.getUserId();
//            }
//            sql.append(tableIdentity).append(" INNER JOIN editor_user ON ").append(tableIdentity)
//                    .append(".editor_user_id = editor_user.id)");
//            sql.append(" WHERE state = 'true' AND identity = (?)");
//            selectSt = getConnection().prepareStatement(sql.toString());
//            selectSt.setString(1, identifier);
//
//        } catch (SQLException e) {
//            LOGGER.error("Could not process select statement: " + selectSt, e);
//        }
//        try {
//            ResultSet rs = selectSt.executeQuery();
//            while (rs.next()) {
//                userId = rs.getLong("editor_user_id");
//            }
//        } catch (SQLException e) {
//            LOGGER.error("Query: " + selectSt, e);
//            if (closeCon) {
//                e.printStackTrace();
//            } else {
//                throw new SQLException(e);
//            }
//        } finally {
//            if (closeCon) closeConnection();
//        }
//        return userId;
//    }
}
