/*
 * UnterverteilungDBA
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
  * Implementation of the persistency layer for the entity Unterverteilung.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class UnterverteilungDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(UnterverteilungDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(UnterverteilungDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Unterverteilung"; //$NON-NLS-1$
   public static final String ID_UNTERVERTEILUNG = "ID_Unterverteilung"; //$NON-NLS-1$
   public static final String ID_UNTERVERTEILUNG_QUAL = "Unterverteilung.ID_Unterverteilung"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Unterverteilung.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "Unterverteilung.ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION = "ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION_QUAL = "Unterverteilung.ID_Listenkombination"; //$NON-NLS-1$
   public static final String STIMMEN = "Stimmen"; //$NON-NLS-1$
   public static final String STIMMEN_QUAL = "Unterverteilung.Stimmen"; //$NON-NLS-1$
   public static final String SITZE = "Sitze"; //$NON-NLS-1$
   public static final String SITZE_QUAL = "Unterverteilung.Sitze"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_UNTERVERTEILUNG,ID_ERGEBNISEINGANG,ID_GRUPPE,ID_LISTENKOMBINATION,STIMMEN,SITZE};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>UnterverteilungModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, UnterverteilungModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_UNTERVERTEILUNG.toUpperCase())) != null) {
         m._id_Unterverteilung = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GRUPPE.toUpperCase())) != null) {
         m._id_Gruppe = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTENKOMBINATION.toUpperCase())) != null) {
         m._id_Listenkombination = r.getString(idx.intValue());
      }
      if ((idx = columns.get(STIMMEN.toUpperCase())) != null) {
         m._stimmen = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(SITZE.toUpperCase())) != null) {
         m._sitze = r.getInt(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>UnterverteilungModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(UnterverteilungModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(ID_GRUPPE.toUpperCase())) {
         p.setString(idx++, m._id_Gruppe);
      }
      if (columns.containsKey(ID_LISTENKOMBINATION.toUpperCase())) {
         p.setString(idx++, m._id_Listenkombination);
      }
      if (columns.containsKey(STIMMEN.toUpperCase())) {
         p.setInt(idx++, m._stimmen);
      }
      if (columns.containsKey(SITZE.toUpperCase())) {
         p.setInt(idx++, m._sitze);
      }
      p.setString(idx++, m._id_Unterverteilung);
   }

   /**
     * Inserts or changes the data of the given object in the table Unterverteilung (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (UnterverteilungModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>UnterverteilungModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(UnterverteilungModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(ID_GRUPPE)) {
         values.add(toString(m.getID_Gruppe()));
      }
      if (columns.containsKey(ID_LISTENKOMBINATION)) {
         values.add(toString(m.getID_Listenkombination()));
      }
      if (columns.containsKey(STIMMEN)) {
         values.add(toString(m.getStimmen()));
      }
      if (columns.containsKey(SITZE)) {
         values.add(toString(m.getSitze()));
      }
      values.add(toString(m.getID_Unterverteilung()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Unterverteilung
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
     * @param id_Unterverteilung searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Unterverteilung) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Unterverteilung=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Unterverteilung});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Unterverteilung=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Unterverteilung searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(UnterverteilungModelImpl m, String id_Unterverteilung)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Unterverteilung);
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
         values[idx++] = toString(id_Unterverteilung);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Unterverteilung IDs
     *

     * @return a {@link Collection} of Unterverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Unterverteilung from Unterverteilung",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Unterverteilung by a from parameters builded WHERE-clause
     *
     * @param id_Unterverteilung searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Unterverteilung) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Unterverteilung=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Unterverteilung});
   }

   /**
     * Changes the data of the given object in the table Unterverteilung 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(UnterverteilungModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Unterverteilung=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object UnterverteilungModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (UnterverteilungModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Listenkombination returns a {@link Collection} of Unterverteilung IDs
     *
     * @param id_Listenkombination searching condition

     * @return a {@link Collection} of Unterverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkombination(String id_Listenkombination)
      throws SQLException {

      return retrieveIDs(
         "select ID_Unterverteilung from " + TABLENAME + " where ID_Listenkombination=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkombination});
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of Unterverteilung IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of Unterverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Unterverteilung from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByID_Gruppe returns a {@link Collection} of Unterverteilung IDs
     *
     * @param id_Gruppe searching condition

     * @return a {@link Collection} of Unterverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gruppe(String id_Gruppe) throws SQLException {
      return retrieveIDs(
         "select ID_Unterverteilung from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gruppe});
   }

   /**
     * Method retrieveIDsByStimmen returns a {@link Collection} of Unterverteilung IDs
     *
     * @param stimmen searching condition

     * @return a {@link Collection} of Unterverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByStimmen(int stimmen) throws SQLException {
      return retrieveIDs(
         "select ID_Unterverteilung from " + TABLENAME + " where Stimmen=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(stimmen)});
   }

   /**
     * Method retrieveIDsBySitze returns a {@link Collection} of Unterverteilung IDs
     *
     * @param sitze searching condition

     * @return a {@link Collection} of Unterverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySitze(int sitze) throws SQLException {
      return retrieveIDs(
         "select ID_Unterverteilung from " + TABLENAME + " where Sitze=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(sitze)});
   }
}
