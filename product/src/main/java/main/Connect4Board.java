package main;

/**
 * A class for the board that the game will be played in.
 */
public class Connect4Board {
  char[][] grid;
  private final int rows = 6;
  private final int columns = 7;


  /**
   * Constructor to initialise the grid.
   */
  public Connect4Board() {
    grid = new char[rows][columns];
    // Fill the grid with empty spaces
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        grid[i][j] = 0;
      }
    }
  }

  /**
   * Displays the Connect 4 board to the console.
   */
  public void displayBoard() {
    for (int r = 0; r < rows; r++) {
      // Print each row with | separating columns
      for (int c = 0; c < columns; c++) {
        System.out.print("|" + grid[r][c]);
      }
      System.out.println("|"); // Close the row
    }

    // Print the bottom border
    for (int c = 0; c < columns; c++) {
      System.out.print("--");
    }
    System.out.println("-");
  }
}
