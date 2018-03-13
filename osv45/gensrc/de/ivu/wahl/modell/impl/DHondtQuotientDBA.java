/*
 * DHondtQuotientDBA
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
  * Implementation of the persistency layer for the entity DHondtQuotient.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class DHondtQuotientDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(DHondtQuotientDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(DHondtQuotientDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "DHondtQuotient"; //$NON-NLS-1$
   public static final String ID_DHONDTQUOTIENT = "ID_DHondtQuotient"; //$NON-NLS-1$
   public static final String ID_DHONDTQUOTIENT_QUAL = "DHondtQuotient.ID_DHondtQuotient"; //$NON-NLS-1$
   public static final String ID_RESTSITZVERTEILUNG = "ID_Restsitzverteilung"; //$NON-NLS-1$
   public static final String ID_RESTSITZVERTEILUNG_QUAL = "DHondtQuotient.ID_Restsitzverteilung"; //$NON-NLS-1$
   public static final String LAUF = "Lauf"; //$NON-NLS-1$
   public static final String LAUF_QUAL = "DHondtQuotient.Lauf"; //$NON-NLS-1$
   public static final String ZAEHLER = "Zaehler"; //$NON-NLS-1$
   public static final String ZAEHLER_QUAL = "DHondtQuotient.Zaehler"; //$NON-NLS-1$
   public static final String NENNER = "Nenner"; //$NON-NLS-1$
   public static final String NENNER_QUAL = "DHondtQuotient.Nenner"; //$NON-NLS-1$
   public static final String SITZAUSRESTANTEIL = "SitzAusRestanteil"; //$NON-NLS-1$
   public static final String SITZAUSRESTANTEIL_QUAL = "DHondtQuotient.SitzAusRestanteil"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_DHONDTQUOTIENT,ID_RESTSITZVERTEILUNG,LAUF,ZAEHLER,NENNER,SITZAUSRESTANTEIL};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>DHondtQuotientModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, DHondtQuotientModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_DHONDTQUOTIENT.toUpperCase())) != null) {
         m._iD_DHondtQuotient = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_RESTSITZVERTEILUNG.toUpperCase())) != null) {
         m._id_Restsitzverteilung = r.getString(idx.intValue());
      }
      if ((idx = columns.get(LAUF.toUpperCase())) != null) {
         m._lauf = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ZAEHLER.toUpperCase())) != null) {
         m._zaehler = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(NENNER.toUpperCase())) != null) {
         m._nenner = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(SITZAUSRESTANTEIL.toUpperCase())) != null) {
         m._sitzAusRestanteil = r.getBoolean(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>DHondtQuotientModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(DHondtQuotientModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_RESTSITZVERTEILUNG.toUpperCase())) {
         p.setString(idx++, m._id_Restsitzverteilung);
      }
      if (columns.containsKey(LAUF.toUpperCase())) {
         p.setInt(idx++, m._lauf);
      }
      if (columns.containsKey(ZAEHLER.toUpperCase())) {
         p.setInt(idx++, m._zaehler);
      }
      if (columns.containsKey(NENNER.toUpperCase())) {
         p.setInt(idx++, m._nenner);
      }
      if (columns.containsKey(SITZAUSRESTANTEIL.toUpperCase())) {
         p.setBoolean(idx++, m._sitzAusRestanteil);
      }
      p.setString(idx++, m._iD_DHondtQuotient);
   }

   /**
     * Inserts or changes the data of the given object in the table DHondtQuotient (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (DHondtQuotientModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>DHondtQuotientModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(DHondtQuotientModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_RESTSITZVERTEILUNG)) {
         values.add(toString(m.getID_Restsitzverteilung()));
      }
      if (columns.containsKey(LAUF)) {
         values.add(toString(m.getLauf()));
      }
      if (columns.containsKey(ZAEHLER)) {
         values.add(toString(m.getZaehler()));
      }
      if (columns.containsKey(NENNER)) {
         values.add(toString(m.getNenner()));
      }
      if (columns.containsKey(SITZAUSRESTANTEIL)) {
         values.add(toString(m.isSitzAusRestanteil()));
      }
      values.add(toString(m.getID_DHondtQuotient()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table DHondtQuotient
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
     * @param iD_DHondtQuotient searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String iD_DHondtQuotient) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_DHondtQuotient=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{iD_DHondtQuotient});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_DHondtQuotient=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param iD_DHondtQuotient searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(DHondtQuotientModelImpl m, String iD_DHondtQuotient)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, iD_DHondtQuotient);
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
         values[idx++] = toString(iD_DHondtQuotient);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of DHondtQuotient IDs
     *

     * @return a {@link Collection} of DHondtQuotient IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_DHondtQuotient from DHondtQuotient",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table DHondtQuotient by a from parameters builded WHERE-clause
     *
     * @param iD_DHondtQuotient searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String iD_DHondtQuotient) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_DHondtQuotient=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{iD_DHondtQuotient});
   }

   /**
     * Changes the data of the given object in the table DHondtQuotient 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(DHondtQuotientModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_DHondtQuotient=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object DHondtQuotientModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (DHondtQuotientModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Restsitzverteilung returns a {@link Collection} of DHondtQuotient IDs
     *
     * @param id_Restsitzverteilung searching condition

     * @return a {@link Collection} of DHondtQuotient IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Restsitzverteilung(String id_Restsitzverteilung)
      throws SQLException {

      return retrieveIDs(
         "select ID_DHondtQuotient from " + TABLENAME + " where ID_Restsitzverteilung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Restsitzverteilung});
   }

   /**
     * Method retrieveIDsByLauf returns a {@link Collection} of DHondtQuotient IDs
     *
     * @param lauf searching condition

     * @return a {@link Collection} of DHondtQuotient IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByLauf(int lauf) throws SQLException {
      return retrieveIDs(
         "select ID_DHondtQuotient from " + TABLENAME + " where Lauf=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(lauf)});
   }

   /**
     * Method retrieveIDsByZaehler returns a {@link Collection} of DHondtQuotient IDs
     *
     * @param zaehler searching condition

     * @return a {@link Collection} of DHondtQuotient IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByZaehler(int zaehler) throws SQLException {
      return retrieveIDs(
         "select ID_DHondtQuotient from " + TABLENAME + " where Zaehler=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(zaehler)});
   }

   /**
     * Method retrieveIDsByNenner returns a {@link Collection} of DHondtQuotient IDs
     *
     * @param nenner searching condition

     * @return a {@link Collection} of DHondtQuotient IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNenner(int nenner) throws SQLException {
      return retrieveIDs(
         "select ID_DHondtQuotient from " + TABLENAME + " where Nenner=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(nenner)});
   }

   /**
     * Method retrieveIDsBySitzAusRestanteil returns a {@link Collection} of DHondtQuotient IDs
     *
     * @param sitzAusRestanteil searching condition

     * @return a {@link Collection} of DHondtQuotient IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySitzAusRestanteil(boolean sitzAusRestanteil)
      throws SQLException {

      return retrieveIDs(
         "select ID_DHondtQuotient from " + TABLENAME + " where SitzAusRestanteil=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(sitzAusRestanteil)});
   }
}
