/*
 * AnwenderHandling
 * 
 * Copyright (c) 2002-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.anwender;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.RechtegruppeModel;

/**
 * SessionBean zum Verwalten der Anwender
 * 
 * @author cos@ivu.de klie@ivu.de
 */
public interface AnwenderHandling {

  /**
   * Passwort des angemeldeten Anwenders �ndern
   * 
   * @param anwContext Anwenderkontext
   * @param oldPW old password (entered by the user)
   * @param newPW new password
   * @throws AnwenderException wenn die Parameter nicht stimmen (null, altes PW falsch oder PW
   *           unver�ndert)
   * @throws EJBException bei einem allgemeinen Problem
   */
  void changePassword(AnwContext anwContext, String oldPW, String newPW)
      throws EJBException, AnwenderException;

  /**
   * Universelle Methode zur Administration von Anwendern
   * 
   * @param anwContext Anwenderkontext des Aufrufers
   * @param anmeldename User ID
   * @param name echter Name des Anwenders
   * @param passwortHash Pr�fsumme aus Anmeldename und Passwort
   * @param id_Rechtegruppen Collection von Prim�rschl�sseln der Rechtegruppen, denen der Anwender
   *          angeh�rt
   * @param id_Gebiet Prim�rschl�ssel des Gebietes, dem der Anwender angeh�rt
   * @param anzahlFehlversuche Anzahl fehlerhafter Anmeldungen (nur sinnvoll bei der Replikation)
   * @param create wenn <code>true</code> soll der Anwender neu erstellt werden, wenn er nicht schon
   *          existiert
   * @param modify wenn <code>true</code> darf der Anwender auch dann ge�ndert werden, wenn er schon
   *          existiert
   * @throws AnwenderException wenn der Anwender nicht gefunden wurde, aber auch nicht neu erstellt
   *           werden soll
   * @throws EJBException bei einem allgemeinen Problem
   */
  void createOrModifyAnwender(AnwContext anwContext,
      String anmeldename,
      String name,
      String passwortHash,
      Collection<String> id_Rechtegruppen,
      String id_Gebiet,
      int anzahlFehlversuche,
      boolean create,
      boolean modify) throws AnwenderException, EJBException;

  /**
   * Pr�fen, ob ein Anwender etwas darf
   * 
   * @param ac Anwenderkontext
   * @param right das Recht, das gepr�ft wird
   * @return <code>true</code>, wenn der Anwender darf
   * @throws EJBException wenn es nicht m�glich war, die Rechte des Anwenders zu ermitteln
   */
  boolean checkRight(AnwContext ac, String right) throws EJBException;

  /**
   * Legt Admin und System-Anwender an.
   * 
   * @throws EJBException bei einem Problem mit der Erzeugung
   */
  void createWurzelAnwender() throws EJBException;

  /**
   * Findet alle Modellobjekte einer bestimmten Gebietsart f�r eine Wahl
   * 
   * @param id_Wahl Prim�rschl�ssel der Wahl f�r die die Gebietsmodelle gesucht werden
   * @param gebietsart Gebietsart die gesucht wird
   * @return List aller Gebiete der Wahl mit der bestimmten Gebietsart als GebietModel
   */
  List<GebietModel> getAllGebieteForWahl(String id_Wahl, int gebietsart);

  /**
   * Findet alle Modelle der Kindergebiete eines Gebietes
   * 
   * @param id_Gebiet Prim�rschl�ssel des Elterngebietes
   * @return List aller Kindergebiete als GebietModel
   */
  List<GebietModel> getAllChildGebieteForGebiet(String id_Gebiet);

  /**
   * Findet ale verf�gbaren Rechtegruppen
   * 
   * @return List aller Rechtegruppen als RechtegruppeModel
   * @throws EJBException bei einem allgemeinen Fehler
   */
  List<RechtegruppeModel> getAllRechtegruppen() throws EJBException;

  /**
   * Findet alle angemeldeten Anwender
   * 
   * @return alle angemeldeten Anwender als Collection von Anmelde-Datens�tzen
   */
  Collection<Anmeldung> getAngemeldeteAnwender();

