import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try{ 
            Class.forName("org.postgresql.Driver"); 
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        }catch(ClassNotFoundException e){
            System.out.println("Driver not found.");
            e.printStackTrace();
            
        }catch(SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }

        return null;
    }
}
