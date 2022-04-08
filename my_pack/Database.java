package my_pack;

import java.util.*;

public class Database {
    ArrayList<Table> tableList;

    String databaseName;

    public Database(String databaseName, ArrayList<Table> tableList) {
        this.tableList = new ArrayList<Table>();
        this.databaseName = databaseName;
    }

    public void createTable(String tableName, Map<String, ArrayList<String>> columnAttributes, String primaryKey) {
        // Create a new Table add it to the tableList

        Table newTable = new Table(tableName, columnAttributes);
        this.tableList.add(newTable);
    }

    public ArrayList<Table> getTableList() {
        return tableList;
    }

    public String getDatabaseName() {
        return databaseName;
    }

}
