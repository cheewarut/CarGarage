package Garage;
import java.sql.*;

public class Connect {

    public static void main(String[] args) {
        
    }
    public static Connection connectDB(){
        String db_name = "cargarage";
        String user = "root";
        String pass = "";
        String hostName = "localhost";
        String driverName = "com.mysql.jdbc.Driver";
        try{
            Class.forName(driverName);
            String url = "jdbc:mysql://"+hostName+"/"+db_name;
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
