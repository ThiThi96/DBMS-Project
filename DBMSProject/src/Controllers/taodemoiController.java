package Controllers;

import java.net.URL; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.util.Optional;
import java.util.ResourceBundle;

import Models.User;
import application.FxDialogs;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class taodemoiController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private TextArea moTa;
	@FXML
	private Button btnTaoDe;
	@FXML
	private Button btnQuayLai;
	@FXML
	private ImageView iconUser;
	@FXML
	private ComboBox<String> delay;
	
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
		delay.setValue("No delay");
	}
	
	public void chonTaoDe(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tao de");
		alert.setHeaderText("Ban muon cho de nay cho mon hay chi them vao thu vien de?");
		alert.setContentText("Lua chon cua ban");

		ButtonType buttonTypeOne = new ButtonType("Tiep tuc them de cho mon");
		ButtonType buttonTypeTwo = new ButtonType("Chi them vao thu vien de");
		ButtonType buttonTypeCancle = new ButtonType("Huy", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancle);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
			Parent pane = null;
			FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("../application/themdechomon.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			themdechomonController display = Loader.getController();
			display.setTextMon(maLop.getText(), tenLop.getText());
			display.setTextTenDn(user);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnTaoDe.getScene().getWindow();
			stage.setTitle("Thêm đề cho môn");
			stage.setResizable(false);				        
			stage.setScene(scene);
		} 
		else if(result.get() == buttonTypeTwo){
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText("Them de vao thu vien de thanh cong");
			alert1.showAndWait();
			moTa.clear();
		}
	}
	
	public void chonQuayLai(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/themde.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		themdeController display = Loader.getController();
		display.setTextMon(maLop.getText(), tenLop.getText());
		display.setTextTenDn(user);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Thêm đề");
		stage.setResizable(false);				        
		stage.setScene(scene);
	 }
	public void setTextLop(String ma, String ten){
		this.maLop.setText(ma);
		this.tenLop.setText(ten);
	}
	public void setTextTenDn(User u){
		user = u;
		this.tendn.setText(u.getUserName());
	}
	
	public void dangxuatClicked(){
		if (FxDialogs.showConfirm("Thông báo", "Bạn có muốn đăng xuất??", 1, "Có", "Không").equals(FxDialogs.YES)) {
			Parent pane = null;
	    	FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("../Application/signIn.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene scene = new Scene(pane);
			Stage stage = (Stage) dangxuat.getScene().getWindow();
			stage.setTitle("Đăng nhập");
			stage.setResizable(false);				        
			stage.setScene(scene);
	   }
	}
	
	public void updateUser(MouseEvent e){
		   Parent pane = null;
	    	FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("../application/suaThongTinCaNhan.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Stage stage = (Stage) iconUser.getScene().getWindow();
			suaThongTinCaNhanController display = Loader.getController();
			display.setTextTenDn(user);
			display.setPreviousPage(stage);
			display.hienKhoa();
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);		
			stage1.setTitle("Cập nhật thông tin cá nhân");
			stage1.setScene(scene);
			stage.hide();
			stage1.show();
	   };
}