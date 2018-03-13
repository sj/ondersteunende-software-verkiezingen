package de.ivu.wahl.modell.ejb;

import de.ivu.wahl.modell.GruppeGebietsspezifischGruppeComposite;
import de.ivu.wahl.modell.GruppeModel;

/**
 * GruppeGebietsspezifisch corresponds to a OSV candidate list, i.e. it belongs to (usually) one
 * political party and one electoral district.
 */

public interface GruppeGebietsspezifisch extends BasicGruppeGebietsspezifisch {

  /**
   * @return Komplexer Datenbehälter mit Daten des {@link GruppeModel} und
   *         {@link GruppeGebeitsspezifischModel}
   */
  GruppeGebietsspezifischGruppeComposite getGruppeGebietsspezifischGruppeComposite();
}
