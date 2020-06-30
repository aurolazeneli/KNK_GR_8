package ordinance;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.sun.prism.Image;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.IIOException;

public class homeController  implements Initializable{
	
	 @FXML
	    private BorderPane pane;

	 @FXML
	    private Menu file_h;

	    @FXML
	    private MenuItem logout_h;

	    @FXML
	    private MenuItem close_h;

	    @FXML
	    private Menu Statistics_h;

	    @FXML
	    private MenuItem medication_h;

	    @FXML
	    private MenuItem user_h;

	    @FXML
	    private Menu help_h;

	    @FXML
	    private MenuItem about_h;


	    @FXML
	    private VBox contentPane;

	   

	  
	 private ResourceBundle bundle;
	    private Locale locale;

	
    @FXML
   private AnchorPane pane1 ,pane2,pane3,pane4,opacityPane,drawerPane;
    @FXML
    private JFXButton appoin_h;
    @FXML
    private JFXButton doc_h;

    @FXML
    private JFXButton test_h;

    @FXML
    private JFXButton medic_h;

    @FXML
    private JFXButton nightsh_h;

    @FXML
    private JFXButton admin_h;

    
    /*    @FXML
    private void onEnCheckMenuItemClick(ActionEvent event) {
      try {
        AppConfig.get().setLanguage(LangEnum.EN);
        enCheckMenuItem.setSelected(true);
        alCheckMenuItem.setSelected(false);
        ResourceBundle langBundle = getLangBundle();
        loadLangTexts(langBundle);
        childController.loadLangTexts(langBundle);
      } catch (Exception e) {
        
      }
    }
    @FXML
    private void onAlCheckMenuItemClick(ActionEvent event) {
      try {
        AppConfig.get().setLanguage(LangEnum.AL);
        enCheckMenuItem.setSelected(false);
        alCheckMenuItem.setSelected(true);
        ResourceBundle langBundle = getLangBundle();
        loadLangTexts(langBundle);
        childController.loadLangTexts(langBundle);
      } catch (Exception e) {
        
      }
    }
    */
    @FXML
    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
        	Stage primaryStage = (Stage) pane.getScene().getWindow();
            primaryStage.close();}
        }
    @FXML
    private void onExitNavClick(ActionEvent event) {
      Stage primaryStage = (Stage) contentPane.getScene().getWindow();
      primaryStage.close();
    }
    
    @FXML
    private void onStatisticsClick(ActionEvent event) throws Exception {
     Stage stage = new Stage();
       statistics stat=new statistics();
       stat.start(stage);
    }
    @FXML
    private void onAboutClick(ActionEvent event) throws Exception {
    	Parent ho = FXMLLoader.load(getClass().getResource("about.fxml"));
    	Scene h1 = new Scene(ho);
    	Stage primaryStage =new Stage();
    	primaryStage.setScene(h1);
    	primaryStage.show();
    }
    @FXML
    private void onLogoutNavClick(ActionEvent event) {
      try {
        Parent root = FXMLLoader.load(getClass().getResource("sign.fxml"));
        Scene scene = new Scene(root);

        Stage primaryStage = null;
        Object source = event.getSource();
        if (source instanceof MenuItem) {
          primaryStage = (Stage) contentPane.getScene().getWindow();
        } else {
          primaryStage = (Stage) ((Node) source).getScene().getWindow();
        }

        primaryStage.setScene(scene);
        primaryStage.show();
       
      } catch (Exception e) {
       
      }
    }

    @FXML
    public void Action1(ActionEvent event)throws Exception {
    	Parent ho = FXMLLoader.load(getClass().getResource("sample.fxml"));
    	Scene h1 = new Scene(ho);
    	Stage primaryStage =new Stage();
    	primaryStage.setScene(h1);
    	primaryStage.show();
    	
    	
    }
    
    @FXML
    public void Action4(ActionEvent event)throws Exception {
    	Parent ho = FXMLLoader.load(getClass().getResource("sample2.fxml"));
    	Scene h1 = new Scene(ho);
    	Stage primaryStage =new Stage();
    	
    	primaryStage.setScene(h1);
    	primaryStage.show();
    	
    	
    }
    @FXML
    public void Action2(ActionEvent event)throws Exception {
    	Parent ho = FXMLLoader.load(getClass().getResource("Doctor.fxml"));
    	Scene h1 = new Scene(ho);
    	Stage primaryStage =new Stage();
    	primaryStage.setScene(h1);
    	primaryStage.show();
    	
    	
    }
    @FXML
    public void Action5(ActionEvent event)throws Exception {
    	Parent ho = FXMLLoader.load(getClass().getResource("sample1.fxml"));
    	Scene h1 = new Scene(ho);
    	Stage primaryStage =new Stage();
    	primaryStage.setScene(h1);
    	primaryStage.show();
    	
    	
    }
    
 /*   @FXML
	void handleKeyPressed(KeyEvent ke) {
    	if (KeyCode.ESCAPE == ke.getCode()) {
    		Stage primaryStage =(Stage)((Node)ke.getSource()).getScene().getWindow();
        	
        	
            primaryStage.close();
        }
            }*/
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {

     
   /* 	pane.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>
    	  () {
    	        @Override
    	        public void handle(KeyEvent t) {
    	          if(t.getCode()==KeyCode.ESCAPE)
    	          {
    	              
    	             Stage primaryStage =(Stage)((Node)t.getSource()).getScene().getWindow();
    	          	
    	          	
    	              primaryStage.close();
    	          }
    	        }
    	    });
      */
       
      pane2.setStyle("-fx-background-image: url(\"ordinance/images/a.jpg\"); -fx-background-repeat: no-repeat; -fx-background-size: cover, auto; height:300px;");
      pane3.setStyle("-fx-background-image: url(\"ordinance/images/aq.jpg\"); -fx-background-repeat: no-repeat; -fx-background-size: cover, auto; height:300px;");
       pane4.setStyle("-fx-background-image: url(\"ordinance/images/ar.jpg\"); -fx-background-repeat: no-repeat; -fx-background-size: cover, auto; height:300px;");

       Animation();
}
    @FXML
    private void btnEN(ActionEvent event) {
    	loadLang("en");
    }
    @FXML
    private void btnAL(ActionEvent event) {
    	loadLang("al");
    }

    public void loadLang(String lang) {
    	// TODO Auto-generated method stub
    	locale = new Locale(lang);
    	bundle = ResourceBundle.getBundle("login.lang",locale);

    	file_h.setText(bundle.getString("file_h1"));
    	logout_h.setText(bundle.getString("logout_h1"));
    	close_h.setText(bundle.getString("close_h1"));
    	Statistics_h.setText(bundle.getString("Statistics_h1"));
    	medication_h.setText(bundle.getString("medication_h1"));
    	user_h.setText(bundle.getString("user_h1"));
    	help_h.setText(bundle.getString("help_h1"));
    	about_h.setText(bundle.getString("about_h1"));
    	appoin_h.setText(bundle.getString("appoin_h1"));
    	doc_h.setText(bundle.getString("doc_h1"));
    	test_h.setText(bundle.getString("test_h1"));
    	medic_h.setText(bundle.getString("medic_h1"));
    	nightsh_h.setText(bundle.getString("nightsh_h1"));
    	admin_h.setText(bundle.getString("admin_h1"));


    }
    public  void  Animation(){


        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3),pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(3),pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {

                FadeTransition fadeTransition2=new FadeTransition(Duration.seconds(3),pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                    FadeTransition fadeTransition00=new FadeTransition(Duration.seconds(3),pane2);
                    fadeTransition00.setFromValue(0);
                    fadeTransition00.setToValue(1);
                    fadeTransition00.play();


                    fadeTransition00.setOnFinished(event3 -> {
                        FadeTransition fadeTransition11=new FadeTransition(Duration.seconds(3),pane3);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {
                            FadeTransition fadeTransition22=new FadeTransition(Duration.seconds(3),pane4);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.setToValue(1);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                Animation();
                            });
                        });


                    });
                });

            });




        });



    }
    
}