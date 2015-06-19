package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.ActionDAO;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.shared.domain.DigitalObjectModel;
import cz.mzk.editor.shared.rpc.EditorDate;
import cz.mzk.editor.shared.rpc.HistoryItem;
import cz.mzk.editor.shared.rpc.HistoryItemInfo;
import cz.mzk.editor.shared.rpc.IntervalStatisticData;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rumanekm on 6/19/15.
 */
@Repository
public class ActionDAOImpl implements ActionDAO {
    @Override
    public List<EditorDate> getHistoryDays(Long userId, String uuid) throws DatabaseException {
        return null;
    }

    @Override
    public List<HistoryItem> getHistoryItems(Long editorUserId, String uuid, EditorDate lowerLimit, EditorDate upperLimit) throws DatabaseException {
        return null;
    }

    @Override
    public HistoryItemInfo getHistoryItemInfo(Long id, String tableName) throws DatabaseException {
        return null;
    }

    @Override
    public HashMap<Integer, IntervalStatisticData> getUserStatisticsData(Long userId, DigitalObjectModel model, EditorDate dateFrom, EditorDate dateTo, Constants.STATISTICS_SEGMENTATION segmentation) throws DatabaseException, ParseException {
        return null;
    }
}
