package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.entity.User;
import backend.service.UserServiceImpl;
import backend.service.WordServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController{
    
    public Stage stage;
    public Scene scene;
    public Parent root;
    int userID =LoginController.UserID;
    

    public void switchToMemory1(ActionEvent event) throws IOException {
     try {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Memorize-1.fxml"));
     Parent root = loader.load();
     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
     Memory1Controller memory1Controller = loader.getController();
     
    // Get the wordlist
     WordServiceImpl wd = new WordServiceImpl();
     List wordsList = null;
     int currentIndex = 0;
     
     if(Objects.isNull(wordsList)) {
      wordsList = wd.queryDailyWords(userID);
     }
     List a = (List) wordsList.get(currentIndex);
     memory1Controller.setText(a.get(0).toString());
     currentIndex++;
     
//     controller.setText("hhhaaa");
     scene = new Scene(root);
     stage.setScene(scene);
     stage.show();}
     catch(Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Alert");
         alert.setHeaderText("Your subscription list is empty!");
         alert.setContentText(" Please go to Words List to add subscriptions.");
         alert.showAndWait();
     }
    }
    
    public void switchToMemory2(ActionEvent event) throws IOException {
     try {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Memorize-2.fxml"));
        Parent root = loader.load();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        MemorizeController2 reviewController = loader.getController();
        WordServiceImpl wd = new WordServiceImpl();
        List wordsList = null;
        int currentIndex = 0;
        
        if(Objects.isNull(wordsList)) {
            wordsList = wd.wordPriorityQueue(userID);
           }
           List a = (List) wordsList.get(currentIndex);
           reviewController.setText(a.get(0).toString());
           System.out.print(wordsList);
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();}
     catch(Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Alert");
         alert.setHeaderText("Your subscription list is empty!");
         alert.setContentText(" Please go to Words List to add subscriptions.");
         alert.showAndWait();
           }
    }
    
    public void switchToQuiz(ActionEvent event) throws IOException {
     try {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
        Parent root = loader.load();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        QuizController quizController = loader.getController();
        System.out.println(userID);
     // Get the wordlist
        WordServiceImpl wd = new WordServiceImpl();
        List wordsList = null;
        int currentIndex = 0;
        
        if(Objects.isNull(wordsList)) {
         wordsList = wd.queryDailyWords(userID);
        }
        List a = (List) wordsList.get(currentIndex);
        quizController.setText(a.get(0).toString());
        quizController.userID=userID;
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
     catch(Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Alert");
         alert.setHeaderText("Your subscription list is empty!");
         alert.setContentText(" Please go to Words List to add subscriptions.");
         alert.showAndWait();
     }
    }
    
    public void switchToCompletion(ActionEvent event) throws IOException {
     try {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("completion.fxml"));
     Parent root = loader.load();
     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
     CompletionController compController = loader.getController();
     
        WordServiceImpl wd = new WordServiceImpl();
     List wordsCompletion =null;
     int currentIndex = 0;
     
     wordsCompletion = wd.wordsCompletion(userID);
     List a = (List) wordsCompletion.get(currentIndex); 
     compController.setText(a.get(2).toString(),a.get(1).toString());
     compController.wordsCompletion = wordsCompletion;
     compController.userID = userID;
       
     scene = new Scene(root);
     stage.setScene(scene);
     stage.show();}
     catch(Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Alert");
         alert.setHeaderText("Your subscription list is empty!");
         alert.setContentText(" Please go to Words List to add subscriptions.");
         alert.showAndWait();
     }
    }
    
    public void switchToList(ActionEvent event) throws IOException {
     try {
     Parent root = FXMLLoader.load(getClass().getResource("Wordlist.fxml"));
     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
     scene = new Scene(root);
     stage.setScene(scene);
     stage.show();}catch(Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Alert");
         alert.setHeaderText("Your subscription list is empty!");
         alert.setContentText(" Please go to Words List to add subscriptions.");
         alert.showAndWait();
     }
    }
    
    public void switchToExit(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
     scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    public void switchToAccount(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
     Parent root = loader.load();
     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
     AccountController accountController = loader.getController();
     
  UserServiceImpl us = new UserServiceImpl();
  List UserInfo = us.queryUser(userID);
  accountController.setText(null);
    
     scene = new Scene(root);
     stage.setScene(scene);
     stage.show();}

}