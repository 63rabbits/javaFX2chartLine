package javaFX2chartLine;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChartLine extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(this.getClass().getResource("ChartLine.fxml"));
		Scene scene = new Scene(root);
		// http://docs.oracle.com/javafx/2/charts/css-styles.htm
		scene.getStylesheets().addAll(this.getClass().getResource("ChartLine.css").toExternalForm());

		stage.setTitle("JavaFX 2 line chart");

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
