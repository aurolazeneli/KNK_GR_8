package ordinance;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorModel extends RecursiveTreeObject<DoctorModel> {

	
	
	StringProperty name, surname,prof,office,tel, email;
	
	
	public DoctorModel(String name, String surname,String prof, String office, String tel, String email ) {
		this.name = new SimpleStringProperty(name );
		this.surname = new SimpleStringProperty(surname );
		this.prof = new SimpleStringProperty(prof );
		this.office = new SimpleStringProperty(office );
		this.tel = new SimpleStringProperty(tel );
		this.email = new SimpleStringProperty(email );
	}


	public StringProperty getName() {
		return name;
	}


	public void setName(StringProperty name) {
		this.name = name;
	}


	public StringProperty getsurname() {
		return surname;
	}


	public void setDname(StringProperty dname) {
		this.surname = dname;
	}


	public StringProperty getType() {
		return prof;
	}


	public void setType(StringProperty type) {
		this.prof = type;
	}


	public StringProperty getAge() {
		return office;
	}


	public void setAge(StringProperty age) {
		this.office = age;
	}


	public StringProperty getDate() {
		return tel;
	}


	public void setDate(StringProperty date) {
		this.tel = date;
	}


	public StringProperty getResult() {
		return email;
	}


	public void setResult(StringProperty result) {
		this.email = result;
	}
	
	
}
