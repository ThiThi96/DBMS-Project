package sinhvien;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.event.ChangeListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class sinhvienTrangchuController implements Initializable {
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private ListView<String> listLop = new ListView<String>();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
		Lop lop1 = new Lop("Lop01", "An toan bao mat trong HTTT" );
		Lop lop2 = new Lop("Lop02", "Ung dung phan tan" );
		Lop lop3 = new Lop("Lop03", "Bao mat co so du lieu" );
		Lop lop4 = new Lop("Lop04", "He quan tri co so du lieu" );
		
		//Danh sach lop Lop
	    ObservableList<Lop> lops = FXCollections.observableArrayList(lop1, lop2, lop3, lop4);
	    //Danh sach lop String
	    ObservableList<String> lopStrings = FXCollections.observableArrayList();
	    for(int i=0; i<lops.size();i++){
	    	 
	    	lopStrings.add(lops.get(i).getMalop() + "\t\t" + lops.get(i).getTenlop() + "\n\n\n");
	    } 
	    listLop.setItems(lopStrings);
	    
	    // Chi cho phep chon 1 dong trong listview
	    listLop.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	      
	    listLop.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        //System.out.println("ListView Selection Changed (selected: " + newValue.toString() + ")");
	    	String tam = newValue.toString();
	    	String[] parts = tam.substring(0, tam.length()-3).split("\t\t");
	    	
	    	Parent pane = null;
	    	FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("sinhvienNhomDe.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sinhvienNhomDeController display = Loader.getController();
			display.setTextLop(parts[0], parts[1]);
			display.setTextTenDn(tendn.getText());
			Scene scene = new Scene(pane);
			Stage stage = (Stage) listLop.getScene().getWindow();
			stage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("sinhvien.css").toExternalForm());					        
			stage.setScene(scene);
	    });

	}
	
	public void setTextTenDn(String ten){
		 this.tendn.setText(ten);
	}
	
}