package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Models.DeMon;
import Models.User;
import application.FxDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class giaovien_TTDeCuaLopController implements Initializable{
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label tenTK; 
	@FXML
	private Label dangxuat;
	@FXML
	private Label lbMaLop;
	@FXML
	private Label lbTenLop;
	@FXML
	private Button btnChiTietDe;
	@FXML
	private Button btnThemDe;
	@FXML
	private Button btnQuayVe;
	@FXML
	private TableView<DeMon> tableCT_De = new TableView<DeMon>();
	
	private User user;

	DeMon dm1 = new DeMon("De001", "CTT001", "Cuoi ky", true, "Hom nay la mot ngay khong dep troi gi ca", 20, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Hoang Anh Tu");
	DeMon dm2 = new DeMon("De002", "CTT001", "Giua ky", false, "Troi khong nang khong mo hoho", 10, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Trần Minh Triết" );
	DeMon dm3 = new DeMon("De003", "CTT001", "Bai tap", true, "Chan qua di mat ", 20, 10, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Nguyễn Hồng" );
	DeMon dm4 = new DeMon("De004", "CTT001", "Bai tap", false, "Troi oi la troi", 10, 8, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "Nguyễn Nhung" );
	
	private final ObservableList<DeMon> listDe =
	        FXCollections.observableArrayList(
	        dm1, dm2, dm3, dm4
	        );   
	
	String maDeSel, maMonSel, loaiDASel, moTaSel, ngayBDSel, deadlineSel;
	DeMon deSelect;
	Boolean loaiDeSel;
	Integer slDKToiDaSel, slDaDKSel, slSVNhomSel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tenTK.setAlignment(Pos.TOP_RIGHT);
		
		TableColumn cMaDe = new TableColumn("Mã đề");
		cMaDe.setMinWidth(60);
		cMaDe.setMaxWidth(60);
		
		TableColumn cMaMon = new TableColumn("Mã môn");
		cMaMon.setMinWidth(80);
		cMaMon.setMaxWidth(80);
		
		TableColumn cLoaiDA = new TableColumn("Loại đồ án");
		cLoaiDA.setMinWidth(100);
		cLoaiDA.setMaxWidth(100);
		
		TableColumn cLoaiDe = new TableColumn("Loại đề");
		cLoaiDe.setMinWidth(80);
		cLoaiDe.setMaxWidth(80);
		
		TableColumn cMoTa = new TableColumn("Mô tả");
		cMoTa.setMinWidth(100);
		cMoTa.setMaxWidth(100);
		
		TableColumn cSLDKToiDa = new TableColumn("SLĐK TĐ");
		cSLDKToiDa.setMinWidth(80);
		cSLDKToiDa.setMaxWidth(80);
		
		TableColumn cSLDaDK = new TableColumn("SL đã đk");
		cSLDaDK.setMinWidth(80);
		cSLDaDK.setMaxWidth(80);
		
		TableColumn cSLSV_Nhom = new TableColumn("SLSV/Nhóm");
		cSLSV_Nhom.setMinWidth(100);
		cSLSV_Nhom.setMaxWidth(100);
		
		TableColumn cNgayBD = new TableColumn("Ngày bắt đầu đk");
		cNgayBD.setMinWidth(140);
		cNgayBD.setMaxWidth(140);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(140);
		cDeadline.setMaxWidth(140);
		
		// Dinh nghia cach de lay du lieu cho moi o
		// Lay gia tri tu cac thuoc tinh cua DeMon
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("de"));
		cMaMon.setCellValueFactory(new PropertyValueFactory<DeMon, String>("mon"));
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeMon, String>("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("loaiDeHien"));
		cMoTa.setCellValueFactory(new PropertyValueFactory<DeMon, String>("moTa"));
		cSLDKToiDa.setCellValueFactory(new PropertyValueFactory<DeMon, Integer>("slDangKyTD"));
		cSLDaDK.setCellValueFactory(new PropertyValueFactory<DeMon, Integer>("slDangKy"));
		cSLSV_Nhom.setCellValueFactory(new PropertyValueFactory<DeMon, Integer>("slSVNhom"));
		cNgayBD.setCellValueFactory(new PropertyValueFactory<DeMon, String>("ngayBDDangKy"));
		cDeadline.setCellValueFactory(new PropertyValueFactory<DeMon, String>("deadline"));
		
		// Hien thi cac dong du lieu
		tableCT_De.getColumns().addAll(cMaDe, cMaMon, cLoaiDA, cLoaiDe, cMoTa, cSLDKToiDa, cSLDaDK, cSLSV_Nhom, cNgayBD, cDeadline);
		
		tableCT_De.setItems(listDe);
	 
		tableCT_De.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	this.btnChiTietDe.setDisable(false);
            	deSelect = newSelection;
            }
        });
	
	}
	
	public void setTextLop(String ma, String ten){
		 this.lbMaLop.setText(ma);
		 this.lbTenLop.setText(ten);
	}
	
	public void chiTietDe_Clicked(ActionEvent event){
		Parent pane = null;
    	FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/giaovien_ChiTietDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			System.out.println("Sao lai null nua roi huhuhuhu");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovien_ChiTietDeController display = Loader.getController();
		display.setTextLop(lbMaLop.getText(), lbTenLop.getText());
		display.setTextTenDn(user);
		display.setDe(deSelect);
		
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnChiTietDe.getScene().getWindow();
		stage.setTitle("Chi tiết đề");
		stage.setResizable(false);					        
		stage.setScene(scene);
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
	
	public void themDe_Clicked(ActionEvent event){
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
		display.setTextTenDn(user);
		display.setTextMon(lbMaLop.getText(), lbTenLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setTitle("Thêm đề");
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void quayVe_Clicked(ActionEvent event){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/giaovienTrangChu.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovienTrangChuController display = Loader.getController();
		display.setTextTenDn(user);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setTitle("Giáo viên");
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tenTK.setText(u.getUserName());
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
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);				        
			stage1.setScene(scene);
			stage1.setTitle("Cập nhật thông tin cá nhân");
			stage.hide();
			stage1.show();
	   };
	
}

