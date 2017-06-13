package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Models.DeDaDK;
import Models.Nhom;
import Models.User;
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
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTaiKhoan.setAlignment(Pos.TOP_RIGHT);
		// TÃ¡ÂºÂ¡o cÃƒÂ¡c cÃ¡Â»â„¢t cÃ¡Â»Â§a bÃ¡ÂºÂ£ng
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
		
		// HiÃ¡Â»Æ’n thÃ¡Â»â€¹ cÃƒÂ¡c dÃƒÂ²ng dÃ¡Â»Â¯ liÃ¡Â»â€¡u
	    ObservableList<DeDaDK> list = getDeDaDKList();
	    tableDeDaDK.setItems(list);
	 
	    tableDeDaDK.getColumns().addAll(cMaDe, cMoTa, cLoaiDA, cLoaiDe, cNhom, cGVPT, cDeadline);
		
	}
	
	private ObservableList<DeDaDK> getDeDaDKList() {
		return null;
		// Danh sÃƒÂ¡ch cÃƒÂ¡c Ã„ï¿½Ã¡Â»ï¿½ Ã„â€˜ÃƒÂ£ Ã„â€˜Ã„Æ’ng kÃƒÂ½ cÃ¡Â»Â§a sinh viÃƒÂªn
		// vÃƒÂ­ dÃ¡Â»Â¥: UserAccount user1 = new UserAccount(1L, "smith", "smith@gmail.com",  "Susan", "Smith", true);
		
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
    	Loader.setLocation(getClass().getResource("../Application/sinhvienNhomDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sinhvienNhomDeController display = Loader.getController();
		display.setTextTenDn(lblTaiKhoan.getText());
		display.setTextLop(maLop.getText(), tenLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void setTextTenDn(String ten){
		 this.lblTaiKhoan.setText(ten);
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
	}
}
