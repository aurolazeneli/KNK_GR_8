package app;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class PrdouctDetailsController implements Initializable{
	public enum OperationMode{
		Create,Update
		}
	
	@FXML
	private TextField idField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField amountField;
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;
	
	private Product product;
	private OperationMode mode=OperationMode.Create;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.idField.setDisable(true);
		
	}
	@FXML
	private void onSaveButtonClicked(ActionEvent event) throws Exception{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("products.fxml"));
		
		Parent parent=loader.load();
		Scene scene=new Scene(parent);
		
		ProductController controller =loader.getController();
		Product product=new Product(Integer.parseInt(idField.getText()),nameField.getText(),Double.parseDouble(priceField.getText()),Double.parseDouble(amountField.getText()));
		if(mode == OperationMode.Create) {
			controller.addProduct(product);
		} 
		else {
			controller.editProduct(product);
		}
	}
	@FXML
	private void onCancelButtonClicked(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
		Scene scene= new Scene(parent);
		Stage primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}
	public void loadProduct(Product item) {
		this.idField.setText(Integer.toString(item.getId()));
		this.nameField.setText(item.getName());
		this.priceField.setText(Double.toString(item.getPrice()));
		this.amountField.setText(Double.toString(item.getAmount()));
	}
	public void setOperationMode(OperationMode mode) {
		this.mode=mode;
	}
}
