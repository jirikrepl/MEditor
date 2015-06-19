package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.DAO.StoredAndLocksDAO;
import cz.mzk.editor.shared.rpc.ActiveLockItem;
import cz.mzk.editor.shared.rpc.StoredItem;
import cz.mzk.editor.shared.rpc.TreeStructureInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/19/15.
 */
@Repository
public class StoredAndLocksDAOImpl implements StoredAndLocksDAO {
    @Override
    public List<StoredItem> getAllStoredWorkingCopyItems(Long userId) throws DatabaseException {
        return null;
    }

    @Override
    public boolean deleteStoredWorkingCopyItem(Long id) throws DatabaseException {
        return false;
    }

    @Override
    public ArrayList<TreeStructureInfo> getAllSavedTreeStructures(Long userId) throws DatabaseException {
        return null;
    }

    @Override
    public boolean removeSavedStructure(long id) throws DatabaseException {
        return false;
    }

    @Override
    public boolean unlockDigitalObject(String uuid) throws DatabaseException {
        return false;
    }

    @Override
    public Long getLockId(String uuid) throws DatabaseException {
        return null;
    }

    @Override
    public List<ActiveLockItem> getAllActiveLocks(Long userId) throws DatabaseException {
        return null;
    }
}
