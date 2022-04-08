package my_pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
	public static ArrayList<Database> databaseList = new ArrayList<>();
	public static Map<String, Integer> dbMap = new HashMap<>();
	public static Database currentDatabase;
	private static Scanner sc = new Scanner(System.in);
	private static QueryParser queryParser = new QueryParser(databaseList);
	public static char delimiter = ';';

	public void takeQuery() {
		StringBuffer newQuery = new StringBuffer(" ");
		while (newQuery.charAt(newQuery.length() - 1) != delimiter) {
			System.out.print("\nMySql > ");
			newQuery.append(sc.nextLine());
		}

		String result = queryParser.parse(new String(newQuery));
		System.out.println(result);
		takeQuery();
	}

	static {
		System.out.println(
				" \n  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  \n | .--------------. || .--------------. || .--------------. || .--------------. || .--------------. | \n | | ____    ____ | || |  ____  ____  | || |    _______   | || |    ___       | || |   _____      | | \n | ||_   \\  /   _|| || | |_  _||_  _| | || |   /  ___  |  | || |  .'   '.     | || |  |_   _|     | | \n | |  |   \\/   |  | || |   \\ \\  / /   | || |  |  (__ \\_|  | || | /  .-.  \\    | || |    | |       | | \n | |  | |\\  /| |  | || |    \\ \\/ /    | || |   '.___`-.   | || | | |   | |    | || |    | |   _   | | \n | | _| |_\\/_| |_ | || |    _|  |_    | || |  |`\\____) |  | || | \\  `-'  \\_   | || |   _| |__/ |  | | \n | ||_____||_____|| || |   |______|   | || |  |_______.'  | || |  `.___.\\__|  | || |  |________|  | | \n | |              | || |              | || |              | || |              | || |              | | \n | '--------------' || '--------------' || '--------------' || '--------------' || '--------------' | \n  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
	}
}