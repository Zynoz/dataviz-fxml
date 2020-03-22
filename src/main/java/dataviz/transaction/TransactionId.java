package dataviz.transaction;

import java.util.UUID;

public class TransactionId {
    private String tId;

    public TransactionId() {
        this.tId = UUID.randomUUID().toString().substring(0, 7);
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "TransactionId{" +
                "tId='" + tId + '\'' +
                '}';
    }
}
