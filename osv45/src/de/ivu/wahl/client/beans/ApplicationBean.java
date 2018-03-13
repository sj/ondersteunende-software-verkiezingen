package de.ivu.wahl.client.beans;

import static de.ivu.ejb.EJBUtil.findLocalHomeNoCache;
import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.AnwContext.ID_PWAHL_SUPER_ADMIN;
import static de.ivu.wahl.Konstanten.DATUM;
import static de.ivu.wahl.Konstanten.PROP_EXT_LINK_1;
import static de.ivu.wahl.Konstanten.PROP_EXT_LINK_2;
import static de.ivu.wahl.Konstanten.PROP_EXT_LINK_BUTTON_1;
import static de.ivu.wahl.Konstanten.PROP_EXT_LINK_BUTTON_2;
import static de.ivu.wahl.Konstanten.VERSION_P4;
import static de.ivu.wahl.Konstanten.VERSION_P5;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.CUR_ANW_KEY;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.EXC_RECHTEZUGRIFF;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.GEBIETNRIS;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.LEVEL;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.LEVEL_ADMIN;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.LEVEL_NACHRICHT;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.LEVEL_UNABHAENGIG;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.LOGIN_ERROR;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.VIEW_BASIS;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.WORKIS;
import static de.ivu.wahl.client.beans.Command.ADM_STIMMBEZIRKE_EDIT;
import static de.ivu.wahl.client.beans.Command.ANWENDER_VERAENDERN_PASSWORT;
import static de.ivu.wahl.client.beans.Command.GEBE;
import static de.ivu.wahl.client.beans.Command.GEB_ERG;
import static de.ivu.wahl.client.beans.Command.IMPORT_ERGEBNISSE;
import static de.ivu.wahl.client.util.ClientHelper.dateToString;
import static de.ivu.wahl.client.util.ClientHelper.getAllParameters;
import static de.ivu.wahl.client.util.ClientHelper.getSuffixLevel;
import static de.ivu.wahl.client.util.ClientHelper.rewriteURL;
import static de.ivu.wahl.client.util.GUICommand.STATE_DONT_CARE;
import static de.ivu.wahl.modell.GebietModel.ANZAHL_GEBIETSARTEN;
import static de.ivu.wahl.modell.WahlModel.WAHLGEBIETSARTEN;
import static java.lang.System.currentTimeMillis;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.CharBuffer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;

import de.ivu.util.session.SessionHandler;
import de.ivu.util.session.SessionState;
import de.ivu.util.session.Step;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.GebietsBaum;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.PasswordService;
import de.ivu.wahl.SystemInfo;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.AdminHandling;
import de.ivu.wahl.admin.AdminHandlingBean;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.admin.StateHandling;
import de.ivu.wahl.admin.StateHandlingBean;
import de.ivu.wahl.anwender.AnmeldeFehlversuchException;
import de.ivu.wahl.anwender.Anmeldung;
import de.ivu.wahl.anwender.AnwRechte;
import de.ivu.wahl.anwender.AnwenderHandling;
import de.ivu.wahl.anwender.AnwenderHandlingBean;
import de.ivu.wahl.auswertung.AuswertungHandling;
import de.ivu.wahl.auswertung.AuswertungHandlingBean;
import de.ivu.wahl.auswertung.erg.Besonderheiten;
import de.ivu.wahl.auswertung.erg.EingangsHistorieErgebnis;
import de.ivu.wahl.auswertung.erg.EingangsHistorieErgebnis.EingangsContainer;
import de.ivu.wahl.auswertung.erg.Status;
import de.ivu.wahl.auswertung.erg.sv.SitzverteilungErg;
import de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatenListe;
import de.ivu.wahl.auswertung.sv.SitzverteilungHandling;
import de.ivu.wahl.auswertung.sv.SitzverteilungHandlingBean;
import de.ivu.wahl.auswertung.sv.SitzverteilungStatus;
import de.ivu.wahl.client.LogoutException;
import de.ivu.wahl.client.beans.InitGuiCommand.GUICommandList;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.client.util.GUICommand;
import de.ivu.wahl.dataimport.AbstractImportEML;
import de.ivu.wahl.eingang.EingangHandling;
import de.ivu.wahl.eingang.EingangHandlingBean;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.ErgebniseingangKonstanten;
import de.ivu.wahl.modell.GebietInfo;
import de.ivu.wahl.modell.GebietModel;
import de.ivu.wahl.modell.Gebietsart;
import de.ivu.wahl.modell.GruppeGebietsspezifischModel;
import de.ivu.wahl.modell.GruppeModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.Anwender;
import de.ivu.wahl.modell.ejb.Gebiet;
import de.ivu.wahl.modell.ejb.Personendaten;
import de.ivu.wahl.modell.ejb.Rechtegruppe;
import de.ivu.wahl.modell.ejb.Wahl;
import de.ivu.wahl.modell.ejb.WahlHome;
import de.ivu.wahl.modell.ejb.service.GebietHandling;
import de.ivu.wahl.modell.ejb.service.GebietHandlingBean;
import de.ivu.wahl.modell.ejb.service.VotesHandling;
import de.ivu.wahl.modell.ejb.service.VotesHandlingBean;
import de.ivu.wahl.util.BundleHelper;
import de.ivu.wahl.wus.electioncategory.ElectionCategory;

/**
 * ClientBean, welches der Anker der Applikation ist. Beinhaltet die grundlegenden Funktionen zur
 * Steuerung im Client und arbeitet dabei eng mit den JSPs und den anderen ClientBeans zusammen.
 * Enthaelt einige Durchgriffe auf SessionBeans.
 * 
 * @author mur@ivu.de Copyright (c) 2002-17 IVU Traffic Technologies AG
 */
public class ApplicationBean implements Executer, Serializable, HttpSessionBindingListener {

  public static final String USER_ATTRIBUTE = "User2017"; //$NON-NLS-1$
  public static final String PASSWORD_ATTRIBUTE = "Password2017"; //$NON-NLS-1$

  private static final long serialVersionUID = 713939946682990818L;
  private SitzverteilungStatus _sitzverteilungStatus = null;

  public static class LabelValueBean implements Serializable {
    private static final long serialVersionUID = -3016274013254949592L;
    private final String _label;
    private final String _value;

    /**
     * @param label Label (der angezeigte Wert)
     * @param value Value (der interne Wert)
     */
    public LabelValueBean(String label, String value) {
      _label = label;
      _value = value;
    }

    public String getLabel() {
      return _label;
    }

    public String getValue() {
      return _value;
    }
  }

  /**
   * Logger for instances of this class
   */
  protected static final Logger LOGGER = Logger.getLogger(ApplicationBean.class);

  private transient int _gebietsart;
  private transient int _gebietsnummer;
  private transient ElectionCategory _electionCategory;
  private transient int _gebietsartWurzelgebiet;
  private transient int _gebietsnummerWurzelgebiet;

  /**
   * Ein echter Internet Explorer von Microsoft
   */
  private boolean _realIE;

  /**
   * der Anwenderkontext wir nach erfolgreicher Anmeldung im Application Bean hinterlegt
   */
  private transient AnwContext _anwContext;

  /**
   * Diese Map stellt die Beziehung zwischen work-Kodierung und JSPs her. Die Initialisierung
   * erfolgt beim Anlegen der Befehle.
   */
  private final Map<Integer, String> _jspMap = new HashMap<Integer, String>();

  /**
   * Diese Map stellt die Beziehung zwischen zwischen den Befehlsnamen aus den Buttons und den
   * angezeigten JSP-Seiten im Arbeitsbereich her. Die Initialisierung erfolgt beim Anlegen der
   * Befehle.
   */
  private final Map<String, String> _jspLevelWorkName = new HashMap<String, String>();

