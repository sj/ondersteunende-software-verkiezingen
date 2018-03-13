/*
 * ListeDBA
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
  * Implementation of the persistency layer for the entity Liste.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class ListeDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(ListeDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListeDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Liste"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "Liste.ID_Liste"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Liste.ID_Wahl"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "Liste.ID_Gruppe"; //$NON-NLS-1$
   public static final String TYP = "Typ"; //$NON-NLS-1$
   public static final String TYP_QUAL = "Liste.Typ"; //$NON-NLS-1$
   public static final String SATZ = "Satz"; //$NON-NLS-1$
   public static final String SATZ_QUAL = "Liste.Satz"; //$NON-NLS-1$
   public static final String NAME = "Name"; //$NON-NLS-1$
   public static final String NAME_QUAL = "Liste.Name"; //$NON-NLS-1$
   public static final String GESCHLECHTSICHTBAR = "GeschlechtSichtbar"; //$NON-NLS-1$
   public static final String GESCHLECHTSICHTBAR_QUAL = "Liste.GeschlechtSichtbar"; //$NON-NLS-1$
   public static final String PUBLICATIONLANGUAGE = "PublicationLanguage"; //$NON-NLS-1$
   public static final String PUBLICATIONLANGUAGE_QUAL = "Liste.PublicationLanguage"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_LISTE,ID_WAHL,ID_GRUPPE,TYP,SATZ,NAME,GESCHLECHTSICHTBAR,PUBLICATIONLANGUAGE};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>ListeModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, ListeModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_LISTE.toUpperCase())) != null) {
         m._id_Liste = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GRUPPE.toUpperCase())) != null) {
         m._id_Gruppe = r.getString(idx.intValue());
      }
      if ((idx = columns.get(TYP.toUpperCase())) != null) {
         m._typ = r.getString(idx.intValue());
      }
      if ((idx = columns.get(SATZ.toUpperCase())) != null) {
         m._satz = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(NAME.toUpperCase())) != null) {
         m._name = r.getString(idx.intValue());
      }
      if ((idx = columns.get(GESCHLECHTSICHTBAR.toUpperCase())) != null) {
         m._geschlechtSichtbar = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(PUBLICATIONLANGUAGE.toUpperCase())) != null) {
         m._publicationLanguage = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>ListeModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(ListeModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_WAHL.toUpperCase())) {
         p.setString(idx++, m._id_Wahl);
      }
      if (columns.containsKey(ID_GRUPPE.toUpperCase())) {
         p.setString(idx++, m._id_Gruppe);
      }
      if (columns.containsKey(TYP.toUpperCase())) {
         p.setString(idx++, m._typ);
      }
      if (columns.containsKey(SATZ.toUpperCase())) {
         p.setInt(idx++, m._satz);
      }
      if (columns.containsKey(NAME.toUpperCase())) {
         p.setString(idx++, m._name);
      }
      if (columns.containsKey(GESCHLECHTSICHTBAR.toUpperCase())) {
         p.setBoolean(idx++, m._geschlechtSichtbar);
      }
      if (columns.containsKey(PUBLICATIONLANGUAGE.toUpperCase())) {
         p.setString(idx++, m._publicationLanguage);
      }
      p.setString(idx++, m._id_Liste);
   }

   /**
     * Inserts or changes the data of the given object in the table Liste (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (ListeModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>ListeModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(ListeModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_WAHL)) {
         values.add(toString(m.getID_Wahl()));
      }
      if (columns.containsKey(ID_GRUPPE)) {
         values.add(toString(m.getID_Gruppe()));
      }
      if (columns.containsKey(TYP)) {
         values.add(toString(m.getTyp()));
      }
      if (columns.containsKey(SATZ)) {
         values.add(toString(m.getSatz()));
      }
      if (columns.containsKey(NAME)) {
         values.add(toString(m.getName()));
      }
      if (columns.containsKey(GESCHLECHTSICHTBAR)) {
         values.add(toString(m.isGeschlechtSichtbar()));
      }
      if (columns.containsKey(PUBLICATIONLANGUAGE)) {
         values.add(toString(m.getPublicationLanguage()));
      }
      values.add(toString(m.getID_Liste()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Liste
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
     * @param id_Liste searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Liste) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Liste});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Liste=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Liste searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(ListeModelImpl m, String id_Liste) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Liste);
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
         values[idx++] = toString(id_Liste);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Liste IDs
     *

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Liste from Liste",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Liste by a from parameters builded WHERE-clause
     *
     * @param id_Liste searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Liste) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Liste=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Liste});
   }

   /**
     * Changes the data of the given object in the table Liste 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(ListeModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Liste=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object ListeModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (ListeModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Gruppe returns a {@link Collection} of Liste IDs
     *
     * @param id_Gruppe searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gruppe(String id_Gruppe) throws SQLException {
      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gruppe});
   }

   /**
     * Method retrieveIDsByID_Wahl returns a {@link Collection} of Liste IDs
     *
     * @param id_Wahl searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahl(String id_Wahl) throws SQLException {
      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahl});
   }

   /**
     * Method retrieveIDsByTyp returns a {@link Collection} of Liste IDs
     *
     * @param typ searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByTyp(String typ) throws SQLException {
      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where Typ=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{typ});
   }

   /**
     * Method retrieveIDsLikeTyp returns a {@link Collection} of Liste IDs
     *
     * @param typ searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeTyp(String typ) throws SQLException {
      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where Typ like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{typ});
   }

   /**
     * Method retrieveIDsBySatz returns a {@link Collection} of Liste IDs
     *
     * @param satz searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySatz(int satz) throws SQLException {
      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where Satz=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(satz)});
   }

   /**
     * Method retrieveIDsByName returns a {@link Collection} of Liste IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where Name=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsLikeName returns a {@link Collection} of Liste IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where Name like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsByGeschlechtSichtbar returns a {@link Collection} of Liste IDs
     *
     * @param geschlechtSichtbar searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGeschlechtSichtbar(boolean geschlechtSichtbar)
      throws SQLException {

      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where GeschlechtSichtbar=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(geschlechtSichtbar)});
   }

   /**
     * Method retrieveIDsByPublicationLanguage returns a {@link Collection} of Liste IDs
     *
     * @param publicationLanguage searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByPublicationLanguage(String publicationLanguage)
      throws SQLException {

      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where PublicationLanguage=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{publicationLanguage});
   }

   /**
     * Method retrieveIDsLikePublicationLanguage returns a {@link Collection} of Liste IDs
     *
     * @param publicationLanguage searching condition

     * @return a {@link Collection} of Liste IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikePublicationLanguage(String publicationLanguage)
      throws SQLException {

      return retrieveIDs(
         "select ID_Liste from " + TABLENAME + " where PublicationLanguage like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{publicationLanguage});
   }
}
