package my_pack;

public class Table extends WeakTable {
    public String primaryKey;

    public Table(String tableName, String primaryKey) {
        super(tableName);
        Table(primaryKey);
    }

    private void Table(String primaryKey) {
        this.primaryKey = primaryKey;
    }

}
