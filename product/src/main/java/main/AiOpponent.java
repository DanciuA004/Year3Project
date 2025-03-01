package main;

import java.util.Random;

/**
 * This is the class that will house the AI Opponent for the PvE game mode.
 */
public class AiOpponent {
  private Random random = new Random();
  
  /**
   * Temp method to randomly select a column.
   *
   * @return a random int from 0-6.
   */
  public int play() {
    return random.nextInt(7);
  }
}
