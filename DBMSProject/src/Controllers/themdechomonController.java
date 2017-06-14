package Controllers;

import Models.LoaiDA;
import Models.LoaiDe;
import Models.User;
import application.FxDialogs;

import java.net.URL; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

public class themdechomonController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private Label lbmd;
	@FXML
	private Label maDe;
	@FXML
	private Label maMon;
	@FXML
	private ComboBox<LoaiDA> loaiDA;
	@FXML
	private ComboBox<LoaiDe> loaiDe;
	@FXML
	private TextField SLDKToiDa;
	@FXML
	private TextField SLSVNhom;
	@FXML
	private JFXDatePicker ngayBD;
	@FXML
	private JFXDatePicker ngayDeadline;
	@FXML
	private JFXTimePicker gioBD;
	@FXML
	private JFXTimePicker gioDeadline;
	@FXML
	private Button btnThemDe;
	@FXML
	private Button btnQuayLai;
	@FXML
	private ImageView iconUser;
	
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<LoaiDA> list1 = getLoaiDAList();
		tendn.setAlignment(Pos.TOP_RIGHT);
		loaiDA.setItems(list1);
		loaiDA.getSelectionModel().select(0);
		ObservableList<LoaiDe> list2 = getLoaiDeList();
		loaiDe.setItems(list2);
		loaiDe.getSelectionModel().select(0);
		
		ngayBD.setPromptText("Ngày");
		ngayDeadline.setPromptText("Giờ");
		gioBD.setPromptText("Ngày");
		gioDeadline.setPromptText("Giờ");
	}
	
	public static ObservableList<LoaiDA> getLoaiDAList() {
    	LoaiDA baitap = new LoaiDA("01", "Bai tap");
    	LoaiDA giuaky = new LoaiDA("02", "Giua ky");
    	LoaiDA cuoiky = new LoaiDA("03", "Cuoi ky");
 
        ObservableList<LoaiDA> list //
                = FXCollections.observableArrayList(baitap, giuaky, cuoiky);
 
        return list;
    }
	
	public static ObservableList<LoaiDe> getLoaiDeList() {
    	LoaiDe canhan = new LoaiDe("1", "Ca nhan");
    	LoaiDe nhom = new LoaiDe("0", "Nhom");
 
        ObservableList<LoaiDe> list //
                = FXCollections.observableArrayList(canhan, nhom);
        return list;
    }
	
	public void chonThemDe(ActionEvent event) {
		//Them de ....
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Them de thanh cong");
		alert.showAndWait();
		SLDKToiDa.clear();
		SLSVNhom.clear();
		ngayBD.setValue(null);
		ngayDeadline.setValue(null);
		gioBD.setValue(null);
		gioDeadline.setValue(null);
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
	
	public void setTextMon(String ma, String ten){
		this.maLop.setText(ma);
		this.tenLop.setText(ten);
		this.maMon.setText(ma);
	}
	
	public void setMaDe(String maD ){
		maDe.setVisible(true);
		lbmd.setVisible(true);
		maDe.setText(maD);
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