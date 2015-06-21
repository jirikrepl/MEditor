package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.DAO.UserDAO;
import cz.mzk.editor.server.jooq.tables.*;
import cz.mzk.editor.shared.rpc.RoleItem;
import cz.mzk.editor.shared.rpc.UserIdentity;
import cz.mzk.editor.shared.rpc.UserInfoItem;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import javax.activation.UnsupportedDataTypeException;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.insertInto;
import static org.jooq.impl.DSL.val;

/**
 * Created by rumanekm on 6/7/15.
 */
//TODO-MR crud log? why?
@Repository
public class UserDAOImpl implements UserDAO {

    @Inject
    private DSLContext dsl;

    @Override
    public boolean hasRole(String role, long userId) throws DatabaseException {
        ArrayList<RoleItem> roles = getRolesOfUser(userId);
        if (role == null) return false;
        for (RoleItem candidateRole : roles) {
            if (candidateRole != null && candidateRole.getName() != null
                    && candidateRole.getName().equals(role)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName(Long key) throws DatabaseException {
        EditorUser editorUserTable = EditorUser.EDITOR_USER;
        return dsl.select(concat(editorUserTable.NAME, val(" "), editorUserTable.SURNAME)).from(editorUserTable)
                .where(editorUserTable.ID.eq(key.intValue())).fetchOne().value1();

    }

    @Override
    public boolean addRemoveUserIdentity(UserIdentity userIdentity, boolean add) throws DatabaseException, UnsupportedDataTypeException {
        if (userIdentity == null) throw new NullPointerException("identity");
        if (userIdentity.getIdentities() == null || userIdentity.getIdentities().get(0) == null
                || "".equals(userIdentity.getIdentities()) || userIdentity.getUserId() == null)
            throw new NullPointerException();

        if (add) {
            addUserIdentity(userIdentity);
        } else {
            removeUserIdentity(userIdentity);
        }

        return true;
    }

    private void addUserIdentity(UserIdentity userIdentity) throws DatabaseException {

        Integer userId = userIdentity.getUserId().intValue();
        String identity = userIdentity.getIdentities().get(0);

        switch (userIdentity.getType()) {
            case OPEN_ID:
                OpenIdIdentity openIdIdentityTable = OpenIdIdentity.OPEN_ID_IDENTITY;
                dsl.insertInto(openIdIdentityTable, openIdIdentityTable.EDITOR_USER_ID, openIdIdentityTable.IDENTITY)
                        .values(userId, identity).execute();
                break;
            case SHIBBOLETH:
                ShibbolethIdentity shibbolethIdentity = ShibbolethIdentity.SHIBBOLETH_IDENTITY;
                dsl.insertInto(shibbolethIdentity, shibbolethIdentity.EDITOR_USER_ID, shibbolethIdentity.IDENTITY)
                        .values(userId, identity).execute();
                break;
            case LDAP:
                LdapIdentity ldapIdentity = LdapIdentity.LDAP_IDENTITY;
                dsl.insertInto(ldapIdentity, ldapIdentity.EDITOR_USER_ID, ldapIdentity.IDENTITY)
                        .values(userId, identity).execute();
                break;
            default:
                throw new DatabaseException("Unknown type of identity");
        }
    }

    private void removeUserIdentity(UserIdentity userIdentity) throws DatabaseException {
        Integer userId = userIdentity.getUserId().intValue();
        String identity = userIdentity.getIdentities().get(0);

        switch (userIdentity.getType()) {
            case OPEN_ID:
                OpenIdIdentity openIdIdentityTable = OpenIdIdentity.OPEN_ID_IDENTITY;
                dsl.delete(openIdIdentityTable)
                        .where(openIdIdentityTable.EDITOR_USER_ID.eq(userId), openIdIdentityTable.IDENTITY.eq(identity)).execute();
                break;
            case SHIBBOLETH:
                ShibbolethIdentity shibbolethIdentity = ShibbolethIdentity.SHIBBOLETH_IDENTITY;
                dsl.delete(shibbolethIdentity)
                        .where(shibbolethIdentity.EDITOR_USER_ID.eq(userId), shibbolethIdentity.IDENTITY.eq(identity)).execute();
                break;
            case LDAP:
                LdapIdentity ldapIdentity = LdapIdentity.LDAP_IDENTITY;
                dsl.delete(ldapIdentity)
                        .where(ldapIdentity.EDITOR_USER_ID.eq(userId), ldapIdentity.IDENTITY.eq(identity)).execute();
                break;
            default:
                throw new DatabaseException("Unknown type of identity");
        }
    }



    @Override
    public boolean addRemoveUserRoleItem(RoleItem roleItem, boolean add) throws DatabaseException, UnsupportedDataTypeException {
        if (roleItem == null) throw new NullPointerException("role");
        if (roleItem.getName() == null || "".equals(roleItem.getName()) || roleItem.getUserId() == null)
            throw new NullPointerException();
        if (add && hasRole(roleItem.getName(), roleItem.getUserId())) {
            return true;
        }

        UsersRole roleTable = UsersRole.USERS_ROLE;
        Integer userId = roleItem.getUserId().intValue();
        String roleName = roleItem.getName();
        if (add) {
            dsl.insertInto(roleTable, roleTable.EDITOR_USER_ID, roleTable.ROLE_NAME)
                    .values(roleItem.getUserId().intValue(), roleItem.getName()).execute();
        } else {
            dsl.delete(roleTable).where(roleTable.EDITOR_USER_ID.eq(userId), roleTable.ROLE_NAME.eq(roleName)).execute();
        }
        return true;
    }

    @Override
    public boolean addRemoveUserRightItem(String rightName, Long userId, boolean add) throws DatabaseException, UnsupportedDataTypeException {
        if (rightName == null || "".equals(rightName)) throw new NullPointerException("right");

        UsersRight rightTable = UsersRight.USERS_RIGHT;
        if (add) {
            dsl.insertInto(rightTable, rightTable.EDITOR_USER_ID, rightTable.EDITOR_RIGHT_NAME)
                    .values(userId.intValue(), rightName).execute();
        } else {
            dsl.delete(rightTable)
                    .where(rightTable.EDITOR_USER_ID.eq(userId.intValue()), rightTable.EDITOR_RIGHT_NAME.eq(rightName))
                    .execute();
        }
        return true;
    }

    @Override
    public ArrayList<RoleItem> getRolesOfUser(long userId) throws DatabaseException {

        ArrayList<RoleItem> retList = new ArrayList<RoleItem>();
        String SELECT_ROLES_OF_USER_STATEMENT = "SELECT name, description FROM "
                + Constants.TABLE_ROLE + " WHERE name IN ( SELECT role_name FROM " + Constants.TABLE_USERS_ROLE
                + " WHERE editor_user_id = (?) )";


        Role roleTable = Role.ROLE;
        UsersRole usersRoleTable = UsersRole.USERS_ROLE;
        Result<Record2<String, String>> result =  dsl.select(roleTable.NAME, roleTable.DESCRIPTION).from(roleTable)
                .where(roleTable.NAME.in(dsl.select(usersRoleTable.ROLE_NAME).from(usersRoleTable)
                        .where(usersRoleTable.EDITOR_USER_ID.eq(new Long(userId).intValue())))).fetch();

        for (Record2<String, String> role : result) {
            retList.add(new RoleItem(userId, role.value1(), role.value2()));
        }

        return retList;
    }

    @Override
    public List<Constants.EDITOR_RIGHTS> getRightsOfUser(long userId) throws DatabaseException {
        ArrayList<Constants.EDITOR_RIGHTS> retList = new ArrayList<Constants.EDITOR_RIGHTS>();

        EditorRight editorRight = EditorRight.EDITOR_RIGHT;
        UsersRight usersRightTable = UsersRight.USERS_RIGHT;
        Result<Record1<String>> result = dsl.select(editorRight.NAME).from(editorRight).where(editorRight.NAME
                .in(dsl.select(usersRightTable.EDITOR_RIGHT_NAME).from(usersRightTable)
                        .where(usersRightTable.EDITOR_USER_ID.eq(new Long(userId).intValue())))).fetch();
        for (Record1<String> record1 : result) {
            Constants.EDITOR_RIGHTS right = Constants.EDITOR_RIGHTS.parseString(record1.value1());
            if (right != null) retList.add(right);
        }

        return retList;
    }

    @Override
    public List<String> getReferencesToRight(String rightName, boolean getRoles) throws DatabaseException {
        return null;
    }

    @Override
    public List<String> checkAllRights() throws DatabaseException {
        return null;
    }

    @Override
    public boolean removeRight(String toRemove) throws DatabaseException {
        return false;
    }

    @Override
    public void updateRight(Constants.EDITOR_RIGHTS toUpdate) throws DatabaseException {

    }

    @Override
    public void insertRight(Constants.EDITOR_RIGHTS toInsert) throws DatabaseException {

    }

    @Override
    public ArrayList<RoleItem> getRoles() throws DatabaseException {
        return null;
    }

    @Override
    public UserIdentity getIdentities(String userId, Constants.USER_IDENTITY_TYPES type) throws DatabaseException, UnsupportedDataTypeException {
        return null;
    }

    @Override
    public boolean disableUser(long userId) throws DatabaseException {
        return false;
    }

    @Override
    public Long insertUpdatetUser(UserInfoItem user) throws DatabaseException {
        return null;
    }

    @Override
    public ArrayList<UserInfoItem> getUsers() throws DatabaseException {
        return null;
    }

    @Override
    public UserInfoItem getUser() throws DatabaseException {
        return null;
    }

    @Override
    public List<String> removeRoleItem(RoleItem roleItem, boolean force) throws DatabaseException {
        return null;
    }

    @Override
    public boolean addRemoveRoleItem(RoleItem roleItem, boolean add) throws DatabaseException {
        return false;
    }

    @Override
    public List<Constants.EDITOR_RIGHTS> selectRightInRoleItems(String roleName) throws DatabaseException {
        return null;
    }

    @Override
    public boolean hasUserRight(Constants.EDITOR_RIGHTS right) throws DatabaseException {
        return false;
    }
}
