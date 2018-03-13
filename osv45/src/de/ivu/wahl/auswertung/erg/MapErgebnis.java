/*
 * MapErgebnis
 * 
 * Created on 11.07.2005
 * Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg;

import java.util.Map;

import de.ivu.wahl.modell.GebietModel;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class MapErgebnis extends Ergebnis {
  private static final long serialVersionUID = -6721098513787211631L;

  private Map<GebietModel, String> _map;

  /**
   * @param ergBezeichnung
   */
  public MapErgebnis(String ergBezeichnung) {
    super(ergBezeichnung);
  }

  /**
   * @param anderesErgebnis
   */
  public MapErgebnis(Ergebnis anderesErgebnis) {
    super(anderesErgebnis);
  }

  /**
   * @return map
   */
  public Map<GebietModel, String> getMap() {
    return _map;
  }

  /**
   * @param map Neuer Wert f�r map
   */
  public void setMap(Map<GebietModel, String> map) {
    _map = map;
  }
}
