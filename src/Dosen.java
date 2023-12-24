import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dosen extends Roster{

    public static void tampilkanNidn() throws SQLException {
        String query = "SELECT * FROM dosen";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        System.out.println("\nDaftar NIDN dan nama dosen: ");
        while (res.next()) {
            System.out.println("Nama Dosen: " + res.getString("nama") + " NIDN : " + res.getString("nidn"));
        }
    }
}
