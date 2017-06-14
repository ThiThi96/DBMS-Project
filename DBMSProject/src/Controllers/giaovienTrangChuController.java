package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Mon;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class giaovienTrangChuController implements Initializable {
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label tenTK; 
	@FXML
	private Label dangxuat; 
	@FXML
	private ListView<String> listLop = new ListView<String>();
	
	private User user;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tenTK.setAlignment(Pos.TOP_RIGHT);
		Mon lop1 = new Mon("Lop01", "An toan bao mat trong HTTT" );
		Mon lop2 = new Mon("Lop02", "Bao mat co so du lieu" );
		Mon lop3 = new Mon("Lop03", "He quan tri co so du lieu" );
		Mon lop4 = new Mon("Lop04", "Ung dung phan tan" );
		
		// Tao danh sach lops
	    ObservableList<Mon> lops = FXCollections.observableArrayList(lop1, lop2, lop3, lop4);
	    // Tao danh sach lopStrings
	    ObservableList<String> lopStrings = FXCollections.observableArrayList();
	    for(int i=0; i<lops.size();i++){
	    	 
	    	lopStrings.add(lops.get(i).getMalop() + "\t\t" + lops.get(i).getTenlop() + "\n\n\n");
	    } 
	    listLop.setItems(lopStrings);
	    
	    // Chi cho phep chon 1 dong trong listview
	    listLop.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	      
	    listLop.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	    	String tam = newValue.toString();
	    	String[] parts = tam.substring(0, tam.length()-3).split("\t\t");
	    	
	    	Parent pane = null;
	    	FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("../application/giaovien_TTDe.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			giaovien_TTDeCuaLopController display = Loader.getController();
			display.setTextLop(parts[0], parts[1]);
			display.setTextTenDn(user);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) listLop.getScene().getWindow();
			stage.setResizable(false);					        
			stage.setScene(scene);
	    });

	}
	
	public void setTextTenDn(User u){
		 user = u;
		 tenTK.setText(user.getUserName());
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
			stage1.setTitle("Cập nhật thông tin cá nhân");
			stage1.setScene(scene);
			stage.hide();
			stage1.show();
	   };
	
}
