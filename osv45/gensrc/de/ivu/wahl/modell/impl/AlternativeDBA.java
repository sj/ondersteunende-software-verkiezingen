/*
 * AlternativeDBA
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
  * Implementation of the persistency layer for the entity Alternative.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class AlternativeDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(AlternativeDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(AlternativeDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Alternative"; //$NON-NLS-1$
   public static final String ID_ALTERNATIVE = "ID_Alternative"; //$NON-NLS-1$
   public static final String ID_ALTERNATIVE_QUAL = "Alternative.ID_Alternative"; //$NON-NLS-1$
   public static final String ID_KONFLIKT = "ID_Konflikt"; //$NON-NLS-1$
   public static final String ID_KONFLIKT_QUAL = "Alternative.ID_Konflikt"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION = "ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION_QUAL = "Alternative.ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "Alternative.ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "Alternative.ID_Liste"; //$NON-NLS-1$
   public static final String ID_PERSONENDATEN = "ID_Personendaten"; //$NON-NLS-1$
   public static final String ID_PERSONENDATEN_QUAL = "Alternative.ID_Personendaten"; //$NON-NLS-1$
   public static final String NUMMER = "Nummer"; //$NON-NLS-1$
   public static final String NUMMER_QUAL = "Alternative.Nummer"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_ALTERNATIVE,ID_KONFLIKT,ID_LISTENKOMBINATION,ID_GRUPPE,ID_LISTE,ID_PERSONENDATEN,NUMMER};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>AlternativeModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, AlternativeModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_ALTERNATIVE.toUpperCase())) != null) {
         m._id_Alternative = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_KONFLIKT.toUpperCase())) != null) {
         m._id_Konflikt = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTENKOMBINATION.toUpperCase())) != null) {
         m._id_Listenkombination = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GRUPPE.toUpperCase())) != null) {
         m._id_Gruppe = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTE.toUpperCase())) != null) {
         m._id_Liste = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_PERSONENDATEN.toUpperCase())) != null) {
         m._id_Personendaten = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NUMMER.toUpperCase())) != null) {
         m._nummer = r.getInt(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>AlternativeModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(AlternativeModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_KONFLIKT.toUpperCase())) {
         p.setString(idx++, m._id_Konflikt);
      }
      if (columns.containsKey(ID_LISTENKOMBINATION.toUpperCase())) {
         p.setString(idx++, m._id_Listenkombination);
      }
      if (columns.containsKey(ID_GRUPPE.toUpperCase())) {
         p.setString(idx++, m._id_Gruppe);
      }
      if (columns.containsKey(ID_LISTE.toUpperCase())) {
         p.setString(idx++, m._id_Liste);
      }
      if (columns.containsKey(ID_PERSONENDATEN.toUpperCase())) {
         p.setString(idx++, m._id_Personendaten);
      }
      if (columns.containsKey(NUMMER.toUpperCase())) {
         p.setInt(idx++, m._nummer);
      }
      p.setString(idx++, m._id_Alternative);
   }

   /**
     * Inserts or changes the data of the given object in the table Alternative (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (AlternativeModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>AlternativeModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(AlternativeModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_KONFLIKT)) {
         values.add(toString(m.getID_Konflikt()));
      }
      if (columns.containsKey(ID_LISTENKOMBINATION)) {
         values.add(toString(m.getID_Listenkombination()));
      }
      if (columns.containsKey(ID_GRUPPE)) {
         values.add(toString(m.getID_Gruppe()));
      }
      if (columns.containsKey(ID_LISTE)) {
         values.add(toString(m.getID_Liste()));
      }
      if (columns.containsKey(ID_PERSONENDATEN)) {
         values.add(toString(m.getID_Personendaten()));
      }
      if (columns.containsKey(NUMMER)) {
         values.add(toString(m.getNummer()));
      }
      values.add(toString(m.getID_Alternative()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Alternative
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
     * @param id_Alternative searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Alternative) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Alternative=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Alternative});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Alternative=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Alternative searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(AlternativeModelImpl m, String id_Alternative)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Alternative);
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
         values[idx++] = toString(id_Alternative);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Alternative IDs
     *

     * @return a {@link Collection} of Alternative IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Alternative from Alternative",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Alternative by a from parameters builded WHERE-clause
     *
     * @param id_Alternative searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Alternative) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Alternative=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Alternative});
   }

   /**
     * Changes the data of the given object in the table Alternative 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(AlternativeModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Alternative=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object AlternativeModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (AlternativeModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Personendaten returns a {@link Collection} of Alternative IDs
     *
     * @param id_Personendaten searching condition

     * @return a {@link Collection} of Alternative IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Personendaten(String id_Personendaten)
      throws SQLException {

      return retrieveIDs(
         "select ID_Alternative from " + TABLENAME + " where ID_Personendaten=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Personendaten});
   }

   /**
     * Method retrieveIDsByID_Konflikt returns a {@link Collection} of Alternative IDs
     *
     * @param id_Konflikt searching condition

     * @return a {@link Collection} of Alternative IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Konflikt(String id_Konflikt) throws SQLException {
      return retrieveIDs(
         "select ID_Alternative from " + TABLENAME + " where ID_Konflikt=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Konflikt});
   }

   /**
     * Method retrieveIDsByID_Listenkombination returns a {@link Collection} of Alternative IDs
     *
     * @param id_Listenkombination searching condition

     * @return a {@link Collection} of Alternative IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkombination(String id_Listenkombination)
      throws SQLException {

      return retrieveIDs(
         "select ID_Alternative from " + TABLENAME + " where ID_Listenkombination=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkombination});
   }

   /**
     * Method retrieveIDsByID_Gruppe returns a {@link Collection} of Alternative IDs
     *
     * @param id_Gruppe searching condition

     * @return a {@link Collection} of Alternative IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gruppe(String id_Gruppe) throws SQLException {
      return retrieveIDs(
         "select ID_Alternative from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gruppe});
   }

   /**
     * Method retrieveIDsByID_Liste returns a {@link Collection} of Alternative IDs
     *
     * @param id_Liste searching condition

     * @return a {@link Collection} of Alternative IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Liste(String id_Liste) throws SQLException {
      return retrieveIDs(
         "select ID_Alternative from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Liste});
   }

   /**
     * Method retrieveIDsByNummer returns a {@link Collection} of Alternative IDs
     *
     * @param nummer searching condition

     * @return a {@link Collection} of Alternative IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNummer(int nummer) throws SQLException {
      return retrieveIDs(
         "select ID_Alternative from " + TABLENAME + " where Nummer=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(nummer)});
   }
}
