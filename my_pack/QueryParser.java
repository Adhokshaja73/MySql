package my_pack;

import java.util.*;

import javax.print.event.PrintEvent;

public class QueryParser {
    public ArrayList<Database> databaseList;
    public Database currentDatabase;

    public Query query = new Query();

    private final List<String> columnAttributes = Arrays.asList("VARCHAR", "CHAR", "INT");

    public QueryParser(ArrayList<Database> databaseList) {
        this.databaseList = databaseList;
    }

    public String parse(String mQuery) {
        String result = "test";
        mQuery = mQuery.substring(1, mQuery.length() - 1);
        String[] words = mQuery.split(" ");
        if (mQuery.matches("(?i).*CREATE DATABASE [A-Za-z0-9]+.*")) {
            String newDbName = words[2];
            Database newDb = new Database(newDbName, new ArrayList<Table>());
            if (Server.dbMap.containsKey(newDbName)) {
                result = "Database " + newDbName + " already exists.";
            } else {
                Server.dbMap.put(newDbName, databaseList.size());
                Server.databaseList.add(newDb);
                result = "Database added : " + newDb.databaseName;
            }
        } else if (mQuery.matches("(?i).*SHOW DATABASE.*") &&
                words.length == 2) {
            ArrayList<ArrayList<String>> dbList = new ArrayList<>();
            ArrayList<String> title = new ArrayList<>();
            title.add("Databases");
            dbList.add(title);
            int maxLen = 8;
            for (int i = 0; i < Server.databaseList.size(); i += 1) {
                ArrayList<String> temp = new ArrayList<>();
                String currentDbName = Server.databaseList.get(i).getDatabaseName();
                temp.add(currentDbName);
                if (currentDbName.length() > maxLen) {
                    maxLen = currentDbName.length();
                }
                dbList.add(temp);
            }
            ArrayList<String> longestLen = new ArrayList<>();
            longestLen.add(String.valueOf(maxLen));
            dbList.add(0, longestLen);
            result = Query.output.beautify(dbList);
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

        // CREATE TABLE tableName {}
        else if (mQuery.matches("(?i).*CREATE WEAK TABLE [A-Za-z0-9].*")) {
            if (Server.currentDatabase == null) {
                result = "No database selected..!";
            } else {
                String newTableName = words[2];
                Server.currentDatabase.addTable(newTableName);
                Table currentTable = Server.currentDatabase.tableList.get(Server.currentDatabase.tableList.size() - 1);
                // Extract the table name, the column names and column attributes
                String[] args = mQuery.split("[{]");
                String[] colums = args[1].split(",");
                for (String mCol : colums) {
                    String[] currentColattrs = mCol.split(" ");
                    System.out.println(currentColattrs);

                    if (currentColattrs.length > 2) {
                        result = "Invalid column attribute";
                        break;
                    } else {
                        currentTable.addColumn(currentColattrs[0], currentColattrs[1]);
                    }
                    result = "Table created.";
                }
                // For all elements in columns, do split with ' '

                // Create a new table
                // add it to Server.currentDatabase
                // Success message;
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
        } else if (mQuery.matches("(?i).*exit.*")) {
            System.out.println("\nBye.\n");
            System.exit(0);
        } else {
            result = "Invalid input";
        }

        return result;
    }
}