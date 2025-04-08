package finalproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class hold the tree structure used for the monte-carlo algorithm.
 */
public class McTreeNode {
  private C4Board board;
  private List<McTreeNode> children; // the list of children, the next moves from this node.
  private C4Player currentPlayer;
  private int visits;
  private int wins;
  private int currentRound;
  private int column; // the column of last move that lead to this node.
  private McTreeNode parent; // the parent of this node.

  public C4Board getBoard() {
    return board;
  }

  public List<McTreeNode> getChildren() {
    return children;
  }

  public int getVisits() {
    return visits;
  }

  public int getWins() {
    return wins;
  }

  public int getColumn() {
    return column;
  }

  /**
   * Constructor to initialise a node.
   */
  public McTreeNode(C4Board board, C4Player currentPlayer, int currentRound, 
      int column, McTreeNode parent) {
    this.board = board;
    this.children = new ArrayList<>();
    this.currentPlayer = currentPlayer;
    this.visits = 0;
    this.wins = 0;
    this.currentRound = currentRound;
    this.column = column;
    this.parent = parent;
  }

  /**
   * This method handles the turn for the AI opponent. if no children are linked
   * to this node the expand() method is called then for each child a number of
   * simulation games are run once the simulation and backpropagation happens the
   * select method can be run to pick the best node from the children and that is
   * returned.
   *
   * @return returns the column the disc will be dropped into.
   */
  public int play() {
    if (children.isEmpty()) {
      expand(board);
    }

    for (int i = 0; i < children.size(); i++) {
      simulate(children.get(i));
      System.out.println("visits: " + children.get(i).getVisits()); // console print for debugging
      System.out.println("wins: " + children.get(i).getWins()); // console print for debugging
    }

    System.out.println("\ncurrentRound: " + currentRound + "\n"); // console print for debugging

    return select(children).getColumn();
  }

  /**
   * This method uses the UCB method to pick the next best node in the tree. it
   * will go though all of the child nodes and collect their UCB value and then
   * select the one with the highest and return it.
   */
  public McTreeNode select(List<McTreeNode> children) {
    double maxUcb = Double.NEGATIVE_INFINITY;
    McTreeNode selectedChild = null;

    for (int i = 0; i < children.size(); i++) {
      McTreeNode child = children.get(i);

      if (child.getVisits() == 0) {
        return child;
      }

      double meanReward = (double) child.getWins() / child.getVisits();
      double confidenceInterval = Math.sqrt((2 * Math.log(currentRound + 1)) / child.getVisits());
      double ucbValue = meanReward + confidenceInterval;

      if (ucbValue > maxUcb) {
        maxUcb = ucbValue;
        selectedChild = child;
      }
    }
    return selectedChild;
  }

  /**
   * Expands the tree with the next possible moves, disc drop in column 0-6.
   */
  public void expand(C4Board board) {
    for (int i = 0; i <= 6; i++) {
      if (board.isValidMove(i)) {
        C4Board boardCopy = board.copyBoard();
        boardCopy.dropDisc(i, currentPlayer.getDisc());

        McTreeNode node = new McTreeNode(boardCopy, currentPlayer, currentRound, i, this);

        // Immediately reward winning moves before simulation.
        // Ensures the AI takes the next winning move.
        C4Logic gameLogic = new C4Logic();
        if (gameLogic.checkWin(boardCopy)) {
          node.wins = 10000; // Set max win count.
        }

        children.add(node);
        boardCopy.displayBoard();
      }
    }
  }

  /**
   * For each of the next nodes added by the expand method, multiple simulations
   * are run on them and a value is assigned. Random discs are dropped by yellow
   * and red until someone wins, if yellow wins the AI wins counter gets
   * increased, if red wins the AI win counter gets decreased.
   */
  public void simulate(McTreeNode node) {
    C4Board simBoard = node.getBoard().copyBoard();
    C4Logic gameLogic = new C4Logic();
    Random random = new Random();

    C4Player simPlayer1 = new C4Player("Player 1", 'R');
    C4Player simPlayer2 = new C4Player("Player 2", 'Y');
    C4Player currentSimPlayer = simPlayer2;

    for (int i = 0; i < 10000; i++) {
      C4Board simBoardCopy = simBoard.copyBoard();
      while (!gameLogic.checkWin(simBoardCopy) && !gameLogic.checkDraw(simBoardCopy)) {

        int randomColumn = random.nextInt(7);
        simBoardCopy.dropDisc(randomColumn, currentSimPlayer.getDisc());

        if (gameLogic.checkWin(simBoardCopy)) {
          if (currentSimPlayer == simPlayer2) {
            node.wins++;
            break;
          } else {
            node.wins--;
            break;
          }
        }

        // switch player
        if (currentSimPlayer == simPlayer1) {
          currentSimPlayer = simPlayer2;
        } else {
          currentSimPlayer = simPlayer1;
        }
      }
      // increment visits ever for loop.
      node.visits++;
    }
    // backpropogate the results at the end of the simulation.
    node.backpropagation(node.wins);
  }

  /**
   * Takes the data from the simulation and goes back up the tree, updating all of
   * the parent nodes.
   */
  public void backpropagation(int wins) {
    McTreeNode node = this.parent;
    while (node != null) {
      node.visits++;
      node.wins += wins; 
      node = node.parent;
    }
  }
}
