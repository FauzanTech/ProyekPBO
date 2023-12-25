import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ITHCM{
    private String email, password, status;
    String nama, no_induk, jurusan, prodi;
    
    public boolean login() throws SQLException{
        var scn = new Scanner(System.in);
        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var now = LocalDateTime.now();
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
                
                return true;
            }
            else {
                System.out.println("Akun tidak ditemukan !");
                
                return false;
            }
        } else if (status.toLowerCase().equals("mahasiswa") || status.toLowerCase().equals("mhs")){
            String query = "SELECT * FROM mahasiswa m JOIN user u ON m.email = u.email JOIN jurusan j ON m.id_jurusan=j.id_jurusan JOIN prodi p ON m.id_prodi=p.id_prodi WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
    
            if(result.next()){
                nama = result.getString("nama");
                no_induk = result.getString("nim");
                jurusan = result.getString("jurusan");
                prodi = result.getString("prodi");
                String kalimat = nama + "/" + no_induk + "/"  + prodi + "/"  + jurusan + " ---> " + dtf.format(now);
                Aktivitas.writeFile(kalimat + " Action: Login");
                System.out.println("\n\nSelamat Datang, "+ nama);
                System.out.println("NIM: " + no_induk);
                
                return true;
            }
            else {
                System.out.println("Akun tidak ditemukan !");
                
                return false;
            }
        } else if (status.toLowerCase().equals("dosen")){
            String query = "SELECT * FROM dosen d JOIN user u ON d.email = u.email JOIN jurusan j ON d.id_jurusan=j.id_jurusan JOIN prodi p ON d.id_prodi=p.id_prodi WHERE u.email = ? AND u.password = ?";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            
            if(result.next()){
                nama = result.getString("nama");
                no_induk = result.getString("nidn");
                jurusan = result.getString("jurusan");
                prodi = result.getString("prodi");
                String kalimat = nama + "/" + no_induk + "/"  + prodi + "/"  + jurusan + " ---> " + dtf.format(now);
                Aktivitas.writeFile(kalimat + " Action: Login");
                System.out.println("\n\nSelamat Datang, ");
                System.out.println("NIDN: ");
                
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
    
    void menuAdmin() {
        System.out.println("\nMenu admin: ");
        System.out.println("1. Tambahkan Akun mahasiswa");
        System.out.println("2. Tambahkan Akun Dosen");
        System.out.println("3. Edit Nama Akun Mahasiswa");
        System.out.println("4. Edit Kelas Akun Mahasiswa");
        System.out.println("5. Edit Password Akun Mahasiswa");
        System.out.println("6. Edit Nama Akun Dosen");
        System.out.println("7. Edit Password Akun Dosen");
        System.out.println("8. Menambahkan Ruangan");
        System.out.println("9. Tampilkan data mahasiswa");
        System.out.println("10. Tampilkan data dosen");
        System.out.println("11. Berikan akses ke mahasiswa");
        System.out.println("12. Hapus jadwal");
        System.out.println("13. Tampilkan roster");
        System.out.println("14. Memesan ruangan");
        System.out.println("15. Quit");
        System.out.println("___________________________________");
        System.out.print("Pilih menu: ");
    }
    
    void actionAdmin() throws SQLException {
        var adm = new Admin();
        Scanner scn = new Scanner(System.in);
        
        while (true) {
            
            menuAdmin();
            String menu = scn.nextLine();
            
            if(menu.equals("1")){
                adm.tambahkanAkunMahasiswa();
            } else if (menu.equals("2")){
                adm.tambahkanAkunDosen();
            } else if (menu.equals("3")){
                adm.editNamaMahasiswa();
            } else if (menu.equals("4")){
                adm.editKelasMahasiswa();
            } else if (menu.equals("5")){
                adm.editPassMahasiswa();
            } else if (menu.equals("6")){
                adm.editNamaDosen();
            } else if (menu.equals("7")){
                adm.editPassDosen();
            } else if (menu.equals("8")){
                Roster.tambahkanRuangan();
            } else if (menu.equals("9")){
                adm.tampilkanDataMahasiswa();
            } else if (menu.equals("11")) {
                adm.berikanAkses();
            } else if (menu.equals("12")) {
                Roster.tampilkanRoster();
                Roster.deleteJadwal();
            } else if (menu.equals("13")) {
                Roster.tampilkanRoster();
            } else if (menu.equals("14")) {
                Roster.memesanRuangan();
            } else if (menu.equals("15")) {
                break;
            } else if (menu.equals("10")) {
                adm.tampilkanDataDosen();
            } else {
                System.out.println("Masukkan nomor menu yang sesuai! ");
            }
            scn.nextLine();
        }
        
    }
    
    void menuMahasiswa1 () {
        System.out.println("\nMenu mahasiswa: ");
        System.out.println("1. Tampilkan ruangan");
        System.out.println("2. Tampilkan roster");
        System.out.println("3. Pesan ruangan");
        System.out.println("4. Quit");
        System.out.println("___________________________________");
        System.out.print("Pilih menu: ");
    }
    
    void menuMahasiswa2() {
        System.out.println("\nMenu mahasiswa: ");
        System.out.println("1. Tampilkan ruangan");
        System.out.println("2. Tampilkan roster");        
        System.out.println("3. Quit");
        System.out.println("___________________________________");
        System.out.print("Pilih menu: ");
    }
    
    void actionMahasiswa() throws SQLException {
        Scanner scn = new Scanner(System.in);
        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var now = LocalDateTime.now();
        
        String query = "SELECT m.* FROM user u JOIN mahasiswa m ON u.email=m.email WHERE u.email = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, email);
        ResultSet res = stmt.executeQuery();
        if(res.next()){
            Boolean akses = res.getBoolean("akses");
            String kalimat = nama + "/" + no_induk + "/"  + prodi + "/"  + jurusan + " ---> " + dtf.format(now);
            if(akses == true){
                
                while (true) {
                    menuMahasiswa1();
                    String menu = scn.nextLine();
    
                    if(menu.equals("1")) {
                        Roster.tampilkanRuangan();
                        Aktivitas.writeFile(kalimat + " Action: Menampilkan Ruangan");
                    } else if (menu.equals("2")) {
                        Roster.tampilkanRoster();
                        Aktivitas.writeFile(kalimat + " Action: Menampilkan Roster");
                    } else if (menu.equals("3")) {
                        Dosen.tampilkanNidn();
                        Roster.memesanRuangan();
                        Aktivitas.writeFile(kalimat + " Action: Memesan Ruangan");
                    } else if (menu.equals("4")){
                        break;
                    } else {
                        System.out.println("Masukkan nomor sesuai pada menu!");
                    }
                    scn.nextLine();
                }
            } else {
                while (true) {
                    menuMahasiswa2();
                    String menu = scn.nextLine();
                    
                    if(menu.equals("1")) {
                        Roster.tampilkanRuangan();
                        Aktivitas.writeFile(kalimat + " Action: Menampilkan Ruangan");
                    } else if (menu.equals("2")) {
                        Roster.tampilkanRoster();
                        Aktivitas.writeFile(kalimat + " Action: Menampilkan Roster");
                    } else if (menu.equals("3")){
                        break;
                    } else {
                        System.out.println("Masukkan nomor sesuai pada menu!");
                    }
                    scn.nextLine();
                }
            }
        }
        
    }
    
    void menuDosen () {
        System.out.println("\nMenu dosen: ");
        System.out.println("1. Tampilkan ruangan");
        System.out.println("2. Tampilkan roster");
        System.out.println("3. Pesan ruangan");
        System.out.println("4. Quit");
        System.out.println("___________________________________");
        System.out.print("Pilih menu: ");
    }
    
    void actionDosen() throws SQLException {
        Scanner scn = new Scanner(System.in);
        var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var now = LocalDateTime.now();
        String kalimat = nama + "/" + no_induk + "/"  + prodi + "/"  + jurusan + " ---> " + dtf.format(now);

        while (true) {
            menuDosen();
            String menu = scn.nextLine();
            
            if(menu.equals("1")) {
                Roster.tampilkanRuangan();
                Aktivitas.writeFile(kalimat + " Action: Menampilkan Ruangan" );
            } else if (menu.equals("2")) {
                Roster.tampilkanRoster();
                Aktivitas.writeFile(kalimat + " Action: Menampilkan Roster");
            } else if (menu.equals("3")) {
                Roster.memesanRuangan();
                Aktivitas.writeFile(kalimat + " Action: Memesan Ruangan");
            } else if (menu.equals("4")){
                break;
            } else {
                System.out.println("Masukkan nomor sesuai pada menu!");
            }
            scn.nextLine();
        }
        
    }

    public static void main(String[] args) throws SQLException {
        var obj = new ITHCM();
        Scanner scn = new Scanner(System.in);
        boolean kondisi = obj.login();
        Aktivitas.createFile();
        
        if(obj.status.equals("admin") && kondisi){
            obj.actionAdmin();
        } else if ((obj.status.equals("mahasiswa") || obj.status.equals("mhs")) && kondisi){
            obj.actionMahasiswa();
        } else if (obj.status.equals("dosen") && kondisi) {
            obj.actionDosen();
        }
        
    }
}
