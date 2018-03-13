/*
 * StimmergebnisDBA
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
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.fw.DBABase;


/**
  * Implementation of the persistency layer for the entity Stimmergebnis.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class StimmergebnisDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(StimmergebnisDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(StimmergebnisDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Stimmergebnis"; //$NON-NLS-1$
   public static final String ID_STIMMERGEBNIS = "ID_Stimmergebnis"; //$NON-NLS-1$
   public static final String ID_STIMMERGEBNIS_QUAL = "Stimmergebnis.ID_Stimmergebnis"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Stimmergebnis.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_GEBIET = "ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET_QUAL = "Stimmergebnis.ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GRUPPEGEBIETSSPEZIFISCH = "ID_GruppeGebietsspezifisch"; //$NON-NLS-1$
   public static final String ID_GRUPPEGEBIETSSPEZIFISCH_QUAL = "Stimmergebnis.ID_GruppeGebietsspezifisch"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATUR = "ID_Listenkandidatur"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATUR_QUAL = "Stimmergebnis.ID_Listenkandidatur"; //$NON-NLS-1$
   public static final String WAHLERGEBNISART = "Wahlergebnisart"; //$NON-NLS-1$
   public static final String WAHLERGEBNISART_QUAL = "Stimmergebnis.Wahlergebnisart"; //$NON-NLS-1$
   public static final String STIMMEN = "Stimmen"; //$NON-NLS-1$
   public static final String STIMMEN_QUAL = "Stimmergebnis.Stimmen"; //$NON-NLS-1$
   public static final String STIMMART = "Stimmart"; //$NON-NLS-1$
   public static final String STIMMART_QUAL = "Stimmergebnis.Stimmart"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_STIMMERGEBNIS,ID_ERGEBNISEINGANG,ID_GEBIET,ID_GRUPPEGEBIETSSPEZIFISCH,ID_LISTENKANDIDATUR,WAHLERGEBNISART,STIMMEN,STIMMART};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>StimmergebnisModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, StimmergebnisModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_STIMMERGEBNIS.toUpperCase())) != null) {
         m._id_Stimmergebnis = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GEBIET.toUpperCase())) != null) {
         m._id_Gebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GRUPPEGEBIETSSPEZIFISCH.toUpperCase())) != null) {
         m._id_GruppeGebietsspezifisch = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTENKANDIDATUR.toUpperCase())) != null) {
         m._id_Listenkandidatur = r.getString(idx.intValue());
      }
      if ((idx = columns.get(WAHLERGEBNISART.toUpperCase())) != null) {
         m._wahlergebnisart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(STIMMEN.toUpperCase())) != null) {
         m._stimmen = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(STIMMART.toUpperCase())) != null) {
         m._stimmart = r.getInt(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>StimmergebnisModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(StimmergebnisModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(ID_GEBIET.toUpperCase())) {
         p.setString(idx++, m._id_Gebiet);
      }
      if (columns.containsKey(ID_GRUPPEGEBIETSSPEZIFISCH.toUpperCase())) {
         p.setString(idx++, m._id_GruppeGebietsspezifisch);
      }
      if (columns.containsKey(ID_LISTENKANDIDATUR.toUpperCase())) {
         p.setString(idx++, m._id_Listenkandidatur);
      }
      if (columns.containsKey(WAHLERGEBNISART.toUpperCase())) {
         p.setInt(idx++, m._wahlergebnisart);
      }
      if (columns.containsKey(STIMMEN.toUpperCase())) {
         p.setInt(idx++, m._stimmen);
      }
      if (columns.containsKey(STIMMART.toUpperCase())) {
         p.setInt(idx++, m._stimmart);
      }
      p.setString(idx++, m._id_Stimmergebnis);
   }

   /**
     * Inserts or changes the data of the given object in the table Stimmergebnis (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (StimmergebnisModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>StimmergebnisModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(StimmergebnisModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(ID_GEBIET)) {
         values.add(toString(m.getID_Gebiet()));
      }
      if (columns.containsKey(ID_GRUPPEGEBIETSSPEZIFISCH)) {
         values.add(toString(m.getID_GruppeGebietsspezifisch()));
      }
      if (columns.containsKey(ID_LISTENKANDIDATUR)) {
         values.add(toString(m.getID_Listenkandidatur()));
      }
      if (columns.containsKey(WAHLERGEBNISART)) {
         values.add(toString(m.getWahlergebnisart()));
      }
      if (columns.containsKey(STIMMEN)) {
         values.add(toString(m.getStimmen()));
      }
      if (columns.containsKey(STIMMART)) {
         values.add(toString(m.getStimmart()));
      }
      values.add(toString(m.getID_Stimmergebnis()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Stimmergebnis
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
     * @param id_Stimmergebnis searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Stimmergebnis) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Stimmergebnis=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Stimmergebnis});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Stimmergebnis=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Stimmergebnis searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(StimmergebnisModelImpl m, String id_Stimmergebnis)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Stimmergebnis);
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
         values[idx++] = toString(id_Stimmergebnis);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Stimmergebnis IDs
     *

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Stimmergebnis from Stimmergebnis",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Stimmergebnis by a from parameters builded WHERE-clause
     *
     * @param id_Stimmergebnis searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Stimmergebnis) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Stimmergebnis=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Stimmergebnis});
   }

   /**
     * Changes the data of the given object in the table Stimmergebnis 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(StimmergebnisModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Stimmergebnis=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object StimmergebnisModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (StimmergebnisModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of Stimmergebnis IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Stimmergebnis from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByID_GruppeGebietsspezifisch returns a {@link Collection} of Stimmergebnis IDs
     *
     * @param id_GruppeGebietsspezifisch searching condition

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_GruppeGebietsspezifisch(String id_GruppeGebietsspezifisch)
      throws SQLException {

      return retrieveIDs(
         "select ID_Stimmergebnis from " + TABLENAME + " where ID_GruppeGebietsspezifisch=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_GruppeGebietsspezifisch});
   }

   /**
     * Method retrieveIDsByID_Gebiet returns a {@link Collection} of Stimmergebnis IDs
     *
     * @param id_Gebiet searching condition

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gebiet(String id_Gebiet) throws SQLException {
      return retrieveIDs(
         "select ID_Stimmergebnis from " + TABLENAME + " where ID_Gebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gebiet});
   }

   /**
     * Method retrieveIDsByID_Listenkandidatur returns a {@link Collection} of Stimmergebnis IDs
     *
     * @param id_Listenkandidatur searching condition

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkandidatur(String id_Listenkandidatur)
      throws SQLException {

      return retrieveIDs(
         "select ID_Stimmergebnis from " + TABLENAME + " where ID_Listenkandidatur=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkandidatur});
   }

   /**
     * Method retrieveIDsByWahlergebnisart returns a {@link Collection} of Stimmergebnis IDs
     *
     * @param wahlergebnisart searching condition

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahlergebnisart(int wahlergebnisart)
      throws SQLException {

      return retrieveIDs(
         "select ID_Stimmergebnis from " + TABLENAME + " where Wahlergebnisart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(wahlergebnisart)});
   }

   /**
     * Method retrieveIDsByStimmen returns a {@link Collection} of Stimmergebnis IDs
     *
     * @param stimmen searching condition

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByStimmen(int stimmen) throws SQLException {
      return retrieveIDs(
         "select ID_Stimmergebnis from " + TABLENAME + " where Stimmen=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(stimmen)});
   }

   /**
     * Method retrieveIDsByStimmart returns a {@link Collection} of Stimmergebnis IDs
     *
     * @param stimmart searching condition

     * @return a {@link Collection} of Stimmergebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByStimmart(int stimmart) throws SQLException {
      return retrieveIDs(
         "select ID_Stimmergebnis from " + TABLENAME + " where Stimmart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(stimmart)});
   }
}
