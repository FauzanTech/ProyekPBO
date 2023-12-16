import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB instance;
    private Connection con;

    private ConnectionDB() {
        String dburl = "jdbc:mysql://localhost:3306/ithcm";
        String dbuser = "root"; 
        String dbpass = ""; 

        try {
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
        } catch (SQLException e) {
            throw new IllegalStateException("DB Errors: ", e); 
        }
    }

    public static Connection getConnection() {
        if(instance == null){
            instance = new ConnectionDB();
        }
        return instance.con;
    }
}
