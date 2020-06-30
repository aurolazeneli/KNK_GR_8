package login;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;


public class FXMLDocumentController implements Initializable {
    
	 @FXML
	    private Button login_btn;

	    @FXML
	    private Button signup_btn;

	    @FXML
	    private AnchorPane pane_login;

	    @FXML
	    private TextField txt_username;

	    @FXML
	    private PasswordField txt_password;

	    @FXML
	    private Button btn_login;

	    @FXML
	    private AnchorPane pane_signup;

	    @FXML
	    private TextField txt_username_up;

	    @FXML
	    private TextField txt_password_up;

	    @FXML
	    private TextField email_up;

	    @FXML
	    private Button signup;
	    
	    private ResourceBundle bundle;
	    private Locale locale;
	    

    Connection conn = null;
    ResultSet rs = null;
    Statement st = null;
    
    public void LoginpaneShow(){
    
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }
    
    public void SignuppaneShow(){
    
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }
    
    
    @FXML  
    private void Login (ActionEvent event) throws Exception{  
    	
    	
    conn = mysqlconnect.ConnectDb();
    
        try {
        	 String pass2=  login(txt_username.getText());
   		
            
            
            if(checkPassword(txt_password.getText(),pass2)) {
    
                JOptionPane.showMessageDialog(null, "Username And Password is Corect");
                
                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("CPanel.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
            else {
            	 JOptionPane.showMessageDialog(null, "Username And Password is UnCorect");
			}
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
   
    }
    
    
    public void add_users(ActionEvent event){  
    	final Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com");
        if (txt_username_up.getText().isEmpty()  )  {
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                "Please enter your username");
            return;
        }
    	    if (email_up.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please enter your email ");
                return;
            }
    	    if (txt_password_up.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please enter a password");
                return;
            }
    	    String email = email_up.getText();
    	    if (!emailPattern.matcher(email).matches()) {
                showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Your email is invalid ");
                return;
            }
          
        conn = mysqlconnect.ConnectDb();
        
    
     
        try {
        	st = (Statement) conn.createStatement();
        	   String sql = "INSERT INTO users(username,password,email) " + "VALUES ("+"'"+txt_username_up.getText()+"',"+" '"+hashPassword(txt_password_up.getText())+"',"+" '"+email_up.getText()+"')";
        	   st.executeUpdate(sql);
           

        
            showAlert(Alert.AlertType.CONFIRMATION, "Registration Successful!",
                    "You have an account " + txt_username_up.getText());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
        
    }
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        
        alert.show();
    }
	public static  String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(15);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return hashed_password;
	}
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}
public String  login(String name) throws SQLException {
		
	st = (Statement) conn.createStatement();
	String sql = "select * from users where username = '" + txt_username.getText() + "'";
	rs = st.executeQuery(sql);
	 String pass2="";
 
     while (rs.next())
     {
        pass2 = rs.getString("password");

     }
	      return pass2;
	      
	}
 

@FXML
private void btnEN(ActionEvent event) {
	loadLang("en");
}
@FXML
private void btnAL(ActionEvent event) {
	loadLang("al");
}


@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}

public void loadLang(String lang) {
	// TODO Auto-generated method stub
	locale = new Locale(lang);
	bundle = ResourceBundle.getBundle("login.lang",locale);

	login_btn.setText(bundle.getString("login_btn1"));
	signup_btn.setText(bundle.getString("signup_btn1"));
	txt_username.setPromptText(bundle.getString("txt_username1"));
	txt_password.setPromptText(bundle.getString("txt_password1"));
	txt_username_up.setPromptText(bundle.getString("txt_username_up1"));
	email_up.setPromptText(bundle.getString("email_up1"));
	txt_password_up.setPromptText(bundle.getString("txt_password_up1"));


	btn_login.setText(bundle.getString("btn_login1"));
	signup.setText(bundle.getString("signup1"));
}

    
      
    
}