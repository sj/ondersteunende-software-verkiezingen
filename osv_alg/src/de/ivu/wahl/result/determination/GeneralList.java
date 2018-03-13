/*
 * GeneralList
 * 
 * Created on 02.03.2009
 * Copyright (c) 2009 Kiesraad
 */
package de.ivu.wahl.result.determination;

import de.ivu.wahl.result.NamedObject;

/**
 * Tagging interface for different kinds of "lists", like CandidateLists, P2Lists, P3Lists and
 * P42Lists.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface GeneralList extends NamedObject {

  /**
   * @return a short representation like list number or combined list letter
   */
  String getKey();

  /**
   * @return a unique external identifier for this object
   */
  Object getExternalKey();

}
