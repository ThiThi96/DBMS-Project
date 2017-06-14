package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import Models.Subject;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.FxDialogs;

public class adminPageController implements Initializable {
	

	   @FXML
	   private Button btnBack;
	   @FXML
	   private Button btnLock;
	   @FXML
	   private Button btnAdd;
	   @FXML
	   private Label userPic;
	   @FXML
	   private TableView<User> tblUser;
	   @FXML
	   private TableColumn<User, String> idCol;
	   @FXML
	   private TableColumn<User, String> nameCol;
	   @FXML
	   private TableColumn<User, String> dobCol;
	   @FXML
	   private TableColumn<User, String> telCol;
	   @FXML
	   private TableColumn<User, String> addCol;
	   @FXML
	   private TableColumn<User, String> emCol;
	   @FXML
	   private Label uName;
	   @FXML
	   private Label logOut;
	   @FXML
	   private TableView<Subject> tblSubject;
	   @FXML
	   private TableColumn<Subject, String> subIDCol;
	   @FXML
	   private TableColumn<Subject, String> subNameCol;
	   @FXML
	   private Button deleteSub;
	   @FXML
	   private Button openSub;
	   @FXML
	   private Button updateSub;
	   
	   private User user;
	   private Stage previousPage;
	   
	  // private Stage stage = (Stage) tblUser.getScene().getWindow();
	   
	   public void goBack(MouseEvent e){
			previousPage.show();
	   };
	   
	   public void deactiveUser(MouseEvent e){
		   if (FxDialogs.showConfirm("Nguy hiểm", "Bạn có muốn khóa tài khoản này??", 0, "Có", "Không").equals(FxDialogs.YES)) {
		       
		   }
	   };
	   
	   public void addUser(MouseEvent e){

		   try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("../application/adminAddUser.fxml"));
		        
		        Parent root1 = (Parent) fxmlLoader.load();
		        adminAddUserController con = fxmlLoader.getController();
		        Stage stage1 = new Stage();
		        stage1.setScene(new Scene(root1));  
		        stage1.initModality(Modality.APPLICATION_MODAL);
		        stage1.show();
		        } catch(Exception e1) {
		           e1.printStackTrace();
		          }
		   
	   };
	   
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
			Stage stage = (Stage) uName.getScene().getWindow();
			suaThongTinCaNhanController display = Loader.getController();
			display.setTextTenDn(user);
			display.setPreviousPage(stage);
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);				        
			stage1.setScene(scene);
			stage.hide();
			stage1.show();
	   };

	   
	   //môn học
	   public void deleteSubject(MouseEvent e)
	   {
		   if (FxDialogs.showConfirm("Nguy hiểm", "Bạn có muốn khóa môn học này??", 0, "Có", "Không").equals(FxDialogs.YES)) {
		       
		   }
	   }
	   
	   public void updateSubject(MouseEvent e)
	   {
		   Subject item =tblSubject.getSelectionModel().getSelectedItem();
		   System.out.println(item.getSubjectID());
		   try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../application/adminAddSubject.fxml"));
		        //fxmlLoader.setLocation(getClass().getResource("../application/adminAddSubject.fxml"));

		        Parent root3 = (Parent) fxmlLoader.load();
		        adminAddSubjectController con = fxmlLoader.getController();
		        con.setID(item.getSubjectID());
		        con.setTitle("Chỉnh sửa môn học");
		        Stage stage3 = new Stage();
		        stage3.setTitle("Chỉnh sửa môn học");
		        stage3.setScene(new Scene(root3));  
		        stage3.initModality(Modality.APPLICATION_MODAL);
		        stage3.show();
		        } catch(Exception e1) {
		           e1.printStackTrace();
		          }
		   
	   }
	   
	   public void openSubject(MouseEvent e)
	   {
		   try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("../application/adminAddSubject.fxml"));
		        
		        Parent root2 = (Parent) fxmlLoader.load();
		        adminAddSubjectController con = fxmlLoader.getController();
		        Stage stage2 = new Stage();
		        stage2.setScene(new Scene(root2));  
		        stage2.setTitle("Thêm môn học");
		        stage2.initModality(Modality.APPLICATION_MODAL);
		        stage2.show();
		        } catch(Exception e1) {
		           e1.printStackTrace();
		          }
	   }
	   
	   public ObservableList<User> getUserList(){
		   User user1 = new User("hoho", "haha", "hihi", "huc huc", "hic hic", "hô hô");
		   User user2 = new User("hoho1", "haha", "hihi", "huc huc", "hic hic", "haha");
		   Vector<User> u= new Vector<User>();
		   u.add(user1);
		   u.add(user2);
		      ObservableList<User> list = FXCollections.observableArrayList(u);
		      return list;
	   };
	   
	   public ObservableList<Subject> getSubjectList(){
		   Subject sub1 = new Subject("1", "Giáo dục cô dâu");
		   Subject sub2 = new Subject("2", "Bộ môn mệt mỏi học");
		      ObservableList<Subject> list = FXCollections.observableArrayList(sub1, sub2);
		      return list;
	   };
	   
	   public void lgOut(MouseEvent e){
		   if (FxDialogs.showConfirm("Thông báo", "Bạn có muốn đăng xuất??", 1, "Có", "Không").equals(FxDialogs.YES)) {
			   Parent pane = null;
		    	FXMLLoader Loader = new FXMLLoader();
		    	Loader.setLocation(getClass().getResource("../Application/signIn.fxml"));
				try {
					pane = Loader.load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scene scene = new Scene(pane); 
				Node source = (Node) e.getSource();
			    Stage stage = (Stage) source.getScene().getWindow();
			    stage.setResizable(false);				        
				stage.setScene(scene);
		   }
	   };
	   

	   public void setUser(User u)
	   {
		   user = u;
		   uName.setText(user.getUserName());
	   };
	   
	   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		ObservableList<User> list = getUserList();
		
		
		tblUser.setItems(list);
		
		ObservableList<Subject> list1 = getSubjectList();
		tblSubject.setItems(list1);
		
		btnLock.setDisable(true);
		tblUser.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			btnLock.setDisable(false);  
		});
		//tblUser.getColumns().addAll(idCol, nameCol, dobCol, telCol, addCol, emCol);
		deleteSub.setDisable(true);
		updateSub.setDisable(true);
		tblSubject.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			updateSub.setDisable(false);
			deleteSub.setDisable(false);  
		});
		
	}
}
