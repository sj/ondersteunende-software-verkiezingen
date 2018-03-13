/*
 * P2ListKey
 * 
 * Created on 26.08.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.result.model2builder;

import de.ivu.wahl.modell.GruppeComposite.Liste;

/**
 * External key for a P2 list in the determination of the election result.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class P2ListKey {

  private final String id_Liste;

  /**
   * Constructor by Liste
   */
  public P2ListKey(Liste liste) {
    this.id_Liste = liste.getId_Liste();
  }

  /**
   * Constructor by String
   */
  @SuppressWarnings("hiding")
  public P2ListKey(String id_Liste) {
    this.id_Liste = id_Liste;
  }

  @Override
  public String toString() {
    return id_Liste;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id_Liste == null) ? 0 : id_Liste.hashCode());
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
    P2ListKey other = (P2ListKey) obj;
    if (id_Liste == null) {
      if (other.id_Liste != null) {
        return false;
      }
    } else if (!id_Liste.equals(other.id_Liste)) {
      return false;
    }
    return true;
  }

}
