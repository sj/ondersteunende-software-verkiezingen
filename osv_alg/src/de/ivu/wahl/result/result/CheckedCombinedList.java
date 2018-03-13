/*
 * CheckedCombinedList
 * 
 * Created on 20.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.result;

import java.util.List;

import de.ivu.wahl.result.Util;
import de.ivu.wahl.result.builder.CombinedList;
import de.ivu.wahl.result.determination.P3List;

public class CheckedCombinedList {
  public final CombinedList _combinedList;
  public final List<P3List> _listsOverKT;
  public final List<P3List> _listsUnderKT;
  public final boolean _valid;

  public CheckedCombinedList(CombinedList combinedList,
      List<P3List> listsOverKT,
      List<P3List> listsUnderKT,
      boolean valid) {
    _combinedList = combinedList;
    _listsOverKT = Util.createUnmodifiableCopy(listsOverKT);
    _listsUnderKT = Util.createUnmodifiableCopy(listsUnderKT);
    _valid = valid;
  }
}