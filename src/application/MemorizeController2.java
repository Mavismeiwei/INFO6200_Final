package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import Dialog.AlertDialog;
import backend.dao.WordTableSQL;
import backend.service.WordServiceImpl;


public class MemorizeController2 {

	private Stage stage;
	private Scene scene;
	private Parent parent;
	int userID =LoginController.UserID;
	
	@FXML
	private Label names;
	@FXML
	private Label explanation;
	@FXML
	private Button memorizeButton;
	@FXML
	private Button exitButton;
	
    @FXML
    private Button btnKnow;
	
	WordServiceImpl wd = new WordServiceImpl();
	List wordsList =null;
	private int currentIndex = 0;
	
	public void setText(String text) {
		if(Objects.isNull(wordsList)) {
			wordsList = wd.wordPriorityQueue(userID);
		}
		List a = (List) wordsList.get(currentIndex);
		names.setText(a.get(0).toString());
	}
	
	public void memorizeButton() {
		System.out.println("Button is clicked");
		  currentIndex++;
		  try{
		  if(Objects.isNull(wordsList)) {
		   wordsList = wd.wordPriorityQueue(userID);
		  }
		  List a = (List) wordsList.get(currentIndex);
		  names.setText(a.get(0).toString());
		  explanation.setText("");
		  }
		  catch(Exception e) {
		  Alert alert = new Alert(Alert.AlertType.ERROR);
		         alert.setTitle("Alert");
		         alert.setHeaderText("Words Review Finished");
		         alert.showAndWait();
		  }

	}
	
	public void Button() {
		System.out.println("Button is clicked");
		if(Objects.isNull(wordsList)) {
			wordsList = wd.wordPriorityQueue(userID);
		}
		for(int i = 0; i<wordsList.size();i++) {
			int index =i;
			List a = (List) wordsList.get(index);
			if (names.getText().equals(a.get(0).toString())){
				explanation.setText(a.get(1).toString()+"\n"+"\n"+a.get(2).toString()+"\n"+"\n"+a.get(3).toString());
			}
		}
	}
	
	public void Score(ActionEvent event) throws IOException{
		btnKnow.setOnAction((ActionEvent e) ->{
				Button();
				boolean score = wd.wordsScoreUpdate(userID,1,names.getText());
				System.out.println(names.getText());
				System.out.println(score);
      			if(score==true) {
      				AlertDialog.displayAlert("info", "Congrulations");}
		});
	}
	
	public void switchToMemory1(ActionEvent event) throws IOException{
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
    	
//    	controller.setText("hhhaaa");
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
	
	public void switchToMain(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
	     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	     scene = new Scene(root);
	     stage.setScene(scene);
	     stage.show();
	}
	
	public void switchToQuiz(ActionEvent event) throws IOException{
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
	
	public void switchToCompletion(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("completion.fxml"));
    	Parent root = loader.load();
    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
    	CompletionController compController = loader.getController();
    	
        WordServiceImpl wd = new WordServiceImpl();
    	List wordsCompletion =null;
    	int currentIndex = 0;
    	
    	wordsCompletion = wd.wordsCompletion(userID);
    	List a = (List) wordsCompletion.get( currentIndex); 
    	compController.setText(a.get(2).toString(),a.get(1).toString());
    	compController.wordsCompletion = wordsCompletion;
       
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
	public void switchToList(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("wordlist.fxml"));
	     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	     scene = new Scene(root);
	     stage.setScene(scene);
	     stage.show();
	}
	public void switchToExit(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	     stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	     scene = new Scene(root);
	     stage.setScene(scene);
	     stage.show();
	}
}
