package user_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainUI extends Application {

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
			Parent p = FXMLLoader.load(getClass().getResource("/user_interface/userInterface.fxml"));
			Scene s = new Scene(p,900,500); 
			primaryStage.setScene(s);
			Image icon = new Image("univ2.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("3SAT problem resolve");
			primaryStage.show();
		
		
		
	}

}
