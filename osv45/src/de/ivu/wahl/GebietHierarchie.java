/*
 * GebietHierarchie
 * 
 * Created on 06.02.2004
 * Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import de.ivu.ejb.IVUBeanBase;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.GebietHome;

/**
 * Gibt zu jeder ID_Gebiet die Liste der Schl�ssel aller Nachkommen inklusive des Gebiets selbst.
 * 
 * @author cos@ivu.de IVU Traffic Technologies AG
 */

public class GebietHierarchie implements Serializable {
  private static final long serialVersionUID = 7498748186004865494L;

  /**
   * Kombinierter Gebietsschl�ssel. Fasst zwei Identit�tsangaben eines Gebiets zusammen:
   * Wahlunabh�ngig �ber Gebietsart und Gebietsnummer und Wahlabh�ngig �ber den Prim�rschl�ssel.
   * 
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  public static class GebietKey implements Serializable {
    private static final long serialVersionUID = 4817617457740621450L;
    private String _id_Gebiet;
    // optionaler Eintrag, wird nicht zum Vergleich herangezogen
    private final int _gebietsart;
    private final int _gebietsnummer;

    /**
     * @param gebietsID
     * @param gebietsart
     * @param gebietsnummer
     */
    GebietKey(String id_Gebiet, final int gebietsart, final int gebietsnummer) {
      _id_Gebiet = id_Gebiet;
      _gebietsart = gebietsart;
      _gebietsnummer = gebietsnummer;
    }

    /**
     * @param gebietsart
     * @param gebietsnummer
     */
    public GebietKey(final int gebietsart, final int gebietsnummer) {
      _gebietsart = gebietsart;
      _gebietsnummer = gebietsnummer;
    }

    /**
     * @return Prim�rschl�ssel des Gebiets
     */
    public String getID_Gebiet() {
      return _id_Gebiet;
    }

    /**
     * @return Gebietsart des Gebiets
     */
    public int getGebietsart() {
      return _gebietsart;
    }

    /**
     * @return Gebietsnummer des Gebiets (mindestens innerhalb der Gebietsart eindeutig)
     */
    public int getGebietsnummer() {
      return _gebietsnummer;
    }

    @Override
    public boolean equals(Object obj) {
      GebietKey other = (GebietKey) obj;
      return other._gebietsnummer == _gebietsnummer && other._gebietsart == _gebietsart;
    }

    @Override
    public int hashCode() {
      return _gebietsnummer + (_gebietsart << 16);
    }

    @Override
    public String toString() {
      return _id_Gebiet;
    }
  }

  private static final Map<String, Set<GebietKey>> __linGebietshierachie = new HashMap<String, Set<GebietKey>>();

  /**
   * Liefert die Aufz�hlung aller Gebiete des ausgew�hlten Teilbaums.
   * 
   * @param id_Gebiet Wurzelgebiet des Teilbaums
   * @return alle Gebiete des Teilbaums, inklusive der Wurzel, als {@link GebietKey}
   */
  public static synchronized Set<GebietKey> getAlleGebiete(final String id_Gebiet) {
    try {
      Set<GebietKey> alleGebiete = __linGebietshierachie.get(id_Gebiet);
      if (alleGebiete == null) {
        alleGebiete = new HashSet<GebietKey>();
        if (id_Gebiet != null) {
          GebietHome gebietHome = (GebietHome) IVUBeanBase.findLocalHomeExt("Gebiet"); //$NON-NLS-1$
          Gebiet gebiet = gebietHome.findByPrimaryKey(id_Gebiet);
          addGebiet(alleGebiete, gebiet);
          __linGebietshierachie.put(id_Gebiet, alleGebiete);
        }
      }
      return alleGebiete;
    } catch (FinderException e) {
      throw new EJBException(e);
    }
  }

  private static void addGebiet(final Set<GebietKey> alleGebiete, final Gebiet gebiet) {
    Set<GebietKey> vorhandenesGebiet = __linGebietshierachie.get(gebiet.getID_Gebiet());
    if (vorhandenesGebiet != null) {
      alleGebiete.addAll(vorhandenesGebiet);
    } else {
      if (!alleGebiete.add(new GebietKey(gebiet.getID_Gebiet(), gebiet.getGebietsart(), gebiet
          .getNummer()))) {
        throw new RuntimeException(Messages
            .getString(MessageKeys.Error_ZyklischerGebietsgrafStattBaum));
      }
      Collection<Gebiet> untergeordneteGebiete = gebiet.getGebietCol();
      for (Gebiet untergeordnetesGebiet : untergeordneteGebiete) {
        addGebiet(alleGebiete, untergeordnetesGebiet);
      }
    }
  }
}