  private transient AdminHandling _admHandling;
  private transient AnwenderHandling _anwHandling;
  private transient AuswertungHandling _auswertungHandling;
  private transient EingangHandling _eingangHandling;
  private transient GebietHandling _gebietHandling;
  private transient PropertyHandling _propertyHandling;
  private transient SitzverteilungHandling _sitzverteilungHandling;
  private transient StateHandling _stateHandling;
  private transient VotesHandling _votesHandling;

  /**
   * Verfuegbarer Befehlsvorrat wird in diese Listen geschrieben
   */
  private GUICommandList[] _befehleInitial;

  /**
   * gefilterter Befehlsvorrat wird in diese Listen geschrieben
   */
  private GUICommandList[] _befehle;

  private volatile long _filteredTimestamp;

  private transient InitGuiCommand _initGuiCommand;

  /**
   * Initialisierung; Insbesondere Ermittlung des nachfragenden Browsers
   * 
   * @param request HTTP Request
   */
  public void init(HttpServletRequest request) {
    String ua = request.getHeader("User-Agent"); //$NON-NLS-1$
    if (ua != null) {
      if (ua.indexOf("MSIE") != -1) { //$NON-NLS-1$
        LOGGER.info("Browser is IE " + ua); //$NON-NLS-1$
        _realIE = true;
      } else if (ua.indexOf("Mozilla/5.0") != -1) { //$NON-NLS-1$
        LOGGER.info("Browser is Mozilla-based " + ua); //$NON-NLS-1$
      } else if (ua.indexOf("4.") != -1) { //$NON-NLS-1$
        LOGGER.info("Browser is NS4 " + ua); //$NON-NLS-1$
      } else {
        // Annahme: anderer "moderner" Browser
        LOGGER.info("UserAgent: " + ua); //$NON-NLS-1$
      }
    }
  }

  public PropertyHandling getPropertyHandling() {
    if (_propertyHandling == null) {
      _propertyHandling = lookupLocal(PropertyHandlingBean.class.getSimpleName());
    }
    return _propertyHandling;
  }

  public EingangHandling getEingangHandling() {
    if (_eingangHandling == null) {
      _eingangHandling = lookupLocal(EingangHandlingBean.class.getSimpleName());
    }
    return _eingangHandling;
  }

  public StateHandling getStateHandling() {
    if (_stateHandling == null) {
      _stateHandling = lookupLocal(StateHandlingBean.class.getSimpleName());
    }
    return _stateHandling;
  }

  public AuswertungHandling getAuswertungHandling() {
    if (_auswertungHandling == null) {
      _auswertungHandling = lookupLocal(AuswertungHandlingBean.class.getSimpleName());
    }
    return _auswertungHandling;
  }

  public GebietHandling getGebietHandling() {
    if (_gebietHandling == null) {
      _gebietHandling = lookupLocal(GebietHandlingBean.class.getSimpleName());
    }
    return _gebietHandling;
  }

  public SitzverteilungHandling getSitzverteilungHandling() {
    if (_sitzverteilungHandling == null) {
      _sitzverteilungHandling = lookupLocal(SitzverteilungHandlingBean.class.getSimpleName());
    }
    return _sitzverteilungHandling;
  }

  public VotesHandling getVotesHandling() {
    if (_votesHandling == null) {
      _votesHandling = lookupLocal(VotesHandlingBean.class.getSimpleName());
    }
    return _votesHandling;
  }

  public String getLogoForWahl() {
    return getInitGuiCommand().getLogoForWahl(_gebietsnummerWurzelgebiet);
  }

