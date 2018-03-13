/*
 * ListenkandidaturErgebnisDBA
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
  * Implementation of the persistency layer for the entity ListenkandidaturErgebnis.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public class ListenkandidaturErgebnisDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(ListenkandidaturErgebnisDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(ListenkandidaturErgebnisDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "ListenkandidaturErgebnis"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATURERGEBNIS = "ID_ListenkandidaturErgebnis"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATURERGEBNIS_QUAL = "ListenkandidaturErgebnis.ID_ListenkandidaturErgebnis"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATUR = "ID_Listenkandidatur"; //$NON-NLS-1$
   public static final String ID_LISTENKANDIDATUR_QUAL = "ListenkandidaturErgebnis.ID_Listenkandidatur"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "ListenkandidaturErgebnis.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String LISTENPLATZ = "Listenplatz"; //$NON-NLS-1$
   public static final String LISTENPLATZ_QUAL = "ListenkandidaturErgebnis.Listenplatz"; //$NON-NLS-1$
   public static final String GEWAEHLT = "Gewaehlt"; //$NON-NLS-1$
   public static final String GEWAEHLT_QUAL = "ListenkandidaturErgebnis.Gewaehlt"; //$NON-NLS-1$
   public static final String GEWAEHLTINGRUPPE = "GewaehltInGruppe"; //$NON-NLS-1$
   public static final String GEWAEHLTINGRUPPE_QUAL = "ListenkandidaturErgebnis.GewaehltInGruppe"; //$NON-NLS-1$
   public static final String BEVORZUGTGEWAEHLT = "BevorzugtGewaehlt"; //$NON-NLS-1$
   public static final String BEVORZUGTGEWAEHLT_QUAL = "ListenkandidaturErgebnis.BevorzugtGewaehlt"; //$NON-NLS-1$
   public static final String LOSTEILNEHMER = "Losteilnehmer"; //$NON-NLS-1$
   public static final String LOSTEILNEHMER_QUAL = "ListenkandidaturErgebnis.Losteilnehmer"; //$NON-NLS-1$
   public static final String LOSGEWINNER = "Losgewinner"; //$NON-NLS-1$
   public static final String LOSGEWINNER_QUAL = "ListenkandidaturErgebnis.Losgewinner"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_LISTENKANDIDATURERGEBNIS,ID_LISTENKANDIDATUR,ID_ERGEBNISEINGANG,LISTENPLATZ,GEWAEHLT,GEWAEHLTINGRUPPE,BEVORZUGTGEWAEHLT,LOSTEILNEHMER,LOSGEWINNER};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>ListenkandidaturErgebnisModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, ListenkandidaturErgebnisModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_LISTENKANDIDATURERGEBNIS.toUpperCase())) != null) {
         m._id_ListenkandidaturErgebnis = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_LISTENKANDIDATUR.toUpperCase())) != null) {
         m._id_Listenkandidatur = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(LISTENPLATZ.toUpperCase())) != null) {
         m._listenplatz = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(GEWAEHLT.toUpperCase())) != null) {
         m._gewaehlt = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(GEWAEHLTINGRUPPE.toUpperCase())) != null) {
         m._gewaehltInGruppe = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(BEVORZUGTGEWAEHLT.toUpperCase())) != null) {
         m._bevorzugtGewaehlt = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(LOSTEILNEHMER.toUpperCase())) != null) {
         m._losteilnehmer = r.getBoolean(idx.intValue());
      }
      if ((idx = columns.get(LOSGEWINNER.toUpperCase())) != null) {
         m._losgewinner = r.getBoolean(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>ListenkandidaturErgebnisModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(ListenkandidaturErgebnisModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_LISTENKANDIDATUR.toUpperCase())) {
         p.setString(idx++, m._id_Listenkandidatur);
      }
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(LISTENPLATZ.toUpperCase())) {
         p.setInt(idx++, m._listenplatz);
      }
      if (columns.containsKey(GEWAEHLT.toUpperCase())) {
         p.setBoolean(idx++, m._gewaehlt);
      }
      if (columns.containsKey(GEWAEHLTINGRUPPE.toUpperCase())) {
         p.setBoolean(idx++, m._gewaehltInGruppe);
      }
      if (columns.containsKey(BEVORZUGTGEWAEHLT.toUpperCase())) {
         p.setBoolean(idx++, m._bevorzugtGewaehlt);
      }
      if (columns.containsKey(LOSTEILNEHMER.toUpperCase())) {
         p.setBoolean(idx++, m._losteilnehmer);
      }
      if (columns.containsKey(LOSGEWINNER.toUpperCase())) {
         p.setBoolean(idx++, m._losgewinner);
      }
      p.setString(idx++, m._id_ListenkandidaturErgebnis);
   }

   /**
     * Inserts or changes the data of the given object in the table ListenkandidaturErgebnis (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (ListenkandidaturErgebnisModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>ListenkandidaturErgebnisModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(ListenkandidaturErgebnisModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_LISTENKANDIDATUR)) {
         values.add(toString(m.getID_Listenkandidatur()));
      }
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(LISTENPLATZ)) {
         values.add(toString(m.getListenplatz()));
      }
      if (columns.containsKey(GEWAEHLT)) {
         values.add(toString(m.isGewaehlt()));
      }
      if (columns.containsKey(GEWAEHLTINGRUPPE)) {
         values.add(toString(m.isGewaehltInGruppe()));
      }
      if (columns.containsKey(BEVORZUGTGEWAEHLT)) {
         values.add(toString(m.isBevorzugtGewaehlt()));
      }
      if (columns.containsKey(LOSTEILNEHMER)) {
         values.add(toString(m.isLosteilnehmer()));
      }
      if (columns.containsKey(LOSGEWINNER)) {
         values.add(toString(m.isLosgewinner()));
      }
      values.add(toString(m.getID_ListenkandidaturErgebnis()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table ListenkandidaturErgebnis
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
     * @param id_ListenkandidaturErgebnis searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_ListenkandidaturErgebnis) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_ListenkandidaturErgebnis=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_ListenkandidaturErgebnis});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_ListenkandidaturErgebnis=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_ListenkandidaturErgebnis searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(ListenkandidaturErgebnisModelImpl m, String id_ListenkandidaturErgebnis)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_ListenkandidaturErgebnis);
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
         values[idx++] = toString(id_ListenkandidaturErgebnis);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from ListenkandidaturErgebnis",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table ListenkandidaturErgebnis by a from parameters builded WHERE-clause
     *
     * @param id_ListenkandidaturErgebnis searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_ListenkandidaturErgebnis) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_ListenkandidaturErgebnis=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_ListenkandidaturErgebnis});
   }

   /**
     * Changes the data of the given object in the table ListenkandidaturErgebnis 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(ListenkandidaturErgebnisModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_ListenkandidaturErgebnis=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object ListenkandidaturErgebnisModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (ListenkandidaturErgebnisModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Listenkandidatur returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param id_Listenkandidatur searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkandidatur(String id_Listenkandidatur)
      throws SQLException {

      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where ID_Listenkandidatur=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkandidatur});
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByListenplatz returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param listenplatz searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByListenplatz(int listenplatz) throws SQLException {
      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where Listenplatz=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(listenplatz)});
   }

   /**
     * Method retrieveIDsByGewaehlt returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param gewaehlt searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGewaehlt(boolean gewaehlt) throws SQLException {
      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where Gewaehlt=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(gewaehlt)});
   }

   /**
     * Method retrieveIDsByGewaehltInGruppe returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param gewaehltInGruppe searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByGewaehltInGruppe(boolean gewaehltInGruppe)
      throws SQLException {

      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where GewaehltInGruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(gewaehltInGruppe)});
   }

   /**
     * Method retrieveIDsByBevorzugtGewaehlt returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param bevorzugtGewaehlt searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByBevorzugtGewaehlt(boolean bevorzugtGewaehlt)
      throws SQLException {

      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where BevorzugtGewaehlt=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(bevorzugtGewaehlt)});
   }

   /**
     * Method retrieveIDsByLosteilnehmer returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param losteilnehmer searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByLosteilnehmer(boolean losteilnehmer)
      throws SQLException {

      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where Losteilnehmer=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(losteilnehmer)});
   }

   /**
     * Method retrieveIDsByLosgewinner returns a {@link Collection} of ListenkandidaturErgebnis IDs
     *
     * @param losgewinner searching condition

     * @return a {@link Collection} of ListenkandidaturErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByLosgewinner(boolean losgewinner) throws SQLException {
      return retrieveIDs(
         "select ID_ListenkandidaturErgebnis from " + TABLENAME + " where Losgewinner=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(losgewinner)});
   }
}
