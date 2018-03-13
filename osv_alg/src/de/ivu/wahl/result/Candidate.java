/*
 * Candidate
 * 
 * Created on 15.12.2008
 * Copyright (c) 2008 Kiesraad
 */
package de.ivu.wahl.result;


/**
 * A person that is nominated on one or multiple candidate lists. The candidate is directly related
 * to the person, i.e. a person that is nominated for different P2-lists or even P3-lists is
 * represented each time by the same {@link Candidate} object.
 * <p>
 * compareTo() sorts candidates by name.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public interface Candidate extends NamedObject, Comparable<Candidate> {
  public Object getExternalKey();
}
