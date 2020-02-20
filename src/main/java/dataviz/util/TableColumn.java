package dataviz.util;

public enum TableColumn {
    NAME,
    AMOUNT,
    MAXAMOUNT,
    INVALID;

    static TableColumn getTableColumn(String tableColumn) {
        System.out.println(tableColumn);
        if (NAME.name().toLowerCase().equals(tableColumn)) {
            return NAME;
        } else if (AMOUNT.name().toLowerCase().equals(tableColumn)) {
            return AMOUNT;
        } else if (MAXAMOUNT.name().toLowerCase().equals(tableColumn)) {
            return MAXAMOUNT;
        } else {
            return INVALID;
        }
    }
}
