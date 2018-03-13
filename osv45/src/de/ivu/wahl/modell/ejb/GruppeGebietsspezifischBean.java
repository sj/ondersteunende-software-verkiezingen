package de.ivu.wahl.modell.ejb;

/**
 * GruppeGebietsspezifischBean
 */

import static de.ivu.ejb.fw.DBABase.retrieveIDs;
import static de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA.ID_GEBIET;
import static de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA.ID_GEBIET_QUAL;
import static de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA.ID_GRUPPE;
import static de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA.ID_GRUPPEGEBIETSSPEZIFISCH;
import static de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA.ID_GRUPPE_QUAL;
import static de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA.POSITION;
import static de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA.TABLENAME;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.GruppeGebietsspezifischGruppeComposite;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.impl.BasicGebietDBA;
import de.ivu.wahl.modell.impl.BasicGruppeDBA;
import de.ivu.wahl.modell.impl.GruppeGebietsspezifischDBA;
import de.ivu.wahl.modell.impl.GruppeGebietsspezifischGruppeCompositeImpl;

/**
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class GruppeGebietsspezifischBean extends BasicGruppeGebietsspezifischBean {
  private static final long serialVersionUID = 7851027871136929781L;

  private static final Category LOGGER = Log4J.configure(GruppeGebietsspezifischBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(GruppeGebietsspezifischBean.class, Log4J
        .extractVersion("$Revision: 1.18 $"))); //$NON-NLS-1$
  }

  /**
   * Führt eine Suche mit einem (oder keinem) erwarteten Ergebnis aus
   * 
   * @param query Suchanfrage (mit Platzhaltern)
   * @param params Parameter der Anfrage
   * @return das einzelne Ergebnis der Abfrage
   * @throws ObjectNotFoundException wenn keine Instanz gefunden werden konnte
   * @throws FinderException bei einem anderen Problem bei der Suche
   */
  private String findSingle(String query, Object[] params)
      throws ObjectNotFoundException, FinderException {

    try {
      return findSingle(retrieveIDs(query, params));
    } catch (SQLException se) {
      throw new FinderException(se.getMessage());
    }
  }

  /**
   * @param id_Gebiet Primärschlüssel des Gebiets
   * @param position Position auf dem Wahlzettel (bzw. Pseudo-Position bei Pseudo-Gruppen)
   * @return Primärschlüssel der gefundenen GruppeGebietsspezifisch
   * @throws ObjectNotFoundException wenn keine Instanz gefunden werden konnte
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public String ejbFindByGebietAndPosition(String id_Gebiet, int position)
      throws ObjectNotFoundException, FinderException, EJBException {

    return findSingle("select ID_GruppeGebietsspezifisch from " + TABLENAME //$NON-NLS-1$
        + " where ID_Gebiet=? and Position=?", new Object[]{id_Gebiet, new Integer(position)}); //$NON-NLS-1$
  }

  /**
   * @param id_Gruppe Primärschlüssel der Gruppe
   * @param id_Gebiet Primärschlüssel des Gebiets
   * @return Primärschlüssel der GruppeGebietsspezifisch an der Kreuzung von gegebener Gruppe mit
   *         gegebenem Gebiet, wenn eindeutig
   * @throws ObjectNotFoundException wenn keine Instanz gefunden werden konnte
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public String ejbFindByGruppeAndGebiet(String id_Gruppe, String id_Gebiet)
      throws ObjectNotFoundException, FinderException, EJBException {

    return findSingle(ejbFindAllByGruppeAndGebiet(id_Gruppe, id_Gebiet));
  }

  /**
   * @param id_Gruppe Primärschlüssel der Gruppe
   * @param id_Gebiet Primärschlüssel des Gebiets
   * @return Primärschlüssel aller GruppeGebietsspezifisch an der Kreuzung von gegebener Gruppe mit
   *         gegebenem Gebiet
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public Collection<String> ejbFindAllByGruppeAndGebiet(String id_Gruppe, String id_Gebiet)
      throws FinderException, EJBException {

    try {
      return retrieveIDs("select ID_GruppeGebietsspezifisch from " + TABLENAME //$NON-NLS-1$
          + " where ID_Gruppe=? and ID_Gebiet=?", new Object[]{id_Gruppe, id_Gebiet}); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @param id_Gebiet Primärschlüssel des Gebiets
   * @return Primärschlüssel aller GruppeGebietsspezifisch eines Gebiets, sortiert nach der Position
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public Collection<String> ejbFindAllByGebietOrderByPosition(String id_Gebiet)
      throws FinderException, EJBException {

    try {
      return retrieveIDs("select ID_GruppeGebietsspezifisch from " + TABLENAME //$NON-NLS-1$
          + " where ID_Gebiet=? " + " order by " + POSITION, new Object[]{id_Gebiet}); //$NON-NLS-1$//$NON-NLS-2$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @param id_Gebiet PG of region object Gebiet
   * @param gruppenschluessel Key for group or party
   * @return PK of the corresponding region specific group
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public String ejbFindByGebietAndGruppenschluessel(String id_Gebiet, int gruppenschluessel)
      throws FinderException, EJBException {

    return findSingle("select ID_GruppeGebietsspezifisch from " + TABLENAME + ", " //$NON-NLS-1$ //$NON-NLS-2$
        + BasicGruppeDBA.TABLENAME + " where " + ID_GEBIET_QUAL + " = ? " + " and " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + ID_GRUPPE_QUAL + " = " + BasicGruppeDBA.ID_GRUPPE_QUAL + " and " //$NON-NLS-1$ //$NON-NLS-2$
        + BasicGruppeDBA.SCHLUESSEL + " = ? ", new Object[]{id_Gebiet, gruppenschluessel}); //$NON-NLS-1$
  }

  /**
   * @param id_Gruppe Primärschlüssel der Gruppe
   * @return Primärschlüssel aller GruppeGebietsspezifisch einer Gruppe, geordnet nach Gebietsart
   *         und Position der jeweiligen Gebiete
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public Collection<String> ejbFindAllByGruppeOrderByGebietsartAndPosition(String id_Gruppe)
      throws FinderException, EJBException {

    try {
      return retrieveIDs("select " + ID_GRUPPEGEBIETSSPEZIFISCH + " from " + TABLENAME + " join " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + BasicGebietDBA.TABLENAME + " using(" + BasicGebietDBA.ID_GEBIET + ") where " //$NON-NLS-1$ //$NON-NLS-2$
          + ID_GRUPPE + "=? order by " + BasicGebietDBA.GEBIETSART_QUAL + ", " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietDBA.POSITION_QUAL, new Object[]{id_Gruppe});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @param position Position im betroffenen Gebiet (entspricht der Wahlzettelposition wo
   *          zutreffend)
   * @param gebietIDs Gebiete über die gesucht wird
   * @return Primärschlüssel aller GruppeGebietsspezifisch auf einer bestimmten Position in einer
   *         Gruppe von Gebieten
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public Collection<String> ejbFindAllByPositionAndGebietCol(int position,
      Collection<String> gebietIDs) throws FinderException, EJBException {

    try {
      char quote = '\'';
      StringBuilder whereIn = new StringBuilder();
      for (Iterator<String> gebietIdItr = gebietIDs.iterator(); gebietIdItr.hasNext();) {
        String id = gebietIdItr.next();
        whereIn.append(quote).append(id);
        if (gebietIdItr.hasNext()) {
          whereIn.append(',');
        }
      }

      return retrieveIDs("select " + ID_GRUPPEGEBIETSSPEZIFISCH + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_GEBIET + " in (" + whereIn + ") and " + POSITION + "=" + position); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @param id_Wahl Primärschlüssel der Wahl
   * @return Primärschlüssel aller GruppeGebietsspezifisch der Wahl
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public Collection<String> ejbFindAllByWahl(String id_Wahl) throws FinderException, EJBException {
    try {
      return retrieveIDs("select ID_GruppeGebietsspezifisch from " + TABLENAME + " gg , " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGruppeDBA.TABLENAME + " g where g.ID_Wahl = ? and gg.ID_Gruppe = g.ID_Gruppe ", //$NON-NLS-1$
          new Object[]{id_Wahl});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @param id_Wahl
   * @return
   * @throws FinderException
   * @throws EJBException
   */
  public Collection<String> ejbFindAllWithListsByWahl(String id_Wahl)
      throws FinderException, EJBException {
    try {
      return retrieveIDs("select ID_GruppeGebietsspezifisch from " + TABLENAME + " gg , " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGruppeDBA.TABLENAME + " g where g.ID_Wahl = ? and gg.ID_Gruppe = g.ID_Gruppe " //$NON-NLS-1$
          + " and gg." + GruppeGebietsspezifischDBA.ID_LISTE + " is not null", //$NON-NLS-1$//$NON-NLS-2$
          new Object[]{id_Wahl});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Liefert die Primärschlüssel aller auf der Liste oder direkt zugelassenen
   * GruppeGebietsspezifisch eines Gebietes (also die, die eine Zeile auf dem Wahlzettel
   * beanspruchen)
   * 
   * @param id_Gebiet Primärschlüssel des Gebiets
   * @return Primärschlüssel aller auf der Liste oder direkt zugelassenen GruppeGebietsspezifisch
   *         eines Gebietes
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public Collection<String> ejbFindAllZugelassenByGebietSortByPosition(String id_Gebiet)
      throws FinderException, EJBException {

    try {
      return retrieveIDs("select " + ID_GRUPPEGEBIETSSPEZIFISCH + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_GEBIET + " = ? order by " + POSITION, new Object[]{id_Gebiet}); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Sucht nach erhaltenen Stimmanzahl die Gruppe/Gruppen im Gebiet mit der maximalen Stimmanzahl
   * heraus.
   * 
   * @param id_Gebiet Primärschlüssel des Gebiets, in dem gesucht wird
   * @param wahlergebnisart Wahlergebnisart, für die der/die Gewinner gesucht wird/werden
   * @param stimmart Stimmart, für die der/die Gewinner gesucht wird/werden
   * @return Primärschlüssel der Gruppe/Gruppen im Gebiet mit der maximalen Stimmanzahl
   * @throws FinderException genereller Fehler bei der Durchführung der Suche
   * @throws EJBException bei einem allgemeinem Problem
   */
  public Collection<String> ejbFindAllGewinnerByGebiet(String id_Gebiet,
      int wahlergebnisart,
      int stimmart) throws FinderException, EJBException {

    try {
      String query = "select ID_GruppeGebietsspezifisch from (" //$NON-NLS-1$
          + "select GruppeGebietsspezifisch.ID_GruppeGebietsspezifisch, Stimmen,  Stimmergebnis.ID_Gebiet " //$NON-NLS-1$
          + "from Stimmergebnis join GruppeGebietsspezifisch using(ID_GruppeGebietsspezifisch) " //$NON-NLS-1$
          + "where Stimmergebnis.ID_Gebiet=? and Wahlergebnisart=? and Stimmart=? and Position>0 " //$NON-NLS-1$
          + "order by ID_Gebiet, Stimmen desc) as Temp group by ID_Gebiet"; //$NON-NLS-1$

      return retrieveIDs(query, new Object[]{id_Gebiet, wahlergebnisart, stimmart});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @return zusammengesetztes Wert-Objekt welches ein {@link GruppeGebietsspezifischModel} mit
   *         einem {@link GruppeModel} und für der zugehörigen Kandidatenliste bündelt
   * @throws EJBException bei einem allgemeinem Problem
   */
  public GruppeGebietsspezifischGruppeComposite getGruppeGebietsspezifischGruppeComposite()
      throws EJBException {
    return new GruppeGebietsspezifischGruppeCompositeImpl(getDetails(), getGruppe().getDetails(),
        null);
  }

  @Override
  protected void resetRelations() {
    super.resetRelations();
  }
}