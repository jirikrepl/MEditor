package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DAOUtils;
import cz.mzk.editor.server.DAO.DAOUtilsImpl;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.ConversionDAO;
import cz.mzk.editor.server.cz.mzk.server.editor.api.InputQueueItemDAO;
import cz.mzk.editor.server.jooq.tables.Conversion;
import cz.mzk.editor.shared.rpc.InputQueueItem;
import static org.jooq.impl.DSL.*;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Table;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by rumanekm on 6/4/15.
 */
@Repository
public class ConversionDAOImpl implements ConversionDAO {

    @Inject
    private DSLContext dsl;

    @Inject
    private InputQueueItemDAO inputQueueItemDAO;

    @Override
    public void insertConversionInfo(String directoryPath, Long userId) throws DatabaseException {
        if (inputQueueItemDAO.checkInputQueue(directoryPath, null)) {
            Conversion conversionTable = Conversion.CONVERSION;
            dsl.insertInto(conversionTable).set(conversionTable.EDITOR_USER_ID, userId.intValue())
                    .set(conversionTable.INPUT_QUEUE_DIRECTORY_PATH, directoryPath)
                    .set(conversionTable.TIMESTAMP, currentTimestamp());
        }
    }

    @Override
    //TODO-MR
    public ArrayList<InputQueueItem> getConversionInfo(ArrayList<InputQueueItem> data, int numberOfDays) throws DatabaseException {
        for (InputQueueItem item : data) {
            // wut
            //public static final String SELECT_CONVERSION_INFO =
            //        "SELECT lastTimestamp, (lastTimestamp < (NOW() - INTERVAL '%s day')) AS isOlder FROM (SELECT MAX(timestamp) AS lastTimestamp FROM "
            //                + Constants.TABLE_CONVERSION
            //                + " WHERE input_queue_directory_path = (?) GROUP BY timestamp ORDER BY timestamp DESC LIMIT '1') c";
        }
        return data;
    }
}
