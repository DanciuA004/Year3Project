package main;

import java.util.ArrayList;
import java.util.List;

/*
 * This class solves the bandit problem using the "Upper Confidence Bound (UCB)" method.
 * UCB selects arms based on the UCB formula.
 */
public class BanditSolverUCB {
  List<BanditArm> arms = new ArrayList<>();
  List<Integer> totalPointsEachArm; 
  int totalPoints;
  int totalRounds;
  int currentRound;
  int maxUCB;
  int selectedArm;
  
  /*
   * Initialised variables above and taking list arms and totalRounds from main when set. 
   */
  public BanditSolverUCB(List<BanditArm> arms, int totalRounds) {
    this.arms = arms;
    this.totalPointsEachArm = new ArrayList<>();
    this.totalPoints = 0;
    this.totalRounds = totalRounds;
    this.currentRound = 0;
    this.maxUCB = 0;
    this.selectedArm = 0;
    
    for (int i = 0; i < arms.size(); i++) {
      totalPointsEachArm.add(0);
    }
  }

  /*
   * Returns the chosen arm based on the UCB algorithm.
   */
  public BanditArm selectArm() {
    for (int i = 0; i < arms.size(); i++) {
      BanditArm arm = arms.get(i);

      // If arm has not been visited before return this arm.
      if (arm.getVisits() == 0) {
        return arm;
      }
      
      // If arm has been visited before, calculate UCB score.
      int meanReward = totalPointsEachArm.get(i) / arm.getVisits();
      int confidenceInterval =  (int) Math.sqrt((2 * Math.log(currentRound)) / arm.getVisits());
      int UCBvalue = meanReward + confidenceInterval;
      
      // Calculate arm with highest UCB.
      if (UCBvalue > maxUCB) {
        maxUCB = UCBvalue;
        selectedArm = i;
      }
    }
    
    return arms.get(selectedArm);
  }
  
  /*
   * Runs one round, selecting an arm using selectArm(), getting the points 
   * and updating relevant values.
   */
  public void round() {
    BanditArm arm = selectArm();
    arm.incrimentVisits();
   
    int points = arm.getPoints();
    totalPoints += points;
    
    int armIndex = arms.indexOf(arm);
    totalPointsEachArm.set(armIndex, totalPointsEachArm.get(armIndex) + points);
    
    // Uncomment to see how the values in totalPointsEachArm itterate each time.
//    System.out.println("\n");
//    for (int i : totalPointsEachArm) {
//      System.out.println("i:" + i);
//    }
    
    currentRound++;
  }
  
  /*
   * Checks whether there are more rounds to be run and then runs them.
   */
  public void runRound() {
    while (currentRound < totalRounds) {
      round();
    }
  }
  
  public int getTotalPoints() {
    return totalPoints;
  }
}