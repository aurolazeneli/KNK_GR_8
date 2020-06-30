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

public class Controller implements Initializable {

	
	@FXML
    private JFXTreeTableView<Model> TreeTableView;

    @FXML
    private TreeTableColumn<Model, String> nameCol;

    @FXML
    private TreeTableColumn<Model, String> dnameCol;

    @FXML
    private TreeTableColumn<Model, String> ageCol;

    @FXML
    private TreeTableColumn<Model, String> dateCol;

    @FXML
    private TreeTableColumn<Model, String> typeCol;

    @FXML
    private TreeTableColumn<Model, String> resultCol;

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
    private Label pn_s;
    @FXML
    private Label dn_s;

    @FXML
    private Label d_s;

    @FXML
    private Label t_s;

    @FXML
    private Label r_s;
  
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
    
    @FXML
    private JFXButton BTNS;

    @FXML
    private JFXButton BTNS1;

    @FXML
    private JFXButton BTNS2;

    @FXML
    private JFXButton BTNS3;


    private ResourceBundle bundle;
    private Locale locale;

    
    
    ObservableList<Model> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		typeTF.getItems().addAll("1","2","3");

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().name;
            }
        });

        
        dnameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().dname;
            }
        });

        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().age;
            }
        });
        
        dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().date;
            }
        });
        
        typeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().type;
            }
        });
        
        resultCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().result;
            }
        });
        
        list = FXCollections.observableArrayList();
        TreeItem<Model> root = new RecursiveTreeItem<Model>(list, RecursiveTreeObject:: getChildren);
        TreeTableView.setRoot(root);
        TreeTableView.setShowRoot(false);
        try {
        	
        	dbconn conn = new dbconn();
            conn.st = (Statement)conn.connection.createStatement();
    		String query = "select * from patients ";
    		 conn.rs = conn.st.executeQuery(query);
    		 
    	      while (conn.rs.next())
    	      {
    	         list.add(new Model(conn.rs.getString("name"), conn.rs.getString("doctorName"),conn.rs.getString("age"),conn.rs.getString("date"),conn.rs.getString("type"),conn.rs.getString("result")));
    	      }
			
		} catch (Exception e) {
			
		}
       
        
        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TreeTableView.setPredicate(new Predicate<TreeItem<Model>>() {
                    @Override
                    public boolean test(TreeItem<Model> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue) |modelTreeItem.getValue().age.getValue().contains(newValue) ;
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
		boolean c = formValidation.validation(nameTF.getText(),"Pacient Name");
		boolean a = formValidation.validation(dnameTF.getText(),"Doctor Name");
		boolean b = formValidation.validationDate(dateTF.getText());
		boolean e = formValidation.isNumeric(ageTF.getText(),"Age");
		boolean d = formValidation.validationString(resultTF.getText(),"Result");
		boolean f = formValidation.validationString(typeTF.getSelectionModel().getSelectedItem(),"Type");
		if(a && b && c && d && e && f) {
		
		dbconn conn = new dbconn();
        conn.insert(nameTF.getText(),dnameTF.getText(),ageTF.getText(),dateTF.getText(),typeTF.getSelectionModel().getSelectedItem(),resultTF.getText());
        list.addAll(new Model(nameTF.getText(),dnameTF.getText(),ageTF.getText(),dateTF.getText(),typeTF.getSelectionModel().getSelectedItem(),resultTF.getText()));     
		// String query1 = "INSERT INTO patients(name,doctorName,age,date,type,result) " + "VALUES ("+"'"+nameTF.getText()+"',"+" '"+dnameTF.getText()+"',"+" '"+ageTF.getText()+"',"+"'"+dateTF.getText()+"',"+" '"+typeTF.getSelectionModel().getSelectedItem()+"',"+"'"+resultTF.getText()+"')";
		}
		
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
	        conn.delete(TreeTableView.getSelectionModel().getModelItem(index).getValue().date.get(), TreeTableView.getSelectionModel().getModelItem(index).getValue().dname.get(),TreeTableView.getSelectionModel().getModelItem(index).getValue().name.get());
	        clearFields();
	      
	        list.remove(index);
	       
	      
	    }
	 
	 public void showDetails(TreeItem<Model> treeItem)  {

		 String a = treeItem.getValue().getName().get();
	        nameTF.setText(a);
	        nameLB.setText(a);

	        dnameTF.setText(treeItem.getValue().getDname().get());
	        dnameLB.setText(dnameTF.getText());
	        
	        dateTF.setText(treeItem.getValue().getDate().get());
	        dateLB.setText(treeItem.getValue().getDate().get());

	        ageTF.setText(treeItem.getValue().getAge().get());
	        ageLB.setText(treeItem.getValue().getAge().get());

	        typeTF.getSelectionModel().select(treeItem.getValue().getType().get());
	        typeLB.setText(treeItem.getValue().getType().get());
	        
	        resultTF.setText(treeItem.getValue().getResult().get());
	        resultLB.setText(treeItem.getValue().getResult().get());


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
	    	TreeItem<Model> treeItem = TreeTableView.getSelectionModel().getSelectedItem();
	        String a = treeItem.getValue().date.get();
	        String b =treeItem.getValue().name.get();
	        String c =treeItem.getValue().dname.get();
	        dbconn con = new dbconn();
	        
	        con.st = (Statement) con.connection.createStatement();
	    	String query = "UPDATE patients SET name='"+nameTF.getText()+"',doctorName='"+dnameTF.getText()+"',age='" +ageTF.getText()+"',date='"+dateTF.getText()+"',type='"+typeTF.getSelectionModel().getSelectedItem()+"',result='"+resultTF.getText()+"' where name = '" + b + "' AND doctorName='"+c+"' AND date='"+a+"'";
	    	con.st.executeUpdate(query);
	    	Model m = new Model(nameTF.getText(), dnameTF.getText(), ageTF.getText(), dateTF.getText(), typeTF.getSelectionModel().getSelectedItem(), resultTF.getText());
	    	treeItem.setValue(m);
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
	    	bundle = ResourceBundle.getBundle("ordinance.lang",locale);

	    	nameCol.setText(bundle.getString("nameCol2"));
	    	ageCol.setText(bundle.getString("ageCol2"));
	    	dateCol.setText(bundle.getString("dateCol2"));
	    	typeCol.setText(bundle.getString("typeCol2"));
	    	resultCol.setText(bundle.getString("resultCol2"));
	    	searchTF.setPromptText(bundle.getString("searchTF2"));
	    	dnameTF.setPromptText(bundle.getString("dnameTF2"));
	    	nameTF.setPromptText(bundle.getString("nameTF2"));
	    	ageTF.setPromptText(bundle.getString("ageTF2"));
	    	resultTF.setPromptText(bundle.getString("resultTF2"));
	    	dateTF.setPromptText(bundle.getString("dateTF2"));

	    	BTNS.setText(bundle.getString("BTNS_2"));
	    	BTNS1.setText(bundle.getString("BTNS1_2"));
	    	BTNS2.setText(bundle.getString("BTNS2_2"));
	    	BTNS3.setText(bundle.getString("BTNS3_2"));
	    	
	    	pn_s.setText(bundle.getString("pn_s2"));
	    	dn_s.setText(bundle.getString("dn_s2"));
	    	d_s.setText(bundle.getString("d_s2"));
	    	t_s.setText(bundle.getString("t_s2"));
	    	r_s.setText(bundle.getString("r_s2"));
	    

	    	
	    	


	    }
	    
	}











