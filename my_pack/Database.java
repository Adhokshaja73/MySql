package my_pack;

import java.util.*;

public class Database {
    public ArrayList<Table> tableList;
    public Map<String, Integer> tableMap = new HashMap<>();

    String databaseName;

    public Database(String databaseName, ArrayList<Table> tableList) {
        this.tableList = new ArrayList<Table>();
        this.databaseName = databaseName;
    }

    public void addTable(String tableName) {
        Table newTable = new Table(tableName);
        this.tableList.add(newTable);
    }

    public void addTable(String tableName, String primaryKey) {
        // Create a new Table add it to the tableList

        Table newTable = new Table(tableName, primaryKey);
        this.tableList.add(newTable);
    }

    public ArrayList<Table> getTableList() {
        return tableList;
    }

    public String getDatabaseName() {
        return databaseName;
    }

}
