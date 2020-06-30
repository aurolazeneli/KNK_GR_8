package app;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController implements Initializable {
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;
	
	private UserRepository repository;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		repository =new UserRepository();
	}
	@FXML
	private void onLoginButtonClick(ActionEvent event) throws Exception {
		String username=usernameField.getText();
		String password=passwordField.getText();
		
		boolean loginRes=login(username,password);
		if(loginRes) {
		Parent parent=FXMLLoader.load(getClass().getResource("admin.fxml"));
		Scene scene=new Scene(parent);
		
		Stage primaryStage= (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
		}
		else {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setContentText("Invalid credentials");
			alert.showAndWait();
		}
		
	}
	private boolean login(String username,String pasword) {
		User user = repository.find(username,pasword);
		return user !=null; 
	}
	

}
