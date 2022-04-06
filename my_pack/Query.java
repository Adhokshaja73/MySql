package my_pack;

import java.util.ArrayList;

public class Query {
    public void hello() {
        System.out.println("Hello");
    }

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
