package dataviz.util;

import dataviz.exception.SQLException;
import dataviz.transaction.TableEntry;
import dataviz.transaction.TransactionController;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLParser {

    //                                                                       tableName1  field2    value3                                id5
    public static final Pattern UPDATE_PATTERN = Pattern.compile("update benutzer\\sset (name|amount|maxamount) = (((\\d+)('[.,]\\d{1,2}')?)|('\\w+ ?'))\\swhere (id) = (\\d{1,4});", Pattern.CASE_INSENSITIVE);
    //                                                                          tableName1               value1     value2  value2(\w+), (\w+)
    public static final Pattern INSERT_PATTERN = Pattern.compile("insert into (benutzer) \\((\\d{1,4}), ('[A-z]+'), (\\d+(\\.\\d{1,2})?), (\\d+(\\.\\d{1,2})?)\\);", Pattern.CASE_INSENSITIVE);
    //                                                                            tableName1         id2
    public static final Pattern DELETE_PATTERN = Pattern.compile("delete from benutzer where (id) = (\\d{1,4});", Pattern.CASE_INSENSITIVE);

    private static final Logger LOGGER = Logger.getLogger(SQLParser.class.getSimpleName());


    public static SQLType getType(String sql) {
        sql = sql.toLowerCase();
        final Matcher insertMatcher = INSERT_PATTERN.matcher(sql);
        final Matcher updateMatcher = UPDATE_PATTERN.matcher(sql);
        final Matcher deleteMatcher = DELETE_PATTERN.matcher(sql);

        if (insertMatcher.matches()) {
            return SQLType.INSERT;
        } else if (updateMatcher.matches()) {
            return SQLType.UPDATE;
        } else if (deleteMatcher.matches()) {
            return SQLType.DELETE;
        } else {
            return null;
        }
    }

    public static List<SQLPair<SQLType, String>> getSQL(List<String> sqlStatements) throws SQLException {
        Iterator<String> sqlIterator = sqlStatements.iterator();
        List<SQLPair<SQLType, String>> list = new ArrayList<>();
        while (sqlIterator.hasNext()) {
            String sql = sqlIterator.next();
//            System.out.println(sql);
            SQLType type = getType(sql);
            if (type == null) {
                throw new SQLException("Invalid SQL Statement!");
            }
            list.add(SQLPair.of(type, sql));
        }
        return list;
    }

    public static SQLPair<SQLType, String> getSQLFromSingleString(String sql) throws SQLException {
        SQLType type = getType(sql);
        if (type == null) {
            throw new SQLException("Invalid SQL Statement!");
        } else {
            return SQLPair.of(type, sql);
        }
    }

    public static List<SQLPair<SQLType, String>> getSQLFromString(String sql) throws SQLException {
        LOGGER.info(sql);
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
                    LOGGER.severe("Invalid SQL Statement for SQL: " + sqlStatement);
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
                throw new SQLException("This id is already in use");
            }
            return new TableEntry(Long.parseLong(matcher.group(2)), matcher.group(3).replaceAll("'", ""), Double.parseDouble(matcher.group(4)), Double.parseDouble(matcher.group(6)));
        }
        throw new SQLException("Invalid SQL");
    }

    //todo
    public static TableEntry getDeleteData(String sql) {
        Matcher matcher = DELETE_PATTERN.matcher(sql);
        TableEntry toDelete = null;
        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group(2));
            LOGGER.info("ID to delete: " + id);
            ObservableList<TableEntry> entries = TransactionController.getInstance().getEntries();
            for (TableEntry next : entries) {
                if (next.getId() == id) {
                    toDelete = next;
                    LOGGER.info("Found TableEntry to delete");
                }
            }
            return toDelete;
        }
        LOGGER.info("Found no TableEntry to delete");
        return null;
    }

    @SneakyThrows
    public static TableEntry getUpdateData(String sql) {
        ObservableList<TableEntry> entries = TransactionController.getInstance().getEntries();

        Matcher matcher = UPDATE_PATTERN.matcher(sql);
        if (matcher.matches()) {
            int id = Integer.parseInt(matcher.group(8));
            LOGGER.info("ID: " + id);

            TableEntry toUpdate = null;
            for (TableEntry te : entries) {
                if (te.getId() == id) {
                    toUpdate = te;
                }
            }
            LOGGER.info("toUpdate: " + toUpdate);
            LOGGER.info("tablecolumnname: " + matcher.group(1));
            TableColumn toChange = TableColumn.getTableColumn(matcher.group(1));
            LOGGER.info("toChange: " + toChange);
            if (toChange.equals(TableColumn.NAME)) {
                String name = matcher.group(2).replaceAll("'", "");
                LOGGER.info("Name: " + name);
                toUpdate.setName(name);
                return toUpdate;
            } else if (toChange.equals(TableColumn.AMOUNT)) {
                double amount = Double.parseDouble(matcher.group(2));
                toUpdate.setCurrentAmount(amount);
                return toUpdate;
            } else if (toChange.equals(TableColumn.MAXAMOUNT)) {
                double maxamount = Double.parseDouble(matcher.group(2));
                LOGGER.info("maxamount: " + maxamount);
                toUpdate.setMaxAmount(maxamount);
                return toUpdate;
            }
        }
        throw new SQLException("Could not parse SQL!");
    }

    public static Map<String, String> convertInsertListToMap(List<String> insertList) {
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put(insertList.get(0), insertList.get(3));
        fieldValues.put(insertList.get(1), insertList.get(4));
        fieldValues.put(insertList.get(2), insertList.get(5));
        int half = insertList.size() / 2;
        for (int i = 0; i < half; i++) {
            fieldValues.put(insertList.get(i), insertList.get(i + half));
        }
        return fieldValues;
    }
}
