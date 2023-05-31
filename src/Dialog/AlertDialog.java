package Dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AlertDialog {
	public static void displayAlert(String title,String message){
		Stage window = new Stage();
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMaxHeight(100);
		
		Label label = new Label();
		label.setText(message);
		Button buttonOk = new Button("OK");
		buttonOk.setOnAction(e -> window.close());
		
		VBox layout = new VBox(5);
		layout.getChildren().addAll(label,buttonOk);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout,400,400);
		window.setScene(scene);
		window.showAndWait();
	}

}
