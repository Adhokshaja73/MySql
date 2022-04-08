package my_pack;

import java.util.ArrayList;
import java.util.Map;

public class Table {
    // CREATE TABLE tableName(col_name col_attr1 colattr2, . . . . coll_attr_n.,
    // col_name_2 ... )
    public String tableName;
    public String primaryKey;
    public Map<String, ArrayList<String>> columnAttributes;
    public ArrayList<ArrayList<String>> columns;

    public Table(String tableName, Map<String, ArrayList<String>> columnAttributes) {
        this.tableName = tableName;

    }

    public void addColumn() {
    }
}
