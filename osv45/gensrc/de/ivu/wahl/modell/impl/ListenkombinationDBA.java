/*
 * ListenkombinationDBA
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
  * Implementation of the persistency layer for the entity Listenkombination.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class ListenkombinationDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(ListenkombinationDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkombinationDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION = "ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION_QUAL = "Listenkombination.ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Listenkombination.ID_Wahl"; //$NON-NLS-1$
   public static final String BEZEICHNUNG = "Bezeichnung"; //$NON-NLS-1$
   public static final String BEZEICHNUNG_QUAL = "Listenkombination.Bezeichnung"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_LISTENKOMBINATION,ID_WAHL,BEZEICHNUNG};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>ListenkombinationModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, ListenkombinationModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_LISTENKOMBINATION.toUpperCase())) != null) {
         m._id_Listenkombination = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(BEZEICHNUNG.toUpperCase())) != null) {
         m._bezeichnung = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>ListenkombinationModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(ListenkombinationModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_WAHL.toUpperCase())) {
         p.setString(idx++, m._id_Wahl);
      }
      if (columns.containsKey(BEZEICHNUNG.toUpperCase())) {
         p.setString(idx++, m._bezeichnung);
      }
      p.setString(idx++, m._id_Listenkombination);
   }

   /**
     * Inserts or changes the data of the given object in the table Listenkombination (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (ListenkombinationModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>ListenkombinationModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(ListenkombinationModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_WAHL)) {
         values.add(toString(m.getID_Wahl()));
      }
      if (columns.containsKey(BEZEICHNUNG)) {
         values.add(toString(m.getBezeichnung()));
      }
      values.add(toString(m.getID_Listenkombination()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Listenkombination
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
     * @param id_Listenkombination searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Listenkombination) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Listenkombination=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Listenkombination});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Listenkombination=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Listenkombination searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(ListenkombinationModelImpl m, String id_Listenkombination)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Listenkombination);
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
         values[idx++] = toString(id_Listenkombination);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Listenkombination IDs
     *

     * @return a {@link Collection} of Listenkombination IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Listenkombination from Listenkombination",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Listenkombination by a from parameters builded WHERE-clause
     *
     * @param id_Listenkombination searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Listenkombination) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Listenkombination=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Listenkombination});
   }

   /**
     * Changes the data of the given object in the table Listenkombination 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(ListenkombinationModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Listenkombination=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object ListenkombinationModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (ListenkombinationModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Wahl returns a {@link Collection} of Listenkombination IDs
     *
     * @param id_Wahl searching condition

     * @return a {@link Collection} of Listenkombination IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahl(String id_Wahl) throws SQLException {
      return retrieveIDs(
         "select ID_Listenkombination from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahl});
   }

   /**
     * Method retrieveIDsByBezeichnung returns a {@link Collection} of Listenkombination IDs
     *
     * @param bezeichnung searching condition

     * @return a {@link Collection} of Listenkombination IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByBezeichnung(String bezeichnung) throws SQLException {
      return retrieveIDs(
         "select ID_Listenkombination from " + TABLENAME + " where Bezeichnung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{bezeichnung});
   }

   /**
     * Method retrieveIDsLikeBezeichnung returns a {@link Collection} of Listenkombination IDs
     *
     * @param bezeichnung searching condition

     * @return a {@link Collection} of Listenkombination IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeBezeichnung(String bezeichnung) throws SQLException {
      return retrieveIDs(
         "select ID_Listenkombination from " + TABLENAME + " where Bezeichnung like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{bezeichnung});
   }
}
