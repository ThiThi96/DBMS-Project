package Controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Models.SvNhom;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class suaThongTinCaNhanController implements Initializable {
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat;
	@FXML
	private Label lbKhoa;
	
	@FXML
	private TextField textMaNguoiDung;
	@FXML
	private TextField textKhoa;
	@FXML
	private TextField textHoTen;
	@FXML
	private TextField textMatKhau;
	@FXML
	private TextField textSDT;
	@FXML
	private TextField textEmail;
	@FXML
	private TextArea textDiaChi;
	
	@FXML
	private Button btnLuuChinhSua;
	@FXML
	private Button btnQuayVe;
	@FXML
	private Button btnDoiMatKhau;
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
		
		
	}
	
	public void anKhoa(){
		lbKhoa.setVisible(false);
		textKhoa.setVisible(false);
	}
	
	public void setTextTenDn(String ten){
		 this.tendn.setText(ten);
	}
	
	
	public void btnDoiMatKhauClick(){
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Đổi mật khẩu");
		dialog.setHeaderText("Đổi mật khẩu");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Hoàn tất", ButtonData.OK_DONE);
		ButtonType loginButtonCancel = new ButtonType("Hủy", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, loginButtonCancel);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		PasswordField passCu = new PasswordField();
		passCu.setPromptText("Mật khẩu cũ");
		PasswordField passMoi = new PasswordField();
		passMoi.setPromptText("Mật khẩu mới");

		grid.add(new Label("Nhập mật khẩu cũ:"), 0, 0);
		grid.add(passCu, 1, 0);
		grid.add(new Label("Nhập mật khẩu mới:"), 0, 1);
		grid.add(passMoi, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		passCu.textProperty().addListener((observable, oldValue, newValue) -> {
			passMoi.textProperty().addListener((observable1, oldValue1, newValue1) -> {
				if(!newValue.trim().isEmpty() && !newValue1.trim().isEmpty())
					loginButton.setDisable(false);
				else
					loginButton.setDisable(true);
			});
		});
		
		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> passCu.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(passCu.getText(), passMoi.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pass -> {
		    System.out.println("Cũ = " + pass.getKey() + ", Mới = " + pass.getValue());
		});
	}
	
}
