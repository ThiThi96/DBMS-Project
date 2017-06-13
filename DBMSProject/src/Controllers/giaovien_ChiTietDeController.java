package Controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Models.Nhom;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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
	private Label lbMaDe;
	@FXML
	private TextField txtMaDe = new TextField();
	@FXML
	private Label lbMon;
	@FXML
	private TextField txtMaMon = new TextField();
	@FXML
	private Label lbMoTa;
	@FXML
	private TextArea txtMoTa = new TextArea();
	@FXML
	private Label lbDeadline;
	@FXML
	private DatePicker dpkDeadline = new DatePicker();
	@FXML
	private Label lbNgayBD;
	@FXML
	private DatePicker dpkNgayBD = new DatePicker();
	@FXML
	private Label lbSLSV_Nhom;
	@FXML
	private TextField txtSLSV_Nhom = new TextField();
	@FXML
	private Label lbSLDKToiDa;
	@FXML
	private TextField txtSLDKToiDa = new TextField();
	@FXML
	private Label lbSLDaDK;
	@FXML
	private TextField txtSLDaDK = new TextField();
	
	@FXML
	private Button btnQuayVe;
	@FXML
	private Label lbDSNhom;
	@FXML
	private TableView<Nhom> tableDS_Nhom = new TableView<Nhom>();
	Nhom n1 = new Nhom("Nhom1","Hai con vịt", "sv1", 4 );
	Nhom n2 = new Nhom("Nhom2","Hai con heo", "sv2", 3 );
	Nhom n3 = new Nhom("Nhom3","Hai con chó", "sv3", 2 );
	Nhom n4 = new Nhom("Nhom4","Hai con gà", "sv4", 5);
	
	private final ObservableList<Nhom> data =
		        FXCollections.observableArrayList(
		        n1, n2, n3, n4
		        );   
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tenTK.setAlignment(Pos.TOP_RIGHT);

		dpkDeadline.setValue(LocalDate.of(2017, 6, 30));
		dpkNgayBD.setValue(LocalDate.of(2017, 5, 25));
			
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
		
        tableDS_Nhom.getColumns().addAll(cManhom, cTennhom, cNhomtruong, cSoluong);
        
        tableDS_Nhom.setItems(data);
		
	}
	
	public void quayVe_Clicked(ActionEvent event){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("giaovien_TTDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovien_TTDeCuaLopController display = Loader.getController();
		display.setTextTenDn(tenTK.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void setTextLop(String ma, String ten){
		 this.lbMaLop.setText(ma);
		 this.lbTenLop.setText(ten);
	}
	
	public void setTextTenDn(String ten){
		 this.tenTK.setText(ten);
	}
	
	public void setTextMaDe(String maDe){
		this.txtMaDe.setText(maDe);
	}
	
	public void setTextMaMon(String maMon){
		this.txtMaMon.setText(maMon);
	}
	
	public void setTextMoTa(String moTa){
		this.txtMoTa.setText(moTa);
	}
	
	public void setTextSLSVNhom(String slSVNhom){
		this.txtSLSV_Nhom.setText(slSVNhom);
	}
	
	public void setTextslDKToiDa(String slDKToiDa){
		this.txtSLDKToiDa.setText(slDKToiDa);
	}
	
	public void setTextSLDaDK(String slDaDK){
		this.txtSLDaDK.setText(slDaDK);
	}
	
	
	
}




