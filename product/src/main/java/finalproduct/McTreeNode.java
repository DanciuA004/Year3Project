package finalproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class hold the tree structure used for the monte-carlo algorithm.
 */
public class McTreeNode {
  private C4Board board; // Current game state
  private List<McTreeNode> children; // List of child nodes
  private List<Integer> totalPointsEachChild;
  private int visits; // Number of times this node was visited
  private int wins; // Number of wins from this node
  private C4Player currentPlayer; // Player ('R' or 'Y') who made the last move
  int currentRound;

  /*
   * To Do: 
   * do getUCB() method, 
   * fix select(), 
   * clickColumn() fix user clicking while AI turn bug, 
   * clickColumn() handle AI and User turns, 
   * do simulate(), 
   * do backpropagation(), 
   * fix play();
   */

  public int getVisits() {
    return visits;
  }

  public int getWins() {
    return wins;
  }

  public C4Board getBoard() {
    return board;
  }

  /**
   * Constructor to initialise a node.
   */
  public McTreeNode(C4Board board, C4Player currentPlayer) {
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.children = new ArrayList<>();
    this.totalPointsEachChild = new ArrayList<>();
    this.visits = 0;
    this.wins = 0;
  }

  /**
   * This method handles the turn for the AI opponent.
   *
   * @return returns the column the disc will be dropped into.
   */
  public int play() {
    expand();
    Random random = new Random();
    return random.nextInt(7);
  }

  /**
   * This method uses the UCB method to pick the next best node in the tree. it
   * will go though all of the child nodes and collect their UCB value and then
   * select the one with the highest and return it.
   */
  public McTreeNode select() {
    double maxUcb = Double.NEGATIVE_INFINITY;
    McTreeNode selectedChild = null;

    for (int i = 0; i < children.size(); i++) {
      McTreeNode child = children.get(i);

      if (child.getVisits() == 0) {
        return child;
      }

      double meanReward = (double) totalPointsEachChild.get(i) / child.getVisits();
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
   * Calculates the UCB value for this node going off the number of visits and
   * number of wins.
   *
   * @return the USB value
   */
  public double getUcb() {
    return 0;

  }

  /**
   * Expands the tree with the next possible moves, disc drop in column 0-6.
   */
  public void expand() {
    for (int i = 0; i <= 6; i++) {
      C4Board boardCopy = board.copyBoard(); 
      boardCopy.dropDisc(i, currentPlayer.getDisc());

      McTreeNode node = new McTreeNode(boardCopy, currentPlayer);
      children.add(node);
      boardCopy.displayBoard();
    }
  }

  /**
   * For each of the next nodes added by the expand method, multiple simulations
   * are run on them and a value is assigned.
   */
  public void simulate() {

  }

  /**
   * Takes the data from the simulation and goes back up the tree, updating all of
   * the parent nodes.
   */
  public void backpropagation() {

  }
}
