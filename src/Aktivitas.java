import java.io.*;
// import java.io.OutputStreamWriter;
// import java.io.FileWriter;
// import java.io.FileWriter.filewriter;
// import java.io.IOException;
// import java.io.BufferedWriter;

public class Aktivitas {

    public static void createFile() {
        try {
            File myObj = new File("aktivitas.txt");
            myObj.createNewFile();
            // if (myObj.createNewFile()) {
            //   System.out.println("File created: " + myObj.getName());
            // } else {
            //   System.out.println("File already exists.");
            // }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public static void writeFile() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("aktivitas.txt"));
            writer.write("Daftar Aktivitas User: ");
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }    

    public static void main(String[] args) {
        Aktivitas.createFile();
        // Aktivitas.writeFile();
    }
}
