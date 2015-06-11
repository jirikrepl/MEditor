package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.InputQueueItemDAO;
import cz.mzk.editor.server.jooq.tables.InputQueue;
import cz.mzk.editor.shared.rpc.IngestInfo;
import cz.mzk.editor.shared.rpc.InputQueueItem;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/4/15.
 */
@Repository
public class InputQueueItemDAOImpl implements InputQueueItemDAO {

    @Inject
    private DSLContext create;

    public static final String FIND_ITEMS_ON_TOP_LVL_STATEMENT =
            "SELECT p.path, p.barcode, p.ingested, n.name FROM " + Constants.TABLE_INPUT_QUEUE_ITEM
                    + " p LEFT JOIN " + Constants.TABLE_INPUT_QUEUE + " n ON(p.path=n.directory_path) "
                    + "WHERE position('" + File.separator + "' IN trim(leading ((?)) FROM p.path)) = 0";

    /**
     * The Constant FIND_ITEMS_ON_TOP_LVL_STATEMENT_ORDERED.
     */
    public static final String FIND_ITEMS_ON_TOP_LVL_STATEMENT_ORDERED = FIND_ITEMS_ON_TOP_LVL_STATEMENT
            + " ORDER BY p.path";

    @Override
    public void updateItems(List<InputQueueItem> toUpdate) throws DatabaseException {

    }

    @Override
    public ArrayList<InputQueueItem> getItems(String prefix) throws DatabaseException {
        boolean top = (prefix == null || "".equals(prefix));

        ArrayList<InputQueueItem> retList = new ArrayList<InputQueueItem>();

        // FIND_ITEMS_ON_TOP_LVL_STATEMENT_ORDERED
        if (top) {
            create.select(cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM.PATH,
                    cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM.BARCODE,
                    cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM.INGESTED,
                    InputQueue.INPUT_QUEUE.NAME).from(cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM)
                    .join(InputQueue.INPUT_QUEUE);
        }

        return retList;

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
