package my_pack;

import java.util.ArrayList;

public class QueryParser {
    public ArrayList<Database> databaseList;
    public Database currentDatabase;

    public Query query;

    public QueryParser(ArrayList<Database> databaseList) {
        this.databaseList = databaseList;
    }

    public String parse(String mQuery) {
        String result = "TEST";
        mQuery = mQuery.substring(1, mQuery.length() - 1);
        String[] words = mQuery.split(" ");
        if (words[0].equals("CREATE") && words[1].equals("DATABASE") && words.length == 3) {
            String newDbName = words[2];
            Database newDb = new Database(newDbName, new ArrayList<Table>());
            Server.databaseList.add(newDb);
            Server.currentDatabase = newDb;
            Server.dbMap.put(newDbName, newDb);
            result = "Databse added : " + newDb.databaseName;
        } else if (words[0].equals("SHOW") && words[1].equals("DATABASE") && words.length == 2) {
            String dbName = " ";

            for (int i = 0; i < Server.databaseList.size(); i += 1) {
                dbName += "\n" + Server.databaseList.get(i).databaseName;
            }
            result = dbName;
        } else if (words[0].equals("USE") && words.length == 2) {
            try {
                Server.currentDatabase = Server.dbMap.get(words[1]);
                result = "SELECTED DATBASE " + Server.currentDatabase.databaseName;
            } catch (Exception e) {
                result = "DATABASE NOT FOUND";
            }

        } else {
            result = "Invalid input";
        }
        return result;
    }
}