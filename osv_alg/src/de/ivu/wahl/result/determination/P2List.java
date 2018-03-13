/*
 * P2List
 * 
 * Created on 02.03.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import de.ivu.wahl.result.Candidate;
import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;

/**
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface P2List extends Comparable<P2List>, GeneralList, DrawingLotsAlternative {
  Comparator<? super P2List> SORT_BY_ELECTORAL_DISTRICT_NUMBER = new Comparator<P2List>() {
    public int compare(P2List x, P2List y) {
      int dx = x.getElectoralDistrictNumber();
      int dy = y.getElectoralDistrictNumber();
      return dx < dy ? -1 : dx == dy ? 0 : 1;
    }
  };

  public Set<CandidateList> getCandidateLists();

  public List<Candidate> getCandidates();

  public int getElectoralDistrictNumber();

  /**
   * @return a String displaying the electoral district numbers like "2" or "3 en 4" or
   *         "2, 4, 6 en 8"
   */
  public String getElectoralDistrictNumbers();

}