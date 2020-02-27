package dataviz.memory;

public class Record {

    private long id;
    private String name;
    private double currentAmount;

    public Record() {
    }

    public Record(long id, String name, double currentAmount) {

        this.id = id;
        this.name = name;
        this.currentAmount = currentAmount;

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

}
