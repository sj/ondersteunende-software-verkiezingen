/*
 * PropertyHandler
 * 
 * Created on 19.03.2009
 * Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 */
package de.ivu.wahl.client.beans;

import static de.ivu.ejb.EJBUtil.lookupLocal;
import static de.ivu.wahl.client.beans.ApplicationBean.getAnwContext;
import static de.ivu.wahl.client.beans.ApplicationBeanKonstanten.PREFIX;
import static de.ivu.wahl.client.util.ClientHelper.getIntParameter;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

import java.io.File;
import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.ejb.EJBException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Basiseinstellung;
import de.ivu.wahl.InputMode;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.WahlInfo;
import de.ivu.wahl.admin.AdminHandling;
import de.ivu.wahl.admin.AdminHandlingBean;
import de.ivu.wahl.admin.ContextHandling;
import de.ivu.wahl.admin.ContextHandlingBean;
import de.ivu.wahl.admin.PropertyHandling;
import de.ivu.wahl.admin.PropertyHandlingBean;
import de.ivu.wahl.admin.StateHandling;
import de.ivu.wahl.admin.StateHandlingBean;
import de.ivu.wahl.anwender.AnwenderHandling;
import de.ivu.wahl.anwender.AnwenderHandlingBean;
import de.ivu.wahl.auswertung.AuswertungHandling;
import de.ivu.wahl.auswertung.AuswertungHandlingBean;
import de.ivu.wahl.dataimport.ImportHandling;
import de.ivu.wahl.dataimport.ImportHandlingBean;
import de.ivu.wahl.eingang.EingangHandling;
import de.ivu.wahl.eingang.EingangHandlingBean;
import de.ivu.wahl.export.ExportHandling;
import de.ivu.wahl.export.ExportHandlingBean;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.modell.RepositoryModel;
import de.ivu.wahl.modell.WahlModel;
import de.ivu.wahl.modell.ejb.service.GebietHandling;
import de.ivu.wahl.modell.ejb.service.GebietHandlingBean;
import de.ivu.wahl.modell.ejb.service.VotesHandling;
import de.ivu.wahl.modell.ejb.service.VotesHandlingBean;
import de.ivu.wahl.util.BundleHelper;
import de.ivu.wahl.wus.reportgen.ReportOutputFormatEnum;

/**
 * @author mur@ivu.de, IVU Traffic Technologies AG
 */
public class RepositoryPropertyHandler {
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
  private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm"); //$NON-NLS-1$
  final static String TIME_REGEX = "((0|1)[0-9]|2[0-3]):([0-5]?[0-9])";//$NON-NLS-1$
  final static String DATE_REGEX = "[0-3]?\\d-[0-1]?\\d-[1-2]\\d\\d\\d";//$NON-NLS-1$
  final static String ZIP_REGEX = "\\d{4}\\s[A-Z]{2}";//$NON-NLS-1$
  static final String PARAM_FORCE = PREFIX + "force"; //$NON-NLS-1$
  static final String ATTR_EXPORT_RESULT_510 = "EML_510"; //$NON-NLS-1$
  static final String ATTR_EXPORT_RESULT_520 = "EML_520"; //$NON-NLS-1$

  public String _adminWarningOverride;

  public String _adminMsgExport;
  public String _adminMsgExportConfirmation = null;

  public String _adminMsgProps;
  public String _adminMsgPropsConfirmation = null;

  private final static Logger LOGGER = Logger.getLogger(RepositoryPropertyHandler.class);

  public static final String ZURUECK = BundleHelper.getBundleString("zurueck"); //$NON-NLS-1$
  public static final String WEITER = BundleHelper.getBundleString("weiter"); //$NON-NLS-1$
  public static final String RESET = BundleHelper.getBundleString("abbruch"); //$NON-NLS-1$
  public static final String NEU = BundleHelper.getBundleString("neu"); //$NON-NLS-1$
  public static final String RESET_COLOR = BundleHelper.getBundleString("Reset_color"); //$NON-NLS-1$

