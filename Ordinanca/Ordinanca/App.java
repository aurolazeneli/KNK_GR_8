package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application  {
	 public void start(Stage primaryStage) throws Exception {

		 Parent parent=FXMLLoader.load(getClass().getResource("login.fxml"));
		 
		 Scene scene=new Scene(parent);

		 primaryStage.setScene(scene);
		 primaryStage.show();
		  
	 }
	 public static void main(String[] args) {
		Application.launch(args);
	}

}
