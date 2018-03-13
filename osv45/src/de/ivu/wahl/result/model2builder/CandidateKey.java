/*
 * CandidateKey
 * 
 * Created on 26.08.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.result.model2builder;

import de.ivu.wahl.modell.PersonendatenModel;

/**
 * External key for candidates in the determination of the election result.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class CandidateKey {

  private final String id_Personendaten;

  /**
   * Constructor by PersonendatenModel
   */
  public CandidateKey(PersonendatenModel personendatenModel) {
    this(personendatenModel.getID_Personendaten());
  }

  /**
   * Constructor by String
   */
  @SuppressWarnings("hiding")
  public CandidateKey(String id_Personendaten) {
    this.id_Personendaten = id_Personendaten;
  }

  @Override
  public String toString() {
    return id_Personendaten;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id_Personendaten == null) ? 0 : id_Personendaten.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CandidateKey other = (CandidateKey) obj;
    if (id_Personendaten == null) {
      if (other.id_Personendaten != null) {
        return false;
      }
    } else if (!id_Personendaten.equals(other.id_Personendaten)) {
      return false;
    }
    return true;
  }

}
