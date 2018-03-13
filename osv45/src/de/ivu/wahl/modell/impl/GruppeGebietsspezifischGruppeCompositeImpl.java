/*
 * Created on 22.01.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.ivu.wahl.modell.impl;

import de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatenListe;
import de.ivu.wahl.modell.GruppeGebietsspezifischGruppeComposite;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeModel;

/**
 * Verbindet Gruppegebietssepezifisch mit der zugehoerigen Gruppe
 * 
 * @author bae
 */
public class GruppeGebietsspezifischGruppeCompositeImpl
    implements
      GruppeGebietsspezifischGruppeComposite {
  private static final long serialVersionUID = -5136738599454531815L;

  private final GruppeGebietsspezifischModel _gruppeGebietsspezifischModel;
  private final GruppeModel _gruppeModel;

  private final KandidatenListe _kandidatenListe;

  public GruppeGebietsspezifischGruppeCompositeImpl(GruppeGebietsspezifischModel gruppeGebietsspezifischModel,
      GruppeModel gruppeModel,
      KandidatenListe kandidatenListe) {
    _gruppeGebietsspezifischModel = gruppeGebietsspezifischModel;
    _gruppeModel = gruppeModel;
    _kandidatenListe = kandidatenListe;
  }

  public GruppeGebietsspezifischModel getGruppeGebietsspezifischModel() {
    return _gruppeGebietsspezifischModel;
  }

  public GruppeModel getGruppeModel() {
    return _gruppeModel;
  }

  /**
   * @return kandidatenListe.
   */
  public KandidatenListe getKandidatenListe() {
    return _kandidatenListe;
  }
}