  private transient AdminHandling _admHandling;
  private transient AnwenderHandling _anwHandling;
  private transient AuswertungHandling _auswertungHandling;
  private transient EingangHandling _eingangHandling;
  private transient ExportHandling _exportHandling;
  private transient GebietHandling _gebietHandling;
  private transient ImportHandling _importHandling;
  private transient PropertyHandling _propHandling;
  private transient StateHandling _stateHandling;
  private transient VotesHandling _votesHandling;

  protected void handlePropEingabeAllg(HttpServletRequest request) {
    String gotoModus = request.getParameter(ApplicationBeanKonstanten.PREFIX + "propuebernehmen"); //$NON-NLS-1$
    handlePropEingabeAllg(request, Konstanten.PROP_ALLG);
    if (RESET_COLOR.equals(gotoModus)) {
      resetColor(request);
    }
  }

  private void resetColor(HttpServletRequest request) {
    AnwContext anwContext = getAnwContext(request);
    AdminHandling adminHandling = getAdminHandling();

    adminHandling.setProperty(anwContext,
        Konstanten.PROP_BACKGROUND_COLOR_RED,
        String.valueOf(Konstanten.DEFAULT_BACKGROUND_COLOR_GREY));
    adminHandling.setProperty(anwContext,
        Konstanten.PROP_BACKGROUND_COLOR_GREEN,
        String.valueOf(Konstanten.DEFAULT_BACKGROUND_COLOR_GREY));
    adminHandling.setProperty(anwContext,
        Konstanten.PROP_BACKGROUND_COLOR_BLUE,
        String.valueOf(Konstanten.DEFAULT_BACKGROUND_COLOR_GREY));

  }

  /**
   * Verarbeitet das abgesendete Formular der JSP f�r Properties Dabei wird auf das in Konstanten
   * definieret Array von Bezeichnern zur�ckgegriffen
   * 
   * @param request der HttpRequest mit den submitteten Werten
   */
  protected int handlePropEingabeAllg(HttpServletRequest request,
      Map<String, List<Basiseinstellung>> propAllg,
      int modus) {
    handlePropEingabeAllg(request, propAllg);
    if (_adminMsgProps == null
        || _adminMsgProps.isEmpty()
        || Messages.getString(MessageKeys.Msg_ErfolgreichUebernommen)
            .equals(_adminMsgPropsConfirmation)) {
      // In this case, reset _admMsgProps for export
      _adminMsgProps = ""; //$NON-NLS-1$
      _adminMsgPropsConfirmation = null;
      return setNewMode(request, modus);
    }
    return modus;
  }

