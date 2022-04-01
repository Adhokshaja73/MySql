package my_pack;

import java.util.ArrayList;

public class QueryParser {
    public ArrayList<Database> databaseList;
    public Database currentDatabase;

    public Query query = new Query();

    public QueryParser(ArrayList<Database> databaseList) {
        this.databaseList = databaseList;
    }

    public String parse(String mQuery) {
        String result = "test";
        mQuery = mQuery.substring(1, mQuery.length() - 1);

        String[] words = mQuery.split(" ");

        if (mQuery.matches("CREATE DATABASE [A-Za-z0-9]+")) {
            String newDbName = words[2];
            Database newDb = new Database(newDbName, new ArrayList<Table>());
            Server.databaseList.add(newDb);
            Server.currentDatabase = newDb;
            Server.dbMap.put(newDbName, newDb);
            result = "Databse added : " + newDb.databaseName;
        } else if (mQuery.matches("SHOW DATABASE") &&
                words.length == 2) {
            ArrayList<ArrayList<String>> dbList = new ArrayList<>();
            ArrayList<String> title = new ArrayList<>();
            title.add("Databases");
            dbList.add(title);
            for (int i = 0; i < Server.databaseList.size(); i += 1) {
                ArrayList<String> temp = new ArrayList<>();
                temp.add(Server.databaseList.get(i).getDatabaseName());
                dbList.add(temp);
            }
            result = Query.beautify(dbList);
        } else if (mQuery.matches("USE [A-Za-z0-9]+")) {
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