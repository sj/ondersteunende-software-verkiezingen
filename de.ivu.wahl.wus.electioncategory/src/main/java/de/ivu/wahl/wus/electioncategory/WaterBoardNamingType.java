package de.ivu.wahl.wus.electioncategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum WaterBoardNamingType {
  WATERSCHAP("waterschap", 1, 3, 4, 5, 6, 7, 8, 15, 16, 17, 18, 19, 20, 21, 22, 23),

  WETTERSKIP("wetterskip", 2),

  HOOGHEEMRAADSCHAP("hoogheemraadschap", 9, 10, 11),

  HOOGHEEMRAADSCHAP_VAN("hoogheemraadschap van", 12, 13, 14);

  private final String name;
  private final List<Integer> contestIds;

  private WaterBoardNamingType(String name, int... contestIds) {
    this.name = name;
    List<Integer> list = new ArrayList<Integer>();
    for (int i : contestIds) {
      list.add(i);
    }
    this.contestIds = Collections.unmodifiableList(list);
  }

  public String getName() {
    return name;
  }

  public List<Integer> getContestIds() {
    return contestIds;
  }

  public static String getPrefixForWaterBoardElection(int contestID) {
    for (WaterBoardNamingType type : WaterBoardNamingType.values()) {
      if (type.getContestIds().contains(contestID)) {
        return type.getName();
      }
    }
    return WATERSCHAP.getName();
  }
}
