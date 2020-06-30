package app;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.PrdouctDetailsController.OperationMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ProductController implements Initializable {
	@FXML
	private TableView<Product> tableView;
	@FXML
	private TableColumn<Product, Integer>idColumn;
	@FXML
	private TableColumn<Product,String> nameColumn;
	@FXML
	private TableColumn<Product,Double> priceColumn;
	@FXML
	private TableColumn<Product,Double> amountColumn;
	
	@FXML	
	private Button addNewButton;
	@FXML	
	private Button editButton;
	@FXML	
	private Button deleteButton;
	
	private ProductRepository repository;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		repository=new ProductRepository(); 
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		this.loadProducts();
	}
	@FXML
	private void onAddButtonClicked(ActionEvent event) throws Exception {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("product-detalis.fxml"));
		Parent parent =loader.load();
		Scene scene=new Scene(parent);
		
		PrdouctDetailsController controller =loader.getController();
		controller.loadProduct(new Product());
		controller.setOperationMode(OperationMode.Create);
		
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	} 
	@FXML
	private void onEditButtonClicked(ActionEvent event) throws Exception {
		Product item=this.getSelectedProduct();
		if(item ==null) return;
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("product-detalis.fxml"));
		Parent parent =loader.load();
		Scene scene=new Scene(parent);
		
		PrdouctDetailsController controller =loader.getController();
		controller.loadProduct(item);
		controller.setOperationMode(OperationMode.Update);
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@FXML
	private void onDeleteButtonClicked(ActionEvent event) {
		Product item=this.getSelectedProduct();
		if(item != null) {
		deleteProduct(item.getId());
		}
	}
	private Product getSelectedProduct() {
		return tableView.getSelectionModel().getSelectedItem();
	}
	public void addProduct(Product item ) {
		repository.create(item);
		loadProducts();
	}
	public void editProduct(Product item ) {
		repository.update(item);
		loadProducts();
	}
	public void deleteProduct(int id) {
		repository.remove(id);
		loadProducts();
	}
	public void loadProducts() {
		ObservableList<Product> items=FXCollections.observableArrayList(repository.getAll());
		tableView.setItems(items);
		
	}

}