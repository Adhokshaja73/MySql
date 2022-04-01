package my_pack;

import java.util.ArrayList;

public class Query {
    public void hello() {
        System.out.println("Hello");
    }

    public static String beautify(ArrayList<ArrayList<String>> result) {
        int maxLen = getMaxLen(result);
        StringBuilder newResult = new StringBuilder();

        newResult.append("\n+" + getBorder(maxLen * result.get(0).size() + 2 * (result.get(0).size())) + "+\n| ");
        ArrayList<String> currentRow = result.get(0);
        for (int j = 0; j < currentRow.size(); j += 1) {
            String currentString = currentRow.get(j);
            newResult.append(currentString);
            for (int k = 0; k <= maxLen - currentString.length(); k += 1)
                newResult.append(" ");
            newResult.append("|");
        }
        newResult.append("\n+" + getBorder(maxLen * result.get(0).size() + 2 * (result.get(0).size())) + "+\n| ");

        for (int i = 1; i < result.size(); i += 1) {
            currentRow = result.get(i);
            for (int j = 0; j < currentRow.size(); j += 1) {
                String currentString = currentRow.get(j);
                newResult.append(currentString);
                for (int k = 0; k <= maxLen - currentString.length(); k += 1)
                    newResult.append(" ");
                newResult.append("|\n| ");

            }
        }
        newResult.replace(newResult.length() - 3, newResult.length(), "");
        newResult.append("\n+" + getBorder(maxLen * result.get(0).size() + 2 * (result.get(0).size())) + "+\n");

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

    private static int getMaxLen(ArrayList<ArrayList<String>> result) {
        int maxLen = 0;
        for (int i = 0; i < result.size(); i += 1) {
            ArrayList<String> currentRow = result.get(i);
            for (int j = 0; j < currentRow.size(); j += 1) {
                if (maxLen < currentRow.get(j).length()) {
                    maxLen = currentRow.get(j).length();
                }
            }
        }
        return maxLen;
    }
}
