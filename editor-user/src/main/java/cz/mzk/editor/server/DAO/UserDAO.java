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

package cz.mzk.editor.server.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.client.util.Constants.EDITOR_RIGHTS;
import cz.mzk.editor.client.util.Constants.USER_IDENTITY_TYPES;
import cz.mzk.editor.shared.rpc.RoleItem;
import cz.mzk.editor.shared.rpc.UserIdentity;
import cz.mzk.editor.shared.rpc.UserInfoItem;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDAO.
 */
public interface UserDAO {

    /** The Constant UNKNOWN. */
    public static final int UNKNOWN = -1;

    /** The Constant NOT_PRESENT. */
    public static final int NOT_PRESENT = 0;

    /** The Constant USER. */
    public static final int USER = 1;

    /** The Constant ADMIN. */
    public static final int ADMIN = 2;

    /** The Constant ADMIN_STRING. */
    public static final String ADMIN_STRING = "admin";

    /** The Constant EDIT_USERS_STRING. */
    public static final String EDIT_USERS_STRING = "edit_users";

    /** The Constant CAN_PUBLISH_STRING. */
    public static final String CAN_PUBLISH_STRING = "can_publish";

    /**
     * Checks for role.
     * 
     * @param role
     *        the role
     * @param userId
     *        the user id
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     */
    boolean hasRole(String role, long userId) throws DatabaseException;

    /**
     * Gets the name.
     * 
     * @param key
     *        the key
     * @return the name
     * @throws DatabaseException
     *         the database exception
     */
    String getName(Long key) throws DatabaseException;


    /**
     * Adds the remove user identity.
     * 
     * @param userIdentity
     *        the user identity
     * @param add
     *        the add
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     * @throws UnsupportedDataTypeException
     *         the unsupported data type exception
     */
    boolean addRemoveUserIdentity(UserIdentity userIdentity, boolean add) throws DatabaseException,
            UnsupportedDataTypeException;

    /**
     * Adds the remove role item.
     * 
     * @param roleItem
     *        the role item
     * @param add
     *        the add
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     * @throws UnsupportedDataTypeException
     *         the unsupported data type exception
     */
    boolean addRemoveUserRoleItem(RoleItem roleItem, boolean add) throws DatabaseException,
            UnsupportedDataTypeException;

    /**
     * Adds the remove user right item.
     * 
     * @param rightName
     *        the right name
     * @param userId
     *        the user id
     * @param add
     *        the add
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     * @throws UnsupportedDataTypeException
     *         the unsupported data type exception
     */
    boolean addRemoveUserRightItem(String rightName, Long userId, boolean add) throws DatabaseException,
            UnsupportedDataTypeException;

    /**
     * Gets the roles of user.
     * 
     * @param id
     *        the id
     * @return the roles of user
     * @throws DatabaseException
     *         the database exception
     */
    ArrayList<RoleItem> getRolesOfUser(long id) throws DatabaseException;

    /**
     * Gets the rights of user.
     * 
     * @param userId
     *        the user id
     * @return the rights of user
     * @throws DatabaseException
     *         the database exception
     */
    List<Constants.EDITOR_RIGHTS> getRightsOfUser(long userId) throws DatabaseException;

    /**
     * Gets the references to right.
     * 
     * @param rightName
     *        the right name
     * @param getRoles
     *        the get roles
     * @return the references to right
     * @throws DatabaseException
     *         the database exception
     */
    List<String> getReferencesToRight(String rightName, boolean getRoles) throws DatabaseException;

    /**
     * Check all rights.
     * 
     * @return the list
     * @throws DatabaseException
     *         the database exception
     */
    List<String> checkAllRights() throws DatabaseException;

    /**
     * Removes the right.
     * 
     * @param toRemove
     *        the to remove
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     */
    boolean removeRight(String toRemove) throws DatabaseException;

    /**
     * Update right.
     * 
     * @param toUpdate
     *        the to update
     * @throws DatabaseException
     *         the database exception
     */
    void updateRight(EDITOR_RIGHTS toUpdate) throws DatabaseException;

    /**
     * Insert right.
     * 
     * @param toInsert
     *        the to insert
     * @throws DatabaseException
     *         the database exception
     */
    void insertRight(EDITOR_RIGHTS toInsert) throws DatabaseException;

    /**
     * Gets the roles.
     * 
     * @return the roles
     * @throws DatabaseException
     *         the database exception
     */
    ArrayList<RoleItem> getRoles() throws DatabaseException;

    /**
     * Gets the identities.
     * 
     * @param userId
     *        the user id
     * @param type
     *        the type
     * @return the identities
     * @throws DatabaseException
     *         the database exception
     * @throws UnsupportedDataTypeException
     *         the unsupported data type exception
     */
    UserIdentity getIdentities(String userId, USER_IDENTITY_TYPES type) throws DatabaseException,
            UnsupportedDataTypeException;

    /**
     * Removes the user.
     * 
     * @param userId
     *        the user id
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     */
    boolean disableUser(long userId) throws DatabaseException;

    /**
     * Inser updatet user.
     * 
     * @param user
     *        the user
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     */
    Long insertUpdatetUser(UserInfoItem user) throws DatabaseException;

    /**
     * Gets the users.
     * 
     * @return the users
     * @throws DatabaseException
     *         the database exception
     */
    ArrayList<UserInfoItem> getUsers() throws DatabaseException;

    /**
     * Gets the user.
     * 
     * @return the user
     * @throws DatabaseException
     *         the database exception
     */
    UserInfoItem getUser() throws DatabaseException;

    /**
     * Removes the role item.
     * 
     * @param roleItem
     *        the role item
     * @param force
     *        the force
     * @return the list
     * @throws DatabaseException
     *         the database exception
     */
    List<String> removeRoleItem(RoleItem roleItem, boolean force) throws DatabaseException;

    /**
     * Adds the remove role item.
     * 
     * @param roleItem
     *        the role item
     * @param add
     *        the add
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     */
    boolean addRemoveRoleItem(RoleItem roleItem, boolean add) throws DatabaseException;

    /**
     * Select right in role items.
     * 
     * @param roleName
     *        the role name
     * @return the list
     * @throws DatabaseException
     *         the database exception
     */
    List<EDITOR_RIGHTS> selectRightInRoleItems(String roleName) throws DatabaseException;

    /**
     * Checks for user right.
     * 
     * @param right
     *        the right
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     */
    boolean hasUserRight(EDITOR_RIGHTS right) throws DatabaseException;
}
