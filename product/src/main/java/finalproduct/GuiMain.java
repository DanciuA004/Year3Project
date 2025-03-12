package finalproduct;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is where i will be testing the GUI for Connect4.
 */
public class GuiMain extends Application {

  /**
   * Main.
   *
   * @param args args.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("GuiMenu.fxml"));
    Scene scene = new Scene(root);
    
    stage.setResizable(false);
    stage.setTitle("Welcome to Connect4!");
    
    stage.setScene(scene);
    stage.show();
    
  }
}
