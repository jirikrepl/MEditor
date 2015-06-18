package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.OcrDao;
import org.springframework.stereotype.Repository;

import javax.inject.Named;

/**
 * Created by rumanekm on 6/18/15.
 */
@Repository
public class OcrDAOImpl implements OcrDao {
    @Override
    public void insertOcr(String uuid, String txtOcr, String altoOcr) throws DatabaseException {

    }
}
