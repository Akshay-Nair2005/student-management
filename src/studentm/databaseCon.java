package studentm;
import java.sql.DriverManager;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class databaseCon {
    final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://localhost:3306/ganesha";
    final static String USER = "root";
    final static String PASS = "root";
    
    public static Connection connection(){
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("connected");
            return conn;
        } catch(Exception e){
            e.printStackTrace();  // Print the exception stack trace
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    } 
}
