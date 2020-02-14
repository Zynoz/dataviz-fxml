package dataviz.util;

import dataviz.exception.SQLException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLParser {
    //                                                                       tableName1  field2    value3                                id5
    private static final Pattern UPDATE_PATTERN = Pattern.compile("update (\\w+) set (\\w+) = (((\\d+)('[.,]\\d{1,2}')?)|('\\w+ ?')) where id = (\\d+);", Pattern.CASE_INSENSITIVE);

    //                                                                            tableName1         id2
    private static final Pattern DELETE_PATTERN = Pattern.compile("delete from (\\w+) where id = (\\d+);", Pattern.CASE_INSENSITIVE);

    //                                                                          tableName1               value1     value2  value2(\w+), (\w+)
    private static final Pattern INSERT_PATTERN = Pattern.compile("insert into (\\w+) \\((\\w+), (\\w+), (\\w+)\\) values \\((\\w+), (\\w+), (\\w+)\\);", Pattern.CASE_INSENSITIVE);
//    insert into dbname (?, ?, ?) values (1, 2, 3)

    private static final Pattern SELECT_PATTERN = Pattern.compile("select \\* from (\\w+);");

    //todo remove select type and replace with "select all"-button
    public static SQLType getType(String sql) {
        sql = sql.toLowerCase();
        final Matcher insertMatcher = INSERT_PATTERN.matcher(sql);
        final Matcher updateMatcher = UPDATE_PATTERN.matcher(sql);
        final Matcher deleteMatcher = DELETE_PATTERN.matcher(sql);
        final Matcher selectMatcher = SELECT_PATTERN.matcher(sql);

        if (insertMatcher.matches()) {
            System.out.println(sql + " matches insert");
            return SQLType.INSERT;
        } else if (updateMatcher.matches()) {
            System.out.println(sql + " matches update");
            return SQLType.UPDATE;
        } else if (deleteMatcher.matches()) {
            System.out.println(sql + " matches delete");
            return SQLType.DELETE;
        } else if (selectMatcher.matches()) {
            System.out.println(sql + " matches select");
            return SQLType.SELECT;
        } else {
            return null;
        }
    }

    public static List<SQLPair<SQLType, String>> getSQL(List<String> sqlStatements) throws SQLException {
        Iterator<String> sqlIterator = sqlStatements.iterator();
        List<SQLPair<SQLType, String>> list = new ArrayList<>();
        while (sqlIterator.hasNext()) {
            String sql = sqlIterator.next();
            System.out.println(sql);
            SQLType type = getType(sql);
            if (type == null) {
                throw new SQLException("Invalid SQL Statement!");
            }
            list.add(SQLPair.of(type, sql));
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
