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

package cz.mzk.editor.server.cz.mzk.server.editor.api;

import java.util.ArrayList;
import java.util.List;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.shared.rpc.TreeStructureBundle.TreeStructureNode;
import cz.mzk.editor.shared.rpc.TreeStructureInfo;

/**
 * @author Jiri Kremser
 * @version 25. 1. 2011
 */
public interface TreeStructureDAO {

    /**
     * Returns all saved structures of user
     * 
     * @param userId
     *        id of the user
     * @return list of TreeStructureInfo instances belonging to specified user
     * @throws DatabaseException
     */
    ArrayList<TreeStructureInfo> getAllSavedStructuresOfUser(long userId) throws DatabaseException;

    /**
     * Returns saved structures of user of an object
     * 
     * @param userId
     *        id of the user
     * @param code
     *        code
     * @return list of TreeStructureInfo instances belonging to specified user
     * @throws DatabaseException
     */
    ArrayList<TreeStructureInfo> getSavedStructuresOfUser(long userId, String code) throws DatabaseException;

    /**
     * Returns all saved structures
     * 
     * @param code
     * @return list of TreeStructureInfo instances belonging to specified user
     * @throws DatabaseException
     */
    ArrayList<TreeStructureInfo> getAllSavedStructures(String code) throws DatabaseException;

    /**
     * Deletes the structure from DB
     * 
     * @param id
     *        id of the record
     * @return
     * @throws DatabaseException
     */
    boolean removeSavedStructure(long id) throws DatabaseException;

    /**
     * Returns the list of TreeStructureNode representing the unfinished work in
     * editor
     * 
     * @param structureId
     *        id of a stored structure
     * @throws DatabaseException
     */
    ArrayList<TreeStructureNode> loadStructure(long structureId) throws DatabaseException;

    /**
     * Saves the unfinished work into DB
     * 
     * @param userId
     *        user's id
     * @param info
     *        Information about the tree such as description
     * @param structure
     *        list with TreeStructureNode instances
     * @return
     * @throws DatabaseException
     */
    boolean saveStructure(long userId, TreeStructureInfo info, List<TreeStructureNode> structure)
            throws DatabaseException;

}
