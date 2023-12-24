import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class Roster {
    static String[] arrWaktu = {"07.30-09.10", "09.20-11.00", "11.10-12.00", "13.30-15.10", "15.20-17.00", "17.10-18.00"};
    static String[] matkulKelas = {"","","","","",""};
    static String[] dosenRoster = {"","","","","",""};

    public static void tampilkanRuangan() throws SQLException {
        String query = "SELECT * FROM ruangan";
        Statement stmt = ConnectionDB.getConnection().createStatement();
        ResultSet res = stmt.executeQuery(query);

        while (res.next()) {
            String kode, nama;
            int kapasitas;

            kode = res.getString("kode_ruangan");
            nama = res.getString("nama_ruangan");
            kapasitas = res.getInt("kapasitas");

            System.out.println("Kode ruangan: " + kode);
            System.out.println("Nama ruangan: " + nama);
            System.out.println("Kapasitas ruangan: " + kapasitas);
            System.out.println("__________________________");
        }
    } 
    
    static void tampilkanRoster() throws SQLException{

        String query = "SELECT r.*, k.*, rn.*, mk.*, d.* FROM roster r JOIN kelas k ON r.singkatan_kelas=k.singkatan_kelas JOIN ruangan rn ON r.kode_ruangan=rn.kode_ruangan JOIN mata_kuliah mk ON r.kode_matkul=mk.kode_matkul JOIN dosen d ON r.nidn=d.nidn";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        ResultSet res = stmt.executeQuery();

        System.out.printf("\n%-95s\n", "\nRuang Kampus 2 Gedung Pemuda");
        System.out.println("Senin");
        System.out.printf("%-15s %-40s\n", "Waktu", "R. MACCA 101");

        while(res.next()){
            // int[] rentang_waktu = {0, 0, 0, 0};


            String kelas, matkul, nama_dosen, waktu;
            waktu = res.getString("waktu");
            kelas = res.getString("singkatan_kelas");
            matkul = res.getString("nama_matkul");
            nama_dosen = res.getString("nama");
            

            if(waktu.equals(arrWaktu[0])){
                // rentang_waktu[0] = 1;
                matkulKelas[0] = matkul +" (" + kelas + ")";
                dosenRoster[0] = nama_dosen;
            } else if (waktu.equals(arrWaktu[1])){
                // rentang_waktu[1] = 1;
                matkulKelas[1] = matkul +" (" + kelas + ")";
                dosenRoster[1] = nama_dosen;
            } else if (waktu.equals(arrWaktu[2])){
                // rentang_waktu[2] = 1;
                matkulKelas[2] = matkul +" (" + kelas + ")";
                dosenRoster[2] = nama_dosen;
            } else if (waktu.equals(arrWaktu[3])){
                // rentang_waktu[3] = 1;
                matkulKelas[3] = matkul +" (" + kelas + ")";
                dosenRoster[3] = nama_dosen;
            } else if (waktu.equals(arrWaktu[4])){
                // rentang_waktu[4] = 1;
                matkulKelas[4] = matkul +" (" + kelas + ")";
                dosenRoster[4] = nama_dosen;
            } else if (waktu.equals(arrWaktu[5])) {
                matkulKelas[5] = matkul +" (" + kelas + ")";
                dosenRoster[5] = nama_dosen;
            }
            
            // System.out.printf("%-15s %-40s %-40s\n", "07.30-09.10", matkul +" (" + kelas + ")", matkul +" (" + kelas + ")");
        }
        System.out.printf("%-15s %-40s\n", "07.30-09.10", matkulKelas[0]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[0]);
        System.out.printf("%-15s %-40s\n", "09.20-11.00", matkulKelas[1]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[1]);
        System.out.printf("%-15s %-40s\n", "11.10-12.00", matkulKelas[2]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[2]);
        System.out.printf("%-15s %-40s\n", "13.30-15.10", matkulKelas[3]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[3]);
        System.out.printf("%-15s %-40s\n", "15.20-17.00", matkulKelas[4]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[4]);
        System.out.printf("%-15s %-40s\n", "17.10-18.00", matkulKelas[5]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[5]);

    }

    static void memesanRuangan() throws SQLException{
        Scanner scn = new Scanner(System.in);
        Roster.tampilkanRoster();
        
        System.out.print("Masukkan jadwal: ");
        String jadwal = scn.nextLine();

        if(jadwal.equals(arrWaktu[0]) && matkulKelas[0].equals("")) {
            String hari, kelas, matkul, ruangan, nidn;

            System.out.print("Masukkan hari: ");
            hari = scn.nextLine();
            System.out.print("Masukkan kelas: ");
            kelas = scn.nextLine();
            System.out.print("Masukkan matkul: ");
            matkul = scn.nextLine();
            System.out.print("Masukkan ruangan: ");
            ruangan = scn.nextLine();
            System.out.print("Masukkan nidn: ");
            nidn = scn.nextLine();

            String query = "INSERT INTO roster (hari, singkatan_kelas, kode_matkul, kode_ruangan, waktu, nidn) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, hari);
            stmt.setString(2, kelas);
            stmt.setString(3, matkul);
            stmt.setString(4, ruangan);
            stmt.setString(5, jadwal);
            stmt.setString(6, nidn);
            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Berhasil memesan ruangan! ");
            } else {
                System.out.println("Gagal memesan!");
            }
        } else if (jadwal.equals(arrWaktu[1]) && matkulKelas[1].equals("")) {
            String hari, kelas, matkul, ruangan, nidn;

            System.out.print("Masukkan hari: ");
            hari = scn.nextLine();
            System.out.print("Masukkan kelas: ");
            kelas = scn.nextLine();
            System.out.print("Masukkan matkul: ");
            matkul = scn.nextLine();
            System.out.print("Masukkan ruangan: ");
            ruangan = scn.nextLine();
            System.out.print("Masukkan nidn: ");
            nidn = scn.nextLine();

            String query = "INSERT INTO roster (hari, singkatan_kelas, kode_matkul, kode_ruangan, waktu, nidn) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, hari);
            stmt.setString(2, kelas);
            stmt.setString(3, matkul);
            stmt.setString(4, ruangan);
            stmt.setString(5, jadwal);
            stmt.setString(6, nidn);
            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Berhasil memesan ruangan! ");
            } else {
                System.out.println("Gagal memesan!");
            }
            
        } else if (jadwal.equals(arrWaktu[2]) && matkulKelas[2].equals("")){
            String hari, kelas, matkul, ruangan, nidn;

            System.out.print("Masukkan hari: ");
            hari = scn.nextLine();
            System.out.print("Masukkan kelas: ");
            kelas = scn.nextLine();
            System.out.print("Masukkan matkul: ");
            matkul = scn.nextLine();
            System.out.print("Masukkan ruangan: ");
            ruangan = scn.nextLine();
            System.out.print("Masukkan nidn: ");
            nidn = scn.nextLine();

            String query = "INSERT INTO roster (hari, singkatan_kelas, kode_matkul, kode_ruangan, waktu, nidn) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, hari);
            stmt.setString(2, kelas);
            stmt.setString(3, matkul);
            stmt.setString(4, ruangan);
            stmt.setString(5, jadwal);
            stmt.setString(6, nidn);
            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Berhasil memesan ruangan! ");
            } else {
                System.out.println("Gagal memesan!");
            }

        } else if (jadwal.equals(arrWaktu[3]) && matkulKelas[3].equals("")){
            String hari, kelas, matkul, ruangan, nidn;

            System.out.print("Masukkan hari: ");
            hari = scn.nextLine();
            System.out.print("Masukkan kelas: ");
            kelas = scn.nextLine();
            System.out.print("Masukkan matkul: ");
            matkul = scn.nextLine();
            System.out.print("Masukkan ruangan: ");
            ruangan = scn.nextLine();
            System.out.print("Masukkan nidn: ");
            nidn = scn.nextLine();

            String query = "INSERT INTO roster (hari, singkatan_kelas, kode_matkul, kode_ruangan, waktu, nidn) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, hari);
            stmt.setString(2, kelas);
            stmt.setString(3, matkul);
            stmt.setString(4, ruangan);
            stmt.setString(5, jadwal);
            stmt.setString(6, nidn);
            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Berhasil memesan ruangan! ");
            } else {
                System.out.println("Gagal memesan!");
            }

        } else if (jadwal.equals(arrWaktu[4]) && matkulKelas[4].equals("")){
            String hari, kelas, matkul, ruangan, nidn;

            System.out.print("Masukkan hari: ");
            hari = scn.nextLine();
            System.out.print("Masukkan kelas: ");
            kelas = scn.nextLine();
            System.out.print("Masukkan matkul: ");
            matkul = scn.nextLine();
            System.out.print("Masukkan ruangan: ");
            ruangan = scn.nextLine();
            System.out.print("Masukkan nidn: ");
            nidn = scn.nextLine();

            String query = "INSERT INTO roster (hari, singkatan_kelas, kode_matkul, kode_ruangan, waktu, nidn) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, hari);
            stmt.setString(2, kelas);
            stmt.setString(3, matkul);
            stmt.setString(4, ruangan);
            stmt.setString(5, jadwal);
            stmt.setString(6, nidn);
            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Berhasil memesan ruangan! ");
            } else {
                System.out.println("Gagal memesan!");
            }

        } else if (jadwal.equals(arrWaktu[5]) && matkulKelas[5].equals("")){
            String hari, kelas, matkul, ruangan, nidn;

            System.out.print("Masukkan hari: ");
            hari = scn.nextLine();
            System.out.print("Masukkan kelas: ");
            kelas = scn.nextLine();
            System.out.print("Masukkan matkul: ");
            matkul = scn.nextLine();
            System.out.print("Masukkan ruangan: ");
            ruangan = scn.nextLine();
            System.out.print("Masukkan nidn: ");
            nidn = scn.nextLine();

            String query = "INSERT INTO roster (hari, singkatan_kelas, kode_matkul, kode_ruangan, waktu, nidn) VALUE (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
            stmt.setString(1, hari);
            stmt.setString(2, kelas);
            stmt.setString(3, matkul);
            stmt.setString(4, ruangan);
            stmt.setString(5, jadwal);
            stmt.setString(6, nidn);
            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Berhasil memesan ruangan! ");
            } else {
                System.out.println("Gagal memesan!");
            }

        }
        else {
            System.out.println("Jadwal terisi!");
        }
    }

    static void tambahkanRuangan() throws SQLException{
        Scanner scn = new Scanner(System.in);
        String kode, nama;
        System.out.print("Masukkan kode ruangan baru: ");
        kode = scn.nextLine();
        System.out.print("Masukkan nama ruangan baru: ");
        nama = scn.nextLine();
        System.out.print("Masukkan kapasitas ruangan: ");
        int kapasitas = scn.nextInt();
        String query = "INSERT INTO ruangan (kode_ruangan, nama_ruangan, kapasitas) VALUE (?, ?, ?)";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, kode);
        stmt.setString(2, nama);
        stmt.setInt(3, kapasitas);

        int res = stmt.executeUpdate();
        if (res > 0) {
            System.out.println("Berhasil menambahkan ruangan!");
        } else {
            System.out.println("Berhasil menambahkan ruangan!");
        }
    }

    static void deleteJadwal() throws SQLException{
        Scanner cn = new Scanner(System.in);
        String jadwal, query;
        System.out.print("Masukkan jadwal yang ingin dihapus: ");
        jadwal = cn.nextLine();

        query = "DELETE FROM roster WHERE waktu = ?";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        stmt.setString(1, jadwal);
        int res = stmt.executeUpdate();
        if(res > 0){
            for(int i = 0; i < 6; i++){
                if(arrWaktu[i].equals(jadwal)){
                    matkulKelas[i] = "";
                    dosenRoster[i] = "";
                }
            }
            System.out.println("Penghapusan jadwal berhasil!");
        } else {
            System.out.println("Penghapusan jadwal gagal!");
        }

    }

    public static void main(String[] args) throws SQLException {
        tampilkanRoster();
        memesanRuangan();
        tampilkanRoster();
    }
}
