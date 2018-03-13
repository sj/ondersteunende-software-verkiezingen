/*
 * SitzverteilungDBA
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
  * Implementation of the persistency layer for the entity Sitzverteilung.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class SitzverteilungDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(SitzverteilungDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(SitzverteilungDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Sitzverteilung"; //$NON-NLS-1$
   public static final String ID_SITZVERTEILUNG = "ID_Sitzverteilung"; //$NON-NLS-1$
   public static final String ID_SITZVERTEILUNG_QUAL = "Sitzverteilung.ID_Sitzverteilung"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Sitzverteilung.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "Sitzverteilung.ID_Liste"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "Sitzverteilung.ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION = "ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION_QUAL = "Sitzverteilung.ID_Listenkombination"; //$NON-NLS-1$
   public static final String SITZEGESAMTZAHL = "SitzeGesamtzahl"; //$NON-NLS-1$
   public static final String SITZEGESAMTZAHL_QUAL = "Sitzverteilung.SitzeGesamtzahl"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_SITZVERTEILUNG,ID_ERGEBNISEINGANG,ID_LISTE,ID_GRUPPE,ID_LISTENKOMBINATION,SITZEGESAMTZAHL};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>SitzverteilungModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, SitzverteilungModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_SITZVERTEILUNG.toUpperCase())) != null) {
         m._id_Sitzverteilung = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTE.toUpperCase())) != null) {
         m._id_Liste = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GRUPPE.toUpperCase())) != null) {
         m._id_Gruppe = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTENKOMBINATION.toUpperCase())) != null) {
         m._id_Listenkombination = r.getString(idx.intValue());
      }
      if ((idx = columns.get(SITZEGESAMTZAHL.toUpperCase())) != null) {
         m._sitzeGesamtzahl = r.getInt(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>SitzverteilungModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(SitzverteilungModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(ID_LISTE.toUpperCase())) {
         p.setString(idx++, m._id_Liste);
      }
      if (columns.containsKey(ID_GRUPPE.toUpperCase())) {
         p.setString(idx++, m._id_Gruppe);
      }
      if (columns.containsKey(ID_LISTENKOMBINATION.toUpperCase())) {
         p.setString(idx++, m._id_Listenkombination);
      }
      if (columns.containsKey(SITZEGESAMTZAHL.toUpperCase())) {
         p.setInt(idx++, m._sitzeGesamtzahl);
      }
      p.setString(idx++, m._id_Sitzverteilung);
   }

   /**
     * Inserts or changes the data of the given object in the table Sitzverteilung (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (SitzverteilungModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>SitzverteilungModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(SitzverteilungModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(ID_LISTE)) {
         values.add(toString(m.getID_Liste()));
      }
      if (columns.containsKey(ID_GRUPPE)) {
         values.add(toString(m.getID_Gruppe()));
      }
      if (columns.containsKey(ID_LISTENKOMBINATION)) {
         values.add(toString(m.getID_Listenkombination()));
      }
      if (columns.containsKey(SITZEGESAMTZAHL)) {
         values.add(toString(m.getSitzeGesamtzahl()));
      }
      values.add(toString(m.getID_Sitzverteilung()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Sitzverteilung
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
     * @param id_Sitzverteilung searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Sitzverteilung) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Sitzverteilung=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Sitzverteilung});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Sitzverteilung=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Sitzverteilung searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(SitzverteilungModelImpl m, String id_Sitzverteilung)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Sitzverteilung);
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
         values[idx++] = toString(id_Sitzverteilung);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Sitzverteilung IDs
     *

     * @return a {@link Collection} of Sitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Sitzverteilung from Sitzverteilung",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Sitzverteilung by a from parameters builded WHERE-clause
     *
     * @param id_Sitzverteilung searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Sitzverteilung) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Sitzverteilung=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Sitzverteilung});
   }

   /**
     * Changes the data of the given object in the table Sitzverteilung 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(SitzverteilungModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Sitzverteilung=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object SitzverteilungModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (SitzverteilungModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Listenkombination returns a {@link Collection} of Sitzverteilung IDs
     *
     * @param id_Listenkombination searching condition

     * @return a {@link Collection} of Sitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkombination(String id_Listenkombination)
      throws SQLException {

      return retrieveIDs(
         "select ID_Sitzverteilung from " + TABLENAME + " where ID_Listenkombination=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkombination});
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of Sitzverteilung IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of Sitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Sitzverteilung from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByID_Liste returns a {@link Collection} of Sitzverteilung IDs
     *
     * @param id_Liste searching condition

     * @return a {@link Collection} of Sitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Liste(String id_Liste) throws SQLException {
      return retrieveIDs(
         "select ID_Sitzverteilung from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Liste});
   }

   /**
     * Method retrieveIDsByID_Gruppe returns a {@link Collection} of Sitzverteilung IDs
     *
     * @param id_Gruppe searching condition

     * @return a {@link Collection} of Sitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gruppe(String id_Gruppe) throws SQLException {
      return retrieveIDs(
         "select ID_Sitzverteilung from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gruppe});
   }

   /**
     * Method retrieveIDsBySitzeGesamtzahl returns a {@link Collection} of Sitzverteilung IDs
     *
     * @param sitzeGesamtzahl searching condition

     * @return a {@link Collection} of Sitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySitzeGesamtzahl(int sitzeGesamtzahl)
      throws SQLException {

      return retrieveIDs(
         "select ID_Sitzverteilung from " + TABLENAME + " where SitzeGesamtzahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(sitzeGesamtzahl)});
   }
}
