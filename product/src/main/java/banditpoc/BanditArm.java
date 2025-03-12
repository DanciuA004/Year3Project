package banditpoc;

import java.util.Random;

/**
 * This class is the base for the bandit problem. it holds the different arms, the different places
 * the user can go. it has methods that allow for viewing points, visits and incrementing the visits
 * once it has run.
 */
public class BanditArm {
  int armId;
  int expectedPoints;
  int deviation;
  int numOfVisits;
  Random random;

  /**
   * A constructor for the BanditArm class.
   *
   * @param armId A unique identifier for each arm.
   * @param expectedPoints The value of the most common points given by the arm.
   * @param deviation The value of the deviation from the expected most common point value.
   */
  public BanditArm(int armId, int expectedPoints, int deviation) {
    this.armId = armId;
    this.expectedPoints = expectedPoints;
    this.deviation = deviation;
    this.numOfVisits = 0;
    this.random = new Random();
  }

  public int getId() {
    return armId;
  }

  public int getPoints() {
    int points = (int) (random.nextGaussian() * deviation + expectedPoints);
    return points;
  }

  public int getVisits() {
    return numOfVisits;
  }

  public void incrimentVisits() {
    numOfVisits += 1;
  }

  public void resetVisits() {
    numOfVisits = 0;
  }
}
