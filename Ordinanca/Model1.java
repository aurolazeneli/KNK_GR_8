package ordinance;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model1 extends RecursiveTreeObject<Model1> {

	
	
	StringProperty dname,date;
	
	
	public Model1(String dname, String date ) {
		this.dname = new SimpleStringProperty(dname );
		this.date = new SimpleStringProperty(date );
	}




	public StringProperty getDname() {
		return dname;
	}


	public void setDname(StringProperty dname) {
		this.dname = dname;
	}


	public StringProperty getDate() {
		return date;
	}


	public void setDate(StringProperty date) {
		this.date = date;
	}

}