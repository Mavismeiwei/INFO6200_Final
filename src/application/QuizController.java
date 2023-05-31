package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import Dialog.AlertDialog;
import backend.service.WordServiceImpl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class QuizController {
	
	public Stage stage;
	public Scene scene;
	public Parent root;
	

    @FXML
    private Button meaning1Btn;

    @FXML
    private Button meaning2Btn;

    @FXML
    private Button meaning3Btn;

    @FXML
    private Button meaning4Btn;

    @FXML
    private Label quizWord;
    
    WordServiceImpl wd = new WordServiceImpl();
   	List wordsList = null;
   	private int currentIndex = 0;
   	int userID = 1;
   	
   	List<Integer> idList = new ArrayList<>(Arrays.asList(1,2,3,4));
   	
   	private List a = null;
   	 
    public void setText(String text) {
    	if (Objects.isNull(wordsList)) {
    		wordsList = wd.wordsQuiz(userID);
    	}
    	a = (List) wordsList.get(currentIndex);
    	
    	Collections.shuffle(idList);
    	int correctId = idList.indexOf(1);
    	   	
    	quizWord.setText(a.get(0).toString());
    	meaning1Btn.setText(a.get(idList.get(0)).toString());
    	meaning2Btn.setText(a.get(idList.get(1)).toString());
    	meaning3Btn.setText(a.get(idList.get(2)).toString());
    	meaning4Btn.setText(a.get(idList.get(3)).toString());
    	
//		System.out.println("setText: "+a);
    }
    
    private void updateContent(Button clickBtn) {
    		if(clickBtn.getText().equals(a.get(1).toString())) {
    			int type = 2;
    			String word = a.get(0).toString();
    			boolean scoreUpdate = wd.wordsScoreUpdate(userID, type, word);

    			if(scoreUpdate == true) {
    				Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		    	alert.setContentText("Answer Correct!");
    		    	alert.show();
    		    	
    		    	a = (List) wordsList.get(++currentIndex);
    		    	setText("");  	
    			}
    		}
    		else {
    			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    	alert.setContentText("Wrong!");
		    	alert.show();
		    	
		    	a = (List) wordsList.get(++currentIndex);
		    	setText("");  	
    			}
    		
    		if(currentIndex == wordsList.size()-1) {
	    		AlertDialog.displayAlert("info", "Words Quiz finished！");
	    	}
    }
    
    public void quizChoice(ActionEvent event) throws IOException{
    	
    	meaning1Btn.setOnAction((ActionEvent e) ->{
    		updateContent(meaning1Btn);
    	});
    	
    	meaning2Btn.setOnAction((ActionEvent e) ->{
    		updateContent(meaning2Btn);
    	});
    	
    	meaning3Btn.setOnAction((ActionEvent e) ->{
    		updateContent(meaning3Btn);
    	});
    	
    	meaning4Btn.setOnAction((ActionEvent e) ->{
    		updateContent(meaning4Btn);
    	});
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
    		wordsList = wd.queryDailyWords(1);
    	}
    	List a = (List) wordsList.get(currentIndex);
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
            wordsList = wd.queryDailyWords(userID);
           }
           List a = (List) wordsList.get(currentIndex);
           reviewController.setText(a.get(0).toString());
           
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }
    
    public void switchToCompletion(ActionEvent event) throws IOException {
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