  protected void handlePropEingabeAllg(HttpServletRequest request,
      Map<String, List<Basiseinstellung>> propAllg) {
    DATE_FORMAT.setLenient(false);
    TIME_FORMAT.setLenient(false);
    _adminMsgExport = ""; //$NON-NLS-1$
    _adminMsgProps = ""; //$NON-NLS-1$
    _adminMsgPropsConfirmation = null;
    AnwContext anwContext = getAnwContext(request);
    AdminHandling adminHandling = getAdminHandling();

    int subwork = getIntParameter(request.getParameter(PREFIX + "subwork"), 0); //$NON-NLS-1$
    String subworkKey = new ArrayList<String>(propAllg.keySet()).get(subwork);

    HttpSession session = request.getSession();
    boolean restart = false;
    for (Basiseinstellung basiseinstellung : propAllg.get(subworkKey)) {
      try {

        String property = basiseinstellung.getProperty();
        String parameterName = PREFIX + property;
        String wert = request.getParameter(parameterName);
        // Leere Eingabefelder werden als null gesetzt!
        if (wert != null && wert.trim().length() == 0) {
          if (basiseinstellung.isPflichtfeld()) {
            String errorMsg = Messages.bind(MessageKeys.Error_0_mussEingetragenWerden,
                basiseinstellung.getBeschreibung());
            addErrorMessage(errorMsg);
          }
          // wert = null;
        }

        // wurde der Wert ge�ndert?
        String sessionWert = (String) session.getAttribute(property);
        switch (basiseinstellung.getTyp()) {
          case RelURL :
          case String :
          case Textarea :
            // steht im Schl�ssel der Property "dir" wird, falls ben�tigt, ein
            // / angeh�ngt
            if (property.toLowerCase().indexOf("dir") != -1) { //$NON-NLS-1$
              if (wert != null) {
                if (!wert.endsWith("/")) { //$NON-NLS-1$
                  wert += "/"; //$NON-NLS-1$
                }
                File file = new File(wert);
                file.mkdirs();
                if (!file.isDirectory()) {
                  String errorMsg = Messages
                      .bind(MessageKeys.Error_Verzeichnis_0_KonnteNichtAngelegtWerden, wert);
                  addErrorMessage(errorMsg);
                  throw new Exception(
                      Messages.getString(MessageKeys.Error_VerzeichnisNichtAngelegt));
                }
              }
            }
            if (basiseinstellung.getRegex() != null && !wert.matches(basiseinstellung.getRegex())) {
              String errorMsg = Messages.bind(basiseinstellung.getRegexErrorMsgKey(), wert);
              addErrorMessage(errorMsg);
              LOGGER.warn(errorMsg);
              break;
            }
            if (sessionWert == null ? wert != null : !sessionWert.equals(wert)) {
              if (wert != null && wert.length() > RepositoryModel.WERT_LENGTH) {
                addErrorMessage(Messages
                    .bind(MessageKeys.Error_FeldlaengeUeberschreitetSpaltenbreite_0,
                        RepositoryModel.WERT_LENGTH));
              }
              adminHandling.setProperty(anwContext, property, wert);
              restart |= basiseinstellung.isRestart();
            }
            break;

          case Integer :
            if (basiseinstellung.getRegex() != null && !wert.matches(basiseinstellung.getRegex())) {
              String errorMsg = Messages.bind(basiseinstellung.getRegexErrorMsgKey(), wert);
              addErrorMessage(errorMsg);
              LOGGER.warn(errorMsg);
              break;
            }
            try {
              wert = Integer.toString(parseInt(wert));
              if (sessionWert == null ? wert != null : !sessionWert.equals(wert)) {
                adminHandling.setProperty(anwContext, property, wert);
                restart |= basiseinstellung.isRestart();
              }
            } catch (Exception ex) {
              LOGGER.warn(Messages.bind(MessageKeys.Error_KeineZahl_0_ignoriert, wert));
            }

            if (Konstanten.PROP_DOUBLE_INPUT.equals(property)
                && InputMode.fromProperty(getPropertyHandling()
                    .getProperty(Konstanten.PROP_DOUBLE_INPUT)).isSingleInput()
                && getPropertyHandling().getBooleanProperty(Konstanten.PROP_IS_INPUT_MODE_COMPLETE)) {
              // If single input && complete input is active, set input mode to double input
              adminHandling.setProperty(anwContext,
                  Konstanten.PROP_DOUBLE_INPUT,
                  InputMode.INPUT_MODE_DOUBLE.getProperty());
              String errorMsg = Messages
                  .bind(MessageKeys.Error_SingleInputAndCompleteInputNotAllowed, wert);
              LOGGER.warn(errorMsg);
              addErrorMessage(errorMsg);
            }
            break;
          case Date :
            try {
              if (StringUtils.isEmpty(wert)) {
                wert = null;
              }
              if (wert == null || Pattern.matches(DATE_REGEX, wert)) {
                if (wert != null && !wert.isEmpty()) {
                  synchronized (DATE_FORMAT) {
                    DATE_FORMAT.parse(wert);
                  }
                }
                if (sessionWert == null ? wert != null : !sessionWert.equals(wert)) {
                  adminHandling.setProperty(anwContext, property, wert);
                  restart |= basiseinstellung.isRestart();
                }
              } else {
                String errorMsg = Messages.bind(MessageKeys.Error_UngueltigesDatumsFormat, wert);
                addErrorMessage(errorMsg);
                LOGGER.warn(errorMsg);
              }
            } catch (Exception ex) {
              String errorMsg = Messages.bind(MessageKeys.Error_UngueltigesDatumsFormat, wert);
              addErrorMessage(errorMsg);
              LOGGER.error(errorMsg, ex);
            }
            break;
          case Time :
            try {
              if (Pattern.matches(TIME_REGEX, wert)) {
                if (wert != null && !wert.isEmpty()) {
                  synchronized (TIME_FORMAT) {
                    TIME_FORMAT.parse(wert);
                  }
                }
                if (sessionWert == null ? wert != null : !sessionWert.equals(wert)) {
                  adminHandling.setProperty(anwContext, property, wert);
                  restart |= basiseinstellung.isRestart();
                }
              } else {
                String errorMsg = Messages.bind(MessageKeys.Error_UngueltigesZeitFormat, wert);
                addErrorMessage(errorMsg);
                LOGGER.warn(errorMsg);
              }
            } catch (Exception ex) {
              String errorMsg = Messages.bind(MessageKeys.Error_UngueltigesZeitFormat, wert);
              addErrorMessage(errorMsg);
              LOGGER.error(errorMsg, ex);
            }
            break;

          case Boolean :
            boolean bWert = wert != null;
            boolean alterBWert = parseBoolean(sessionWert);
            if (bWert != alterBWert) {
              adminHandling.setProperty(anwContext, property, bWert);
              if (Konstanten.PROP_IS_INPUT_MODE_COMPLETE.equals(property)) {
                if (bWert) {
                  WahlInfo.getWahlInfo().getWahl()
                      .setAktuelleWahlergebnisart(WahlModel.INPUT_MODE_COMPLETE);
                } else {
                  WahlInfo.getWahlInfo().getWahl()
                      .setAktuelleWahlergebnisart(WahlModel.INPUT_MODE_GROUPS);
                }
                WahlInfo.getWahlInfo().getWahl()
                    .setLetzteAenderung(new Timestamp(System.currentTimeMillis()));
                WahlInfo.getWahlInfo().synchronize();
                getStateHandling().getGebietsBaum(anwContext);
                ExportP4Bean exp4Bean = (ExportP4Bean) session.getAttribute("expP4Bean"); //$NON-NLS-1$
                if (exp4Bean != null) {
                  exp4Bean.initP4ExportState();
                }
              }
              restart |= basiseinstellung.isRestart();
            }
            break;
          case ZIP :
            try {
              if (StringUtils.isEmpty(wert) || Pattern.matches(ZIP_REGEX, wert)) {
                if (sessionWert == null ? wert != null : !sessionWert.equals(wert)) {
                  adminHandling.setProperty(anwContext, property, wert);
                  restart |= basiseinstellung.isRestart();
                }
              } else {
                String errorMsg = Messages.bind(MessageKeys.Error_UngueltigesZipFormat, wert);
                addErrorMessage(errorMsg);
                LOGGER.warn(errorMsg);
              }
            } catch (Exception ex) {
              String errorMsg = Messages.bind(MessageKeys.Error_UngueltigesZipFormat, wert);
              addErrorMessage(errorMsg);
              LOGGER.error(errorMsg, ex);
            }
            break;

          default :
            throw new RuntimeException(
                Messages.getString(MessageKeys.Error_NichtUnterstuetztesEingabeformat));
        }
      } catch (Exception e) {
        LOGGER.error(e, e);
      }
    }
    String restartAttribute = (String) session.getAttribute("restart"); //$NON-NLS-1$
    if (restartAttribute == null || (!parseBoolean(restartAttribute) && restart)) {
      session.setAttribute("restart", Boolean.toString(restart)); //$NON-NLS-1$
    }
    if (_adminMsgProps.isEmpty()) {
      _adminMsgPropsConfirmation = Messages.getString(MessageKeys.Msg_ErfolgreichUebernommen);
    }

  }

