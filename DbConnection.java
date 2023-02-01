import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection con;
    public static synchronized Connection getConnection(){
        String DB_URL = "jdbc:mysql://localhost:3306/registerapplication";
        String USERNAME = "root";
        String PASSWORD = "12ra345";
        try{
            con = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
