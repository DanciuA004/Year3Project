package main;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 */
public class BanditSolverExploreOnly {
  List<BanditArm> arms = new ArrayList<>();
  int totalPoints;
  int totalRounds;
  int currentRound;

  public BanditArm selectArm() {
    BanditArm arm = new BanditArm();
    return arm;
  }

  public void updateArm(BanditArm arm) {

  }
}
