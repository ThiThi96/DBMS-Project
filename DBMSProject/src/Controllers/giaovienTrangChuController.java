package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Lop;
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tenTK.setAlignment(Pos.TOP_RIGHT);
		Lop lop1 = new Lop("Lop01", "An toan bao mat trong HTTT" );
		Lop lop2 = new Lop("Lop02", "Bao mat co so du lieu" );
		Lop lop3 = new Lop("Lop03", "He quan tri co so du lieu" );
		Lop lop4 = new Lop("Lop04", "Ung dung phan tan" );
		
		// Tao danh sach lops
	    ObservableList<Lop> lops = FXCollections.observableArrayList(lop1, lop2, lop3, lop4);
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
	    	Loader.setLocation(getClass().getResource("giaovien_TTDe.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			giaovien_TTDeCuaLopController display = Loader.getController();
			display.setTextLop(parts[0], parts[1]);
			display.setTextTenDn(tenTK.getText());
			Scene scene = new Scene(pane);
			Stage stage = (Stage) listLop.getScene().getWindow();
			stage.setResizable(false);					        
			stage.setScene(scene);
	    });

	}
	
	public void setTextTenDn(String ten){
		 this.tenTK.setText(ten);
	}
	
}
