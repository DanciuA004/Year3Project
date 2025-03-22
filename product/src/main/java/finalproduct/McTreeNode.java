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
  private C4Player currentPlayer; // Player ('R' or 'Y') who made the last move
  private int visits; // Number of times this node was visited
  private int wins; // Number of wins from this node
  private int currentRound;

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
    this.children = new ArrayList<>();
    this.currentPlayer = currentPlayer;
    this.visits = 0;
    this.wins = 0;
    this.currentRound = 0;
  }

  /**
   * This method handles the turn for the AI opponent.
   *
   * @return returns the column the disc will be dropped into.
   */
  public int play() {
    expand(board);

    for (int i = 0; i < children.size(); i++) {
      simulate(children.get(i));
    }

    System.out.println(getVisits());
    System.out.println(getWins());
    select(children).getBoard().displayBoard();

    Random random = new Random();
    return random.nextInt(7);
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

      double meanReward = child.getWins() / child.getVisits();
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
  public void simulate(McTreeNode node) {
    Random random = new Random();
    node.visits++;
    node.wins = node.wins + random.nextInt(1);
  }

  /**
   * Takes the data from the simulation and goes back up the tree, updating all of
   * the parent nodes.
   */
  public void backpropagation() {

  }
}
