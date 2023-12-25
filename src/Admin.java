import java.util.Scanner;
// import java.sql.Connection;
// import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {

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
        String query = "CREATE TABLE IF NOT EXISTS roster (hari varchar(20), singkatan_kelas varchar(255) NOT NULL, kode_matkul varchar(255) NOT NULL, kode_ruangan varchar(255) NOT NULL, waktu varchar(50) NOT NULL, nidn varchar(20) NOT NULL, FOREIGN KEY (singkatan_kelas) REFERENCES kelas(singkatan_kelas), FOREIGN KEY (nidn) REFERENCES dosen(nidn), FOREIGN KEY (kode_ruangan) REFERENCES ruangan(kode_ruangan), FOREIGN KEY (kode_matkul) REFERENCES mata_kuliah(kode_matkul))";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    void insertKelas() throws SQLException{
        String query = "INSERT INTO kelas(singkatan_kelas, nama_kelas) VALUES ('IK22-A','Ilmu Komputer 2022 A'),('IK22-B','Ilmu Komputer 2022 B'),('IK22-C','Ilmu Komputer 2022 C'),('IK22-D','Ilmu Komputer 2022 D'),('SI22-A','Sistem Informasi 2022 A'),('SI22-B','Sistem Informasi 2022 B'),('SI22-C','Sistem Informasi 2022 C'),('MA22','Matematika 2022'),('IK23-A','Ilmu Komputer 2023 A'),('IK23-B','Ilmu Komputer 2023 B'),('IK23-C','Ilmu Komputer 2023 C'),('IK23-D','Ilmu Komputer 2023 D'),('IK23-E','Ilmu Komputer 2023 E'),('IK23-AB','Ilmu Komputer 2023 AB'),('IK23-CDE','Ilmu Komputer 2023 CDE'),('SI23-A','Sistem Informasi 2023 A'),('SI23-B','Sistem Informasi 2023 B'),('SI23-C','Sistem Informasi 2023 C'),('SI23-D','Sistem Informasi 2023 D'),('SI23-AB','Sistem Informasi 2023 AB'),('SI23-CD','Sistem Informasi 2023 CD'),('TP23','Teknologi Pangan 2023'),('TM23','Teknik Metalurgi 2023'),('TE23','Teknik Sistem Energi 2023'),('MA23','Matematika 2023'),('SD23','Sains Data 2023'),('GP23-A','Gabungan Kelas TP23, TM23 dan TE23'),('GP23-B','Gabungan Kelas MA23 dan SD23'),('GP23','Gabungan Kelas TP23, TM23, TE23, MA23 dan SD23')";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }
    
    void insertMatkul() throws SQLException {
        String query = "INSERT INTO mata_kuliah(kode_matkul, nama_matkul, sks) VALUES ('ISLAM','Agama Islam'),('KRISTEN','Agama Kristen'),('PANCASILA','Pancasila'),('BAHASA','Bahasa Indonesia'),('KALDAS 1','Kalkulus Dasar I'),('PROG','Pengantar Pemrograman'),('PTI','Pengantar Teknologi Informasi'),('DIGITAL','Pengantar Sistem Digital'),('SAINSTER','Sains Terpadu'),('CINTA','Wawasan Cinta IPTEK dan IMTAQ'),('IMK','Interaksi Manusia Komputer'),('ALIN','Aljabar Linear'),('TECHNO','Technopreneurship'),('PBO','Pemrograman Berbasis Objek'),('SBD','Sistem Basis Data'),('SO','Sistem Operasi'),('JARKOM','Jaringan Komputer'),('RTI','Riset Teknologi Informasi'),('APS','Analisis dan Perancangan Sistem'),('SBD2','Sistem Basis Data II'),('DGTX','Transformasi Digital'),('SCM','Supply Chain Management'),('MATDIS','Matematika Diskrit'),('KALJUT','Kalkulus Lanjut'),('PDB','Persamaan Diferensial Biasa'),('ASBWEB','Analisis Suku Bunga Berbasis Web'),('MABIZTEK','Matematika Bisnis dan Teknologi')";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);    
    }

    void insertRuangan() throws SQLException {
        String query = "INSERT INTO ruangan (kode_ruangan, nama_ruangan, kapasitas) VALUES ('101', 'MACCA', '40'), ('102', 'MAKKAWARU', '50'), ('103', 'MARESO', '50'), ('104', 'MAGETTENG', '50'), ('202', 'MALEBBI', '50'), ('203', 'MATEPPE', '50'), ('205', 'MATOTO', '50'), ('206', 'MAMASE', '50')";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    void insertJurusan() throws SQLException {
        String query = "INSERT INTO jurusan (id_jurusan, jurusan) VALUES ('1', 'Teknologi Produksi dan Industri'), ('2', 'Sains');";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }
    
    void insertProdi() throws SQLException {
        String query = "INSERT INTO prodi (id_prodi, prodi) VALUES ('1', 'Ilmu Komputer'), ('2', 'Teknologi Pangan'), ('3', 'Teknik Sistem Energi'), ('4', 'Teknik Metalurgi'), ('5', 'Sistem Informasi'), ('6', 'Matematika'), ('7', 'Sains Data'), ('8', 'Sains Aktuaria');";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        stmt.execute(query);
    }

    Admin() throws SQLException{
        String query = "SELECT * FROM admin";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);
        if(!res.next()){
            createTableUser();
            createTableAdmin();
            insertDataAdmin();
        }
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
        String query = "CREATE TABLE IF NOT EXISTS mahasiswa (nim varchar(20) PRIMARY KEY, nama varchar(255) NOT NULL, singkatan_kelas varchar(255) NOT NULL, id_jurusan int(11) NOT NULL, id_prodi int(11) NOT NULL, email varchar(255) NOT NULL, akses bool DEFAULT false, FOREIGN KEY (email) REFERENCES user(email), FOREIGN KEY (id_jurusan) REFERENCES jurusan(id_jurusan), FOREIGN KEY (singkatan_kelas) REFERENCES kelas(singkatan_kelas) ,FOREIGN KEY (id_prodi) REFERENCES prodi(id_prodi))";
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
        String nama, userEmail, kelas, query, nim, password;
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
        // scn.close();
        if(result > 0){
            query = "INSERT INTO mahasiswa (nim, nama, singkatan_kelas, id_jurusan, id_prodi, email) VALUE (?, ?, ?, ?, ?, ?)";
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
        String nama, userEmail, query, password, nidn;
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
        // scn.close();
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

    public void editNamaMahasiswa() throws SQLException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Masukkan nim: ");
        String nim = scn.nextLine();
        System.out.println("Masukkan nama baru: ");
        String nama = scn.nextLine();
        String query = "UPDATE mahasiswa SET nama = ? WHERE nim = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, nama);
        stmt.setString(2, nim);
        int res = stmt.executeUpdate();
        if(res>0){
            System.out.println("Nama berhasil di update! ");
        } else {
            System.out.println("Nama gagal di update!");
        }
        // scn.close();
    }

    public void editNamaDosen() throws SQLException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Masukkan nidn: ");
        String nidn = scn.nextLine();
        System.out.println("Masukkan nama baru: ");
        String nama = scn.nextLine();
        String query = "UPDATE dosen SET nama = ? WHERE nidn = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, nama);
        stmt.setString(2, nidn);
        int res = stmt.executeUpdate();
        if(res>0){
            System.out.println("Nama berhasil di update! ");
        } else {
            System.out.println("Nama gagal di update!");
        }
        // scn.close();
    }

    public void editKelasMahasiswa() throws SQLException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Masukkan nim: ");
        String nim = scn.nextLine();
        System.out.println("Masukkan kelas baru: ");
        String kelas = scn.nextLine();
        String query = "UPDATE mahasiswa SET singkatan_kelas = ? WHERE nim = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, kelas);
        stmt.setString(2, nim);
        int res = stmt.executeUpdate();
        if(res>0){
            System.out.println("Kelas berhasil di update! ");
        } else {
            System.out.println("Kelas gagal di update!");
        }
        // scn.close();
    }

    public void editPassMahasiswa() throws SQLException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Masukkan nim: ");
        String nim = scn.nextLine();
        System.out.println("Masukkan password baru: ");
        String pass = scn.nextLine();
        String query = "UPDATE user JOIN mahasiswa ON user.email = mahasiswa.email SET user.password = ? WHERE mahasiswa.nim = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, pass);
        stmt.setString(2, nim);
        int res = stmt.executeUpdate();
        if(res>0){
            System.out.println("Password berhasil di update! ");
        } else {
            System.out.println("Password gagal di update!");
        }
        // scn.close();
    }

    public void editPassDosen() throws SQLException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Masukkan nidn: ");
        String nidn = scn.nextLine();
        System.out.println("Masukkan password baru: ");
        String pass = scn.nextLine();
        String query = "UPDATE user JOIN dosen ON user.email = dosen.email SET user.password = ? WHERE dosen.nidn = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, pass);
        stmt.setString(2, nidn);
        int res = stmt.executeUpdate();
        if(res>0){
            System.out.println("Password berhasil di update! ");
        } else {
            System.out.println("Password gagal di update!");
        }
        // scn.close();
    }
    
    public void tampilkanDataMahasiswa() throws SQLException{
        System.out.println("\nDaftar Informasi Mahasiswa: ");
        String query = "SELECT m.*, p.prodi, j.jurusan FROM mahasiswa m JOIN prodi p ON m.id_prodi = p.id_prodi JOIN jurusan j ON m.id_jurusan = j.id_jurusan";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);
        while (res.next()) {
            System.out.println("Nama: " + res.getString("nama"));
            System.out.println("NIM: " + res.getString("nim"));
            System.out.println("Kelas: " + res.getString("singkatan_kelas"));
            System.out.println("Jurusan: " + res.getString("jurusan"));
            System.out.println("Prodi: " + res.getString("prodi"));
            System.out.println("Akses: " + res.getBoolean("akses"));
            System.out.println("--------------------------------");
        }
    }

    public void tampilkanDataDosen() throws SQLException{
        System.out.println("\nDaftar Informasi Dosen: ");
        String query = "SELECT d.*, p.prodi, j.jurusan FROM dosen d JOIN prodi p ON d.id_prodi = p.id_prodi JOIN jurusan j ON d.id_jurusan = j.id_jurusan";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);
        while (res.next()) {
            System.out.println("Nama: " + res.getString("nama"));
            System.out.println("NIDN: " + res.getString("nidn"));
            System.out.println("Jurusan: " + res.getString("jurusan"));
            System.out.println("Prodi: " + res.getString("prodi"));
            System.out.println("--------------------------------");
        }
    }

    public boolean berikanAkses() throws SQLException{
        Scanner scn = new Scanner(System.in);
        System.out.print("Masukkan nim: ");
        String nim = scn.nextLine();

        String query = "SELECT m.akses FROM mahasiswa m WHERE m.nim = ?"; 
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, nim);
        ResultSet akses = stmt.executeQuery();
        akses.next();
        if(akses.getBoolean("akses") == true){
            System.out.println("Mahasiswa sudah memiliki akses");
            return true;
        } 

        String query2 = "UPDATE mahasiswa SET akses = true WHERE nim = ?";
        PreparedStatement stmt2 = ConnectionDB.getConnection().prepareStatement(query2);
        stmt2.setString(1, nim);
        int res = stmt2.executeUpdate();
        if(res > 0){
            System.out.println("Berhasil memberikan akses!");
            return true;
        } else {
            System.out.println("Gagal memberikan akses!");
            return false;
        }
        // scn.close();
    }

}
