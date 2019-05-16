import java.sql.*;

public class ConnectionManager {
    private static String url = "jdbc:mysql://localhost/rev";    
    private static String username = "root";   
    private static String password = "Ahr1man32";
    private static Connection con;
 

    public static Connection getConnection() {
      
           try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                
                System.out.println("Failed to create the database connection."); 
            }
        
        return con;
    }
}