package login;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class mysqlconnect {
   private static Connection conn = null;
    public static Connection ConnectDb(){
 
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			 String driver = "com.mysql.jdbc.Driver";
			    String url    = "jdbc:mysql://localhost:3306/knkdb?autoReconnect=true&useSSL=false";
			    String username = "root";
			    String password = "";            // Change it to your Password
			    System.setProperty(driver,"");
			    conn= DriverManager.getConnection(url,username,password);
         
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.getMessage();
            return null;
        } 
    }

}