package connectfourpoc;

import java.util.Scanner;

/**
 * This class is used as a replacement for the main as two proof of concept programs need to be run
 * at the same time. This class initiates the Connect4 classes.
 */
public class Connect4Main {

  /**
   * This method runs the flow of the Connect4 game.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Connect4Board board = new Connect4Board();
    Connect4GameLogic gameLogic = new Connect4GameLogic();

    // Get player information
    System.out.print("Enter Player 1's name, who will use the Red disc: ");
    String player1Name = scanner.nextLine();
    Connect4Player player1 = new Connect4Player(player1Name, 'R');

    System.out.print("Enter Player 2's name, who will use the Yellow disc: ");
    String player2Name = scanner.nextLine();
    Connect4Player player2 = new Connect4Player(player2Name, 'Y');

    Connect4Player currentPlayer = player1;

    // Game loop
    while (true) {
      // Display the current board state
      System.out.println("\nCurrent board:");
      board.displayBoard();

      // Prompt the current player for their move
      System.out
          .println(currentPlayer.getName() + " (" + currentPlayer.getDisc() + "), it's your turn.");
      System.out.print("Enter a column (0-6) to drop your disc: ");

      int column;
      while (true) {
        try {
          column = Integer.parseInt(scanner.nextLine());
          if (board.dropDisc(column, currentPlayer.getDisc())) {
            break;
          } else {
            System.out.print("Invalid move. Try again (0-6): ");
          }
        } catch (NumberFormatException e) {
          System.out.print("Please enter a valid number (0-6): ");
        }
      }

      // Check for a win
      if (gameLogic.checkWin(board)) {
        board.displayBoard();
        System.out.println("\nCongratulations, " + currentPlayer.getName() + "! You win!");
        break;
      }

      // Check for a draw
      if (gameLogic.checkDraw(board)) {
        board.displayBoard();
        System.out.println("\nThe game is a draw! No more valid moves.");
        break;
      }

      // Switch to the other player
      if (currentPlayer == player1) {
        currentPlayer = player2;
      } else {
        currentPlayer = player1;
      }
    }

    // Game over
    System.out.println("Game over! Thanks for playing.");
    scanner.close();
  }
}
