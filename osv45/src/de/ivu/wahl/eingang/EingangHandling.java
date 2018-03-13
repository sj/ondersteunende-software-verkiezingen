/*
 * EingangHandling
 * 
 * Created on 14.10.2003
 * Copyright (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.eingang;

import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.exception.ImportException;

/**
 * Lokales Interface f�r die wesentlichen Verarbeitungsfunktionen bei der Eingabe der
 * Erfassungseinheiten und administrativer Nachrichten �ber Dialog oder per Datei.
 * 
 * @author cos@ivu.de (c) 2003-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
public interface EingangHandling {

  /**
   * Verarbeiten einer Eingangsnachricht. Es werden alle n�tigen Schritte zur Verarbeitung
   * durchgef�hrt. Fehler gehen ins Log oder werden als Nachricht (Benachrichtigung) versandt.
   * 
   * @param msg Eingangsnachricht
   * @throws ImportException Fehler bei der Eingangsverarbeitung
   * @throws EJBException genereller Fehler
   */
  void processInputMsg(EingangMsg msg) throws ImportException, EJBException;

  /**
   * Create data object with region metadata structure for display of voting results or input of
   * next result resp., fill with previous result and warnings or errors if applicable
   * 
   * @param anwContext user context
   * @param regionCategory region category
   * @param regionNumber region number
   * @param forDisplay <code>true</code> ask for valid results only <code>false</code> initialize
   *          input GUI
   * @return data object with metadata and voting results
   * @throws EJBException any problem
   */
  GUIEingangMsg getGUIMsg(AnwContext anwContext,
      int regionCategory,
      int regionNumber,
      boolean forDisplay) throws EJBException;

  /**
   * This method returns the source attribute or what #getGUIMsg() would give.
   */
  int sourceForGUIMsg(AnwContext anwContext, int gebietsart, int gebietsnummer, boolean forDisplay)
      throws EJBException;

  /**
   * Create data object with region metadata structure for display of voting results or input of
   * next result resp., fill with previous result and warnings or errors if applicable
   * 
   * @param anwContext user context
   * @param gebiet
   * @param forDisplay <code>true</code> ask for valid results only <code>false</code> initialize
   *          input GUI
   * @return data object with metadata and voting results
   * @throws EJBException any problem
   */
  GUIEingangMsg getGUIMsg(AnwContext anwContext, Gebiet gebiet, boolean forDisplay)
      throws EJBException;

  /**
   * @param anwContext
   * @param gebietEjb
   * @param forDisplay <code>true</code> ask for valid results only <code>false</code> initialize
   *          input GUI
   * @param lastInputWithLastStatus
   * @return
   */
  GUIEingangMsg getGUIMsg(AnwContext anwContext,
      Gebiet gebiet,
      boolean forDisplay,
      boolean lastInputWithLastStatus);

  /**
   * The administrators can remove a user input lock. The administrators should handle this
   * possibility carefully.
   * 
   * @param anwContext User context of the administrator
   * @param id_Gebiet region which shall be set free for input
   * @return id_Anwender of user who was holding the lock
   */
  String removeLock(AnwContext anwContext, String id_Gebiet);

  /**
   * The administrators can remove a user input lock for all areas. The administrators should handle
   * this possibility carefully.
   * 
   * @param anwContext User context of the administrator
   */
  void removeLockForUser(AnwContext anwContext);

  /**
   * remove a input lock for the user.
   * 
   * @param anwContext User context to unlock
   * @param id_Gebiet region which shall be set free for input
   */
  void removeLockForUser(AnwContext anwContext, String id_Gebiet);

  /**
   * The program flow lock the area input for an User.
   * 
   * @param anwContext User context for the administrator
   * @param id_Gebiet region which shall be set free for input
   * @return id_Anwender of user who was holding the lock
   */
  String lock(AnwContext anwContext, String id_Gebiet);

  /**
   * The administrators can lock all areas for them. The administrators should handle this
   * possibility carefully.
   * 
   * @param anwContext User context for the administrator
   * @return <code>false</code> if another user was holding the lock on at least one region.
   *         <code>true</code> if lock on all regions was successfully set.
   * @throws FinderException if no region is found
   */
  boolean lockAllRegions(AnwContext anwContext) throws FinderException;

  Map<String, String> getINPUT_MAP();

  /**
   * Allow or forbid manual input.
   * 
   * @param anwContext
   * @param gebietsartErfassungseinheit
   * @param nummerErfassungseinheit
   * @param guiEingabeErlaubt
   */
  void setGUIEingabeErlaubt(AnwContext anwContext,
      int gebietsartErfassungseinheit,
      int nummerErfassungseinheit,
      boolean guiEingabeErlaubt);

  /**
   * @param anwContext
   * @param gebietsart
   * @param gebietsnummer
   * @return
   */
  Gebiet getGebiet(AnwContext anwContext, int gebietsart, int gebietsnummer);

  /**
   * @param msg
   * @return
   */
  String processInput(EingangMsg msg) throws ImportException;

}
