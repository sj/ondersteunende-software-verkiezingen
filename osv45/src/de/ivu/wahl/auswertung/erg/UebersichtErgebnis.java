package de.ivu.wahl.auswertung.erg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.ivu.wahl.modell.GebietInfo;

/**
 * UebersichtErgebnis.java Objekt mit Informationen f�r die �bersicht der eingegangenen sowie der
 * ausstehenden Gebieten. Die Liste der Gebiete spiegelt z.B. bei der Bundestagswahl je nach Anfrage
 * das angeforderte Bundesland bzw. alle Bundesl�nder wieder, die wiederum mit den Wahlkreisen als
 * eine Liste von Gebieten bef�llt sind.
 * 
 * @author mur 15.10.2003 Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public class UebersichtErgebnis extends Ergebnis {
  private static final long serialVersionUID = 4835877538865988325L;

  private List<GebietInfo> _gebiete;

  public UebersichtErgebnis(String ergBezeichnung) {
    super(ergBezeichnung);
    _gebiete = new ArrayList<GebietInfo>();
  }

  /**
   * @param list mit Gebiet-Objekten bef�llt
   */
  public void setGebiete(List<GebietInfo> list) {
    _gebiete = list;
  }

  /**
   * @return Gebiete-Iterator zum iterieren �ber eine Liste von Gebiet-Objekten
   */
  public Iterator<GebietInfo> getGebiete() {
    return _gebiete.iterator();
  }

  public int getAnzahlGebiete() {
    return _gebiete.size();
  }

  /**
   * @param gebiet {@link GebietInfo}-Instanz des ensprechenden Gebiets
   */
  public void addGebiet(GebietInfo gebiet) {
    if (_gebiete == null) {
      _gebiete = new ArrayList<GebietInfo>();
    }
    _gebiete.add(gebiet);
  }
}
