package de.ivu.wahl.modell.ejb;

/**
 * SitzverteilungBean
 *
 * @author cos@ivu.de  (c) 2003-5 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.modell.impl.SitzverteilungDBA;

public class SitzverteilungBean extends BasicSitzverteilungBean {
  private static final long serialVersionUID = -846334887870677969L;
  private static final Category LOGGER = Log4J.configure(SitzverteilungBean.class);

  static {
    LOGGER.info(Log4J.dumpVersion(SitzverteilungBean.class, Log4J
        .extractVersion("$Revision: 1.13 $"))); //$NON-NLS-1$
  }

  /**
   * Find seat distribution by result and list
   * 
   * @param id_Ergebniseingang
   * @param id_Liste
   * @return
   * @throws FinderException
   */
  public String ejbFindByErgebniseingangAndListe(String id_Ergebniseingang, String id_Liste)
      throws FinderException {
    try {
      return findSingle(DBABase.retrieveIDs("select " + SitzverteilungDBA.ID_SITZVERTEILUNG //$NON-NLS-1$
          + " from " + SitzverteilungDBA.TABLENAME + " where " //$NON-NLS-1$//$NON-NLS-2$
          + SitzverteilungDBA.ID_ERGEBNISEINGANG + "=? and " + SitzverteilungDBA.ID_LISTE + "=?", //$NON-NLS-1$ //$NON-NLS-2$
          new Object[]{id_Ergebniseingang, id_Liste}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find seat distribution by result and list combination with the sum of the list combination
   * 
   * @param id_Ergebniseingang
   * @param id_Listenkombination
   * @param withListenkombinationsumme
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByErgebniseingangAndListenkombinationWithLKSumme(String id_Ergebniseingang,
      String id_Listenkombination,
      boolean withListenkombinationsumme) throws FinderException {
    try {
      return DBABase.retrieveIDs("select " //$NON-NLS-1$
          + SitzverteilungDBA.ID_SITZVERTEILUNG_QUAL + " from " //$NON-NLS-1$
          + SitzverteilungDBA.TABLENAME + " where " //$NON-NLS-1$
          + SitzverteilungDBA.ID_ERGEBNISEINGANG_QUAL + "=? and " //$NON-NLS-1$
          + SitzverteilungDBA.ID_LISTENKOMBINATION_QUAL + "=? and " //$NON-NLS-1$
          + SitzverteilungDBA.ID_LISTE_QUAL + " is null " //$NON-NLS-1$
          + (withListenkombinationsumme ? "" : " and " + SitzverteilungDBA.ID_GRUPPE_QUAL //$NON-NLS-1$ //$NON-NLS-2$
              + " is not NULL "), new Object[]{id_Ergebniseingang, id_Listenkombination}); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }
}
