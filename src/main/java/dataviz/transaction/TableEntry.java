package dataviz.transaction;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableEntry that = (TableEntry) o;

        if (id != that.id) return false;
        if (Double.compare(that.currentAmount, currentAmount) != 0) return false;
        if (Double.compare(that.maxAmount, maxAmount) != 0) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(currentAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
