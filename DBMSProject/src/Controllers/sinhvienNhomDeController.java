package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class sinhvienNhomDeController  implements Initializable {
	@FXML
	private Label tendn;
	@FXML
	private Label dangxuat;
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private Button btnQuayve;
	@FXML
	private Button btnDangkynhom;
	@FXML
	private Button btnRutnhom;
	@FXML
	private Button btnChitietnhom;
	@FXML
	private Tab tabNhom;
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Label lblDSDeConHanDK;
	@FXML
	private TableView<DeMon> tableDeConHanDK = new TableView<DeMon>();
	@FXML
	private Button btnDangKyDe;
	@FXML
	private Button btnQuayVeTC;
	@FXML 
	private Button btnXemDSDeDaDK;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private TableView<Nhom> tableNhom = new TableView<Nhom>();
	
	private User user;
	private Nhom nhomSelect;
	private DeMon deSelect;
	
	Nhom n1 = new Nhom("Nhom1","Hai con vit", "sv1", 4 );
	Nhom n2 = new Nhom("Nhom2","Hai con heo", "sv2", 3 );
	Nhom n3 = new Nhom("Nhom3","Hai con cho", "sv3", 2 );
	Nhom n4 = new Nhom("Nhom4","Hai con gaÂ ", "sv4", 5);
	
	DeMon dm1 = new DeMon("De001", "CTT001", "Cuoi ky", true, "Hom nay la mot ngay khong dep troi gi ca", 20, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "", "");
	DeMon dm2 = new DeMon("De002", "CTT001", "Giua ky", false, "Troi khong nang khong mo hoho", 10, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "", "N001");
	DeMon dm3 = new DeMon("De003", "CTT001", "Bai tap", true, "Chan qua di mat ", 20, 10, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "", "");
	DeMon dm4 = new DeMon("De004", "CTT001", "Bai tap", false, "Troi oi la troi", 10, 8, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00", "", "N003");
	
	//Nhom ma thang dang nhap lam nhom truong
	ObservableList<Nhom> nhomTruongCua = 
		    FXCollections.observableArrayList(
		    n3, n4
		    );
	
	//Nhom
	private final ObservableList<Nhom> data =
		        FXCollections.observableArrayList(
		        n1, n2, n3, n4
		        );   
	//De
	private final ObservableList<DeMon> data_de =
	        FXCollections.observableArrayList(
	        dm1, dm2, dm3, dm4
	        );   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
		delay.setValue("No delay");
		
		TableColumn cManhom = new TableColumn("Ma nhom");
		cManhom.setMinWidth(110);
		cManhom.setMaxWidth(110);
		cManhom.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("maNhom")
        );

		TableColumn cTennhom = new TableColumn("Ten nhom");
        cTennhom.setMinWidth(335);
        cTennhom.setMaxWidth(335);
        cTennhom.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("tenNhom")
        );

        TableColumn cNhomtruong = new TableColumn("Nhom truong");
        cNhomtruong.setMinWidth(150);
        cNhomtruong.setMaxWidth(150);
        cNhomtruong.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("nhomTruong")
        );
        TableColumn cSoluong = new TableColumn("So luong thanh vien");
        cSoluong.setMinWidth(200);
        cSoluong.setMaxWidth(200);
        cSoluong.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("soLuong")
        );
		
        tableNhom.getColumns().addAll(cManhom, cTennhom, cNhomtruong, cSoluong);
        
        tableNhom.setItems(data);
        
        tableNhom.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	this.btnChitietnhom.setDisable(false);
	        	this.btnRutnhom.setDisable(false);
	        	nhomSelect = newSelection;
            	
            	//System.out.println(maNhom + "\n" + tenNhom + "\n" + nhomTruong + "\n" + soLuong);
            }
        });
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////
        TableColumn cMaDe = new TableColumn("Ma de");
		cMaDe.setMinWidth(60);
		cMaDe.setMaxWidth(60);
		
		TableColumn cMoTa = new TableColumn("Mo ta");
		cMoTa.setMinWidth(480);
		cMoTa.setMaxWidth(480);
		
		TableColumn cLoaiDA = new TableColumn("Loai do an");
		cLoaiDA.setMinWidth(90);
		cLoaiDA.setMaxWidth(90);
		
		TableColumn cLoaiDe = new TableColumn("Loai de");
		cLoaiDe.setMinWidth(70);
		cLoaiDe.setMaxWidth(70);
		
		TableColumn cNgaybd = new TableColumn("Ngay bd");
		cNgaybd.setMinWidth(130);
		cNgaybd.setMaxWidth(130);
		
