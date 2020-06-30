package ordinance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class formValidation {

	
	public static void showAlertDialog(String message) {
		
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        
		}
	
	
	public static Boolean validation(String str,String field) {
		Boolean b= true;
	   
		if ((str == null || str.length() == 0)) {
	        try {
	        	showAlertDialog("Field \""+field+"\"  is empty");

	            return false;
	        } catch (NumberFormatException e) { 
	        	System.out.println("Error"+e);
	        }
		}
		else {
			if(!Pattern.matches("^[a-zA-Z_ ]*$",str)) {
	    	try {
	    		showAlertDialog("Field \""+field+"\" is not valid");

	            return false;
	        } catch (NumberFormatException e) { 
	        	System.out.println("Error"+e);
	        }
	    	}
	    	else {
	    	return b;
		}
	    
	}
		return b;
}
	
	public static Boolean validationString(String str,String field) {
		Boolean b= true;
	   
		if ((str == null || str.length() == 0)) {
	        try {
	        	showAlertDialog("Field \""+field+"\" is empty");

	            return false;
	        } catch (NumberFormatException e) { 
	        	System.out.println("Error"+e);
	        }
		}
		
		return b;
}
	public static boolean isNumeric(String strNum,String field) {
		
		if (strNum == null || strNum.length() == 0){

			showAlertDialog("Field \""+field+"\"  is empty");
			return false;
		}
		else if (!strNum.matches("-?\\d+(\\.\\d+)?")){
			showAlertDialog("Field \""+field+"\" is not number"+"\""+strNum+"\"");
			return false;
		}
		return true;
	}
	public static Boolean validationDate(String str) {
		
		if (str.trim().equals(""))
		{
			showAlertDialog("Date field is empty");
		    return false;
		}
		
		else
		{
		    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		    sdfrmt.setLenient(false);
		    
		    try
		    {
		        Date javaDate = sdfrmt.parse(str); 
		        
		    }
		    catch (ParseException e)
		    {
		    	showAlertDialog(str+" is Invalid Date format");

		        return false;
		    }
		    return true;
		}
	
	}
}