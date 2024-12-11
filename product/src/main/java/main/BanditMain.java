package main;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is used as a replacement for the main as two proof of concept programs need to be run
 * at the same time. This class initiates the Bandit Problem classes and runs the program.
 */
public class BanditMain {

  /**
   * This method initiates the Bandit Problem classes and runs them, printing out the results.
   */
  public void run() {
    int totalRounds = 100;
    final int epsilon = 50;
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
    BanditSolverExploreOnly exploreOnly = new BanditSolverExploreOnly(arms, totalRounds);
    exploreOnly.runRound();

    // Print results from BanditSolverExploreOnly and reset the number of visits.
    System.out
        .println("\n" + "Total points earned with Explore Only: " + exploreOnly.getTotalPoints());

    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armId + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }

    // Initialise BanditSolverExploitOnly and run.
    BanditSolverExploitOnly exploitOnly = new BanditSolverExploitOnly(arms, totalRounds);
    exploitOnly.runRound();

    // Print results from BanditSolverExploitOnly and reset the number of visits.
    System.out
        .println("\n" + "Total points earned with Exploit Only: " + exploitOnly.getTotalPoints());
    System.out.println("Best arm: " + exploitOnly.getBestArm());
    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armId + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }

    // Initialise BanditSolverEGreedy and run.
    BanditSolvereGreedy epsilonGreedy = new BanditSolvereGreedy(arms, totalRounds, epsilon);
    epsilonGreedy.runRound();

    // Print results from BanditSolverEGreedy and reset the number of visits.
    System.out
        .println("\n" + "Total points earned with E-Greedy: " + epsilonGreedy.getTotalPoints());
    System.out.println("Best arm: " + epsilonGreedy.getBestArm());
    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armId + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }

    // Initialise BanditSolverUCB and run.
    BanditSolverUcb upperConfidenceBound = new BanditSolverUcb(arms, totalRounds);
    upperConfidenceBound.runRound();

    // Print results from BanditSolverEGreedy and reset the number of visits.
    System.out
        .println("\n" + "Total points earned with UCB: " + upperConfidenceBound.getTotalPoints());
    for (BanditArm arm : arms) {
      System.out.println("Arm " + arm.armId + " visited " + arm.getVisits() + " times.");
      arm.resetVisits();
    }
  }
}
