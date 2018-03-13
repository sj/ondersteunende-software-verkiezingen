/*
 * WahlDBA
 * 
 * WARNING! Automatically generated file! Do not edit!
 * Code Generator by IVU
 * Definition Version: $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
 */

package de.ivu.wahl.modell.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.fw.DBABase;


/**
  * Implementation of the persistency layer for the entity Wahl.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class WahlDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(WahlDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(WahlDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Wahl.ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WURZELGEBIET = "ID_Wurzelgebiet"; //$NON-NLS-1$
   public static final String ID_WURZELGEBIET_QUAL = "Wahl.ID_Wurzelgebiet"; //$NON-NLS-1$
   public static final String ID_WAHLGEBIET = "ID_Wahlgebiet"; //$NON-NLS-1$
   public static final String ID_WAHLGEBIET_QUAL = "Wahl.ID_Wahlgebiet"; //$NON-NLS-1$
   public static final String WAHLART = "Wahlart"; //$NON-NLS-1$
   public static final String WAHLART_QUAL = "Wahl.Wahlart"; //$NON-NLS-1$
   public static final String WAHLEBENE = "Wahlebene"; //$NON-NLS-1$
   public static final String WAHLEBENE_QUAL = "Wahl.Wahlebene"; //$NON-NLS-1$
   public static final String WAHLKATEGORIE = "Wahlkategorie"; //$NON-NLS-1$
   public static final String WAHLKATEGORIE_QUAL = "Wahl.Wahlkategorie"; //$NON-NLS-1$
   public static final String NAME = "Name"; //$NON-NLS-1$
   public static final String NAME_QUAL = "Wahl.Name"; //$NON-NLS-1$
   public static final String ELECTIONDOMAIN = "ElectionDomain"; //$NON-NLS-1$
   public static final String ELECTIONDOMAIN_QUAL = "Wahl.ElectionDomain"; //$NON-NLS-1$
   public static final String ELECTIONDOMAINID = "ElectionDomainId"; //$NON-NLS-1$
   public static final String ELECTIONDOMAINID_QUAL = "Wahl.ElectionDomainId"; //$NON-NLS-1$
   public static final String TERMIN = "Termin"; //$NON-NLS-1$
   public static final String TERMIN_QUAL = "Wahl.Termin"; //$NON-NLS-1$
   public static final String VORRANGSCHWELLE = "Vorrangschwelle"; //$NON-NLS-1$
   public static final String VORRANGSCHWELLE_QUAL = "Wahl.Vorrangschwelle"; //$NON-NLS-1$
   public static final String ANZAHLSITZE = "AnzahlSitze"; //$NON-NLS-1$
   public static final String ANZAHLSITZE_QUAL = "Wahl.AnzahlSitze"; //$NON-NLS-1$
   public static final String GEBIETSARTAUSWERTUNGSEINHEIT = "GebietsartAuswertungseinheit"; //$NON-NLS-1$
   public static final String GEBIETSARTAUSWERTUNGSEINHEIT_QUAL = "Wahl.GebietsartAuswertungseinheit"; //$NON-NLS-1$
   public static final String GEBIETSARTERFASSUNGSEINHEIT = "GebietsartErfassungseinheit"; //$NON-NLS-1$
   public static final String GEBIETSARTERFASSUNGSEINHEIT_QUAL = "Wahl.GebietsartErfassungseinheit"; //$NON-NLS-1$
   public static final String AKTUELLEWAHLERGEBNISART = "AktuelleWahlergebnisart"; //$NON-NLS-1$
   public static final String AKTUELLEWAHLERGEBNISART_QUAL = "Wahl.AktuelleWahlergebnisart"; //$NON-NLS-1$
   public static final String DATUMNOMINIERUNG = "DatumNominierung"; //$NON-NLS-1$
   public static final String DATUMNOMINIERUNG_QUAL = "Wahl.DatumNominierung"; //$NON-NLS-1$
   public static final String STANDMETADATEN = "StandMetadaten"; //$NON-NLS-1$
   public static final String STANDMETADATEN_QUAL = "Wahl.StandMetadaten"; //$NON-NLS-1$
   public static final String GESCHLOSSENMETADATEN = "GeschlossenMetadaten"; //$NON-NLS-1$
   public static final String GESCHLOSSENMETADATEN_QUAL = "Wahl.GeschlossenMetadaten"; //$NON-NLS-1$
   public static final String STATUS = "Status"; //$NON-NLS-1$
   public static final String STATUS_QUAL = "Wahl.Status"; //$NON-NLS-1$
   public static final String FREIGEGEBEN = "Freigegeben"; //$NON-NLS-1$
   public static final String FREIGEGEBEN_QUAL = "Wahl.Freigegeben"; //$NON-NLS-1$
   public static final String LETZTEAENDERUNG = "LetzteAenderung"; //$NON-NLS-1$
   public static final String LETZTEAENDERUNG_QUAL = "Wahl.LetzteAenderung"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_WAHL,ID_WURZELGEBIET,ID_WAHLGEBIET,WAHLART,WAHLEBENE,WAHLKATEGORIE,NAME,ELECTIONDOMAIN,ELECTIONDOMAINID,TERMIN,VORRANGSCHWELLE,ANZAHLSITZE,GEBIETSARTAUSWERTUNGSEINHEIT,GEBIETSARTERFASSUNGSEINHEIT,AKTUELLEWAHLERGEBNISART,DATUMNOMINIERUNG,STANDMETADATEN,GESCHLOSSENMETADATEN,STATUS,FREIGEGEBEN,LETZTEAENDERUNG};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>WahlModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, WahlModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WURZELGEBIET.toUpperCase())) != null) {
         m._id_Wurzelgebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHLGEBIET.toUpperCase())) != null) {
         m._id_Wahlgebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(WAHLART.toUpperCase())) != null) {
         m._wahlart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(WAHLEBENE.toUpperCase())) != null) {
         m._wahlebene = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(WAHLKATEGORIE.toUpperCase())) != null) {
         m._wahlkategorie = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NAME.toUpperCase())) != null) {
         m._name = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ELECTIONDOMAIN.toUpperCase())) != null) {
         m._electionDomain = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ELECTIONDOMAINID.toUpperCase())) != null) {
         m._electionDomainId = r.getString(idx.intValue());
      }
      if ((idx = columns.get(TERMIN.toUpperCase())) != null) {
         m._termin = r.getTimestamp(idx.intValue());
      }
      if ((idx = columns.get(VORRANGSCHWELLE.toUpperCase())) != null) {
         m._vorrangschwelle = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ANZAHLSITZE.toUpperCase())) != null) {
         m._anzahlSitze = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(GEBIETSARTAUSWERTUNGSEINHEIT.toUpperCase())) != null) {
         m._gebietsartAuswertungseinheit = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(GEBIETSARTERFASSUNGSEINHEIT.toUpperCase())) != null) {
         m._gebietsartErfassungseinheit = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(AKTUELLEWAHLERGEBNISART.toUpperCase())) != null) {
         m._aktuelleWahlergebnisart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(DATUMNOMINIERUNG.toUpperCase())) != null) {
         m._datumNominierung = r.getTimestamp(idx.intValue());
      }
      if ((idx = columns.get(STANDMETADATEN.toUpperCase())) != null) {
         m._standMetadaten = r.getTimestamp(idx.intValue());
      }
      if ((idx = columns.get(GESCHLOSSENMETADATEN.toUpperCase())) != null) {
         m._geschlossenMetadaten = r.getTimestamp(idx.intValue());
      }
      if ((idx = columns.get(STATUS.toUpperCase())) != null) {
         m._status = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(FREIGEGEBEN.toUpperCase())) != null) {
         m._freigegeben = r.getTimestamp(idx.intValue());
      }
      if ((idx = columns.get(LETZTEAENDERUNG.toUpperCase())) != null) {
         m._letzteAenderung = r.getTimestamp(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>WahlModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(WahlModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_WURZELGEBIET.toUpperCase())) {
         p.setString(idx++, m._id_Wurzelgebiet);
      }
      if (columns.containsKey(ID_WAHLGEBIET.toUpperCase())) {
         p.setString(idx++, m._id_Wahlgebiet);
      }
      if (columns.containsKey(WAHLART.toUpperCase())) {
         p.setInt(idx++, m._wahlart);
      }
      if (columns.containsKey(WAHLEBENE.toUpperCase())) {
         p.setInt(idx++, m._wahlebene);
      }
      if (columns.containsKey(WAHLKATEGORIE.toUpperCase())) {
         p.setString(idx++, m._wahlkategorie);
      }
      if (columns.containsKey(NAME.toUpperCase())) {
         p.setString(idx++, m._name);
      }
      if (columns.containsKey(ELECTIONDOMAIN.toUpperCase())) {
         p.setString(idx++, m._electionDomain);
      }
      if (columns.containsKey(ELECTIONDOMAINID.toUpperCase())) {
         p.setString(idx++, m._electionDomainId);
      }
      if (columns.containsKey(TERMIN.toUpperCase())) {
         p.setTimestamp(idx++, m._termin);
      }
      if (columns.containsKey(VORRANGSCHWELLE.toUpperCase())) {
         p.setInt(idx++, m._vorrangschwelle);
      }
      if (columns.containsKey(ANZAHLSITZE.toUpperCase())) {
         p.setInt(idx++, m._anzahlSitze);
      }
      if (columns.containsKey(GEBIETSARTAUSWERTUNGSEINHEIT.toUpperCase())) {
         p.setInt(idx++, m._gebietsartAuswertungseinheit);
      }
      if (columns.containsKey(GEBIETSARTERFASSUNGSEINHEIT.toUpperCase())) {
         p.setInt(idx++, m._gebietsartErfassungseinheit);
      }
      if (columns.containsKey(AKTUELLEWAHLERGEBNISART.toUpperCase())) {
         p.setInt(idx++, m._aktuelleWahlergebnisart);
      }
      if (columns.containsKey(DATUMNOMINIERUNG.toUpperCase())) {
         p.setTimestamp(idx++, m._datumNominierung);
      }
      if (columns.containsKey(STANDMETADATEN.toUpperCase())) {
         p.setTimestamp(idx++, m._standMetadaten);
      }
      if (columns.containsKey(GESCHLOSSENMETADATEN.toUpperCase())) {
         p.setTimestamp(idx++, m._geschlossenMetadaten);
      }
      if (columns.containsKey(STATUS.toUpperCase())) {
         p.setInt(idx++, m._status);
      }
      if (columns.containsKey(FREIGEGEBEN.toUpperCase())) {
         p.setTimestamp(idx++, m._freigegeben);
      }
      if (columns.containsKey(LETZTEAENDERUNG.toUpperCase())) {
         p.setTimestamp(idx++, m._letzteAenderung);
      }
      p.setString(idx++, m._id_Wahl);
   }

   /**
     * Inserts or changes the data of the given object in the table Wahl (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (WahlModelImpl m, String query) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(query);
         try {
            putIntoPreparedStatement(m, prepstatement);
            return calculateModificationSuccessStatus(prepstatement.executeUpdate());
         } finally {
            prepstatement.close();
         }
      } catch (SQLException se) {
         logError(LOGGER, se, query, convertModelToStringArray(m));
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Writes the data from a <code>WahlModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(WahlModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_WURZELGEBIET)) {
         values.add(toString(m.getID_Wurzelgebiet()));
      }
      if (columns.containsKey(ID_WAHLGEBIET)) {
         values.add(toString(m.getID_Wahlgebiet()));
      }
      if (columns.containsKey(WAHLART)) {
         values.add(toString(m.getWahlart()));
      }
      if (columns.containsKey(WAHLEBENE)) {
         values.add(toString(m.getWahlebene()));
      }
      if (columns.containsKey(WAHLKATEGORIE)) {
         values.add(toString(m.getWahlkategorie()));
      }
      if (columns.containsKey(NAME)) {
         values.add(toString(m.getName()));
      }
      if (columns.containsKey(ELECTIONDOMAIN)) {
         values.add(toString(m.getElectionDomain()));
      }
      if (columns.containsKey(ELECTIONDOMAINID)) {
         values.add(toString(m.getElectionDomainId()));
      }
      if (columns.containsKey(TERMIN)) {
         values.add(toString(m.getTermin()));
      }
      if (columns.containsKey(VORRANGSCHWELLE)) {
         values.add(toString(m.getVorrangschwelle()));
      }
      if (columns.containsKey(ANZAHLSITZE)) {
         values.add(toString(m.getAnzahlSitze()));
      }
      if (columns.containsKey(GEBIETSARTAUSWERTUNGSEINHEIT)) {
         values.add(toString(m.getGebietsartAuswertungseinheit()));
      }
      if (columns.containsKey(GEBIETSARTERFASSUNGSEINHEIT)) {
         values.add(toString(m.getGebietsartErfassungseinheit()));
      }
      if (columns.containsKey(AKTUELLEWAHLERGEBNISART)) {
         values.add(toString(m.getAktuelleWahlergebnisart()));
      }
      if (columns.containsKey(DATUMNOMINIERUNG)) {
         values.add(toString(m.getDatumNominierung()));
      }
      if (columns.containsKey(STANDMETADATEN)) {
         values.add(toString(m.getStandMetadaten()));
      }
      if (columns.containsKey(GESCHLOSSENMETADATEN)) {
         values.add(toString(m.getGeschlossenMetadaten()));
      }
      if (columns.containsKey(STATUS)) {
         values.add(toString(m.getStatus()));
      }
      if (columns.containsKey(FREIGEGEBEN)) {
         values.add(toString(m.getFreigegeben()));
      }
      if (columns.containsKey(LETZTEAENDERUNG)) {
         values.add(toString(m.getLetzteAenderung()));
      }
      values.add(toString(m.getID_Wahl()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Wahl
     *
     * @return Number of rows
     * @throws SQLException Communication with database is failing
     */
   public static int count() throws SQLException {
      return count("select count(*) from " + TABLENAME); //$NON-NLS-1$
   }

   /**
     * Method returns the number of objects, which were limited by a WHERE-clause by the parameters 
     *
     * @param id_Wahl searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Wahl) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Wahl});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Wahl=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Wahl searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(WahlModelImpl m, String id_Wahl) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Wahl);
            ResultSet r = prepstatement.executeQuery();
            try {
               if (r.next()) {
                  getFromResultSet(r, m);
                  return true;
               } else {
                  return false;
               }
            } finally {
               r.close ();
            }
         } finally {
            prepstatement.close();
         }
      } catch (SQLException se) {
         String[] values = new String[1];
         int idx = 0;
         values[idx++] = toString(id_Wahl);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Wahl IDs
     *

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from Wahl",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Wahl by a from parameters builded WHERE-clause
     *
     * @param id_Wahl searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Wahl) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Wahl=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Wahl});
   }

   /**
     * Changes the data of the given object in the table Wahl 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(WahlModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Wahl=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object WahlModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (WahlModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Wahlgebiet returns a {@link Collection} of Wahl IDs
     *
     * @param id_Wahlgebiet searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahlgebiet(String id_Wahlgebiet)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where ID_Wahlgebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahlgebiet});
   }

   /**
     * Method retrieveIDsByID_Wurzelgebiet returns a {@link Collection} of Wahl IDs
     *
     * @param id_Wurzelgebiet searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wurzelgebiet(String id_Wurzelgebiet)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where ID_Wurzelgebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wurzelgebiet});
   }

   /**
     * Method retrieveIDsByWahlart returns a {@link Collection} of Wahl IDs
     *
     * @param wahlart searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahlart(int wahlart) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Wahlart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(wahlart)});
   }

   /**
     * Method retrieveIDsByWahlebene returns a {@link Collection} of Wahl IDs
     *
     * @param wahlebene searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahlebene(int wahlebene) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Wahlebene=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(wahlebene)});
   }

   /**
     * Method retrieveIDsByWahlkategorie returns a {@link Collection} of Wahl IDs
     *
     * @param wahlkategorie searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahlkategorie(String wahlkategorie)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Wahlkategorie=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{wahlkategorie});
   }

   /**
     * Method retrieveIDsLikeWahlkategorie returns a {@link Collection} of Wahl IDs
     *
     * @param wahlkategorie searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeWahlkategorie(String wahlkategorie)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Wahlkategorie like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{wahlkategorie});
   }

   /**
     * Method retrieveIDsByName returns a {@link Collection} of Wahl IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Name=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsLikeName returns a {@link Collection} of Wahl IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Name like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsByElectionDomain returns a {@link Collection} of Wahl IDs
     *
     * @param electionDomain searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByElectionDomain(String electionDomain)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where ElectionDomain=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{electionDomain});
   }

   /**
     * Method retrieveIDsLikeElectionDomain returns a {@link Collection} of Wahl IDs
     *
     * @param electionDomain searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeElectionDomain(String electionDomain)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where ElectionDomain like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{electionDomain});
   }

   /**
     * Method retrieveIDsByElectionDomainId returns a {@link Collection} of Wahl IDs
     *
     * @param electionDomainId searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByElectionDomainId(String electionDomainId)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where ElectionDomainId=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{electionDomainId});
   }

   /**
     * Method retrieveIDsLikeElectionDomainId returns a {@link Collection} of Wahl IDs
     *
     * @param electionDomainId searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeElectionDomainId(String electionDomainId)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where ElectionDomainId like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{electionDomainId});
   }

   /**
     * Method retrieveIDsByTermin returns a {@link Collection} of Wahl IDs
     *
     * @param termin searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByTermin(Timestamp termin) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Termin=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{termin});
   }

   /**
     * Method retrieveIDsByVorrangschwelle returns a {@link Collection} of Wahl IDs
     *
     * @param vorrangschwelle searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByVorrangschwelle(int vorrangschwelle)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Vorrangschwelle=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(vorrangschwelle)});
   }

   /**
     * Method retrieveIDsByAnzahlSitze returns a {@link Collection} of Wahl IDs
     *
     * @param anzahlSitze searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByAnzahlSitze(int anzahlSitze) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where AnzahlSitze=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(anzahlSitze)});
   }

   /**
     * Method retrieveIDsByGebietsartAuswertungseinheit returns a {@link Collection} of Wahl IDs
     *
     * @param gebietsartAuswertungseinheit searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGebietsartAuswertungseinheit(int gebietsartAuswertungseinheit)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where GebietsartAuswertungseinheit=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(gebietsartAuswertungseinheit)});
   }

   /**
     * Method retrieveIDsByGebietsartErfassungseinheit returns a {@link Collection} of Wahl IDs
     *
     * @param gebietsartErfassungseinheit searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGebietsartErfassungseinheit(int gebietsartErfassungseinheit)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where GebietsartErfassungseinheit=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(gebietsartErfassungseinheit)});
   }

   /**
     * Method retrieveIDsByAktuelleWahlergebnisart returns a {@link Collection} of Wahl IDs
     *
     * @param aktuelleWahlergebnisart searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByAktuelleWahlergebnisart(int aktuelleWahlergebnisart)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where AktuelleWahlergebnisart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(aktuelleWahlergebnisart)});
   }

   /**
     * Method retrieveIDsByDatumNominierung returns a {@link Collection} of Wahl IDs
     *
     * @param datumNominierung searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByDatumNominierung(Timestamp datumNominierung)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where DatumNominierung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{datumNominierung});
   }

   /**
     * Method retrieveIDsByStandMetadaten returns a {@link Collection} of Wahl IDs
     *
     * @param standMetadaten searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByStandMetadaten(Timestamp standMetadaten)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where StandMetadaten=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{standMetadaten});
   }

   /**
     * Method retrieveIDsByGeschlossenMetadaten returns a {@link Collection} of Wahl IDs
     *
     * @param geschlossenMetadaten searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGeschlossenMetadaten(Timestamp geschlossenMetadaten)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where GeschlossenMetadaten=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{geschlossenMetadaten});
   }

   /**
     * Method retrieveIDsByStatus returns a {@link Collection} of Wahl IDs
     *
     * @param status searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByStatus(int status) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Status=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(status)});
   }

   /**
     * Method retrieveIDsByFreigegeben returns a {@link Collection} of Wahl IDs
     *
     * @param freigegeben searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByFreigegeben(Timestamp freigegeben) throws SQLException {
      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where Freigegeben=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{freigegeben});
   }

   /**
     * Method retrieveIDsByLetzteAenderung returns a {@link Collection} of Wahl IDs
     *
     * @param letzteAenderung searching condition

     * @return a {@link Collection} of Wahl IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByLetzteAenderung(Timestamp letzteAenderung)
      throws SQLException {

      return retrieveIDs(
         "select ID_Wahl from " + TABLENAME + " where LetzteAenderung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{letzteAenderung});
   }
}
