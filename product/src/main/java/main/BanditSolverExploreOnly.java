package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * This class solves the bandit problem using the "Explore Only" method.
 * This is a naive algorithm that simply chooses a random arm each round.
 */
public class BanditSolverExploreOnly {
  List<BanditArm> arms = new ArrayList<>();
  int totalPoints;
  int totalRounds;
  int currentRound;
  Random random;

  /*
   * Initialised variables above and taking list arms and totalRounds from main when set. 
   */
  public BanditSolverExploreOnly(List<BanditArm> arms, int totalRounds) {
    this.arms = arms;
    this.totalRounds = totalRounds;
    this.currentRound = 0;
    this.totalPoints = 0;
    this.random = new Random();
  }

  /*
   * Selects a random arm from the list arms.
   */
  public BanditArm selectArm() {
    int randomIndex = random.nextInt(arms.size());
    return arms.get(randomIndex);
  }

  /*
   * Runs one round, selecting an arm using selectArm(), getting the points 
   * and updating relevant values.
   */
  public void round() {
    if (currentRound < totalRounds) {
      BanditArm arm = selectArm();
      arm.incrimentVisits();
      totalPoints += arm.getPoints();
      currentRound++;
    }
  }
  
  /*
   * Checks whether there are more rounds to be run and then runs them.
   */
  public void runRound() {
    while (currentRound < totalRounds) {
      round();
    }
  }
}