package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import Models.DeMon;
import Models.Nhom;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class giaovien_ChiTietDeController implements Initializable {
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
	private TextField txtMaDe = new TextField();
	@FXML
	private TextField txtLoaiDe = new TextField();
	@FXML
	private TextArea txtMoTa = new TextArea();
	@FXML
	private JFXDatePicker ngayBD;
	@FXML
	private JFXDatePicker ngayDeadline;
	@FXML
	private JFXTimePicker gioBD;
	@FXML
	private JFXTimePicker gioDeadline;
	@FXML
	private TextField txtSLSV_Nhom = new TextField();
	@FXML
	private TextField txtSLDKToiDa = new TextField();
	@FXML
	private TextField txtSLDaDK = new TextField();
	@FXML
	private TextField txtSLDangQuanLy = new TextField();
	@FXML
	private Button btnQuayVe;
	@FXML
	private Button luuThayDoi;
	@FXML
	private TableView<Nhom> tableDS_Nhom = new TableView<Nhom>();
	@FXML
	private TableView<User> tableDS_SV = new TableView<User>();
	
	private User user;
	private DeMon deLuu;
	
	Nhom n1 = new Nhom("Nhom1","Hai con vit", "sv1", 4 );
	Nhom n2 = new Nhom("Nhom2","Hai con heo", "sv2", 3 );
	Nhom n3 = new Nhom("Nhom3","Hai con cho", "sv3", 2 );
	Nhom n4 = new Nhom("Nhom4","Hai con ga", "sv4", 5);
	
	User u1 = new User("SV1","Thang thu nhat", "", "","","", 2 );
	User u2 = new User("SV2", "Ten dua thu 2",  "", "","","" , 2);
	User u3 = new User("Sv3","Ten dua thu 3", "", "","","", 2 );
	User u4 = new User("SV4","Ten dua thu 4",  "", "","","", 2 );
	
	//Nhom dang ky de ma giao vien quan ly
	private final ObservableList<Nhom> data_Nhom =
		        FXCollections.observableArrayList(
		        n1, n2, n3, n4
		        );   
	
	//SV dang ky de ma giao vien quan ly
	private final ObservableList<User> data_SV =
	        FXCollections.observableArrayList(
	        u1, u2, u3, u4
	        );   
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tenTK.setAlignment(Pos.TOP_RIGHT);
		
	}
	
	public void quayVe_Clicked(ActionEvent event){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/giaovien_TTDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovien_TTDeCuaLopController display = Loader.getController();
		display.setTextLop(lbMaLop.getText(), lbTenLop.getText());
		display.setTextTenDn(user);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setTitle("Giáo viên");
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void setTextLop(String ma, String ten){
		 this.lbMaLop.setText(ma);
		 this.lbTenLop.setText(ten);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tenTK.setText(u.getUserName());
	}
	
	public void setDe(DeMon de){
		this.txtMaDe.setText(de.getDe());
		this.txtLoaiDe.setText(de.getLoaiDeHien());
		this.txtMoTa.setText(de.getMoTa());
		this.txtSLSV_Nhom.setText(Integer.toString(de.getSlSVNhom()));
		this.txtSLDKToiDa.setText(Integer.toString(de.getSlDangKyTD()));
		this.txtSLDaDK.setText(Integer.toString(de.getSlDangKy()));
		this.ngayBD.setPromptText(de.getNgayBDDangKy());
		this.ngayDeadline.setPromptText(de.getDeadline());
		deLuu = de;
		
		if(de.isLoaiDe()){
			TableColumn cMasv = new TableColumn("MSSV");
			
			cMasv.setMinWidth(150);
			cMasv.setMaxWidth(150);
			cMasv.setCellValueFactory(
	            new PropertyValueFactory<User,String>("userID")
	        );

			TableColumn cTensv = new TableColumn("Tên sinh viên");
			cTensv.setMinWidth(390);
			cTensv.setMaxWidth(390);
			cTensv.setCellValueFactory(
	            new PropertyValueFactory<User,String>("userName")
	        );
			
	        tableDS_SV.setVisible(true);
	        tableDS_SV.getColumns().addAll(cMasv, cTensv);
	        tableDS_SV.setItems(data_SV);
	   
		}
		else{
			TableColumn cManhom = new TableColumn("Mã nhóm");
			cManhom.setMinWidth(90);
			cManhom.setMaxWidth(90);
			cManhom.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("maNhom")
	        );

			TableColumn cTennhom = new TableColumn("Tên nhóm");
	        cTennhom.setMinWidth(200);
	        cTennhom.setMaxWidth(200);
	        cTennhom.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("tenNhom")
	        );

	        TableColumn cNhomtruong = new TableColumn("Nhóm trưởng");
	        cNhomtruong.setMinWidth(100);
	        cNhomtruong.setMaxWidth(100);
	        cNhomtruong.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("nhomTruong")
	        );
	        TableColumn cSoluong = new TableColumn("Số lượng thành viên");
	        cSoluong.setMinWidth(150);
	        cSoluong.setMaxWidth(150);
	        cSoluong.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("soLuong")
	        );
			
	        tableDS_Nhom.setVisible(true);
	        tableDS_Nhom.getColumns().addAll(cManhom, cTennhom, cNhomtruong, cSoluong);
	        tableDS_Nhom.setItems(data_Nhom);
		}
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
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);				        
			stage1.setScene(scene);
			stage1.setTitle("Cập nhật thông tin cá nhân");
			stage.hide();
			stage1.show();
	   };
}




