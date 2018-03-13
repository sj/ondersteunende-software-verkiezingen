package de.ivu.wahl.modell.ejb;

/**
 * GebietsstatusBean
 */

import java.sql.SQLException;
import java.util.Collection;

import javax.ejb.EJBException;
import javax.ejb.FinderException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.ejb.fw.DBABase;
import de.ivu.util.debug.Log4J;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.modell.impl.BasicGebietDBA;
import de.ivu.wahl.modell.impl.BasicGebiet_GebietDBA;
import de.ivu.wahl.modell.impl.BasicGebietsstatusDBA;

public class GebietsstatusBean extends BasicGebietsstatusBean {
  private static final long serialVersionUID = -3078953810941245817L;

  private static final Category LOGGER = Log4J.configure(GebietsstatusBean.class);
  static {
    LOGGER.info(Log4J
        .dumpVersion(GebietsstatusBean.class, Log4J.extractVersion("$Revision: 1.4 $"))); //$NON-NLS-1$
  }

  /**
   * Find all region states of the region by kind of election result and region
   * 
   * @param wahlergebnisart
   * @param id_Gebiet
   * @return
   * @throws FinderException
   */
  public Collection<String> ejbFindAllByWahlergebnisartAndGebiet(int wahlergebnisart,
      String id_Gebiet) throws FinderException {
    try {
      return DBABase.retrieveIDs("select " + BasicGebietsstatusDBA.ID_GEBIETSSTATUS + " from " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.TABLENAME + " where " + BasicGebietsstatusDBA.WAHLERGEBNISART //$NON-NLS-1$
          + "=? and " + BasicGebietsstatusDBA.ID_GEBIET + "=?", new Object[]{wahlergebnisart, //$NON-NLS-1$//$NON-NLS-2$
          id_Gebiet});
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find a region's state, that was valid at the moment of the given result
   * 
   * @param id_Ergebniseingang
   * @param id_Gebiet
   * @return
   * @throws FinderException
   */
  public String ejbFindByErgebniseingangAndGebiet(String id_Ergebniseingang, String id_Gebiet)
      throws FinderException {
    try {
      return findSingle(DBABase.retrieveIDs("select ID_Gebietsstatus from " //$NON-NLS-1$
          + BasicGebietsstatusDBA.TABLENAME + " where ID_Ergebniseingang=? and ID_Gebiet=?", //$NON-NLS-1$
          new Object[]{id_Ergebniseingang, id_Gebiet}));
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  @Override
  protected void resetRelations() {
    super.resetRelations();
  }

  /**
   * Get the number of incoming detect units
   * 
   * @return
   * @throws EJBException
   */
  public int getAnzahlErfassungseinheitenEingenangen() throws EJBException {
    try {
      return DBABase.count("select count(*) from " + BasicGebietsstatusDBA.TABLENAME + " gs, " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebiet_GebietDBA.TABLENAME + " where " + BasicGebiet_GebietDBA.ID_ELTERNGEBIET //$NON-NLS-1$
          + "=? and " + BasicGebietsstatusDBA.ID_GEBIET + "=" //$NON-NLS-1$//$NON-NLS-2$
          + BasicGebiet_GebietDBA.ID_UNTERGEBIET + " and " + BasicGebietsstatusDBA.VOLLSTAENDIG //$NON-NLS-1$
          + "=" + Konstanten.DB_TRUE + " and " + BasicGebietsstatusDBA.ID_ERGEBNISEINGANG //$NON-NLS-1$//$NON-NLS-2$
          + "=(select max(" + BasicGebietsstatusDBA.ID_ERGEBNISEINGANG + ") from " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.TABLENAME + ", " + BasicGebietDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.WAHLERGEBNISART + "=? and " + BasicGebietDBA.ERFASSUNGSEINHEIT //$NON-NLS-1$
          + "=" + Konstanten.DB_TRUE + " and " + BasicGebietsstatusDBA.ID_GEBIET_QUAL + " =gs." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + BasicGebietsstatusDBA.ID_GEBIET + " and " + BasicGebietDBA.ID_GEBIET_QUAL + "=gs." //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.ID_GEBIET + " and " + BasicGebietsstatusDBA.ID_ERGEBNISEINGANG //$NON-NLS-1$
          + "<=? group by " + BasicGebietDBA.ERFASSUNGSEINHEIT + ")", new Object[]{getID_Gebiet(), //$NON-NLS-1$ //$NON-NLS-2$
          getWahlergebnisart(), getID_Ergebniseingang()});
    } catch (SQLException se) {
      throw new EJBException(se);
    }
  }

  /**
   * Get the number of incoming by kind of region
   * 
   * @param gebietsart
   * @return
   * @throws EJBException
   */
  public int getAnzahlEingegangenByGebietsart(int gebietsart) throws EJBException {
    try {
      return DBABase.count("select count(*) from " + BasicGebietsstatusDBA.TABLENAME + " gs, " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebiet_GebietDBA.TABLENAME + " where " + BasicGebiet_GebietDBA.ID_ELTERNGEBIET //$NON-NLS-1$
          + "=? and " + BasicGebietsstatusDBA.ID_GEBIET + "=" //$NON-NLS-1$//$NON-NLS-2$
          + BasicGebiet_GebietDBA.ID_UNTERGEBIET + " and " + BasicGebietsstatusDBA.VOLLSTAENDIG //$NON-NLS-1$
          + "=" + Konstanten.DB_TRUE + " and " + BasicGebietsstatusDBA.ID_ERGEBNISEINGANG //$NON-NLS-1$//$NON-NLS-2$
          + "=(select max(" + BasicGebietsstatusDBA.ID_ERGEBNISEINGANG + ") from " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.TABLENAME + ", " + BasicGebietDBA.TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.WAHLERGEBNISART + "=? and " + BasicGebietDBA.GEBIETSART //$NON-NLS-1$
          + "=? and " + BasicGebietsstatusDBA.ID_GEBIET_QUAL + " =gs." //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.ID_GEBIET + " and " + BasicGebietDBA.ID_GEBIET_QUAL + "=gs." //$NON-NLS-1$ //$NON-NLS-2$
          + BasicGebietsstatusDBA.ID_GEBIET + " and " + BasicGebietsstatusDBA.ID_ERGEBNISEINGANG //$NON-NLS-1$
          + "<=? group by " + BasicGebietDBA.GEBIETSART + ")", new Object[]{getID_Gebiet(), //$NON-NLS-1$//$NON-NLS-2$
          new Integer(getWahlergebnisart()), new Integer(gebietsart), getID_Ergebniseingang()});
    } catch (SQLException se) {
      throw new EJBException(se);
    }
  }
}