package main;

import java.util.ArrayList;
import java.util.List;

/*
 * This class solves the bandit problem using the "Epsilon Greedy" method.
 * This is an algorithm that has a value, E, set to a certain %,
 * each round there is an E% chance that we pick a random restaurant,
 * if not we go to the restaurant that has given us the best point score so far
 */
public class BanditSolverEGreedy {
  List<BanditArm> arms = new ArrayList<>();
  int totalPoints;
  int totalRounds;
  int currentRound;
  int bestPoints;
  BanditArm bestArm;
  
  /*
   * Initialised variables above and taking list arms and totalRounds from main when set. 
   */
  public BanditSolverEGreedy(List<BanditArm> arms, int totalRounds) {
    this.arms = arms;
    this.totalRounds = totalRounds;
    this.currentRound = 0;
    this.totalPoints = 0;
    this.bestPoints = 0;
    this.bestArm = null;
  }

  /*
   * Returns the chosen arm.
   */
  public BanditArm selectArm() {
    
  }
  
  /*
   * Perform the initial exploration by visiting each arm once and finding the best.
   */
  public void firstRound() {
    for (BanditArm arm : arms) {
      arm.incrimentVisits();
      totalPoints += arm.getPoints(); 
      currentRound++; 
      
      if (arm.getPoints() > bestPoints) {
        bestPoints = arm.getPoints(); 
        bestArm = arm;
      }
    }
  }
  
  /*
   * Runs one round, selecting an arm using selectArm(), getting the points 
   * and updating relevant values.
   */
  public void round() {
    BanditArm arm = selectArm();
    arm.incrimentVisits();
    totalPoints += arm.getPoints();
    currentRound++;
  }
  
  /*
   * Checks whether there are more rounds to be run and then runs them.
   */
  public void runRound() {
    firstRound();
    while (currentRound < totalRounds) {
      round();
    }
  }
  
  public int getTotalPoints() {
    return totalPoints;
  }
  
  public int getBestArm() {
    return bestArm.getID();
  }
}