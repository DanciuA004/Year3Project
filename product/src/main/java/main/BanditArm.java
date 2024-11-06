package main;

/* 
 * This class is the base for the bandit problem.
 * it holds the different arms, the different places the user can go.
 * it has methods that allow for viewing points, visits and incrementing the visits once it has run.
 */
public class BanditArm {
  int armID;
  int points;
  int numOfVisits;
  
  public int getPoints() {
    return points;
  }
  
  public int getID() {
    return armID;
  }
  
  public int getVisits() {
    return numOfVisits;
  }
  
  public void resetVisits() {
    numOfVisits = 0;
  }
  
  public void incrimentVisits() {
    numOfVisits += 1;
  }
}
