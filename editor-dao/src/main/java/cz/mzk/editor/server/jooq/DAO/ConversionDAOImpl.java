package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.ConversionDAO;
import cz.mzk.editor.shared.rpc.InputQueueItem;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by rumanekm on 6/4/15.
 */
@Repository
public class ConversionDAOImpl implements ConversionDAO {
    @Override
    public void insertConversionInfo(String directoryPath, Long userId) throws DatabaseException {

    }

    @Override
    public ArrayList<InputQueueItem> getConversionInfo(ArrayList<InputQueueItem> data, int numberOfDays) throws DatabaseException {
        throw new NotImplementedException();
    }
}
