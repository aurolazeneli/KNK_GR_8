import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
public class statistics  implements Initializable{

	@FXML
	private Pane paneView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		loadData();
		
	}
	private void loadData() {
		
		paneView.getChildren().clear();
		CategoryAxis xAxis=new CategoryAxis();
		xAxis.setLabel("Medicament Name");
		NumberAxis yaAxis=new NumberAxis();
		yaAxis.setLabel("Number of Medicament");
		BarChart sts=new BarChart(xAxis,yaAxis);
		XYChart.Series series= new XYChart.Series();	
		series.getData().add(new XYChart.Data<>("Aspirin",30000)); 
		series.getData().add(new XYChart.Data<>("Paracetamol",60000)); 
		series.getData().add(new XYChart.Data<>("OKI",60000)); 
		series.getData().add(new XYChart.Data<>("Ibrufen",90000)); 
		sts.getData().add(series);
		
		paneView.getChildren().add(sts);
		

		

		
	}
	
	
	
	
	
	

}
