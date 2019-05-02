package javaFX2chartLine;

import java.net.URL;
import java.util.Iterator;
import java.util.TreeMap;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class ChartLineController {

	@FXML
	private Pane linecPane;

	@FXML
	void initialize() {
		assert linecPane != null : "fx:id=\"linecPane\" was not injected: check your FXML file 'LineChart.fxml'.";

		XYChart.Series<Number, Number> series01 = new XYChart.Series<>();
		XYChart.Series<Number, Number> series02 = new XYChart.Series<>();
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<Number, Number> linec = new LineChart<Number, Number>(xAxis, yAxis);

		linec.prefWidthProperty().bind(linecPane.widthProperty());
		linec.prefHeightProperty().bind(linecPane.heightProperty());
		linecPane.getChildren().add(linec);

		linec.setTitle("Line Chart Sample");
		xAxis.setLabel("X-Axis label");
		yAxis.setLabel("Y-Axis label");
		//		linec.setCreateSymbols(false);

		series01.setName("Line01");
		{
			URL url = this.getClass().getResource("res/data01.csv");
			OpCsv csv = new OpCsv(url);

			TreeMap<Integer, String[]> map = csv.getNumberSortedCsv(0);
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				int no = it.next();
				String[] words = map.get(no);
				Double dataX = Double.parseDouble(words[0]);
				Double dataY = Double.parseDouble(words[1]);

				series01.getData().add(new XYChart.Data<Number, Number>(dataX, dataY));
			}
		}
		linec.getData().add(series01);

		series02.setName("Line02");
		{
			URL url = this.getClass().getResource("res/data02.csv");
			OpCsv csv = new OpCsv(url);

			TreeMap<Integer, String[]> map = csv.getNumberSortedCsv(0);
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				int no = it.next();
				String[] words = map.get(no);
				Double dataX = Double.parseDouble(words[0]);
				Double dataY = Double.parseDouble(words[1]);

				series02.getData().add(new XYChart.Data<Number, Number>(dataX, dataY));
			}
		}
		linec.getData().add(series02);
	}

}
