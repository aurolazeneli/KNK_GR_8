package ordinance;

import java.io.IOException;
import java.sql.*;
import java.util.Properties; 


import javafx.*;
public class dbconn {
public Connection connection;
public Statement st;
public ResultSet rs;
public dbconn(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		 String driver = "com.mysql.jdbc.Driver";
		    String url    = "jdbc:mysql://localhost:3307/knk?autoReconnect=true&useSSL=false";
		    String username = "root";
		    String password = "";            // Change it to your Password
		    System.setProperty(driver,"");
 
		    connection= DriverManager.getConnection(url,username,password);
	
		
	} catch (Exception e) {
		System.out.println("Error:" + e);
	}
}
	
public void insert(String name,String docname, String age, String date, String type, String result) {
	try {
		
		 st = (Statement) connection.createStatement();
		 String query1 = "INSERT INTO patients(name,doctorName,age,date,type,result) " + "VALUES ("+"'"+name+"',"+" '"+docname+"',"+" '"+age+"',"+"'"+date+"',"+" '"+type+"',"+"'"+result+"')";
	     st.executeUpdate(query1);
	     
		      
	} catch (Exception e) {
		System.out.println("Error:" + e);
	}
}
public void insertt(String name,String docname, String age, String date, String type, String result) {
	try {
		
		 st = (Statement) connection.createStatement();
		 String query1 = "INSERT INTO doctors(name,surname,prof,office,tel,email) " + "VALUES ("+"'"+name+"',"+" '"+docname+"',"+" '"+type+"',"+"'"+date+"',"+" '"+age+"',"+"'"+result+"')";
	     st.executeUpdate(query1);
	     
		      
	} catch (Exception e) {
		System.out.println("Error:" + e);
	}
}
public void  delete(String date, String doctorName, String pname) throws SQLException {
	try {
	st = (Statement) connection.createStatement();
	String query = "delete from patients where name = '" + pname + "' AND doctorName='"+doctorName+"' AND date='"+date+"'";
	st.executeUpdate(query);
	}
	catch (Exception e) {
		System.out.println("Error:" + e);		
		}
}
public void insertmed(String imedicament,String iindication, String iquantity, String ifrom) {
	try {
		
		 st = (Statement) connection.createStatement();
		 String query1 = "INSERT INTO medicament(Medicament,Indication,Quantity,Origin) " + "VALUES ("+"'"+imedicament+"',"+" '"+iindication+"',"+" '"+iquantity+"',"+"'"+ifrom+"')";
	     st.executeUpdate(query1);
	     
		      
	} catch (Exception e) {
		System.out.println("Error:" + e);
	}
}
   
public void insertshift(String docname, String date) {
	try {
		
		 st = (Statement) connection.createStatement();
		 String query1 = "INSERT INTO doctorshift(doctorName,date) " + "VALUES ("+" '"+docname+"',"+"'"+date+"')";
	     st.executeUpdate(query1);
	     
		      
	} catch (Exception e) {
		System.out.println("Error34:" + e);
	}
}


public void  deleteshift(String date, String doctorName) throws SQLException {
	try {
	st = (Statement) connection.createStatement();
	String query = "delete from doctorshift where  doctorName='"+doctorName+"'AND date='"+date+"'";
	st.executeUpdate(query);
	}
	catch (Exception e) {
		System.out.println("Error33:" + e);		
		}
}
public void  deletemed(String dquantity, String dmedicament, String dindication) throws SQLException {
	try {
	st = (Statement) connection.createStatement();
	String query = "delete from medicament where Medicament = '" + dmedicament + "'&& Quantity='"+dquantity+"'&& Indication='"+dindication+"'";
	st.executeUpdate(query);
	}
	catch (Exception e) {
		System.out.println("Error:" + e);		
		}
}
public void  deletee(String date, String doctorName, String pname) throws SQLException {
	try {
	st = (Statement) connection.createStatement();
	String query = "delete from doctors where name = '" + pname + "' AND surname='"+doctorName+"' AND office='"+date+"'";
	st.executeUpdate(query);
	}
	catch (Exception e) {
		System.out.println("Error:" + e);		
		}
}
public void  update(String date, String doctorName, String pname) throws SQLException {
	try {
	st = (Statement) connection.createStatement();
	String query = "UPDATE patients SET name='hasan' where name = '" + pname + "' AND doctorName='"+doctorName+"' AND date='"+date+"'";
	st.executeUpdate(query);
	}
	catch (Exception e) {
		System.out.println("Error:" + e);		
		}
}
}























