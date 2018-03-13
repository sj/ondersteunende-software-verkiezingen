package de.ivu.wahl.modell.ejb;

import static de.ivu.ejb.fw.DBABase.retrieveIDs;
import static de.ivu.wahl.modell.impl.SchwellwertDBA.ID_SCHWELLWERT;
import static de.ivu.wahl.modell.impl.SchwellwertDBA.ID_WAHL;
import static de.ivu.wahl.modell.impl.SchwellwertDBA.NAME;
import static de.ivu.wahl.modell.impl.SchwellwertDBA.TABLENAME;

import java.sql.SQLException;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;

import org.apache.log4j.Category;

import de.ivu.ejb.IVUFinderException;
import de.ivu.util.debug.Log4J;

/**
 * SchwellwertBean
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
public class SchwellwertBean extends BasicSchwellwertBean {
  private static final long serialVersionUID = 1884422045992527943L;
  private static final Category LOGGER = Log4J.configure(SchwellwertBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(SchwellwertBean.class, Log4J.extractVersion("$Revision: 1.7 $"))); //$NON-NLS-1$
  }

  /**
   * Find threshold by election and name
   * 
   * @param id_Wahl
   * @param name
   * @return
   * @throws ObjectNotFoundException
   * @throws FinderException
   */
  public String ejbFindByWahlAndName(String id_Wahl, String name)
      throws ObjectNotFoundException, FinderException {
    try {
      // Sondernutzung der GG Wahlberechtigte für allgemeine Schwellwerte !
      return findSingle(retrieveIDs("select " + ID_SCHWELLWERT + " from " + TABLENAME + " where " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ID_WAHL + "=?  and " + NAME + "=?", new Object[]{id_Wahl, name})); //$NON-NLS-1$ //$NON-NLS-2$
    } catch (SQLException se) {
      throw new IVUFinderException(se.getMessage(), se);
    }
  }

  /**
   * Find threshold by group, region and name
   * 
   * @param id_Gruppe
   * @param id_Gebiet
   * @param name
   * @return
   * @throws FinderException
   */
  public String ejbFindByGruppeAndGebietAndName(String id_Gruppe, String id_Gebiet, String name)
      throws FinderException {
    throw new RuntimeException("not implemented"); //$NON-NLS-1$
    // try {
    // return findSingle(DBABase.retrieveIDs("select ID_Schwellwert from "
    // + SchwellwertDBA.TABLENAME + " s," + GruppeGebietsspezifischDBA.TABLENAME + " gg"
    // + " where gg.ID_Gruppe=? and gg.ID_Gebiet=? and s.Name=?"
    // + " and s.ID_GruppeGebietsspezifisch=gg.ID_GruppeGebietsspezifisch", new Object[]{
    // id_Gruppe, id_Gebiet, name}));
    // } catch (SQLException se) {
    // throw new IVUFinderException(se.getMessage(), se);
    // }
  }

  /*
   * (non-Javadoc) Get kind of treshold
   * @see de.ivu.wahl.modell.SchwellwertModel#getArtSchwellwert(java.lang.String)
   */
  public int getArtSchwellwert(String schwellwertKey) {
    return _details.getArtSchwellwert(schwellwertKey);
  }

  /*
   * (non-Javadoc) Get plaintext of treshold
   * @see de.ivu.wahl.modell.SchwellwertModel#getKlartextSchwellwert(java.lang.String)
   */
  public String getKlartextSchwellwert(String schwellwertKey) {
    return _details.getKlartextSchwellwert(schwellwertKey);
  }
}