/*
 * ElectoralDistrict
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;

/**
 * Electoral district with a name (for the user) and a number (for sorting).
 * ElectoralDistrict#compareTo() sorts by number.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface ElectoralDistrict
    extends
      NamedObject,
      NumberedObject,
      Comparable<ElectoralDistrict> {

  public long getVoteValue();
}
