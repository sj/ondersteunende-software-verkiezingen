package de.ivu.wahl.modell.ejb;

/**
 * ErgebniseingangBean
 * 
 * @author cos@ivu.de (c) 2003 Statistisches Bundesamt und IVU Traffic Technologies AG
 */

import static de.ivu.ejb.fw.DBABase.count;
import static de.ivu.ejb.fw.DBABase.retrieveIDs;
import static de.ivu.wahl.modell.ErgebniseingangKonstanten.STATE_OK;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.ERGEBNISHASH;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.ID_ERFASSUNGSEINHEIT;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.ID_ERGEBNISEINGANG;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.ID_ERGEBNISEINGANG_QUAL;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.ID_WAHL;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.ID_WAHL_QUAL;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.STATUS;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.TABLENAME;
import static de.ivu.wahl.modell.impl.ErgebniseingangDBA.WAHLERGEBNISART;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.impl.BasicGebietDBA;
import de.ivu.wahl.modell.impl.ErgebniseingangDBA;
import de.ivu.wahl.modell.impl.Gebiet_ErgebniseingangDBA;

/**
 * @author SMA@ivu.de, IVU Traffic Technologies AG
 */
public class ErgebniseingangBean extends BasicErgebniseingangBean {
  private static final long serialVersionUID = -8264874589769938537L;

