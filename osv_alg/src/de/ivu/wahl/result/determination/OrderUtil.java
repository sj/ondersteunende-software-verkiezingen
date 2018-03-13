/*
 * OrderUtil
 * 
 * Created on 06.08.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ivu.wahl.result.Util;

/**
 * sortAndGroup() creates sets of elements that are equivalent with respect to the order, and sorts
 * these equivalence classes.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class OrderUtil {
  public static <T> List<Set<T>> sortAndGroup(Collection<T> base, Comparator<T> comparator) {
    List<T> sorted = Util.asList(base);
    Collections.sort(sorted, comparator);

    List<Set<T>> result = new ArrayList<Set<T>>();
    Set<T> set = new HashSet<T>();
    for (int i = 0; i < sorted.size(); i++) {
      T each = sorted.get(i);
      set.add(each);
      if (i == sorted.size() - 1 || comparator.compare(each, sorted.get(i + 1)) != 0) {
        result.add(set);
        set = new HashSet<T>();
      }
    }

    return result;
  }
}
