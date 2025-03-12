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
   * Drops a disc into a column on the board.
   *
   * @param column The column where the disc will be dropped.
   * @param disc The disc to be dropped (e.g., "R" or "Y").
   * @return true if the disc was successfully dropped, 
   *         false if the column is invalid.
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
}
