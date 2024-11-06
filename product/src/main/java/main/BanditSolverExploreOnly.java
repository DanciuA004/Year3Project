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

  public BanditSolverExploreOnly(List<BanditArm> arms, int totalRounds) {
    this.arms = arms;
    this.totalRounds = totalRounds;
    this.currentRound = 0;
    this.totalPoints = 0;
    this.random = new Random();
  }

  public BanditArm selectArm() {
    BanditArm arm = new BanditArm();
    return arm;
  }

  public void Round(BanditArm arm) {

  }
  
  public void runRound() {
    
  }
}