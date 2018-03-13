/*
 * DBABase
 * 
 * Created on 08.10.2003
 * Copyright (c) 2003-5 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.ejb.fw;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

/**
 * Basisklasse f�r die Zugriffe auf die Datenbank
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public abstract class DBABase extends DatabaseAccess {
  private static final long serialVersionUID = -268547960178033157L;
  private static final Category LOGGER = Log4J.configure(DBABase.class);
  static {
    LOGGER.info(Log4J.dumpVersion(DBABase.class, Log4J.extractVersion("$Revision: 1.11 $"))); //$NON-NLS-1$
  }

  private static final IDResultCopier ID_RESULT_COPIER = new IDResultCopier();
  private static final IDIntegerResultCopier ID_INTEGER_RESULT_COPIER = new IDIntegerResultCopier();
  private static final IDBigDecimalResultCopier ID_BIG_DECIMAL_RESULT_COPIER = new IDBigDecimalResultCopier();
  protected static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

  /**
   * Methode retrieveIDs liefert eine Collection von IDs
   * 
   * @param query Anfrage mit Platzhaltern
   * @return eine Collection von IDs
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static List<String> retrieveIDs(String query) throws SQLException {
    return retrieveIDs(query, EMPTY_OBJECT_ARRAY);
  }

  /**
   * Methode retrieveIDs liefert eine Collection von IDs
   * 
   * @param query Anfrage mit Platzhaltern
   * @param condition konkrete Suchbedingungen
   * @return eine Collection von IDs
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static List<String> retrieveIDs(String query, Object[] condition) throws SQLException {
    return retrieveIDs(null, query, condition, null);
  }

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  protected static class KomplexSQL {
    private final String _sql;
    private Object[] _params;

    /**
     * @param sql
     */
    public KomplexSQL(String sql) {
      _sql = sql;
    }

    /**
     * @param sql
     * @param params
     */
    public KomplexSQL(String sql, Object[] params) {
      _sql = sql;
      _params = params;
    }

    /**
     * @return Parameter f�r das {@link PreparedStatement}
     */
    public Object[] getParams() {
      return _params;
    }

    /**
     * @return SQL-String
     */
    public String getSql() {
      return _sql;
    }
  }

  /**
   * Methode retrieveIDs liefert eine Collection von IDs
   * 
   * @param prologSQL beliebiges SQL, das vor der eigentlichen Abfrage ausgef�hrt wird
   * @param query Anfrage mit Platzhaltern
   * @param epilogSQL beliebiges SQL, das nach der eigentlichen Abfrage ausgef�hrt wird
   * @param queryCondition konkrete Suchbedingungen
   * @return eine Collection von IDs
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static List<String> retrieveIDs(KomplexSQL[] prologSQL,
      String query,
      Object[] queryCondition,
      KomplexSQL[] epilogSQL) throws SQLException {

    return retreive(prologSQL, query, queryCondition, epilogSQL, ID_RESULT_COPIER);
  }

  /**
   * Liefert eine Map von ID-Integer-Paaren
   * 
   * @param prologSQL beliebiges SQL, das vor der eigentlichen Abfrage ausgef�hrt wird
   * @param query Anfrage mit Platzhaltern
   * @param queryCondition konkrete Suchbedingungen
   * @param epilogSQL beliebiges SQL, das nach der eigentlichen Abfrage ausgef�hrt wird
   * @return eine Map von Abbildungen von IDs auf Zahlenwerte
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static Map<String, Integer> retrieveIDIntegerMap(KomplexSQL[] prologSQL,
      String query,
      Object[] queryCondition,
      KomplexSQL[] epilogSQL) throws SQLException {

    return retreive(prologSQL, query, queryCondition, epilogSQL, ID_INTEGER_RESULT_COPIER);
  }

  /**
   * Liefert eine Map von ID-BigDecimal-Paaren
   * 
   * @param prologSQL beliebiges SQL, das vor der eigentlichen Abfrage ausgef�hrt wird
   * @param query Anfrage mit Platzhaltern
   * @param queryCondition konkrete Suchbedingungen
   * @param epilogSQL beliebiges SQL, das nach der eigentlichen Abfrage ausgef�hrt wird
   * @return eine Map von Abbildungen von IDs auf Zahlenwerte
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static Map<String, BigDecimal> retrieveIDBigDecimalMap(KomplexSQL[] prologSQL,
      String query,
      Object[] queryCondition,
      KomplexSQL[] epilogSQL) throws SQLException {

    return retreive(prologSQL, query, queryCondition, epilogSQL, ID_BIG_DECIMAL_RESULT_COPIER);
  }

  /**
   * F�hrt die Query aus und liefert einen vom Command abh�ngigen Ergebnisbeh�lter
   * 
   * @param prologSQL beliebiges SQL, das vor der eigentlichen Abfrage ausgef�hrt wird
   * @param query Anfrage mit Platzhaltern
   * @param queryCondition konkrete Suchbedingungen
   * @param epilogSQL beliebiges SQL, das nach der eigentlichen Abfrage ausgef�hrt wird
   * @param resultCopier Command Pattern implementierung, die das ResultSet in den gew�nschten
   *          Beh�lter umkopiert
   * @return vom Command abh�ngiger Ergebnisbeh�lter
   * @throws SQLException
   */
  private static <T> T retreive(KomplexSQL[] prologSQL,
      String query,
      Object[] queryCondition,
      KomplexSQL[] epilogSQL,
      ResultCopier<T> resultCopier) throws SQLException {

    Connection con = connect();
    try {
      PreparedStatement queryStatement = con.prepareStatement(query);
      try {
        execute(prologSQL, con);
        try {
          queryStatement.setQueryTimeout(QUERY_TIMEOUT);
          if (queryCondition != null) {
            for (int i = 0; i < queryCondition.length; i++) {
              Object condition = queryCondition[i];
              if (condition == null) {
                queryStatement.setString(i + 1, null);
              } else {
                queryStatement.setObject(i + 1, condition);
              }
            }
          }
          ResultSet r = queryStatement.executeQuery();
          try {
            return resultCopier.copyResult(r);
          } finally {
            r.close();
          }
        } finally {
          execute(epilogSQL, con);
        }
      } finally {
        queryStatement.close();
      }
    } catch (SQLException se) {
      logError(LOGGER, se, query, queryCondition);
      throw se;
    } finally {
      release(con);
    }
  }

  private interface ResultCopier<T> {
    T copyResult(ResultSet r) throws SQLException;
  }

  static class IDResultCopier implements ResultCopier<List<String>> {
    public List<String> copyResult(ResultSet r) throws SQLException {
      List<String> retRows = new ArrayList<String>();
      while (r.next()) {
        // Achtung funktioniert nicht f�r zusammengesetzte PKs
        retRows.add(r.getString(1));
      }
      return retRows;
    }
  }

  static class IDIntegerResultCopier implements ResultCopier<Map<String, Integer>> {
    public Map<String, Integer> copyResult(ResultSet r) throws SQLException {
      Map<String, Integer> ret = new LinkedHashMap<String, Integer>();
      while (r.next()) {
        ret.put(r.getString(1), r.getInt(2));
      }
      return ret;
    }
  }

  static class IDBigDecimalResultCopier implements ResultCopier<Map<String, BigDecimal>> {
    public Map<String, BigDecimal> copyResult(ResultSet r) throws SQLException {
      Map<String, BigDecimal> ret = new LinkedHashMap<String, BigDecimal>();
      while (r.next()) {
        ret.put(r.getString(1), r.getBigDecimal(2));
      }
      return ret;
    }
  }

  /**
   * @param komplexSQLArray
   * @param con
   * @throws SQLException
   */
  private static void execute(KomplexSQL[] komplexSQLArray, Connection con) throws SQLException {
    if (komplexSQLArray != null) {
      for (int i = 0; i < komplexSQLArray.length; i++) {
        KomplexSQL komplexSQL = komplexSQLArray[i];
        String sql = komplexSQL.getSql();
        Object[] condition = komplexSQL.getParams();
        try {
          PreparedStatement statement = con.prepareStatement(sql);
          try {
            if (condition != null) {
              for (int j = 0; j < condition.length; j++) {
                statement.setObject(j + 1, condition[j]);
              }
            }
            statement.execute();
          } finally {
            statement.close();
          }
        } catch (SQLException se) {
          logError(LOGGER, se, sql, condition);
          if ("Lock wait timeout exceeded; try restarting transaction".equals(se.getMessage())) { //$NON-NLS-1$
            try {
              final Statement statement = con.createStatement();
              try {
                ResultSet resultSet = statement.executeQuery("show innodb status"); //$NON-NLS-1$
                if (resultSet.next()) {
                  LOGGER.info(resultSet.getString("Status")); //$NON-NLS-1$
                }
                resultSet.close();
              } finally {
                statement.close();
              }
            } catch (Exception e) {
              LOGGER.error(e);
            }
          }
          throw se;
        }
      }
    }
  }

  /**
   * L�scht aus der Tabelle mittels einer, aus den Parametern gebildeten WHERE Klausel
   * 
   * @param query Anfrage mit Platzhaltern
   * @param condition konkrete Suchbedingungen
   * @return <code>true</code> wenn das Objekt erfolgreich gel�scht wurde <code>false</code>
   *         andernfalls
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static boolean delete(String query, Object[] condition) throws SQLException {
    return calculateModificationSuccessStatus(deleteAll(query, condition));
  }

  /**
   * L�scht aus der Tabelle mittels einer, aus den Parametern gebildeten WHERE Klausel
   * 
   * @param query Anfrage mit Platzhaltern
   * @param condition konkrete Suchbedingungen
   * @return Anzahl gel�schter Zeilen
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static int deleteAll(String query, Object[] condition) throws SQLException {
    Connection con = connect();
    try {
      PreparedStatement prepstatement = con.prepareStatement(query);
      try {
        prepstatement.setQueryTimeout(QUERY_TIMEOUT);
        for (int i = 0; i < condition.length; i++) {
          prepstatement.setObject(i + 1, condition[i]);
        }
        return prepstatement.executeUpdate();
      } finally {
        prepstatement.close();
      }
    } catch (SQLException se) {
      logError(LOGGER, se, query, condition);
      throw se;
    } finally {
      release(con);
    }
  }

  /**
   * Methode liefert die Anzahl der Objekte, die mittels einer WHERE-Klausel �ber die Parameter
   * eingeschr�nkt wurde
   * 
   * @param query Anfrage mit Platzhaltern
   * @param condition konkrete Suchbedingungen
   * @return Anzahl der Objekte
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static int count(String query, Object[] condition) throws SQLException {
    Connection con = connect();
    try {
      PreparedStatement prepstatement = con.prepareStatement(query);
      try {
        prepstatement.setQueryTimeout(QUERY_TIMEOUT);
        for (int i = 0; i < condition.length; i++) {
          prepstatement.setObject(i + 1, condition[i]);
        }
        ResultSet r = prepstatement.executeQuery();
        try {
          return r.next() ? r.getInt(1) : -1;
        } finally {
          r.close();
        }
      } finally {
        prepstatement.close();
      }
    } catch (SQLException se) {
      logError(LOGGER, se, query, condition);
      throw se;
    } finally {
      release(con);
    }
  }

  /**
   * Methode liefert die Anzahl der Zeilen der Tabelle Anwender
   * 
   * @param query select count(*) from "table" oder �hnlich
   * @return Anzahl der Zeilen
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  public static int count(String query) throws SQLException {
    Connection con = connect();
    try {
      Statement statement = con.createStatement();
      try {
        statement.setQueryTimeout(QUERY_TIMEOUT);
        ResultSet r = statement.executeQuery(query);
        try {
          return r.next() ? r.getInt(1) : -1;
        } finally {
          r.close();
        }
      } finally {
        statement.close();
      }
    } catch (SQLException se) {
      logError(LOGGER, se, query, EMPTY_OBJECT_ARRAY);
      throw se;
    } finally {
      release(con);
    }
  }

  protected static boolean calculateModificationSuccessStatus(int lines) {
    if (lines == 1) {
      return true;
    }
    if (lines != 0) {
      setCorruptionError();
    }
    return false;
  }

  /**
   * @author cos@ivu.de, IVU Traffic Technologies AG
   */
  protected static class MetaContainer {
    private static final Category ILOGGER = Log4J.configure(MetaContainer.class);

    private final String _tableName;
    private final String[] _origColumns;
    private final String[] _origColumnsUpper;
    private Map<String, Integer> _columns;
    private Set<String> _pks;
    private String _propertyList;
    private String _valuesWildCards;
    private String _updateSets;

    public MetaContainer(String tableName, String[] origColumns) {
      _tableName = tableName.toUpperCase();
      _origColumns = origColumns;
      _origColumnsUpper = new String[origColumns.length];
      for (int idx = 0; idx < _origColumns.length; ++idx) {
        _origColumnsUpper[idx] = origColumns[idx].toUpperCase();
      }
    }

    /**
     * @return {@link Map} vom Spaltennamen zum Spaltenindex, geordnet nach Spaltenindex
     */
    public synchronized Map<String, Integer> getColumns() {
      if (_columns == null) {
        Connection con = DatabaseAccess.connect();
        try {
          DatabaseMetaData metaData = con.getMetaData();
          // String catalog = con.getCatalog();
          String catalog = "osv"; //$NON-NLS-1$
          String schema = null;

          ResultSet pkRS = metaData.getPrimaryKeys(catalog, schema, _tableName);
          Set<String> pks = new HashSet<String>();
          for (int i = 1; pkRS.next(); i++) {
            String string = pkRS.getString("COLUMN_NAME"); //$NON-NLS-1$
            pks.add(string);
            ILOGGER.debug(string);
          }
          pkRS.close();

          ResultSet columnsRS = metaData.getColumns(catalog, schema, _tableName, "%"); //$NON-NLS-1$
          Map<String, Integer> columns = new LinkedHashMap<String, Integer>();
          Map<Integer, String> pksSorted = new TreeMap<Integer, String>();
          for (int i = 1; columnsRS.next(); i++) {
            String string = columnsRS.getString("COLUMN_NAME"); //$NON-NLS-1$
            // Prim�rschl�ssel erst einmal herausnehmen
            if (pks.contains(string)) {
              pksSorted.put(i, string);
            } else {
              columns.put(string, i);
            }
            ILOGGER.debug(i + ". " + string); //$NON-NLS-1$
          }
          columnsRS.close();

          for (Entry<Integer, String> entry : pksSorted.entrySet()) {
            columns.put(entry.getValue(), entry.getKey());
          }
          // Schnittmenge mit den Spalten bilden, die zur Generierungszeit bekannt waren
          columns.keySet().retainAll(Arrays.asList(_origColumnsUpper));
          _pks = pks;
          _columns = columns;
        } catch (SQLException e) {
          ILOGGER.fatal("Cannot read the meta data from the database", e); //$NON-NLS-1$
          throw new RuntimeException(e.getMessage(), e);
        } finally {
          DatabaseAccess.release(con);
        }
      }
      return _columns;
    }

    /**
     * @return Liste der Spaltennamen f�r die SELECT- oder INSERT-Klausel
     */
    public String getPropertyList() {
      if (_propertyList == null) {
        StringBuilder propertyList = new StringBuilder();
        for (Iterator<String> columnItr = getColumns().keySet().iterator(); columnItr.hasNext();) {
          propertyList.append(columnItr.next());
          if (columnItr.hasNext()) {
            propertyList.append(',');
          }
        }
        ILOGGER.debug("PROPERTYLIST: " + propertyList); //$NON-NLS-1$
        _propertyList = propertyList.toString();
      }
      return _propertyList;
    }

    /**
     * @return Liste der Fragezeichne f�r die VALUES-Klausel
     */
    public String getValuesWildCards() {
      if (_valuesWildCards == null) {
        StringBuilder valuesWildCards = new StringBuilder();
        for (Iterator<String> columnItr = getColumns().keySet().iterator(); columnItr.hasNext();) {
          columnItr.next();
          valuesWildCards.append('?');
          if (columnItr.hasNext()) {
            valuesWildCards.append(',');
          }
        }
        ILOGGER.debug("VALUESWILDCARDS: " + valuesWildCards); //$NON-NLS-1$
        _valuesWildCards = valuesWildCards.toString();
      }
      return _valuesWildCards;
    }

    /**
     * @return Liste der Zuweisungen von Platzhaltern f�r die SET-Klausel
     */
    public synchronized String getUpdateSets() {
      if (_updateSets == null) {
        StringBuilder updateSets = new StringBuilder();
        boolean first = true;
        for (Iterator<String> columnItr = getColumns().keySet().iterator(); columnItr.hasNext();) {
          String name = columnItr.next();
          if (_pks.contains(name)) {
            continue;
          }
          if (first) {
            first = false;
          } else {
            updateSets.append(',');
          }
          updateSets.append(name).append("=?"); //$NON-NLS-1$
        }
        ILOGGER.debug("UPDATESETS: " + updateSets); //$NON-NLS-1$
        _updateSets = updateSets.toString();
      }
      return _updateSets;
    }
  }

  protected static void logError(Category log, SQLException se, String query, Object[] condition) {
    StringBuilder values = new StringBuilder();

    if (condition != null && condition.length > 0) {
      values.append(" with the parameters ["); //$NON-NLS-1$

      try {
        for (int i = 0; i < condition.length; i++) {
          if (i > 0) {
            values.append(", "); //$NON-NLS-1$
          }
          values.append(condition[i]);
        }
      } catch (Exception e) {
        values.append(" Further parametres not evaluateable!"); //$NON-NLS-1$
      }
      values.append("]"); //$NON-NLS-1$
    }

    log.error("Error at query: " + query + values + " -> " + se); //$NON-NLS-1$ //$NON-NLS-2$
  }

  protected static String toString(long l) {
    return Long.toString(l);
  }

  protected static String toString(int i) {
    return Integer.toString(i);
  }

  protected static String toString(short s) {
    return Short.toString(s);
  }

  protected static String toString(byte b) {
    return Byte.toString(b);
  }

  protected static String toString(boolean b) {
    return Boolean.toString(b);
  }

  protected static String toString(Object obj) {
    return String.valueOf(obj);
  }
}
