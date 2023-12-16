import java.util.Scanner;
// import java.sql.Connection;
// import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
    public String namaAdmin;
    private String userEmail;

    void createTableAdmin() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS admin (id int(11) PRIMARY KEY AUTO_INCREMENT, email varchar(50) NOT NULL, password varchar(50) NOT NULL, FOREIGN KEY (email) REFERENCES user(email))";
        var stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
        System.out.println("Pembuatan table berhasil!");
    }

    void insertDataAdmin() throws SQLException{
        String query = "INSERT INTO user (email, password, status) VALUE (?, ?, ?)";
        var stmt = ConnectionDB.getConnection().prepareStatement(query);
        String email = "admin123@gmail.com";
        String pass = "admin123";
        stmt.setString(1, email);
        stmt.setString(2, pass);
        stmt.setString(3, "admin");
        int result = stmt.executeUpdate();
        if(result > 0){
            query = "INSERT INTO admin (email, password) VALUE (?, ?)";
            var stmt2 = ConnectionDB.getConnection().prepareStatement(query);
            stmt2.setString(1, email);
            stmt2.setString(2, pass);
            result = stmt2.executeUpdate();
            if(result > 0){
                System.out.println("Insert data berhasil!");
            } else {
                System.out.println("Insert data gagal!");
            }
        } else {
            System.out.println("Insert data gagal!");
        }
    }

    void changePassAdmin(String newPassword, int id) throws SQLException{
        String query = "UPDATE admin SET password = ? WHERE id = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, newPassword);
        stmt.setInt(2, id);
        int res = stmt.executeUpdate();
        if(res > 0){
            System.out.println("Data berhasil di update!");
        } else {
            System.out.println("Data gagal di update!");
        }
    }

    void deleteAdmin(String email, String password) throws SQLException{
        String query = "DELETE FROM admin WHERE email = ? and password = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, email);
        stmt.setString(2, password);
        int res = stmt.executeUpdate();

        if(res > 0){
            System.out.println("Data berhasil dihapus!");
        } else {
            System.out.println("Data gagal dihapus!");
        }
    }

    void createTableProdi() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS prodi (id_prodi int(11) PRIMARY KEY AUTO_INCREMENT, prodi varchar(255) NOT NULL)";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    void createTableJurusan() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS jurusan (id_jurusan int(11) PRIMARY KEY AUTO_INCREMENT, jurusan varchar(255) NOT NULL)";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    // void insertJurusan() throws SQLException {
    //     String query = "INSERT INTO jurusan ()"
    // }

    void createTableKelas() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS kelas (singkatan_kelas varchar(255) PRIMARY KEY, nama_kelas varchar(255) NOT NULL)";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    void createTableUser() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS user (email varchar(255) PRIMARY KEY, password varchar(255), status varchar(50) NOT NULL)";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    void createTableMatkul() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS mata_kuliah (kode_matkul varchar(255) PRIMARY KEY, nama_matkul varchar(255) NOT NULL, sks int(11))";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    void createTableRuangan() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS ruangan (kode_ruangan varchar(255) PRIMARY KEY, nama_ruangan varchar(255) NOT NULL, kapasitas int(11))";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    void createTableRoster() throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS roster (hari varchar(20) NOT NULL, singkatan_kelas varchar(255) NOT NULL, kode_matkul varchar(255) NOT NULL, kode_ruangan varchar(255) NOT NULL, waktu varchar(50) NOT NULL, nidn varchar(20) NOT NULL, FOREIGN KEY (singkatan_kelas) REFERENCES kelas(singkatan_kelas), FOREIGN KEY (nidn) REFERENCES dosen(nidn), FOREIGN KEY (kode_ruangan) REFERENCES ruangan(kode_ruangan), FOREIGN KEY (kode_matkul) REFERENCES mata_kuliah(kode_matkul))";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    Admin() throws SQLException{
        // String query = "SELECT * FROM admin";
        // Statement stmt = ConnectionDB.getConnection().createStatement();
        // ResultSet res = stmt.executeQuery(query);
        // if(!res.next()){
        // }
        createTableUser();
        createTableAdmin();
        insertDataAdmin();
        createTableKelas();
        createTableProdi();
        createTableJurusan();
        createTableMahasiswa();
        createTableDosen();
        createTableMatkul();
        createTableRuangan();
        createTableRoster();
    }
    public static void main(String[] args) throws SQLException{
        Admin adm = new Admin();
        // adm.deleteAdmin("admin123@gmail.com", "admin12*");
    }
    
    public void createTableMahasiswa() throws SQLException{
        var stmt = ConnectionDB.getConnection().createStatement();
        String query = "CREATE TABLE IF NOT EXISTS mahasiswa (nim varchar(20) PRIMARY KEY, nama varchar(255) NOT NULL, singkatan_kelas varchar(255) NOT NULL, id_jurusan int(11) NOT NULL, id_prodi int(11) NOT NULL, email varchar(255) NOT NULL, FOREIGN KEY (email) REFERENCES user(email), FOREIGN KEY (id_jurusan) REFERENCES jurusan(id_jurusan), FOREIGN KEY (singkatan_kelas) REFERENCES kelas(singkatan_kelas) ,FOREIGN KEY (id_prodi) REFERENCES prodi(id_prodi))";
        stmt.execute(query);
    }

    public void createTableDosen() throws SQLException{
        var stmt = ConnectionDB.getConnection().createStatement();
        String query = "CREATE TABLE IF NOT EXISTS dosen (nidn varchar(20) PRIMARY KEY, nama varchar(255) NOT NULL, id_jurusan int(11), id_prodi int(11), email varchar(255), FOREIGN KEY (email) REFERENCES user(email), FOREIGN KEY (id_prodi) REFERENCES prodi(id_prodi), FOREIGN KEY (id_jurusan) REFERENCES jurusan(id_jurusan))";
        stmt.execute(query);
    }

    public boolean tambahkanAkunMahasiswa() throws SQLException{
        // this.createTableMahasiswa();
        var scn = new Scanner(System.in);
        String nama, kelas, query, nim, password;
        int id_jurusan, id_prodi;
        
        System.out.print("Masukkan email mahasiswa: ");
        userEmail = scn.nextLine();
        System.out.print("Masukkan password mahasiswa: ");
        password = scn.nextLine();
        System.out.println("===================================");
        System.out.print("Masukkan nama mahasiswa: ");
        nama = scn.nextLine();
        System.out.print("Masukkan kelas mahasiswa: ");
        kelas = scn.nextLine();
        System.out.print("Masukkan NIM mahasiswa: ");
        nim = scn.nextLine();
        System.out.print("Masukkan jurusan mahasiswa: ");
        id_jurusan = scn.nextInt();
        System.out.print("Masukkan prodi mahasiswa: ");
        id_prodi = scn.nextInt();
        
        query = "INSERT INTO user (email, password, status) VALUE (?, ?, ?)";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, userEmail);
        stmt.setString(2, password);
        stmt.setString(3, "mahasiswa");
        int result = stmt.executeUpdate();
        scn.close();
        if(result > 0){
            query = "INSERT INTO mahasiswa (nim, nama, kelas, id_jurusan, id_prodi, email) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt2 = ConnectionDB.getConnection().prepareStatement(query);
            stmt2.setString(1, nim);
            stmt2.setString(2, nama);
            stmt2.setString(3, kelas);
            stmt2.setInt(4, id_jurusan);
            stmt2.setInt(5, id_prodi);
            stmt2.setString(6, userEmail);
            result = stmt2.executeUpdate();
            if(result > 0){
                System.out.println("Insert data berhasil!");
                return true;
            }
            return false;
        } else {
            System.out.println("Insert data gagal!");
            return false;
        }
    }

    public boolean tambahkanAkunDosen() throws SQLException{
        // this.createTableDosen();
        var scn = new Scanner(System.in);
        String nama, query, password, nidn;
        int id_jurusan, id_prodi;
        
        System.out.print("Masukkan email dosen: ");
        userEmail = scn.nextLine();
        System.out.print("Masukkan password dosen: ");
        password = scn.nextLine();
        System.out.println("===================================");
        System.out.print("Masukkan nama dosen: ");
        nama = scn.nextLine();
        System.out.print("Masukkan nidn dosen: ");
        nidn = scn.nextLine();
        System.out.print("Masukkan jurusan dosen: ");
        id_jurusan = scn.nextInt();
        System.out.print("Masukkan prodi dosen: ");
        id_prodi = scn.nextInt();

        query = "INSERT INTO user (email, password, status) VALUE (?, ?, ?)";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, userEmail);
        stmt.setString(2, password);
        stmt.setString(3, "dosen");
        int result = stmt.executeUpdate();
        scn.close();
        if(result > 0){
            query = "INSERT INTO dosen (nidn, nama, id_jurusan, id_prodi, email) VALUE (?, ?, ?, ?, ?)";
            PreparedStatement stmt2 = ConnectionDB.getConnection().prepareStatement(query);
            stmt2.setString(1, nidn);
            stmt2.setString(2, nama);
            stmt2.setInt(3, id_jurusan);
            stmt2.setInt(4, id_prodi);
            stmt2.setString(5, userEmail);
            result = stmt2.executeUpdate();
            if(result > 0){
                System.out.println("Insert data berhasil!");
                return true;
            }
            return false;
        } else {
            System.out.println("Insert data gagal!");
            return false;
        }
    }
    

    public boolean editAkun(String ni, String pk,String target, String newValue) {
        if(pk.toLowerCase().equals("nim")){
            String query = "UPDATE mahasiswa SET ? = ? WHERE nim = ?";
            
        }
        return false;
    }
    
    public void tampilkanDataMahasiswa() throws SQLException{
        String query = "SELECT m.*, p.prodi, j.jurusan FROM mahasiswa m JOIN prodi p ON m.id_prodi = p.id_prodi JOIN jurusan j ON m.id_jurusan = j.id_jurusan";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);
        while (res.next()) {
            System.out.println("Nama: " + res.getString("nama"));
            System.out.println("NIM: " + res.getString("nim"));
            System.out.println("Kelas: " + res.getString("kelas"));
            System.out.println("Jurusan: " + res.getString("jurusan"));
            System.out.println("Prodi: " + res.getString("prodi"));
            System.out.println();
        }
    }

    // public String editRuangan() {}


}
