package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model4 extends RecursiveTreeObject<Model4>{
    StringProperty name, grupi,result;


    public Model4(String name, String grupi, String result) {
        this.name = new SimpleStringProperty(name );
        this.grupi = new SimpleStringProperty(grupi );
        this.result = new SimpleStringProperty(result);


    }


    public StringProperty getName() {
        return name;
    }


    public void setName(StringProperty name) {
        this.name = name;
    }


    public StringProperty getgrupi() {
        return grupi;
    }


    public void setgrupi(StringProperty grupi) {
        this.grupi = grupi;
    }


    public StringProperty getResult() {
        return result;
    }


    public void setResult(StringProperty result) {
        this.result = result;
    }



}
