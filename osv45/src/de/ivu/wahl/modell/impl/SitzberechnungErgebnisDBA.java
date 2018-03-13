package de.ivu.wahl.modell.impl;

/**
 * SitzberechnungErgebnisDBA
 *
 * @author cos@ivu.de  (c) 2003-4 IVU Traffic Technologies AG
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

public class SitzberechnungErgebnisDBA extends BasicSitzberechnungErgebnisDBA {
  private static final long serialVersionUID = -1307248293280215975L;

  private static final Category LOGGER = Log4J.configure(SitzberechnungErgebnisDBA.class);
  static {
    LOGGER.info(Log4J.dumpVersion(SitzberechnungErgebnisDBA.class, Log4J
        .extractVersion("$Revision: 1.6 $"))); //$NON-NLS-1$
  }

  final static String innerQuerySitzeFrauen = ""; //$NON-NLS-1$
  // final static String innerQuerySitzeFrauen = "select count(*) from " +
  // BasicListenkandidaturDBA.TABLENAME
  // + " join " + ListeDBA.TABLENAME + " using (" + BasicListenkandidaturDBA.ID_LISTE + ") join "
  // + KandidatDBA.TABLENAME + " on (" + BasicListenkandidaturDBA.ID_KANDIDAT_QUAL + " = "
  // + KandidatDBA.ID_KANDIDAT_QUAL + ") left join " + ListenkandidaturAlternativeDBA.TABLENAME +
  // " on ("
  // + BasicListenkandidaturDBA.ID_LISTENKANDIDATUR_QUAL + " = "
  // + ListenkandidaturAlternativeDBA.ID_LISTENKANDIDATUR_QUAL + ") where "
  // + ListeDBA.ID_GRUPPEGEBIETSSPEZIFISCH_QUAL + " = " + ID_GRUPPEGEBIETSSPEZIFISCH_QUAL + " and ("
  // + BasicListenkandidaturDBA.GEWAEHLTVORL + " != 0 and " + WAHLERGEBNISART + " = 0 or "
  // + BasicListenkandidaturDBA.GEWAEHLTENDG + " != 0 and " + WAHLERGEBNISART + " = 1 or "
  // + BasicListenkandidaturDBA.GEWAEHLTVORP + " != 0 and " + WAHLERGEBNISART + " = 3) and "
  // + KandidatDBA.GESCHLECHT + "=2 " + "and (" + ID_ALTERNATIVE_QUAL
  // + " is null and not exists (select * from " + ListenkandidaturAlternativeDBA.TABLENAME +
  // " where "
  // + ListenkandidaturAlternativeDBA.ID_LISTENKANDIDATUR_QUAL + " = "
  // + BasicListenkandidaturDBA.ID_LISTENKANDIDATUR_QUAL + ") or " + ID_ALTERNATIVE_QUAL + " = "
  // + ListenkandidaturAlternativeDBA.ID_ALTERNATIVE_QUAL + ")";

  final static String innerQueryBesetzteSitze = ""; //$NON-NLS-1$
  // final static String innerQueryBesetzteSitze = "select count(*) from " +
  // BasicListenkandidaturDBA.TABLENAME
  // + " join " + ListeDBA.TABLENAME + " using (" + BasicListenkandidaturDBA.ID_LISTE + ") join "
  // + KandidatDBA.TABLENAME + " on (" + BasicListenkandidaturDBA.ID_KANDIDAT_QUAL + " = "
  // + KandidatDBA.ID_KANDIDAT_QUAL + ") left join " + ListenkandidaturAlternativeDBA.TABLENAME +
  // " on ("
  // + BasicListenkandidaturDBA.ID_LISTENKANDIDATUR_QUAL + " = "
  // + ListenkandidaturAlternativeDBA.ID_LISTENKANDIDATUR_QUAL + ") where "
  // + ListeDBA.ID_GRUPPEGEBIETSSPEZIFISCH_QUAL + " = " + ID_GRUPPEGEBIETSSPEZIFISCH_QUAL + " and ("
  // + BasicListenkandidaturDBA.GEWAEHLTVORL + " != 0 and " + WAHLERGEBNISART + " = 0 or "
  // + BasicListenkandidaturDBA.GEWAEHLTENDG + " != 0 and " + WAHLERGEBNISART + " = 1 or "
  // + BasicListenkandidaturDBA.GEWAEHLTVORP + " != 0 and " + WAHLERGEBNISART + " = 3) and ("
  // + ID_ALTERNATIVE_QUAL + " is null and not exists (select * from "
  // + ListenkandidaturAlternativeDBA.TABLENAME + " where "
  // + ListenkandidaturAlternativeDBA.ID_LISTENKANDIDATUR_QUAL + " = "
  // + BasicListenkandidaturDBA.ID_LISTENKANDIDATUR_QUAL + ") or " + ID_ALTERNATIVE_QUAL + " = "
  // + ListenkandidaturAlternativeDBA.ID_ALTERNATIVE_QUAL + ")";

  private final static String fromWhere = " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
      + ID_SITZBERECHNUNGERGEBNIS + "=?"; //$NON-NLS-1$

  /**
   * Query für die Methode retrieveByKey
   */
  final static String queryRetrieveByKeyExt = "(" + innerQueryBesetzteSitze + ") as " + SITZE //$NON-NLS-1$ //$NON-NLS-2$
      + fromWhere;

  final static String querySitzeFrauen = "select " + "(" + innerQuerySitzeFrauen + ")" + fromWhere; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

  final static String queryBesetzteSitze = "select " + "(" + innerQueryBesetzteSitze + ")" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      + fromWhere;

  /**
   * Holt den berechneten Wert des SitzberechnungErgebnisses.
   * 
   * @param id_SitzberechnungErgebnis Suchbedingung
   * @return <code>int</code> BesetzteSitze, wenn die Zeile in der Datenbank gefunden wurde, sonst
   *         <code>null</code>
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft TODO: auf ein Ergebnis
   *           reduziert -> Prüfen!!!
   */
  public static Integer retrieveCalcByKey(String id_SitzberechnungErgebnis) throws SQLException {

    Connection con = connect();
    try {
      PreparedStatement prepstatement = con.prepareStatement(queryRetrieveByKeyExt);
      try {
        prepstatement.setQueryTimeout(QUERY_TIMEOUT);
        prepstatement.setString(1, id_SitzberechnungErgebnis);
        ResultSet r = prepstatement.executeQuery();
        try {
          if (r.next()) {
            return r.getInt(1);
          } else {
            return null;
          }
        } finally {
          r.close();
        }
      } finally {
        prepstatement.close();
      }
    } catch (SQLException se) {
      String[] values = new String[1];
      int idx = 0;
      values[idx++] = toString(id_SitzberechnungErgebnis);
      logError(LOGGER, se, queryRetrieveByKeyExt, values);
      throw se;
    } finally {
      release(con);
    }
  }

  /**
   * Holt einen berechneten int-Wert des SitzberechnungErgebnisses.
   * 
   * @param query die verwendete SQL-Abfrage
   * @param id_SitzberechnungErgebnis Suchbedingung
   * @return <code>int</code> mit dem angeforderten Wert, wenn die Zeile in der Datenbank gefunden
   *         wurde, sonst <code>0</code>
   * @throws SQLException Kommunikation mit der Datenbank fehlerhaft
   */
  private static int retrieveIntByKey(String query, String id_SitzberechnungErgebnis)
      throws SQLException {

    Connection con = connect();
    try {
      PreparedStatement prepstatement = con.prepareStatement(query);
      try {
        prepstatement.setQueryTimeout(QUERY_TIMEOUT);
        prepstatement.setString(1, id_SitzberechnungErgebnis);
        ResultSet r = prepstatement.executeQuery();
        try {
          if (r.next()) {
            return r.getInt(1);
          } else {
            return 0;
          }
        } finally {
          r.close();
        }
      } finally {
        prepstatement.close();
      }
    } catch (SQLException se) {
      String[] values = new String[1];
      int idx = 0;
      values[idx++] = toString(id_SitzberechnungErgebnis);
      logError(LOGGER, se, query, values);
      throw se;
    } finally {
      release(con);
    }
  }

  public static int retrieveSitzeFrauenByKey(String id_SitzberechnungErgebnis) throws SQLException {
    return retrieveIntByKey(querySitzeFrauen, id_SitzberechnungErgebnis);
  }

  public static int retrieveBesetzteSitzeByKey(String id_SitzberechnungErgebnis)
      throws SQLException {
    return retrieveIntByKey(queryBesetzteSitze, id_SitzberechnungErgebnis);
  }
}
