package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.InputQueueItemDAO;
import cz.mzk.editor.server.jooq.tables.InputQueue;
import cz.mzk.editor.shared.rpc.IngestInfo;
import cz.mzk.editor.shared.rpc.InputQueueItem;
import org.jooq.DSLContext;
import static org.jooq.impl.DSL.*;
import org.jooq.Record4;
import org.jooq.Result;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/4/15.
 */
@Repository
public class InputQueueItemDAOImpl implements InputQueueItemDAO {

    @Inject
    private DSLContext dsl;

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
        if (toUpdate == null) throw new NullPointerException("toUpdate");


//        ResultSet rs = selectCount.executeQuery();
//        SELECT count(path) FROM "
//                + Constants.TABLE_INPUT_QUEUE_ITEM


        cz.mzk.editor.server.jooq.tables.InputQueueItem inputQueueItemTable = cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM;
        int totalBefore = dsl.select(count(inputQueueItemTable.PATH)).from(inputQueueItemTable).fetchOne().value1();

        int deleted = dsl.delete(inputQueueItemTable).execute();

        int updated = 0;
        for (InputQueueItem item : toUpdate) {
            updated += dsl.insertInto(inputQueueItemTable, inputQueueItemTable.PATH, inputQueueItemTable.BARCODE, inputQueueItemTable.INGESTED)
                    .values(item.getPath(), item.getBarcode(), item.getIngestInfo()).execute();

            //updated += getItemInsertStatement(item).executeUpdate();
        }
        if (totalBefore == deleted && updated == toUpdate.size()) {
            //TODO-MR
        } else {
            //TODO-MR error, rollback
        }

    }

    @Override
    public ArrayList<InputQueueItem> getItems(String prefix) throws DatabaseException {
        boolean top = (prefix == null || "".equals(prefix));

        ArrayList<InputQueueItem> retList = new ArrayList<InputQueueItem>();

        // FIND_ITEMS_ON_TOP_LVL_STATEMENT_ORDERED
        if (top) {
            Result<Record4<String, String, Boolean, String>> results = dsl.select(cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM.PATH,
                    cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM.BARCODE,
                    cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM.INGESTED,
                    InputQueue.INPUT_QUEUE.NAME).from(cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM)
                    .join(InputQueue.INPUT_QUEUE)
                    .on(cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM.PATH.eq(InputQueue.INPUT_QUEUE.DIRECTORY_PATH)).fetch();
            for (Record4<String, String, Boolean, String> r : results) {
                retList.add(new InputQueueItem(r.value1(), r.value2(), r.value3(), r.value4()));
            }
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
