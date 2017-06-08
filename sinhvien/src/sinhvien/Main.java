package sinhvien;
	
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader Loader = new FXMLLoader();
			Loader.setLocation(getClass()
	                   .getResource("sinhvien-trangchu.fxml"));
			Parent root = Loader.load();
			sinhvienTrangchuController display = Loader.getController();
			display.setTextTenDn("1412123");
			primaryStage.setTitle("Sinh viên");
			root.getStylesheets().add(getClass().getResource("sinhvien.css").toExternalForm());	
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
