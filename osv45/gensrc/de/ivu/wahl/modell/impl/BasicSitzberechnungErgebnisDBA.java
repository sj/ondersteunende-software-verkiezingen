/*
 * BasicSitzberechnungErgebnisDBA
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
  * Implementation of the persistency layer for the entity SitzberechnungErgebnis.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicSitzberechnungErgebnisDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(BasicSitzberechnungErgebnisDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicSitzberechnungErgebnisDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "SitzberechnungErgebnis"; //$NON-NLS-1$
   public static final String ID_SITZBERECHNUNGERGEBNIS = "ID_SitzberechnungErgebnis"; //$NON-NLS-1$
   public static final String ID_SITZBERECHNUNGERGEBNIS_QUAL = "SitzberechnungErgebnis.ID_SitzberechnungErgebnis"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "SitzberechnungErgebnis.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_LISTE = "ID_Liste"; //$NON-NLS-1$
   public static final String ID_LISTE_QUAL = "SitzberechnungErgebnis.ID_Liste"; //$NON-NLS-1$
   public static final String ID_GRUPPE = "ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_GRUPPE_QUAL = "SitzberechnungErgebnis.ID_Gruppe"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION = "ID_Listenkombination"; //$NON-NLS-1$
   public static final String ID_LISTENKOMBINATION_QUAL = "SitzberechnungErgebnis.ID_Listenkombination"; //$NON-NLS-1$
   public static final String VERTEILUNG = "Verteilung"; //$NON-NLS-1$
   public static final String VERTEILUNG_QUAL = "SitzberechnungErgebnis.Verteilung"; //$NON-NLS-1$
   public static final String SCHRITTNUMMER = "Schrittnummer"; //$NON-NLS-1$
   public static final String SCHRITTNUMMER_QUAL = "SitzberechnungErgebnis.Schrittnummer"; //$NON-NLS-1$
   public static final String SCHRITTTYP = "Schritttyp"; //$NON-NLS-1$
   public static final String SCHRITTTYP_QUAL = "SitzberechnungErgebnis.Schritttyp"; //$NON-NLS-1$
   public static final String SITZE = "Sitze"; //$NON-NLS-1$
   public static final String SITZE_QUAL = "SitzberechnungErgebnis.Sitze"; //$NON-NLS-1$
   public static final String ZAEHLER = "Zaehler"; //$NON-NLS-1$
   public static final String ZAEHLER_QUAL = "SitzberechnungErgebnis.Zaehler"; //$NON-NLS-1$
   public static final String NENNER = "Nenner"; //$NON-NLS-1$
   public static final String NENNER_QUAL = "SitzberechnungErgebnis.Nenner"; //$NON-NLS-1$
   public static final String ZAEHLERVOMNENNER = "ZaehlerVomNenner"; //$NON-NLS-1$
   public static final String ZAEHLERVOMNENNER_QUAL = "SitzberechnungErgebnis.ZaehlerVomNenner"; //$NON-NLS-1$
   public static final String NENNERVOMNENNER = "NennerVomNenner"; //$NON-NLS-1$
   public static final String NENNERVOMNENNER_QUAL = "SitzberechnungErgebnis.NennerVomNenner"; //$NON-NLS-1$
   public static final String ZAEHLERVOMREST = "ZaehlerVomRest"; //$NON-NLS-1$
   public static final String ZAEHLERVOMREST_QUAL = "SitzberechnungErgebnis.ZaehlerVomRest"; //$NON-NLS-1$
   public static final String NENNERVOMREST = "NennerVomRest"; //$NON-NLS-1$
   public static final String NENNERVOMREST_QUAL = "SitzberechnungErgebnis.NennerVomRest"; //$NON-NLS-1$
   public static final String LOSENTSCHEID = "Losentscheid"; //$NON-NLS-1$
   public static final String LOSENTSCHEID_QUAL = "SitzberechnungErgebnis.Losentscheid"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_SITZBERECHNUNGERGEBNIS,ID_ERGEBNISEINGANG,ID_LISTE,ID_GRUPPE,ID_LISTENKOMBINATION,VERTEILUNG,SCHRITTNUMMER,SCHRITTTYP,SITZE,ZAEHLER,NENNER,ZAEHLERVOMNENNER,NENNERVOMNENNER,ZAEHLERVOMREST,NENNERVOMREST,LOSENTSCHEID};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>SitzberechnungErgebnisModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, SitzberechnungErgebnisModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_SITZBERECHNUNGERGEBNIS.toUpperCase())) != null) {
         m._id_SitzberechnungErgebnis = r.getString(idx.intValue());
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
      if ((idx = columns.get(SCHRITTNUMMER.toUpperCase())) != null) {
         m._schrittnummer = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(SCHRITTTYP.toUpperCase())) != null) {
         m._schritttyp = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(SITZE.toUpperCase())) != null) {
         m._sitze = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ZAEHLER.toUpperCase())) != null) {
         m._zaehler = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(NENNER.toUpperCase())) != null) {
         m._nenner = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ZAEHLERVOMNENNER.toUpperCase())) != null) {
         m._zaehlerVomNenner = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(NENNERVOMNENNER.toUpperCase())) != null) {
         m._nennerVomNenner = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ZAEHLERVOMREST.toUpperCase())) != null) {
         m._zaehlerVomRest = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(NENNERVOMREST.toUpperCase())) != null) {
         m._nennerVomRest = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(LOSENTSCHEID.toUpperCase())) != null) {
         m._losentscheid = r.getBoolean(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>SitzberechnungErgebnisModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(SitzberechnungErgebnisModelImpl m, PreparedStatement p) throws SQLException {
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
      if (columns.containsKey(SCHRITTNUMMER.toUpperCase())) {
         p.setInt(idx++, m._schrittnummer);
      }
      if (columns.containsKey(SCHRITTTYP.toUpperCase())) {
         p.setInt(idx++, m._schritttyp);
      }
      if (columns.containsKey(SITZE.toUpperCase())) {
         p.setInt(idx++, m._sitze);
      }
      if (columns.containsKey(ZAEHLER.toUpperCase())) {
         p.setInt(idx++, m._zaehler);
      }
      if (columns.containsKey(NENNER.toUpperCase())) {
         p.setInt(idx++, m._nenner);
      }
      if (columns.containsKey(ZAEHLERVOMNENNER.toUpperCase())) {
         p.setInt(idx++, m._zaehlerVomNenner);
      }
      if (columns.containsKey(NENNERVOMNENNER.toUpperCase())) {
         p.setInt(idx++, m._nennerVomNenner);
      }
      if (columns.containsKey(ZAEHLERVOMREST.toUpperCase())) {
         p.setInt(idx++, m._zaehlerVomRest);
      }
      if (columns.containsKey(NENNERVOMREST.toUpperCase())) {
         p.setInt(idx++, m._nennerVomRest);
      }
      if (columns.containsKey(LOSENTSCHEID.toUpperCase())) {
         p.setBoolean(idx++, m._losentscheid);
      }
      p.setString(idx++, m._id_SitzberechnungErgebnis);
   }

   /**
     * Inserts or changes the data of the given object in the table SitzberechnungErgebnis (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (SitzberechnungErgebnisModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>SitzberechnungErgebnisModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(SitzberechnungErgebnisModelImpl m) {
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
      if (columns.containsKey(SCHRITTNUMMER)) {
         values.add(toString(m.getSchrittnummer()));
      }
      if (columns.containsKey(SCHRITTTYP)) {
         values.add(toString(m.getSchritttyp()));
      }
      if (columns.containsKey(SITZE)) {
         values.add(toString(m.getSitze()));
      }
      if (columns.containsKey(ZAEHLER)) {
         values.add(toString(m.getZaehler()));
      }
      if (columns.containsKey(NENNER)) {
         values.add(toString(m.getNenner()));
      }
      if (columns.containsKey(ZAEHLERVOMNENNER)) {
         values.add(toString(m.getZaehlerVomNenner()));
      }
      if (columns.containsKey(NENNERVOMNENNER)) {
         values.add(toString(m.getNennerVomNenner()));
      }
      if (columns.containsKey(ZAEHLERVOMREST)) {
         values.add(toString(m.getZaehlerVomRest()));
      }
      if (columns.containsKey(NENNERVOMREST)) {
         values.add(toString(m.getNennerVomRest()));
      }
      if (columns.containsKey(LOSENTSCHEID)) {
         values.add(toString(m.isLosentscheid()));
      }
      values.add(toString(m.getID_SitzberechnungErgebnis()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table SitzberechnungErgebnis
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
     * @param id_SitzberechnungErgebnis searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_SitzberechnungErgebnis) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_SitzberechnungErgebnis=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_SitzberechnungErgebnis});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_SitzberechnungErgebnis=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_SitzberechnungErgebnis searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(SitzberechnungErgebnisModelImpl m, String id_SitzberechnungErgebnis)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_SitzberechnungErgebnis);
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
         values[idx++] = toString(id_SitzberechnungErgebnis);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of SitzberechnungErgebnis IDs
     *

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from SitzberechnungErgebnis",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table SitzberechnungErgebnis by a from parameters builded WHERE-clause
     *
     * @param id_SitzberechnungErgebnis searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_SitzberechnungErgebnis) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_SitzberechnungErgebnis=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_SitzberechnungErgebnis});
   }

   /**
     * Changes the data of the given object in the table SitzberechnungErgebnis 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(SitzberechnungErgebnisModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_SitzberechnungErgebnis=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object SitzberechnungErgebnisModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (SitzberechnungErgebnisModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Listenkombination returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param id_Listenkombination searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Listenkombination(String id_Listenkombination)
      throws SQLException {

      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where ID_Listenkombination=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Listenkombination});
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByID_Liste returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param id_Liste searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Liste(String id_Liste) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where ID_Liste=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Liste});
   }

   /**
     * Method retrieveIDsByID_Gruppe returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param id_Gruppe searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gruppe(String id_Gruppe) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where ID_Gruppe=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gruppe});
   }

   /**
     * Method retrieveIDsByVerteilung returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param verteilung searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByVerteilung(int verteilung) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where Verteilung=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(verteilung)});
   }

   /**
     * Method retrieveIDsBySchrittnummer returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param schrittnummer searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySchrittnummer(int schrittnummer) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where Schrittnummer=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(schrittnummer)});
   }

   /**
     * Method retrieveIDsBySchritttyp returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param schritttyp searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySchritttyp(int schritttyp) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where Schritttyp=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(schritttyp)});
   }

   /**
     * Method retrieveIDsBySitze returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param sitze searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsBySitze(int sitze) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where Sitze=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(sitze)});
   }

   /**
     * Method retrieveIDsByZaehler returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param zaehler searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByZaehler(int zaehler) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where Zaehler=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(zaehler)});
   }

   /**
     * Method retrieveIDsByNenner returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param nenner searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNenner(int nenner) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where Nenner=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(nenner)});
   }

   /**
     * Method retrieveIDsByZaehlerVomNenner returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param zaehlerVomNenner searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByZaehlerVomNenner(int zaehlerVomNenner)
      throws SQLException {

      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where ZaehlerVomNenner=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(zaehlerVomNenner)});
   }

   /**
     * Method retrieveIDsByNennerVomNenner returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param nennerVomNenner searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNennerVomNenner(int nennerVomNenner)
      throws SQLException {

      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where NennerVomNenner=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(nennerVomNenner)});
   }

   /**
     * Method retrieveIDsByZaehlerVomRest returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param zaehlerVomRest searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByZaehlerVomRest(int zaehlerVomRest) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where ZaehlerVomRest=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(zaehlerVomRest)});
   }

   /**
     * Method retrieveIDsByNennerVomRest returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param nennerVomRest searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByNennerVomRest(int nennerVomRest) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where NennerVomRest=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(nennerVomRest)});
   }

   /**
     * Method retrieveIDsByLosentscheid returns a {@link Collection} of SitzberechnungErgebnis IDs
     *
     * @param losentscheid searching condition

     * @return a {@link Collection} of SitzberechnungErgebnis IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByLosentscheid(boolean losentscheid) throws SQLException {
      return retrieveIDs(
         "select ID_SitzberechnungErgebnis from " + TABLENAME + " where Losentscheid=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(losentscheid)});
   }
}
