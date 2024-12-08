package main;

/**
 * This class is used as a replacement for the main as two proof of concept programs need to be run
 * at the same time. This class initiates the Connect4 classes.
 */
public class Connect4Main {

  public void run() {
    Connect4Board board = new Connect4Board();
    board.displayBoard();
  }
}
