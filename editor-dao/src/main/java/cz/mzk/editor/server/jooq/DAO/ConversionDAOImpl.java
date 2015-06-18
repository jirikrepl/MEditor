package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DAOUtilsImpl;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.ConversionDAO;
import cz.mzk.editor.shared.rpc.InputQueueItem;
import org.apache.commons.lang.NotImplementedException;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by rumanekm on 6/4/15.
 */
@Repository
public class ConversionDAOImpl implements ConversionDAO {

    @Inject
    private DSLContext dsl;

    @Override
    public void insertConversionInfo(String directoryPath, Long userId) throws DatabaseException {

    }

    @Override
    //TODO-MR
    public ArrayList<InputQueueItem> getConversionInfo(ArrayList<InputQueueItem> data, int numberOfDays) throws DatabaseException {
        for (InputQueueItem item : data) {

        }
        return data;
    }
}
