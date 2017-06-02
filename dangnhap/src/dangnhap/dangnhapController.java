package dangnhap;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class dangnhapController implements Initializable {
	@FXML //overwrite để đồng bộ với file fxml, tương tự bên dưới
	private TextField tenDangNhap; //tên giống với fx:id bên file fxml, tương tự bên dưới
	@FXML
	private PasswordField matKhau;
	@FXML
	private Button btnDangNhap;
	@FXML
	private Button btnDangKy;
	@FXML
	private Button btnThoat;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	//tên giống với onAction của button tương ứng bên file fxml 
	public void chonDangNhap(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Đăng nhập thành công!!");
		alert.showAndWait();
	}

	public void chonDangKy(ActionEvent event) {
		
	}
	
	public void chonThoat(ActionEvent event) {
//		btnThoat.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
		Stage stage = (Stage) btnThoat.getScene().getWindow();
		stage.close();
	 }
	
}
