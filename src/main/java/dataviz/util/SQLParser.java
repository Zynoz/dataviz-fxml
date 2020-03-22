package dataviz.util;

import dataviz.exception.SQLException;
import dataviz.transaction.TableEntry;
import dataviz.transaction.TransactionController;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLParser {

    //                                                                       tableName1  field2    value3                                id5
    public static final Pattern UPDATE_PATTERN = Pattern.compile("update benutzer\\sset (name|amount|maxamount) = (((\\d+)('[.,]\\d{1,2}')?)|('\\w+ ?'))\\swhere (id) = (\\d{1,4});", Pattern.CASE_INSENSITIVE);
    //                                                                          tableName1               value1     value2  value2(\w+), (\w+)
    public static final Pattern INSERT_PATTERN = Pattern.compile("insert into (benutzer) \\((\\d{1,4}), ('[A-z]+'), (\\d+(\\.\\d{1,2})?), (\\d+(\\.\\d{1,2})?)\\);", Pattern.CASE_INSENSITIVE);
    //                                                                            tableName1         id2
    public static final Pattern DELETE_PATTERN = Pattern.compile("delete from benutzer where (id) = (\\d{1,4});", Pattern.CASE_INSENSITIVE);

    public static final Pattern COMMIT_PATTERN = Pattern.compile("commit;", Pattern.CASE_INSENSITIVE);

    private static final Logger log = LogManager.getLogger(SQLParser.class.getSimpleName());


    public static SQLType getType(String sql) {
        sql = sql.toLowerCase();
        final Matcher insertMatcher = INSERT_PATTERN.matcher(sql);
        final Matcher updateMatcher = UPDATE_PATTERN.matcher(sql);
        final Matcher deleteMatcher = DELETE_PATTERN.matcher(sql);
        final Matcher commitMatcher = COMMIT_PATTERN.matcher(sql);

        if (insertMatcher.matches()) {
            return SQLType.INSERT;
        } else if (updateMatcher.matches()) {
            return SQLType.UPDATE;
        } else if (deleteMatcher.matches()) {
            return SQLType.DELETE;
        } else if (commitMatcher.matches()) {
            return SQLType.COMMIT;
        } else {
            log.error("Could not match SQL to pattern");
            return null;
        }
    }

    public static List<SQLPair<SQLType, String>> getSQL(List<String> sqlStatements) throws SQLException {
        Iterator<String> sqlIterator = sqlStatements.iterator();
        List<SQLPair<SQLType, String>> list = new ArrayList<>();
        while (sqlIterator.hasNext()) {
            String sql = sqlIterator.next();
            SQLType type = getType(sql);
            if (type == null) {
                log.error("Invalid SQL Statement: " + sql);
                throw new SQLException("Invalid SQL Statement!");
            }
            list.add(SQLPair.of(type, sql));
        }
        return list;
    }

    public static SQLPair<SQLType, String> getSQLFromSingleString(String sql) throws SQLException {
        SQLType type = getType(sql);
        if (type == null) {
            log.error("Invalid SQL Statement: " + sql);
            throw new SQLException("Invalid SQL Statement!");
        } else {
            return SQLPair.of(type, sql);
        }
    }

    public static List<SQLPair<SQLType, String>> getSQLFromString(String sql) throws SQLException {
        sql = sql.replaceAll("\\n", "");
        List<SQLPair<SQLType, String>> list = new ArrayList<>();
        if (sql.split(";").length > 1) {
            String[] sqlStatements = sql.split(";");
            for (int i = 0; i < sqlStatements.length; i++) {
                sqlStatements[i] = sqlStatements[i] + ";";
            }
            for (String sqlStatement : sqlStatements) {
                SQLType type = getType(sqlStatement.trim());
                if (type == null) {
                    log.error("Invalid SQL Statement for SQL: " + sqlStatement);
                    throw new SQLException("Invalid SQL Statement for SQL: " + sqlStatement);
                }
                list.add(SQLPair.of(type, sqlStatement));
            }
        } else {
            list.add(getSQLFromSingleString(sql));
        }
        return list;
    }

    public static List<String> getGroupValues(String sql) {
        List<String> groupValues = new ArrayList<>();
        sql = sql.replaceAll("'", "");
        Matcher matcher = INSERT_PATTERN.matcher(sql);
        if (matcher.matches()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                groupValues.add(matcher.group(i));
            }
        }
        return groupValues;
    }

    public static TableEntry getInsertData(String sql) throws SQLException {
        Matcher matcher = INSERT_PATTERN.matcher(sql);
        if (matcher.matches()) {
            List<TableEntry> entries = TransactionController.getInstance().getEntries();
            int id = Integer.parseInt(matcher.group(2));
            if (entries.stream().anyMatch(te -> te.getId() == id)) {
                log.error("This id is already in use");
                throw new SQLException("This id is already in use: " + id);
            }
            return new TableEntry(Long.parseLong(matcher.group(2)), matcher.group(3).replaceAll("'", ""), Double.parseDouble(matcher.group(4)), Double.parseDouble(matcher.group(6)));
        }
        log.error("Invalid SQL: " + sql);
        throw new SQLException("Invalid SQL");
    }

    //todo
    public static TableEntry getDeleteData(String sql) throws SQLException {
        Matcher matcher = DELETE_PATTERN.matcher(sql);
        TableEntry toDelete = null;
        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group(2));
            log.info("ID to delete: " + id);
            ObservableList<TableEntry> entries = TransactionController.getInstance().getEntries();
            for (TableEntry next : entries) {
                if (next.getId() == id) {
                    toDelete = next;
                    log.info("Found TableEntry to delete");
                }
            }
            if (toDelete == null) {
                log.error("Could not find entry to delete: " + id);
                throw new SQLException("Could not find entry to delete");
            }
            return toDelete;
        }
        log.error("Invalid SQL: " + sql);
        throw new SQLException("Invalid SQL");
    }

    @SneakyThrows
    public static TableEntry getUpdateData(String sql) {
        ObservableList<TableEntry> entries = TransactionController.getInstance().getEntries();

        Matcher matcher = UPDATE_PATTERN.matcher(sql);
        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group(8));
            log.info("ID: " + id);
            TableEntry toUpdate = null;
            for (TableEntry te : entries) {
                if (te.getId() == id) {
                    toUpdate = te;
                }
            }
            if (toUpdate == null) {
                log.error("Could not find entry to update: " + id);
                throw new SQLException("Could not find entry to update: " + id);
            }
            log.info("toUpdate: " + toUpdate);
            log.debug("tablecolumnname: " + matcher.group(1));
            TableColumn toChange = TableColumn.getTableColumn(matcher.group(1));
            log.info("toChange: " + toChange);
            if (toChange.equals(TableColumn.NAME)) {
                String name = matcher.group(2).replaceAll("'", "");
                log.debug("Name: " + name);
                toUpdate.setName(name);
                return toUpdate;
            } else if (toChange.equals(TableColumn.AMOUNT)) {
                double amount = Double.parseDouble(matcher.group(2));
                toUpdate.setCurrentAmount(amount);
                return toUpdate;
            } else if (toChange.equals(TableColumn.MAXAMOUNT)) {
                double maxamount = Double.parseDouble(matcher.group(2));
                log.info("maxamount: " + maxamount);
                toUpdate.setMaxAmount(maxamount);
                return toUpdate;
            }
        }
        log.error("Could not parse SQL: " + sql);
        throw new SQLException("Could not parse SQL: " + sql);
    }
}
