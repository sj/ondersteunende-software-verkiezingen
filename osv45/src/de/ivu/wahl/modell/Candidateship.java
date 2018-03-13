/*
 * Candidateship
 * 
 * Created on 17.11.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell;

import java.util.Comparator;

import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Gruppe;
import de.ivu.wahl.modell.ejb.GruppeGebietsspezifisch;
import de.ivu.wahl.modell.ejb.Liste;
import de.ivu.wahl.modell.ejb.Listenkandidatur;
import de.ivu.wahl.modell.ejb.Personendaten;

/**
 * Immutable object.
 * <p>
 * All information about a Listenkandidatur restricted to a given region.
 * 
 * @author jon@ivu.de, IVU Traffic Technologies AG
 */
public class Candidateship {
  public static Comparator<Candidateship> SORT_BY_POSITION_IN_LIST = new Comparator<Candidateship>() {
    public int compare(Candidateship x, Candidateship y) {
      return x.getPositionInList() - y.getPositionInList();
    }
  };

  private final Personendaten personendaten;
  private final Listenkandidatur listenkandidatur;
  private final GruppeGebietsspezifisch gruppeGebietsspezifisch;
  private final Gruppe gruppe;
  private final Gebiet region;

  /**
   * Constructor
   */
  @SuppressWarnings("hiding")
  public Candidateship(Personendaten personendaten,
      Listenkandidatur lk,
      Gebiet region,
      GruppeGebietsspezifisch gruppeGebietsspezifisch,
      Liste liste,
      Gruppe gruppe) {
    this.personendaten = personendaten;
    this.listenkandidatur = lk;
    this.gruppeGebietsspezifisch = gruppeGebietsspezifisch;
    this.gruppe = gruppe;
    this.region = region;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + gruppeGebietsspezifisch.getID_GruppeGebietsspezifisch().hashCode();
    result = prime * result + personendaten.getID_Personendaten().hashCode();
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
    Candidateship other = (Candidateship) obj;
    if (gruppeGebietsspezifisch == null) {
      if (other.gruppeGebietsspezifisch != null) {
        return false;
      }
    } else if (!gruppeGebietsspezifisch.equals(other.gruppeGebietsspezifisch)) {
      return false;
    }
    if (personendaten == null) {
      if (other.personendaten != null) {
        return false;
      }
    } else if (!personendaten.equals(other.personendaten)) {
      return false;
    }
    return true;
  }

  public Listenkandidatur getListenkandidatur() {
    return listenkandidatur;
  }

  public Personendaten getPersonendaten() {
    return personendaten;
  }

  public Gebiet getRegion() {
    return region;
  }

  public String getID_Gruppe() {
    return gruppe.getID_Gruppe();
  }

  public int getPositionInList() {
    return listenkandidatur.getListenplatz();
  }

  public String getPublicationLanguage() {
    return listenkandidatur.getListe().getPublicationLanguage();
  }

}
