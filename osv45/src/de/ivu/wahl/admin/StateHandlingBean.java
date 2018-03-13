/*
 * StateHandlingBean
 * 
 * Created on 12.09.2008
 * Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.admin;

import static de.ivu.wahl.Konstanten.PROP_INPUTDISABLE;
import static java.lang.System.currentTimeMillis;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import org.apache.log4j.Category;

import de.ivu.util.debug.Log4J;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.GebietsBaum;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.WahlStatelessSessionBeanBase;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ejb.Ergebniseingang;
import de.ivu.wahl.modell.ejb.ErgebniseingangHome;
import de.ivu.wahl.modell.ejb.Wahl;

@Stateless
@Local(StateHandling.class)
public class StateHandlingBean extends WahlStatelessSessionBeanBase implements StateHandling {
  private static final long serialVersionUID = 1667557526685140088L;

  private static final Category LOGGER = Log4J.configure(StateHandlingBean.class);

  static {
    LOGGER.info(Log4J.dumpVersion(StateHandlingBean.class, Log4J
        .extractVersion("$Revision: 1.14 $"))); //$NON-NLS-1$
  }

  @EJB
  private PropertyHandling _propertyHandling;

  @EJB
  private StateHandling _stateHandling;

  public long getZeitstempelLetzteAenderung(AnwContext c) {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo(c);
    if (wahlInfo == null) {
      return 0L;
    } else {
      long zeitWahl = wahlInfo.getWahl().getLetzteAenderung().getTime();
      ErgebniseingangHome ergebniseingangHome = ErgebniseingangHome.HomeFinder.findHome(this);
      try {
        final Ergebniseingang ee = ergebniseingangHome.findLetzterByWahlAndWahlergebnisart(wahlInfo
            .getID_Wahl(), wahlInfo.getAktuelleWahlergebnisart());
        final Timestamp zeitstempel = ee.getZeitstempel();
        if (zeitstempel != null) {
          long zeitEE = zeitstempel.getTime();
          if (zeitEE > zeitWahl) {
            zeitWahl = zeitEE;
          }
        } else {
          LOGGER.info("Timestamp not set in result input state " + ee);
        }
      } catch (ObjectNotFoundException e) {
        LOGGER.debug(e);
      } catch (FinderException e) {
        LOGGER.warn(e);
      }
      return zeitWahl;
    }
  }

  public boolean isFreigegeben(AnwContext c, int wahlergebnisart) throws EJBException {
    return WahlInfo.getWahlInfo(c).isFreigegeben();
  }

  public boolean isInputDisabled(AnwContext c) throws EJBException {
    return _propertyHandling.getBooleanProperty(PROP_INPUTDISABLE);
  }

  public boolean isWahlVollstaendig(AnwContext c) throws EJBException {
    return WahlInfo.getWahlInfo(c).isWahlVollstaendig();
  }

  public void setFreigabe(AnwContext c, boolean freigabe) throws EJBException {
    _stateHandling.setFreigabeInternal(c, freigabe);
  }

  @TransactionAttribute(REQUIRES_NEW)
  public boolean setFreigabeInternal(AnwContext c, boolean freigabe) throws EJBException {
    writeAppLog(c, Messages.bind(MessageKeys.Logger_SetzenDerWahlfreigabeFuerArt_0_Auf_1, freigabe));

    // nur was tun, wenn ein neuer Status gesetzt wird
    WahlInfo wahlInfo = WahlInfo.getWahlInfo(c);
    Wahl w = wahlInfo.getWahl();
    if (w.isFreigegeben() != freigabe) {

      // TODO Prüfen, ob in der Wahl bereits ein Ergebnis vorliegt
      if (!wahlInfo.darfWahlFreigegebenWerden() && freigabe) {
        String msg = Messages
            .getString(MessageKeys.Msg_EsWurdeVersuchtDieWahlFreizugebenFreigabeNichtMoeglichDaFuerDieWahlNochKeinErgebnisVorliegt);
        LOGGER.warn(msg);
        writeAppLog(c, msg);
        return false;
      }

      w.setFreigegeben(freigabe ? new Timestamp(currentTimeMillis()) : null);

      writeAppLog(c, (freigabe ? Messages.getString(MessageKeys.Logger_Freigabe) : Messages
          .getString(MessageKeys.Logger_ZuruecknehmenDerFreigabe))
          + Messages.getString(MessageKeys.Logger__derWahl));
      setLastChangeNow(c);
      wahlInfo.synchronize();
      return freigabe;
    } else {
      LOGGER.info(Messages.getString(MessageKeys.WahlzustandFreigabe)
          + (freigabe ? "" : Messages.getString(MessageKeys.Logger__zurueckgenommen)) //$NON-NLS-1$
          + Messages.getString(MessageKeys.Logger_NichtGeaendertDaBereitsRichtig));
      // zur Sicherheit
      wahlInfo.synchronize();
      return false;
    }
  }

  public void setLastChangeNow(AnwContext c) throws EJBException {
    WahlInfo wahlInfo = WahlInfo.getWahlInfo(c);
    wahlInfo.getWahl().setLetzteAenderung(new Timestamp(currentTimeMillis()));
    wahlInfo.synchronize();
  }

  public WahlInfo getWahlInfo(AnwContext c) throws EJBException {
    return WahlInfo.getWahlInfo(c);
  }

  public GebietsBaum getGebietsBaum(AnwContext c) {
    return GebietsBaum.getGebietsBaum(c);
  }
}
