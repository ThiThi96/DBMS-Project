package sinhvien;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;

public class sinhvien_DeDaDKController implements Initializable  {

	@FXML
	private TableView<DeDaDK> tableDeDaDK = new TableView<DeDaDK>();
	@FXML
	private Button btnHuyDKDe;
	@FXML
	private Label lblDangXuat;
	@FXML
	private Label lblTaiKhoan;
	@FXML
	private Button btnQuayLai;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTaiKhoan.setAlignment(Pos.TOP_RIGHT);
		// Táº¡o cÃ¡c cá»™t cá»§a báº£ng
		TableColumn cMaDe = new TableColumn("Mã đề");
		cMaDe.setMinWidth(100);
		
		TableColumn cMoTa = new TableColumn("Mô tả");
		cMoTa.setMinWidth(500);
		
		TableColumn cLoaiDA = new TableColumn("Loại đồ án");
		cLoaiDA.setMinWidth(100);
		
		TableColumn cLoaiDe = new TableColumn("Loại đề");
		cLoaiDe.setMinWidth(100);
		
		TableColumn cNhom = new TableColumn("Nhóm");
		cNhom.setMinWidth(100);
		
		TableColumn cGVPT = new TableColumn("Giáo viên phụ trách");
		cGVPT.setMinWidth(100);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(100);
		
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeDaDK, String>("maDe"));
		cMoTa.setCellValueFactory(new PropertyValueFactory<DeDaDK, String>("moTa"));
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeDaDK, Byte >("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeDaDK, Boolean >("loaiDe"));
		cNhom.setCellValueFactory(new PropertyValueFactory<DeDaDK, Nhom>("Nhom"));
		cDeadline.setCellValueFactory(new PropertyValueFactory<DeDaDK, Date>("deadLine"));
		cGVPT.setCellValueFactory(new PropertyValueFactory<DeDaDK, User>("gvPhuTrach"));
		
		// Hiá»ƒn thá»‹ cÃ¡c dÃ²ng dá»¯ liá»‡u
	    ObservableList<DeDaDK> list = getDeDaDKList();
	    tableDeDaDK.setItems(list);
	 
	    tableDeDaDK.getColumns().addAll(cMaDe, cMoTa, cLoaiDA, cLoaiDe, cNhom, cGVPT, cDeadline);
		
	}
	
	private ObservableList<DeDaDK> getDeDaDKList() {
		return null;
		// Danh sÃ¡ch cÃ¡c Ä�á»� Ä‘Ã£ Ä‘Äƒng kÃ½ cá»§a sinh viÃªn
		// vÃ­ dá»¥: UserAccount user1 = new UserAccount(1L, "smith", "smith@gmail.com",  "Susan", "Smith", true);
		
		//ObservableList<DeDaDK> list = FXCollections.observableArrayList(user1, user2, user3);
	    //return list;
	}

	public void huyDKDe_Clicked(){
		
	}
	
	public void taiKhoan_Clicked(){
		
	}
	
	public void dangXuat_Clicked(){
		
	}
	
	public void quayLai_Clicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("sinhvienNhomDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sinhvienNhomDeController display = Loader.getController();
		display.setTextTenDn(lblTaiKhoan.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void setTextTenDn(String ten){
		 this.lblTaiKhoan.setText(ten);
	}
}
