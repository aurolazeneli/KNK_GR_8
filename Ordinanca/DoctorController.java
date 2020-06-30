package ordinance;

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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class DoctorController implements Initializable {

	
	@FXML
    private JFXTreeTableView<DoctorModel> TreeTableView;

    @FXML
    private TreeTableColumn<DoctorModel, String> nameCol;

    @FXML
    private TreeTableColumn<DoctorModel, String> dnameCol;

    @FXML
    private TreeTableColumn<DoctorModel, String> ageCol;

    @FXML
    private TreeTableColumn<DoctorModel, String> dateCol;

    @FXML
    private TreeTableColumn<DoctorModel, String> typeCol;

    @FXML
    private TreeTableColumn<DoctorModel, String> resultCol;

    @FXML
    private JFXTextField searchTF;

    @FXML
    private JFXTextField dnameTF;

    @FXML
    private JFXTextField nameTF;

    @FXML
    private JFXTextField ageTF;

    @FXML
    private JFXTextField resultTF;

    @FXML
    private JFXTextField dateTF;

    @FXML
    private JFXComboBox<String> typeTF;

    @FXML
    private Label nameLB;

    @FXML
    private Label dnameLB;

    @FXML
    private Label ageLB;

    @FXML
    private Label dateLB;

    @FXML
    private Label typeLB;

    @FXML
    private Label resultLB;
    
    ObservableList<DoctorModel> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		typeTF.getItems().addAll("1","2","3");

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DoctorModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DoctorModel, String> param) {
                return param.getValue().getValue().name;
            }
        });

        
        dnameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DoctorModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DoctorModel, String> param) {
                return param.getValue().getValue().surname;
            }
        });

        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DoctorModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DoctorModel, String> param) {
                return param.getValue().getValue().prof;
            }
        });
        
        dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DoctorModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DoctorModel, String> param) {
                return param.getValue().getValue().office;
            }
        });
        
        typeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DoctorModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DoctorModel, String> param) {
                return param.getValue().getValue().tel;
            }
        });
        
        resultCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<DoctorModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<DoctorModel, String> param) {
                return param.getValue().getValue().email;
            }
        });
        
        list = FXCollections.observableArrayList();
        TreeItem<DoctorModel> root = new RecursiveTreeItem<DoctorModel>(list, RecursiveTreeObject:: getChildren);
        TreeTableView.setRoot(root);
        TreeTableView.setShowRoot(false);
        try {
        	
        	dbconn conn = new dbconn();
            conn.st = (Statement)conn.connection.createStatement();
    		String query = "select * from doctors ";
    		 conn.rs = conn.st.executeQuery(query);
    		 
    	      while (conn.rs.next())
    	      {
    	         list.add(new DoctorModel(conn.rs.getString("name"), conn.rs.getString("doctorName"),conn.rs.getString("age"),conn.rs.getString("date"),conn.rs.getString("type"),conn.rs.getString("result")));
    	      }
			
		} catch (Exception e) {
			
		}
       
        
        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TreeTableView.setPredicate(new Predicate<TreeItem<DoctorModel>>() {
                    @Override
                    public boolean test(TreeItem<DoctorModel> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue) |modelTreeItem.getValue().office.getValue().contains(newValue) ;
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
		
		dbconn conn = new dbconn();
        conn.insertt(nameTF.getText(),dnameTF.getText(),ageTF.getText(),dateTF.getText(),typeTF.getSelectionModel().getSelectedItem(),resultTF.getText());
        list.addAll(new DoctorModel(nameTF.getText(),dnameTF.getText(),ageTF.getText(),dateTF.getText(),typeTF.getSelectionModel().getSelectedItem(),resultTF.getText()));     
		// String query1 = "INSERT INTO patients(name,doctorName,age,date,type,result) " + "VALUES ("+"'"+nameTF.getText()+"',"+" '"+dnameTF.getText()+"',"+" '"+ageTF.getText()+"',"+"'"+dateTF.getText()+"',"+" '"+typeTF.getSelectionModel().getSelectedItem()+"',"+"'"+resultTF.getText()+"')";
	    
		
	}
	//@FXML
	//void handleKeyPressed(KeyEvent ke) {
		//int index=TreeTableView.getSelectionModel().getSelectedIndex();
          //  if(ke.getCode().equals(KeyCode.DELETE)) {
            //	 list.remove(index);
           // }
//	}
		
	
	 @FXML
	    void  deleteAction(ActionEvent event) throws SQLException{
		 dbconn conn = new dbconn();
		  int index=TreeTableView.getSelectionModel().getSelectedIndex();
	        conn.deletee(TreeTableView.getSelectionModel().getModelItem(index).getValue().office.get(), TreeTableView.getSelectionModel().getModelItem(index).getValue().surname.get(),TreeTableView.getSelectionModel().getModelItem(index).getValue().name.get());
	        clearFields();
	      
	        list.remove(index);
	       
	      
	    }
	 
	 public void showDetails(TreeItem<DoctorModel> newValue)  {

		 String a = newValue.getValue().getName().get();
	        nameTF.setText(a);
	        nameLB.setText(a);

	        dnameTF.setText(newValue.getValue().getsurname().get());
	        dnameLB.setText(dnameTF.getText());
	        
	        dateTF.setText(newValue.getValue().getDate().get());
	        dateLB.setText(newValue.getValue().getDate().get());

	        ageTF.setText(newValue.getValue().getAge().get());
	        ageLB.setText(newValue.getValue().getAge().get());

	        typeTF.getSelectionModel().select(newValue.getValue().getType().get());
	        typeLB.setText(newValue.getValue().getType().get());
	        
	        resultTF.setText(newValue.getValue().getResult().get());
	        resultLB.setText(newValue.getValue().getResult().get());


	}
	 
	 public void clearFields(){
	        nameTF.setText("");
	        nameLB.setText("");
	        dnameTF.setText("");
	        dnameLB.setText("");
	        dateTF.setText("");
	        dateLB.setText("");
	        resultTF.setText("");
	        resultLB.setText("");
	        ageTF.setText("");
	        ageLB.setText("");
	        typeLB.setText("");
	        typeTF.getSelectionModel().select("");
	    }

	    @FXML
	    void clearAction(ActionEvent event){
	        clearFields();
	    }

	    @FXML
	    void editAction(ActionEvent event) throws SQLException {
	    	TreeItem<DoctorModel> treeItem = TreeTableView.getSelectionModel().getSelectedItem();
	        String a = treeItem.getValue().email.get();
	        String b =treeItem.getValue().name.get();
	        String c =treeItem.getValue().surname.get();
	        dbconn con = new dbconn();
	        
	        con.st = (Statement) con.connection.createStatement();
	    	String query = "UPDATE doctors SET name='"+nameTF.getText()+"',surname='"+dnameTF.getText()+"',email='" +ageTF.getText()+"',office='"+dateTF.getText()+"',prof='"+typeTF.getSelectionModel().getSelectedItem()+"',tel='"+resultTF.getText()+"' where name = '" + b + "' AND surname='"+c+"' AND email='"+a+"'";
	    	con.st.executeUpdate(query);
	    	DoctorModel m = new DoctorModel(nameTF.getText(), dnameTF.getText(), ageTF.getText(), dateTF.getText(), typeTF.getSelectionModel().getSelectedItem(), resultTF.getText());
	    	treeItem.setValue(m);
	    }
	    
	}











