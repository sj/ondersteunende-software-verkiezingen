package de.ivu.wahl.modell.ejb;

/**
 * WahlBean
 */

import static de.ivu.wahl.client.util.ClientHelper.formatDateForExport;
import static java.util.Calendar.YEAR;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.ejb.EJBException;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;

public class WahlBean extends BasicWahlBean {
  private static final long serialVersionUID = 1210830990581403362L;

  private static final Category LOGGER = Log4J.configure(WahlBean.class);
  static {
    LOGGER.info(Log4J.dumpVersion(WahlBean.class, Log4J.extractVersion("$Revision: 1.7 $"))); //$NON-NLS-1$
  }

  /**
   * Lock the election for reading and writing access
   */
  public void lock() {
    // tut nichts, außer dem AppServer zu sagen, dass Wahl in der TX gesperrt werden soll
  }

  /**
   * Lock the election for writing access only
   */
  public void readLock() {
    // tut nichts, außer dem AppServer zu sagen, dass für die Wahl nachfolgende Schreibzugriffe aus
    // anderer TX gesperrt werden sollen
  }

  /**
   * Get year of the election for the given kind of election result
   * 
   * @param wahlergebnisart
   * @return
   * @throws EJBException
   */
  public int getWahljahr() throws EJBException {
    Timestamp termin = getTermin();
    if (termin != null) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(termin);
      return cal.get(YEAR);
    }
    return 0;
  }

  /**
   * Get election date for the given kind of period for this election
   * 
   * @param wahlperiodenart
   * @return
   * @throws EJBException
   */
  public String getWahltermin() throws EJBException {
    Timestamp termin = getTermin();
    return termin != null ? formatDateForExport(termin) : null;
  }

  /**
   * Check if election for actual kind of election result is unlocked
   * 
   * @return
   */
  public boolean isFreigegeben() {
    return getFreigegeben() != null;
  }

  /**
   * Short election identifier
   * 
   * @return
   */
  public String getNameKurz() {
    return getID_Wahl();
  }

}