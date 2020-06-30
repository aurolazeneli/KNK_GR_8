package ordinance;

import java.io.IOException;
import java.sql.*;
import java.util.Properties; 


import javafx.*;
public class dbconn1 {
public Connection connection;
public Statement st;
public ResultSet rs;
public dbconn1(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		 String driver = "com.mysql.jdbc.Driver";
		    String url    = "jdbc:mysql://localhost:3306/knk";
		    String username = "root";
		    String password = "";            // Change it to your Password
		    System.setProperty(driver,"");
 
		    connection= DriverManager.getConnection(url,username,password);
	
		
	} catch (Exception e) {
		System.out.println("Error31:" + e);
	}
}
	
public void insert(String docname, String date) {
	try {
		
		 st = (Statement) connection.createStatement();
		 String query1 = "INSERT INTO doctors(doctorName,date) " + "VALUES ("+" '"+docname+"',"+"'"+date+"')";
	     st.executeUpdate(query1);
	     
		      
	} catch (Exception e) {
		System.out.println("Error34:" + e);
	}
}
public void  delete(String date, String doctorName) throws SQLException {
	try {
	st = (Statement) connection.createStatement();
	String query = "delete from doctors where  doctorName='"+doctorName+"'&& date='"+date+"'";
	st.executeUpdate(query);
	}
	catch (Exception e) {
		System.out.println("Error33:" + e);		
		}
}
public void  update(String date, String doctorName) throws SQLException {
	try {
	st = (Statement) connection.createStatement();
	String query = "UPDATE doctors SET doctorName='hasan' where doctorName='"+doctorName+"'&& date='"+date+"'";
	st.executeUpdate(query);
	}
	catch (Exception e) {
		System.out.println("Error32:" + e);		
		}
}
}

