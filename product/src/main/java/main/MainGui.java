package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is where i will be testing the GUI for Connect4.
 */
public class MainGui extends Application {

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
    Group root = new Group();
    Scene scene = new Scene(root, Color.BLACK);
    
    stage.setWidth(800);
    stage.setHeight(800);
    stage.setResizable(false);
    stage.setTitle("Welcome to Connect4!");
    
    stage.setScene(scene);
    stage.show();
    
  }
}
