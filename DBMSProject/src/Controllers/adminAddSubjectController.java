package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class adminAddSubjectController implements Initializable{
	
	@FXML
	private Label lblTitle;
	@FXML
	private TextField txtID;
	@FXML
	private TextField txtName;
	@FXML
	private ComboBox<String> delay;
	
	public void createSubject(MouseEvent e){
		
	}
	
	public void setTitle(String tit)
	{
		lblTitle.setText(tit);;
	}
	
	public void setID(String id)
	{
		txtID.setText(id);
	}
	
	
	public void goBack(MouseEvent e){
	    Node source = (Node) e.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		txtID.setDisable(true);
		delay.setValue("No delay");
	}

}
