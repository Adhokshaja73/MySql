import java.util.ArrayList;
import java.util.Scanner;

import my_pack.*;

public class Main {
    public static ArrayList<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        Server server;
        try {
            if (args[0].equals("--createuser")) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter user name : ");
                String name = sc.nextLine();

                System.out.print("Enter password : ");
                String password = sc.nextLine();

                sc.close();

                userList.add(new User(name, password));
                // File Manager to add user data to storage
            }
            if (args[0].equals("-u")) {

            }
        } catch (Exception e) {

        } finally {
            try {
                server = (Server) FileManager
                        .ReadObjectFromFile("C:\\Users\\adhok\\Desktop\\MCA3\\JAVA\\MySql\\Output\\Server");
                System.out.println("Server loaded");

            } catch (Exception e) {
                System.out.println("Loading server failed.");
                server = new Server();
            }
            server.takeQuery();
        }
    }
}
