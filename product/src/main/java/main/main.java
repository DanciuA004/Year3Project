package main;

import java.util.ArrayList;
import java.util.List;

public class main {

  public static void main(String[] args) {
 
    // Create a list of bandit arms
    List<BanditArm> arms = new ArrayList<>();
    BanditArm arm1 = new BanditArm();
    arm1.armID = 1;
    arm1.points = 10;
    arms.add(arm1);

    BanditArm arm2 = new BanditArm();
    arm2.armID = 2;
    arm2.points = 5;
    arms.add(arm2);

    BanditArm arm3 = new BanditArm();
    arm3.armID = 3;
    arm3.points = 19;
    arms.add(arm3);

    BanditArm arm4 = new BanditArm();
    arm3.armID = 4;
    arm3.points = 8;
    arms.add(arm4);
    
 // Initialise BanditSolverExploreOnly and run
    int totalRounds = 100;
    BanditSolverExploreOnly ExploreOnly = new BanditSolverExploreOnly(arms, totalRounds);
    ExploreOnly.runRound();
  }
}
