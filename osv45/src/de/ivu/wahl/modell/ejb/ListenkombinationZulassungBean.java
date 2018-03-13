package de.ivu.wahl.modell.ejb;

/**
 * ListenkombinationZulassungBean
 *
 * @author cos@ivu.de  (c) 2003-7 Statistisches Bundesamt und IVU Traffic Technologies AG

 */

import static de.ivu.ejb.fw.DBABase.retrieveIDs;

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.impl.ListenkombinationZulassungDBA;

public class ListenkombinationZulassungBean extends BasicListenkombinationZulassungBean {
  /** long */
  private static final long serialVersionUID = 8672163305750770692L;
  private static final Category LOGGER = Log4J.configure(ListenkombinationZulassungBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(ListenkombinationZulassungBean.class, Log4J
        .extractVersion("$Revision$"))); //$NON-NLS-1$
  }

  /**
   * @param id_Ergebniseingang
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByErgebniseingangAndZugelassen(String id_Ergebniseingang,
      boolean zugelassen) throws FinderException {

    try {
      String query = "select " + ListenkombinationZulassungDBA.ID_LISTENKOMBINATIONZULASSUNG //$NON-NLS-1$
          + " from " + ListenkombinationZulassungDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
          + ListenkombinationZulassungDBA.ID_ERGEBNISEINGANG_QUAL + " = ? and " //$NON-NLS-1$
          + ListenkombinationZulassungDBA.ZUGELASSEN + " = ?"; //$NON-NLS-1$
      return retrieveIDs(query, new Object[]{id_Ergebniseingang, zugelassen});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }
}