  /**
   * Erfüllt gewünschte Commands vom HTML-Client
   * 
   * @param request Der Request mit allen Informationen
   * @param n Anzahl der Commands
   */
  public void executeCommand(HttpServletRequest request, int n) {
    String cmd = request.getParameter("cmd" + (n == 0 ? "" : "" + n)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    if (cmd != null) {
      if (cmd.equals("app_login")) { //$NON-NLS-1$
        // Login
        String name = request.getParameter(USER_ATTRIBUTE);
        String pass = request.getParameter(PASSWORD_ATTRIBUTE);
        doLogin(request, name, pass);
      } else if (cmd.equals("app_logout")) { //$NON-NLS-1$
        // Logout
        doLogout(request);
      } else if (cmd.equals("app_startSitzberechnung")) { //$NON-NLS-1$
        startSitzberechnung(request);
      } else if (cmd.equals("app_setKonfliktAlternative")) { //$NON-NLS-1$
        _sitzverteilungStatus.setErrorMsg(null);
        setKonfliktAlternative(request);
      } else {
        // unbekannter Befehl
        final String message = "Command " + cmd + " unknown"; //$NON-NLS-1$ //$NON-NLS-2$
        LOGGER.warn(message);
        throw new RuntimeException(message);
      }
    }
  }

  /**
   * Loggt den default user automatisch mit dem default passwort ein.
   */
  public void loginDefaultUser(HttpServletRequest request) {
    String name = Konstanten.DEFAULT_USER_LOGINNAME;
    String pass = Konstanten.DEFAULT_USER_PASSWORD;
    doLogin(request, name, pass);
  }

  /**
   * @param request
   */
  private void setKonfliktAlternative(HttpServletRequest request) {
    String id_alternative = request.getParameter(ApplicationBeanKonstanten.PREFIX + "alternative"); //$NON-NLS-1$
    if (id_alternative == null || id_alternative.isEmpty()) {
      _sitzverteilungStatus.setErrorMsg(Messages.getString(MessageKeys.ERROR_AuswahlAlternative));
      return;
    }
    getSitzverteilungHandling().writeKonfliktWithLosAlternative(_sitzverteilungStatus,
        id_alternative);
    startSitzberechnung(request);

  }

  private void startSitzberechnung(HttpServletRequest request) {
    final WahlInfo wahlInfo = WahlInfo.getWahlInfo();
    wahlInfo.synchronize();
    final int status = wahlInfo.getStatus();
    // don't start while calculating
    if (status != WahlModel.STATE_CALCULATING && status != WahlModel.STATE_CALCULATION_SUCCESSFUL) {
      int defaultSessionTimeout = ClientHelper.changeSessionTimeout(request, 60);
      try {
        String id_Ergebniseingang = request.getParameter(ApplicationBeanKonstanten.PREFIX
            + "id_Ergebniseingang"); //$NON-NLS-1$
        _sitzverteilungStatus = getSitzverteilungHandling()
            .berechneSitzverteilung(id_Ergebniseingang);
      } catch (Exception e) {
        _sitzverteilungStatus = new SitzverteilungStatus();
        _sitzverteilungStatus.setErrorMsg(e.getMessage());
      } finally {
        ClientHelper.setSessionTimeoutBackToDefaultPlus(request, defaultSessionTimeout);
      }

    }
  }

  public String getSitzverteilungErrorMsg() {
    return _sitzverteilungStatus != null ? _sitzverteilungStatus.getErrorMsg() : null;
  }

  /**
   * Aufruf durch das Servlet-API wenn das bean an eine Session gebunden wird
   * 
   * @param event binding event
   */
  public void valueBound(HttpSessionBindingEvent event) {
    // do nothing
    LOGGER.info("ApplicationBean bound to session " + event.getSession().getId()); //$NON-NLS-1$
  }

  /**
   * Aufruf durch das Servlet-API beim zerstoeren der Session Dies ist klassischerweise bei einem
   * Timeout der Fall
   * 
   * @param event binding event
   */
  public void valueUnbound(HttpSessionBindingEvent event) {
    LOGGER.info("ApplicationBean unbound from session " + event.getSession().getId()); //$NON-NLS-1$
    try {
      // wenn ein Anwender angemeldet ist -> abmelden
      if (_anwContext != null) {
        String keyForViewlock = _anwContext.getKeyForViewlock();
        LOGGER.info(Messages
            .getString(MessageKeys.Logger_AutomatischesAbmeldenNachTimeout_AnwenderID)
            + _anwContext.getID_Anwender() + " - " + keyForViewlock); //$NON-NLS-1$
        getAnwenderHandling().logout(_anwContext);
        getEingangHandling().removeLockForUser(_anwContext);
        LOGGER.info(Messages.bind(MessageKeys.Logger_GebieteEntsperrtNachAutomatischemAbmelden,
            keyForViewlock));
      }
    } catch (Exception e) {
      LOGGER.error(Messages.getString(MessageKeys.Error_FehlerBeimAufloesenDerSession), e);
    } finally {
      _anwContext = null;
    }
  }

  /**
   * @return AnwenderHandling
   */
  public AnwenderHandling getAnwenderHandling() {
    if (_anwHandling == null) {
      _anwHandling = lookupLocal(AnwenderHandlingBean.class.getSimpleName());
    }
    return _anwHandling;
  }

  /**
   * @param request
   * @return den angemeldeten Anweneder aus der Session
   */
  public static AnwContext getAnwContext(HttpServletRequest request) {
    return (AnwContext) request.getSession().getAttribute(CUR_ANW_KEY);
  }

  /**
   * @return den angemeldeten Anweneder aus der Session
   */
  public AnwContext getAnwContext() {
    return _anwContext;
  }

  public String getIdAnwContext() {
    return _anwContext.getID_Anwender();
  }

  public static boolean isAnwenderMemberOfRechtegruppe(Anwender anwender, String id_Rechtegruppe) {
    try {
      for (Rechtegruppe rechtegruppe : anwender.getRechtegruppeCol()) {
        if (rechtegruppe.getPrimaryKey().equals(id_Rechtegruppe)) {
          return true;
        }
      }
    } catch (Exception e) {
      LOGGER.error(e);
    }
    return false;
  }

  /**
   * Erledigt die Anmeldung eines Anwenders. Klappt alles, so wird der AnwContext im ApplicationBean
   * und in der Session abgelegt (CUR_ANW_KEY). Fehler werden in der Session abgelegt und können so
   * von der JSP angezeigt werden.
   * 
   * @param request Request mit "User" und "Password"
   */
  private void doLogin(HttpServletRequest request, String pName, String pPass) {
    String name = pName;
    String pass = pPass;
    // eine "ordentliche" Anmeldung liegt nur dann vor, wenn kein LEVEL
    // enthalten ist
    if (request.getParameter(LEVEL) == null) {
      String errmsg = null; // für mögliche Fehler
      // als erstes möglicherweise vorhandenen User aus der Session werfen
      HttpSession session = request.getSession();
      AnwContext anwContext = (AnwContext) session.getAttribute(CUR_ANW_KEY);
      if (anwContext != null) {
        doLogout(anwContext);
        session.removeAttribute(CUR_ANW_KEY);
      }
      if (_anwContext != null) {
        doLogout(_anwContext);
        _anwContext = null;
      }

      if (session.getAttribute(SessionState.DEFAULT_KEY) == null) {
        // if session timed out during log in, create some things anew
        SessionState state = new SessionHandler(30);
        session.setAttribute(SessionState.DEFAULT_KEY, state);
        Step step = state.createStep();
        request.setAttribute(ClientHelper.CURRENT_STEP, step);
        session.setAttribute(ClientHelper.CURRENT_STEP, step);
      }

      // Fehler werden auch zurückgesetzt
      session.removeAttribute(LOGIN_ERROR);
      // Parameter prüfen und trimmen
      // wahlid wird nur dann gesetzt, wenn ein Admin eine Wahl auswaehlen
      // muss
      String id_WahlAuswahl = request.getParameter("id_wahlauswahl"); //$NON-NLS-1$
      if (id_WahlAuswahl == null) {
        if (name != null && name.trim().length() > 0) {
          name = name.trim();
          if (pass != null && pass.trim().length() > 0) {
            pass = pass.trim();
          } else {
            errmsg = BundleHelper.getBundleString("Passwort_eingabe") + "!"; //$NON-NLS-1$ //$NON-NLS-2$
          }
        } else {
          errmsg = BundleHelper.getBundleString("Anwender_eingabe") + "!"; //$NON-NLS-1$ //$NON-NLS-2$
        }
      }
      // Anmeldung versuchen, wenn alle Daten da sind
      if (errmsg == null) {
        boolean connectionFailed = false;
        do { // Schleife wird max 2x durchlaufen
          try {
            String passHash;
            if (id_WahlAuswahl == null) {
              String salt = getAnwenderHandling().getSalt(name);
              passHash = PasswordService.INSTANCE.calcHash(name + pass, salt);
            } else {
              name = getAndRemoveAttribute(session, "USERNAME"); //$NON-NLS-1$
              passHash = getAndRemoveAttribute(session, "PASSWORDHASH"); //$NON-NLS-1$
            }
            // wenn der passende Anwender gefunden wird, gilt dieser als
            // angemeldet
            _anwContext = getAnwenderHandling().login(name,
                passHash,
                id_WahlAuswahl,
                request.getSession().getId());

            if (_anwContext != null) {
              if (Konstanten.DEFAULT_USER_LOGINNAME.equals(name)
                  && Konstanten.DEFAULT_USER_PASSWORD.equals(pass)) {
                _anwContext.setChangePasswordForced(true);
              }
              LOGGER.info(Messages
                  .getString(MessageKeys.Logger_AnwenderContextGefundenFuerAnwenderMitDerID)
                  + _anwContext.getID_Anwender());
              // Login for only one of the same useraccount
              if (AnwContext.ADM_USER_ALREADY_LOGGED_IN.equals(_anwContext)) {
                // _anwContext == ADM_USER_ALREADY_LOGGED_IN only for detection of the ErrorState
                errmsg = Messages
                    .bind(MessageKeys.Logger_AnwenderBereitsAmSystemAngemeldet_0, name);
                _anwContext = null;
              } else if (_anwContext.getID_WahlPWahl() == null) {
                session.setAttribute("USERNAME", name); //$NON-NLS-1$
                session.setAttribute("PASSWORDHASH", passHash); //$NON-NLS-1$
                LOGGER.info("setAttribute USERNAME    = " + name); //$NON-NLS-1$
                LOGGER.info("setAttribute PASSWORDHASH  = " + passHash); //$NON-NLS-1$
                int anzahlWahlen = getWahlen().size();
                if (anzahlWahlen > 1) {
                  errmsg = BundleHelper.getBundleString("Anwender_mehrerer_Wahlen"); //$NON-NLS-1$
                } else {
                  errmsg = BundleHelper.getBundleString("Anwender_eine_Wahl"); //$NON-NLS-1$
                }
              } else {
                if (_anwContext.getID_Wahl() != null) {
                  WahlInfo wahlInfo = WahlInfo.getWahlInfo();
                  _electionCategory = wahlInfo.getElectionCategory();
                  GebietModel gebietModel = wahlInfo.getModel4Gebiet(_anwContext.getID_Gebiet());
                  _gebietsart = gebietModel.getGebietsart();
                  _gebietsnummer = gebietModel.getNummer();
                  GebietModel wurzelgebietModel = wahlInfo.getModel4Gebiet(wahlInfo
                      .getID_Wurzelgebiet());
                  _gebietsartWurzelgebiet = wurzelgebietModel.getGebietsart();
                  _gebietsnummerWurzelgebiet = wurzelgebietModel.getNummer();
                }
                initBefehle();
                connectionFailed = false;
              }
            }
          } catch (AnmeldeFehlversuchException afe) {
            if (afe.getVerbleibendeAnmeldeversuche() > 3) {
              errmsg = BundleHelper.getBundleString("Anmeldung_fehlerhaft_Fehlversuche"); //$NON-NLS-1$
            } else if (afe.getVerbleibendeAnmeldeversuche() > 0) {
              errmsg = Messages
                  .applyPattern(BundleHelper
                      .getBundleString("Anmeldung_fehlerhaft_verbleibende_Versuche_0"), afe.getVerbleibendeAnmeldeversuche()); //$NON-NLS-1$
            } else {
              errmsg = BundleHelper.getBundleString("Anmeldung_fehlerhaft_User_gesperrt"); //$NON-NLS-1$
            }
            connectionFailed = false;
          } catch (Exception e) {
            LOGGER.error(e, e);
            errmsg = BundleHelper.getBundleString("Anmeldung_fehlerhaft"); //$NON-NLS-1$
            connectionFailed = false;
          }
        } while (connectionFailed);
        // wenn es nicht geklappt hat, heisst dies entweder, es war was
        // falsch, oder die Zahl der Fehlversuche ist ueberschritten
        /*
         * moeglicherweise die beiden Varianten durch eine gesonderte Nachfrage unterscheiden, damit
         * der Anwender weiss, wo er dran ist
         */
        if (_anwContext == null && errmsg == null) {
          errmsg = BundleHelper.getBundleString("Anmeldung_fehlerhaft_Fehlversuche"); //$NON-NLS-1$
        }
      }
      // und Abschluss -> Fehlermeldung moeglicherweise in die Session
      if (errmsg != null) {
        session.setAttribute(LOGIN_ERROR, errmsg);
      } else {
        session.setAttribute(CUR_ANW_KEY, _anwContext);
      }
    }
  }

  private String getAndRemoveAttribute(HttpSession session, String attributeName) {
    String attributeValue = (String) session.getAttribute(attributeName);
    LOGGER.info("getAttribute " + attributeName + "  = " + attributeValue); //$NON-NLS-1$ //$NON-NLS-2$
    session.removeAttribute(attributeName);
    return attributeValue;
  }

  /**
   * Abwickeln der Abmeldung eines Anwenders dies beinhaltet bescheid sagen beim Server und werfen
   * einer Logout Exception zum benachrichtigen des Servlets
   */
  private void doLogout(HttpServletRequest request) {
    doLogout(_anwContext);
    _anwContext = null; // im bean entfernen
    // Anwender aus der Session entfernen (man weiss ja nie ...)
    request.getSession().removeAttribute(CUR_ANW_KEY);
    throw new LogoutException();
  }

  /**
   * Kern des Abmeldeprozesses
   */
  private void doLogout(AnwContext anwContext) {
    try {
      // fuer das Fuehren einer Liste der angemeldeten Benutzer
      getAnwenderHandling().logout(anwContext);
    } catch (Exception e) {
      LOGGER.error(e, e);
    }
  }

  /**
   * ALLE MoeGLICHEN BEFEHLE als GUICommands initialisieren und in die entsprechenden Collections
   * eintueten
   */
  private void initBefehle() {
    if (ID_PWAHL_SUPER_ADMIN.equals(_anwContext.getID_WahlPWahl())) {
      initAdminBefehle();
    } else {
      InitGuiCommand command = initStandardBefehle();
      if (command != null) {
        _initGuiCommand = command;
      }
    }

    initExternalLinks();
  }

  private void initAdminBefehle() {
    SystemInfo systemInfo = SystemInfo.getSystemInfo();
    _initGuiCommand = new InitGuiCommandAdmin(_jspMap, 0, systemInfo.getWahlModus(),
        systemInfo.getWahlEbene());
  }

  private InitGuiCommand initStandardBefehle() {
    SystemInfo systemInfo = SystemInfo.getSystemInfo();
    int wahlModus = systemInfo.getWahlModus();
    int wahlEbene = systemInfo.getWahlEbene();
    int gebietsartErfassungseinheitMax = getWahlInfo().getGebietsartErfassungseinheitMax();

    switch (_electionCategory) {
      case TK :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandTK_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandTK_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case EK :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandEK_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandEK_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case EP :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandEP_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandEP_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case GR :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandGemeente_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandGemeente_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case ER :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandEiland_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandEiland_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case BC :
      case GC :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandDeelraad_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandDeelraad_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case PS :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandProv_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandProv_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case AB :
        switch (wahlModus) {
          case AbstractImportEML.MODE_DB_P4 :
            return new InitGuiCommandAB_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene);
          case AbstractImportEML.MODE_DB_P5 :
            return new InitGuiCommandAB_P5(_jspMap, gebietsartErfassungseinheitMax);
        }
        break;
      case LR :
      case IR :
      case NR :
      case PR :
        return new InitGuiCommandReferendum_P4(_jspMap, gebietsartErfassungseinheitMax, wahlEbene,
            _electionCategory);
      default :
        break;
    }
    return null;
  }

