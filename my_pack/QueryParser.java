package my_pack;

import java.io.IOException;
import java.util.*;

public class QueryParser extends Query {
    public ArrayList<Database> databaseList;
    public Database currentDatabase;

    public QueryParser(ArrayList<Database> databaseList) {
        this.databaseList = databaseList;
    }

    public String parse(String mQuery) {
        String result = "test";
        mQuery = mQuery.substring(1, mQuery.length() - 1);
        String[] words = mQuery.split(" ");
        if (mQuery.matches("(?i).*CREATE DATABASE [A-Za-z0-9]+.*")) {
            String newDbName = words[2];
            result = createDatabase(newDbName);
            Server tempServer = new Server();
            String filePath = "C:\\Users\\adhok\\Desktop\\MCA3\\JAVA\\MySql\\Output\\Server";
            try {
                FileManager.WriteObjectToFile(tempServer, filePath);
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Failed to save the database to storage");
                e.printStackTrace();
            }
        } else if (mQuery.matches("(?i).*SHOW DATABASE.*") &&
                words.length == 2) {
            result = showDatabase();
        } else if (mQuery.matches("(?i).*USE [A-Za-z0-9]+.*")) {
            try {
                Server.currentDatabase = Server.databaseList.get(Server.dbMap.get(words[1]));
                result = "SELECTED DATBASE : " + Server.currentDatabase.databaseName;
            } catch (Exception e) {
                result = "DATABASE NOT FOUND";
            }

        } else if (mQuery.matches("(?i).*DELIMITER [$&+,:;=?@#|'<>.^*()%!-]+.*")) {
            Server.delimiter = words[1].charAt(0);
            result = "Delimiter set to " + words[1].charAt(0);
        }

        else if (mQuery.matches("(?i).*CREATE WEAK TABLE [A-Za-z0-9].*")) {
            if (Server.currentDatabase == null) {
                result = "No database selected..!";
            } else {
                result = createWeakTable(mQuery);
            }
        }

        else if (mQuery.matches("(?i).*SHOW TABLE.*")) {
            if (Server.currentDatabase == null) {
                result = "No database selected.";
            } else {
                ArrayList<ArrayList<String>> tableList = new ArrayList<>();
                ArrayList<String> title = new ArrayList<>();
                title.add("Tables");
                tableList.add(title);
                int maxLen = 6;
                for (int i = 0; i < Server.currentDatabase.tableList.size(); i += 1) {
                    ArrayList<String> temp = new ArrayList<>();
                    String tableName = Server.currentDatabase.tableList.get(i).tableName;
                    temp.add(tableName);
                    if (tableName.length() > maxLen) {
                        maxLen = tableName.length();
                    }
                    tableList.add(temp);
                }
                ArrayList<String> longestLen = new ArrayList<>();
                longestLen.add(String.valueOf(maxLen));
                tableList.add(0, longestLen);
                result = Query.output.beautify(tableList);

            }
        } else if (mQuery.matches("(?i).*cls.*")) {
            Query.clearConsole();
            result = "";
            Server newServer = new Server();
            String filePath = "C:\\Users\\adhok\\Desktop\\MCA3\\JAVA\\MySql\\Output\\Server";

            newServer.setTest("test");
            try {
                FileManager.WriteObjectToFile(newServer, filePath);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (mQuery.matches("(?i).*exit.*")) {
            System.out.println("\nBye.\n");
            System.exit(0);
        } else {
            result = "Invalid input";
        }

        return result;
    }

    @Override
    public String createDatabase(String newDbName) {
        {
            String result;
            Database newDb = new Database(newDbName, new ArrayList<Table>());
            if (Server.dbMap.containsKey(newDbName)) {
                result = "Database " + newDbName + " already exists.";
            } else {
                Server.dbMap.put(newDbName, Server.databaseList.size());
                Server.databaseList.add(newDb);
                result = "Database added : " + newDb.databaseName;
            }
            return result;
        }
    }
}