/*
 * StimmergebnisseUntergebieteDBA
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
  * Implementation of the persistency layer for the entity StimmergebnisseUntergebiete.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class StimmergebnisseUntergebieteDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(StimmergebnisseUntergebieteDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(StimmergebnisseUntergebieteDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "StimmergebnisseUntergebiete"; //$NON-NLS-1$
   public static final String ID_STIMMERGEBNISSEUNTERGEBIETE = "ID_StimmergebnisseUntergebiete"; //$NON-NLS-1$
   public static final String ID_STIMMERGEBNISSEUNTERGEBIETE_QUAL = "StimmergebnisseUntergebiete.ID_StimmergebnisseUntergebiete"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "StimmergebnisseUntergebiete.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_GEBIET = "ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET_QUAL = "StimmergebnisseUntergebiete.ID_Gebiet"; //$NON-NLS-1$
   public static final String ERGEBNISSEXML = "ErgebnisseXML"; //$NON-NLS-1$
   public static final String ERGEBNISSEXML_QUAL = "StimmergebnisseUntergebiete.ErgebnisseXML"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_STIMMERGEBNISSEUNTERGEBIETE,ID_ERGEBNISEINGANG,ID_GEBIET,ERGEBNISSEXML};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>StimmergebnisseUntergebieteModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, StimmergebnisseUntergebieteModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_STIMMERGEBNISSEUNTERGEBIETE.toUpperCase())) != null) {
         m._id_StimmergebnisseUntergebiete = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GEBIET.toUpperCase())) != null) {
         m._id_Gebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ERGEBNISSEXML.toUpperCase())) != null) {
         m._ergebnisseXML = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>StimmergebnisseUntergebieteModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(StimmergebnisseUntergebieteModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(ID_GEBIET.toUpperCase())) {
         p.setString(idx++, m._id_Gebiet);
      }
      if (columns.containsKey(ERGEBNISSEXML.toUpperCase())) {
         p.setString(idx++, m._ergebnisseXML);
      }
      p.setString(idx++, m._id_StimmergebnisseUntergebiete);
   }

   /**
     * Inserts or changes the data of the given object in the table StimmergebnisseUntergebiete (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (StimmergebnisseUntergebieteModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>StimmergebnisseUntergebieteModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(StimmergebnisseUntergebieteModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(ID_GEBIET)) {
         values.add(toString(m.getID_Gebiet()));
      }
      if (columns.containsKey(ERGEBNISSEXML)) {
         values.add(toString(m.getErgebnisseXML()));
      }
      values.add(toString(m.getID_StimmergebnisseUntergebiete()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table StimmergebnisseUntergebiete
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
     * @param id_StimmergebnisseUntergebiete searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_StimmergebnisseUntergebiete) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_StimmergebnisseUntergebiete=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_StimmergebnisseUntergebiete});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_StimmergebnisseUntergebiete=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_StimmergebnisseUntergebiete searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(StimmergebnisseUntergebieteModelImpl m, String id_StimmergebnisseUntergebiete)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_StimmergebnisseUntergebiete);
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
         values[idx++] = toString(id_StimmergebnisseUntergebiete);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of StimmergebnisseUntergebiete IDs
     *

     * @return a {@link Collection} of StimmergebnisseUntergebiete IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_StimmergebnisseUntergebiete from StimmergebnisseUntergebiete",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table StimmergebnisseUntergebiete by a from parameters builded WHERE-clause
     *
     * @param id_StimmergebnisseUntergebiete searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_StimmergebnisseUntergebiete) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_StimmergebnisseUntergebiete=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_StimmergebnisseUntergebiete});
   }

   /**
     * Changes the data of the given object in the table StimmergebnisseUntergebiete 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(StimmergebnisseUntergebieteModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_StimmergebnisseUntergebiete=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object StimmergebnisseUntergebieteModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (StimmergebnisseUntergebieteModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of StimmergebnisseUntergebiete IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of StimmergebnisseUntergebiete IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_StimmergebnisseUntergebiete from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByID_Gebiet returns a {@link Collection} of StimmergebnisseUntergebiete IDs
     *
     * @param id_Gebiet searching condition

     * @return a {@link Collection} of StimmergebnisseUntergebiete IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gebiet(String id_Gebiet) throws SQLException {
      return retrieveIDs(
         "select ID_StimmergebnisseUntergebiete from " + TABLENAME + " where ID_Gebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gebiet});
   }

   /**
     * Method retrieveIDsByErgebnisseXML returns a {@link Collection} of StimmergebnisseUntergebiete IDs
     *
     * @param ergebnisseXML searching condition

     * @return a {@link Collection} of StimmergebnisseUntergebiete IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByErgebnisseXML(String ergebnisseXML)
      throws SQLException {

      return retrieveIDs(
         "select ID_StimmergebnisseUntergebiete from " + TABLENAME + " where ErgebnisseXML=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{ergebnisseXML});
   }

   /**
     * Method retrieveIDsLikeErgebnisseXML returns a {@link Collection} of StimmergebnisseUntergebiete IDs
     *
     * @param ergebnisseXML searching condition

     * @return a {@link Collection} of StimmergebnisseUntergebiete IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeErgebnisseXML(String ergebnisseXML)
      throws SQLException {

      return retrieveIDs(
         "select ID_StimmergebnisseUntergebiete from " + TABLENAME + " where ErgebnisseXML like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{ergebnisseXML});
   }
}
