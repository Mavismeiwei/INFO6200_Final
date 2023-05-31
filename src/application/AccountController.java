package application;

import java.io.IOException;
import java.util.List;

import javax.swing.event.ChangeListener;

import Dialog.AlertDialog;
import backend.service.UserServiceImpl;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AccountController {
	
	private Stage stage;
    private Scene scene;
    private Parent root;
    
   int userID =LoginController.UserID;
   
    @FXML
    private ImageView disPwd;

    @FXML
    private ImageView hidPwd;
    
    @FXML 
    private Button updateBtn;

    @FXML
    private TextField txt_firstName;

    @FXML
    private TextField txt_lastName;
    
    @FXML
    private TextField txt_userName;
    
    @FXML
    private PasswordField txt_password;
    
    @FXML
    private TextField txt_passwordField;
    
    @FXML
    private TextField txt_email;
    
    @FXML
    private TextField txt_dailyWordsNum;
    
	public void setText(String text) {
		
		UserServiceImpl us = new UserServiceImpl();
		List UserInfo = us.queryUser(userID);
		
		txt_userName.setText(UserInfo.get(2).toString());
		txt_password.setText(UserInfo.get(3).toString());
		txt_firstName.setText(UserInfo.get(0).toString());
		txt_lastName.setText(UserInfo.get(1).toString());
		txt_email.setText(UserInfo.get(4).toString());
		txt_dailyWordsNum.setText(UserInfo.get(5).toString());
	}
    
	public void updateBtn(ActionEvent event) throws IOException{
	
	    
	    String username = txt_userName.getText();
	    String password = txt_password.getText();
	    String firstName = txt_firstName.getText();
	    String lastName = txt_lastName.getText();
	    String email = txt_email.getText();
	    int dailyWordsNum = Integer.parseInt(txt_dailyWordsNum.getText());
	    
	    UserServiceImpl us = new UserServiceImpl();
		List UserInfo = us.queryUser(userID);
		boolean userModifyResult = us.userConfigModify(userID, username, password, firstName, lastName, email, dailyWordsNum);

		if((UserInfo.get(0).equals(txt_firstName.getText()))&&(UserInfo.get(1).equals(txt_lastName.getText()))&&
			(UserInfo.get(2).equals(txt_userName.getText()))&&(UserInfo.get(3).equals(txt_password.getText()))&&
			(UserInfo.get(4).equals(txt_email.getText()))&&(UserInfo.get(5).equals(Integer.parseInt(txt_dailyWordsNum.getText())))){
			AlertDialog.displayAlert("Info","Nothing Update!");
			System.out.print(userModifyResult);
		}
		else {
			AlertDialog.displayAlert("Info","Update Succeed!");
		}
	}	
		
	public void switchToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
	
}
