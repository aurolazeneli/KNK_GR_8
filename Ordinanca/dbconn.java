package sample;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class dbconn {
    public Connection connection;
    public Statement st;
    public ResultSet rs;
    public dbconn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String driver = "com.mysql.jdbc.Driver";
            String url    = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "";            // Change it to your Password
            System.setProperty(driver,"");

            connection= DriverManager.getConnection(url,username,password);


        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    public void insert(String iname,String igrupi, String iresult) {
        try {

            st = (Statement) connection.createStatement();
            String query1 = "INSERT INTO test(Name, Gjaku,Result) " + "VALUES ("+"'"+iname+"',"+" '"+igrupi+"',"+" '"+iresult+"')";
            st.executeUpdate(query1);


        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
    public void  delete(String dname, String dgrupi, String dresult) throws SQLException {
        try {
            st = (Statement) connection.createStatement();
            String query = "delete from test where Name = '" + dname + "'&&  Gjaku='"+dgrupi+"'&& Result='"+dresult+"'";
            st.executeUpdate(query);
        }
        catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
    public void  update(String uresult, String uname, String ugrupi) throws SQLException {
        try {
            st = (Statement) connection.createStatement();
            String query = "UPDATE test SET Name='' where Name = '" + uname + "'&& Result ='"+uresult+"'&&  Gjaku='"+ugrupi+"'";
            st.executeUpdate(query);
        }
        catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
}
