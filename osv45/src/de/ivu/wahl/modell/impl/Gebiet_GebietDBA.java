package de.ivu.wahl.modell.impl;

/**
 * Gebiet_GebietDBA
 *
 * @author cos@ivu.de  (c) 2003-4 IVU Traffic Technologies AG
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

public class Gebiet_GebietDBA extends BasicGebiet_GebietDBA {
  private static final long serialVersionUID = 6084196511194345637L;
  private static final Category LOGGER = Log4J.configure(Gebiet_GebietDBA.class);
  static {
    LOGGER
        .info(Log4J.dumpVersion(Gebiet_GebietDBA.class, Log4J.extractVersion("$Revision: 1.4 $"))); //$NON-NLS-1$
  }

  /**
   * Füllt die Tabelle Gebiet_Gebiet mit generationsübergreifenden Vorfahre-Nachkomme-Referenzen
   * anhand der Daten in Tabelle Gebiet. Schon früher eingetragene Daten werden übersprungen (kann
   * also mehrmalls aufgerufen werden).
   * 
   * @throws SQLException bei einem Datenbankproblem
   */
  public static void init() throws SQLException {
    Connection con = connect();

    String insertDirect = "insert into " + TABLENAME + " (" + ID_ELTERNGEBIET + "," //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        + ID_UNTERGEBIET + ") " + "select " + BasicGebietDBA.ID_UEBERGEORDNETESGEBIET + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + BasicGebietDBA.ID_GEBIET + " from " + BasicGebietDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
        + BasicGebietDBA.ID_UEBERGEORDNETESGEBIET + " is not null " //$NON-NLS-1$
        + "and not exists (select * from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
        + BasicGebietDBA.ID_UEBERGEORDNETESGEBIET + "=" + ID_ELTERNGEBIET + " and " //$NON-NLS-1$ //$NON-NLS-2$
        + BasicGebietDBA.ID_GEBIET + "=" + ID_UNTERGEBIET + ")"; //$NON-NLS-1$ //$NON-NLS-2$

    String insertInductive = "insert into " + TABLENAME + " (" + ID_ELTERNGEBIET + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + ID_UNTERGEBIET + ") " + "select  gg1." + ID_ELTERNGEBIET + "," + BasicGebietDBA.ID_GEBIET //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + " from " + TABLENAME + " gg1, " + BasicGebietDBA.TABLENAME + " where gg1." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + ID_UNTERGEBIET + "=" + BasicGebietDBA.ID_UEBERGEORDNETESGEBIET //$NON-NLS-1$
        + " and not exists (select * from " + TABLENAME + " gg2 where gg1." + ID_ELTERNGEBIET //$NON-NLS-1$ //$NON-NLS-2$
        + "=gg2." + ID_ELTERNGEBIET + " and " + BasicGebietDBA.ID_GEBIET + "=gg2." + ID_UNTERGEBIET //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + ")"; //$NON-NLS-1$

    String insertSelfreference = "insert into " + TABLENAME + " (" + ID_ELTERNGEBIET + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + ID_UNTERGEBIET + ") " + "select " + BasicGebietDBA.ID_GEBIET + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + BasicGebietDBA.ID_GEBIET + " from " + BasicGebietDBA.TABLENAME //$NON-NLS-1$
        + " where not exists (select * from " + TABLENAME + " where " + BasicGebietDBA.ID_GEBIET //$NON-NLS-1$ //$NON-NLS-2$
        + "=" + ID_ELTERNGEBIET + " and " + BasicGebietDBA.ID_GEBIET + "=" + ID_UNTERGEBIET + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    int phase = 1;
    try {
      PreparedStatement prepstatement1 = con.prepareStatement(insertDirect);
      PreparedStatement prepstatement2 = con.prepareStatement(insertInductive);
      PreparedStatement prepstatement3 = con.prepareStatement(insertSelfreference);
      try {
        if (prepstatement1.executeUpdate() > 0) {
          try {
            phase = 2;
            while (prepstatement2.executeUpdate() > 0) {/* loop body does nothing */
            }
          } finally {
            prepstatement2.close();
          }
          try {
            phase = 3;
            prepstatement3.executeUpdate();
          } finally {
            prepstatement3.close();
          }
        }
      } finally {
        prepstatement1.close();
      }
    } catch (SQLException se) {
      String query = null;
      switch (phase) {
        case 1 :
          query = insertDirect;
          break;
        case 2 :
          query = insertInductive;
          break;
        case 3 :
          query = insertSelfreference;
          break;
      }
      logError(LOGGER, se, query, new Object[]{});
      throw se;
    } finally {
      release(con);
    }
  }
}
