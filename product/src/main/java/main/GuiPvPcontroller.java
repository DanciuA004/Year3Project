package main;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is a controller class that will implements all of the functions for the
 * assets on the PvP stage.
 */
public class GuiPvPcontroller {
  private Stage stage;
  private Scene scene;
  private Parent root;
  
  /**
   * "Back to Menu" buttons leads back to the menu page.
   *
   * @throws IOException If resource not found an exception will be thrown.
   */
  public void switchToMenu(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("GuiMenu.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
