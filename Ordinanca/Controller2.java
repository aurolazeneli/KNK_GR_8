package projektiKNK;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Statement;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class Controller2 implements Initializable {

	
	@FXML
    private JFXTreeTableView<Model2> TreeTableView;

    @FXML
    private TreeTableColumn<Model2, String> medcol;

    @FXML
    private TreeTableColumn<Model2, String> indcol;

    @FXML
    private TreeTableColumn<Model2, String> quancol;

    @FXML
    private TreeTableColumn<Model2, String> fromcol;



    @FXML
    private JFXTextField mediIN;

    @FXML
    private JFXTextField indIN;

    @FXML
    private JFXTextField quanIN;

    @FXML
    private JFXTextField fromIN;

  
    @FXML
    private JFXTextField searchTF;

    @FXML
    private Label medLB;

    @FXML
    private Label indLB;

    @FXML
    private Label quanLB;

    @FXML
    private Label fromLB;

  
    
    ObservableList<Model2> list;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//typeTF.getItems().addAll("1","2","3");

        medcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> param) {
                return param.getValue().getValue().medicament;
            }
        });

        
        indcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> param) {
                return param.getValue().getValue().indication;
            }
        });

        quancol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> param) {
                return param.getValue().getValue().quantity;
            }
        });
        
        fromcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model2, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model2, String> param) {
                return param.getValue().getValue().from;
            }
        });
        
   
        
        list = FXCollections.observableArrayList();
        TreeItem<Model2> root = new RecursiveTreeItem<Model2>(list, RecursiveTreeObject:: getChildren);
        TreeTableView.setRoot(root);
        TreeTableView.setShowRoot(false);
        try {
        	
        	dbconn conn = new dbconn();
            conn.st = (Statement)conn.connection.createStatement();
    		String query = "select * from medicament ";
    		 conn.rs = conn.st.executeQuery(query);
    		 
    	      while (conn.rs.next())
    	      {
    	         list.add(new Model2(conn.rs.getString("Medicament"), conn.rs.getString("Indication"),conn.rs.getString("Quantity"),conn.rs.getString("Origin")));
    	      }
			
		} catch (Exception e) {
			
		}
       
        
        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TreeTableView.setPredicate(new Predicate<TreeItem<Model2>>() {
                    @Override
                    public boolean test(TreeItem<Model2> modelTreeItem) {
                        return modelTreeItem.getValue().medicament.getValue().contains(newValue) |modelTreeItem.getValue().quantity.getValue().contains(newValue) ;
                    }
                });
            }
        });
        
        TreeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails(newValue);
        });


      

    }
	
	
	
	@FXML
	void addAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		
		boolean c = formValidation.validation(mediIN.getText(),"Medicament");
		boolean a = formValidation.validation(indIN.getText(),"Indication");
		boolean e = formValidation.isNumeric(quanIN.getText(),"Quantity");
		boolean d = formValidation.validationString(fromIN.getText(),"Origin");
		
		if(c && a && e && d ) {
			dbconn conn = new dbconn();
	       
		
		
		
		
        conn.insert(mediIN.getText(),indIN.getText(),quanIN.getText(),fromIN.getText());
        list.addAll(new Model2(mediIN.getText(),indIN.getText(),quanIN.getText(),fromIN.getText()));     
		 String query1 = "INSERT INTO medicament(Medicament,Indication,Quantity,Origin) " + "VALUES ("+"'"+mediIN.getText()+"',"+" '"+indIN.getText()+"',"+" '"+quanIN.getText()+"',"+"'"+fromIN.getText()+"')";
	    
		}
	}
	@FXML
	void handleKeyPressed(KeyEvent ke) {
		int index=TreeTableView.getSelectionModel().getSelectedIndex();
            if(ke.getCode().equals(KeyCode.DELETE)) {
             list.remove(index);
            }
	}
		
	
	 @FXML
	    void  deleteAction(ActionEvent event) throws SQLException{
	        int index=TreeTableView.getSelectionModel().getSelectedIndex();
	        list.remove(index);
	        dbconn conn = new dbconn();
	        conn.delete(quanIN.getText(), indIN.getText(), mediIN.getText());
	        clearFields();
	      
	    }
	 
	 public void showDetails(TreeItem<Model2> treeItem)  {

		 String a = treeItem.getValue().getName().get();
	        mediIN.setText(a);
	        medLB.setText(a);

	        indIN.setText(treeItem.getValue().getindication().get());
	        indLB.setText(indIN.getText());
	        
	        quanIN.setText(treeItem.getValue().getQuantity().get());
	        quanLB.setText(treeItem.getValue().getQuantity().get());

	        fromIN.setText(treeItem.getValue().getFrom().get());
	        fromLB.setText(treeItem.getValue().getFrom().get());

	     


	}
	 
	 public void clearFields(){
	        mediIN.setText("");
	        medLB.setText("");
	        indIN.setText("");
	        indLB.setText("");
	        quanIN.setText("");
	        quanLB.setText("");
	        fromIN.setText("");
	        fromLB.setText("");
	     
	    }

	    @FXML
	    void clearAction(ActionEvent event){
	        clearFields();
	    }

	    @FXML
	    void editAction(ActionEvent event) throws SQLException {
	    	TreeItem<Model2> treeItem = TreeTableView.getSelectionModel().getSelectedItem();
	        String a = treeItem.getValue().quantity.get();
	        String b =treeItem.getValue().medicament.get();
	        String c =treeItem.getValue().indication.get();
	        dbconn con = new dbconn();
	        
	        con.st = (Statement) con.connection.createStatement();
	    	String query = "UPDATE medicament SET Medicament='"+mediIN.getText()+"',Indication='"+indIN.getText()+"',Quantity='" +quanIN.getText()+"',Origin='"+fromIN.getText()+"' where Medicament = '" + b + "'&& Indication='"+c+"'&& Quantity='"+a+"'";
	    	con.st.executeUpdate(query);
	    	Model2 m = new Model2(mediIN.getText(), indIN.getText(), quanIN.getText(), fromIN.getText());
	    	treeItem.setValue(m);
	    }
	    
	}





