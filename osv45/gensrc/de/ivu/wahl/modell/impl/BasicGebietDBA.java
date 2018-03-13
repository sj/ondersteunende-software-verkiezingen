/*
 * BasicGebietDBA
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
  * Implementation of the persistency layer for the entity Gebiet.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicGebietDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(BasicGebietDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGebietDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET = "ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET_QUAL = "Gebiet.ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_UEBERGEORDNETESGEBIET = "ID_UebergeordnetesGebiet"; //$NON-NLS-1$
   public static final String ID_UEBERGEORDNETESGEBIET_QUAL = "Gebiet.ID_UebergeordnetesGebiet"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Gebiet.ID_Wahl"; //$NON-NLS-1$
   public static final String ID_LETZTEREINGANG = "ID_LetzterEingang"; //$NON-NLS-1$
   public static final String ID_LETZTEREINGANG_QUAL = "Gebiet.ID_LetzterEingang"; //$NON-NLS-1$
   public static final String ERFASSUNGSEINHEIT = "Erfassungseinheit"; //$NON-NLS-1$
   public static final String ERFASSUNGSEINHEIT_QUAL = "Gebiet.Erfassungseinheit"; //$NON-NLS-1$
   public static final String WAHLEINHEIT = "Wahleinheit"; //$NON-NLS-1$
   public static final String WAHLEINHEIT_QUAL = "Gebiet.Wahleinheit"; //$NON-NLS-1$
   public static final String GEBIETSART = "Gebietsart"; //$NON-NLS-1$
   public static final String GEBIETSART_QUAL = "Gebiet.Gebietsart"; //$NON-NLS-1$
   public static final String NUMMER = "Nummer"; //$NON-NLS-1$
   public static final String NUMMER_QUAL = "Gebiet.Nummer"; //$NON-NLS-1$
   public static final String ROEMISCH = "Roemisch"; //$NON-NLS-1$
   public static final String ROEMISCH_QUAL = "Gebiet.Roemisch"; //$NON-NLS-1$
   public static final String NAME = "Name"; //$NON-NLS-1$
   public static final String NAME_QUAL = "Gebiet.Name"; //$NON-NLS-1$
   public static final String KUERZEL = "Kuerzel"; //$NON-NLS-1$
   public static final String KUERZEL_QUAL = "Gebiet.Kuerzel"; //$NON-NLS-1$
   public static final String POSITION = "Position"; //$NON-NLS-1$
   public static final String POSITION_QUAL = "Gebiet.Position"; //$NON-NLS-1$
   public static final String WAHLBERECHTIGTE = "Wahlberechtigte"; //$NON-NLS-1$
   public static final String WAHLBERECHTIGTE_QUAL = "Gebiet.Wahlberechtigte"; //$NON-NLS-1$
   public static final String GUIEINGABEERLAUBT = "GUIEingabeErlaubt"; //$NON-NLS-1$
   public static final String GUIEINGABEERLAUBT_QUAL = "Gebiet.GUIEingabeErlaubt"; //$NON-NLS-1$
   public static final String POSTALVOTE = "Postalvote"; //$NON-NLS-1$
   public static final String POSTALVOTE_QUAL = "Gebiet.Postalvote"; //$NON-NLS-1$
   public static final String VOTEVALUE = "VoteValue"; //$NON-NLS-1$
   public static final String VOTEVALUE_QUAL = "Gebiet.VoteValue"; //$NON-NLS-1$
   public static final String ZIPCODE = "Zipcode"; //$NON-NLS-1$
   public static final String ZIPCODE_QUAL = "Gebiet.Zipcode"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_GEBIET,ID_UEBERGEORDNETESGEBIET,ID_WAHL,ID_LETZTEREINGANG,ERFASSUNGSEINHEIT,WAHLEINHEIT,GEBIETSART,NUMMER,ROEMISCH,NAME,KUERZEL,POSITION,WAHLBERECHTIGTE,GUIEINGABEERLAUBT,POSTALVOTE,VOTEVALUE,ZIPCODE};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>GebietModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, GebietModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_GEBIET.toUpperCase())) != null) {
         m._id_Gebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_UEBERGEORDNETESGEBIET.toUpperCase())) != null) {
         m._id_UebergeordnetesGebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LETZTEREINGANG.toUpperCase())) != null) {
         m._id_LetzterEingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ERFASSUNGSEINHEIT.toUpperCase())) != null) {
         m._erfassungseinheit = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(WAHLEINHEIT.toUpperCase())) != null) {
         m._wahleinheit = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(GEBIETSART.toUpperCase())) != null) {
         m._gebietsart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(NUMMER.toUpperCase())) != null) {
         m._nummer = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ROEMISCH.toUpperCase())) != null) {
         m._roemisch = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(NAME.toUpperCase())) != null) {
         m._name = r.getString(idx.intValue());
      }
      if ((idx = columns.get(KUERZEL.toUpperCase())) != null) {
         m._kuerzel = r.getString(idx.intValue());
      }
      if ((idx = columns.get(POSITION.toUpperCase())) != null) {
         m._position = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(WAHLBERECHTIGTE.toUpperCase())) != null) {
         m._wahlberechtigte = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(GUIEINGABEERLAUBT.toUpperCase())) != null) {
         m._gUIEingabeErlaubt = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(POSTALVOTE.toUpperCase())) != null) {
         m._postalvote = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(VOTEVALUE.toUpperCase())) != null) {
         m._voteValue = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ZIPCODE.toUpperCase())) != null) {
         m._zipcode = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>GebietModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(GebietModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_UEBERGEORDNETESGEBIET.toUpperCase())) {
         p.setString(idx++, m._id_UebergeordnetesGebiet);
      }
      if (columns.containsKey(ID_WAHL.toUpperCase())) {
         p.setString(idx++, m._id_Wahl);
      }
      if (columns.containsKey(ID_LETZTEREINGANG.toUpperCase())) {
         p.setString(idx++, m._id_LetzterEingang);
      }
      if (columns.containsKey(ERFASSUNGSEINHEIT.toUpperCase())) {
         p.setBoolean(idx++, m._erfassungseinheit);
      }
      if (columns.containsKey(WAHLEINHEIT.toUpperCase())) {
         p.setBoolean(idx++, m._wahleinheit);
      }
      if (columns.containsKey(GEBIETSART.toUpperCase())) {
         p.setInt(idx++, m._gebietsart);
      }
      if (columns.containsKey(NUMMER.toUpperCase())) {
         p.setInt(idx++, m._nummer);
      }
      if (columns.containsKey(ROEMISCH.toUpperCase())) {
         p.setBoolean(idx++, m._roemisch);
      }
      if (columns.containsKey(NAME.toUpperCase())) {
         p.setString(idx++, m._name);
      }
      if (columns.containsKey(KUERZEL.toUpperCase())) {
         p.setString(idx++, m._kuerzel);
      }
      if (columns.containsKey(POSITION.toUpperCase())) {
         p.setInt(idx++, m._position);
      }
      if (columns.containsKey(WAHLBERECHTIGTE.toUpperCase())) {
         p.setInt(idx++, m._wahlberechtigte);
      }
      if (columns.containsKey(GUIEINGABEERLAUBT.toUpperCase())) {
         p.setBoolean(idx++, m._gUIEingabeErlaubt);
      }
      if (columns.containsKey(POSTALVOTE.toUpperCase())) {
         p.setBoolean(idx++, m._postalvote);
      }
      if (columns.containsKey(VOTEVALUE.toUpperCase())) {
         p.setInt(idx++, m._voteValue);
      }
      if (columns.containsKey(ZIPCODE.toUpperCase())) {
         p.setString(idx++, m._zipcode);
      }
      p.setString(idx++, m._id_Gebiet);
   }

   /**
     * Inserts or changes the data of the given object in the table Gebiet (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (GebietModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>GebietModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(GebietModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_UEBERGEORDNETESGEBIET)) {
         values.add(toString(m.getID_UebergeordnetesGebiet()));
      }
      if (columns.containsKey(ID_WAHL)) {
         values.add(toString(m.getID_Wahl()));
      }
      if (columns.containsKey(ID_LETZTEREINGANG)) {
         values.add(toString(m.getID_LetzterEingang()));
      }
      if (columns.containsKey(ERFASSUNGSEINHEIT)) {
         values.add(toString(m.isErfassungseinheit()));
      }
      if (columns.containsKey(WAHLEINHEIT)) {
         values.add(toString(m.isWahleinheit()));
      }
      if (columns.containsKey(GEBIETSART)) {
         values.add(toString(m.getGebietsart()));
      }
      if (columns.containsKey(NUMMER)) {
         values.add(toString(m.getNummer()));
      }
      if (columns.containsKey(ROEMISCH)) {
         values.add(toString(m.isRoemisch()));
      }
      if (columns.containsKey(NAME)) {
         values.add(toString(m.getName()));
      }
      if (columns.containsKey(KUERZEL)) {
         values.add(toString(m.getKuerzel()));
      }
      if (columns.containsKey(POSITION)) {
         values.add(toString(m.getPosition()));
      }
      if (columns.containsKey(WAHLBERECHTIGTE)) {
         values.add(toString(m.getWahlberechtigte()));
      }
      if (columns.containsKey(GUIEINGABEERLAUBT)) {
         values.add(toString(m.isGUIEingabeErlaubt()));
      }
      if (columns.containsKey(POSTALVOTE)) {
         values.add(toString(m.isPostalvote()));
      }
      if (columns.containsKey(VOTEVALUE)) {
         values.add(toString(m.getVoteValue()));
      }
      if (columns.containsKey(ZIPCODE)) {
         values.add(toString(m.getZipcode()));
      }
      values.add(toString(m.getID_Gebiet()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Gebiet
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
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Gebiet) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Gebiet=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gebiet});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Gebiet=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Gebiet searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(GebietModelImpl m, String id_Gebiet) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Gebiet);
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
         values[idx++] = toString(id_Gebiet);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Gebiet IDs
     *

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from Gebiet",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Gebiet by a from parameters builded WHERE-clause
     *
     * @param id_Gebiet searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Gebiet) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Gebiet=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gebiet});
   }

   /**
     * Changes the data of the given object in the table Gebiet 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(GebietModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Gebiet=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object GebietModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (GebietModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_LetzterEingang returns a {@link Collection} of Gebiet IDs
     *
     * @param id_LetzterEingang searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_LetzterEingang(String id_LetzterEingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where ID_LetzterEingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_LetzterEingang});
   }

   /**
     * Method retrieveIDsByID_UebergeordnetesGebiet returns a {@link Collection} of Gebiet IDs
     *
     * @param id_UebergeordnetesGebiet searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_UebergeordnetesGebiet(String id_UebergeordnetesGebiet)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where ID_UebergeordnetesGebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_UebergeordnetesGebiet});
   }

   /**
     * Method retrieveIDsByID_Wahl returns a {@link Collection} of Gebiet IDs
     *
     * @param id_Wahl searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahl(String id_Wahl) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahl});
   }

   /**
     * Method retrieveIDsByErfassungseinheit returns a {@link Collection} of Gebiet IDs
     *
     * @param erfassungseinheit searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByErfassungseinheit(boolean erfassungseinheit)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Erfassungseinheit=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(erfassungseinheit)});
   }

   /**
     * Method retrieveIDsByWahleinheit returns a {@link Collection} of Gebiet IDs
     *
     * @param wahleinheit searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahleinheit(boolean wahleinheit) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Wahleinheit=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(wahleinheit)});
   }

   /**
     * Method retrieveIDsByGebietsart returns a {@link Collection} of Gebiet IDs
     *
     * @param gebietsart searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGebietsart(int gebietsart) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Gebietsart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(gebietsart)});
   }

   /**
     * Method retrieveIDsByNummer returns a {@link Collection} of Gebiet IDs
     *
     * @param nummer searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNummer(int nummer) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Nummer=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(nummer)});
   }

   /**
     * Method retrieveIDsByRoemisch returns a {@link Collection} of Gebiet IDs
     *
     * @param roemisch searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByRoemisch(boolean roemisch) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Roemisch=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(roemisch)});
   }

   /**
     * Method retrieveIDsByName returns a {@link Collection} of Gebiet IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Name=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsLikeName returns a {@link Collection} of Gebiet IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Name like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsByKuerzel returns a {@link Collection} of Gebiet IDs
     *
     * @param kuerzel searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKuerzel(String kuerzel) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Kuerzel=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kuerzel});
   }

   /**
     * Method retrieveIDsLikeKuerzel returns a {@link Collection} of Gebiet IDs
     *
     * @param kuerzel searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeKuerzel(String kuerzel) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Kuerzel like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{kuerzel});
   }

   /**
     * Method retrieveIDsByPosition returns a {@link Collection} of Gebiet IDs
     *
     * @param position searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByPosition(int position) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Position=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(position)});
   }

   /**
     * Method retrieveIDsByWahlberechtigte returns a {@link Collection} of Gebiet IDs
     *
     * @param wahlberechtigte searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahlberechtigte(int wahlberechtigte)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Wahlberechtigte=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(wahlberechtigte)});
   }

   /**
     * Method retrieveIDsByGUIEingabeErlaubt returns a {@link Collection} of Gebiet IDs
     *
     * @param gUIEingabeErlaubt searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGUIEingabeErlaubt(boolean gUIEingabeErlaubt)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where GUIEingabeErlaubt=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(gUIEingabeErlaubt)});
   }

   /**
     * Method retrieveIDsByPostalvote returns a {@link Collection} of Gebiet IDs
     *
     * @param postalvote searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByPostalvote(boolean postalvote) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Postalvote=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(postalvote)});
   }

   /**
     * Method retrieveIDsByVoteValue returns a {@link Collection} of Gebiet IDs
     *
     * @param voteValue searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByVoteValue(int voteValue) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where VoteValue=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(voteValue)});
   }

   /**
     * Method retrieveIDsByZipcode returns a {@link Collection} of Gebiet IDs
     *
     * @param zipcode searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByZipcode(String zipcode) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Zipcode=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{zipcode});
   }

   /**
     * Method retrieveIDsLikeZipcode returns a {@link Collection} of Gebiet IDs
     *
     * @param zipcode searching condition

     * @return a {@link Collection} of Gebiet IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeZipcode(String zipcode) throws SQLException {
      return retrieveIDs(
         "select ID_Gebiet from " + TABLENAME + " where Zipcode like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{zipcode});
   }
}
