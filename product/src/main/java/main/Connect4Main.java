package main;

/**
 * This class is used as a replacement for the main as two proof of concept programs need to be run
 * at the same time. This class initiates the Connect4 classes.
 */
public class Connect4Main {

  /**
   * This method runs the flow of the Connect4 game.
   */
  public void run() {
    Connect4Board board = new Connect4Board();
    
    String name = "Alex";
    char disc = 'R';
    Connect4Player player1 = new Connect4Player(name, disc);
    
    board.dropDisc(3, player1.getDisc());
    board.displayBoard();
  }
}
