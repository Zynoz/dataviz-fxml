package dataviz.transaction;

import dataviz.exception.SQLException;
import dataviz.util.SQLPair;
import dataviz.util.SQLParser;
import dataviz.util.SQLType;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class TransactionManager {

    private static TransactionManager instance;

    private static final Logger LOGGER = Logger.getLogger(TransactionController.class.getSimpleName());

    private TransactionManager() {

    }

    public static synchronized TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    //todo implement
    public void executeStatement(String sql) throws SQLException {
        List<SQLPair<SQLType, String>> sqlPairs = SQLParser.getSQLFromString(sql);
        for (SQLPair<SQLType, String> pair : sqlPairs) {
            if (pair.getType().equals(SQLType.INSERT)) {
                TableEntry insert = SQLParser.getInsertData(pair.getSql());
                InsertStatement insertStatement = new InsertStatement(pair.getSql());
                TransactionController.getInstance().addToRedoTable(insertStatement);
                TransactionController.getInstance().addToTableView(insert);
            } else if (pair.getType().equals(SQLType.DELETE)) {
                TableEntry delete = SQLParser.getDeleteData(pair.getSql());
                DeleteStatement deleteStatement = new DeleteStatement(pair.getSql());
                TransactionController.getInstance().addToRedoTable(deleteStatement);
                TransactionController.getInstance().removeFromTableView(delete);
            } else if (pair.getType().equals(SQLType.UPDATE)) {
                TableEntry update = SQLParser.getUpdateData(pair.getSql());
                UpdateStatement updateStatement = new UpdateStatement(pair.getSql());
                TransactionController.getInstance().addToRedoTable(updateStatement);
                TransactionController.getInstance().updateTableView(update);
            }
        }
    }

    public String getNextTransactionID() {
        return UUID.randomUUID().toString();
    }
}
