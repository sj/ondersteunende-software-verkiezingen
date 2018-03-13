/*
 * RestsitzverteilungDBA
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
  * Implementation of the persistency layer for the entity Restsitzverteilung.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class RestsitzverteilungDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(RestsitzverteilungDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(RestsitzverteilungDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Restsitzverteilung"; //$NON-NLS-1$
   public static final String ID_RESTSITZVERTEILUNG = "ID_Restsitzverteilung"; //$NON-NLS-1$
   public static final String ID_RESTSITZVERTEILUNG_QUAL = "Restsitzverteilung.ID_Restsitzverteilung"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Restsitzverteilung.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "Restsitzverteilung.ID_Liste"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "Restsitzverteilung.ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION = "ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION_QUAL = "Restsitzverteilung.ID_Listenkombination"; //$NON-NLS-1$
   public static final String VERTEILUNG = "Verteilung"; //$NON-NLS-1$
   public static final String VERTEILUNG_QUAL = "Restsitzverteilung.Verteilung"; //$NON-NLS-1$
   public static final String SITZE = "Sitze"; //$NON-NLS-1$
   public static final String SITZE_QUAL = "Restsitzverteilung.Sitze"; //$NON-NLS-1$
   public static final String SITZEREST = "SitzeRest"; //$NON-NLS-1$
   public static final String SITZEREST_QUAL = "Restsitzverteilung.SitzeRest"; //$NON-NLS-1$
   public static final String SITZEGESAMTZUVERTEILEN = "SitzeGesamtZuVerteilen"; //$NON-NLS-1$
   public static final String SITZEGESAMTZUVERTEILEN_QUAL = "Restsitzverteilung.SitzeGesamtZuVerteilen"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_RESTSITZVERTEILUNG,ID_ERGEBNISEINGANG,ID_LISTE,ID_GRUPPE,ID_LISTENKOMBINATION,VERTEILUNG,SITZE,SITZEREST,SITZEGESAMTZUVERTEILEN};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>RestsitzverteilungModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, RestsitzverteilungModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_RESTSITZVERTEILUNG.toUpperCase())) != null) {
         m._id_Restsitzverteilung = r.getString(idx.intValue());
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
      if ((idx = columns.get(VERTEILUNG.toUpperCase())) != null) {
         m._verteilung = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(SITZE.toUpperCase())) != null) {
         m._sitze = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(SITZEREST.toUpperCase())) != null) {
         m._sitzeRest = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(SITZEGESAMTZUVERTEILEN.toUpperCase())) != null) {
         m._sitzeGesamtZuVerteilen = r.getInt(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>RestsitzverteilungModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(RestsitzverteilungModelImpl m, PreparedStatement p) throws SQLException {
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
      if (columns.containsKey(VERTEILUNG.toUpperCase())) {
         p.setInt(idx++, m._verteilung);
      }
      if (columns.containsKey(SITZE.toUpperCase())) {
         p.setInt(idx++, m._sitze);
      }
      if (columns.containsKey(SITZEREST.toUpperCase())) {
         p.setInt(idx++, m._sitzeRest);
      }
      if (columns.containsKey(SITZEGESAMTZUVERTEILEN.toUpperCase())) {
         p.setInt(idx++, m._sitzeGesamtZuVerteilen);
      }
      p.setString(idx++, m._id_Restsitzverteilung);
   }

   /**
     * Inserts or changes the data of the given object in the table Restsitzverteilung (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (RestsitzverteilungModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>RestsitzverteilungModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(RestsitzverteilungModelImpl m) {
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
      if (columns.containsKey(VERTEILUNG)) {
         values.add(toString(m.getVerteilung()));
      }
      if (columns.containsKey(SITZE)) {
         values.add(toString(m.getSitze()));
      }
      if (columns.containsKey(SITZEREST)) {
         values.add(toString(m.getSitzeRest()));
      }
      if (columns.containsKey(SITZEGESAMTZUVERTEILEN)) {
         values.add(toString(m.getSitzeGesamtZuVerteilen()));
      }
      values.add(toString(m.getID_Restsitzverteilung()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Restsitzverteilung
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
     * @param id_Restsitzverteilung searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Restsitzverteilung) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Restsitzverteilung=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Restsitzverteilung});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Restsitzverteilung=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Restsitzverteilung searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(RestsitzverteilungModelImpl m, String id_Restsitzverteilung)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Restsitzverteilung);
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
         values[idx++] = toString(id_Restsitzverteilung);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Restsitzverteilung IDs
     *

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Restsitzverteilung from Restsitzverteilung",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Restsitzverteilung by a from parameters builded WHERE-clause
     *
     * @param id_Restsitzverteilung searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Restsitzverteilung) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Restsitzverteilung=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Restsitzverteilung});
   }

   /**
     * Changes the data of the given object in the table Restsitzverteilung 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(RestsitzverteilungModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Restsitzverteilung=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object RestsitzverteilungModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (RestsitzverteilungModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Listenkombination returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param id_Listenkombination searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkombination(String id_Listenkombination)
      throws SQLException {

      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where ID_Listenkombination=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkombination});
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByID_Liste returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param id_Liste searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Liste(String id_Liste) throws SQLException {
      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Liste});
   }

   /**
     * Method retrieveIDsByID_Gruppe returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param id_Gruppe searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gruppe(String id_Gruppe) throws SQLException {
      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gruppe});
   }

   /**
     * Method retrieveIDsByVerteilung returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param verteilung searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByVerteilung(int verteilung) throws SQLException {
      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where Verteilung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(verteilung)});
   }

   /**
     * Method retrieveIDsBySitze returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param sitze searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySitze(int sitze) throws SQLException {
      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where Sitze=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(sitze)});
   }

   /**
     * Method retrieveIDsBySitzeRest returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param sitzeRest searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySitzeRest(int sitzeRest) throws SQLException {
      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where SitzeRest=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(sitzeRest)});
   }

   /**
     * Method retrieveIDsBySitzeGesamtZuVerteilen returns a {@link Collection} of Restsitzverteilung IDs
     *
     * @param sitzeGesamtZuVerteilen searching condition

     * @return a {@link Collection} of Restsitzverteilung IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySitzeGesamtZuVerteilen(int sitzeGesamtZuVerteilen)
      throws SQLException {

      return retrieveIDs(
         "select ID_Restsitzverteilung from " + TABLENAME + " where SitzeGesamtZuVerteilen=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(sitzeGesamtZuVerteilen)});
   }
}
