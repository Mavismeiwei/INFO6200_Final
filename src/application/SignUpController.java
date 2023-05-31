package application;

import java.io.IOException;

import Dialog.AlertDialog;
import backend.service.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {
	

	public Stage stage;
	public Scene scene;
	public Parent root;
	
	  @FXML
	    private CheckBox btnAgree;

	    @FXML
	    private TextField txtEmail;

	    @FXML
	    private TextField txtFirstName;

	    @FXML
	    private TextField txtLastName;

	    @FXML
	    private TextField txtNum;

	    @FXML
	    private PasswordField txtPassword;

	    @FXML
	    private TextField txtUserName;
	    
	    UserServiceImpl us = new UserServiceImpl();

	    
	    public void switchToLogin(ActionEvent event) throws IOException {
	    	String username = txtUserName.getText();
	    	String password = txtPassword.getText();
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			String email = txtEmail.getText();
			int dailyWordsNum = Integer.parseInt(txtNum.getText());
			try {
			boolean createUserResult = us.createUser(username, password, firstName, lastName, email, dailyWordsNum);
			if(createUserResult==true) {
				AlertDialog.displayAlert("info", "Register Successfully！");
	    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();}}catch(Exception e) {
	    		Alert alert = new Alert(Alert.AlertType.ERROR);
	    	    alert.setTitle("Attention");
	    	    alert.setHeaderText("Wrong");
	    	    alert.setContentText(e.getMessage());
	    	    alert.showAndWait();
	    	}
	    }
	    
	    public void alreadyRegister(ActionEvent event) throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();}
}
