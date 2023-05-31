package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import Dialog.AlertDialog;
import backend.dao.WordTableSQL;
import backend.entity.Wordlist;
import backend.service.WordServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class WordlistController implements Initializable{

    public Stage stage;
    public Scene scene;
    public Parent root;
 
	
	@FXML
	private TableView<Wordlist> table;
	
//    @FXML
//    private TableColumn<Word, Integer> score;

    @FXML
    private TableColumn<Wordlist, String> wordName;
    
    @FXML
    private TextField txt_CnMean;

    @FXML
    private TextField txt_EnMeam;

    @FXML
    private TextField txt_class;

    @FXML
    private TextField txt_word;
    
    @FXML
    private TextField txt_search;

    WordServiceImpl wd = new WordServiceImpl();
	List wordsList =null;
	int userID =LoginController.UserID;
   
    private ObservableList<Wordlist> data;
	
    
    @FXML
    private void handleImport(ActionEvent event) {
    	String name = txt_word.getText();
    	String wordEnMean = txt_EnMeam.getText();
    	String wordCnMean = txt_CnMean.getText();
    	String wordClass = txt_class.getText();
    	try {
    	boolean i =wd.wordsManualImport(userID,name,wordEnMean,wordCnMean,wordClass);
    	if(i==true) {
    		AlertDialog.displayAlert("Info","Import Success!");}
    	}
    	catch(Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setTitle("Alert");
    	    alert.setHeaderText("Attention");
    	    alert.setContentText(e.getMessage());
    	    alert.showAndWait();
    	}
    	table.getItems().clear();
    	lodaDataFromDatabase();
    	clearTextField();
    }
    
    @FXML
    private void handleModify(ActionEvent event) {
    	
    	String name = txt_word.getText();
    	String wordEnMean = txt_EnMeam.getText();
    	String wordCnMean = txt_CnMean.getText();
    	String wordClass = txt_class.getText();
    
    	try {
    	boolean i =wd.wordsModify(userID,name,wordEnMean,wordCnMean,wordClass);
    	if(i==true) {
    		AlertDialog.displayAlert("Info","Modify Success!");
    	}}catch(Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setTitle("Alert");
    	    alert.setHeaderText("Attention");
    	    alert.setContentText(e.getMessage());
    	    alert.showAndWait();
    	}
    	table.getItems().clear();
    	lodaDataFromDatabase();
    	clearTextField();
    }
    
    @FXML
    private void handleDelete(ActionEvent event) {
    	
    	String name = txt_word.getText();
 
    	try {
    	boolean i =wd.wordSubscriptionDeletion(userID, name);
    	if(i==true) {
    		AlertDialog.displayAlert("Info","Delete Success!");
    	}}catch(Exception e) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setTitle("Alert");
    	    alert.setHeaderText("Attention");
    	    alert.setContentText(e.getMessage());
    	    alert.showAndWait();
    	}
    	table.getItems().clear();
    	lodaDataFromDatabase();
    	clearTextField();    }
    
    @FXML
    private void handleSearch(ActionEvent event) {
    	table.getItems().clear();
    	searchWord();
    	
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		data = FXCollections.observableArrayList();
		setCellTable();
		lodaDataFromDatabase();
		setCellValueFromTableToTextField();
		clearTextField();	}
	
	private void setCellTable() {
	wordName.setCellValueFactory(new PropertyValueFactory<Wordlist,String>("word"));
	}
	
	private void lodaDataFromDatabase() {
		WordServiceImpl wd = new WordServiceImpl();
		List wordsList =wd.queryTargetUserWords(userID);
		for(int i = 0; i<wordsList.size();i++) {
			List a = (List) wordsList.get(i);
			data.add(new Wordlist(a.get(0).toString()));
		}
		table.setItems(data);
	}
	
	private void setCellValueFromTableToTextField() {
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Wordlist wl = table.getItems().get(table.getSelectionModel().getSelectedIndex());
				txt_word.setText(wl.getWord());	
			
				if(Objects.isNull(wordsList)) {
					wordsList = wd.queryTargetUserWords(userID);
				}
				for(int i = 0; i<wordsList.size();i++) {
					int index =i;
					List a = (List) wordsList.get(index);
					
					if (txt_word.getText().equals(a.get(0).toString())){
						txt_EnMeam.setText(a.get(1).toString());
						txt_CnMean.setText(a.get(2).toString());
						txt_class.setText(a.get(3).toString());
						}
					}
				}
			});
		}
	
	private void clearTextField() {
		txt_word.clear();
		txt_EnMeam.clear();
		txt_CnMean.clear();
		txt_class.clear();
	}
	
	private void searchWord() {
		if(txt_search.getText().length()==0){
			lodaDataFromDatabase();
		}
		else {
		WordServiceImpl wd = new WordServiceImpl();
		List wordsList =wd.queryTargetUserWords(userID);
		for(int i = 0; i<wordsList.size();i++) {
			List a = (List) wordsList.get(i);
			if(txt_search.getText().equals(a.get(0).toString())) {
			data.add(new Wordlist(a.get(0).toString()));}
		}
		table.setItems(data);}
		
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
