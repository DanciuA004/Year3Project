package finalproduct;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is a controller class that will implements all of the functions for the
 * assets on the PvE stage.
 */
public class GuiPvEcontroller implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;

  private final int rows = 6;
  private final int columns = 7;

  @FXML
  private Label labelPvP;
  @FXML
  GridPane gridPane;

  private C4Board board = new C4Board();
  private C4Logic gameLogic = new C4Logic();
  private C4Player player1 = new C4Player("Player 1", 'R');
  private C4Player player2 = new C4Player("Player 2", 'Y');
  private C4Player currentPlayer = player1;
  private int currentRound = 0;
  private McTreeNode mctsRoot = new McTreeNode(board, player2, currentRound, -1, null);
  private Circle[][] circles = new Circle[rows][columns];

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        Circle circle = new Circle(45);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        circles[row][col] = circle;
        gridPane.add(circle, col, row);
      }
    }
  }

  /**
   * When a column is clicked a disc is dropped.
   */
  public void clickColumn(ActionEvent event) {

    // contains the element that caused the event.
    String eventSource = event.getSource().toString();

    // checks through all seven column IDs a drops disc into correct column.
    if (eventSource.equals("Button[id=buttonColumn1, styleClass=button]''")) {
      int row = board.dropDisc(0, currentPlayer.getDisc());
      updateMctsTree(0);
      updateGrid(row, 0);
    } else if (eventSource.equals("Button[id=buttonColumn2, styleClass=button]''")) {
      int row = board.dropDisc(1, currentPlayer.getDisc());
      updateMctsTree(1);
      updateGrid(row, 1);
    } else if (eventSource.equals("Button[id=buttonColumn3, styleClass=button]''")) {
      int row = board.dropDisc(2, currentPlayer.getDisc());
      updateMctsTree(2);
      updateGrid(row, 2);
    } else if (eventSource.equals("Button[id=buttonColumn4, styleClass=button]''")) {
      int row = board.dropDisc(3, currentPlayer.getDisc());
      updateMctsTree(3);
      updateGrid(row, 3);
    } else if (eventSource.equals("Button[id=buttonColumn5, styleClass=button]''")) {
      int row = board.dropDisc(4, currentPlayer.getDisc());
      updateMctsTree(4);
      updateGrid(row, 4);
    } else if (eventSource.equals("Button[id=buttonColumn6, styleClass=button]''")) {
      int row = board.dropDisc(5, currentPlayer.getDisc());
      updateMctsTree(5);
      updateGrid(row, 5);
    } else if (eventSource.equals("Button[id=buttonColumn7, styleClass=button]''")) {
      int row = board.dropDisc(6, currentPlayer.getDisc());
      updateMctsTree(6);
      updateGrid(row, 6);
    }

    // checks if there is a win condition and sends an alert.
    if (gameLogic.checkWin(board)) {
      displayWin();
    } else if (gameLogic.checkDraw(board)) {
      displayDraw();
    }
    switchPlayer();
  }

  /**
   * Switches the current player and runs the AI's turn.
   */
  public void switchPlayer() {
    if (currentPlayer == player1) {
      currentPlayer = player2;
      labelPvP.setText("AI Opponent's Turn!");

      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
        int column = mctsRoot.play();
        int row = board.dropDisc(column, currentPlayer.getDisc());
        updateGrid(row, column);

        // checks if there is a win condition and sends an alert.
        if (gameLogic.checkWin(board)) {
          displayWin();
        } else if (gameLogic.checkDraw(board)) {
          displayDraw();
        }

        for (McTreeNode child : mctsRoot.getChildren()) {
          if (child.getColumn() == column) {
            mctsRoot = child;
            break;
          }
        }
        switchPlayer();
        currentRound++;
      }));
      timeline.play();
    } else {
      currentPlayer = player1;
      labelPvP.setText("Player One, please select a column!");
      currentRound++;
    }
  }

  /**
   * Updates the MCTS tree after the player's move.
   */
  private void updateMctsTree(int selectedColumn) {
    McTreeNode nextRoot = null;

    for (McTreeNode child : mctsRoot.getChildren()) {
      if (child.getColumn() == selectedColumn) {
        nextRoot = child;
        break;
      }
    }

    if (nextRoot != null) {
      mctsRoot = nextRoot;
    } else {
      mctsRoot = new McTreeNode(board, player2, currentRound, -1, null);
      //mctsRoot.expand(board);
    }
  }

  /**
   * This method changes the colour of the circle when a disc is dropped.
   *
   * @param row the row number of the disc.
   * @param col the column number of the disc.
   */
  public void updateGrid(int row, int col) {
    if (currentPlayer.getDisc() == 'R') {
      circles[row][col].setFill(Color.RED);
    } else {
      circles[row][col].setFill(Color.YELLOW);
    }
  }

  /**
   * Shows an alert box displaying the winner.
   */
  public void displayWin() {
    Timeline delay = new Timeline(new KeyFrame(Duration.millis(500), e -> {
      Platform.runLater(() -> {
        Alert alertWin = new Alert(AlertType.INFORMATION);
        alertWin.setTitle("Winner!");
        alertWin.setHeaderText("The Winner is: " + currentPlayer.getName());
        alertWin.setContentText("The game will now restart");

        if (alertWin.showAndWait().get() == ButtonType.OK) {
          restart();
        }
      });
    }));
    delay.setCycleCount(1);
    delay.play();
  }

  /**
   * Shows an alert box displaying draw condition.
   */
  public void displayDraw() {
    Platform.runLater(() -> {
      Alert alertDraw = new Alert(AlertType.INFORMATION);
      alertDraw.setTitle("Draw!");
      alertDraw.setHeaderText("No Winner! There is a draw!");
      alertDraw.setContentText("The game will now restart");

      if (alertDraw.showAndWait().get() == ButtonType.OK) {
        restart();
      }
    });
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
    board = new C4Board();
    currentPlayer = player1;
    currentRound = 0;
    mctsRoot = new McTreeNode(board, player2, currentRound, -1, null);

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        Circle circle = new Circle(45);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        circles[row][col] = circle;
        gridPane.add(circle, col, row);
      }
    }
  }
}