  private void addErrorMessage(String errorMsg) {
    _adminMsgProps += errorMsg + Konstanten.BR;
  }

  /**
   * @param request
   */
  protected int setNewMode(HttpServletRequest request, int modus) {
    String gotoModus = request.getParameter(ApplicationBeanKonstanten.PREFIX + "uebernehmen"); //$NON-NLS-1$
    if (ZURUECK.equals(gotoModus)) {
      return modus - 1;
    } else if (WEITER.equals(gotoModus)) {
      return modus + 1;
    }
    return modus;
  }

  /**
   * @param request
   * @return
   */
  protected ReportOutputFormatEnum getRtfPdf(HttpServletRequest request) {
    String rtfPdf = request.getParameter(ApplicationBeanKonstanten.PREFIX + "RTF_PDF"); //$NON-NLS-1$
    if ("rtf".equals(rtfPdf)) { //$NON-NLS-1$
      getPropertyHandling().setProperty(Konstanten.PROP_LAST_EXPORT_FORMAT, rtfPdf);
      return ReportOutputFormatEnum.RTF;
    } else if ("pdf".equals(rtfPdf)) { //$NON-NLS-1$
      getPropertyHandling().setProperty(Konstanten.PROP_LAST_EXPORT_FORMAT, rtfPdf);
      return ReportOutputFormatEnum.PDF;
    } else {
      throw new RuntimeException(
          Messages.getString(MessageKeys.Error_No_ReportOutputFormatEnum_Defined));
    }
  }

