package giaovien;

import java.net.URL; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class taodemoiController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maMon;
	@FXML
	private TextArea moTa;
	@FXML
	private Button btnTaoDe;
	@FXML
	private Button btnQuayLai;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void chonTaoDe(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Đề đã được tạo thành công!");
		alert.showAndWait();
	}
	
	public void chonQuayLai(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("themde.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		themdeController display = Loader.getController();
		display.setTextMon(maMon.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Quay lai");
		stage.setResizable(false);				        
		stage.setScene(scene);
	 }
	public void setTextMon(String ma){
		 this.maMon.setText(ma);
	}
	public void setTextTenDn(String ten){
		 this.tendn.setText(ten);
	}
}