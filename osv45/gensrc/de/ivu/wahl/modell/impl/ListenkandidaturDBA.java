/*
 * ListenkandidaturDBA
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
  * Implementation of the persistency layer for the entity Listenkandidatur.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class ListenkandidaturDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(ListenkandidaturDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkandidaturDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Listenkandidatur"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATUR = "ID_Listenkandidatur"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATUR_QUAL = "Listenkandidatur.ID_Listenkandidatur"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "Listenkandidatur.ID_Liste"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Listenkandidatur.ID_Wahl"; //$NON-NLS-1$
   public static final String ID_PERSONENDATEN = "ID_Personendaten"; //$NON-NLS-1$
   public static final String ID_PERSONENDATEN_QUAL = "Listenkandidatur.ID_Personendaten"; //$NON-NLS-1$
   public static final String LISTENPLATZ = "Listenplatz"; //$NON-NLS-1$
   public static final String LISTENPLATZ_QUAL = "Listenkandidatur.Listenplatz"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_LISTENKANDIDATUR,ID_LISTE,ID_WAHL,ID_PERSONENDATEN,LISTENPLATZ};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>ListenkandidaturModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, ListenkandidaturModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_LISTENKANDIDATUR.toUpperCase())) != null) {
         m._id_Listenkandidatur = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTE.toUpperCase())) != null) {
         m._id_Liste = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_PERSONENDATEN.toUpperCase())) != null) {
         m._id_Personendaten = r.getString(idx.intValue());
      }
      if ((idx = columns.get(LISTENPLATZ.toUpperCase())) != null) {
         m._listenplatz = r.getInt(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>ListenkandidaturModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(ListenkandidaturModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_LISTE.toUpperCase())) {
         p.setString(idx++, m._id_Liste);
      }
      if (columns.containsKey(ID_WAHL.toUpperCase())) {
         p.setString(idx++, m._id_Wahl);
      }
      if (columns.containsKey(ID_PERSONENDATEN.toUpperCase())) {
         p.setString(idx++, m._id_Personendaten);
      }
      if (columns.containsKey(LISTENPLATZ.toUpperCase())) {
         p.setInt(idx++, m._listenplatz);
      }
      p.setString(idx++, m._id_Listenkandidatur);
   }

   /**
     * Inserts or changes the data of the given object in the table Listenkandidatur (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (ListenkandidaturModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>ListenkandidaturModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(ListenkandidaturModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_LISTE)) {
         values.add(toString(m.getID_Liste()));
      }
      if (columns.containsKey(ID_WAHL)) {
         values.add(toString(m.getID_Wahl()));
      }
      if (columns.containsKey(ID_PERSONENDATEN)) {
         values.add(toString(m.getID_Personendaten()));
      }
      if (columns.containsKey(LISTENPLATZ)) {
         values.add(toString(m.getListenplatz()));
      }
      values.add(toString(m.getID_Listenkandidatur()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Listenkandidatur
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
     * @param id_Listenkandidatur searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Listenkandidatur) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Listenkandidatur=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Listenkandidatur});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Listenkandidatur=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Listenkandidatur searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(ListenkandidaturModelImpl m, String id_Listenkandidatur)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Listenkandidatur);
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
         values[idx++] = toString(id_Listenkandidatur);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Listenkandidatur IDs
     *

     * @return a {@link Collection} of Listenkandidatur IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Listenkandidatur from Listenkandidatur",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Listenkandidatur by a from parameters builded WHERE-clause
     *
     * @param id_Listenkandidatur searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Listenkandidatur) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Listenkandidatur=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Listenkandidatur});
   }

   /**
     * Changes the data of the given object in the table Listenkandidatur 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(ListenkandidaturModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Listenkandidatur=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object ListenkandidaturModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (ListenkandidaturModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Liste returns a {@link Collection} of Listenkandidatur IDs
     *
     * @param id_Liste searching condition

     * @return a {@link Collection} of Listenkandidatur IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Liste(String id_Liste) throws SQLException {
      return retrieveIDs(
         "select ID_Listenkandidatur from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Liste});
   }

   /**
     * Method retrieveIDsByID_Personendaten returns a {@link Collection} of Listenkandidatur IDs
     *
     * @param id_Personendaten searching condition

     * @return a {@link Collection} of Listenkandidatur IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Personendaten(String id_Personendaten)
      throws SQLException {

      return retrieveIDs(
         "select ID_Listenkandidatur from " + TABLENAME + " where ID_Personendaten=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Personendaten});
   }

   /**
     * Method retrieveIDsByID_Wahl returns a {@link Collection} of Listenkandidatur IDs
     *
     * @param id_Wahl searching condition

     * @return a {@link Collection} of Listenkandidatur IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahl(String id_Wahl) throws SQLException {
      return retrieveIDs(
         "select ID_Listenkandidatur from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahl});
   }

   /**
     * Method retrieveIDsByListenplatz returns a {@link Collection} of Listenkandidatur IDs
     *
     * @param listenplatz searching condition

     * @return a {@link Collection} of Listenkandidatur IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByListenplatz(int listenplatz) throws SQLException {
      return retrieveIDs(
         "select ID_Listenkandidatur from " + TABLENAME + " where Listenplatz=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(listenplatz)});
   }
}
