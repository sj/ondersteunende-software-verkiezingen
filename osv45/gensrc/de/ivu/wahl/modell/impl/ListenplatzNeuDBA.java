/*
 * ListenplatzNeuDBA
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
  * Implementation of the persistency layer for the entity ListenplatzNeu.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class ListenplatzNeuDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(ListenplatzNeuDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenplatzNeuDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "ListenplatzNeu"; //$NON-NLS-1$
   public static final String ID_LISTENPLATZNEU = "ID_ListenplatzNeu"; //$NON-NLS-1$
   public static final String ID_LISTENPLATZNEU_QUAL = "ListenplatzNeu.ID_ListenplatzNeu"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "ListenplatzNeu.ID_Liste"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "ListenplatzNeu.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String GEAENDERT = "Geaendert"; //$NON-NLS-1$
   public static final String GEAENDERT_QUAL = "ListenplatzNeu.Geaendert"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_LISTENPLATZNEU,ID_LISTE,ID_ERGEBNISEINGANG,GEAENDERT};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>ListenplatzNeuModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, ListenplatzNeuModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_LISTENPLATZNEU.toUpperCase())) != null) {
         m._id_ListenplatzNeu = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTE.toUpperCase())) != null) {
         m._id_Liste = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(GEAENDERT.toUpperCase())) != null) {
         m._geaendert = r.getBoolean(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>ListenplatzNeuModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(ListenplatzNeuModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_LISTE.toUpperCase())) {
         p.setString(idx++, m._id_Liste);
      }
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(GEAENDERT.toUpperCase())) {
         p.setBoolean(idx++, m._geaendert);
      }
      p.setString(idx++, m._id_ListenplatzNeu);
   }

   /**
     * Inserts or changes the data of the given object in the table ListenplatzNeu (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (ListenplatzNeuModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>ListenplatzNeuModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(ListenplatzNeuModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_LISTE)) {
         values.add(toString(m.getID_Liste()));
      }
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(GEAENDERT)) {
         values.add(toString(m.isGeaendert()));
      }
      values.add(toString(m.getID_ListenplatzNeu()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table ListenplatzNeu
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
     * @param id_ListenplatzNeu searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_ListenplatzNeu) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_ListenplatzNeu=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_ListenplatzNeu});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_ListenplatzNeu=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_ListenplatzNeu searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(ListenplatzNeuModelImpl m, String id_ListenplatzNeu)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_ListenplatzNeu);
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
         values[idx++] = toString(id_ListenplatzNeu);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of ListenplatzNeu IDs
     *

     * @return a {@link Collection} of ListenplatzNeu IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_ListenplatzNeu from ListenplatzNeu",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table ListenplatzNeu by a from parameters builded WHERE-clause
     *
     * @param id_ListenplatzNeu searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_ListenplatzNeu) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_ListenplatzNeu=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_ListenplatzNeu});
   }

   /**
     * Changes the data of the given object in the table ListenplatzNeu 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(ListenplatzNeuModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_ListenplatzNeu=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object ListenplatzNeuModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (ListenplatzNeuModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Liste returns a {@link Collection} of ListenplatzNeu IDs
     *
     * @param id_Liste searching condition

     * @return a {@link Collection} of ListenplatzNeu IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Liste(String id_Liste) throws SQLException {
      return retrieveIDs(
         "select ID_ListenplatzNeu from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Liste});
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of ListenplatzNeu IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of ListenplatzNeu IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_ListenplatzNeu from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByGeaendert returns a {@link Collection} of ListenplatzNeu IDs
     *
     * @param geaendert searching condition

     * @return a {@link Collection} of ListenplatzNeu IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGeaendert(boolean geaendert) throws SQLException {
      return retrieveIDs(
         "select ID_ListenplatzNeu from " + TABLENAME + " where Geaendert=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(geaendert)});
   }
}
