package connectfourpoc;

/**
 * This is a class that hold the information for each player, such as their name
 * and disc colour.
 */
public class Connect4Player {
  String name;
  char disc;

  /**
   * A constructor for the player class.
   *
   * @param name sets the name of the player.
   * @param disc set the disc colour for the player.
   */
  public Connect4Player(String name, char disc) {
    this.name = name;
    this.disc = disc;
  }

  public String getName() {
    return name;
  }

  public char getDisc() {
    return disc;
  }
}
