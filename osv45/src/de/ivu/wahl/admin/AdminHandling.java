/*
 * AdminHandling
 * 
 * Created on 17.10.2003 Copyright (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import java.util.Collection;

import javax.ejb.EJBException;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.RechtegruppeModel;

/**
 * SessionBean f�r Administrationsunterst�tzung
 * <P>
 * Enth�lt auch Methoden f�r das Handling von Schwellwerten, die aus der Applikation verwendet
 * werden. Zus�tzlich finden sich weitere n�tzlichen Methoden, die kein "nat�rliches" Pl�tzchen
 * gefunden haben
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface AdminHandling {

  /**
   * Pr�fen, ob bereits ein Ergebnis f�r eine Erfassungseinheit vorliegt (Ergebniseingang) Wird
   * verwendet, um das Zur�cksetzen einer Erfassungseinheit zu verhindern, die gar keinen
   * Ergebniseingang hat.
   * 
   * @param c Anwenderkontext
   * @param gebietsart Konstante f�r die Gebietsart
   * @param nummer Gebietsnummer (innerhalb der Gebietsart)
   * @return <code>true</code>, wenn es f�r die angegebene Erfassungseinheit ein Ergebnis gibt.
   * @throws EJBException genereller Fehler
   */
  boolean existsErgebniseingang(AnwContext c, int gebietsart, int nummer) throws EJBException;

  /**
   * Implementierung ist suboptimal und langsam Aber: wird ausschlie�lich vor einer Freigabe
   * gepr�ft, daher nicht weiter tragisch
   * 
   * @param c Anwenderkontext
   * @return <code>true</code>, wenn es irgendwelche Erfassungseinheiten auf der aktuellen Wahl
   *         gibt, die eine Warnung oder einen Fehler tragen.
   * @throws EJBException genereller Fehler
   */
  boolean existWarnungenOderFehler(AnwContext c) throws EJBException;

  /**
   * @param c Anwenderkontext
   * @param gebietsart Art der zu liefernden Gebiete
   * @return aller Gebiete mit der genannten Gebietsart als Wertobjekte
   * @throws EJBException genereller Fehler
   */
  Collection<GebietModel> getGebiete(AnwContext c, int gebietsart) throws EJBException;

  /**
   * @param gebietsart Art der zu liefernden Gebiete
   * @return aller Gebiete mit der genannten Gebietsart als Wertobjekte
   * @throws EJBException genereller Fehler
   */
  Collection<GebietModel> getGebiete(int gebietsart) throws EJBException;

  /**
   * @param context Anwenderkontext
   * @param gebietart Gebietsart des gesuchten Gebiets
   * @param gebietNr Gebietsnummer des gesuchten Gebiets
   * @return Gebiet mit der angegebenen Nummer und Art als Wertobjekt
   * @throws EJBException genereller Fehler
   */
  GebietModel getGebietModel(AnwContext context, int gebietart, int gebietNr) throws EJBException;

  /**
   * @param context Anwenderkontext
   * @param gebietart Gebietsart des untergeordneten Gebiets
   * @param gebietNr Gebietsnummer des untergeordneten Gebiets
   * @return unmittelbar zum Gebiet mit der angegebenen Nummer und Art �bergeordnetes Gebiet als
   *         Wertobjekt
   * @throws EJBException genereller Fehler
   */
  GebietModel getGebietModelParent(AnwContext context, int gebietart, int gebietNr)
      throws EJBException;

  /**
   * Property besorgen, gedoppelt f�r Interfacnig mit dem Client: m�glicherweise Korrekturen oder
   * Speicherung an verschiedene Orten!
   * 
   * @param name Name der Property
   * @return Anwendungsproperty
   * @throws EJBException genereller Fehler
   */
  String getProperty(String name) throws EJBException;

  /**
   * @return alle Rechtegruppen als Wertobjekte
   * @throws EJBException genereller Fehler
   */
  Collection<RechtegruppeModel> getRechtegruppe() throws EJBException;

  /**
   * @param c Anwednerkontext
   * @return alle Wahleinheiten der durch den Anwenderkontext bestimmten Wahl
   * @throws EJBException genereller Fehler
   */
  Collection<GebietModel> getWahleinheiten(AnwContext c) throws EJBException;

  /**
   * Init der Schwellwerte und anderer Ausgangswerte der Anwendung f�r eine Wahl
   * 
   * @param id_Wahl Prim�rschl�ssel der Wahl, f�r die die Ausgangswerte gesetzt werden sollen
   * @throws EJBException genereller Fehler
   */
  void initAdministrationValues(String id_Wahl) throws EJBException;

  /**
   * @param c Anwenderkontext
   * @param gebietsart Gebietsart des gesuchten Gebiets
   * @param nummer Gebietsnummer des gesuchten Gebiets
   * @return <code>true</code>, wenn ein Anwender die angegebene Erfassungseinheit zur�cksetzen
   *         darf. Dies ist der Fall, wenn exakt ein einziger Ergebniseingang vorliegt und das
   *         �bergeordnete Gebiet noch nicht vollst�ndig ist.
   * @throws EJBException genereller Fehler
   */
  boolean isZuruecksetzenErlaubtAnwender(AnwContext c, int gebietsart, int nummer)
      throws EJBException;

  /**
   * @param c Anwenderkontext
   * @param name Name der Property
   * @param val Wert der Property
   * @throws EJBException genereller Fehler
   */
  void setProperty(AnwContext c, String name, boolean val) throws EJBException;

  /**
   * Property Schreiben
   * 
   * @param c Anwenderkontext
   * @param name Name der Property
   * @param val der Wert der Property
   * @throws EJBException genereller Fehler
   */
  void setProperty(AnwContext c, String name, String val) throws EJBException;

  /**
   * Schreiben in das ApplicationLog f�r Clientkomponenten
   * 
   * @param c Anwenderkontext
   * @param message Nachricht f�r das Log
   * @throws EJBException genereller Fehler
   */
  void writeAppLog(AnwContext c, String message) throws EJBException;

  void setPersonendatenBenennbar(String idPersonendaten, boolean benennbar) throws EJBException;

  void setVoteValue(String idRegion, int voteValue) throws EJBException;

  void setGebietNameUndWahlberechtigte(String idGebiet, String name, int eligible_voter)
      throws EJBException;

  void setWahlStatus(String idWahl, int status) throws EJBException;

  void deleteGebiet(String idGebiet) throws EJBException;

  void updatePollingStations(Collection<GebietModel> pollingStations);

  /**
   * Re-creates the indexes of the table stimmergebnis
   * 
   * @return
   */
  public boolean reIndexStimmergebnis();

}