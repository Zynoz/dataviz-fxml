package dataviz.transaction;

import dataviz.exception.SQLException;
import dataviz.util.SQLPair;
import dataviz.util.SQLParser;
import dataviz.util.SQLType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionManager {

    private static TransactionManager instance;

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
        List<TableEntry> test = new ArrayList<>();
        List<SQLPair<SQLType, String>> sqlPairs = SQLParser.getSQLFromString(sql);
        for (SQLPair<SQLType, String> pair : sqlPairs) {

        }
        TransactionController.getInstance().addToTableView(test);
    }

    public String getNextTransactionID() {
        return UUID.randomUUID().toString();
    }
}