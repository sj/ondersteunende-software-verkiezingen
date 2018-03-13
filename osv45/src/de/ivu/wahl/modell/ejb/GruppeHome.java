package de.ivu.wahl.modell.ejb;

import java.util.Collection;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

/**
 * Interface for services related to the {@link Gruppe} entity.
 * <p>
 * Gruppe corresponds to P3-List in the DotER specification or to \"Listengruppen und nicht zu einer
 * Listengruppe gehörenden Sätzen identischer Listen und unabhängigen Listen\" in the electoral law.
 * A Gruppe consists of one or more {@link Liste}n (P2-lists).
 * <p>
 * There are also special / technical Gruppen for "Wahlberechtigte", "Wähler", "Ungültige Stimmen"
 * etc.
 * 
 * @see https://confluence.ivu.de/display/OSV/Listen-Ontologie
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */

public interface GruppeHome extends BasicGruppeHome {

  /**
   * Find all by election and kind of group
   */
  Collection<Gruppe> findAllByWahlAndGruppenart(String id_Wahl, int gruppenart)
      throws FinderException, EJBException;

  @Deprecated
  Map<String, Number> getAnzahlSitzeProGruppeAufGebiet(String id_Gebiet, int wahlergebnisart)
      throws FinderException, EJBException;

}