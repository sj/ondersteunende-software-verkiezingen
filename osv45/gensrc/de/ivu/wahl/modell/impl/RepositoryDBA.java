/*
 * RepositoryDBA
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
  * Implementation of the persistency layer for the entity Repository.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class RepositoryDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(RepositoryDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(RepositoryDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Repository"; //$NON-NLS-1$
   public static final String ID_REPOSITORY = "ID_Repository"; //$NON-NLS-1$
   public static final String ID_REPOSITORY_QUAL = "Repository.ID_Repository"; //$NON-NLS-1$
   public static final String NAME = "Name"; //$NON-NLS-1$
   public static final String NAME_QUAL = "Repository.Name"; //$NON-NLS-1$
   public static final String WERT = "Wert"; //$NON-NLS-1$
   public static final String WERT_QUAL = "Repository.Wert"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_REPOSITORY,NAME,WERT};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>RepositoryModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, RepositoryModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_REPOSITORY.toUpperCase())) != null) {
         m._id_Repository = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NAME.toUpperCase())) != null) {
         m._name = r.getString(idx.intValue());
      }
      if ((idx = columns.get(WERT.toUpperCase())) != null) {
         m._wert = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>RepositoryModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(RepositoryModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(NAME.toUpperCase())) {
         p.setString(idx++, m._name);
      }
      if (columns.containsKey(WERT.toUpperCase())) {
         p.setString(idx++, m._wert);
      }
      p.setString(idx++, m._id_Repository);
   }

   /**
     * Inserts or changes the data of the given object in the table Repository (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (RepositoryModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>RepositoryModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(RepositoryModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(NAME)) {
         values.add(toString(m.getName()));
      }
      if (columns.containsKey(WERT)) {
         values.add(toString(m.getWert()));
      }
      values.add(toString(m.getID_Repository()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Repository
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
     * @param id_Repository searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Repository) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Repository=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Repository});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Repository=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Repository searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(RepositoryModelImpl m, String id_Repository) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Repository);
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
         values[idx++] = toString(id_Repository);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Repository IDs
     *

     * @return a {@link Collection} of Repository IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Repository from Repository",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Repository by a from parameters builded WHERE-clause
     *
     * @param id_Repository searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Repository) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Repository=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Repository});
   }

   /**
     * Changes the data of the given object in the table Repository 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(RepositoryModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Repository=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object RepositoryModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (RepositoryModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByName returns a {@link Collection} of Repository IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Repository IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Repository from " + TABLENAME + " where Name=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsLikeName returns a {@link Collection} of Repository IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Repository IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Repository from " + TABLENAME + " where Name like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsByWert returns a {@link Collection} of Repository IDs
     *
     * @param wert searching condition

     * @return a {@link Collection} of Repository IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWert(String wert) throws SQLException {
      return retrieveIDs(
         "select ID_Repository from " + TABLENAME + " where Wert=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{wert});
   }

   /**
     * Method retrieveIDsLikeWert returns a {@link Collection} of Repository IDs
     *
     * @param wert searching condition

     * @return a {@link Collection} of Repository IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeWert(String wert) throws SQLException {
      return retrieveIDs(
         "select ID_Repository from " + TABLENAME + " where Wert like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{wert});
   }
}
