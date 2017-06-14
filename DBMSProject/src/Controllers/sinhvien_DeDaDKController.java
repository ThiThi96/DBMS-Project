package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Models.DeMon;
import Models.Nhom;
import Models.User;
import application.FxDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class sinhvien_DeDaDKController implements Initializable  {

	@FXML
	private TableView<DeMon> tableDeDaDK = new TableView<DeMon>();
	@FXML
	private Button btnHuyDKDe;
	@FXML
	private Label lblDangXuat;
	@FXML
	private Label lblTaiKhoan;
	@FXML
	private Button btnQuayLai;
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private ImageView iconUser;
	@FXML
	private ComboBox<String> delay;
	
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTaiKhoan.setAlignment(Pos.TOP_RIGHT);
		delay.setValue("No delay");
		TableColumn cMaDe = new TableColumn("Ma de");
		cMaDe.setMinWidth(70);
		cMaDe.setMaxWidth(70);
		
		TableColumn cMoTa = new TableColumn("Mo ta");
		cMoTa.setMinWidth(500);
		cMoTa.setMaxWidth(500);
		
		TableColumn cLoaiDA = new TableColumn("Loai do an");
		cLoaiDA.setMinWidth(100);
		cLoaiDA.setMaxWidth(100);
		
		TableColumn cLoaiDe = new TableColumn("Loai de");
		cLoaiDe.setMinWidth(85);
		cLoaiDe.setMaxWidth(85);
		
//		TableColumn cNhom = new TableColumn("NhÃ³m");
//		cNhom.setMinWidth(100);
		
		TableColumn cGVPT = new TableColumn("Giao vien phu trach");
		cGVPT.setMinWidth(100);
		cGVPT.setMaxWidth(100);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(100);
		cDeadline.setMaxWidth(100);
		
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("de"));
		cMoTa.setCellValueFactory(new PropertyValueFactory<DeMon, String>("moTa"));
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeMon, Byte >("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeMon, String >("loaiDeHien"));
//		cNhom.setCellValueFactory(new PropertyValueFactory<DeMon, Nhom>("Nhom"));
		cDeadline.setCellValueFactory(new PropertyValueFactory<DeMon, Date>("deadline"));
		cGVPT.setCellValueFactory(new PropertyValueFactory<DeMon, User>("giaoVienPhuTrach"));
		
	    ObservableList<DeMon> list = getDeDaDKList();
	    tableDeDaDK.setItems(list);
	 
	    tableDeDaDK.getColumns().addAll(cMaDe, cMoTa, cLoaiDA, cLoaiDe, cGVPT, cDeadline);
	    tableDeDaDK.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	this.btnHuyDKDe.setDisable(false);
            	
            	//System.out.println(maNhom + "\n" + tenNhom + "\n" + nhomTruong + "\n" + soLuong);
            }
        });
	}
	
	private ObservableList<DeMon> getDeDaDKList() {
		DeMon dm1 = new DeMon("De001", "CTT001", "Cuoi ky", true, "Hom nay la mot ngay khong dep troi gi ca", 20, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Hoang Anh Tu");
		DeMon dm2 = new DeMon("De002", "CTT001", "Giua ky", true, "Troi khong nang khong mo hoho", 10, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Trần Minh Triết" );
		DeMon dm3 = new DeMon("De003", "CTT001", "Bai tap", true, "Chan qua di mat ", 20, 10, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Nguyễn Hồng" );
		DeMon dm4 = new DeMon("De004", "CTT001", "Bai tap", true, "Troi oi la troi", 10, 8, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Nguyễn Nhung" );
		return FXCollections.observableArrayList(
		        dm1, dm2, dm3, dm4
		        ); 
	}

	public void huyDKDe_Clicked(){
		
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
			Stage stage = (Stage) lblDangXuat.getScene().getWindow();
			stage.setTitle("Đăng nhâp");
			stage.setResizable(false);				        
			stage.setScene(scene);
	   }
	}
	
	public void quayLai_Clicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/sinhvienNhomDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sinhvienNhomDeController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Sinh viên");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.lblTaiKhoan.setText(u.getUserName());
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
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
			stage.setTitle("Cập nhật thông tin cá nhân");
			stage1.setScene(scene);
			stage.hide();
			stage1.show();
	   };
}
