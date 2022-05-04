package my_pack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    public static void WriteObjectToFile(Object serObj, String filepath) throws IOException, ClassNotFoundException {
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(serObj);
        objectOut.close();
        System.out.println("The Object  was succesfully written to a file");

    }

    public static Object ReadObjectFromFile(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

        Object obj = objectIn.readObject();

        System.out.println("The Object has been read from the file");
        objectIn.close();
        return obj;
    }
}
