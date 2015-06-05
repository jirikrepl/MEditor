package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.InputQueueItemDAO;
import cz.mzk.editor.shared.rpc.IngestInfo;
import cz.mzk.editor.shared.rpc.InputQueueItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/4/15.
 */
@Repository
public class InputQueueItemDAOImpl implements InputQueueItemDAO {
    @Override
    public void updateItems(List<InputQueueItem> toUpdate) throws DatabaseException {

    }

    @Override
    public ArrayList<InputQueueItem> getItems(String prefix) throws DatabaseException {
        return null;
    }

    @Override
    public void updateName(String path, String name) throws DatabaseException {

    }

    @Override
    public List<IngestInfo> getIngestInfo(String path) throws DatabaseException {
        return null;
    }

    @Override
    public boolean hasBeenIngested(String path) throws DatabaseException {
        return false;
    }

    @Override
    public void setIngested(String path) throws DatabaseException {

    }
}
