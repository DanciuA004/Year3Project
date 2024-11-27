package main;

import java.util.ArrayList;
import java.util.List;

/*
 * This class solves the bandit problem using the "Upper Confidence Bound (UCB)" method.
 * UCB selects arms based on the UCB formula.
 */
public class BanditSolverUCB {
    private List<BanditArm> arms;
    private List<Integer> totalPointsEachArm;
    private int totalPoints;
    private int totalRounds;
    private int currentRound;

    public BanditSolverUCB(List<BanditArm> arms, int totalRounds) {
        this.arms = arms;
        this.totalPointsEachArm = new ArrayList<>();
        this.totalPoints = 0;
        this.totalRounds = totalRounds;
        this.currentRound = 0;

        for (int i = 0; i < arms.size(); i++) {
            totalPointsEachArm.add(0);
        }
    }

    /*
     * Returns the chosen arm based on the UCB algorithm.
     */
    public BanditArm selectArm() {
        double maxUCB = Double.NEGATIVE_INFINITY; 
        BanditArm selectedArm = null;

        for (int i = 0; i < arms.size(); i++) {
            BanditArm arm = arms.get(i);

            if (arm.getVisits() == 0) {
                return arm;
            }

            double meanReward = (double) totalPointsEachArm.get(i) / arm.getVisits();
            double confidenceInterval = Math.sqrt((2 * Math.log(currentRound + 1)) / arm.getVisits());
            double UCBvalue = meanReward + confidenceInterval;

            if (UCBvalue > maxUCB) {
                maxUCB = UCBvalue;
                selectedArm = arm;
            }
        }
        return selectedArm;
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
