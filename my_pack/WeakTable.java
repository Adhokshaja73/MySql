package my_pack;

import java.util.*;

public class WeakTable {
    public final String tableName;
    public Map<String, String> columnAttributes = new HashMap<>();
    public ArrayList<ArrayList<String>> rows;

    public WeakTable(String tableName) {
        this.tableName = tableName;
    }

    public void addColumn(String colName, String colAttr) {
        this.columnAttributes.put(colName, colAttr);
    }

    public void insertRow() {

    }
}
