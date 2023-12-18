import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Roster {

    void selectDataRoster() throws SQLException{
    }
    
    static void tampilkanRoster() throws SQLException{
        String[] arrWaktu = {"07.30-09.10", "09.20-11.00", "11.10-12.00", "13.30-15.10", "15.20-17.00", "17.10-18.00"};
        String[] matkulKelas = {"","","","","",""};
        String[] dosenRoster = {"","","","","",""};

        String query = "SELECT r.*, k.*, rn.*, mk.*, d.* FROM roster r JOIN kelas k ON r.singkatan_kelas=k.singkatan_kelas JOIN ruangan rn ON r.kode_ruangan=rn.kode_ruangan JOIN mata_kuliah mk ON r.kode_matkul=mk.kode_matkul JOIN dosen d ON r.nidn=d.nidn";
        PreparedStatement stmt = ConnectionDB.getConnection().prepareStatement(query);
        ResultSet res = stmt.executeQuery();

        System.out.printf("%-95s\n", "Ruang Kampus 2 Gedung Pemuda");
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
            }
            
            // System.out.printf("%-15s %-40s %-40s\n", "07.30-09.10", matkul +" (" + kelas + ")", matkul +" (" + kelas + ")");
        }
        System.out.printf("%-15s %-40s\n", "07.30-09.10", matkulKelas[0]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[0]);
        System.out.printf("%-15s %-40s\n", "11.10-12.00", matkulKelas[1]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[1]);
        System.out.printf("%-15s %-40s\n", "13.30-15.10", matkulKelas[2]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[2]);
        System.out.printf("%-15s %-40s\n", "15.20-17.00", matkulKelas[3]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[3]);
        System.out.printf("%-15s %-40s\n", "17.10-18.00", matkulKelas[4]);
        System.out.printf("%-15s %-40s\n", " ", dosenRoster[4]);

    }
    public static void main(String[] args) throws SQLException {
        tampilkanRoster();
    }
}
