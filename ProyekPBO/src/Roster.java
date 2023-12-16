import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Roster {

    void selectDataRoster(){
        String query = "SELECT ";
    }

    static void tampilkanRoster(){


        System.out.println("SENIN");
        System.out.printf("%170s\n", "Ruang Kampus 2 Gedung Pemuda");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", "Waktu", "");
    }

    public static void main(String[] args) {
        tampilkanRoster();
    }
}
