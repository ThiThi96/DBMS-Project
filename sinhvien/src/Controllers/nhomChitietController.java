package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

import Models.Nhom;
import Models.SvNhom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class nhomChitietController implements Initializable {
	@FXML
	private Label tendn;
	@FXML
	private Label dangxuat;
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label maLop;
	@FXML
	private Label lableMaNhom;
	@FXML
	private TextField textMaNhom ;
	@FXML
	private Label tenLop;
	@FXML
	private TextField tenNhom;
	@FXML
	private Button btnQuayve;
	@FXML
	private Button btnHoantat;
	@FXML
	private Button btnThemthanhvien;
	@FXML
	private TableView<SvNhom> tableNhom;
	
	private Vector<SvNhom> thanhVienThem = new Vector<SvNhom>();
	
	private final ObservableList<SvNhom> data =
		        FXCollections.observableArrayList(
		        );   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
		tenNhom.setFocusTraversable(false);
		
		TableColumn cMasv = new TableColumn("Mã sinh viên");
		cMasv.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("maSv")
	        );
		cMasv.setMinWidth(200);
		cMasv.setMaxWidth(200);
		
		TableColumn cTenSv = new TableColumn("Tên sinh viên");
		cTenSv.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("tenSv")
	        );
		cTenSv.setMinWidth(300);
		cTenSv.setMaxWidth(300);
		
		TableColumn cNhomTruong = new TableColumn("Nhóm trưởng");
		cNhomTruong.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("nhomTruongHien")
	        );
		cNhomTruong.setMinWidth(200);
		cNhomTruong.setMaxWidth(200);
		
		tableNhom.getColumns().addAll(cMasv, cTenSv, cNhomTruong);
		
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
	}
	
	public void setTextTenDn(String ten){
		 this.tendn.setText(ten);
	}
	
	public void disableEditTenNhom(){
		this.tenNhom.setEditable(false);
	}
	
	public void setTenNhom(String ten){
		this.tenNhom.setText(ten);
	}
	
	public void setVisibleMaNhom(String ma){
		this.lableMaNhom.setVisible(true);
		this.textMaNhom.setText(ma);
		this.textMaNhom.setVisible(true);
	}
	
	public void setNhomTruong(String masv){
		data.add(new SvNhom(masv, "Ten thang dang dang nhap lay duoi csdl", true));
		thanhVienThem.add(new SvNhom(masv, "Ten tuong ung voi ma so sv nhap vao lay duoi csdl", true));
		tableNhom.setItems(data);
	}
	
	public void layThongTinNhom(String maNhom){
		//Lay duoi co so du lieu len
		SvNhom s1 = new SvNhom("Sv1","Nguyen Hoang Anh", true );
		SvNhom s2 = new SvNhom("Sv2","Tran Thu Ha ",  false );
		SvNhom s3 = new SvNhom("Sv3","Vo Ngoc Bich",  false );
		
		data.addAll(s1, s2, s3);
		tableNhom.setItems(data);
	}
	
	public void quaylaiClicked(){
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
		display.setTextTenDn(tendn.getText());
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.setNhomDefault();
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayve.getScene().getWindow();
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void themthanhvienClicked(){
		TextInputDialog dialog = new TextInputDialog("");		
		dialog.getDialogPane().getStylesheets().add(
				   getClass().getResource("../Application/sinhvien.css").toExternalForm());
		dialog.getDialogPane().getStyleClass().add("dialogThemthanhvien");
		dialog.setTitle("Thêm thành viên");
		dialog.setHeaderText("Nhập mã sinh viên");
		dialog.getDialogPane().setPrefSize(300, 180);
		
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
				//query láº¥y tÃªn dÆ°á»›i csdl
			    data.add(new SvNhom(result.get(), "Ten thang dang dang nhap lay duoi csdl", false));
			    thanhVienThem.add(new SvNhom(result.get(), "Ten tuong ung lay duoi csdl", false));
			    tableNhom.setItems(data);
			    tableNhom.refresh();
		}
	}
}
