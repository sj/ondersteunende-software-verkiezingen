/*
 * AnwenderDBA
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
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.ejb.fw.DBABase;


/**
  * Implementation of the persistency layer for the entity Anwender.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class AnwenderDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(AnwenderDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(AnwenderDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Anwender"; //$NON-NLS-1$
   public static final String ID_ANWENDER = "ID_Anwender"; //$NON-NLS-1$
   public static final String ID_ANWENDER_QUAL = "Anwender.ID_Anwender"; //$NON-NLS-1$
   public static final String ID_GEBIET = "ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET_QUAL = "Anwender.ID_Gebiet"; //$NON-NLS-1$
   public static final String NAME = "Name"; //$NON-NLS-1$
   public static final String NAME_QUAL = "Anwender.Name"; //$NON-NLS-1$
   public static final String ANWENDERNAME = "Anwendername"; //$NON-NLS-1$
   public static final String ANWENDERNAME_QUAL = "Anwender.Anwendername"; //$NON-NLS-1$
   public static final String PASSWORDHASH = "PasswordHash"; //$NON-NLS-1$
   public static final String PASSWORDHASH_QUAL = "Anwender.PasswordHash"; //$NON-NLS-1$
   public static final String SALT = "Salt"; //$NON-NLS-1$
   public static final String SALT_QUAL = "Anwender.Salt"; //$NON-NLS-1$
   public static final String FEHLVERSUCHEANMELDUNG = "FehlversucheAnmeldung"; //$NON-NLS-1$
   public static final String FEHLVERSUCHEANMELDUNG_QUAL = "Anwender.FehlversucheAnmeldung"; //$NON-NLS-1$
   public static final String LETZTERZUGRIFF = "LetzterZugriff"; //$NON-NLS-1$
   public static final String LETZTERZUGRIFF_QUAL = "Anwender.LetzterZugriff"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_ANWENDER,ID_GEBIET,NAME,ANWENDERNAME,PASSWORDHASH,SALT,FEHLVERSUCHEANMELDUNG,LETZTERZUGRIFF};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>AnwenderModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, AnwenderModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_ANWENDER.toUpperCase())) != null) {
         m._id_Anwender = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GEBIET.toUpperCase())) != null) {
         m._id_Gebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NAME.toUpperCase())) != null) {
         m._name = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ANWENDERNAME.toUpperCase())) != null) {
         m._anwendername = r.getString(idx.intValue());
      }
      if ((idx = columns.get(PASSWORDHASH.toUpperCase())) != null) {
         m._passwordHash = r.getString(idx.intValue());
      }
      if ((idx = columns.get(SALT.toUpperCase())) != null) {
         m._salt = r.getString(idx.intValue());
      }
      if ((idx = columns.get(FEHLVERSUCHEANMELDUNG.toUpperCase())) != null) {
         m._fehlversucheAnmeldung = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(LETZTERZUGRIFF.toUpperCase())) != null) {
         m._letzterZugriff = r.getTimestamp(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>AnwenderModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(AnwenderModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_GEBIET.toUpperCase())) {
         p.setString(idx++, m._id_Gebiet);
      }
      if (columns.containsKey(NAME.toUpperCase())) {
         p.setString(idx++, m._name);
      }
      if (columns.containsKey(ANWENDERNAME.toUpperCase())) {
         p.setString(idx++, m._anwendername);
      }
      if (columns.containsKey(PASSWORDHASH.toUpperCase())) {
         p.setString(idx++, m._passwordHash);
      }
      if (columns.containsKey(SALT.toUpperCase())) {
         p.setString(idx++, m._salt);
      }
      if (columns.containsKey(FEHLVERSUCHEANMELDUNG.toUpperCase())) {
         p.setInt(idx++, m._fehlversucheAnmeldung);
      }
      if (columns.containsKey(LETZTERZUGRIFF.toUpperCase())) {
         p.setTimestamp(idx++, m._letzterZugriff);
      }
      p.setString(idx++, m._id_Anwender);
   }

   /**
     * Inserts or changes the data of the given object in the table Anwender (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (AnwenderModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>AnwenderModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(AnwenderModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_GEBIET)) {
         values.add(toString(m.getID_Gebiet()));
      }
      if (columns.containsKey(NAME)) {
         values.add(toString(m.getName()));
      }
      if (columns.containsKey(ANWENDERNAME)) {
         values.add(toString(m.getAnwendername()));
      }
      if (columns.containsKey(PASSWORDHASH)) {
         values.add(toString(m.getPasswordHash()));
      }
      if (columns.containsKey(SALT)) {
         values.add(toString(m.getSalt()));
      }
      if (columns.containsKey(FEHLVERSUCHEANMELDUNG)) {
         values.add(toString(m.getFehlversucheAnmeldung()));
      }
      if (columns.containsKey(LETZTERZUGRIFF)) {
         values.add(toString(m.getLetzterZugriff()));
      }
      values.add(toString(m.getID_Anwender()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Anwender
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
     * @param id_Anwender searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Anwender) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Anwender=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Anwender});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Anwender=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Anwender searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(AnwenderModelImpl m, String id_Anwender) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Anwender);
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
         values[idx++] = toString(id_Anwender);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Anwender IDs
     *

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from Anwender",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Anwender by a from parameters builded WHERE-clause
     *
     * @param id_Anwender searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Anwender) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Anwender=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Anwender});
   }

   /**
     * Changes the data of the given object in the table Anwender 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(AnwenderModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Anwender=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object AnwenderModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (AnwenderModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Gebiet returns a {@link Collection} of Anwender IDs
     *
     * @param id_Gebiet searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gebiet(String id_Gebiet) throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where ID_Gebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gebiet});
   }

   /**
     * Method retrieveIDsByName returns a {@link Collection} of Anwender IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where Name=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsLikeName returns a {@link Collection} of Anwender IDs
     *
     * @param name searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeName(String name) throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where Name like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{name});
   }

   /**
     * Method retrieveIDsByAnwendername returns a {@link Collection} of Anwender IDs
     *
     * @param anwendername searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByAnwendername(String anwendername) throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where Anwendername=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{anwendername});
   }

   /**
     * Method retrieveIDsLikeAnwendername returns a {@link Collection} of Anwender IDs
     *
     * @param anwendername searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeAnwendername(String anwendername)
      throws SQLException {

      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where Anwendername like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{anwendername});
   }

   /**
     * Method retrieveIDsByPasswordHash returns a {@link Collection} of Anwender IDs
     *
     * @param passwordHash searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByPasswordHash(String passwordHash) throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where PasswordHash=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{passwordHash});
   }

   /**
     * Method retrieveIDsLikePasswordHash returns a {@link Collection} of Anwender IDs
     *
     * @param passwordHash searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikePasswordHash(String passwordHash)
      throws SQLException {

      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where PasswordHash like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{passwordHash});
   }

   /**
     * Method retrieveIDsBySalt returns a {@link Collection} of Anwender IDs
     *
     * @param salt searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySalt(String salt) throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where Salt=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{salt});
   }

   /**
     * Method retrieveIDsLikeSalt returns a {@link Collection} of Anwender IDs
     *
     * @param salt searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeSalt(String salt) throws SQLException {
      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where Salt like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{salt});
   }

   /**
     * Method retrieveIDsByFehlversucheAnmeldung returns a {@link Collection} of Anwender IDs
     *
     * @param fehlversucheAnmeldung searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByFehlversucheAnmeldung(int fehlversucheAnmeldung)
      throws SQLException {

      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where FehlversucheAnmeldung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(fehlversucheAnmeldung)});
   }

   /**
     * Method retrieveIDsByLetzterZugriff returns a {@link Collection} of Anwender IDs
     *
     * @param letzterZugriff searching condition

     * @return a {@link Collection} of Anwender IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByLetzterZugriff(Timestamp letzterZugriff)
      throws SQLException {

      return retrieveIDs(
         "select ID_Anwender from " + TABLENAME + " where LetzterZugriff=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{letzterZugriff});
   }
}
