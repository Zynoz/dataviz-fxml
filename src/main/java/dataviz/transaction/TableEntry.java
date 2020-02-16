package dataviz.transaction;

public class TableEntry {

    private long id;
    private String name;
    private double currentAmount;
    private double maxAmount;

    public TableEntry() {
    }

    public TableEntry(long id, String name, double currentAmount, double maxAmount) {
        this.id = id;
        this.name = name;
        this.currentAmount = currentAmount;
        this.maxAmount = maxAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public String toString() {
        return "TableEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentAmount=" + currentAmount +
                ", maxAmount=" + maxAmount +
                '}';
    }
}
