package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Models.DeMon;
import Models.DeMon1;
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
	private TableView<DeMon1> tableCT_De = new TableView<DeMon1>();

	DeMon1 dm1 = new DeMon1("De001", "CTT001", "Cuối kỳ", true, "Hệ thống quản lí sinh viên 1", 20, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00" );
	DeMon1 dm2 = new DeMon1("De002", "CTT001", "Giữa kỳ", true, "Hệ thống quản lí sinh viên 2", 10, 5, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00" );
	DeMon1 dm3 = new DeMon1("De003", "CTT001", "Bài tập", true, "Hệ thống quản lí sinh viên 3", 20, 10, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00" );
	DeMon1 dm4 = new DeMon1("De004", "CTT001", "Bài tập", true, "Hệ thống quản lí sinh viên 4", 10, 8, 7, "25-05-2017 20:00:00", "30-06-2017 23:55:00" );
	
	private final ObservableList<DeMon1> listDe =
	        FXCollections.observableArrayList(
	        dm1, dm2, dm3, dm4
	        );   
	
	String maDeSel, maMonSel, loaiDASel, moTaSel, ngayBDSel, deadlineSel;
	Boolean loaiDeSel;
	Integer slDKToiDaSel, slDaDKSel, slSVNhomSel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tenTK.setAlignment(Pos.TOP_RIGHT);
		
		// Tạo các cột cho bảng
		TableColumn cMaDe = new TableColumn("Mã đề");
		cMaDe.setMinWidth(100);
		cMaDe.setMinWidth(100);
		
		TableColumn cMaMon = new TableColumn("Mã môn học");
		cMaMon.setMinWidth(100);
		cMaMon.setMinWidth(100);
		
		TableColumn cLoaiDA = new TableColumn("Loại đồ án");
		cLoaiDA.setMinWidth(100);
		cLoaiDA.setMinWidth(100);
		
		TableColumn cLoaiDe = new TableColumn("Loại đề");
		cLoaiDe.setMinWidth(100);
		cLoaiDe.setMinWidth(100);
		
		TableColumn cMoTa = new TableColumn("Mô tả");
		cMoTa.setMinWidth(100);
		cMoTa.setMinWidth(100);
		
		TableColumn cSLDKToiDa = new TableColumn("Số lượng ĐK tối đa");
		cSLDKToiDa.setMinWidth(100);
		cSLDKToiDa.setMinWidth(100);
		
		TableColumn cSLDaDK = new TableColumn("Số lượng đã ĐK");
		cSLDaDK.setMinWidth(100);
		cSLDaDK.setMinWidth(100);
		
		TableColumn cSLSV_Nhom = new TableColumn("SLSV/Nhóm");
		cSLSV_Nhom.setMinWidth(100);
		cSLSV_Nhom.setMinWidth(100);
		
		TableColumn cNgayBD = new TableColumn("Ngày bắt đầu");
		cNgayBD.setMinWidth(100);
		cNgayBD.setMinWidth(100);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(100);
		cDeadline.setMinWidth(100);
		
		// Dinh nghia cach de lay du lieu cho moi o
		// Lay gia tri tu cac thuoc tinh cua DeMon
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("de"));
		cMaMon.setCellValueFactory(new PropertyValueFactory<DeMon, String>("mon"));
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeMon, String>("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeMon, Boolean>("loaiDe"));
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
            	maDeSel = tableCT_De.getSelectionModel().getSelectedItem().getDe();
            	maMonSel = tableCT_De.getSelectionModel().getSelectedItem().getMon();
            	loaiDASel = tableCT_De.getSelectionModel().getSelectedItem().getLoaiDA();
            	loaiDeSel = tableCT_De.getSelectionModel().getSelectedItem().isLoaiDe();
            	moTaSel = tableCT_De.getSelectionModel().getSelectedItem().getMoTa();
            	slDKToiDaSel = tableCT_De.getSelectionModel().getSelectedItem().getSlDangKyTD();
            	slDaDKSel = tableCT_De.getSelectionModel().getSelectedItem().getSlDangKy();
            	slSVNhomSel = tableCT_De.getSelectionModel().getSelectedItem().getSlSVNhom();
            	ngayBDSel = tableCT_De.getSelectionModel().getSelectedItem().getNgayBDDangKy();
            	deadlineSel = tableCT_De.getSelectionModel().getSelectedItem().getDeadline();
            	
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
    	Loader.setLocation(getClass().getResource("giaovien_ChiTietDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovien_ChiTietDeController display = Loader.getController();
		display.setTextLop(lbMaLop.getText(), lbTenLop.getText());
		display.setTextTenDn(tenTK.getText());
		display.setTextMaDe(maDeSel);
		display.setTextMaMon(maMonSel);
		display.setTextMoTa(moTaSel);
		String s1 = String.valueOf(slSVNhomSel);
		display.setTextSLSVNhom(s1);
		String s2 = String.valueOf(slDKToiDaSel);
		display.setTextslDKToiDa(s2);
		String s3 = String.valueOf(slDaDKSel);
		display.setTextSLDaDK(s3);
		
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnChiTietDe.getScene().getWindow();
		stage.setTitle("Chi tiết đề của lớp");
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void tenTK_Clicked(){
		
	}
	
	public void dangXuat_Clicked(){
		
	}
	
	public void themDe_Clicked(ActionEvent event){
		
	}
	
	public void quayVe_Clicked(ActionEvent event){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("giaovienTrangChu.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovienTrangChuController display = Loader.getController();
		display.setTextTenDn(tenTK.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void setTextTenDn(String ten){
		 this.tenTK.setText(ten);
	}
	
}

