/*
 * BasicGebietsstatusDBA
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
  * Implementation of the persistency layer for the entity Gebietsstatus.
  * Contains all SQL access functions.
  *
  * @author cos@ivu.de  (c) 2003-2016 Statistisches Bundesamt und IVU Traffic Technologies AG
  * @version $Id: tablegen.properties,v 1.36 2009/10/12 09:33:21 jon Exp $
  */
public abstract class BasicGebietsstatusDBA extends DBABase {
   private static final Category LOGGER = Log4J.configure(BasicGebietsstatusDBA.class);
   static {
      LOGGER.info(Log4J.dumpVersion(BasicGebietsstatusDBA.class, Log4J.extractVersion("$Revision: 1.36 $"))); //$NON-NLS-1$
   }

   public static final String TABLENAME = "Gebietsstatus"; //$NON-NLS-1$
   public static final String ID_GEBIETSSTATUS = "ID_Gebietsstatus"; //$NON-NLS-1$
   public static final String ID_GEBIETSSTATUS_QUAL = "Gebietsstatus.ID_Gebietsstatus"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG = "ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_ERGEBNISEINGANG_QUAL = "Gebietsstatus.ID_Ergebniseingang"; //$NON-NLS-1$
   public static final String ID_GEBIET = "ID_Gebiet"; //$NON-NLS-1$
   public static final String ID_GEBIET_QUAL = "Gebietsstatus.ID_Gebiet"; //$NON-NLS-1$
   public static final String WAHLERGEBNISART = "Wahlergebnisart"; //$NON-NLS-1$
   public static final String WAHLERGEBNISART_QUAL = "Gebietsstatus.Wahlergebnisart"; //$NON-NLS-1$
   public static final String KORREKTURNUMMER = "Korrekturnummer"; //$NON-NLS-1$
   public static final String KORREKTURNUMMER_QUAL = "Gebietsstatus.Korrekturnummer"; //$NON-NLS-1$
   public static final String ANZAHLERGEBNISSEKUMULIERT = "AnzahlErgebnisseKumuliert"; //$NON-NLS-1$
   public static final String ANZAHLERGEBNISSEKUMULIERT_QUAL = "Gebietsstatus.AnzahlErgebnisseKumuliert"; //$NON-NLS-1$
   public static final String VOLLSTAENDIG = "Vollstaendig"; //$NON-NLS-1$
   public static final String VOLLSTAENDIG_QUAL = "Gebietsstatus.Vollstaendig"; //$NON-NLS-1$

   private static final String[] COLUMNS = {ID_GEBIETSSTATUS,ID_ERGEBNISEINGANG,ID_GEBIET,WAHLERGEBNISART,KORREKTURNUMMER,ANZAHLERGEBNISSEKUMULIERT,VOLLSTAENDIG};
   private static final MetaContainer META_CONTAINER = new MetaContainer(TABLENAME, COLUMNS);

