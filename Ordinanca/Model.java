package ordinance;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model extends RecursiveTreeObject<Model> {

	
	
	StringProperty name, dname,type,age,date, result;
	
	
	public Model(String name, String dname,String age, String date, String type, String result ) {
		this.name = new SimpleStringProperty(name );
		this.dname = new SimpleStringProperty(dname );
		this.age = new SimpleStringProperty(age );
		this.date = new SimpleStringProperty(date );
		this.type = new SimpleStringProperty(type );
		this.result = new SimpleStringProperty(result );
	}


	public StringProperty getName() {
		return name;
	}


	public void setName(StringProperty name) {
		this.name = name;
	}


	public StringProperty getDname() {
		return dname;
	}


	public void setDname(StringProperty dname) {
		this.dname = dname;
	}


	public StringProperty getType() {
		return type;
	}


	public void setType(StringProperty type) {
		this.type = type;
	}


	public StringProperty getAge() {
		return age;
	}


	public void setAge(StringProperty age) {
		this.age = age;
	}


	public StringProperty getDate() {
		return date;
	}


	public void setDate(StringProperty date) {
		this.date = date;
	}


	public StringProperty getResult() {
		return result;
	}


	public void setResult(StringProperty result) {
		this.result = result;
	}
	
	
}
