import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Aktivitas {
    static String namaFile = "aktivitas.txt";

    public static void createFile() {
        try{
            File obj = new File(namaFile);
            if(obj.createNewFile()) {
                FileWriter writer = new FileWriter(namaFile);
                writer.write("Daftar aktivitas user: ");
                writer.close();
            } else {
            }
        } catch (IOException e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

    public static void readFile() {
        try {
            File Obj = new File(namaFile);
            Scanner Reader = new Scanner(Obj);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                System.out.println(data);
            }
            Reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }

    }

    public static void writeFile(String kalimat) {
        try {
            FileWriter Writer = new FileWriter(namaFile, true);
            Writer.write("\n" + kalimat);
            Writer.close();
        }
        catch (IOException e) {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }

}