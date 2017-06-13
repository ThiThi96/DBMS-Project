package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.DeMon;
import Models.Nhom;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
	private TableView<Nhom> tableNhom = new TableView<Nhom>();
	
	Nhom n1 = new Nhom("Nhom1","Hai con vit", "sv1", 4 );
	Nhom n2 = new Nhom("Nhom2","Hai con heo", "sv2", 3 );
	Nhom n3 = new Nhom("Nhom3","Hai con cho", "sv3", 2 );
	Nhom n4 = new Nhom("Nhom4","Hai con ga ", "sv4", 5);
	
	private final ObservableList<Nhom> data =
		        FXCollections.observableArrayList(
		        n1, n2, n3, n4
		        );   
	
	String maNhomSelect, tenNhomSelect, nhomTruongSelect;
	int soLuongSelect;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
		//data.addAll(n1, n2, n3, n4);
		
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
            	maNhomSelect = tableNhom.getSelectionModel().getSelectedItem().getMaNhom();
            	tenNhomSelect = tableNhom.getSelectionModel().getSelectedItem().getTenNhom();
            	nhomTruongSelect = tableNhom.getSelectionModel().getSelectedItem().getNhomTruong();
            	soLuongSelect = tableNhom.getSelectionModel().getSelectedItem().getSoLuong();
            	
            	//System.out.println(maNhom + "\n" + tenNhom + "\n" + nhomTruong + "\n" + soLuong);
            }
        });
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
	}
	
	public void setTextTenDn(String ten){
		 this.tendn.setText(ten);
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
		display.setTextTenDn(tendn.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayve.getScene().getWindow();
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
		display.setTextTenDn(tendn.getText());
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
		display.setTextTenDn(tendn.getText());
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.disableEditTenNhom();
		display.setTenNhom(tenNhomSelect);
		display.layThongTinNhom(maNhomSelect);
		display.setVisibleMaNhom(maNhomSelect);
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
		display.setTextTenDn(tendn.getText());
		display.setNhomDefault();
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnRutnhom.getScene().getWindow();
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void dangKyDe_CLicked(){
		
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
		display.setTextTenDn(tendn.getText());
		display.setTextLop(maLop.getText(), tenLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnXemDSDeDaDK.getScene().getWindow();
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
		
	}
	
	public void tendn_Clicked(){
		
	}
	
	public void dangXuat_Clicked(){
		
	}
}
