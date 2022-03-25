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
            result = "Databse added : " + newDb.databaseName;
        }
        if (words[0] == "SHOW" && words[1] == "DATABASE" && words.length == 2) {
            String dbName = String.valueOf(Server.databaseList.size());

            /*
             * for (int i = 0; i < Server.databaseList.size(); i += 1) {
             * dbName += "\n THIS" + Server.databaseList.get(i).databaseName;
             * }
             */
            result = dbName;
        }
        return result;
    }
}