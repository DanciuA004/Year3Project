package main;

import java.util.ArrayList;
import java.util.List;

public class main {

  public static void main(String[] args) {
    int totalRounds = 100;
    System.out.println("Total rounds: " + totalRounds);
    
    // Create a list of bandit arms.
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
    arm3.points = 20;
    arms.add(arm3);

    BanditArm arm4 = new BanditArm();
    arm4.armID = 4;
    arm4.points = 8;
    arms.add(arm4);
    
    // Initialise BanditSolverExploreOnly and run.
    BanditSolverExploreOnly ExploreOnly = new BanditSolverExploreOnly(arms, totalRounds);
    ExploreOnly.runRound();
    
    // Print results from BanditSolverExploreOnly and reset number of visits. 
    System.out.println("\n" + "Total points earned with Explore Only: " + ExploreOnly.getTotalPoints());
    
    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armID + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }
    
    
    // Initialise BanditSolverExploitOnly and run.
    BanditSolverExploitOnly ExploitOnly = new BanditSolverExploitOnly(arms, totalRounds);
    ExploitOnly.runRound();
    
    // Print results from BanditSolverExploitOnly and reset number of visits.
    System.out.println("\n" + "Total points earned with Exploit Only: " + ExploitOnly.getTotalPoints());
    System.out.println("Best arm: " + ExploitOnly.getBestArm());
    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armID + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }
  }
}
