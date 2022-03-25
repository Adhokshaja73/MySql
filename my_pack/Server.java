package my_pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
	public static ArrayList<Database> databaseList = new ArrayList<>();
	public static Map<String, Database> dbMap = new HashMap<>();
	public static Database currentDatabase;
	private static Scanner sc = new Scanner(System.in);
	private static QueryParser queryParser = new QueryParser(databaseList);

	public void takeQuery() {
		String newQuery = " ";
		while (newQuery.charAt(newQuery.length() - 1) != ';') {
			System.out.print("\nMySql > ");
			newQuery += sc.nextLine();
		}

		String result = queryParser.parse(newQuery);
		System.out.println("\n" + result);

		takeQuery();
	}
}