package giaovien;

import giaovien.LoaiDA;
import giaovien.LoaiDe;
import java.net.URL; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class themdechomonController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maMon;
	@FXML
	private Label maDe;
	@FXML
	private ComboBox<LoaiDA> loaiDA;
	@FXML
	private ComboBox<LoaiDe> loaiDe;
	@FXML
	private TextField SLDKToiDa;
	@FXML
	private TextField SLSVNhom;
	@FXML
	private DatePicker NgayBD;
	@FXML
	private DatePicker Deadline;
	@FXML
	private Button btnThemDe;
	@FXML
	private Button btnQuayLai;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<LoaiDA> list1 = getLoaiDAList();
		loaiDA.setItems(list1);
		loaiDA.getSelectionModel().select(0);
		
		ObservableList<LoaiDe> list2 = getLoaiDeList();
		loaiDe.setItems(list2);
		loaiDe.getSelectionModel().select(0);
	}
	
	public static ObservableList<LoaiDA> getLoaiDAList() {
    	LoaiDA baitap = new LoaiDA("01", "Bài tập");
    	LoaiDA giuaky = new LoaiDA("02", "Giữa kỳ");
    	LoaiDA cuoiky = new LoaiDA("03", "Cuối kỳ");
 
        ObservableList<LoaiDA> list //
                = FXCollections.observableArrayList(baitap, giuaky, cuoiky);
 
        return list;
    }
	
	public static ObservableList<LoaiDe> getLoaiDeList() {
    	LoaiDe canhan = new LoaiDe("1", "Cá nhân");
    	LoaiDe nhom = new LoaiDe("0", "Nhóm");
 
        ObservableList<LoaiDe> list //
                = FXCollections.observableArrayList(canhan, nhom);
        return list;
    }
	
	public void chonThemDe(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Đề đã được thêm vào môn học!");
		alert.showAndWait();
	}
	
	public void chonQuayLai(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("themde.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		themdeController display = Loader.getController();
		display.setTextMon(maMon.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Quay lai");
		stage.setResizable(false);				        
		stage.setScene(scene);
	 }
	
	public void setTextMon(String ma){
		 this.maMon.setText(ma);
	}
	
	public void setTextTenDn(String ten){
		 this.tendn.setText(ten);
	}

	
}