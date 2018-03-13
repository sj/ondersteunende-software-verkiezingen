package de.ivu.wahl.modell.ejb;

/**
 * RechtBean
 */

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;

public class RechtBean extends BasicRechtBean {
  private static final long serialVersionUID = -6267557935915948832L;

  private final static Category LOGGER = Log4J.configure(RechtBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(RechtBean.class, Log4J.extractVersion("$Revision: 1.3 $"))); //$NON-NLS-1$
  }

  /**
   * Query um alle Rechte eines Anwenders zu ermitteln
   * <p>
   * Dazu wird der join "nach unten" über die Rechtegruppe zu den Rechten gebildet
   * </p>
   */
  private final static String ANW_RIGHT_QUERY = " select Recht.ID_Recht from Recht, Rechtegruppe_Recht, Rechtegruppe_Anwender " //$NON-NLS-1$
      + " where Recht.ID_Recht = Rechtegruppe_Recht.ID_Recht and Rechtegruppe_Recht.ID_Rechtegruppe = Rechtegruppe_Anwender.ID_Rechtegruppe " //$NON-NLS-1$
      + " and Rechtegruppe_Anwender.ID_Anwender =? "; //$NON-NLS-1$

  /**
   * Find all rights of the given user
   * 
   * @param id_Anwender
   * @return
   * @throws FinderException
   * @throws EJBException
   */
  public Collection<String> ejbFindAllByAnwender(String id_Anwender)
      throws FinderException, EJBException {
    try {
      return DBABase.retrieveIDs(ANW_RIGHT_QUERY, new Object[]{id_Anwender});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }
}