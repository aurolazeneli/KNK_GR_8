package projektiKNK;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model2 extends RecursiveTreeObject<Model2> {

	
	
	StringProperty medicament, indication,quantity,from;
	
	
	public Model2(String medicament, String indication,String quantity, String from) {
		this.medicament = new SimpleStringProperty(medicament );
		this.indication = new SimpleStringProperty(indication );
		this.quantity = new SimpleStringProperty(quantity);
		this.from = new SimpleStringProperty(from);

	}


	public StringProperty getName() {
		return medicament;
	}


	public void setName(StringProperty medicament) {
		this.medicament = medicament;
	}


	public StringProperty getindication() {
		return indication;
	}


	public void setindication(StringProperty indication) {
		this.indication = indication;
	}


	public StringProperty getQuantity() {
		return quantity;
	}


	public void setQuantity(StringProperty quantity) {
		this.quantity = quantity;
	}


	public StringProperty getFrom() {
		return from;
	}


	public void setFrom(StringProperty from) {
		this.from = from;
	}
	
	
}