  /**
   * Dient zur Initialisierung der beiden Listen: Anzahl aller Gebiete plus 3 weiter fuer
   * Nachrichten und Admin sowie Level unabhaengig
   */
  private void initExternalLinks() {
    String extlink_1 = null;
    String extlinkName_1 = null;
    String extlink_2 = null;
    String extlinkName_2 = null;
    try {
      AdminHandling adminHandling = getAdminHandling();
      extlink_1 = adminHandling.getProperty(PROP_EXT_LINK_1);
      extlinkName_1 = adminHandling.getProperty(PROP_EXT_LINK_BUTTON_1);
      extlink_2 = adminHandling.getProperty(PROP_EXT_LINK_2);
      extlinkName_2 = adminHandling.getProperty(PROP_EXT_LINK_BUTTON_2);
    } catch (Exception e) {
      LOGGER.error(e);
    }
    int levelCount = ANZAHL_GEBIETSARTEN + 3;
    _befehleInitial = new GUICommandList[levelCount];
    _befehle = new GUICommandList[levelCount];
    _initGuiCommand.initBefehle(_jspLevelWorkName, _befehleInitial, _befehle, levelCount);
  }

  /**
   * Liefert die aktuelle Instanz des InitGuiCommand zurueck. Die Initialisierung erfolgt unter
   * initBefehle()
   * 
   * @see #initBefehle()
   * @return fertige Instanz von InitGuiCommand
   */
  public InitGuiCommand getInitGuiCommand() {
    if (_initGuiCommand == null) {
      initBefehle();
    }
    return _initGuiCommand;
  }

  private Map<Integer, String> _levelKlartext;

  private Map<Integer, String> getLevelKlartexte() {
    if (_levelKlartext == null) {
      _levelKlartext = new HashMap<Integer, String>();
      if (_initGuiCommand.isAdminVorhanden()) {
        _levelKlartext.put(LEVEL_ADMIN, BundleHelper.getBundleString("Administration")); //$NON-NLS-1$
      }
      if (_initGuiCommand.isGebieteVorhanden()) {
        for (int gebietsart : WAHLGEBIETSARTEN.get(_electionCategory)) {
          _levelKlartext.put(gebietsart, Gebietsart.getKlartext(gebietsart));
        }
      }
    }
    return _levelKlartext;
  }

  public String getLevelKlartext(int level) {
    if (_levelKlartext == null) {
      getLevelKlartexte();
    }
    if (_levelKlartext.containsKey(level)) {
      return _levelKlartext.get(level);
    }
    return ""; //$NON-NLS-1$
  }

  public GebietsBaum getGebietsBaum() {
    return getStateHandling().getGebietsBaum(_anwContext);
  }

  public WahlInfo getWahlInfo() {
    return WahlInfo.getWahlInfo();
  }

  public String getWahlName() {
    WahlInfo wahlInfo = getWahlInfo();
    if (wahlInfo != null) {
      return wahlInfo.getWahlName();
    } else {
      return BundleHelper.getBundleString("WUA"); //$NON-NLS-1$
    }
  }

  public String getWahlNameOrReferendum() {
    WahlInfo wahlInfo = getWahlInfo();
    if (wahlInfo != null) {
      if (wahlInfo.isReferendum()) {
        return BundleHelper.getBundleString("Referendum"); //$NON-NLS-1$
      } else {
        return wahlInfo.getWahlName();
      }
    } else {
      return BundleHelper.getBundleString("WUA"); //$NON-NLS-1$
    }
  }

