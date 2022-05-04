package my_pack;

import java.util.*;

import javax.print.event.PrintEvent;
import javax.swing.text.html.HTMLDocument.RunElement;

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
            result = query.createDatabase(newDbName);
        } else if (mQuery.matches("(?i).*SHOW DATABASE.*") &&
                words.length == 2) {
            result = query.showDatabase();
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
                result = query.createWeakTable(mQuery);
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