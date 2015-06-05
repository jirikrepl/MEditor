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

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.shared.rpc.RecentlyModifiedItem;

// TODO: Auto-generated Javadoc
/**
 * The Interface RecentlyModifiedItemDAO.
 */
public interface RecentlyModifiedItemDAO {

    /**
     * Put.
     * 
     * @param toPut
     *        the to put
     * @return true, if successful
     * @throws DatabaseException
     *         the database exception
     */
    boolean put(RecentlyModifiedItem toPut) throws DatabaseException;

    /**
     * Gets the items.
     * 
     * @param nLatest
     *        the n latest
     * @param user_id
     *        the user_id
     * @return the items
     * @throws DatabaseException
     *         the database exception
     */
    ArrayList<RecentlyModifiedItem> getItems(int nLatest, Long user_id) throws DatabaseException;

}
