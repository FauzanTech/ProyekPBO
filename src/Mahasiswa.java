import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Mahasiswa extends Roster{
    public String namaMahasiswa, NIM, Jurusan, Prodi;


    public static void main(String[] args) throws SQLException{
        tampilkanRuangan();
        tampilkanRoster();
    }

    // static public String tampilkanRoster() throws SQLException {
    //     Roster.tampilkanRoster();
    // } 
}
