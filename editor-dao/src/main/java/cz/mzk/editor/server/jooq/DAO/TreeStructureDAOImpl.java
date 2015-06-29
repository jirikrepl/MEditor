package cz.mzk.editor.server.jooq.DAO;

import cz.mzk.editor.client.util.Constants;
import cz.mzk.editor.server.DAO.DatabaseException;
import cz.mzk.editor.server.DAO.StoredAndLocksDAO;
import cz.mzk.editor.server.cz.mzk.server.editor.api.TreeStructureDAO;
import cz.mzk.editor.server.jooq.tables.CrudTreeStructureAction;
import cz.mzk.editor.server.jooq.tables.EditorUser;
import cz.mzk.editor.server.jooq.tables.TreeStructure;
import cz.mzk.editor.server.jooq.tables.TreeStructureNode;
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

        /**
         * The ALL.
         */
        ALL,
        /**
         * The AL l_ o f_ user.
         */
        ALL_OF_USER,
        /**
         * The BARCOD e_ o f_ user.
         */
        BARCODE_OF_USER
    }

    private static final class TreeMapper implements RecordMapper<Record8<String, Integer, Timestamp, String, String, String, String, String>, TreeStructureInfo> {
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
    }

    private static final TreeStructure treeStructure = TreeStructure.TREE_STRUCTURE;
    private static final CrudTreeStructureAction structureActionTable = CrudTreeStructureAction.CRUD_TREE_STRUCTURE_ACTION;
    private static final EditorUser userTable = EditorUser.EDITOR_USER;


    private SelectConditionStep selectInfosQuery() {
        return dsl.select(
                concat(userTable.SURNAME, val(", "), userTable.NAME),
                treeStructure.ID,
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
                .where(structureActionTable.TYPE.eq("c"), treeStructure .STATE.eq(true));
    }



    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructuresOfUser(long userId) throws DatabaseException {
        List<TreeStructureInfo> retList;
        retList = selectInfosQuery().and(structureActionTable.EDITOR_USER_ID.eq(new Long(userId).intValue())) .fetch().map(new TreeMapper());

        return new ArrayList<>(retList);
    }

    @Override
    public ArrayList<TreeStructureInfo> getSavedStructuresOfUser(long userId, String code) throws DatabaseException {
        List<TreeStructureInfo> retList;

        retList = selectInfosQuery().and(treeStructure.BARCODE.eq(code)).and(structureActionTable.EDITOR_USER_ID.eq(new Long(userId).intValue())).
                fetch().map(new TreeMapper());

        return new ArrayList<>(retList);

    }

    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructures(String code) throws DatabaseException {
        List<TreeStructureInfo> retList;
        if (code != null) {
            retList = selectInfosQuery().and(treeStructure.BARCODE.eq(code)).fetch().map(new TreeMapper());

        } else {
            retList = selectInfosQuery().fetch().map(new TreeMapper());
        }

        return new ArrayList<>(retList);
    }



    @Override
    public boolean removeSavedStructure(long id) throws DatabaseException {
        return false;
    }

    @Override
    public ArrayList<TreeStructureBundle.TreeStructureNode> loadStructure(long structureId) throws DatabaseException {
        List<TreeStructureBundle.TreeStructureNode> retList = dsl.select().from(TreeStructureNode.TREE_STRUCTURE_NODE)
                .where(TreeStructureNode.TREE_STRUCTURE_NODE.TREE_STRUCTURE_ID.eq(new Long(structureId).intValue()))
                .orderBy(TreeStructureNode.TREE_STRUCTURE_NODE.ID).fetch().map(new RecordMapper<Record, TreeStructureBundle.TreeStructureNode>() {
                    @Override
                    public TreeStructureBundle.TreeStructureNode map(Record rs) {
                        return new TreeStructureBundle.TreeStructureNode((String) rs.getValue("prop_id"),
                                (String) rs.getValue("prop_parent"),
                                (String) rs.getValue("prop_name"),
                                (String) rs.getValue("prop_picture_or_uuid"),
                                (String) rs.getValue("prop_model_id"),
                                (String) rs.getValue("prop_type"),
                                (String) rs.getValue("prop_date_or_int_part_name"),
                                (String) rs.getValue("prop_note_or_int_subtitle"),
                                (String) rs.getValue("prop_part_number_or_alto"),
                                (String) rs.getValue("prop_aditional_info_or_ocr"),
                                (String) rs.getValue("prop_ocr_path"),
                                (String) rs.getValue("prop_alto_path"),
                                (Boolean) rs.getValue("prop_exist"));
                    }
                });

        return new ArrayList<>(retList);
    }

    @Override
    public boolean saveStructure(long userId, TreeStructureInfo info, List<TreeStructureBundle.TreeStructureNode> structure) throws DatabaseException {
        return false;
    }
}
