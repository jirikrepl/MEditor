package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DAOUtilsImpl;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/4/15.
 */
@Repository
public class InputQueueItemDAOImpl implements InputQueueItemDAO {

    @Inject
    private DSLContext dsl;

    @Override
    public void updateItems(List<InputQueueItem> toUpdate) throws DatabaseException {
        if (toUpdate == null) throw new NullPointerException("toUpdate");

        cz.mzk.editor.server.jooq.tables.InputQueueItem inputQueueItemTable = cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM;
        int totalBefore = dsl.select(count(inputQueueItemTable.PATH)).from(inputQueueItemTable).fetchOne().value1();

        int deleted = dsl.delete(inputQueueItemTable).execute();

        int updated = 0;
        for (InputQueueItem item : toUpdate) {
            updated += dsl.insertInto(inputQueueItemTable, inputQueueItemTable.PATH, inputQueueItemTable.BARCODE, inputQueueItemTable.INGESTED)
                    .values(item.getPath(), item.getBarcode(), item.getIngestInfo()).execute();
        }
        if (totalBefore != deleted || updated != toUpdate.size()) {
            throw new DatabaseException("Update items was not successfull");
        }
    }

    /**
     * List directory contents
     *
     * @param prefix
     *        path
     * @return the items
     * @throws DatabaseException
     */
    @Override
    public ArrayList<InputQueueItem> getItems(String prefix) throws DatabaseException {
        boolean top = (prefix == null || "".equals(prefix));

        ArrayList<InputQueueItem> retList = new ArrayList<InputQueueItem>();

        cz.mzk.editor.server.jooq.tables.InputQueueItem inputQueueItemTable = cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM;
        InputQueue inputQueueTable = InputQueue.INPUT_QUEUE;

        Result<Record4<String, String, Boolean, String>> results;

        if (top) {
            results = dsl.select(inputQueueItemTable.PATH,
                    inputQueueItemTable.BARCODE,
                    inputQueueItemTable.INGESTED,
                    value("")).from(inputQueueItemTable)
                    .where("position('/' IN trim(leading '/' FROM path)) = 0")
                    .orderBy(inputQueueItemTable.PATH)
                    .fetch();
        } else {
            results = dsl.select(inputQueueItemTable.PATH,
                    inputQueueItemTable.BARCODE,
                    inputQueueItemTable.INGESTED,
                    inputQueueTable.NAME).from(inputQueueItemTable)
                    .leftOuterJoin(inputQueueTable)
                    .on(inputQueueTable.DIRECTORY_PATH.eq(inputQueueItemTable.PATH))
                    .where(condition("position('/' IN trim(leading {0} FROM path)) = 0", prefix + "/"))
                    .and(inputQueueItemTable.PATH.like("%" + prefix + "/%"))
                    .orderBy(inputQueueItemTable.PATH)
                    .fetch();
        }

        for (Record4<String, String, Boolean, String> r : results) {
            retList.add(new InputQueueItem(r.value1(), r.value2(), r.value3(), r.value4()));
        }

        return retList;
    }

    @Override
    public void updateName(String path, String name) throws DatabaseException {
        this.checkInputQueue(path, name);
    }

    @Override
    public List<IngestInfo> getIngestInfo(String path) throws DatabaseException {
        String SELECT_INGEST_INFO =
                "SELECT eu.surname || ', ' || eu.name as full_name, io.top_digital_object_uuid, io.timestamp FROM ((SELECT top_digital_object_uuid, timestamp, editor_user_id FROM "
                        + Constants.TABLE_CRUD_DO_ACTION_WITH_TOP_OBJECT
                        + " WHERE type = 'c' AND top_digital_object_uuid = digital_object_uuid) a INNER JOIN (SELECT uuid, input_queue_directory_path FROM "
                        + Constants.TABLE_DIGITAL_OBJECT
                        + " WHERE input_queue_directory_path = (?)) o ON a.top_digital_object_uuid = o.uuid) io LEFT JOIN "
                        + Constants.TABLE_EDITOR_USER + " eu ON io.editor_user_id = eu.id";

        dsl.select();

        return null;
    }

    @Override
    public boolean hasBeenIngested(String path) throws DatabaseException {
        List<IngestInfo> ingestInfo = getIngestInfo(path);
        return ingestInfo != null && !ingestInfo.isEmpty();
    }

    @Override
    public void setIngested(String path) throws DatabaseException {
        cz.mzk.editor.server.jooq.tables.InputQueueItem inputQueueItemTable = cz.mzk.editor.server.jooq.tables.InputQueueItem.INPUT_QUEUE_ITEM;
        dsl.update(inputQueueItemTable).set(inputQueueItemTable.INGESTED, true)
                .where(inputQueueItemTable.PATH.eq(path));
    }


    //TODO-MR implement!
    @Override
    public boolean checkInputQueue(String directory_path, String name)
            throws DatabaseException {
        PreparedStatement selSt = null;
        boolean successful = false;
        String dirPath = DAOUtilsImpl.directoryPathToRightFormat(directory_path);

        return true;
    }


}
