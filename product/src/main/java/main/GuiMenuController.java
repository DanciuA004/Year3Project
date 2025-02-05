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
 * assets on the menu stage.
 */
public class GuiMenuController {
  private Stage stage;
  private Scene scene;
  private Parent root;

  /**
   * "Versus Player" button leads to the PvP game stage.
   *
   * @throws IOException If resource not found an exception will be thrown.
   */
  public void switchToPvP(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("GuiPvP.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
  
  /**
   * "Versus Player" button leads to the PvE game stage.
   *
   * @throws IOException If resource not found an exception will be thrown.
   */
  public void switchToPvE(ActionEvent event) throws IOException {
    root = FXMLLoader.load(getClass().getResource("GuiPvE.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
  
}
