package my_pack;

import java.io.Serializable;
import java.util.*;

public class Database implements Serializable {
    public ArrayList<WeakTable> tableList;
    public Map<String, Integer> tableMap = new HashMap<>();

    String databaseName;

    public Database(String databaseName, ArrayList<Table> tableList) {
        this.tableList = new ArrayList<WeakTable>();
        this.databaseName = databaseName;
    }

    public void addTable(String tableName, Map<String, String> columns) {
        WeakTable newTable = new WeakTable(tableName);
        this.tableMap.put(tableName, tableMap.size());
        this.tableList.add(newTable);
    }

    public void addTable(String tableName, String primaryKey, Map<String, String> columns) {
        // Create a new Table add it to the tableList

        Table newTable = new Table(tableName, primaryKey);
        this.tableMap.put(tableName, tableMap.size());
        this.tableList.add(newTable);
    }

    public ArrayList<WeakTable> getTableList() {
        return tableList;
    }

    public String getDatabaseName() {
        return databaseName;
    }

}
