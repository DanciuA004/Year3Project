package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class solves the bandit problem using the "Epsilon Greedy" method.
 * This is an algorithm that has a value, E, set to a certain %,
 * each round there is an E% chance that we pick a random restaurant,
 * if not we go to the restaurant that has given us the best point score so far
 */
public class BanditSolvereGreedy {
  List<BanditArm> arms = new ArrayList<>();
  int totalPoints;
  int totalRounds;
  int currentRound;
  int epsilon; 
  int bestPoints;
  BanditArm bestArm;
  Random random;
  
  /**
   * Initialised variables above and taking list arms and totalRounds from main when set. 
   */
  public BanditSolvereGreedy(List<BanditArm> arms, int totalRounds, int epsilon) {
    this.arms = arms;
    this.totalRounds = totalRounds;
    this.currentRound = 0;
    this.totalPoints = 0;
    this.epsilon = epsilon;
    this.bestPoints = 0;
    this.bestArm = null;
    this.random = new Random();
  }

  /**
   * Returns the chosen arm.
   */
  public BanditArm selectArm() {
    if (random.nextInt(101) < epsilon) {
      int randomIndex = random.nextInt(arms.size());
      return arms.get(randomIndex);
    } else {
      return bestArm;
    }
  }
  
  /**
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
  
  /**
   * Runs one round, selecting an arm using selectArm(), getting the points 
   * and updating relevant values.
   */
  public void round() {
    BanditArm arm = selectArm();
    if (arm.getPoints() > bestPoints) {
      bestPoints = arm.getPoints(); 
      bestArm = arm;
    }
    arm.incrimentVisits();
    totalPoints += arm.getPoints();
    currentRound++;
  }
  
  /**
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
    return bestArm.getId();
  }
}