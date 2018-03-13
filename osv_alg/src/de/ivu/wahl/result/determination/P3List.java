/*
 * P3List
 * 
 * Created on 16.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import de.ivu.wahl.result.CandidateList;
import de.ivu.wahl.result.NumberedObject;
import de.ivu.wahl.result.drawlots.DrawingLotsAlternative;

/**
 * A P3-list is a group of lists, a set of identical groups not belonging to a group of lists or an
 * independent list.
 * <p>
 * compareTo() sorts candidates by name.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface P3List
    extends
      NumberedObject,
      Comparable<P3List>,
      GeneralList,
      DrawingLotsAlternative {
  public Set<P2List> getP2Lists();

  public List<P2List> getP2ListsSorted();

  public Collection<CandidateList> getCandidateLists();

  /**
   * For blanko lists this looks like "lijst 13", for others like "lijst 25, ABC"
   * 
   * @return "lijst" + list number + comma + Name of the political group (if present)
   */
  public String getListNumberWithName();

}
