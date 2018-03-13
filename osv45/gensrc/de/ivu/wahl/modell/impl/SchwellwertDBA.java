/*
 * SchwellwertDBA
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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.fw.DBABase;


/**
  * Implementation of the persistency layer for the entity Schwellwert.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class SchwellwertDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(SchwellwertDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(SchwellwertDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Schwellwert"; //$NON-NLS-1$
   public static final String ID_SCHWELLWERT = "ID_Schwellwert"; //$NON-NLS-1$
   public static final String ID_SCHWELLWERT_QUAL = "Schwellwert.ID_Schwellwert"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Schwellwert.ID_Wahl"; //$NON-NLS-1$
   public static final String NAME = "Name"; //$NON-NLS-1$
   public static final String NAME_QUAL = "Schwellwert.Name"; //$NON-NLS-1$
   public static final String SCHWELLWERTART = "Schwellwertart"; //$NON-NLS-1$
   public static final String SCHWELLWERTART_QUAL = "Schwellwert.Schwellwertart"; //$NON-NLS-1$
   public static final String WERT = "Wert"; //$NON-NLS-1$
   public static final String WERT_QUAL = "Schwellwert.Wert"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_SCHWELLWERT,ID_WAHL,NAME,SCHWELLWERTART,WERT};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>SchwellwertModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, SchwellwertModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_SCHWELLWERT.toUpperCase())) != null) {
         m._id_Schwellwert = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NAME.toUpperCase())) != null) {
         m._name = r.getString(idx.intValue());
      }
      if ((idx = columns.get(SCHWELLWERTART.toUpperCase())) != null) {
         m._schwellwertart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(WERT.toUpperCase())) != null) {
         m._wert = r.getBigDecimal(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>SchwellwertModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(SchwellwertModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_WAHL.toUpperCase())) {
         p.setString(idx++, m._id_Wahl);
      }
      if (columns.containsKey(NAME.toUpperCase())) {
         p.setString(idx++, m._name);
      }
      if (columns.containsKey(SCHWELLWERTART.toUpperCase())) {
         p.setInt(idx++, m._schwellwertart);
      }
      if (columns.containsKey(WERT.toUpperCase())) {
         p.setBigDecimal(idx++, m._wert);
      }
      p.setString(idx++, m._id_Schwellwert);
   }

   /**
     * Inserts or changes the data of the given object in the table Schwellwert (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (SchwellwertModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>SchwellwertModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(SchwellwertModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_WAHL)) {
         values.add(toString(m.getID_Wahl()));
      }
      if (columns.containsKey(NAME)) {
         values.add(toString(m.getName()));
      }
      if (columns.containsKey(SCHWELLWERTART)) {
         values.add(toString(m.getSchwellwertart()));
      }
      if (columns.containsKey(WERT)) {
         values.add(toString(m.getWert()));
      }
      values.add(toString(m.getID_Schwellwert()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Schwellwert
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
     * @param id_Schwellwert searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Schwellwert) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Schwellwert=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Schwellwert});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Schwellwert=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Schwellwert searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(SchwellwertModelImpl m, String id_Schwellwert)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Schwellwert);
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
         values[idx++] = toString(id_Schwellwert);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Schwellwert IDs
     *

     * @return a {@link Collection} of Schwellwert IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Schwellwert from Schwellwert",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Schwellwert by a from parameters builded WHERE-clause
     *
     * @param id_Schwellwert searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Schwellwert) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Schwellwert=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Schwellwert});
   }

   /**
     * Changes the data of the given object in the table Schwellwert 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(SchwellwertModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Schwellwert=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object SchwellwertModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (SchwellwertModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Wahl returns a {@link Collection} of Schwellwert IDs
     *
     * @param id_Wahl searching condition

     * @return a {@link Collection} of Schwellwert IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahl(String id_Wahl) throws SQLException {
      return retrieveIDs(
         "select ID_Schwellwert from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahl});
   }

   /**
     * Method retrieveIDsByName returns a {@link Collection} of Schwellwert IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Schwellwert IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Schwellwert from " + TABLENAME + " where Name=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsLikeName returns a {@link Collection} of Schwellwert IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Schwellwert IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Schwellwert from " + TABLENAME + " where Name like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsBySchwellwertart returns a {@link Collection} of Schwellwert IDs
     *
     * @param schwellwertart searching condition

     * @return a {@link Collection} of Schwellwert IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySchwellwertart(int schwellwertart) throws SQLException {
      return retrieveIDs(
         "select ID_Schwellwert from " + TABLENAME + " where Schwellwertart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(schwellwertart)});
   }

   /**
     * Method retrieveIDsByWert returns a {@link Collection} of Schwellwert IDs
     *
     * @param wert searching condition

     * @return a {@link Collection} of Schwellwert IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWert(BigDecimal wert) throws SQLException {
      return retrieveIDs(
         "select ID_Schwellwert from " + TABLENAME + " where Wert=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{wert});
   }
}
