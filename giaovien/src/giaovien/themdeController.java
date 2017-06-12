package giaovien;

import java.net.URL; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import giaovien.De;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class themdeController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maMon;
	@FXML
	private TableView<De> tableDSDe;
	@FXML
	private TableColumn<De, String > maDe;
	@FXML
	private TableColumn<De, String> moTa;
	@FXML
	private Button btnTaoDeMoi;
	@FXML
	private Button btnThemDeChoMon;
	@FXML
	private Button btnQuayLai;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		maDe.setCellValueFactory(new PropertyValueFactory<De, String>("maDe"));
		moTa.setCellValueFactory(new PropertyValueFactory<De, String>("moTa"));
		ObservableList<De> list = getDeList();
	    tableDSDe.setItems(list);
	}
	
	public static ObservableList<De> getDeList() {
		De De001 = new De("De001", "Hệ quản trị CSDL");
		De De002 = new De("De002", "An toàn bảo mật HTTT");
		De De003 = new De("De003", "Ứng dụng phân tán");
	 
	    ObservableList<De> list = FXCollections.observableArrayList(De001, De002, De003);
	    return list;
	}
	
	public void setTextMon(String ma){
		 this.maMon.setText(ma);
	}
	
	public void setTextTenDn(String ten){
		 this.tendn.setText(ten);
	}
	
	public void chonTaoDeMoi(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("taodemoi.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		taodemoiController display = Loader.getController();
		display.setTextMon(maMon.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnTaoDeMoi.getScene().getWindow();
		stage.setTitle("Tạo đề mới");
		stage.setResizable(false);				        
		stage.setScene(scene);
	}
	
	public void chonThemDeChoMon(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("themdechomon.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		themdechomonController display = Loader.getController();
		display.setTextMon(maMon.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnThemDeChoMon.getScene().getWindow();
		stage.setTitle("Thêm đề cho môn");
		stage.setResizable(false);				        
		stage.setScene(scene);
	}
	
	
	public void chonQuayLai(ActionEvent event) {
/*		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("giaovien_TTDeCuaLop.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovien_TTDeCuaLopController display = Loader.getController();
		display.setTextMon(maMon.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Quay lai");
		stage.setResizable(false);				        
		stage.setScene(scene);
*/	}
	
}