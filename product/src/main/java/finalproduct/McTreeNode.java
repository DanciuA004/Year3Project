package finalproduct;

import java.util.ArrayList;
import java.util.List;

/**
 * This class hold the tree structure used for the monte-carlo algorithm.
 */
public class McTreeNode {
  C4Board board; // Current game state
  List<McTreeNode> children; // List of child nodes
  int visits; // Number of times this node was visited
  double wins; // Number of wins from this node
  char currentPlayer; // Player ('R' or 'Y') who made the last move

  /**
   * Constructor to initialise a node.
   */
  public McTreeNode(C4Board board, char currentPlayer) {
    this.board = board;
    this.currentPlayer = currentPlayer;
    this.children = new ArrayList<>();
    this.visits = 0;
    this.wins = 0.0;
  }

  /**
   * This method uses the UCB method to pick the next best node in the tree.
   */
  public void select() {

  }

  /**
   * Expands the tree with the next possible moves, disc drop in column 0-6.
   */
  public void expand() {

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
