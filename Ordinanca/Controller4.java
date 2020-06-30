package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mysql.jdbc.Statement;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class Controller4 implements Initializable {
    @FXML
    private JFXTreeTableView<Model4> TreeTableView;

    @FXML
    private TreeTableColumn<Model4, String> namecol;

    @FXML
    private TreeTableColumn<Model4, String> grcol;

    @FXML
    private TreeTableColumn<Model4, String> rescol;


    @FXML
    private JFXTextField nameid;

    @FXML
    private JFXTextField grid;

    @FXML
    private JFXTextField resid;




    @FXML
    private JFXTextField searchTF;

    @FXML
    private Label nameLB;

    @FXML
    private Label grLB;

    @FXML
    private Label resLB;





    ObservableList<Model4> list;


    @Override
    public void initialize(URL location, ResourceBundle resources) {



        namecol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model4, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model4, String> param) {
                return param.getValue().getValue().name;
            }
        });


        grcol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model4, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model4, String> param) {
                return param.getValue().getValue().grupi;
            }
        });

        rescol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model4, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model4, String> param) {
                return param.getValue().getValue().result;
            }
        });





        list = FXCollections.observableArrayList();
        TreeItem<Model4> root = new RecursiveTreeItem<Model4>(list, RecursiveTreeObject:: getChildren);
        TreeTableView.setRoot(root);
        TreeTableView.setShowRoot(false);
        try {

            dbconn conn = new dbconn();
            conn.st = (Statement)conn.connection.createStatement();
            String query = "select * from test ";
            conn.rs = conn.st.executeQuery(query);

            while (conn.rs.next())
            {
                list.add(new Model4(conn.rs.getString("Name"), conn.rs.getString(" Gjaku"),conn.rs.getString("Result")));
            }

        } catch (Exception e) {

        }


        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TreeTableView.setPredicate(new Predicate<TreeItem<Model4>>() {
                    @Override
                    public boolean test(TreeItem<Model4> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue) |modelTreeItem.getValue().grupi.getValue().contains(newValue) ;
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
        conn.insert(nameid.getText(),grid.getText(),resid.getText());
        list.addAll(new Model4(nameid.getText(),grid.getText(),resid.getText()));
        String query1 = "INSERT INTO test(Name,Gjaku,Result) " + "VALUES ("+"'"+nameid.getText()+"',"+" '"+grid.getText()+"',"+" '"+resid.getText()+"')";


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
        conn.delete(resid.getText(), grid.getText(), nameid.getText());
        clearFields();

    }

    public void showDetails(TreeItem<Model4> treeItem)  {

        String a = treeItem.getValue().getName().get();
        nameid.setText(a);
        nameLB.setText(a);

        grid.setText(treeItem.getValue().getgrupi().get());
        grLB.setText(grid.getText());

        resid.setText(treeItem.getValue().getResult().get());
        resLB.setText(treeItem.getValue().getResult().get());






    }

    public void clearFields(){
        nameid.setText("");
        nameLB.setText("");
        grid.setText("");
        grLB.setText("");
        resid.setText("");
        resLB.setText("");


    }

    @FXML
    void clearAction(ActionEvent event){
        clearFields();
    }

    @FXML
    void editAction(ActionEvent event) throws SQLException {
        TreeItem<Model4> treeItem = TreeTableView.getSelectionModel().getSelectedItem();
        String a = treeItem.getValue().result.get();
        String b =treeItem.getValue().name.get();
        String c =treeItem.getValue().grupi.get();
        dbconn con = new dbconn();

        con.st = (Statement) con.connection.createStatement();
        String query = "UPDATE test SET Name='"+nameid.getText()+"', Gjaku='"+grid.getText()+"',Result='" +resid.getText()+"' where Name = '" + b + "'&& Gjaku='"+c+"'&& Result='"+a+"'";
        con.st.executeUpdate(query);
        Model4 m = new Model4(nameid.getText(), grid.getText(), resid.getText());
        treeItem.setValue(m);
    }

}
