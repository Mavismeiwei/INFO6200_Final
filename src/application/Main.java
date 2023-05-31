package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
//        	Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
//			Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        	
//        	Parent root = FXMLLoader.load(getClass().getResource("Memorize-1.fxml"));
//        	Parent root = FXMLLoader.load(getClass().getResource("Memorize-2.fxml"));
//    	    Parent root = FXMLLoader.load(getClass().getResource("completion.fxml"));		
	//   	Parent root = FXMLLoader.load(getClass().getResource("quiz.fxml"));	
//	    	Parent root = FXMLLoader.load(getClass().getResource("WordList.fxml"));
		
//			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

