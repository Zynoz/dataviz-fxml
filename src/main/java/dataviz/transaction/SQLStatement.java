package dataviz.transaction;

public abstract class SQLStatement {
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "SQLStatement{" +
                "sql='" + sql + '\'' +
                '}';
    }
}
