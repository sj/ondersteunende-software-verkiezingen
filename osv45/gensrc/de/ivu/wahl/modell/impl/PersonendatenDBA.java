/*
 * PersonendatenDBA
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
  * Implementation of the persistency layer for the entity Personendaten.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class PersonendatenDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(PersonendatenDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(PersonendatenDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Personendaten"; //$NON-NLS-1$
   public static final String ID_PERSONENDATEN = "ID_Personendaten"; //$NON-NLS-1$
   public static final String ID_PERSONENDATEN_QUAL = "Personendaten.ID_Personendaten"; //$NON-NLS-1$
   public static final String ID_PERSONENDATENAGENT = "ID_PersonendatenAgent"; //$NON-NLS-1$
   public static final String ID_PERSONENDATENAGENT_QUAL = "Personendaten.ID_PersonendatenAgent"; //$NON-NLS-1$
   public static final String NACHNAME = "Nachname"; //$NON-NLS-1$
   public static final String NACHNAME_QUAL = "Personendaten.Nachname"; //$NON-NLS-1$
   public static final String VORNAME = "Vorname"; //$NON-NLS-1$
   public static final String VORNAME_QUAL = "Personendaten.Vorname"; //$NON-NLS-1$
   public static final String PRAEFIX = "Praefix"; //$NON-NLS-1$
   public static final String PRAEFIX_QUAL = "Personendaten.Praefix"; //$NON-NLS-1$
   public static final String INITIALEN = "Initialen"; //$NON-NLS-1$
   public static final String INITIALEN_QUAL = "Personendaten.Initialen"; //$NON-NLS-1$
   public static final String TITEL = "Titel"; //$NON-NLS-1$
   public static final String TITEL_QUAL = "Personendaten.Titel"; //$NON-NLS-1$
   public static final String GESCHLECHT = "Geschlecht"; //$NON-NLS-1$
   public static final String GESCHLECHT_QUAL = "Personendaten.Geschlecht"; //$NON-NLS-1$
   public static final String GENERATION = "Generation"; //$NON-NLS-1$
   public static final String GENERATION_QUAL = "Personendaten.Generation"; //$NON-NLS-1$
   public static final String LAND = "Land"; //$NON-NLS-1$
   public static final String LAND_QUAL = "Personendaten.Land"; //$NON-NLS-1$
   public static final String WOHNORT = "Wohnort"; //$NON-NLS-1$
   public static final String WOHNORT_QUAL = "Personendaten.Wohnort"; //$NON-NLS-1$
   public static final String KONTAKT_LAND = "Kontakt_Land"; //$NON-NLS-1$
   public static final String KONTAKT_LAND_QUAL = "Personendaten.Kontakt_Land"; //$NON-NLS-1$
   public static final String KONTAKT_WOHNORT = "Kontakt_Wohnort"; //$NON-NLS-1$
   public static final String KONTAKT_WOHNORT_QUAL = "Personendaten.Kontakt_Wohnort"; //$NON-NLS-1$
   public static final String KONTAKT_PLZ = "Kontakt_PLZ"; //$NON-NLS-1$
   public static final String KONTAKT_PLZ_QUAL = "Personendaten.Kontakt_PLZ"; //$NON-NLS-1$
   public static final String KONTAKT_STRASSE = "Kontakt_Strasse"; //$NON-NLS-1$
   public static final String KONTAKT_STRASSE_QUAL = "Personendaten.Kontakt_Strasse"; //$NON-NLS-1$
   public static final String BENENNBAR = "Benennbar"; //$NON-NLS-1$
   public static final String BENENNBAR_QUAL = "Personendaten.Benennbar"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_PERSONENDATEN,ID_PERSONENDATENAGENT,NACHNAME,VORNAME,PRAEFIX,INITIALEN,TITEL,GESCHLECHT,GENERATION,LAND,WOHNORT,KONTAKT_LAND,KONTAKT_WOHNORT,KONTAKT_PLZ,KONTAKT_STRASSE,BENENNBAR};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>PersonendatenModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, PersonendatenModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_PERSONENDATEN.toUpperCase())) != null) {
         m._id_Personendaten = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_PERSONENDATENAGENT.toUpperCase())) != null) {
         m._id_PersonendatenAgent = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NACHNAME.toUpperCase())) != null) {
         m._nachname = r.getString(idx.intValue());
      }
      if ((idx = columns.get(VORNAME.toUpperCase())) != null) {
         m._vorname = r.getString(idx.intValue());
      }
      if ((idx = columns.get(PRAEFIX.toUpperCase())) != null) {
         m._praefix = r.getString(idx.intValue());
      }
      if ((idx = columns.get(INITIALEN.toUpperCase())) != null) {
         m._initialen = r.getString(idx.intValue());
      }
      if ((idx = columns.get(TITEL.toUpperCase())) != null) {
         m._titel = r.getString(idx.intValue());
      }
      if ((idx = columns.get(GESCHLECHT.toUpperCase())) != null) {
         m._geschlecht = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(GENERATION.toUpperCase())) != null) {
         m._generation = r.getString(idx.intValue());
      }
      if ((idx = columns.get(LAND.toUpperCase())) != null) {
         m._land = r.getString(idx.intValue());
      }
      if ((idx = columns.get(WOHNORT.toUpperCase())) != null) {
         m._wohnort = r.getString(idx.intValue());
      }
      if ((idx = columns.get(KONTAKT_LAND.toUpperCase())) != null) {
         m._kontakt_Land = r.getString(idx.intValue());
      }
      if ((idx = columns.get(KONTAKT_WOHNORT.toUpperCase())) != null) {
         m._kontakt_Wohnort = r.getString(idx.intValue());
      }
      if ((idx = columns.get(KONTAKT_PLZ.toUpperCase())) != null) {
         m._kontakt_PLZ = r.getString(idx.intValue());
      }
      if ((idx = columns.get(KONTAKT_STRASSE.toUpperCase())) != null) {
         m._kontakt_Strasse = r.getString(idx.intValue());
      }
      if ((idx = columns.get(BENENNBAR.toUpperCase())) != null) {
         m._benennbar = r.getBoolean(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>PersonendatenModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(PersonendatenModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_PERSONENDATENAGENT.toUpperCase())) {
         p.setString(idx++, m._id_PersonendatenAgent);
      }
      if (columns.containsKey(NACHNAME.toUpperCase())) {
         p.setString(idx++, m._nachname);
      }
      if (columns.containsKey(VORNAME.toUpperCase())) {
         p.setString(idx++, m._vorname);
      }
      if (columns.containsKey(PRAEFIX.toUpperCase())) {
         p.setString(idx++, m._praefix);
      }
      if (columns.containsKey(INITIALEN.toUpperCase())) {
         p.setString(idx++, m._initialen);
      }
      if (columns.containsKey(TITEL.toUpperCase())) {
         p.setString(idx++, m._titel);
      }
      if (columns.containsKey(GESCHLECHT.toUpperCase())) {
         p.setInt(idx++, m._geschlecht);
      }
      if (columns.containsKey(GENERATION.toUpperCase())) {
         p.setString(idx++, m._generation);
      }
      if (columns.containsKey(LAND.toUpperCase())) {
         p.setString(idx++, m._land);
      }
      if (columns.containsKey(WOHNORT.toUpperCase())) {
         p.setString(idx++, m._wohnort);
      }
      if (columns.containsKey(KONTAKT_LAND.toUpperCase())) {
         p.setString(idx++, m._kontakt_Land);
      }
      if (columns.containsKey(KONTAKT_WOHNORT.toUpperCase())) {
         p.setString(idx++, m._kontakt_Wohnort);
      }
      if (columns.containsKey(KONTAKT_PLZ.toUpperCase())) {
         p.setString(idx++, m._kontakt_PLZ);
      }
      if (columns.containsKey(KONTAKT_STRASSE.toUpperCase())) {
         p.setString(idx++, m._kontakt_Strasse);
      }
      if (columns.containsKey(BENENNBAR.toUpperCase())) {
         p.setBoolean(idx++, m._benennbar);
      }
      p.setString(idx++, m._id_Personendaten);
   }

   /**
     * Inserts or changes the data of the given object in the table Personendaten (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (PersonendatenModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>PersonendatenModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(PersonendatenModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_PERSONENDATENAGENT)) {
         values.add(toString(m.getID_PersonendatenAgent()));
      }
      if (columns.containsKey(NACHNAME)) {
         values.add(toString(m.getNachname()));
      }
      if (columns.containsKey(VORNAME)) {
         values.add(toString(m.getVorname()));
      }
      if (columns.containsKey(PRAEFIX)) {
         values.add(toString(m.getPraefix()));
      }
      if (columns.containsKey(INITIALEN)) {
         values.add(toString(m.getInitialen()));
      }
      if (columns.containsKey(TITEL)) {
         values.add(toString(m.getTitel()));
      }
      if (columns.containsKey(GESCHLECHT)) {
         values.add(toString(m.getGeschlecht()));
      }
      if (columns.containsKey(GENERATION)) {
         values.add(toString(m.getGeneration()));
      }
      if (columns.containsKey(LAND)) {
         values.add(toString(m.getLand()));
      }
      if (columns.containsKey(WOHNORT)) {
         values.add(toString(m.getWohnort()));
      }
      if (columns.containsKey(KONTAKT_LAND)) {
         values.add(toString(m.getKontakt_Land()));
      }
      if (columns.containsKey(KONTAKT_WOHNORT)) {
         values.add(toString(m.getKontakt_Wohnort()));
      }
      if (columns.containsKey(KONTAKT_PLZ)) {
         values.add(toString(m.getKontakt_PLZ()));
      }
      if (columns.containsKey(KONTAKT_STRASSE)) {
         values.add(toString(m.getKontakt_Strasse()));
      }
      if (columns.containsKey(BENENNBAR)) {
         values.add(toString(m.isBenennbar()));
      }
      values.add(toString(m.getID_Personendaten()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Personendaten
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
     * @param id_Personendaten searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Personendaten) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Personendaten=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Personendaten});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Personendaten=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Personendaten searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(PersonendatenModelImpl m, String id_Personendaten)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Personendaten);
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
         values[idx++] = toString(id_Personendaten);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Personendaten IDs
     *

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from Personendaten",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Personendaten by a from parameters builded WHERE-clause
     *
     * @param id_Personendaten searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Personendaten) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Personendaten=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Personendaten});
   }

   /**
     * Changes the data of the given object in the table Personendaten 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(PersonendatenModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Personendaten=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object PersonendatenModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (PersonendatenModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_PersonendatenAgent returns a {@link Collection} of Personendaten IDs
     *
     * @param id_PersonendatenAgent searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_PersonendatenAgent(String id_PersonendatenAgent)
      throws SQLException {

      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where ID_PersonendatenAgent=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_PersonendatenAgent});
   }

   /**
     * Method retrieveIDsByNachname returns a {@link Collection} of Personendaten IDs
     *
     * @param nachname searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNachname(String nachname) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Nachname=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{nachname});
   }

   /**
     * Method retrieveIDsLikeNachname returns a {@link Collection} of Personendaten IDs
     *
     * @param nachname searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeNachname(String nachname) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Nachname like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{nachname});
   }

   /**
     * Method retrieveIDsByVorname returns a {@link Collection} of Personendaten IDs
     *
     * @param vorname searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByVorname(String vorname) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Vorname=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{vorname});
   }

   /**
     * Method retrieveIDsLikeVorname returns a {@link Collection} of Personendaten IDs
     *
     * @param vorname searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeVorname(String vorname) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Vorname like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{vorname});
   }

   /**
     * Method retrieveIDsByPraefix returns a {@link Collection} of Personendaten IDs
     *
     * @param praefix searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByPraefix(String praefix) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Praefix=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{praefix});
   }

   /**
     * Method retrieveIDsLikePraefix returns a {@link Collection} of Personendaten IDs
     *
     * @param praefix searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikePraefix(String praefix) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Praefix like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{praefix});
   }

   /**
     * Method retrieveIDsByInitialen returns a {@link Collection} of Personendaten IDs
     *
     * @param initialen searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByInitialen(String initialen) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Initialen=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{initialen});
   }

   /**
     * Method retrieveIDsLikeInitialen returns a {@link Collection} of Personendaten IDs
     *
     * @param initialen searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeInitialen(String initialen) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Initialen like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{initialen});
   }

   /**
     * Method retrieveIDsByTitel returns a {@link Collection} of Personendaten IDs
     *
     * @param titel searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByTitel(String titel) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Titel=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{titel});
   }

   /**
     * Method retrieveIDsLikeTitel returns a {@link Collection} of Personendaten IDs
     *
     * @param titel searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeTitel(String titel) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Titel like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{titel});
   }

   /**
     * Method retrieveIDsByGeschlecht returns a {@link Collection} of Personendaten IDs
     *
     * @param geschlecht searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGeschlecht(int geschlecht) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Geschlecht=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(geschlecht)});
   }

   /**
     * Method retrieveIDsByGeneration returns a {@link Collection} of Personendaten IDs
     *
     * @param generation searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGeneration(String generation) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Generation=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{generation});
   }

   /**
     * Method retrieveIDsLikeGeneration returns a {@link Collection} of Personendaten IDs
     *
     * @param generation searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeGeneration(String generation) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Generation like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{generation});
   }

   /**
     * Method retrieveIDsByLand returns a {@link Collection} of Personendaten IDs
     *
     * @param land searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByLand(String land) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Land=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{land});
   }

   /**
     * Method retrieveIDsLikeLand returns a {@link Collection} of Personendaten IDs
     *
     * @param land searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeLand(String land) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Land like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{land});
   }

   /**
     * Method retrieveIDsByWohnort returns a {@link Collection} of Personendaten IDs
     *
     * @param wohnort searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWohnort(String wohnort) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Wohnort=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{wohnort});
   }

   /**
     * Method retrieveIDsLikeWohnort returns a {@link Collection} of Personendaten IDs
     *
     * @param wohnort searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeWohnort(String wohnort) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Wohnort like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{wohnort});
   }

   /**
     * Method retrieveIDsByKontakt_Land returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_Land searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKontakt_Land(String kontakt_Land) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_Land=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_Land});
   }

   /**
     * Method retrieveIDsLikeKontakt_Land returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_Land searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeKontakt_Land(String kontakt_Land)
      throws SQLException {

      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_Land like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_Land});
   }

   /**
     * Method retrieveIDsByKontakt_Wohnort returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_Wohnort searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKontakt_Wohnort(String kontakt_Wohnort)
      throws SQLException {

      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_Wohnort=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_Wohnort});
   }

   /**
     * Method retrieveIDsLikeKontakt_Wohnort returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_Wohnort searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeKontakt_Wohnort(String kontakt_Wohnort)
      throws SQLException {

      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_Wohnort like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_Wohnort});
   }

   /**
     * Method retrieveIDsByKontakt_PLZ returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_PLZ searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKontakt_PLZ(String kontakt_PLZ) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_PLZ=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_PLZ});
   }

   /**
     * Method retrieveIDsLikeKontakt_PLZ returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_PLZ searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeKontakt_PLZ(String kontakt_PLZ) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_PLZ like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_PLZ});
   }

   /**
     * Method retrieveIDsByKontakt_Strasse returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_Strasse searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKontakt_Strasse(String kontakt_Strasse)
      throws SQLException {

      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_Strasse=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_Strasse});
   }

   /**
     * Method retrieveIDsLikeKontakt_Strasse returns a {@link Collection} of Personendaten IDs
     *
     * @param kontakt_Strasse searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeKontakt_Strasse(String kontakt_Strasse)
      throws SQLException {

      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Kontakt_Strasse like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kontakt_Strasse});
   }

   /**
     * Method retrieveIDsByBenennbar returns a {@link Collection} of Personendaten IDs
     *
     * @param benennbar searching condition

     * @return a {@link Collection} of Personendaten IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByBenennbar(boolean benennbar) throws SQLException {
      return retrieveIDs(
         "select ID_Personendaten from " + TABLENAME + " where Benennbar=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(benennbar)});
   }
}
