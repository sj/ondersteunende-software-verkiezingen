/*
 * P3ListKey
 * 
 * Created on 26.08.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.result.model2builder;

import de.ivu.wahl.modell.GruppeComposite;

/**
 * External key for a P3 list in the determination of the election result.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class P3ListKey {

  private final String id_gruppe;

  /**
   * Constructor by String
   */
  @SuppressWarnings("hiding")
  public P3ListKey(String id_gruppe) {
    this.id_gruppe = id_gruppe;
  }

  /**
   * Constructor by GruppeComposite
   */
  public P3ListKey(GruppeComposite gruppe) {
    this(gruppe.getGruppe().getID_Gruppe());
  }

  @Override
  public String toString() {
    return id_gruppe;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id_gruppe == null) ? 0 : id_gruppe.hashCode());
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
    P3ListKey other = (P3ListKey) obj;
    if (id_gruppe == null) {
      if (other.id_gruppe != null) {
        return false;
      }
    } else if (!id_gruppe.equals(other.id_gruppe)) {
      return false;
    }
    return true;
  }

}
