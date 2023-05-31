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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    UserServiceImpl us = new UserServiceImpl();	
	
	private Stage stage;
	private Scene scene;
	private Parent parent;
	public static int UserID;
	
	  @FXML
	    private PasswordField txtPassword;

	    @FXML
	    private TextField txtUserName;
	    
	    public void switchToMain(ActionEvent event) throws IOException {
	    	
	    	String username = txtUserName.getText();
			String inputPWD = txtPassword.getText();
			try {
			int userID = us.loginCheck(username, inputPWD);
			System.out.println(userID);
			if(userID !=-999) {
				UserID=userID;
				AlertDialog.displayAlert("Welcome", "Login Successfully!");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		    	Parent root = loader.load();
		    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	      	    scene = new Scene(root);
	    	    stage.setScene(scene);
	    	    stage.show();
			}}catch(Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
			    alert.setTitle("Attention");
			    alert.setHeaderText("Wrong");
			    alert.setContentText(e.getMessage());
			    alert.showAndWait();
	    }}
	    
	    public void switchToSignUp(ActionEvent event) throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
	    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
	    }
	    
}
