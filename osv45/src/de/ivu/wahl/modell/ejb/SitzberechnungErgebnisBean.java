package de.ivu.wahl.modell.ejb;

/**
 * SitzberechnungErgebnisBean
 *
 * @author cos@ivu.de  (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.impl.BasicSitzberechnungErgebnisDBA;

public class SitzberechnungErgebnisBean extends BasicSitzberechnungErgebnisBean {
  private static final long serialVersionUID = -2547397948816527566L;
  private static final Category LOGGER = Log4J.configure(SitzberechnungErgebnisBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(SitzberechnungErgebnisBean.class, Log4J
        .extractVersion("$Revision: 1.12 $"))); //$NON-NLS-1$
  }

  /**
   * Find results by type result of entry and type
   * 
   * @param id_Ergebniseingang
   * @param typ
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByErgebniseingangAndTyp(String id_Ergebniseingang, int typ)
      throws FinderException {
    try {
      return DBABase
          .retrieveIDs("select " + BasicSitzberechnungErgebnisDBA.ID_SITZBERECHNUNGERGEBNIS //$NON-NLS-1$
              + " from " + BasicSitzberechnungErgebnisDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
              + BasicSitzberechnungErgebnisDBA.ID_ERGEBNISEINGANG + "=? and " //$NON-NLS-1$
              + BasicSitzberechnungErgebnisDBA.SCHRITTTYP + " =? order by " //$NON-NLS-1$
              + BasicSitzberechnungErgebnisDBA.SCHRITTNUMMER, new Object[]{id_Ergebniseingang,
              Integer.valueOf(typ)});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find results by type result of entry and distribution
   * 
   * @param id_Ergebniseingang
   * @param verteilung
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByErgebniseingangAndVerteilung(String id_Ergebniseingang,
      int verteilung) throws FinderException {
    try {
      return DBABase
          .retrieveIDs("select " + BasicSitzberechnungErgebnisDBA.ID_SITZBERECHNUNGERGEBNIS //$NON-NLS-1$
              + " from " + BasicSitzberechnungErgebnisDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
              + BasicSitzberechnungErgebnisDBA.ID_ERGEBNISEINGANG + "=? and " //$NON-NLS-1$
              + BasicSitzberechnungErgebnisDBA.VERTEILUNG + " =?", new Object[]{id_Ergebniseingang, //$NON-NLS-1$
              new Integer(verteilung)});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find results by type result of entry, distribution and type
   * 
   * @param id_Ergebniseingang
   * @param typ
   * @param verteilung
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByErgebniseingangAndTypAndVerteilung(String id_Ergebniseingang,
      int typ,
      int verteilung) throws FinderException {
    try {
      return DBABase
          .retrieveIDs("select " + BasicSitzberechnungErgebnisDBA.ID_SITZBERECHNUNGERGEBNIS //$NON-NLS-1$
              + " from " + BasicSitzberechnungErgebnisDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
              + BasicSitzberechnungErgebnisDBA.ID_ERGEBNISEINGANG
              + "=? and " //$NON-NLS-1$
              + BasicSitzberechnungErgebnisDBA.SCHRITTTYP
              + " =?and " + BasicSitzberechnungErgebnisDBA.VERTEILUNG //$NON-NLS-1$
              + " =?", new Object[]{id_Ergebniseingang, Integer.valueOf(typ), Integer.valueOf(verteilung)}); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

}
