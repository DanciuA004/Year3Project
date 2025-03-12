package connectfourpoc;

/**
 * The class that holds the important methods for game logic, allowing the
 * players to make moves and validating to ensure they are correct and can be
 * made.
 */
public class Connect4GameLogic {

  /**
   * Checks if the current state of the board has a winning condition.
   *
   * @return true if there is a win, false otherwise.
   */
  public boolean checkWin(Connect4Board board) {
    char[][] grid = board.grid;

    // Check horizontal
    for (int r = 0; r < board.rows; r++) {
      for (int c = 0; c <= board.columns - 4; c++) { // Ensure enough space for a sequence
        char disc = grid[r][c];
        if (disc != 0 && disc == grid[r][c + 1] && disc == grid[r][c + 2] 
            && disc == grid[r][c + 3]) {
          return true;
        }
      }
    }

    // Check vertical
    for (int c = 0; c < board.columns; c++) {
      for (int r = 0; r <= board.rows - 4; r++) { // Ensure enough space for a sequence
        char disc = grid[r][c];
        if (disc != 0 && disc == grid[r + 1][c] && disc == grid[r + 2][c] 
            && disc == grid[r + 3][c]) {
          return true;
        }
      }
    }

    // Check diagonal (bottom-left to top-right)
    for (int r = 3; r < board.rows; r++) { // Start from row 3 to ensure enough space upwards
      for (int c = 0; c <= board.columns - 4; c++) { // Ensure enough space rightwards
        char disc = grid[r][c];
        if (disc != 0 && disc == grid[r - 1][c + 1] && disc == grid[r - 2][c + 2] 
            && disc == grid[r - 3][c + 3]) {
          return true;
        }
      }
    }

    // Check diagonal (top-left to bottom-right)
    for (int r = 0; r <= board.rows - 4; r++) { // Ensure enough space downwards
      for (int c = 0; c <= board.columns - 4; c++) { // Ensure enough space rightwards
        char disc = grid[r][c];
        if (disc != 0 && disc == grid[r + 1][c + 1] && disc == grid[r + 2][c + 2] 
            && disc == grid[r + 3][c + 3]) {
          return true;
        }
      }
    }

    return false; // No win condition found
  }

  /**
   * Checks if the game is a draw (i.e., no valid moves left).
   *
   * @return true if the game is a draw, false otherwise.
   */
  public boolean checkDraw(Connect4Board board) {
    for (int c = 0; c < 7; c++) {
      for (int r = 0; r < 6; r++) {
        if (board.grid[r][c] == 0) {
          return false; // There is at least one valid move
        }
      }
    }
    return true; // No valid moves left
  }
}
