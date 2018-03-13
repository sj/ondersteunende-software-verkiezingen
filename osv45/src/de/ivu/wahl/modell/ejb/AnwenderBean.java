package de.ivu.wahl.modell.ejb;

/**
 * AnwenderBean
 */

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.GebietHierarchie;
import de.ivu.wahl.GebietHierarchie.GebietKey;
import de.ivu.wahl.modell.impl.AnwenderDBA;

/**
 * @author SMA@ivu.de, IVU Traffic Technologies AG
 */
public class AnwenderBean extends BasicAnwenderBean {
  private static final long serialVersionUID = 6933624606890668681L;

  private static final Category LOGGER = Log4J.configure(AnwenderBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(AnwenderBean.class, Log4J.extractVersion("$Revision: 1.5 $"))); //$NON-NLS-1$
  }

  /**
   * find by user name and password hash
   * 
   * @param anwendername
   * @param passwordHash
   * @return user with all passed values
   * @throws FinderException
   */
  public String ejbFindByAnwendernameAndPasswordHash(String anwendername, String passwordHash)
      throws FinderException {

    try {
      return findSingle(DBABase.retrieveIDs("select " + AnwenderDBA.ID_ANWENDER + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + AnwenderDBA.TABLENAME + " where " + AnwenderDBA.ANWENDERNAME + "=? " + " and " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + AnwenderDBA.PASSWORDHASH + "=?", new Object[]{anwendername, passwordHash})); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * find by user name
   * 
   * @param anwendername
   * @return
   * @throws FinderException
   */
  public String ejbFindByAnwendername(String anwendername) throws FinderException {
    try {
      return findSingle(AnwenderDBA.retrieveIDsByAnwendername(anwendername));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Collection of all found users is given back
   * 
   * @return all users
   * @throws FinderException when problem by searching
   */
  @Override
  public Collection<String> ejbFindAll() throws IVUFinderException {

    try {
      return DBABase.retrieveIDs("select " + AnwenderDBA.ID_ANWENDER + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + AnwenderDBA.TABLENAME + " order by " + AnwenderDBA.ANWENDERNAME); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * find all users by region or child region
   * 
   * @param id_Gebiet
   * @param anwWithNull
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllVonGebietUndKindergebieten(String id_Gebiet,
      boolean anwWithNull) throws FinderException {

    Collection<GebietKey> gebietKeys = GebietHierarchie.getAlleGebiete(id_Gebiet);
    try {
      boolean gebieteVorhanden = !gebietKeys.isEmpty();
      StringBuilder where = new StringBuilder();
      if (gebieteVorhanden) {
        StringBuilder whereIn = new StringBuilder();
        char quote = '\'';
        for (Iterator<GebietKey> keyItr = gebietKeys.iterator(); keyItr.hasNext();) {
          GebietHierarchie.GebietKey key = keyItr.next();
          whereIn.append(quote).append(key.getID_Gebiet()).append(quote).append(keyItr.hasNext()
              ? ", " //$NON-NLS-1$
              : ""); //$NON-NLS-1$
        }
        where.append(AnwenderDBA.ID_GEBIET).append(" in (").append(whereIn).append(") "); //$NON-NLS-1$ //$NON-NLS-2$
      }
      if (anwWithNull) {
        if (gebieteVorhanden) {
          where.append(" or "); //$NON-NLS-1$
        }
        where.append(AnwenderDBA.ID_GEBIET).append(" is null "); //$NON-NLS-1$
      }
      return DBABase.retrieveIDs("select " + AnwenderDBA.ID_ANWENDER + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + AnwenderDBA.TABLENAME + " where " + where + " order by " + AnwenderDBA.ANWENDERNAME); //$NON-NLS-1$ //$NON-NLS-2$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }
}
