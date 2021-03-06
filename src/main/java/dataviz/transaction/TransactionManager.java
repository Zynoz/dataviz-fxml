package dataviz.transaction;

import dataviz.exception.SQLException;
import dataviz.util.SQLPair;
import dataviz.util.SQLParser;
import dataviz.util.SQLType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.UUID;

public class TransactionManager {

    private static TransactionManager instance;

    private static final Logger log = LogManager.getLogger(TransactionController.class.getSimpleName());

    private TransactionId currentTID;

    private TransactionManager() {

    }

    public static synchronized TransactionManager getInstance() {
        log.debug("initializing...");
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    //todo implement
    public void executeStatement(String sql) throws SQLException {
        List<SQLPair<SQLType, String>> sqlPairs = SQLParser.getSQLFromString(sql);
        if (currentTID == null && sql.equalsIgnoreCase("commit;")) {
            log.error("No transaction open");
            throw new SQLException("No Transaction open");
        } else if (currentTID == null && !sql.equalsIgnoreCase("commit;")) {
            currentTID = new TransactionId();
            log.info("generated new TID: " + currentTID.gettId());
        } else if (sql.equalsIgnoreCase("commit;")) {
            log.info("committed with TID: " + currentTID);
            currentTID = null;
            TransactionController.getInstance().updateUndoTableView();
            //todo commit logic
            return;
        }

        log.debug("using TID: " + currentTID);
        for (SQLPair<SQLType, String> pair : sqlPairs) {
            if (pair.getType().equals(SQLType.INSERT)) {
                TableEntry insert = SQLParser.getInsertData(pair.getSql());
                SQLStatement sqlStatement = new InsertStatement(pair.getSql());
                insert.setSqlStatement(sqlStatement);
                UndoTableEntry undoTableEntry = new UndoTableEntry(nextRowId(), sqlStatement);
                undoTableEntry.settId(currentTID);
                TransactionController.getInstance().addToTableView(insert);
                TransactionController.getInstance().addToUndoLog(undoTableEntry);
            } else if (pair.getType().equals(SQLType.DELETE)) {
                SQLStatement sqlStatement = new DeleteStatement(pair.getSql());
                TableEntry delete = SQLParser.getDeleteData(pair.getSql());
                delete.setSqlStatement(sqlStatement);
                UndoTableEntry undoTableEntry = new UndoTableEntry(nextRowId(), sqlStatement);
                undoTableEntry.settId(currentTID);
                TransactionController.getInstance().removeFromTableView(delete);
                TransactionController.getInstance().addToUndoLog(undoTableEntry);
            } else if (pair.getType().equals(SQLType.UPDATE)) {
                TableEntry update = SQLParser.getUpdateData(pair.getSql());
                SQLStatement sqlStatement = new UpdateStatement(pair.getSql());
                UndoTableEntry undoTableEntry = new UndoTableEntry(nextRowId(), sqlStatement);
                update.setSqlStatement(sqlStatement);
                undoTableEntry.settId(currentTID);
                TransactionController.getInstance().updateTableView(update);
                TransactionController.getInstance().addToUndoLog(undoTableEntry);
            } else if (pair.getType().equals(SQLType.COMMIT)) {
                UndoTableEntry undoTableEntry = new UndoTableEntry("", new CommitStatement());
                undoTableEntry.settId(currentTID);
                TransactionController.getInstance().addToUndoLog(undoTableEntry);
            }
        }
    }

    private String nextRowId() {
        return UUID.randomUUID().toString().substring(0, 7);
    }
}
