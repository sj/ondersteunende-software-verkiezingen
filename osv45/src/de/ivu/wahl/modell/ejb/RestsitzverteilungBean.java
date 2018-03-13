package de.ivu.wahl.modell.ejb;

/**
 * RestsitzverteilungBean
 *
 * @author jon@ivu.de  (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.impl.RestsitzverteilungDBA;
import de.ivu.wahl.result.result.Distribution;

public class RestsitzverteilungBean extends BasicRestsitzverteilungBean {
  private static final long serialVersionUID = 2229891945195092102L;
  private static final Category LOGGER = Log4J.configure(RestsitzverteilungBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(RestsitzverteilungBean.class, Log4J
        .extractVersion("$Revision: 1.2 $"))); //$NON-NLS-1$
  }

  public Collection<String> ejbFindAllForP42Distribution(String id_Ergebniseingang)
      throws FinderException {
    try {
      return DBABase.retrieveIDs("select " + RestsitzverteilungDBA.ID_RESTSITZVERTEILUNG //$NON-NLS-1$
          + " from " + RestsitzverteilungDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
          + RestsitzverteilungDBA.ID_ERGEBNISEINGANG + "=? and " //$NON-NLS-1$
          + RestsitzverteilungDBA.VERTEILUNG + "=? ", //$NON-NLS-1$
          new Object[]{id_Ergebniseingang, Integer.valueOf(Distribution.P42.getId())});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  public Collection<String> ejbFindAllByListenkombination(String id_Ergebniseingang,
      String id_Listenkombination) throws FinderException {
    try {
      return DBABase.retrieveIDs("select " + RestsitzverteilungDBA.ID_RESTSITZVERTEILUNG //$NON-NLS-1$
          + " from " + RestsitzverteilungDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
          + RestsitzverteilungDBA.ID_ERGEBNISEINGANG + "=? and " //$NON-NLS-1$
          + RestsitzverteilungDBA.ID_LISTENKOMBINATION + "=? and " //$NON-NLS-1$
          + RestsitzverteilungDBA.VERTEILUNG + "=? ", //$NON-NLS-1$
          new Object[]{id_Ergebniseingang, id_Listenkombination,
              Integer.valueOf(Distribution.P3.getId())});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  public Collection<String> ejbFindAllByGruppe(String id_Ergebniseingang, String id_Gruppe)
      throws FinderException {
    try {
      return DBABase.retrieveIDs("select " + RestsitzverteilungDBA.ID_RESTSITZVERTEILUNG //$NON-NLS-1$
          + " from " + RestsitzverteilungDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
          + RestsitzverteilungDBA.ID_ERGEBNISEINGANG + "=? and " //$NON-NLS-1$
          + RestsitzverteilungDBA.ID_GRUPPE + "=? and " //$NON-NLS-1$
          + RestsitzverteilungDBA.VERTEILUNG + "=? ", //$NON-NLS-1$
          new Object[]{id_Ergebniseingang, id_Gruppe, Integer.valueOf(Distribution.P2.getId())});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }
}
