package my_pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Query {
    private static String[] mCol = { "string", "int" };
    public static final List<String> COL_ATTR_LIST = Arrays.asList(mCol);
    // .asList({ "string", "int", "bool" });;

    public abstract String createDatabase(String newDbName);

    public String createWeakTable(String mQuery) {
        // mQuery = CREATE TABLE tableName {(colName:colType),(),()}
        String result;
        String[] spaceList = mQuery.split(" ");
        String tableName = spaceList[3].split("[{]")[0];
        if (Server.currentDatabase.tableMap.containsKey(tableName)) {
            result = "Table already exists";
        } else {
            String cols = mQuery.split("[{]")[1].replaceAll("}", "").replaceAll(" ", ""); // temp = (),()
            Map<String, String> columns = extractColumnAttributes(cols);

            if (columns == null) {
                result = "Invalid Syntax";
            } else {
                String primaryKey = "";
                Server.currentDatabase.addTable(tableName, primaryKey, columns);
                result = "New table " + tableName + " was successfully added";
            }
        }
        return (result);
    }

    public Map<String, String> extractColumnAttributes(String mQuery) {
        Map<String, String> colMap = new HashMap<>();
        String[] eachCol = mQuery.split(",");
        for (String col : eachCol) {
            col = col.replaceAll("[(]", "").replaceAll("[)]", "");
            String[] temp = col.split(":");
            if (temp.length > 2) {
                return (null);
            } else if (!COL_ATTR_LIST.contains(temp[1])) {
                return (null);
            } else {
                colMap.put(temp[0], temp[1]);
            }
        }
        return colMap;
    }

    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); // Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String showDatabase() {
        String result;
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
        result = output.beautify(dbList);
        return (result);
    }

    public static class output {

        public static String beautify(ArrayList<ArrayList<String>> result) {
            ArrayList<String> lenghts = result.get(0);

            result.remove(0);
            StringBuilder newResult = new StringBuilder();

            int wholeMaxLen = 0;
            for (int i = 0; i < lenghts.size(); i += 1) {
                wholeMaxLen += (3 + Integer.valueOf(lenghts.get(i)));
            }
            // Adding header top line
            newResult.append("\n+" + getBorder(wholeMaxLen) + "+\n|");

            // Adding columnNames row
            ArrayList<String> currentRow = result.get(0);
            for (int j = 0; j < currentRow.size(); j += 1) {
                int maxLen = Integer.valueOf(lenghts.get(j));
                String currentString = currentRow.get(j);
                newResult.append(" " + currentString + " ");
                for (int k = 0; k <= maxLen - currentString.length(); k += 1)
                    newResult.append(" ");
                newResult.append("|");
            }
            // adding header bottom line
            newResult.append("\n+" + getBorder(wholeMaxLen) + "+\n|");

            // adding rows
            for (int i = 1; i < result.size(); i += 1) {
                currentRow = result.get(i);
                for (int j = 0; j < currentRow.size(); j += 1) {
                    int maxLen = Integer.valueOf(lenghts.get(j));
                    String currentString = currentRow.get(j);
                    newResult.append(" " + currentString + " ");
                    for (int k = 0; k <= maxLen - currentString.length(); k += 1)
                        newResult.append(" ");
                    newResult.append("|\n|");
                }
            }
            newResult.replace(newResult.length() - 2, newResult.length(), "");
            newResult.append("\n+" + getBorder(wholeMaxLen) + "+\n");

            String stringRes = new String(newResult);
            return stringRes;
        }

        private static String getBorder(int size) {
            StringBuffer outputBuffer = new StringBuffer(size);
            for (int i = 0; i < size; i++) {
                outputBuffer.append("-");
            }
            return outputBuffer.toString();
        }
    }

}
