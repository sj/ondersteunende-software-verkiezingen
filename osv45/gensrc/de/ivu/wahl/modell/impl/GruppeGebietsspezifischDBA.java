/*
 * GruppeGebietsspezifischDBA
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
  * Implementation of the persistency layer for the entity GruppeGebietsspezifisch.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class GruppeGebietsspezifischDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(GruppeGebietsspezifischDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(GruppeGebietsspezifischDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "GruppeGebietsspezifisch"; //$NON-NLS-1$
   public static final String ID_GRUPPEGEBIETSSPEZIFISCH = "ID_GruppeGebietsspezifisch"; //$NON-NLS-1$
   public static final String ID_GRUPPEGEBIETSSPEZIFISCH_QUAL = "GruppeGebietsspezifisch.ID_GruppeGebietsspezifisch"; //$NON-NLS-1$
   public static final String ID_UEBERGEORDNETEGG = "ID_UebergeordneteGG"; //$NON-NLS-1$
   public static final String ID_UEBERGEORDNETEGG_QUAL = "GruppeGebietsspezifisch.ID_UebergeordneteGG"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "GruppeGebietsspezifisch.ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GEBIET = "ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET_QUAL = "GruppeGebietsspezifisch.ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "GruppeGebietsspezifisch.ID_Liste"; //$NON-NLS-1$
   public static final String POSITION = "Position"; //$NON-NLS-1$
   public static final String POSITION_QUAL = "GruppeGebietsspezifisch.Position"; //$NON-NLS-1$
   public static final String LISTEZUGELASSEN = "ListeZugelassen"; //$NON-NLS-1$
   public static final String LISTEZUGELASSEN_QUAL = "GruppeGebietsspezifisch.ListeZugelassen"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_GRUPPEGEBIETSSPEZIFISCH,ID_UEBERGEORDNETEGG,ID_GRUPPE,ID_GEBIET,ID_LISTE,POSITION,LISTEZUGELASSEN};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>GruppeGebietsspezifischModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, GruppeGebietsspezifischModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_GRUPPEGEBIETSSPEZIFISCH.toUpperCase())) != null) {
         m._id_GruppeGebietsspezifisch = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_UEBERGEORDNETEGG.toUpperCase())) != null) {
         m._id_UebergeordneteGG = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GRUPPE.toUpperCase())) != null) {
         m._id_Gruppe = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GEBIET.toUpperCase())) != null) {
         m._id_Gebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTE.toUpperCase())) != null) {
         m._id_Liste = r.getString(idx.intValue());
      }
      if ((idx = columns.get(POSITION.toUpperCase())) != null) {
         m._position = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(LISTEZUGELASSEN.toUpperCase())) != null) {
         m._listeZugelassen = r.getBoolean(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>GruppeGebietsspezifischModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(GruppeGebietsspezifischModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_UEBERGEORDNETEGG.toUpperCase())) {
         p.setString(idx++, m._id_UebergeordneteGG);
      }
      if (columns.containsKey(ID_GRUPPE.toUpperCase())) {
         p.setString(idx++, m._id_Gruppe);
      }
      if (columns.containsKey(ID_GEBIET.toUpperCase())) {
         p.setString(idx++, m._id_Gebiet);
      }
      if (columns.containsKey(ID_LISTE.toUpperCase())) {
         p.setString(idx++, m._id_Liste);
      }
      if (columns.containsKey(POSITION.toUpperCase())) {
         p.setInt(idx++, m._position);
      }
      if (columns.containsKey(LISTEZUGELASSEN.toUpperCase())) {
         p.setBoolean(idx++, m._listeZugelassen);
      }
      p.setString(idx++, m._id_GruppeGebietsspezifisch);
   }

   /**
     * Inserts or changes the data of the given object in the table GruppeGebietsspezifisch (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (GruppeGebietsspezifischModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>GruppeGebietsspezifischModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(GruppeGebietsspezifischModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_UEBERGEORDNETEGG)) {
         values.add(toString(m.getID_UebergeordneteGG()));
      }
      if (columns.containsKey(ID_GRUPPE)) {
         values.add(toString(m.getID_Gruppe()));
      }
      if (columns.containsKey(ID_GEBIET)) {
         values.add(toString(m.getID_Gebiet()));
      }
      if (columns.containsKey(ID_LISTE)) {
         values.add(toString(m.getID_Liste()));
      }
      if (columns.containsKey(POSITION)) {
         values.add(toString(m.getPosition()));
      }
      if (columns.containsKey(LISTEZUGELASSEN)) {
         values.add(toString(m.isListeZugelassen()));
      }
      values.add(toString(m.getID_GruppeGebietsspezifisch()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table GruppeGebietsspezifisch
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
     * @param id_GruppeGebietsspezifisch searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_GruppeGebietsspezifisch) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_GruppeGebietsspezifisch=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_GruppeGebietsspezifisch});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_GruppeGebietsspezifisch=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_GruppeGebietsspezifisch searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(GruppeGebietsspezifischModelImpl m, String id_GruppeGebietsspezifisch)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_GruppeGebietsspezifisch);
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
         values[idx++] = toString(id_GruppeGebietsspezifisch);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of GruppeGebietsspezifisch IDs
     *

     * @return a {@link Collection} of GruppeGebietsspezifisch IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_GruppeGebietsspezifisch from GruppeGebietsspezifisch",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table GruppeGebietsspezifisch by a from parameters builded WHERE-clause
     *
     * @param id_GruppeGebietsspezifisch searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_GruppeGebietsspezifisch) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_GruppeGebietsspezifisch=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_GruppeGebietsspezifisch});
   }

   /**
     * Changes the data of the given object in the table GruppeGebietsspezifisch 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(GruppeGebietsspezifischModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_GruppeGebietsspezifisch=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object GruppeGebietsspezifischModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (GruppeGebietsspezifischModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Liste returns a {@link Collection} of GruppeGebietsspezifisch IDs
     *
     * @param id_Liste searching condition

     * @return a {@link Collection} of GruppeGebietsspezifisch IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Liste(String id_Liste) throws SQLException {
      return retrieveIDs(
         "select ID_GruppeGebietsspezifisch from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Liste});
   }

   /**
     * Method retrieveIDsByID_UebergeordneteGG returns a {@link Collection} of GruppeGebietsspezifisch IDs
     *
     * @param id_UebergeordneteGG searching condition

     * @return a {@link Collection} of GruppeGebietsspezifisch IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_UebergeordneteGG(String id_UebergeordneteGG)
      throws SQLException {

      return retrieveIDs(
         "select ID_GruppeGebietsspezifisch from " + TABLENAME + " where ID_UebergeordneteGG=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_UebergeordneteGG});
   }

   /**
     * Method retrieveIDsByID_Gebiet returns a {@link Collection} of GruppeGebietsspezifisch IDs
     *
     * @param id_Gebiet searching condition

     * @return a {@link Collection} of GruppeGebietsspezifisch IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gebiet(String id_Gebiet) throws SQLException {
      return retrieveIDs(
         "select ID_GruppeGebietsspezifisch from " + TABLENAME + " where ID_Gebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gebiet});
   }

   /**
     * Method retrieveIDsByID_Gruppe returns a {@link Collection} of GruppeGebietsspezifisch IDs
     *
     * @param id_Gruppe searching condition

     * @return a {@link Collection} of GruppeGebietsspezifisch IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gruppe(String id_Gruppe) throws SQLException {
      return retrieveIDs(
         "select ID_GruppeGebietsspezifisch from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gruppe});
   }

   /**
     * Method retrieveIDsByPosition returns a {@link Collection} of GruppeGebietsspezifisch IDs
     *
     * @param position searching condition

     * @return a {@link Collection} of GruppeGebietsspezifisch IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByPosition(int position) throws SQLException {
      return retrieveIDs(
         "select ID_GruppeGebietsspezifisch from " + TABLENAME + " where Position=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(position)});
   }

   /**
     * Method retrieveIDsByListeZugelassen returns a {@link Collection} of GruppeGebietsspezifisch IDs
     *
     * @param listeZugelassen searching condition

     * @return a {@link Collection} of GruppeGebietsspezifisch IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByListeZugelassen(boolean listeZugelassen)
      throws SQLException {

      return retrieveIDs(
         "select ID_GruppeGebietsspezifisch from " + TABLENAME + " where ListeZugelassen=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(listeZugelassen)});
   }
}
