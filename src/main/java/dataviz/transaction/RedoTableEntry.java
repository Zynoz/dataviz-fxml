package dataviz.transaction;

public class RedoTableEntry {

    private String rowId;

    private TransactionId tId;

    private SQLStatement sqlStatement;

    public RedoTableEntry(String rowId, SQLStatement sqlStatement) {
        this.rowId = rowId;
        this.sqlStatement = sqlStatement;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public TransactionId gettId() {
        return tId;
    }

    public void settId(TransactionId tId) {
        this.tId = tId;
    }

    public SQLStatement getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(SQLStatement sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    @Override
    public String toString() {
        return "RedoTableEntry{" +
                "rowId='" + rowId + '\'' +
                ", tId=" + tId +
                ", sqlStatement=" + sqlStatement +
                '}';
    }
}
