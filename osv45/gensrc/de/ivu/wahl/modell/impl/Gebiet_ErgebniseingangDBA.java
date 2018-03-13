/*
 * Gebiet_ErgebniseingangDBA
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
  * Implementation of the persistency layer for the entity Gebiet_Ergebniseingang.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class Gebiet_ErgebniseingangDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(Gebiet_ErgebniseingangDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(Gebiet_ErgebniseingangDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Gebiet_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_GEBIET = "ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET_QUAL = "Gebiet_Ergebniseingang.ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Gebiet_Ergebniseingang.ID_Ergebniseingang"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_GEBIET,ID_ERGEBNISEINGANG};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>Gebiet_ErgebniseingangModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, Gebiet_ErgebniseingangModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_GEBIET.toUpperCase())) != null) {
         m._id_Gebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>Gebiet_ErgebniseingangModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(Gebiet_ErgebniseingangModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      p.setQueryTimeout(QUERY_TIMEOUT);
      p.setString(idx++, m._id_Gebiet);
      p.setString(idx++, m._id_Ergebniseingang);
   }

   /**
     * Inserts or changes the data of the given object in the table Gebiet_Ergebniseingang (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (Gebiet_ErgebniseingangModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>Gebiet_ErgebniseingangModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(Gebiet_ErgebniseingangModelImpl m) {
      List<String> values = new ArrayList<String>();
      values.add(toString(m.getID_Gebiet()));
      values.add(toString(m.getID_Ergebniseingang()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Gebiet_Ergebniseingang
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
     * @param id_Gebiet searching condition
     * @param id_Ergebniseingang searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Gebiet, String id_Ergebniseingang) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Gebiet=? and ID_Ergebniseingang=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gebiet, id_Ergebniseingang});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Gebiet=? and ID_Ergebniseingang=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Gebiet searching condition
     * @param id_Ergebniseingang searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(Gebiet_ErgebniseingangModelImpl m, String id_Gebiet, String id_Ergebniseingang)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Gebiet);
            prepstatement.setString(2, id_Ergebniseingang);
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
         values[idx++] = toString(id_Gebiet);
         values[idx++] = toString(id_Ergebniseingang);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Gebiet_Ergebniseingang IDs
     *

     * @return a {@link Collection} of Gebiet_Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet, ID_Ergebniseingang from Gebiet_Ergebniseingang",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Gebiet_Ergebniseingang by a from parameters builded WHERE-clause
     *
     * @param id_Gebiet searching condition
     * @param id_Ergebniseingang searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Gebiet, String id_Ergebniseingang) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Gebiet=? and ID_Ergebniseingang=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gebiet, id_Ergebniseingang});
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object Gebiet_ErgebniseingangModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (Gebiet_ErgebniseingangModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Gets a {@link Collection} of IDs from Gebiet-entities, which is described by
     * N:M relation Gebiet_Ergebniseingang and ID_Ergebniseingang.
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Gets a {@link Collection} of IDs from Ergebniseingang-entities, which is described by
     * N:M relation Gebiet_Ergebniseingang and ID_Gebiet.
     *
     * @param id_Gebiet searching condition

     * @return a {@link Collection} of Ergebniseingang IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gebiet(String id_Gebiet) throws SQLException {
      return retrieveIDs(
         "select ID_Ergebniseingang from " + TABLENAME + " where ID_Gebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gebiet});
   }
}