  /**
   * @param request
   * @return
   */
  protected boolean isExportForCentralCounting(HttpServletRequest request) {
    String value = request.getParameter(ApplicationBeanKonstanten.PREFIX
        + "EXPORTS_FOR_CENTRAL_COUNTING"); //$NON-NLS-1$
    if ("no_central_counting".equals(value)) { //$NON-NLS-1$
      getPropertyHandling().setProperty(Konstanten.PROP_EXPORTS_FOR_CENTRAL_COUNTING, value);
      return false;
    } else if ("central_counting".equals(value)) { //$NON-NLS-1$
      getPropertyHandling().setProperty(Konstanten.PROP_EXPORTS_FOR_CENTRAL_COUNTING, value);
      return true;
    } else {
      return false;
    }
  }

  public AdminHandling getAdminHandling() {
    if (_admHandling == null) {
      _admHandling = lookupLocal(AdminHandlingBean.class.getSimpleName());
    }
    return _admHandling;
  }

  protected PropertyHandling getPropertyHandling() {
    if (_propHandling == null) {
      _propHandling = lookupLocal(PropertyHandlingBean.class.getSimpleName());
    }
    return _propHandling;
  }

  public AnwenderHandling getAnwenderHandling() {
    if (_anwHandling == null) {
      _anwHandling = lookupLocal(AnwenderHandlingBean.class.getSimpleName());
    }
    return _anwHandling;
  }

  protected StateHandling getStateHandling() {
    if (_stateHandling == null) {
      _stateHandling = lookupLocal(StateHandlingBean.class.getSimpleName());
    }
    return _stateHandling;
  }

  protected ImportHandling getImportHandling() {
    if (_importHandling == null) {
      _importHandling = lookupLocal(ImportHandlingBean.class.getSimpleName());
    }
    return _importHandling;
  }

  protected EingangHandling getEingangHandling() {
    if (_eingangHandling == null) {
      _eingangHandling = lookupLocal(EingangHandlingBean.class.getSimpleName());
    }
    return _eingangHandling;
  }

  protected ExportHandling getExportHandling() {
    if (_exportHandling == null) {
      _exportHandling = lookupLocal(ExportHandlingBean.class.getSimpleName());
    }
    return _exportHandling;
  }

  protected AuswertungHandling getAuswertungHandling() {
    if (_auswertungHandling == null) {
      _auswertungHandling = lookupLocal(AuswertungHandlingBean.class.getSimpleName());
    }
    return _auswertungHandling;
  }

  protected GebietHandling getGebietHandling() {
    if (_gebietHandling == null) {
      _gebietHandling = lookupLocal(GebietHandlingBean.class.getSimpleName());
    }
    return _gebietHandling;
  }

  protected VotesHandling getVotesHandling() {
    if (_votesHandling == null) {
      _votesHandling = lookupLocal(VotesHandlingBean.class.getSimpleName());
    }
    return _votesHandling;
  }

  public String getProperty(String name) throws EJBException {
    return getAdminHandling().getProperty(name);
  }

  public URI getBaseURI() {
    return ((ContextHandling) lookupLocal(ContextHandlingBean.class.getSimpleName())).getBaseURI();
  }
}
