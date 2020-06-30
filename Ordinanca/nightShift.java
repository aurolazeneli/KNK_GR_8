package ordinance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class nightShift extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader =new FXMLLoader(getClass().getResource("sample1.fxml"));
        AnchorPane pane= loader.load();
        Scene scene=new Scene(pane);
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Homepage");
        primaryStage.setScene(scene);
      
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
