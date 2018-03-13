package de.ivu.wahl.auswertung.sv;

import javax.ejb.EJBException;

import de.ivu.wahl.auswertung.erg.Besonderheiten;

/**
 * Session Bean which calculates the seat distribution
 * 
 * @author cos@ivu.de Copyright (c) 2004 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

public interface SitzverteilungHandling {
  /**
   * Distribute seats according to the latest valid voting results
   * 
   * @param id_Ergebniseingang
   * @return SitzverteilungStatus
   * @throws EJBException
   */
  SitzverteilungStatus berechneSitzverteilung(String id_Ergebniseingang) throws EJBException;

  /**
   * set the lot to the konflikt
   * 
   * @param id_konflikt
   * @param id_alternative
   * @throws EJBException
   */
  public void writeKonfliktWithLosAlternative(SitzverteilungStatus status, String id_alternative)
      throws EJBException;

  /**
   * Collect conflicts for GUI display
   * 
   * @param id_Ergebniseingang
   * @return
   * @throws EJBException
   */
  Besonderheiten getBesonderheiten(String id_Ergebniseingang) throws EJBException;

}