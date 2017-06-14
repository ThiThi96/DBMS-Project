package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class signInController implements Initializable{
	@FXML
	private TextField username;
	@FXML
	private PasswordField pass;
	@FXML
	private Button btnSignin;
	@FXML
	private Button btnExit;
	
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	
	public void btnSigninClicked (){
		//user = lay duoi csdl
		Parent pane = null;
    	FXMLLoader Loader = new FXMLLoader();
		if(username.getText().compareTo("0")== 0 ){
	    	Loader.setLocation(getClass().getResource("../Application/adminPage.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adminPageController display = Loader.getController();
			//display.setUser(user);
			display.setUser(new User(username.getText(), "Thằng Admin", "", "012563577552", "245 HCM", "blabla@gmail.com"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnSignin.getScene().getWindow();
			stage.setTitle("Admin");
			stage.setResizable(false);			        
			stage.setScene(scene);
		}
		else if (username.getText().compareTo("1")== 0){
	    	Loader.setLocation(getClass().getResource("../Application/giaovienTrangChu.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			giaovienTrangChuController display = Loader.getController();
			//display.setTextTenDn(user);
			display.setTextTenDn(new User(username.getText(), "Giáo viên", "", "012563577552", "245 DN", "gv@gmail.com"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnSignin.getScene().getWindow();
			stage.setTitle("Giáo viên");
			stage.setResizable(false);			        
			stage.setScene(scene);
		}
		else if(username.getText().compareTo("2")== 0){
			Loader.setLocation(getClass().getResource("../Application/sinhvien-trangchu.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sinhvienTrangchuController display = Loader.getController();
			//display.setTextTenDn(user);
			display.setTextTenDn(new User(username.getText(), "Sinh viên", "14", "012563577552", "245 DL", "sv@gmail.com"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnSignin.getScene().getWindow();
			stage.setTitle("Sinh viên");
			stage.setResizable(false);			        
			stage.setScene(scene);
		}
		else{
			JFrame frame = new JFrame("dangnhap");
			frame.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(frame, "Tên đăng nhập hoặc mật khẩu không chính xác!!");
		}
	}
	
	public void btnEixitClicked(){
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}
}