  /**
   * Filtern der Befehle mit dem Anwender. Dabei Umkopieren in _befehle zur weiteren Verwendung
   */
  private void filterBefehle() {
    AnwRechte anwRechte = getAnwenderHandling().getAnwRechte(_anwContext);
    if (_filteredTimestamp != anwRechte.getTimestamp()) {
      _filteredTimestamp = anwRechte.getTimestamp();
      for (int level = 0; level < GebietModel.ANZAHL_GEBIETSARTEN + 2; level++) {
        GUICommandList befehle = _befehle[level];
        befehle.clear();
        for (int i = 0; i < _befehleInitial[level].size(); i++) {
          GUICommand cmd = _befehleInitial[level].get(i);

          // Pruefen der Berechtigung
          if (cmd.getRecht() == null || anwRechte.checkRight(cmd.getRecht())) {
            addGUICommand(befehle, cmd);
          }
        }
      }

      for (GUICommand cmd : _befehleInitial[LEVEL_UNABHAENGIG]) {
        // Pruefen der Berechtigung
        if (cmd.getRecht() == null || anwRechte.checkRight(cmd.getRecht())) {
          // wenn der Command auf allen Ebenen zu sehen sein soll
          if (cmd.getAlleLevel()) {
            setCommandsToAllLevel(cmd);
          } else if (cmd.getNurGebiete()) {
            setCommandsToAllGebiete(cmd);
          }
        }
      }
    }
  }

  public void setCommandsToAllLevel(GUICommand cmd) {
    for (int level = 0; level < ANZAHL_GEBIETSARTEN + 2; level++) {
      List<GUICommand> befehle = _befehle[level];
      addGUICommand(befehle, cmd);
    }
  }

  public void setCommandsToAllGebiete(GUICommand cmd) {
    for (int level = 0; level < ANZAHL_GEBIETSARTEN; level++) {
      List<GUICommand> befehle = _befehle[level];
      addGUICommand(befehle, cmd);
    }
  }

  private void addGUICommand(List<GUICommand> befehle, GUICommand cmd) {
    int position = cmd.getPosition();
    if (position != -1 && befehle.size() > position) {
      befehle.add(position, cmd);
    } else {
      befehle.add(cmd);
    }
  }

  protected boolean isStatusTest() {
    try {
      return getAuswertungHandling().getStatus(getAnwContext()).isStatusTest();
    } catch (Exception ignore) {
      return false;
    }
  }

  /**
   * Filtert die uebergebene Liste von Befehlen gemaess dem uebergebenen Gebiet
   * 
   * @param befehle Liste von GUICommands
   * @param gebietsart Gebietsart des Gebietes
   * @param gebietsnummer Nummer des Gebietes oder -1 fuer kein Bezug zum einem Gebiet
   * @return gemaess dem uebergebenen Gebiet gefilterte Liste der Befehle
   */
  private List<GUICommand> filterForGebiet(List<GUICommand> befehle,
      int gebietsart,
      int gebietsnummer) {

    if (gebietsnummer == -1) {
      return befehle;
    } else {
      AnwRechte ar = getAnwenderHandling().getAnwRechte(_anwContext);
      List<GUICommand> befehleGefiltert = new ArrayList<GUICommand>();
      for (GUICommand cmd : befehle) {
        if (cmd.getGebietsabhaengig()) {
          // todo Ueberpruefung fuer Erfassungseinheit , wahlgebiet, ...
          if (ar.checkRightForGebiet(cmd.getRecht(), gebietsart, gebietsnummer)) {
            pruefeGebietstypen(gebietsart, gebietsnummer, befehleGefiltert, cmd);
          }
        } else {
          pruefeGebietstypen(gebietsart, gebietsnummer, befehleGefiltert, cmd);
        }
      }
      return befehleGefiltert;
    }
  }

  /**
   * Prueft anhand der Eigenschaften des Gebiets, ob der Befehl aufgenommen werden darf und nimmt
   * ihn ggf. in die Liste mit auf
   * 
   * @param gebietsart Gebietsart
   * @param gebietsnummer Gebietsnummer
   * @param befehleGefiltert Liste der aufgenommenen Befehle
   * @param cmd der zu pruefende Befehl
   */
  private void pruefeGebietstypen(int gebietsart,
      int gebietsnummer,
      List<GUICommand> befehleGefiltert,
      GUICommand cmd) {

    if (cmd.getNurErfassungseinheit()) {
      DefaultMutableTreeNode tn = getGebietsBaum().getGebietsNode(gebietsart, gebietsnummer);
      if (tn != null && ((GebietInfo) tn.getUserObject()).isErfassungseinheit()) {
        befehleGefiltert.add(cmd);
      }
    } else if (cmd.getNurWurzelgebiet()) {
      if (_gebietsartWurzelgebiet == gebietsart && _gebietsnummerWurzelgebiet == gebietsnummer) {
        befehleGefiltert.add(cmd);
      }
    } else if (cmd.getNurWahlgebiete()) {
      DefaultMutableTreeNode tn = getGebietsBaum().getGebietsNode(gebietsart, gebietsnummer);
      if (tn != null && ((GebietInfo) tn.getUserObject()).isWahlgebiet()) {
        befehleGefiltert.add(cmd);
      }
    } else {
      befehleGefiltert.add(cmd);
    }
  }

  /**
   * Filtert die uebergebene Liste gemaess Zustand; Gleichzeitig: Spezialregel fuer Konflikte!
   */
  private List<GUICommand> filterForState(List<GUICommand> befehle) {
    boolean vollstaendig;
    boolean freigegeben;
    try {
      StateHandling stateHandling = getStateHandling();
      vollstaendig = stateHandling.isWahlVollstaendig(_anwContext);
      // Constraint genutzt, dass eine freigegebene Wahl IMMER geschlossen sein muss (gesichert
      // anderswo)
      freigegeben = vollstaendig && stateHandling.isFreigegeben(_anwContext, 0);
    } catch (Exception e) {
      LOGGER.error(e, e);
      vollstaendig = false;
      freigegeben = false;
    }
    AnwRechte anwRechte = getAnwenderHandling().getAnwRechte(_anwContext);
    boolean isLevelSondergebiet = false;
    List<GUICommand> l = new ArrayList<GUICommand>();
    for (GUICommand cmd : befehle) {
      int appstate = cmd.getAppstate();
      int wahlArt = cmd.getWahlArt();
      if (((appstate == STATE_DONT_CARE || appstate == 0)
          && ((!vollstaendig || !cmd.getNurNichtVollstaendig()) || (!freigegeben && isLevelSondergebiet))
          && (vollstaendig || !cmd.getNurVollstaendig())
          && (wahlArt == STATE_DONT_CARE || wahlArt == 0)
          && (freigegeben || !cmd.getNurFreigegeben())
          && (!freigegeben || !cmd.getNurNichtFreigegeben()) && (freigegeben
          || cmd.getRechtOderFreigabe() == null || anwRechte.checkRight(cmd.getRechtOderFreigabe())))
          || (vollstaendig && cmd.getRechtUndGeschlossen() != null && anwRechte.checkRight(cmd
              .getRechtUndGeschlossen()))) {
        // wenn es nicht der Konflikt-Befehl ist
        l.add(cmd);
      }
    }
    return l;
  }

  private List<GUICommand> filterForNurWennRechtAufWurzelgebiet(List<GUICommand> befehle) {
    try {
      AnwRechte anwRechte = getAnwenderHandling().getAnwRechte(_anwContext);
      List<GUICommand> l = new ArrayList<GUICommand>();
      for (GUICommand cmd : befehle) {
        if (cmd.getNurWennRechtAufWurzelgebiet()) {
          String recht = cmd.getRecht();
          if (recht == null
              || anwRechte.checkRightForGebiet(recht,
                  _gebietsartWurzelgebiet,
                  _gebietsnummerWurzelgebiet)) {
            l.add(cmd);
          }
        } else {
          l.add(cmd);
        }
      }
      return l;
    } catch (Exception e) {
      throw new RuntimeException(EXC_RECHTEZUGRIFF, e);
    }
  }

