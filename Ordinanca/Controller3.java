package ordinance;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


import java.util.Locale;



public class Controller3 implements Initializable {


	
	@FXML
    private JFXTreeTableView<Model1> TreeTableView;

    @FXML
    private TreeTableColumn<Model1, String> dnameCol;


    @FXML
    private TreeTableColumn<Model1, String> dateCol;

    @FXML
    private JFXTextField searchTF;

    @FXML
    private JFXTextField dnameTF;



    @FXML
    private JFXButton BTNS;

    @FXML
    private JFXButton BTNS1;

    @FXML
    private JFXButton BTNS2;

    @FXML
    private JFXButton BTNS3;
	
    @FXML
    private JFXTextField dateTF;

    @FXML
    private Label dnameLB;

    @FXML
    private Label dateLB;
    private ResourceBundle bundle;
    private Locale locale;
    @FXML
    private void btnEN(ActionEvent event) {
    	loadLang("en");
    }
    @FXML
    private void btnAL(ActionEvent event) {
    	loadLang("al");
    }
    
    ObservableList<Model1> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

        dnameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model1, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model1, String> param) {
                return param.getValue().getValue().dname;
            }
        });

        dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model1, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model1, String> param) {
                return param.getValue().getValue().date;
            }
        });
        
        list = FXCollections.observableArrayList();
        TreeItem<Model1> root = new RecursiveTreeItem<Model1>(list, RecursiveTreeObject:: getChildren);
        TreeTableView.setRoot(root);
        TreeTableView.setShowRoot(false);
        try {
        	
        	dbconn1 conn = new dbconn1();
            conn.st = (Statement)conn.connection.createStatement();
    		String query = "select * from doctors ";
    		 conn.rs = conn.st.executeQuery(query);
    		 
    	      while (conn.rs.next())
    	      {
    	         list.add(new Model1(conn.rs.getString("doctorName"),conn.rs.getString("date")));
    	      }
			
		} catch (Exception e) {
			System.out.println("error 3: "+e);
		}
       
        
        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TreeTableView.setPredicate(new Predicate<TreeItem<Model1>>() {
                    @Override
                    public boolean test(TreeItem<Model1> modelTreeItem) {
                        return modelTreeItem.getValue().dname.getValue().contains(newValue) |modelTreeItem.getValue().date.getValue().contains(newValue) ;
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
		dbconn1 conn = new dbconn1();
		boolean a = formValidation.validation(dnameTF.getText(),"Doctor Name");
		boolean b = formValidation.validationDate(dateTF.getText());
		if(a && b) {
        conn.insert(dnameTF.getText(),dateTF.getText());
       
        list.addAll(new Model1(dnameTF.getText(),dateTF.getText()));     
		
	}
		}

		
	
	 @FXML
	    void  deleteAction(ActionEvent event) throws SQLException{
	        int index=TreeTableView.getSelectionModel().getSelectedIndex();
	        list.remove(index);
	        dbconn1 conn = new dbconn1();
	        conn.delete(dateTF.getText(), dnameTF.getText());
	        clearFields();
	      
	    }
	 
	 public void showDetails(TreeItem<Model1> treeItem)  {

	        dnameTF.setText(treeItem.getValue().getDname().get());
	        dnameLB.setText(dnameTF.getText());
	        
	        dateTF.setText(treeItem.getValue().getDate().get());
	        dateLB.setText(treeItem.getValue().getDate().get());

	}
	 
	 public void clearFields(){
	        dnameTF.setText("");
	        dnameLB.setText("");
	        dateTF.setText("");
	        dateLB.setText("");
	    }

	    @FXML
	    void clearAction(ActionEvent event){
	        clearFields();
	    }

	    @FXML
	    void editAction(ActionEvent event) throws SQLException {
	    	TreeItem<Model1> treeItem = TreeTableView.getSelectionModel().getSelectedItem();
	        String a = treeItem.getValue().date.get();
	        String c =treeItem.getValue().dname.get();
	        dbconn con = new dbconn();
	        
	        con.st = (Statement) con.connection.createStatement();
	    	String query = "UPDATE doctors SET doctorName='"+dateTF.getText()+"',date='"+dateTF.getText()+"' where doctorName='"+c+"'&& date='"+a+"'";
	    	con.st.executeUpdate(query);
	    	Model1 m = new Model1(dnameTF.getText(),dateTF.getText());
	    	treeItem.setValue(m);
	    }
	    public void loadLang(String lang) {
	    	// TODO Auto-generated method stub
	    	locale = new Locale(lang);
	    	bundle = ResourceBundle.getBundle("ordinance.lang",locale);
	    	
//
//	    	dateLB.setText(bundle.getString("dateLB"));
//	    	dnameLB.setText(bundle.getString("dnameLB"));
	    	dateCol.setText(bundle.getString("dateCol2"));
	    	dnameCol.setText(bundle.getString("dnameCol2"));
	    	searchTF.setPromptText(bundle.getString("searchTF2"));
	    	dnameTF.setPromptText(bundle.getString("dnameTF2"));
	    	dateTF.setPromptText(bundle.getString("dateTF2"));

	    	BTNS.setText(bundle.getString("BTNS_2"));
	    	BTNS1.setText(bundle.getString("BTNS1_2"));
	    	BTNS2.setText(bundle.getString("BTNS2_2"));
	    	BTNS3.setText(bundle.getString("BTNS3_2"));
	    	
	   
	    }
	    
	}
