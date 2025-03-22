package finalproduct;

/**
 * A class for the board that the game will be played in.
 */
public class C4Board {
  char[][] grid;
  final int rows = 6;
  final int columns = 7;

  /**
   * Constructor to initialise the grid.
   */
  public C4Board() {
    grid = new char[rows][columns];
    // Fill the grid with empty spaces
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        grid[r][c] = 0;
      }
    }
  }

  /**
   * This method uses the arraycopy method to create a new board and copy the
   * contents of the original board to the new one.
   *
   * @return the copy of the original board.
   */
  public C4Board copyBoard() {
    C4Board boardCopy = new C4Board();
    for (int r = 0; r < rows; r++) {
      System.arraycopy(this.grid[r], 0, boardCopy.grid[r], 0, columns);
    }
    return boardCopy;
  }

  /**
   * Drops a disc into a column on the board.
   *
   * @param column The column where the disc will be dropped.
   * @param disc   The disc to be dropped (e.g., "R" or "Y").
   * @return true if the disc was successfully dropped, false if the column is
   *         invalid.
   */
  public int dropDisc(int column, char disc) {
    if (column < 0 || column >= columns) {
      return -1; // Invalid column
    }

    for (int r = rows - 1; r >= 0; r--) {
      if (grid[r][column] == 0) {
        grid[r][column] = disc;
        return r; // Return the row where the disc was placed
      }
    }
    return -1; // Column is full
  }

  /**
   * Displays the Connect 4 board to the console. Used for testing.
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
