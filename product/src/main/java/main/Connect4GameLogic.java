package main;

/**
 * The class that holds the important methods for game logic, allowing the players to make moves and
 * validating to ensure they are correct and can be made.
 */
public class Connect4GameLogic {
  private Connect4Board board;

  // Constructor to initialise the game logic with a board
  public Connect4GameLogic(Connect4Board board) {
    this.board = board;
  }


  /**
   * Checks if the current state of the board has a winning condition.
   *
   * @return true if there is a win, false otherwise.
   */
  public boolean checkWin() {
    char[][] grid = board.grid;

    // Check horizontal
    for (int r = 0; r < 6; r++) {
      for (int c = 0; c < 4; c++) {
        char disc = grid[r][c];
        if (disc != ' ' && disc == grid[r][c + 1] && disc == grid[r][c + 2]
            && disc == grid[r][c + 3]) {
          return true;
        }
      }
    }

    // Check vertical
    for (int c = 0; c < 7; c++) {
      for (int r = 0; r < 3; r++) {
        char disc = grid[r][c];
        if (disc != ' ' && disc == grid[r + 1][c] && disc == grid[r + 2][c]
            && disc == grid[r + 3][c]) {
          return true;
        }
      }
    }

    // Check diagonal (bottom-left to top-right)
    for (int r = 3; r < 6; r++) {
      for (int c = 0; c < 4; c++) {
        char disc = grid[r][c];
        if (disc != ' ' && disc == grid[r - 1][c + 1] && disc == grid[r - 2][c + 2]
            && disc == grid[r - 3][c + 3]) {
          return true;
        }
      }
    }

    // Check diagonal (top-left to bottom-right)
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 4; c++) {
        char disc = grid[r][c];
        if (disc != ' ' && disc == grid[r + 1][c + 1] && disc == grid[r + 2][c + 2]
            && disc == grid[r + 3][c + 3]) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the game is a draw (i.e., no valid moves left).
   *
   * @return true if the game is a draw, false otherwise.
   */
  public boolean checkDraw() {
    for (int c = 0; c < 7; c++) {
      for (int r = 0; r < 6; r++) {
        if (board.grid[r][c] == ' ') {
          return false; // There is at least one valid move
        }
      }
    }
    return true; // No valid moves left
  }
}