   /**
     * Writes the data from a <code>ResultSet</code> to <code>GebietsstatusModelImpl</code> object.
     *
     * @param r the ResultSet with the data
     * @param m the object to be filled
     * @throws SQLException Communication with database is failing
     */
   protected static void getFromResultSet (ResultSet r, GebietsstatusModelImpl m) throws SQLException {
      Integer idx;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      if ((idx = columns.get(ID_GEBIETSSTATUS.toUpperCase())) != null) {
         m._id_Gebietsstatus = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_ERGEBNISEINGANG.toUpperCase())) != null) {
         m._id_Ergebniseingang = r.getString(idx.intValue());
      }
      if ((idx = columns.get(ID_GEBIET.toUpperCase())) != null) {
         m._id_Gebiet = r.getString(idx.intValue());
      }
      if ((idx = columns.get(WAHLERGEBNISART.toUpperCase())) != null) {
         m._wahlergebnisart = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(KORREKTURNUMMER.toUpperCase())) != null) {
         m._korrekturnummer = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(ANZAHLERGEBNISSEKUMULIERT.toUpperCase())) != null) {
         m._anzahlErgebnisseKumuliert = r.getInt(idx.intValue());
      }
      if ((idx = columns.get(VOLLSTAENDIG.toUpperCase())) != null) {
         m._vollstaendig = r.getBoolean(idx.intValue());
      }
   }

   /**
     * Writes the data from a <code>GebietsstatusModelImpl</code> object into a <code>PreparedStatement</code>.
     *
     * @param m Model object with the data
     * @param p PreparedStatement
     * @throws SQLException Communication with database is failing
     */
   protected static void putIntoPreparedStatement(GebietsstatusModelImpl m, PreparedStatement p) throws SQLException {
      int idx = 1;
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      p.setQueryTimeout(QUERY_TIMEOUT);
      if (columns.containsKey(ID_ERGEBNISEINGANG.toUpperCase())) {
         p.setString(idx++, m._id_Ergebniseingang);
      }
      if (columns.containsKey(ID_GEBIET.toUpperCase())) {
         p.setString(idx++, m._id_Gebiet);
      }
      if (columns.containsKey(WAHLERGEBNISART.toUpperCase())) {
         p.setInt(idx++, m._wahlergebnisart);
      }
      if (columns.containsKey(KORREKTURNUMMER.toUpperCase())) {
         p.setInt(idx++, m._korrekturnummer);
      }
      if (columns.containsKey(ANZAHLERGEBNISSEKUMULIERT.toUpperCase())) {
         p.setInt(idx++, m._anzahlErgebnisseKumuliert);
      }
      if (columns.containsKey(VOLLSTAENDIG.toUpperCase())) {
         p.setBoolean(idx++, m._vollstaendig);
      }
      p.setString(idx++, m._id_Gebietsstatus);
   }

   /**
     * Inserts or changes the data of the given object in the table Gebietsstatus (generic)
     *
     * @param m Model object, which's current state has to be written into the database
     * @param query Query, which runs the operations
     * @return <code>true</code> if the object was written or changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insertOrUpdate (GebietsstatusModelImpl m, String query) throws SQLException {
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
     * Writes the data from a <code>GebietsstatusModelImpl</code> object in <code>String[]</code> for diagnostical reasons.
     *
     * @param m Model object with the data
     * @return String[] with the data from the model object
     */
   protected static String[] convertModelToStringArray(GebietsstatusModelImpl m) {
      Map<String, Integer> columns = META_CONTAINER.getColumns();
      List<String> values = new ArrayList<String>();
      if (columns.containsKey(ID_ERGEBNISEINGANG)) {
         values.add(toString(m.getID_Ergebniseingang()));
      }
      if (columns.containsKey(ID_GEBIET)) {
         values.add(toString(m.getID_Gebiet()));
      }
      if (columns.containsKey(WAHLERGEBNISART)) {
         values.add(toString(m.getWahlergebnisart()));
      }
      if (columns.containsKey(KORREKTURNUMMER)) {
         values.add(toString(m.getKorrekturnummer()));
      }
      if (columns.containsKey(ANZAHLERGEBNISSEKUMULIERT)) {
         values.add(toString(m.getAnzahlErgebnisseKumuliert()));
      }
      if (columns.containsKey(VOLLSTAENDIG)) {
         values.add(toString(m.isVollstaendig()));
      }
      values.add(toString(m.getID_Gebietsstatus()));
      return values.toArray(new String[values.size()]);
   }

   /**
     * Method returns the number of rows of the table Gebietsstatus
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
     * @param id_Gebietsstatus searching condition
     * @return Number of objects
     * @throws SQLException Communication with database is failing
     */
   public static int countByKey(String id_Gebietsstatus) throws SQLException {
      return count(
         "select count(*) from " + TABLENAME + " where ID_Gebietsstatus=?",  //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gebietsstatus});
   }

   /**
     * Query for the method retrieveByKey
     */
   final static String queryRetrieveByKey = 
      "select * from " + TABLENAME + " where ID_Gebietsstatus=?"; //$NON-NLS-1$ //$NON-NLS-2$

   /**
     * Fills the first parameter by a WHERE-clause from other parameters.
     *
     * @param  m the object to be filled
     * @param id_Gebietsstatus searching condition
     * @return <code>true</code> if the row was found in the database, else <code>false</code>
     * @throws SQLException Communication with database is failing
     */
   public static boolean retrieveByKey(GebietsstatusModelImpl m, String id_Gebietsstatus)
      throws SQLException {

      Connection con = connect();
      try {
         PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKey);
         try {
            prepstatement.setQueryTimeout(QUERY_TIMEOUT);
            prepstatement.setString(1, id_Gebietsstatus);
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
         values[idx++] = toString(id_Gebietsstatus);
         logError(LOGGER, se, queryRetrieveByKey, values);
         throw se;
      } finally {
         release(con);
      }
   }

   /**
     * Method retrieveAllIDs returns a {@link Collection} of Gebietsstatus IDs
     *

     * @return a {@link Collection} of Gebietsstatus IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveAllIDs() throws SQLException {
      return retrieveIDs(
         "select ID_Gebietsstatus from Gebietsstatus",  //$NON-NLS-1$
         new Object[]{});
   }

   /**
     * Deletes from the table Gebietsstatus by a from parameters builded WHERE-clause
     *
     * @param id_Gebietsstatus searching condition
     * @return <code>true</code> if the object was deleted successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean deleteByKey(String id_Gebietsstatus) throws SQLException {
      return delete(
         "delete from " + TABLENAME + " where ID_Gebietsstatus=?", //$NON-NLS-1$ //$NON-NLS-2$
         new Object[]{id_Gebietsstatus});
   }

   /**
     * Changes the data of the given object in the table Gebietsstatus 
     *
     * @param m Model object, which's current state has to be written into the database
     * @return <code>true</code> if the object was changed successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean updateByKey(GebietsstatusModelImpl m) throws SQLException {
      return insertOrUpdate(m, 
         "update " + TABLENAME + " set " + META_CONTAINER.getUpdateSets() + " where ID_Gebietsstatus=?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   /**
     * Inserts the given object into the database
     *
     * @param m the object GebietsstatusModelImplto be written 
     * @return <code>true</code> if the object was written successfully
     *         <code>false</code> otherwise
     * @throws SQLException Communication with database is failing
     */
   public static boolean insert (GebietsstatusModelImpl m) throws SQLException {
      if (m != null) {
         return insertOrUpdate(m, 
            "insert into " + TABLENAME + " (" + META_CONTAINER.getPropertyList() + ") " +  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "values (" + META_CONTAINER.getValuesWildCards() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
      } else {
         return true;
      }
   }

   /**
     * Method retrieveIDsByID_Ergebniseingang returns a {@link Collection} of Gebietsstatus IDs
     *
     * @param id_Ergebniseingang searching condition

     * @return a {@link Collection} of Gebietsstatus IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Ergebniseingang(String id_Ergebniseingang)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebietsstatus from " + TABLENAME + " where ID_Ergebniseingang=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Ergebniseingang});
   }

   /**
     * Method retrieveIDsByID_Gebiet returns a {@link Collection} of Gebietsstatus IDs
     *
     * @param id_Gebiet searching condition

     * @return a {@link Collection} of Gebietsstatus IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByID_Gebiet(String id_Gebiet) throws SQLException {
      return retrieveIDs(
         "select ID_Gebietsstatus from " + TABLENAME + " where ID_Gebiet=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{id_Gebiet});
   }

   /**
     * Method retrieveIDsByWahlergebnisart returns a {@link Collection} of Gebietsstatus IDs
     *
     * @param wahlergebnisart searching condition

     * @return a {@link Collection} of Gebietsstatus IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByWahlergebnisart(int wahlergebnisart)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebietsstatus from " + TABLENAME + " where Wahlergebnisart=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(wahlergebnisart)});
   }

   /**
     * Method retrieveIDsByKorrekturnummer returns a {@link Collection} of Gebietsstatus IDs
     *
     * @param korrekturnummer searching condition

     * @return a {@link Collection} of Gebietsstatus IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByKorrekturnummer(int korrekturnummer)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebietsstatus from " + TABLENAME + " where Korrekturnummer=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(korrekturnummer)});
   }

   /**
     * Method retrieveIDsByAnzahlErgebnisseKumuliert returns a {@link Collection} of Gebietsstatus IDs
     *
     * @param anzahlErgebnisseKumuliert searching condition

     * @return a {@link Collection} of Gebietsstatus IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByAnzahlErgebnisseKumuliert(int anzahlErgebnisseKumuliert)
      throws SQLException {

      return retrieveIDs(
         "select ID_Gebietsstatus from " + TABLENAME + " where AnzahlErgebnisseKumuliert=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Integer.valueOf(anzahlErgebnisseKumuliert)});
   }

   /**
     * Method retrieveIDsByVollstaendig returns a {@link Collection} of Gebietsstatus IDs
     *
     * @param vollstaendig searching condition

     * @return a {@link Collection} of Gebietsstatus IDs
     * @throws SQLException Communication with database is failing
     */
   public static Collection<String> retrieveIDsByVollstaendig(boolean vollstaendig) throws SQLException {
      return retrieveIDs(
         "select ID_Gebietsstatus from " + TABLENAME + " where Vollstaendig=?",  //$NON-NLS-1$
          //$NON-NLS-1$
         new Object[]{Boolean.valueOf(vollstaendig)});
   }
}
