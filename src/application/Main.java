package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
  public void start(Stage primaryStage) throws IOException{
    try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
      Parent parent = loader.load();
      Scene scene = new Scene(parent);
      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
		switch (event.getCode()) {
	      case F:
	          primaryStage.setFullScreen(true);
	          break;
	      } 
		}
      });
      primaryStage.setOnCloseRequest(event -> {
    	  
      });
      primaryStage.setScene(scene);
      primaryStage.setTitle("Jadwal Kapolres - Table Only");
      primaryStage.show();
    } catch (IOException ex) {
      Logger.getLogger(application.Main.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}
