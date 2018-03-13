/*
 * ErgebniseingangDBA
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
  * Implementation of the persistency layer for the entity Ergebniseingang.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class ErgebniseingangDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(ErgebniseingangDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ErgebniseingangDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Ergebniseingang.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERFASSUNGSEINHEIT = "ID_Erfassungseinheit"; //$NON-NLS-1$
   public static final String ID_ERFASSUNGSEINHEIT_QUAL = "Ergebniseingang.ID_Erfassungseinheit"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Ergebniseingang.ID_Wahl"; //$NON-NLS-1$
   public static final String ANWENDERNAME = "AnwenderName"; //$NON-NLS-1$
   public static final String ANWENDERNAME_QUAL = "Ergebniseingang.AnwenderName"; //$NON-NLS-1$
   public static final String ZEITSTEMPEL = "Zeitstempel"; //$NON-NLS-1$
   public static final String ZEITSTEMPEL_QUAL = "Ergebniseingang.Zeitstempel"; //$NON-NLS-1$
   public static final String HERKUNFT = "Herkunft"; //$NON-NLS-1$
   public static final String HERKUNFT_QUAL = "Ergebniseingang.Herkunft"; //$NON-NLS-1$
   public static final String WAHLERGEBNISART = "Wahlergebnisart"; //$NON-NLS-1$
   public static final String WAHLERGEBNISART_QUAL = "Ergebniseingang.Wahlergebnisart"; //$NON-NLS-1$
   public static final String UNTERSCHIEDEVORHANDEN = "UnterschiedeVorhanden"; //$NON-NLS-1$
   public static final String UNTERSCHIEDEVORHANDEN_QUAL = "Ergebniseingang.UnterschiedeVorhanden"; //$NON-NLS-1$
   public static final String STATUS = "Status"; //$NON-NLS-1$
   public static final String STATUS_QUAL = "Ergebniseingang.Status"; //$NON-NLS-1$
   public static final String ERGEBNISHASH = "ErgebnisHash"; //$NON-NLS-1$
   public static final String ERGEBNISHASH_QUAL = "Ergebniseingang.ErgebnisHash"; //$NON-NLS-1$
   public static final String FEHLERMELDUNG = "Fehlermeldung"; //$NON-NLS-1$
   public static final String FEHLERMELDUNG_QUAL = "Ergebniseingang.Fehlermeldung"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_ERGEBNISEINGANG,ID_ERFASSUNGSEINHEIT,ID_WAHL,ANWENDERNAME,ZEITSTEMPEL,HERKUNFT,WAHLERGEBNISART,UNTERSCHIEDEVORHANDEN,STATUS,ERGEBNISHASH,FEHLERMELDUNG};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>ErgebniseingangModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, ErgebniseingangModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERFASSUNGSEINHEIT.toUpperCase())) != null) {
         m._id_Erfassungseinheit = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ANWENDERNAME.toUpperCase())) != null) {
         m._anwenderName = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ZEITSTEMPEL.toUpperCase())) != null) {
         m._zeitstempel = r.getTimestamp(idx.intValue());
      }
      if ((idx = columns.get(HERKUNFT.toUpperCase())) != null) {
         m._herkunft = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(WAHLERGEBNISART.toUpperCase())) != null) {
         m._wahlergebnisart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(UNTERSCHIEDEVORHANDEN.toUpperCase())) != null) {
         m._unterschiedeVorhanden = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(STATUS.toUpperCase())) != null) {
         m._status = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ERGEBNISHASH.toUpperCase())) != null) {
         m._ergebnisHash = r.getString(idx.intValue());
      }
      if ((idx = columns.get(FEHLERMELDUNG.toUpperCase())) != null) {
         m._fehlermeldung = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>ErgebniseingangModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(ErgebniseingangModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_ERFASSUNGSEINHEIT.toUpperCase())) {
         p.setString(idx++, m._id_Erfassungseinheit);
      }
      if (columns.containsKey(ID_WAHL.toUpperCase())) {
         p.setString(idx++, m._id_Wahl);
      }
      if (columns.containsKey(ANWENDERNAME.toUpperCase())) {
         p.setString(idx++, m._anwenderName);
      }
      if (columns.containsKey(ZEITSTEMPEL.toUpperCase())) {
         p.setTimestamp(idx++, m._zeitstempel);
      }
      if (columns.containsKey(HERKUNFT.toUpperCase())) {
         p.setInt(idx++, m._herkunft);
      }
      if (columns.containsKey(WAHLERGEBNISART.toUpperCase())) {
         p.setInt(idx++, m._wahlergebnisart);
      }
      if (columns.containsKey(UNTERSCHIEDEVORHANDEN.toUpperCase())) {
         p.setInt(idx++, m._unterschiedeVorhanden);
      }
      if (columns.containsKey(STATUS.toUpperCase())) {
         p.setInt(idx++, m._status);
      }
      if (columns.containsKey(ERGEBNISHASH.toUpperCase())) {
         p.setString(idx++, m._ergebnisHash);
      }
      if (columns.containsKey(FEHLERMELDUNG.toUpperCase())) {
         p.setString(idx++, m._fehlermeldung);
      }
      p.setString(idx++, m._id_Ergebniseingang);
   }

   /**
     * Inserts or changes the data of the given object in the table Ergebniseingang (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (ErgebniseingangModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>ErgebniseingangModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(ErgebniseingangModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_ERFASSUNGSEINHEIT)) {
         values.add(toString(m.getID_Erfassungseinheit()));
      }
      if (columns.containsKey(ID_WAHL)) {
         values.add(toString(m.getID_Wahl()));
      }
      if (columns.containsKey(ANWENDERNAME)) {
         values.add(toString(m.getAnwenderName()));
      }
      if (columns.containsKey(ZEITSTEMPEL)) {
         values.add(toString(m.getZeitstempel()));
      }
      if (columns.containsKey(HERKUNFT)) {
         values.add(toString(m.getHerkunft()));
      }
      if (columns.containsKey(WAHLERGEBNISART)) {
         values.add(toString(m.getWahlergebnisart()));
      }
      if (columns.containsKey(UNTERSCHIEDEVORHANDEN)) {
         values.add(toString(m.getUnterschiedeVorhanden()));
      }
      if (columns.containsKey(STATUS)) {
         values.add(toString(m.getStatus()));
      }
      if (columns.containsKey(ERGEBNISHASH)) {
         values.add(toString(m.getErgebnisHash()));
      }
      if (columns.containsKey(FEHLERMELDUNG)) {
         values.add(toString(m.getFehlermeldung()));
      }
      values.add(toString(m.getID_Ergebniseingang()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Ergebniseingang
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
     * @param id_Ergebniseingang searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Ergebniseingang) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Ergebniseingang=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Ergebniseingang searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(ErgebniseingangModelImpl m, String id_Ergebniseingang)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Ergebniseingang);
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
         values[idx++] = toString(id_Ergebniseingang);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Ergebniseingang IDs
     *

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from Ergebniseingang",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Ergebniseingang by a from parameters builded WHERE-clause
     *
     * @param id_Ergebniseingang searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Ergebniseingang) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Ergebniseingang=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Changes the data of the given object in the table Ergebniseingang 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(ErgebniseingangModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Ergebniseingang=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object ErgebniseingangModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (ErgebniseingangModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Wahl returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param id_Wahl searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahl(String id_Wahl) throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahl});
   }

   /**
     * Method retrieveIDsByID_Erfassungseinheit returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param id_Erfassungseinheit searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Erfassungseinheit(String id_Erfassungseinheit)
      throws SQLException {

      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where ID_Erfassungseinheit=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Erfassungseinheit});
   }

   /**
     * Method retrieveIDsByAnwenderName returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param anwenderName searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByAnwenderName(String anwenderName) throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where AnwenderName=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{anwenderName});
   }

   /**
     * Method retrieveIDsLikeAnwenderName returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param anwenderName searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeAnwenderName(String anwenderName)
      throws SQLException {

      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where AnwenderName like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{anwenderName});
   }

   /**
     * Method retrieveIDsByZeitstempel returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param zeitstempel searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByZeitstempel(Timestamp zeitstempel) throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where Zeitstempel=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{zeitstempel});
   }

   /**
     * Method retrieveIDsByHerkunft returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param herkunft searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByHerkunft(int herkunft) throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where Herkunft=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(herkunft)});
   }

   /**
     * Method retrieveIDsByWahlergebnisart returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param wahlergebnisart searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahlergebnisart(int wahlergebnisart)
      throws SQLException {

      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where Wahlergebnisart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(wahlergebnisart)});
   }

   /**
     * Method retrieveIDsByUnterschiedeVorhanden returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param unterschiedeVorhanden searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByUnterschiedeVorhanden(int unterschiedeVorhanden)
      throws SQLException {

      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where UnterschiedeVorhanden=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(unterschiedeVorhanden)});
   }

   /**
     * Method retrieveIDsByStatus returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param status searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByStatus(int status) throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where Status=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(status)});
   }

   /**
     * Method retrieveIDsByErgebnisHash returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param ergebnisHash searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByErgebnisHash(String ergebnisHash) throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where ErgebnisHash=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{ergebnisHash});
   }

   /**
     * Method retrieveIDsLikeErgebnisHash returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param ergebnisHash searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeErgebnisHash(String ergebnisHash)
      throws SQLException {

      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where ErgebnisHash like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{ergebnisHash});
   }

   /**
     * Method retrieveIDsByFehlermeldung returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param fehlermeldung searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByFehlermeldung(String fehlermeldung)
      throws SQLException {

      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where Fehlermeldung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{fehlermeldung});
   }

   /**
     * Method retrieveIDsLikeFehlermeldung returns a {@link Collection} of Ergebniseingang IDs
     *
     * @param fehlermeldung searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeFehlermeldung(String fehlermeldung)
      throws SQLException {

      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where Fehlermeldung like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{fehlermeldung});
   }
}
