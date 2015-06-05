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

package cz.mzk.editor.server.cz.mzk.server.editor.api;

import java.util.ArrayList;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.shared.rpc.InputQueueItem;

/**
 * @author Matous Jobanek
 * @version Oct 24, 2012
 */
public interface ConversionDAO {

    /**
     * Insert conversion info.
     * 
     * @param directoryPath
     *        the directory path
     * @throws DatabaseException
     *         the database exception
     */
    void insertConversionInfo(String directoryPath, Long userId) throws DatabaseException;

    /**
     * Gets the conversion info.
     * 
     * @param data
     *        the data
     * @param numberOfDays
     *        the number of days
     * @return the conversion info
     * @throws DatabaseException
     *         the database exception
     */
    ArrayList<InputQueueItem> getConversionInfo(ArrayList<InputQueueItem> data, int numberOfDays)
            throws DatabaseException;
}
