package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.cz.mzk.server.editor.api.TreeStructureDAO;
import cz.mzk.editor.shared.rpc.TreeStructureBundle;
import cz.mzk.editor.shared.rpc.TreeStructureInfo;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumanekm on 6/18/15.
 */
@Repository
public class TreeStructureDAOImpl implements TreeStructureDAO {
    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructuresOfUser(long userId) throws DatabaseException {
        return null;
    }

    @Override
    public ArrayList<TreeStructureInfo> getSavedStructuresOfUser(long userId, String code) throws DatabaseException {
        return null;
    }

    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructures(String code) throws DatabaseException {
        return null;
    }

    @Override
    public boolean removeSavedStructure(long id) throws DatabaseException {
        return false;
    }

    @Override
    public ArrayList<TreeStructureBundle.TreeStructureNode> loadStructure(long structureId) throws DatabaseException {
        return null;
    }

    @Override
    public boolean saveStructure(long userId, TreeStructureInfo info, List<TreeStructureBundle.TreeStructureNode> structure) throws DatabaseException {
        return false;
    }
}
