package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.DAO.StoredAndLocksDAO;
import cz.mzk.editor.server.cz.mzk.server.editor.api.TreeStructureDAO;
import cz.mzk.editor.server.jooq.tables.CrudTreeStructureAction;
import cz.mzk.editor.server.jooq.tables.EditorUser;
import cz.mzk.editor.server.jooq.tables.TreeStructure;
import cz.mzk.editor.shared.rpc.TreeStructureBundle;
import cz.mzk.editor.shared.rpc.TreeStructureInfo;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.trueCondition;
import static org.jooq.impl.DSL.val;

/**
 * Created by rumanekm on 6/18/15.
 */
@Repository
public class TreeStructureDAOImpl implements TreeStructureDAO {

    @Inject
    private DSLContext dsl;

    /**
     * The Enum DISCRIMINATOR.
     */
    private static enum DISCRIMINATOR {

        /** The ALL. */
        ALL,
        /** The AL l_ o f_ user. */
        ALL_OF_USER,
        /** The BARCOD e_ o f_ user. */
        BARCODE_OF_USER
    }

    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructuresOfUser(long userId) throws DatabaseException {
        return getSavedStructures(DISCRIMINATOR.ALL_OF_USER, userId, null);
    }

    /**
     * Gets the saved structures.
     *
     * @param what
     *        the what
     * @param userId
     *        the user id
     * @param code
     *        the code
     * @return the saved structures
     * @throws DatabaseException
     *         the database exception
     */
    private ArrayList<TreeStructureInfo> getSavedStructures(DISCRIMINATOR what, long userId, String code)
            throws DatabaseException {

        return null;
    }

    @Override
    public ArrayList<TreeStructureInfo> getSavedStructuresOfUser(long userId, String code) throws DatabaseException {
        return null;
    }

    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructures(String code) throws DatabaseException {
                if (code != null) {
                    TreeStructure treeStructure = TreeStructure.TREE_STRUCTURE;
                    CrudTreeStructureAction structureActionTable = CrudTreeStructureAction.CRUD_TREE_STRUCTURE_ACTION;
                    EditorUser userTable = EditorUser.EDITOR_USER;
                    List<TreeStructureInfo>  retList =dsl.select(
                            concat(userTable.SURNAME, val(", "), userTable.NAME),
                            structureActionTable.ID,
                            structureActionTable.TIMESTAMP,
                            treeStructure.DESCRIPTION,
                            treeStructure.BARCODE,
                            treeStructure.NAME,
                            treeStructure.INPUT_QUEUE_DIRECTORY_PATH,
                            treeStructure.MODEL
                    ).
                            from(treeStructure)
                            .join(structureActionTable).on(treeStructure.ID.eq(structureActionTable.TREE_STRUCTURE_ID))
                            .leftOuterJoin(userTable).on(structureActionTable.EDITOR_USER_ID.eq(userTable.ID))
                            .where(structureActionTable.TYPE.eq("c"), userTable.STATE.eq(true))
                            .fetch().map(new RecordMapper<Record8<String, Integer, Timestamp, String, String, String, String, String>, TreeStructureInfo>() {
                        @Override
                        public TreeStructureInfo map(Record8<String, Integer, Timestamp, String, String, String, String, String> record) {
                            long id = record.value2();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            String created = formatter.format(record.value3());
                            String description = record.value4();
                            String barcode = record.value5();
                            String name = record.value6();
                            String owner = record.value1();
                            String inputPath = record.value7();
                            String model = record.value8();

                            return new TreeStructureInfo(id, created, description, barcode, name, owner, inputPath, model);
                        }
                    });
                    return new ArrayList<>(retList);
                } else {
                    return null;
                }

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
