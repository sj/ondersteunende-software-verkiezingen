/*
 * BasicGruppeDBA
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
  * Implementation of the persistency layer for the entity Gruppe.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicGruppeDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(BasicGruppeDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGruppeDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "Gruppe.ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_WAHL = "ID_Wahl"; //$NON-NLS-1$
   public static final String ID_WAHL_QUAL = "Gruppe.ID_Wahl"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION = "ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION_QUAL = "Gruppe.ID_Listenkombination"; //$NON-NLS-1$
   public static final String SCHLUESSEL = "Schluessel"; //$NON-NLS-1$
   public static final String SCHLUESSEL_QUAL = "Gruppe.Schluessel"; //$NON-NLS-1$
   public static final String GRUPPENART = "Gruppenart"; //$NON-NLS-1$
   public static final String GRUPPENART_QUAL = "Gruppe.Gruppenart"; //$NON-NLS-1$
   public static final String NAMELANG = "NameLang"; //$NON-NLS-1$
   public static final String NAMELANG_QUAL = "Gruppe.NameLang"; //$NON-NLS-1$
   public static final String NAMEKURZ = "NameKurz"; //$NON-NLS-1$
   public static final String NAMEKURZ_QUAL = "Gruppe.NameKurz"; //$NON-NLS-1$
   public static final String KAUTIONGESTELLT = "KautionGestellt"; //$NON-NLS-1$
   public static final String KAUTIONGESTELLT_QUAL = "Gruppe.KautionGestellt"; //$NON-NLS-1$
   public static final String FARBE = "Farbe"; //$NON-NLS-1$
   public static final String FARBE_QUAL = "Gruppe.Farbe"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_GRUPPE,ID_WAHL,ID_LISTENKOMBINATION,SCHLUESSEL,GRUPPENART,NAMELANG,NAMEKURZ,KAUTIONGESTELLT,FARBE};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>GruppeModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, GruppeModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_GRUPPE.toUpperCase())) != null) {
         m._id_Gruppe = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_WAHL.toUpperCase())) != null) {
         m._id_Wahl = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTENKOMBINATION.toUpperCase())) != null) {
         m._id_Listenkombination = r.getString(idx.intValue());
      }
      if ((idx = columns.get(SCHLUESSEL.toUpperCase())) != null) {
         m._schluessel = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(GRUPPENART.toUpperCase())) != null) {
         m._gruppenart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(NAMELANG.toUpperCase())) != null) {
         m._nameLang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NAMEKURZ.toUpperCase())) != null) {
         m._nameKurz = r.getString(idx.intValue());
      }
      if ((idx = columns.get(KAUTIONGESTELLT.toUpperCase())) != null) {
         m._kautionGestellt = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(FARBE.toUpperCase())) != null) {
         m._farbe = r.getString(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>GruppeModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(GruppeModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_WAHL.toUpperCase())) {
         p.setString(idx++, m._id_Wahl);
      }
      if (columns.containsKey(ID_LISTENKOMBINATION.toUpperCase())) {
         p.setString(idx++, m._id_Listenkombination);
      }
      if (columns.containsKey(SCHLUESSEL.toUpperCase())) {
         p.setInt(idx++, m._schluessel);
      }
      if (columns.containsKey(GRUPPENART.toUpperCase())) {
         p.setInt(idx++, m._gruppenart);
      }
      if (columns.containsKey(NAMELANG.toUpperCase())) {
         p.setString(idx++, m._nameLang);
      }
      if (columns.containsKey(NAMEKURZ.toUpperCase())) {
         p.setString(idx++, m._nameKurz);
      }
      if (columns.containsKey(KAUTIONGESTELLT.toUpperCase())) {
         p.setBoolean(idx++, m._kautionGestellt);
      }
      if (columns.containsKey(FARBE.toUpperCase())) {
         p.setString(idx++, m._farbe);
      }
      p.setString(idx++, m._id_Gruppe);
   }

   /**
     * Inserts or changes the data of the given object in the table Gruppe (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (GruppeModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>GruppeModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(GruppeModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_WAHL)) {
         values.add(toString(m.getID_Wahl()));
      }
      if (columns.containsKey(ID_LISTENKOMBINATION)) {
         values.add(toString(m.getID_Listenkombination()));
      }
      if (columns.containsKey(SCHLUESSEL)) {
         values.add(toString(m.getSchluessel()));
      }
      if (columns.containsKey(GRUPPENART)) {
         values.add(toString(m.getGruppenart()));
      }
      if (columns.containsKey(NAMELANG)) {
         values.add(toString(m.getNameLang()));
      }
      if (columns.containsKey(NAMEKURZ)) {
         values.add(toString(m.getNameKurz()));
      }
      if (columns.containsKey(KAUTIONGESTELLT)) {
         values.add(toString(m.isKautionGestellt()));
      }
      if (columns.containsKey(FARBE)) {
         values.add(toString(m.getFarbe()));
      }
      values.add(toString(m.getID_Gruppe()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Gruppe
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
     * @param id_Gruppe searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Gruppe) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gruppe});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Gruppe=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Gruppe searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(GruppeModelImpl m, String id_Gruppe) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Gruppe);
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
         values[idx++] = toString(id_Gruppe);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Gruppe IDs
     *

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from Gruppe",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Gruppe by a from parameters builded WHERE-clause
     *
     * @param id_Gruppe searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Gruppe) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Gruppe=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gruppe});
   }

   /**
     * Changes the data of the given object in the table Gruppe 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(GruppeModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Gruppe=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object GruppeModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (GruppeModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Listenkombination returns a {@link Collection} of Gruppe IDs
     *
     * @param id_Listenkombination searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkombination(String id_Listenkombination)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where ID_Listenkombination=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkombination});
   }

   /**
     * Method retrieveIDsByID_Wahl returns a {@link Collection} of Gruppe IDs
     *
     * @param id_Wahl searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Wahl(String id_Wahl) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where ID_Wahl=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Wahl});
   }

   /**
     * Method retrieveIDsBySchluessel returns a {@link Collection} of Gruppe IDs
     *
     * @param schluessel searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySchluessel(int schluessel) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where Schluessel=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(schluessel)});
   }

   /**
     * Method retrieveIDsByGruppenart returns a {@link Collection} of Gruppe IDs
     *
     * @param gruppenart searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGruppenart(int gruppenart) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where Gruppenart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(gruppenart)});
   }

   /**
     * Method retrieveIDsByNameLang returns a {@link Collection} of Gruppe IDs
     *
     * @param nameLang searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNameLang(String nameLang) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where NameLang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{nameLang});
   }

   /**
     * Method retrieveIDsLikeNameLang returns a {@link Collection} of Gruppe IDs
     *
     * @param nameLang searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeNameLang(String nameLang) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where NameLang like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{nameLang});
   }

   /**
     * Method retrieveIDsByNameKurz returns a {@link Collection} of Gruppe IDs
     *
     * @param nameKurz searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNameKurz(String nameKurz) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where NameKurz=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{nameKurz});
   }

   /**
     * Method retrieveIDsLikeNameKurz returns a {@link Collection} of Gruppe IDs
     *
     * @param nameKurz searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeNameKurz(String nameKurz) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where NameKurz like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{nameKurz});
   }

   /**
     * Method retrieveIDsByKautionGestellt returns a {@link Collection} of Gruppe IDs
     *
     * @param kautionGestellt searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKautionGestellt(boolean kautionGestellt)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where KautionGestellt=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(kautionGestellt)});
   }

   /**
     * Method retrieveIDsByFarbe returns a {@link Collection} of Gruppe IDs
     *
     * @param farbe searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByFarbe(String farbe) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where Farbe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{farbe});
   }

   /**
     * Method retrieveIDsLikeFarbe returns a {@link Collection} of Gruppe IDs
     *
     * @param farbe searching condition

     * @return a {@link Collection} of Gruppe IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsLikeFarbe(String farbe) throws SQLException {
      return retrieveIDs(
         "select ID_Gruppe from " + TABLENAME + " where Farbe like ?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{farbe});
   }
}