  /**
   * Findet alle f�r eine bestimmte Wahl angemeldeten Anwender
   * 
   * @param id_Wahl Prim�rschl�ssel der Wahl
   * @return alle angemeldeten Anwender einer Wahl als Collection von Anmelde-Datens�tzen
   */
  Collection<Anmeldung> getAngemeldeteAnwender(String id_Wahl);

  /**
   * Gibt die aktuellen Rechte des Anwenders zur�ck (das auf dem Server gecachte AnwRechte Objekt)
   * 
   * @param ac Anwenderkontext
   * @return aktuellen Rechte des Anwenders als <code>AnwRechte</code>-Objekt
   * @throws EJBException bei einem Problem
   */
  AnwRechte getAnwRechte(AnwContext ac) throws EJBException;

  /**
   * Initialisieren der Rechte und Rechtegrupen in der Datenbank; k�mmert sich noch nicht um das
   * L�schen von alten Rechten und Rechtegruppen.
   * 
   * @throws EJBException bei einem Problem
   */
  void initRechteUndRechteGruppen() throws EJBException;

  /**
   * Pr�fung des P�rchens anwendername/hash. Stimmt dies, gibt es den Anwenderkontext zur�ck. Die
   * Methode pr�ft auch auf �berschreiten der max. Anzahl Fehlversuche und registriert einen
   * Anwender als angemeldet, wenn die Pr�fung erfolgreich verl�uft.
   * 
   * @param anwendername Anmeldename
   * @param hash Pr�fsumme aus Anmeldename und Passwort
   * @param id_Wahl Prim�rschl�ssel der Wahl, f�r die sich der Anwender anmeldet
   * @param sessionID SessionID f�r die der Anwender angemeldet ist
   * @return Anwenderkontext oder <code>null</code> wenn ung�ltig
   * @throws EJBException bei einem Problem
   * @throws AnmeldeFehlversuchException bei falschem Passwort
   */
  AnwContext login(String anwendername, String hash, String id_Wahl, String sessionID)
      throws EJBException, AnmeldeFehlversuchException;

  /**
   * @param anwendername user name
   * @return In case the user is found in the database, return his/her password salt. Otherwise
   *         return an empty string.
   */
  String getSalt(String anwendername) throws EJBException;

  /**
   * Abmelden des Anwenders: Entfernt einen Anwender aus der Liste der angemeldeten Anwender.
   * 
   * @param ac Anwenderkontext
   */
  void logout(AnwContext ac);

  /**
   * Setzt die Markierung, dass Rechte ge�ndert wurden
   * 
   * @param id_Anwender Prim�rschl�ssel des Anwenders, f�r den die Rechte ge�ndert wurden
   */
  void rightsChanged(String id_Anwender);

  /**
   * Anwender l�schen (inklusive aller Beziehungen und aller abh�ngigen Entities)
   * 
   * @param id_Anwender Prim�rschl�ssel des Anwenders
   * @throws FinderException wenn der Anwender nicht gefunden werden konnte
   * @throws RemoveException wenn der Anwender nicht aus der Datenbank entfernt werden konnte
   * @throws EJBException bei einem allgemeinen Problem
   */
  void delAnwender(String id_Anwender) throws FinderException, EJBException, RemoveException;

  /**
   * Pr�ft, ob der Anwender einem Gebiet zugeordnet ist.
   * 
   * @param id_Anwender Prim�rschl�ssel des Anwenders
   * @return <code>false</code> wenn Anweder keinem Gebiet zugeordnet ist (id_Gebiet == null), sonst
   *         <code>true</code>
   * @throws FinderException wenn der Anwender nicht gefunden wurde
   */
  boolean hasAnwenderGebiet(String id_Anwender) throws FinderException;

  /**
   * @return <code>true</code> wenn der Default-Anwender (noch) das Default-Passwort hat.
   */
  boolean hasDefaultUserUnchangedPasswort();

}
