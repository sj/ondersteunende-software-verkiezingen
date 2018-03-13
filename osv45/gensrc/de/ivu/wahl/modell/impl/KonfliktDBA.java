/*
 * KonfliktDBA
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
  * Implementation of the persistency layer for the entity Konflikt.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class KonfliktDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(KonfliktDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(KonfliktDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Konflikt"; //$NON-NLS-1$
   public static final String ID_KONFLIKT = "ID_Konflikt"; //$NON-NLS-1$
   public static final String ID_KONFLIKT_QUAL = "Konflikt.ID_Konflikt"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Konflikt.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_LOSALTERNATIVE = "ID_LosAlternative"; //$NON-NLS-1$
   public static final String ID_LOSALTERNATIVE_QUAL = "Konflikt.ID_LosAlternative"; //$NON-NLS-1$
   public static final String NUMMER = "Nummer"; //$NON-NLS-1$
   public static final String NUMMER_QUAL = "Konflikt.Nummer"; //$NON-NLS-1$
   public static final String KONFLIKTART = "Konfliktart"; //$NON-NLS-1$
   public static final String KONFLIKTART_QUAL = "Konflikt.Konfliktart"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_KONFLIKT,ID_ERGEBNISEINGANG,ID_LOSALTERNATIVE,NUMMER,KONFLIKTART};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>KonfliktModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, KonfliktModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_KONFLIKT.toUpperCase())) != null) {
         m._id_Konflikt = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LOSALTERNATIVE.toUpperCase())) != null) {
         m._id_LosAlternative = r.getString(idx.intValue());
      }
      if ((idx = columns.get(NUMMER.toUpperCase())) != null) {
         m._nummer = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(KONFLIKTART.toUpperCase())) != null) {
         m._konfliktart = r.getInt(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>KonfliktModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(KonfliktModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(ID_LOSALTERNATIVE.toUpperCase())) {
         p.setString(idx++, m._id_LosAlternative);
      }
      if (columns.containsKey(NUMMER.toUpperCase())) {
         p.setInt(idx++, m._nummer);
      }
      if (columns.containsKey(KONFLIKTART.toUpperCase())) {
         p.setInt(idx++, m._konfliktart);
      }
      p.setString(idx++, m._id_Konflikt);
   }

   /**
     * Inserts or changes the data of the given object in the table Konflikt (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (KonfliktModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>KonfliktModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(KonfliktModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(ID_LOSALTERNATIVE)) {
         values.add(toString(m.getID_LosAlternative()));
      }
      if (columns.containsKey(NUMMER)) {
         values.add(toString(m.getNummer()));
      }
      if (columns.containsKey(KONFLIKTART)) {
         values.add(toString(m.getKonfliktart()));
      }
      values.add(toString(m.getID_Konflikt()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Konflikt
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
     * @param id_Konflikt searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Konflikt) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Konflikt=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Konflikt});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Konflikt=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Konflikt searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(KonfliktModelImpl m, String id_Konflikt) throws SQLException {
      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Konflikt);
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
         values[idx++] = toString(id_Konflikt);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Konflikt IDs
     *

     * @return a {@link Collection} of Konflikt IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Konflikt from Konflikt",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Konflikt by a from parameters builded WHERE-clause
     *
     * @param id_Konflikt searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Konflikt) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Konflikt=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Konflikt});
   }

   /**
     * Changes the data of the given object in the table Konflikt 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(KonfliktModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Konflikt=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object KonfliktModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (KonfliktModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_LosAlternative returns a {@link Collection} of Konflikt IDs
     *
     * @param id_LosAlternative searching condition

     * @return a {@link Collection} of Konflikt IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_LosAlternative(String id_LosAlternative)
      throws SQLException {

      return retrieveIDs(
         "select ID_Konflikt from " + TABLENAME + " where ID_LosAlternative=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_LosAlternative});
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of Konflikt IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of Konflikt IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Konflikt from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByNummer returns a {@link Collection} of Konflikt IDs
     *
     * @param nummer searching condition

     * @return a {@link Collection} of Konflikt IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNummer(int nummer) throws SQLException {
      return retrieveIDs(
         "select ID_Konflikt from " + TABLENAME + " where Nummer=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(nummer)});
   }

   /**
     * Method retrieveIDsByKonfliktart returns a {@link Collection} of Konflikt IDs
     *
     * @param konfliktart searching condition

     * @return a {@link Collection} of Konflikt IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKonfliktart(int konfliktart) throws SQLException {
      return retrieveIDs(
         "select ID_Konflikt from " + TABLENAME + " where Konfliktart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(konfliktart)});
   }
}
