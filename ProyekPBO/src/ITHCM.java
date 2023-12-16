import java.util.Scanner;
import java.sql.ResultSet;
// import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
// import java.

public class ITHCM{
    private String email, password, status;
    
    public boolean login() throws SQLException{
        var scn = new Scanner(System.in);
        System.out.print("Email: ");
        email = scn.next();
        System.out.print("Password: ");
        password = scn.next();
        System.out.print("Status: ");
        status = scn.next();

        if(status.toLowerCase().equals("admin")){
            String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
    
            if(result.next()){
                System.out.println("\n\nSelamat Datang, si "+ result.getString("id"));
                System.out.println();
                // System.out.println("Email: " + result.getString("email"));
                return true;
            }
            else {
                System.out.println("Akun tidak ditemukan !");
                return false;
            }
        } else if (status.toLowerCase().equals("mahasiswa")){
            String query = "SELECT * FROM mahasiswa m JOIN user u ON m.email = u.email WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
    
            if(result.next()){
                System.out.println("\n\nSelamat Datang, "+ result.getString("nama"));
                System.out.println("NIM: " + result.getString("nim"));
                return true;
            }
            else {
                System.out.println("Akun tidak ditemukan !");
                return false;
            }
        } else if (status.toLowerCase().equals("dosen")){
            String query = "SELECT * FROM dosen d JOIN user u ON d.email = u.email WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
    
            if(result.next()){
                System.out.println("\n\nSelamat Datang, "+ result.getString("nama"));
                System.out.println("NIDN: " + result.getString("nidn"));
                return true;
            }
            else {
                System.out.println("Akun tidak ditemukan !");
                return false;
            }
        } else {
            return false;
        }

    }

    public static void main(String[] args) throws SQLException {
        var scn = new Scanner(System.in);
        var obj = new ITHCM();
        
        boolean kondisi = obj.login();
        
        if(obj.status.equals("admin") && kondisi){
            var adm = new Admin();
            adm.namaAdmin = obj.status;
            System.out.println("Menu admin: ");
            System.out.println("1. Tambahkan Akun mahasiswa");
            System.out.println("2. Tambahkan Akun Dosen");
            System.out.println("3. Edit Akun");
            System.out.println("4. Edit Ruangan");
            System.out.println("5. Tampilkan data mahasiswa");
            System.out.println("___________________________________");
            System.out.print("Pilih menu: ");

            int menu = scn.nextInt();
            if(menu == 1){
                adm.tambahkanAkunMahasiswa();
            } else if (menu == 2){
                adm.tambahkanAkunDosen();
            } else if (menu == 5){
                adm.tampilkanDataMahasiswa();
            }

        }
    }
}
