package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.StoredItemsDAO;
import cz.mzk.editor.shared.rpc.StoredItem;
import org.springframework.stereotype.Repository;

/**
 * Created by rumanekm on 6/18/15.
 */
@Repository
public class StoredItemsDAOImpl implements StoredItemsDAO {
    @Override
    public boolean checkStoredDigitalObject(long userId, StoredItem storedItem) throws DatabaseException {
        return false;
    }
}
