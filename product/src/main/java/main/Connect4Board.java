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
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        grid[r][c] = 0;
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
  
  /**
   * Drops a disc into a column on the board.
   *
   * @param column The column where the disc will be dropped.
   * @param disc The disc to be dropped (e.g., "R" or "Y").
   * @return true if the disc was successfully dropped, false if the column invalid.
   */
  public boolean dropDisc(int column, char disc) {
    if (column < 0 || column >= 7) {
      return false; // Invalid column
    }

    for (int r = 5; r >= 0; r--) {
      if (grid[r][column] == ' ') {
        grid[r][column] = disc;
        return true;
      }
    }
    return false; // Column is full
  }
}
