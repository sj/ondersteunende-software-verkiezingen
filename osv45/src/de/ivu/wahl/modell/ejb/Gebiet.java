/*
 * Gebiet
 * 
 * Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.modell.ejb;

import java.util.Collection;

import javax.ejb.EJBException;

/**
 * EJB-Schnittstelle der Entity Gebiet, die zu den Metadaten der Wahl geh�rt. Definiert auch die
 * gebietsspezifische Logik zur Verarbeitung eines Ergebniseingangs auf der jeweiligen Instanz des
 * Gebiets.
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public interface Gebiet extends BasicGebiet {

  /**
   * Gibt Auskunft �ber die Vollst�ndigkeit des Gebietes f�r die aktuelle Wahlergebnisart
   * 
   * @return <code>true</code> wenn das Gebiet vollst�ndig ist
   * @throws EJBException bei einem Problem
   */
  boolean isVollstaendig() throws EJBException;

  /**
   * Gibt den aktuellen Gebietsstatus (f�r die aktuelle Wahlergebnisart) zur�ck
   * 
   * @return aktueller Gebietsstatus (f�r die aktuelle Wahlergebnisart)
   * @throws EJBException bei einem Problem
   */
  Gebietsstatus getCurrentGebietsstatus() throws EJBException;

  /**
   * Gibt den aktuellen Gebietsstatus (f�r die gegebene Wahlergebnisart) zur�ck
   * 
   * @param wahlergebnisart Wahergebnisart
   * @return aktueller Gebietsstatus (f�r die gegebene Wahlergebnisart)
   * @throws EJBException bei einem Problem
   */
  Gebietsstatus getCurrentGebietsstatus(int wahlergebnisart) throws EJBException;

  /**
   * Gibt den Gebietsstatus f�r den �bergebenen Ergebniseingang zur�ck
   * 
   * @param ergebniseingang
   * @return Gebietsstatus f�r den �bergebenen Ergebniseingang zur�ck
   * @throws EJBException bei einem Problem
   */
  Gebietsstatus getGebietsstatusForErgebniseingang(Ergebniseingang ergebniseingang)
      throws EJBException;

  /**
   * Gibt alle direkten Kinder dieses Gebietes zur�ck, die insgesamt eingehen m�ssen, damit das
   * Gebiet vollst�ndig wird.
   * 
   * @return alle erwarteten direkten Kinder dieses Gebiets
   * @throws EJBException bei einem Problem
   */
  Collection<Gebiet> getGebietErwartetCol() throws EJBException;

  /**
   * Ermittelt den letzten g�ltigen Eingang f�r dieses Gebiet f�r die aktuelle Wahlergebnisart
   * 
   * @return Letzter g�ltiger Eingang f�r das Gebiet (bzw. eins seiner untergeordneten Gebiete),
   *         wenn es einen gibt, oder <code>null</code> sonst
   * @throws EJBException bei einem Problem
   */
  Ergebniseingang getLetzterGueltigerEingang() throws EJBException;

  /**
   * The latest voting result for this region with status FIRST_INPUT_OK
   * 
   * @param wahlergebnisart mode of input
   * @return
   * @throws EJBException
   */
  Ergebniseingang getLastValidFirstInput(int wahlergebnisart) throws EJBException;

  /**
   * The latest voting result for this region and this input mode
   * 
   * @param wahlergebnisart mode of input
   * @return
   * @throws EJBException
   */
  Ergebniseingang getLastInput(int wahlergebnisart) throws EJBException;

  /**
   * Ermittelt den letzten g�ltigen Eingang f�r dieses Gebiet f�r eine bestimmte Wahlergebnisart
   * 
   * @param wahlergebnisart Wahlergebnisart, f�r die der letzte g�ltige Eingang ermittelt werden
   *          soll
   * @return Letzter g�ltiger Eingang f�r das Gebiet (bzw. eins seiner untergeordneten Gebiete),
   *         wenn es einen gibt, oder <code>null</code> sonst
   * @throws EJBException bei einem Problem
   */
  Ergebniseingang getLetzterGueltigerEingang(int wahlergebnisart) throws EJBException;

  /**
   * Setzt den letzten g�ltigen Eingang f�r das Gebiet f�r die aktuelle Wahlergebnisart
   * 
   * @param ergebniseingang Letzter g�ltiger Eingang f�r das Gebiet (bzw. eins seiner
   *          untergeordneten Gebiete), wenn es einen gibt, oder <code>null</code> sonst
   * @throws EJBException bei einem Problem
   */
  void setLetzterGueltigerEingang(Ergebniseingang ergebniseingang) throws EJBException;

  /**
   * Setzt den letzten g�ltigen Eingang f�r das Gebiet f�r die explizit angegebene Wahlergebnisart
   * 
   * @param wahlergebnisart explizit angegebene Wahlergebnisart
   * @param ergebniseingang letzter g�ltiger Eingang f�r das Gebiet (bzw. eins seiner
   *          untergeordneten Gebiete), wenn es einen gibt, oder <code>null</code> sonst
   * @throws EJBException bei einem Problem
   */
  void setLetzterGueltigerEingang(int wahlergebnisart, Ergebniseingang ergebniseingang)
      throws EJBException;

  /**
   * Gibt die Anzahl erwarteter Gebiete einer bestimmten Gebietsart, die unter diesem Gebiet liegen
   * (inklusive dieses Gebiet selbst)
   * 
   * @param gebietsart Gebietsart der zu ber�cksichtigenden Gebiete
   * @return Anzahl erwarteter Gebiete einer bestimmten Gebietsart unter und inklusive diesem Gebiet
   * @throws EJBException bei einem Problem
   */
  int getAnzahlErwartetByGebietsart(int gebietsart) throws EJBException;

  /**
   * Liefert alle Gebiete in Hierarchie eines Gebietes (descendant-or-self) mit einer bestimmten
   * Art. Kann zum Beispiel genutzt werden, um alle Wahlkreise eines Landes oder des Bundes zu
   * erhalten.
   * 
   * @param gebietsart Gebietsart wie Land Wahlkreis ...
   * @return eine Collection von Gebieten
   * @see de.ivu.wahl.modell.GebietModel#GEBIETSART_WAHLKREIS ...
   * @throws EJBException bei einem Problem
   */
  Collection<Gebiet> getAllGebieteInHierarchieByGebietsart(int gebietsart) throws EJBException;

  /**
   * Gibt die Auswertungseinheit zur�ck, zu der dieses Gebiet geh�rt
   * 
   * @return die Auswertungseinheit, zu der dieses Gebiet zugeh�rt
   */
  Gebiet getAuswertungseinheit();

  /**
   * Gibt Auskunft, ob es sich bei diesem Gebiet um ein Wahlgebiet handelt
   * 
   * @return <code>true</code> wenn das Gebiet ein Wahlgebiet ist
   * @throws EJBException bei einem Problem
   */
  boolean isWahlgebiet();

  /**
   * @return the region number in the correct format (i.e. roman if needed)
   */
  String getGebietsnummerAnzeige();

  /**
   * @return true if lists are nominated for this region or a superior region
   */
  boolean hasLists();
}