  private static final Category LOGGER = Log4J.configure(ErgebniseingangBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(ErgebniseingangBean.class, Log4J
        .extractVersion("$Revision: 1.20 $"))); //$NON-NLS-1$
  }

  /**
   * Find by election and kind of election result
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart)
      throws FinderException {

    try {
      return retrieveIDs("select ID_Ergebniseingang from " + TABLENAME //$NON-NLS-1$
          + " where ID_Wahl=? and Wahlergebnisart=?", new Object[]{id_Wahl, wahlergebnisart}); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * @return
   * @throws FinderException
   */
  public List<String> ejbFindAllByIDErfassungseinheit(String id_erfassungseinheit)
      throws FinderException {
    try {
      return retrieveIDs("select " + ID_ERGEBNISEINGANG + " from " + TABLENAME //$NON-NLS-1$//$NON-NLS-2$
          + " where " + ID_ERFASSUNGSEINHEIT + " = ?", new Object[]{id_erfassungseinheit}); //$NON-NLS-1$//$NON-NLS-2$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find by region and kind of election
   * 
   * @param id_Gebiet
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  public String ejbFindByGebietAndWahlergebnisart(String id_Gebiet, int wahlergebnisart)
      throws FinderException {

    try {
      Collection<String> ids = retrieveIDs("select " //$NON-NLS-1$
          + Gebiet_ErgebniseingangDBA.ID_ERGEBNISEINGANG_QUAL + " from " //$NON-NLS-1$
          + Gebiet_ErgebniseingangDBA.TABLENAME + " left outer join " + TABLENAME + " on(" //$NON-NLS-1$ //$NON-NLS-2$
          + Gebiet_ErgebniseingangDBA.ID_ERGEBNISEINGANG_QUAL + "=" //$NON-NLS-1$
          + ID_ERGEBNISEINGANG_QUAL + ") where " //$NON-NLS-1$
          + Gebiet_ErgebniseingangDBA.ID_GEBIET + "=? and " + WAHLERGEBNISART + "=?", new Object[]{ //$NON-NLS-1$ //$NON-NLS-2$
          id_Gebiet, wahlergebnisart});

      int len = ids.size();
      if (len == 0) {
        throw new ObjectNotFoundException(Messages
            .bind(MessageKeys.Error_0_wurdeNichtGefunden_id_Gebiet_1_wahlergebnisart_2, getClass()
                .getName(), id_Gebiet, wahlergebnisart));
      }
      if (len > 1) {
        throw new FinderException(Messages
            .bind(MessageKeys.Error_0_istNichtEindeutigBestimmt_id_Gebiet_1_wahlergebnisart_2,
                getClass().getName(),
                id_Gebiet,
                wahlergebnisart));
      }
      return ids.iterator().next();
    } catch (SQLException se) {
      throw new IVUFinderException(Messages.bind(MessageKeys.Error_0_id_Gebiet_1_wahlergebnisart_2,
          se.getMessage(),
          id_Gebiet,
          wahlergebnisart), se);
    }
  }

  /**
   * Find all by kind of election and region, sort by order of results
   * 
   * @param wahlergebnisart
   * @param id_Gebiet
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByWahlergebnisartAndGebietSortByEingangsreihenfolge(int wahlergebnisart,
      String id_Gebiet) throws FinderException {

    try {
      return retrieveIDs(" select " + ID_ERGEBNISEINGANG + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + WAHLERGEBNISART + " = ?" + " and " + ID_ERFASSUNGSEINHEIT + " = ?" + " order by " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          + ID_ERGEBNISEINGANG + " asc", new Object[]{wahlergebnisart, id_Gebiet}); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find inputs with same hash code
   * 
   * @param hash
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByErgebnisHashAndLastValidInput(String hash)
      throws FinderException {
    try {
      return retrieveIDs("select " + ID_ERGEBNISEINGANG + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where " + ERGEBNISHASH + "=? and " + ID_ERGEBNISEINGANG + " in (select max(" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
          + ID_ERGEBNISEINGANG + ") from " + TABLENAME + " where " + STATUS + "=" + STATE_OK //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + " group by " + ID_ERFASSUNGSEINHEIT + ")", new Object[]{ //$NON-NLS-1$ //$NON-NLS-2$ 
          hash});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find last result of election with given kind of election result
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  public String ejbFindLetzterByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart)
      throws ObjectNotFoundException, FinderException {

    try {
      return findSingle(retrieveIDs("select " + ID_ERGEBNISEINGANG + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where " + ID_ERGEBNISEINGANG + "=(select max(" + ID_ERGEBNISEINGANG + ") from " //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
          + TABLENAME + " where " + ID_WAHL + "=? and " + WAHLERGEBNISART + "=?)", new Object[]{ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          id_Wahl, wahlergebnisart}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find last valid first input by region
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  public String ejbFindLastValidFirstInputByGebietAndWahlergebnisart(String id_Gebiet,
      int wahlergebnisart) throws ObjectNotFoundException, FinderException {
    try {
      return findSingle(retrieveIDs("select max(" + ID_ERGEBNISEINGANG + ") from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where " + ID_ERFASSUNGSEINHEIT + "=? and " + ErgebniseingangDBA.WAHLERGEBNISART + "=? and " + ErgebniseingangDBA.STATUS + "=" + ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK, new Object[]{ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
          id_Gebiet, new Integer(wahlergebnisart)}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find last input by region and input type
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  public String ejbFindLastInputByGebietAndWahlergebnisart(String id_Gebiet, int wahlergebnisart)
      throws ObjectNotFoundException, FinderException {
    try {
      return findSingle(retrieveIDs("select max(" + ID_ERGEBNISEINGANG + ") from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where " + ID_ERFASSUNGSEINHEIT + "=? and " + ErgebniseingangDBA.WAHLERGEBNISART + "=?", new Object[]{ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          id_Gebiet, new Integer(wahlergebnisart)}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find last valid result with given kind of election results
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws FinderException
   */
  public String ejbFindLetzterGueltigerByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart)
      throws ObjectNotFoundException, FinderException {

    try {
      return findSingle(retrieveIDs("select " + ID_ERGEBNISEINGANG + " from " + TABLENAME //$NON-NLS-1$ //$NON-NLS-2$
          + " where " + ID_ERGEBNISEINGANG + "=(select max(" + ID_ERGEBNISEINGANG_QUAL + ") from " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + TABLENAME + " join " + Gebiet_ErgebniseingangDBA.TABLENAME + " using(" //$NON-NLS-1$ //$NON-NLS-2$
          + ID_ERGEBNISEINGANG + ") join " + BasicGebietDBA.TABLENAME + " using(" //$NON-NLS-1$ //$NON-NLS-2$
          + Gebiet_ErgebniseingangDBA.ID_GEBIET + ") where " + ID_WAHL_QUAL + "=? and " //$NON-NLS-1$ //$NON-NLS-2$
          + WAHLERGEBNISART + "=?)", new Object[]{id_Wahl, wahlergebnisart})); //$NON-NLS-1$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Get back the number of results for an election and a kind of election
   * 
   * @param id_Wahl
   * @param wahlergebnisart
   * @return
   * @throws EJBException
   */
  public int ejbHomeCountByWahlAndWahlergebnisart(String id_Wahl, int wahlergebnisart)
      throws EJBException {

    try {
      return count("select count(" + ID_ERGEBNISEINGANG + ") from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_WAHL + "='" + id_Wahl + "' and " + WAHLERGEBNISART + "=" + wahlergebnisart); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } catch (SQLException se) {
      throw new EJBException(se);
    }
  }

}