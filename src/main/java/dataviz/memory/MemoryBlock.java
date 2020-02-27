package dataviz.memory;

public class MemoryBlock {

    private Record record1, record2;

    public MemoryBlock() {
    }

    public MemoryBlock(Record record1, Record record2) {
        setRecord1(record1);
        setRecord2(record2);
    }

    public Record getRecord1() {
        return record1;
    }

    public Record getRecord2() {
        return record2;
    }

    public void setRecord1(Record record1) {
        this.record1 = record1;
    }

    public void setRecord2(Record record2) {
        this.record2 = record2;
    }



}
