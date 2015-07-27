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

import org.joda.time.DateTime;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
                    .set(conversionTable.INPUT_QUEUE_DIRECTORY_PATH, DAOUtilsImpl.directoryPathToRightFormat(directoryPath))
                    .set(conversionTable.TIMESTAMP, currentTimestamp());
        }
    }

    @Override
    public ArrayList<InputQueueItem> getConversionInfo(ArrayList<InputQueueItem> data, int numberOfDays) throws DatabaseException {
        for (InputQueueItem item : data) {
            Conversion conversionTable = Conversion.CONVERSION;

            Timestamp timestamp = dsl.select(DSL.max(conversionTable.TIMESTAMP)).from(conversionTable)
                    .where(conversionTable.INPUT_QUEUE_DIRECTORY_PATH.eq(DAOUtilsImpl.directoryPathToRightFormat(item.getPath())))
            .fetchOne().value1();
            DateTime conversionTime = new DateTime(timestamp);

            item.setConversionDate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(timestamp));

                if (numberOfDays > 0) {
                    if (conversionTime.isAfter(new DateTime().minusDays(numberOfDays))) {
                        item.setConverted(true);
                    } else {
                        item.setConverted(false);
                    }

                } else {
                    item.setConverted(true);
                }
        }
        return data;
    }
}
