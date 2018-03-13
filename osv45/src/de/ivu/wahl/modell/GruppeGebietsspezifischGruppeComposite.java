/*
 * Created on 22.01.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.ivu.wahl.modell;

import java.io.Serializable;

import de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatenListe;

/**
 * Verbindet GruppeGebietssepezifisch mit der zugehoerigen Gruppe
 * 
 * @author bae
 */
public interface GruppeGebietsspezifischGruppeComposite extends Serializable {

  /**
   * Liefert das gekapselte {@link GruppeGebietsspezifischModel}
   * 
   * @return das gekapselte {@link GruppeGebietsspezifischModel}
   */
  GruppeGebietsspezifischModel getGruppeGebietsspezifischModel();

  /**
   * Liefert das gekapselte {@link GruppeModel}
   * 
   * @return das gekapselte {@link GruppeModel}
   */
  GruppeModel getGruppeModel();

  /**
   * Liefert die gekapselte {@link KandidatenListe}
   * 
   * @return das gekapselte {@link KandidatenListe}
   */
  KandidatenListe getKandidatenListe();

}
