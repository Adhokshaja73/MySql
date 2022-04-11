package my_pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    // CREATE TABLE tableName(col_name col_attr1 colattr2, . . . . coll_attr_n.,
    // col_name_2 ... )
    public String tableName;
    public String primaryKey;
    public Map<String, String> columnAttributes = new HashMap<>();
    public ArrayList<ArrayList<String>> rows;

    public Table(String tableName, String primaryKey) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public void addColumn(String colName, String colAttr) {
        this.columnAttributes.put(colName, colAttr);
    }

    public void insertRow() {

    }
}