//		TableColumn cGVPT = new TableColumn("Giao vien phu trach");
//		cGVPT.setMinWidth(100);
//		cGVPT.setMaxWidth(100);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(130);
		cDeadline.setMaxWidth(130);
		
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("de"));
		cMoTa.setCellValueFactory(new PropertyValueFactory<DeMon, String>("moTa"));
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeMon, Byte >("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeMon, String >("loaiDeHien"));
		cNgaybd.setCellValueFactory(new PropertyValueFactory<DeMon, String>("ngayBDDangKy"));
		cDeadline.setCellValueFactory(new PropertyValueFactory<DeMon, Date>("deadline"));
//		cGVPT.setCellValueFactory(new PropertyValueFactory<DeMon, User>("giaoVienPhuTrach"));
		
	    tableDeConHanDK.setItems(data_de);
	 
	    tableDeConHanDK.getColumns().addAll(cMaDe, cMoTa, cLoaiDA, cLoaiDe, cNgaybd, cDeadline);
	    tableDeConHanDK.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	this.btnDangKyDe.setDisable(false);
            	deSelect = newSelection;
            }
        });
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tendn.setText(u.getUserName());
	}
	
	public void disableButton(){
		this.btnChitietnhom.setDisable(true);
		this.btnRutnhom.setDisable(true);
	}
	
	public void setNhomDefault(){
		this.tabPane.getSelectionModel().select(this.tabNhom);
	}
	
	public void quaylaiClicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/sinhvien-trangchu.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sinhvienTrangchuController display = Loader.getController();
		display.setTextTenDn(user);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayve.getScene().getWindow();
		stage.setTitle("Sinh viên");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void dangkynhomClicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/nhomChitiet.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nhomChitietController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.setNhomTruong(tendn.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnDangkynhom.getScene().getWindow();
		stage.setTitle("Đăng ký nhóm");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void chitietnhomClicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/nhomChitiet.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nhomChitietController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.disableEditTenNhom();
		display.setTenNhom(nhomSelect.getTenNhom());
		display.layThongTinNhom(nhomSelect.getMaNhom());
		display.setVisibleMaNhom(nhomSelect.getMaNhom());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnDangkynhom.getScene().getWindow();
		stage.setTitle("Chi tiết nhóm");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void rutnhomClicked(){    	
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
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.setTextTenDn(user);
		display.setNhomDefault();
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnRutnhom.getScene().getWindow();
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void dangKyDe_CLicked(){
		if(deSelect.getLoaiDeHien().compareTo("Nhóm")==0){
			List<String> choices = new ArrayList<>();
			for(int i=0;i<nhomTruongCua.size(); i++)
				choices.add(nhomTruongCua.get(i).getMaNhom());

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Chon nhom");
			dialog.setHeaderText("Chon nhom muon dang ky de nay");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Your choice: " + result.get());
			}
			//Tien hanh dang ky de cho nhom
    	}            		
    	else{
    		
    		//Tien hanh dang ky de cho ca nhan
    	}
	}
	
	public void xemDSDeDaDK_Clicked(){
		Parent pane = null;
    	FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/sinhvien_DeDaDK.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sinhvien_DeDaDKController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnXemDSDeDaDK.getScene().getWindow();
		stage.setTitle("Danh sách đề đã đăng ký");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
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
			stage1.setScene(scene);
			stage.setTitle("Cập nhật thông tin cá nhân");
			stage.hide();
			stage1.show();
	   };
}
