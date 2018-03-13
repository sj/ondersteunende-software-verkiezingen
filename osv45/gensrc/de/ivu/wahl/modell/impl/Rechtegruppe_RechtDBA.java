/*
 * Rechtegruppe_RechtDBA
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
  * Implementation of the persistency layer for the entity Rechtegruppe_Recht.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class Rechtegruppe_RechtDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(Rechtegruppe_RechtDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(Rechtegruppe_RechtDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Rechtegruppe_Recht"; //$NON-NLS-1$
   public static final String ID_RECHTEGRUPPE = "ID_Rechtegruppe"; //$NON-NLS-1$
   public static final String ID_RECHTEGRUPPE_QUAL = "Rechtegruppe_Recht.ID_Rechtegruppe"; //$NON-NLS-1$
   public static final String ID_RECHT = "ID_Recht"; //$NON-NLS-1$
   public static final String ID_RECHT_QUAL = "Rechtegruppe_Recht.ID_Recht"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_RECHTEGRUPPE,ID_RECHT};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>Rechtegruppe_RechtModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, Rechtegruppe_RechtModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_RECHTEGRUPPE.toUpperCase())) != null) {
         m._id_Rechtegruppe = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_RECHT.toUpperCase())) != null) {
         m._id_Recht = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>Rechtegruppe_RechtModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(Rechtegruppe_RechtModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      p.setQueryTimeout(QUERY_TIMEOUT);
      p.setString(idx++, m._id_Rechtegruppe);
      p.setString(idx++, m._id_Recht);
   }

   /**
     * Inserts or changes the data of the given object in the table Rechtegruppe_Recht (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (Rechtegruppe_RechtModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>Rechtegruppe_RechtModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(Rechtegruppe_RechtModelImpl m) {
      List<String> values = new ArrayList<String>();
      values.add(toString(m.getID_Rechtegruppe()));
      values.add(toString(m.getID_Recht()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Rechtegruppe_Recht
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
     * @param id_Rechtegruppe searching condition
     * @param id_Recht searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Rechtegruppe, String id_Recht) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Rechtegruppe=? and ID_Recht=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Rechtegruppe, id_Recht});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Rechtegruppe=? and ID_Recht=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Rechtegruppe searching condition
     * @param id_Recht searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(Rechtegruppe_RechtModelImpl m, String id_Rechtegruppe, String id_Recht)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Rechtegruppe);
            prepstatement.setString(2, id_Recht);
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
         String[] values = new String[2];
         int idx = 0;
         values[idx++] = toString(id_Rechtegruppe);
         values[idx++] = toString(id_Recht);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Rechtegruppe_Recht IDs
     *

     * @return a {@link Collection} of Rechtegruppe_Recht IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Rechtegruppe, ID_Recht from Rechtegruppe_Recht",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Rechtegruppe_Recht by a from parameters builded WHERE-clause
     *
     * @param id_Rechtegruppe searching condition
     * @param id_Recht searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Rechtegruppe, String id_Recht) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Rechtegruppe=? and ID_Recht=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Rechtegruppe, id_Recht});
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object Rechtegruppe_RechtModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (Rechtegruppe_RechtModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Gets a {@link Collection} of IDs from Rechtegruppe-entities, which is described by
     * N:M relation Rechtegruppe_Recht and ID_Recht.
     *
     * @param id_Recht searching condition

     * @return a {@link Collection} of Rechtegruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Recht(String id_Recht) throws SQLException {
      return retrieveIDs(
         "select ID_Rechtegruppe from " + TABLENAME + " where ID_Recht=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Recht});
   }

   /**
     * Gets a {@link Collection} of IDs from Recht-entities, which is described by
     * N:M relation Rechtegruppe_Recht and ID_Rechtegruppe.
     *
     * @param id_Rechtegruppe searching condition

     * @return a {@link Collection} of Recht IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Rechtegruppe(String id_Rechtegruppe)
      throws SQLException {

      return retrieveIDs(
         "select ID_Recht from " + TABLENAME + " where ID_Rechtegruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Rechtegruppe});
   }
}
