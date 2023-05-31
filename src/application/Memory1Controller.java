package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import Dialog.AlertDialog;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Memory1Controller{
	
    public Stage stage;
    public Scene scene;
    public Parent root;
	
    @FXML
    private Button nextBtn;

    @FXML
    private Label meaning_en;
    
    @FXML
    private Label meaning_cn;
    
    @FXML
    private Label type;

    @FXML
    private Label myWord;
    
    WordServiceImpl wd = new WordServiceImpl();
	List wordsList = null;
	private int currentIndex = 0;
	int userID =LoginController.UserID;
	
    public void setText(String text) {
    	if(Objects.isNull(wordsList)) {
			wordsList = wd.queryDailyWords(userID);
		}
		List a = (List) wordsList.get(currentIndex);
		
		myWord.setText(a.get(0).toString());
		meaning_en.setText(a.get(1).toString());
		meaning_cn.setText(a.get(2).toString());
		type.setText(a.get(3).toString());

    }
	
	public void ClickMe() {
		try {
		if(Objects.isNull(wordsList)) {
			wordsList = wd.queryDailyWords(userID);
		}
    // Get the second word in wordlist when user click the button, index is currentIndex + 1
		List a = (List) wordsList.get(currentIndex+1);  
			
		myWord.setText(a.get(0).toString());
		meaning_en.setText(a.get(1).toString());
		meaning_cn.setText(a.get(2).toString());
		type.setText(a.get(3).toString());
		
		currentIndex++;
		}catch(Exception e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	         alert.setTitle("Information");
	         alert.setHeaderText("Memorize Finished!");
	         alert.showAndWait();
		}
	}
	
    public void switchToMain(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
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

}
