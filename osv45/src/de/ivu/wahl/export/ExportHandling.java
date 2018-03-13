/*
 * AuswertungHandling
 * 
 * Created on 15.10.2003
 * Copyright (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.export;

import javax.ejb.EJBException;

import nu.xom.Document;

import de.ivu.wahl.auswertung.erg.ResultSummary;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * All eml exports are generated here
 * 
 * @author ugo@ivu.de, IVU Traffic Technologies AG
 */
public interface ExportHandling {

  /**
   * Creates xml-document containing all polling stations
   */
  public Document createEML110() throws EJBException;

  /**
   * Creates xml-document as base for P22
   * 
   * @param id_Ergebniseingang the import action for which the results are exported
   * @param forCandidateLetters do include RG-Node for candidate letters
   * @param forProtocolAppendix
   * @return
   * @throws EJBException technical problem
   * @throws ImportException
   */
  public Document createEML520(String id_Ergebniseingang,
      boolean forCandidateLetters,
      boolean forProtocolAppendix) throws EJBException, ImportException;

  /**
   * Creates xml-document EML 510 containing voting results
   * 
   * @param gebiet region for which the results are exported
   * @return
   * @throws EJBException
   * @throws ImportException
   */
  public Document createEML510(Gebiet gebiet, boolean createRGNodes)
      throws EJBException, ImportException;

  /**
   * Creates xml-document EML 510 containing voting results
   * 
   * @param gebiet region for which the results are exported
   * @param createRGNodes true if ReportGenerator elements shall be created
   * @param create510dForPSB
   * @param emptyResults true if an empty EML (without voting results) shall be created
   */
  public Document createEML510(Gebiet gebiet,
      boolean createRGNodes,
      boolean create510dForPSB,
      boolean emptyResults) throws EJBException, ImportException;

  /**
   * Creates xml-document EML 510 containing empty voting results
   * 
   * @param wurzelgebiet
   * @return
   * @throws EJBException
   * @throws ImportException
   */
  public Document createEmptyEML510(Gebiet wurzelgebiet) throws EJBException, ImportException;

  /**
   * Creates a CSV export of the votes per candidate
   */
  public String createVotesCsvExport(ResultSummary resultSummary, Document eml510)
      throws EJBException, ImportException;

  /**
   * Creates a CSV export of the names and addresses of all candidates (osv5-5)
   */
  public String createCandidateAddressesCsvExport() throws EJBException, ImportException;

  /**
   * Creates a CSV export of the names and addresses of elected candidates (osv5-6)
   */
  public String createCandidateDetailsCsvExport() throws EJBException, ImportException;

  /**
   * Creates or updates a zip file containing all files in the documents folder and the archive
   * folder.
   */
  public void updateBackupArchive();
  
  /**
   * Creates or updates a zip file containing all election results of P4 and P5.
   */
  public void updateElectionResultArchive();
}
