package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import Dialog.AlertDialog;
import backend.service.WordServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompletionController {

	public Stage stage;
    public Scene scene;
    public Parent root;

    
    WordServiceImpl wd = new WordServiceImpl();
	List wordsCompletion=null;
	private int currentIndex = 0;
	int userID =LoginController.UserID;
	private List a = null;
	
	@FXML
    private Label completion;
	
	@FXML
	private Label detail;
	
	@FXML
    private TextField txtAnswer;
    
    
    public void setText(String text,String de) {
    	if(Objects.isNull(wordsCompletion)) {
    	wordsCompletion = wd.wordsCompletion(userID);
    	}
    	a = (List) wordsCompletion.get(currentIndex);  
    	completion.setText(text);
    	detail.setText(de);
    }
    
    public void Next() {
        String de = detail.getText();
        String answer = txtAnswer.getText();
        a = (List) wordsCompletion.get(currentIndex);
        try {
        if((de.equals(a.get(1).toString()))&&(answer.equals(a.get(0).toString()))) {
         boolean score = wd.wordsScoreUpdate(userID, 3, answer);
         if(score==true) {
          AlertDialog.displayAlert("info", "correct");
           currentIndex++;
          a = (List) wordsCompletion.get(currentIndex);
           completion.setText(a.get(2).toString());
               detail.setText(a.get(1).toString()); 
              txtAnswer.clear();}
         }else {
          AlertDialog.displayAlert("info", "wrong");
          currentIndex++;
          a = (List) wordsCompletion.get(currentIndex);
          completion.setText(a.get(2).toString());
              detail.setText(a.get(1).toString()); 
             txtAnswer.clear();}}catch(Exception e) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Alert");
                 alert.setHeaderText("Words Review Finished");
                 alert.showAndWait();
          }

 			}

    public void Score() {
     		String de = detail.getText();
     		String answer = txtAnswer.getText();
     		a = (List) wordsCompletion.get(currentIndex);
     		System.out.println(de);
  		    System.out.println(a.get(1).toString());
  	      	System.out.println(answer);
  		    System.out.println(a.get(0).toString());
     		System.out.println(currentIndex);
     		System.out.println(wordsCompletion);
     		if((de.equals(a.get(1).toString()))&&(answer.equals(a.get(0).toString()))) {
     			boolean score = wd.wordsScoreUpdate(userID, 3, answer);
     			if(score==true) {
     				AlertDialog.displayAlert("info", "correct");
     			}}
     		else{
     			AlertDialog.displayAlert("info", "wrong");}
     		}
    
    
    public void switchToMemory1(ActionEvent event) throws IOException {
    	
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
    	List a = (List) wordsList.get(currentIndex+1);
    	memory1Controller.setText(a.get(0).toString());
    	currentIndex++;
    	
//    	controller.setText("hhhaaa");
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void switchToMemory2(ActionEvent event) throws IOException {
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
           
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }
    
    public void switchToQuiz(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("quiz.fxml"));
        Parent root = loader.load();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        QuizController quizController = loader.getController();
        
     // Get the wordlist
        WordServiceImpl wd = new WordServiceImpl();
        List wordsList = null;
        int currentIndex = 0;
        
        if(Objects.isNull(wordsList)) {
         wordsList = wd.queryDailyWords(userID);
        }
        List a = (List) wordsList.get(currentIndex);
        quizController.setText(a.get(0).toString());
        
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToList(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Wordlist.fxml"));
    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void switchToExit(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
    
    public void switchToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
	
	
}
