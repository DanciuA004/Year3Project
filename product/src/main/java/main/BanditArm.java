package main;

import java.util.Random;

/* 
 * This class is the base for the bandit problem.
 * it holds the different arms, the different places the user can go.
 * it has methods that allow for viewing points, visits and incrementing the visits once it has run.
 */
public class BanditArm {
  int armID;
  int expectedPoints;
  int deviation;
  int numOfVisits;
  Random random;
  
  public BanditArm(int armID, int expectedPoints, int deviation) {
    this.armID = armID;
    this.expectedPoints = expectedPoints;
    this.deviation = deviation;
    this.numOfVisits = 0;
    this.random = new Random();
  }
  
  public int getID() {
    return armID;
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
