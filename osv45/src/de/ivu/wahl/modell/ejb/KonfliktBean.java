package de.ivu.wahl.modell.ejb;

/**
 * KonfliktBean
 *
 * @author cos@ivu.de  (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.impl.KonfliktDBA;

public class KonfliktBean extends BasicKonfliktBean {
  private static final long serialVersionUID = -968693252508300120L;

  private static final Category LOGGER = Log4J.configure(KonfliktBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(KonfliktBean.class, Log4J.extractVersion("$Revision: 1.6 $"))); //$NON-NLS-1$
  }

  /**
   * Find all actual conflicts of the election for the given kind of election result
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart)
      throws FinderException {
    try {
      return DBABase.retrieveIDs("select ID_Konflikt from " + KonfliktDBA.TABLENAME //$NON-NLS-1$
          + " where ID_Wahl=? and Wahlergebnisart=?", new Object[]{id_Wahl, //$NON-NLS-1$
          new Integer(wahlergebnisart)});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all by result and kind
   * 
   * @param id_Ergebniseingang
   * @param art
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByErgebniseingangAndArt(String id_Ergebniseingang, int art)
      throws FinderException {
    try {
      return DBABase.retrieveIDs(" select " + KonfliktDBA.ID_KONFLIKT + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + KonfliktDBA.TABLENAME + " where " + KonfliktDBA.ID_ERGEBNISEINGANG + "=? and " //$NON-NLS-1$ //$NON-NLS-2$
          + KonfliktDBA.KONFLIKTART + "=? order by " + KonfliktDBA.NUMMER, new Object[]{ //$NON-NLS-1$
          id_Ergebniseingang, new Integer(art)});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find all by result, ordered by number
   * 
   * @param id_Ergebniseingang
   * @return
   * @throws IVUFinderException
   */
  public Collection<String> ejbFindAllByErgebniseingangOrderByNummer(String id_Ergebniseingang)
      throws IVUFinderException {
    try {
      return DBABase.retrieveIDs(" select " + KonfliktDBA.ID_KONFLIKT + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + KonfliktDBA.TABLENAME + " where " + KonfliktDBA.ID_ERGEBNISEINGANG + "=? order by " //$NON-NLS-1$ //$NON-NLS-2$
          + KonfliktDBA.NUMMER, new Object[]{id_Ergebniseingang});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }
}