  /**
   * @return List von GUICommands fuer den angegebenen Level
   * @param level Level (Konstanten im ApplicationBeanKonstanten)
   * @param gebNr Gebiet auf das Bezug genommen wird
   */
  public List<GUICommand> getBefehle(int level, int gebNr) {
    boolean printlist = false;
    filterBefehle();
    List<GUICommand> list = _befehle[level];
    if (printlist) {
      printList("nach filterBefehle()", list); //$NON-NLS-1$
    }
    int aktLevel = level;
    int aktGebNr = gebNr;
    if (level == LEVEL_ADMIN || level == LEVEL_NACHRICHT) {
      aktLevel = _gebietsartWurzelgebiet;
      aktGebNr = _gebietsnummerWurzelgebiet;
    }
    list = filterForGebiet(list, aktLevel, aktGebNr);
    if (printlist) {
      printList("nach filterForGebiet()", list); //$NON-NLS-1$
    }
    if (_anwContext.getID_Wahl() != null) {
      list = filterForState(list);
      if (printlist) {
        printList("nach filterForState()", list); //$NON-NLS-1$
      }
      list = filterForNurWennRechtAufWurzelgebiet(list);
      if (printlist) {
        printList("nach filterForNurWennRechtAufWurzelgebiet()", list); //$NON-NLS-1$
      }
    }

    return list;
  }

