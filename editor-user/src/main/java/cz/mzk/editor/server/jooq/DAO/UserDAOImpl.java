package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.DAO.UserDAO;
import cz.mzk.editor.shared.rpc.RoleItem;
import cz.mzk.editor.shared.rpc.UserIdentity;
import cz.mzk.editor.shared.rpc.UserInfoItem;
import org.springframework.stereotype.Repository;

import javax.activation.UnsupportedDataTypeException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/7/15.
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public int isSupported(String identifier) throws DatabaseException {
        return 0;
    }

    @Override
    public boolean hasRole(String role, long userId) throws DatabaseException {
        return false;
    }

    @Override
    public String getName(Long key) throws DatabaseException {
        return null;
    }

    @Override
    public String getName() throws DatabaseException {
        return null;
    }

    @Override
    public boolean addRemoveUserIdentity(UserIdentity userIdentity, boolean add) throws DatabaseException, UnsupportedDataTypeException {
        return false;
    }

    @Override
    public boolean addRemoveUserRoleItem(RoleItem roleItem, boolean add) throws DatabaseException, UnsupportedDataTypeException {
        return false;
    }

    @Override
    public boolean addRemoveUserRightItem(String rightName, Long userId, boolean add) throws DatabaseException, UnsupportedDataTypeException {
        return false;
    }

    @Override
    public ArrayList<RoleItem> getRolesOfUser(long id) throws DatabaseException {
        return null;
    }

    @Override
    public List<Constants.EDITOR_RIGHTS> getRightsOfUser(long userId) throws DatabaseException {
        return null;
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
