/*
 * GewinnerErgebnis
 * 
 * Created on 19.01.2005
 * Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.auswertung.erg.sv.kandidat;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import de.ivu.wahl.auswertung.erg.Ergebnis;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class GewinnerErgebnis extends Ergebnis {
  private static final long serialVersionUID = 7787425521014968593L;

  private Map<String, List<Serializable>> _listMitGGundIhrenEingegangenenGebietsIDs;

  /**
   * @param ergBezeichnung
   */
  public GewinnerErgebnis(String ergBezeichnung) {
    super(ergBezeichnung);
  }

  /**
   * @param anderesErgebnis
   */
  public GewinnerErgebnis(Ergebnis anderesErgebnis) {
    super(anderesErgebnis);
  }

  /**
   * @return Gibt listMitGGundIhrenEingegangenenGebietsIDs zur�ck.
   */
  public Map<String, List<Serializable>> getListMitGGundIhrenEingegangenenGebietsIDs() {
    if (_listMitGGundIhrenEingegangenenGebietsIDs == null) {
      return Collections.emptyMap();
    }
    return _listMitGGundIhrenEingegangenenGebietsIDs;
  }

  /**
   * @param listMitGGundIhrenEingegangenenGebietsIDs Neuer Wert f�r
   *          listMitGGundIhrenEingegangenenGebietsIDs.
   */
  public void setListMitGGundIhrenEingegangenenGebietsIDs(Map<String, List<Serializable>> listMitGGundIhrenEingegangenenGebietsIDs) {
    _listMitGGundIhrenEingegangenenGebietsIDs = listMitGGundIhrenEingegangenenGebietsIDs;
  }
}
