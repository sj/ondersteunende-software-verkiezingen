/*
 * SchwellwertHandlingBean
 * 
 * Created on 12.09.2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import static de.ivu.wahl.modell.SchwellwertModel.SWERT_LEER;
import static de.ivu.wahl.modell.SchwellwertModel.SWERT_UNGUELTIGE;
import static de.ivu.wahl.modell.SchwellwertModel.SWERT_WAHLBERECHTIGTE_HOCH;
import static de.ivu.wahl.modell.SchwellwertModel.SWERT_WAHLBERECHTIGTE_NIEDRIG;
import static de.ivu.wahl.modell.SchwellwertModel.SWERT_WAHLBETEILIGUNG_HOCH;
import static de.ivu.wahl.modell.SchwellwertModel.SWERT_WAHLBETEILIGUNG_NIEDRIG;

import java.math.BigDecimal;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ejb.Schwellwert;
import de.ivu.wahl.modell.ejb.SchwellwertHome;

/**
 * Session Bean f�r die Manipulation der Schwellwerte
 * 
 * @author cos@ivu.de, IVU Traffic Technologies AG
 */
@Stateless
@Local(SchwellwertHandling.class)
public class SchwellwertHandlingBean extends WahlStatelessSessionBeanBase
    implements
      SchwellwertHandling {

  private static final long serialVersionUID = 7736482281411444271L;

  private static final Category LOGGER = Log4J.configure(SchwellwertHandlingBean.class);

  static {
    LOGGER.info(Log4J.dumpVersion(SchwellwertHandlingBean.class, Log4J
        .extractVersion("$Revision: 1.10 $"))); //$NON-NLS-1$
  }

  @EJB
  private StateHandling _stateHandling;

  public int getSchwellwert(AnwContext c, String name) throws EJBException {
    try {
      Schwellwert sw = SchwellwertHome.HomeFinder.findHome(this).findByWahlAndName(c.getID_Wahl(),
          name);
      return sw.getWert().intValue();
    } catch (Exception e) {
      throw new EJBException(Messages.bind(MessageKeys.Error_Schwellwert_0_NichtGefunden, name));
    }
  }

  public void removeSchwellwert(AnwContext c, String name, String id_Partei) throws EJBException {
    try {
      WahlInfo.getWahlInfo(c).getWahl().readLock();
      String id_Gebiet = getID_Wurzelgebiet(c);
      SchwellwertHome.HomeFinder.findHome(this).findByGruppeAndGebietAndName(id_Partei,
          id_Gebiet,
          name).remove();
      writeAppLog(c, Messages.bind(MessageKeys.Error_Schwellwert_0_FuerPartei_1_in_2_geloescht,
          name,
          id_Partei,
          id_Gebiet));
      _stateHandling.setLastChangeNow(c);
    } catch (FinderException e) {
      LOGGER.info(Messages
          .bind(MessageKeys.Error_Schwellwert_0_nichtGefundenKannNichtGeloeschtWerden, name));
    } catch (RemoveException e) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimLoeschen), e);
    }
  }

  /**
   * Setzt den angegebenen Schwellwert, welcher f�r die gesammte Wahl gilt
   * 
   * @param c Anwenderkontext
   * @param schwellwertKey Bezeichnung des Schwellwerts
   * @param wert Wert
   * @throws EJBException
   */
  public void setSchwellwert(AnwContext c, String schwellwertKey, int wert) throws EJBException {

    try {
      Schwellwert sw;
      try {
        sw = SchwellwertHome.HomeFinder.findHome(this).findByWahlAndName(c.getID_Wahl(),
            schwellwertKey);
      } catch (Exception e) {
        LOGGER.info(Messages.bind(MessageKeys.Error_Schwellwert_0_WirdNeuAngelegt, schwellwertKey));
        sw = SchwellwertHome.HomeFinder.findHome(this).create();
      }
      sw.setName(schwellwertKey);
      sw.setWert(new BigDecimal(wert));
      int schwellwertart = sw.getArtSchwellwert(schwellwertKey);
      sw.setSchwellwertart(schwellwertart);
      sw.setID_Wahl(c.getID_Wahl());
      writeAppLog(c, Messages.bind(MessageKeys.Error_Schwellwert_0_auf_1_geaendert,
          schwellwertKey,
          wert));
    } catch (CreateException ce) {
      throw new EJBException(Messages.getString(MessageKeys.Error_FehlerBeimAnlegen), ce);
    }
  }

  /*
   * (non-Javadoc)
   * @see de.ivu.wahl.admin.SchwellwertHandling#initSchwellwerte(de.ivu.wahl.AnwContext)
   */
  public void initSchwellwerte(AnwContext c) throws EJBException {
    int wahlberecht_niedrig = 110000;
    int wahlberecht_hoch = 250000;
    int wahlbetei_niedrig = 20;
    int wahlbetei_hoch = 95;
    int unguelt = 5;
    int leer = 5;
    setSchwellwert(c, SWERT_WAHLBERECHTIGTE_NIEDRIG, wahlberecht_niedrig);
    setSchwellwert(c, SWERT_WAHLBERECHTIGTE_HOCH, wahlberecht_hoch);
    setSchwellwert(c, SWERT_WAHLBETEILIGUNG_NIEDRIG, wahlbetei_niedrig);
    setSchwellwert(c, SWERT_WAHLBETEILIGUNG_HOCH, wahlbetei_hoch);
    setSchwellwert(c, SWERT_UNGUELTIGE, unguelt);
    setSchwellwert(c, SWERT_LEER, leer);
  }

  /**
   * @param c Anwenderkontext
   * @return ID des (Gesamt-)Gebietes, auf dem die Wahl/Wahlen veranstaltet wird/werden
   * @throws EJBException genereller Fehler
   */
  private String getID_Wurzelgebiet(AnwContext c) throws EJBException {
    return WahlInfo.getWahlInfo(c).getWahl().getWurzelgebiet().getID_Gebiet();
  }
}
