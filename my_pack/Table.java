package my_pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
