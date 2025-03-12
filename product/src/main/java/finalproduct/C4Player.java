package finalproduct;

/**
 * This is a class that hold the information for each player, such as their name and disc colour.
 */
public class C4Player {
  String name;
  char disc;

  /**
   * Constructor.
   *
   * @param name the name of the player.
   * @param disc the colour of their disc.
   */
  public C4Player(String name, char disc) {
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
