package main;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is a controller class that will implements all of the functions for the
 * assets on the PvP stage.
 */
public class GuiPvPcontroller {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  private Label labelPvP;

  private Connect4Board board = new Connect4Board();
  private Connect4GameLogic gameLogic = new Connect4GameLogic();
  private Connect4Player player1 = new Connect4Player("Player 1", 'R');
  private Connect4Player player2 = new Connect4Player("Player 2", 'Y');
  private Connect4Player currentPlayer = player1;

  /**
   * When a column is clicked a disc is dropped.
   */
  public void clickColumn(ActionEvent event) {

    // contains the element that caused the event.
    String eventSource = event.getSource().toString();

    // checks through all seven column IDs a drops disc into correct column.
    if (eventSource.equals("Button[id=buttonColumn1, styleClass=button]''")) {
      board.dropDisc(0, currentPlayer.getDisc());
    } else if (eventSource.equals("Button[id=buttonColumn2, styleClass=button]''")) {
      board.dropDisc(1, currentPlayer.getDisc());
    } else if (eventSource.equals("Button[id=buttonColumn3, styleClass=button]''")) {
      board.dropDisc(2, currentPlayer.getDisc());
    } else if (eventSource.equals("Button[id=buttonColumn4, styleClass=button]''")) {
      board.dropDisc(3, currentPlayer.getDisc());
    } else if (eventSource.equals("Button[id=buttonColumn5, styleClass=button]''")) {
      board.dropDisc(4, currentPlayer.getDisc());
    } else if (eventSource.equals("Button[id=buttonColumn6, styleClass=button]''")) {
      board.dropDisc(5, currentPlayer.getDisc());
    } else if (eventSource.equals("Button[id=buttonColumn7, styleClass=button]''")) {
      board.dropDisc(6, currentPlayer.getDisc());
    }

    // checks if there is a win condition and sends an alert.
    if (gameLogic.checkWin(board)) {
      displayWin();
    } else if (gameLogic.checkDraw(board)) {
      displayDraw();
    }

    // switches current player and label
    if (currentPlayer == player1) {
      currentPlayer = player2;
      labelPvP.setText("Player Two, please select a column!");
    } else if (currentPlayer == player2) {
      currentPlayer = player1;
      labelPvP.setText("Player One, please select a column!");
    }

    board.displayBoard();
  }

  /**
   * Shows an alert box displaying the winner.
   */
  public void displayWin() {
    Alert alertWin = new Alert(AlertType.INFORMATION);
    alertWin.setTitle("Winner!");
    alertWin.setHeaderText("The Winner is: " + currentPlayer.getName());
    alertWin.setContentText("The game will now restart");
    if (alertWin.showAndWait().get() == ButtonType.OK) {
      restart();
    }
  }

  /**
   * Shows an alert box displaying draw condition.
   */
  public void displayDraw() {
    Alert alertDraw = new Alert(AlertType.INFORMATION);
    alertDraw.setTitle("Draw!");
    alertDraw.setHeaderText("No Winner! There is a draw!");
    alertDraw.setContentText("The game will now restart");
    if (alertDraw.showAndWait().get() == ButtonType.OK) {
      restart();
    }
  }

  /**
   * "Back to Menu" button leads back to the menu page.
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

  /**
   * "Restart" button restarts the game.
   */
  public void restart() {
    board = new Connect4Board();
    currentPlayer = player1;
  }
}
