package dataviz.transaction;

public class UndoTableEntry {

    private String rowId;

    private TransactionId tId;

    private SQLStatement sqlStatement;

    public UndoTableEntry(String rowId, SQLStatement sqlStatement) {
        this.rowId = rowId;
        this.sqlStatement = sqlStatement;
    }

    public TransactionId gettId() {
        return tId;
    }

    public void settId(TransactionId tId) {
        this.tId = tId;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public SQLStatement getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(SQLStatement sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    @Override
    public String toString() {
        return "UndoTableEntry{" +
                "rowId=" + rowId +
                ", sqlStatement=" + sqlStatement +
                '}';
    }
}
