package main;

/**
 * This is a class that hold the information for each player, such as their name and disc colour.
 */
public class Connect4Player {
  String name;
  char disc;

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
