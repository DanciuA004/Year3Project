package main;

import java.util.ArrayList;
import java.util.List;

public class main {

  public static void main(String[] args) {
    int totalRounds = 100;
    int E = 50;
    System.out.println("Total rounds: " + totalRounds);
    
    // Create a list of bandit arms.
    List<BanditArm> arms = new ArrayList<>();
    BanditArm arm1 = new BanditArm(1, 10, 4);
    arms.add(arm1);

    BanditArm arm2 = new BanditArm(2, 5, 5);
    arms.add(arm2);

    BanditArm arm3 = new BanditArm(3, 7, 3);
    arms.add(arm3);

    BanditArm arm4 = new BanditArm(4, 8, 2);
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
      
   // Initialise BanditSolverEGreedy and run.
      BanditSolverEGreedy EGreedy = new BanditSolverEGreedy(arms, totalRounds, E);
      EGreedy.runRound();
      
   // Print results from BanditSolverEGreedy and reset number of visits.
    System.out.println("\n" + "Total points earned with E-Greedy: " + EGreedy.getTotalPoints());
    System.out.println("Best arm: " + EGreedy.getBestArm());
    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armID + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }
    
   // Initialise BanditSolverUCB and run.
      BanditSolverUCB UCB = new BanditSolverUCB(arms, totalRounds);
      UCB.runRound();
      
   // Print results from BanditSolverEGreedy and reset number of visits.
    System.out.println("\n" + "Total points earned with UCB: " + UCB.getTotalPoints());
    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armID + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }
  }
}
