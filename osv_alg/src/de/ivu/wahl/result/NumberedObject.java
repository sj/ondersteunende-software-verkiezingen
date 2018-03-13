/*
 * NumberedObject
 * 
 * Created on 12.01.2009
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;

import java.util.Comparator;

/**
 * An object that has an integer number. This is mainly used for sorting purpose.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface NumberedObject {
  final static Comparator<NumberedObject> SORT_BY_NUMBER = new Comparator<NumberedObject>() {
    public int compare(NumberedObject o1, NumberedObject o2) {
      return Integer.valueOf(o1.getNumber()).compareTo(Integer.valueOf(o2.getNumber()));
    }
  };

  public int getNumber();

}