  /**
   * @return Map key = id_gruppe/n value = GruppeGebietsspezifischModel
   * @param level Level (Konstanten im ApplicationBeanKonstanten)
   * @param gebNr Gebiet auf das Bezug genommen wird
   */
  public Map<String, GruppeGebietsspezifischModel> getAllGruppen(int level, int gebNr) {
    String id_Gebiet = getWahlInfo().getID4Gebiet(level, gebNr);
    try {
      Collection<GruppeGebietsspezifischModel> gruppen = getAuswertungHandling()
          .getAllGruppen(id_Gebiet);
      Map<String, GruppeGebietsspezifischModel> alleGruppen = new LinkedHashMap<String, GruppeGebietsspezifischModel>();
      for (GruppeGebietsspezifischModel gruppe : gruppen) {
        alleGruppen.put(gruppe.getID_Gruppe(), gruppe);
      }
      return alleGruppen;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public EingangsHistorieErgebnis getEingangshistorie(int level, int gebNr) {
    String id_Gebiet = getWahlInfo().getID4Gebiet(level, gebNr);
    try {
      return getAuswertungHandling().getEingangshistorie(id_Gebiet);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public List<EingangsContainer> getEingangsStatus() {
    try {
      return getAuswertungHandling().getEingangsStatus();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public int[] countEingangsStatus() {
    int[] result = {0, 0, 0, 0};
    for (EingangsContainer eingangsStatus : getAuswertungHandling().getEingangsStatus()) {
      switch (eingangsStatus.getStatus()) {
        case ErgebniseingangKonstanten.STATE_OK :
          result[0]++;
          break;
        case ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK :
          result[1]++;
          break;
        case ErgebniseingangKonstanten.STATE_WARNING :
          result[2]++;
          break;
        case ErgebniseingangKonstanten.STATE_ERROR :
          result[3]++;
          break;
        default :
          // do nothing
      }
    }
    return result;
  }

  private void printList(String description, List<GUICommand> list) {
    for (GUICommand element : list) {
      LOGGER.info(description + "   --->   " + element.getBezeichnung()); //$NON-NLS-1$
    }
  }

  /**
   * @return Gebietsnummer des angemeldeten Anwenders
   */
  public int getGebietNrAngemAnw() {
    return _gebietsnummer;
  }

  /**
   * @return Gebietsart des angemeldeten Anwenders
   */
  public int getGebietArtAngemAnw() {
    return _gebietsart;
  }

  /**
   * @return backgroundColor for the upper menu region
   */
  public String getBackgroundColor() {
    String digit = "0123456789ABCDEF"; //$NON-NLS-1$
    int red = getColorValue(Konstanten.PROP_BACKGROUND_COLOR_RED);
    red = Math.max(0, Math.min(255, red));
    int green = getColorValue(Konstanten.PROP_BACKGROUND_COLOR_GREEN);
    green = Math.max(0, Math.min(255, green));
    int blue = getColorValue(Konstanten.PROP_BACKGROUND_COLOR_BLUE);
    blue = Math.max(0, Math.min(255, blue));
    return "#" + digit.charAt(red / 16) + digit.charAt(red % 16) + digit.charAt(green / 16) + digit.charAt(green % 16) + digit.charAt(blue / 16) + digit.charAt(blue % 16); //$NON-NLS-1$
  }

  private int getColorValue(String component) {
    return _propertyHandling == null ? Konstanten.DEFAULT_BACKGROUND_COLOR_GREY : _propertyHandling
        .getIntProperty(component, Konstanten.DEFAULT_BACKGROUND_COLOR_GREY);
  }

  /**
   * @param view
   * @return jspName als String oder null
   */
  public String getJspForWork(int view) {
    return _jspMap.get(view);
  }

  /**
   * @param levelWorkKey
   * @return jspName als String oder null
   */
  public String getNameForWork(String levelWorkKey) {
    return _jspLevelWorkName.get(levelWorkKey);
  }

  public String getNameForWork(int level, int work) {
    String ret = _jspLevelWorkName.get(level + "_" + work); //$NON-NLS-1$
    if (ret == null) {
      ret = _jspLevelWorkName.get(LEVEL_UNABHAENGIG + "_" + work); //$NON-NLS-1$
    }
    return ret;
  }

  public boolean isIE() {
    return _realIE;
  }

  /**
   * @return AdminHandling
   */
  public AdminHandling getAdminHandling() {
    if (_admHandling == null) {
      _admHandling = lookupLocal(AdminHandlingBean.class.getSimpleName());
    }
    return _admHandling;
  }

  /**
   * @return true, wenn die angegebene Art freigegeben ist
   * @param art Art der Eingaben
   */
  public boolean isFreigegeben(int art) {
    boolean freigegeben = false;
    try {
      freigegeben = getStateHandling().isFreigegeben(_anwContext, art);
    } catch (Exception e) {
      LOGGER.error(e, e);
    }
    return freigegeben;
  }

  /**
   * @return true, wenn die Eingabesperre aktiviert ist
   */
  public boolean isInputDisabled() {
    boolean ret = false;
    try {
      ret = getStateHandling().isInputDisabled(_anwContext);
    } catch (Exception e) {
      LOGGER.error(e, e);
    }
    return ret;
  }

  /**
   * @return program version (like "2.7.2")
   */
  public static String getVersionString() {
    SystemInfo systemInfo = SystemInfo.getSystemInfo();
    switch (systemInfo.getWahlModus()) {
      case AbstractImportEML.MODE_DB_P4 :
        return VERSION_P4;
      case AbstractImportEML.MODE_DB_P5 :
        return VERSION_P5;
      default :
        return "";//$NON-NLS-1$
    }
  }

  /**
   * @return datum
   */
  public static String getDatumString() {
    return DATUM;
  }

  public static String getBuildDatumString() {
    try {
      CharBuffer cb = CharBuffer.allocate(10);
      if (new InputStreamReader(ApplicationBean.class.getResource("/build.ts").openStream()) //$NON-NLS-1$
          .read(cb) == 10) {
        cb.rewind();
        return cb.toString();
      }
      return DATUM;
    } catch (IOException e) {
      return DATUM;
    }
  }

  /**
   * Gibt die Zeit der letzten Modellaenderung zurueck (zur Synchronisation von Client und Server)
   * 
   * @return Zeitstempel der letzten Modellaenderung in Millisekunden
   */
  public long getZeitstempelLetzteAenderung() {
    try {
      if (_anwContext != null) {
        return getStateHandling().getZeitstempelLetzteAenderung(_anwContext);
      } else {
        return currentTimeMillis();
        // noch kein Anwender, Optimierung nicht moeglich
      }
    } catch (Exception e) {
      LOGGER.error(e, e);
      return currentTimeMillis();
    }
  }

  /**
   * @param request HTTP-Request (wird nicht verwendet)
   * @return Konfigurationsstring
   */
  public String getKonfigurationsString(HttpServletRequest request) {
    try {
      return getAuswertungHandling().getKonfigurationString(_anwContext);
    } catch (Exception e) {
      LOGGER.error(e, e);
      return ""; //$NON-NLS-1$
    }
  }

  public GebietModel getGebietModel(int level, int gebietNr) throws EJBException {
    return getAdminHandling().getGebietModel(_anwContext, level, gebietNr);
  }

  public GebietModel getGebietModelParent(int level, int gebietNr) throws EJBException {
    return getAdminHandling().getGebietModelParent(_anwContext, level, gebietNr);
  }

  public Status getStatus() throws EJBException {
    try {
      return getAuswertungHandling().getStatus(_anwContext);
    } catch (Exception e) {
      return null;
    }
  }

  public List<GruppeModel> getParteienForWahl() throws EJBException {

    return getAuswertungHandling().getParteienForWahl(_anwContext);
  }

  public boolean checkRight(String right) throws EJBException {
    return getAnwenderHandling().checkRight(_anwContext, right);
  }

  public Collection<Anmeldung> getAngemeldeteAnwender() throws EJBException {
    return getAnwenderHandling().getAngemeldeteAnwender();
  }

  public Collection<Anmeldung> getAngemeldeteAnwenderForWahl() throws EJBException {
    return getAnwenderHandling().getAngemeldeteAnwender(_anwContext.getID_WahlPWahl());
  }

  public Collection<LabelValueBean> getWahlen() throws EJBException, FinderException {
    Collection<LabelValueBean> wahlenSelect = new ArrayList<LabelValueBean>();
    for (Wahl wahl : ((WahlHome) findLocalHomeNoCache("Wahl")).findAll()) { //$NON-NLS-1$
      String name = wahl.getName();
      String id = wahl.getID_Wahl();
      wahlenSelect.add(new LabelValueBean(name, id));
    }
    return wahlenSelect;
  }

  public Collection<LabelValueBean> getWahlenMitZeitstempel() throws EJBException, FinderException {
    Collection<LabelValueBean> wahlenSelect = new ArrayList<LabelValueBean>();
    for (Wahl wahl : ((WahlHome) findLocalHomeNoCache("Wahl")).findAll()) { //$NON-NLS-1$
      String zusatz = ""; //$NON-NLS-1$
      Timestamp geschlossenMetadaten = wahl.getGeschlossenMetadaten();
      if (geschlossenMetadaten != null) {
        long geschlossenMetadatenLong = geschlossenMetadaten.getTime();
        zusatz = " (" + dateToString(geschlossenMetadatenLong, "HH:mm:ss - dd.MM.yyyy") + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      String name = wahl.getName() + zusatz;
      String id = wahl.getID_Wahl();
      wahlenSelect.add(new LabelValueBean(name, id));
    }
    return wahlenSelect;
  }

  /**
   * Funktion fuellt eine alphabetisch sortierte Kandidatenliste mit den gewaehlten Kandidaten eines
   * Gebietes
   * 
   * @param id_ergebniseingang die ID des Ergebniseinganges, zu dem die Sitzverteilung bestimmt
   *          wurde
   * @param id_gebiet die Id des uebergeordneten Gebietes
   * @return Anzahl der gefundenen Kandidaten
   * @throws EJBException
   */
  public KandidatenListe getGewaehltKandidatenForGebietAlphabetisch(String id_ergebniseingang,
      String id_gebiet) throws EJBException {

    return new KandidatenListe(BundleHelper.getBundleString("Gewaehlte_Kandidaten_Alpha_titel"), //$NON-NLS-1$
        getVotesHandling()
            .getGewaehltKandidatenForGebietAlphabetisch(id_ergebniseingang, id_gebiet));
  }

  /**
   * Funktion fuellt eine Kandidatenliste mit den gewaehlten Kandidaten eines Gebietes
   * 
   * @param id_gebiet die Id des uebergeordneten Gebietes
   * @return Anzahl der gefundenen Kandidaten
   * @throws EJBException
   */
  public Collection<Personendaten> getPersonenAlphabetisch() throws EJBException {
    return getAuswertungHandling().getPersonenAlphabetisch(_anwContext);
  }

  /**
   * @return a list of all provinces sorted by number
   */
  public List<Gebiet> getProvinces() throws EJBException {
    return getGebietHandling().getProvinces();
  }

  /**
   * Funktion fuellt eine nach Gruppe sortierte Kandidatenliste mit den gewaehlten Kandidaten eines
   * Gebietes
   * 
   * @param id_ergebniseingang die ID des Ergebniseinganges, zu dem die Sitzverteilung bestimmt
   *          wurde
   * @param id_gebiet die Id des uebergeordneten Gebietes
   * @return Anzahl der gefundenen Kandidaten
   * @throws EJBException
   */
  public KandidatenListe getGewaehltKandidatenForGebietOrderByGruppe(String id_ergebniseingang,
      String id_gebiet) throws EJBException {

    return new KandidatenListe(
        BundleHelper.getBundleString("Gewaehlte_Kandidaten_nach_Partei_titel"), getVotesHandling() //$NON-NLS-1$
            .getGewaehltKandidatenForGebietOrderByGruppe(_anwContext, id_ergebniseingang, id_gebiet));
  }

  /**
   * Liefert die Gebietsart des Wurzelgebiets der Wahl
   * 
   * @return Gebietsart des Wurzelgebiets der Wahl
   */
  public int getGebietsartWahl() {
    return _gebietsartWurzelgebiet;
  }

  /**
   * Liefert die Gebietsnummer des Wurzelgebiets der Wahl
   * 
   * @return Gebietsnummer des Wurzelgebiets der Wahl
   */
  public int getGebietsnummerWahl() {
    return _gebietsnummerWurzelgebiet;
  }

  public String getInitialURL(HttpServletRequest request, HttpServletResponse response) {
    return getInitialURL(request, response, null);
  }

  public String getInitialURL(HttpServletRequest request,
      HttpServletResponse response,
      String advancedParams) {
    return getInitialURL(request, response, advancedParams, false);
  }

  public String getInitialURL(HttpServletRequest request,
      HttpServletResponse response,
      String advancedParams,
      boolean withoutContextpath) {
    String url;
    boolean fragezeichen = false;
    String allParameters = getAllParameters(request);

    if (isBeforeInitialPasswordChange() || _anwContext.isChangePasswordForced()) {
      url = "/osv/wahl/adm_anwender_change_pw_First?" + getSuffixLevel(VIEW_BASIS, LEVEL_ADMIN) + andWorkIs(ANWENDER_VERAENDERN_PASSWORT); //$NON-NLS-1$
    } else if (_anwContext.getID_WahlPWahl() == null) {
      fragezeichen = allParameters.length() == 0;
      if (allParameters.length() > 0) {
        allParameters = '?' + allParameters;
      }
      url = "/jsp/wahl/wahlauswahl.jsp" + allParameters; //$NON-NLS-1$
    } else {
      String was = "/osv?"; //$NON-NLS-1$
      if (_initGuiCommand.isGebieteVorhanden()) {
        int gebietNrAngemAnw = getGebietNrAngemAnw();
        int gebietsnummer;
        int gebietsart;
        if (gebietNrAngemAnw > 0) {
          gebietsnummer = gebietNrAngemAnw;
          gebietsart = getGebietArtAngemAnw();
        } else {
          gebietsnummer = getGebietsartWahl();
          gebietsart = getGebietsnummerWahl();
        }
        de.ivu.wahl.client.beans.Command work = GEB_ERG;
        if (SystemInfo.getSystemInfo().getWahlModus() == AbstractImportEML.MODE_DB_P5
            && !getEingangshistorie(_gebietsartWurzelgebiet, _gebietsnummerWurzelgebiet)
                .hasResults()) {
          work = IMPORT_ERGEBNISSE;
        }
        url = was + getSuffixLevel(VIEW_BASIS, gebietsart)
            + "&" + GEBIETNRIS + gebietsnummer + andWorkIs(work); //$NON-NLS-1$;
      } else if (_initGuiCommand.isAdminVorhanden()) {
        url = was + getSuffixLevel(VIEW_BASIS, LEVEL_ADMIN)
            + andWorkIs(_initGuiCommand.getAdminWorkDefault());
      } else {
        url = was + getSuffixLevel(VIEW_BASIS, LEVEL_UNABHAENGIG);
      }
    }
    final String advanced = advancedParams != null && !advancedParams.isEmpty() ? fragezeichen
        ? "?" //$NON-NLS-1$
        : "&" + advancedParams : ""; //$NON-NLS-1$ //$NON-NLS-2$
    return rewriteURL(url + advanced, request, response, false, withoutContextpath);
  }

  private String andWorkIs(de.ivu.wahl.client.beans.Command command) {
    return "&" + WORKIS + command.getId(); //$NON-NLS-1$
  }

  /**
   * @return the URL of the password change page
   */
  public String getPasswordChangeURL(HttpServletRequest request, HttpServletResponse response) {
    String url = "/osv/wahl/adm_anwender_change_pw_First?" + getSuffixLevel(VIEW_BASIS, LEVEL_ADMIN) //$NON-NLS-1$ 
        + andWorkIs(ANWENDER_VERAENDERN_PASSWORT);
    return rewriteURL(url, request, response, false, false);
  }

  public SitzverteilungErg getSitzverteilungErgebnis(String id_Ergebniseingang,
      HttpServletRequest request) throws EJBException {
    int defaultSessionTimeout = ClientHelper.changeSessionTimeout(request, 60);
    SitzverteilungErg sitzverteilungErgebnis = getVotesHandling()
        .getSitzverteilungErgebnis(id_Ergebniseingang);
    ClientHelper.setSessionTimeoutBackToDefaultPlus(request, defaultSessionTimeout);
    return sitzverteilungErgebnis;
  }

  public Besonderheiten getBesonderheiten(String id_Ergebniseingang) throws EJBException {
    return getSitzverteilungHandling().getBesonderheiten(id_Ergebniseingang);
  }

  public SitzverteilungStatus getSitzverteilungStatus(String id_Ergebniseingang,
      HttpServletRequest request) {
    if (_sitzverteilungStatus == null) {
      int defaultSessionTimeout = ClientHelper.changeSessionTimeout(request, 60);

      _sitzverteilungStatus = getSitzverteilungHandling()
          .berechneSitzverteilung(id_Ergebniseingang);

      ClientHelper.setSessionTimeoutBackToDefaultPlus(request, defaultSessionTimeout);

    }
    return _sitzverteilungStatus;
  }

  public Map<String, String> getINPUT_MAP() {
    return getEingangHandling().getINPUT_MAP();
  }

  public boolean isLogLocking() {
    String keyForViewlock = getAnwContext().getKeyForViewlock();
    boolean found = false;
    Map<String, String> map = getINPUT_MAP();
    for (String idGebiet : map.keySet()) {
      if (keyForViewlock.equals(map.get(idGebiet))) {
        found = true;
        String message = Messages.bind(MessageKeys.Logger_Region_0_IsLockedByUser_1,
            idGebiet,
            getAnwContext().getAnmeldename());
        LOGGER.info(EingangHandlingBean.LOCKING + message);
      }
    }
    if (!found) {
      String message = Messages.bind(MessageKeys.Logger_NoRegionIsLockedByUser_0, getAnwContext()
          .getAnmeldename());
      LOGGER.info(EingangHandlingBean.LOCKING + message);
    }
    return true;
  }

  /**
   * Lock or unlock regions:
   * <ul>
   * <li>Unlock a single region if the editor is left (information taken from referer)</li>
   * <li>Unlock all regions if the regions editor is left (information taken from referer)</li>
   * <li>Lock a single region if the editor or result import page is opened</li>
   * <li>Lock all regions if the regions editor is opened</li>
   * </ul>
   * If the same region should be locked AND unlocked, only lock it.
   * 
   * @param request
   */
  public void lockOrUnlockRegion(HttpServletRequest request) {
    String headerString = request.getHeader("referer"); //$NON-NLS-1$
    GebietModel gebietModelToUnlock = null;

    // Unlocking ...
    if (headerString != null && headerString.contains("work")) { //$NON-NLS-1$
      int refererWork = ClientHelper.getIntParamValueFromHeader(headerString, "work"); //$NON-NLS-1$
      if (ADM_STIMMBEZIRKE_EDIT.hasId(refererWork)) {
        // unlock all regions
        getEingangHandling().removeLockForUser(_anwContext);
      } else if (headerString.contains("level") //$NON-NLS-1$
          && headerString.contains("gebietnr")) { //$NON-NLS-1$
        // unlock single region -> deferred
        int level = ClientHelper.getIntParamValueFromHeader(headerString, "level"); //$NON-NLS-1$
        int gebietNr = ClientHelper.getIntParamValueFromHeader(headerString, "gebietnr"); //$NON-NLS-1$
        gebietModelToUnlock = getGebietModel(level, gebietNr);
      }
    }

    // Locking ...
    int work = ClientHelper.getWork(request, ApplicationBeanKonstanten.GEB_ERG);
    // lock region-input for other User
    GebietModel gebietModelToLock = null;
    boolean isAllRegionsLocked = false;
    if (GEBE.hasId(work) || IMPORT_ERGEBNISSE.hasId(work)) {
      // lock single region -> deferred
      int level = ClientHelper.getLevel(request);
      int gebietNr = ClientHelper.getGebietNr(request);
      gebietModelToLock = getGebietModel(level, gebietNr);
    } else if (ADM_STIMMBEZIRKE_EDIT.hasId(work)) {
      // lock all regions
      try {
        isAllRegionsLocked = true;
        if (gebietModelToUnlock != null) {
          getEingangHandling().removeLockForUser(_anwContext, gebietModelToUnlock.getID_Gebiet());
        }
        getEingangHandling().lockAllRegions(_anwContext);
      } catch (FinderException e) {
        // ignore exception
        LOGGER.error(e.getMessage(), e);
      }
    }
    if (isAllRegionsLocked) {
      return;
    }

    if (gebietModelToLock != null) {
      getEingangHandling().lock(_anwContext, gebietModelToLock.getID_Gebiet());
    }

    if (gebietModelToLock != null && gebietModelToUnlock != null
        && gebietModelToLock.getID_Gebiet().equals(gebietModelToUnlock.getID_Gebiet())) {
      // Same region to lock and unlock -> do nothing
      return;
    }

    if (gebietModelToUnlock != null) {
      getEingangHandling().removeLockForUser(_anwContext, gebietModelToUnlock.getID_Gebiet());
    }
  }

  /**
   * @return <code>true</code>, if the default user (@see {@link Konstanten.DEFAULT_USER_LOGINNAME})
   *         (still) has the default password.
   */
  public boolean isBeforeInitialPasswordChange() {
    return getAnwenderHandling().hasDefaultUserUnchangedPasswort();
  }
}