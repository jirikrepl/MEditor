package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.ImageResolverDAO;
import cz.mzk.editor.shared.rpc.ImageItem;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/18/15.
 */
@Repository
public class ImageResolverDAOImpl implements ImageResolverDAO {
    @Override
    public void insertItems(List<ImageItem> toInsert) throws DatabaseException {

    }

    @Override
    public ArrayList<String> resolveItems(List<String> identifiers) throws DatabaseException {
        return null;
    }

    @Override
    public ArrayList<String> cacheAgeingProcess(int numberOfDays) throws DatabaseException {
        return null;
    }

    @Override
    public String getOldJpgFsPath(String imageFile) throws DatabaseException {
        return null;
    }

    @Override
    public String getNewImageFilePath(String identifier) throws DatabaseException {
        return null;
    }
}
