package de.ivu.wahl.client.util;

import static java.lang.Integer.parseInt;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.ivu.util.session.SessionState;
import de.ivu.util.session.SessionStateException;
import de.ivu.util.session.Step;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.client.beans.ApplicationBean;
import de.ivu.wahl.client.beans.ApplicationBeanKonstanten;
import de.ivu.wahl.client.beans.Command;
import de.ivu.wahl.dataimport.XMLTags;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;
import de.ivu.wahl.util.BundleHelper;

/**
 * Statische Hilfsklassen für Client servlets und pages
 * 
 * @author klie@ivu.de, mur@ivu.de
 */

public class ClientHelper {
  private static final char AMP_CH = '&';
  private static final String EQUALS = "="; //$NON-NLS-1$
  private static final String AMP = "&"; //$NON-NLS-1$
  private static final String QUESTION_MARK = "?"; //$NON-NLS-1$
  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  private static final String SINGLE_SPACE = " "; //$NON-NLS-1$
  private static final String NBSP = "&nbsp;"; //$NON-NLS-1$
  private static final String AMP_ENTITY = "&amp;"; //$NON-NLS-1$
  private static final int AMP_LENGTH = AMP_ENTITY.length();

  private static final SimpleDateFormat EXPORT_FORMAT = new SimpleDateFormat(
      "yyyy-MM-dd'T'HH:mm:ss");//$NON-NLS-1$
  private static final SimpleDateFormat EXPORT_YEAR_FORMAT = new SimpleDateFormat("yyyy"); //$NON-NLS-1$
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");//$NON-NLS-1$
  protected static final SimpleDateFormat STFM = new SimpleDateFormat("HH:mm:ss"); //$NON-NLS-1$
  protected static final SimpleDateFormat SDTFM = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); //$NON-NLS-1$
  public static final DecimalFormat DF = new DecimalFormat("###,###"); //$NON-NLS-1$

  public static char getAnfBuchstabeOhneUmlaut(char in) {
    char ret = in;
    char inu = Character.toUpperCase(in);
    if (inu == '\u00C4') { // Ä
      return 'A';
    }
    if (inu == '\u00DC') { // Ü
      return 'U';
    }
    if (inu == '\u00D6') { // Ö
      return 'O';
    }
    return ret;
  }

  protected final static Logger LOGGER = Logger.getLogger(ClientHelper.class);

  /**
   * Eine Methode mit einem seltsamen Namen
   * 
   * @param key Schlüssel für den String-Eintrag in der Übersetzungstabelle
   * @return Übersetzung in der Sprache/Version der Locale der Anwendung
   * @deprecated Use {@link BundleHelper#getBundleString(String)} instead
   */
  @Deprecated
  public static String getBundleString(String key) {
    return BundleHelper.getBundleString(key);
  }

  /**
   * versucht den übergebenen Wert zu parsen
   * 
   * @param val zu parsender String
   * @return String als int
   * @throws NumberFormatException Exception
   */
  public static int getIntParameter(String val) throws NumberFormatException {
    return parseInt(val.replace('_', ' ').trim());
  }

  /**
   * Versucht den übergebenen Wert zu parsen und liefrt im Fehlerfall den default zurück
   * 
   * @param val zu parsender String-Wert
   * @param defaultValue Vorgabewert
   * @return int-Wert des Strings oder anderweitig der Vorgabewert
   */
  public static int getIntParameter(String val, int defaultValue) {
    if (val == null) {
      return defaultValue;
    }
    try {
      return parseInt(val.trim());
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * Escape characters for text appearing in HTML markup.
   * <P>
   * This method exists as a defence against Cross Site Scripting (XSS) hacks. The idea is to
   * neutralize control characters commonly used by scripts, such that they will not be executed by
   * the browser. This is done by replacing the control characters with their escaped equivalents.
   * See {@link hirondelle.web4j.security.SafeText} as well.
   * <P>
   * The following characters are replaced with corresponding HTML character entities :
   * <table border='1' cellpadding='3' cellspacing='0'>
   * <tr>
   * <th>Character</th>
   * <th>Replacement</th>
   * </tr>
   * <tr>
   * <td><</td>
   * <td>&lt;</td>
   * </tr>
   * <tr>
   * <td>></td>
   * <td>&gt;</td>
   * </tr>
   * <tr>
   * <td>&</td>
   * <td>&amp;</td>
   * </tr>
   * <tr>
   * <td>"</td>
   * <td>&quot;</td>
   * </tr>
   * <tr>
   * <td>\t</td>
   * <td>&#009;</td>
   * </tr>
   * <tr>
   * <td>!</td>
   * <td>&#033;</td>
   * </tr>
   * <tr>
   * <td>#</td>
   * <td>&#035;</td>
   * </tr>
   * <tr>
   * <td>$</td>
   * <td>&#036;</td>
   * </tr>
   * <tr>
   * <td>%</td>
   * <td>&#037;</td>
   * </tr>
   * <tr>
   * <td>'</td>
   * <td>&#039;</td>
   * </tr>
   * <tr>
   * <td>(</td>
   * <td>&#040;</td>
   * </tr>
   * <tr>
   * <td>)</td>
   * <td>&#041;</td>
   * </tr>
   * <tr>
   * <td></td>
   * <td>&#042;</td>
   * </tr>
   * <tr>
   * <td>+</td>
   * <td>&#043;</td>
   * </tr>
   * <tr>
   * <td>,</td>
   * <td>&#044;</td>
   * </tr>
   * <tr>
   * <td>-</td>
   * <td>&#045;</td>
   * </tr>
   * <tr>
   * <td>.</td>
   * <td>&#046;</td>
   * </tr>
   * <tr>
   * <td>/</td>
   * <td>&#047;</td>
   * </tr>
   * <tr>
   * <td>:</td>
   * <td>&#058;</td>
   * </tr>
   * <tr>
   * <td>;</td>
   * <td>&#059;</td>
   * </tr>
   * <tr>
   * <td>=</td>
   * <td>&#061;</td>
   * </tr>
   * <tr>
   * <td>?</td>
   * <td>&#063;</td>
   * </tr>
   * <tr>
   * <td>@</td>
   * <td>&#064;</td>
   * </tr>
   * <tr>
   * <td>[</td>
   * <td>&#091;</td>
   * </tr>
   * <tr>
   * <td>\</td>
   * <td>&#092;</td>
   * </tr>
   * <tr>
   * <td>]</td>
   * <td>&#093;</td>
   * </tr>
   * <tr>
   * <td>^</td>
   * <td>&#094;</td>
   * </tr>
   * <tr>
   * <td>_</td>
   * <td>&#095;</td>
   * </tr>
   * <tr>
   * <td>`</td>
   * <td>&#096;</td>
   * </tr>
   * <tr>
   * <td>{</td>
   * <td>&#123;</td>
   * </tr>
   * <tr>
   * <td>|</td>
   * <td>&#124;</td>
   * </tr>
   * <tr>
   * <td></td>
   * <td>&#125;</td>
   * </tr>
   * <tr>
   * <td>~</td>
   * <td>&#126;</td>
   * </tr>
   * </table>
   * <P>
   * Note that JSTL's {@code <c:out>} escapes <em>only the first 
   * five</em> of the above characters.
   */
  public static String forHTML(String aText) {
    if (aText == null) {
      return ""; //$NON-NLS-1$
    }
    final StringBuilder result = new StringBuilder();
    final StringCharacterIterator iterator = new StringCharacterIterator(aText);
    char character = iterator.current();
    char characterBefore = 'a';
    while (character != CharacterIterator.DONE) {
      if (character == ' ' && characterBefore == ' ') {
        result.append("&nbsp;"); //$NON-NLS-1$
      } else if (character == '<') {
        result.append("&lt;"); //$NON-NLS-1$
      } else if (character == '>') {
        result.append("&gt;"); //$NON-NLS-1$
      } else if (character == '&') {
        result.append("&amp;"); //$NON-NLS-1$
      } else if (character == '\"') {
        result.append("&quot;"); //$NON-NLS-1$
      } else if (character == '\t') {
        addCharEntity(9, result);
      } else if (character == '!') {
        addCharEntity(33, result);
      } else if (character == '#') {
        addCharEntity(35, result);
      } else if (character == '$') {
        addCharEntity(36, result);
      } else if (character == '%') {
        addCharEntity(37, result);
      } else if (character == '\'') {
        addCharEntity(39, result);
      } else if (character == '(') {
        addCharEntity(40, result);
      } else if (character == ')') {
        addCharEntity(41, result);
      } else if (character == '*') {
        addCharEntity(42, result);
      } else if (character == '+') {
        addCharEntity(43, result);
      } else if (character == ',') {
        addCharEntity(44, result);
      } else if (character == '-') {
        addCharEntity(45, result);
      } else if (character == '.') {
        addCharEntity(46, result);
      } else if (character == '/') {
        addCharEntity(47, result);
      } else if (character == ':') {
        addCharEntity(58, result);
      } else if (character == ';') {
        addCharEntity(59, result);
      } else if (character == '=') {
        addCharEntity(61, result);
      } else if (character == '?') {
        addCharEntity(63, result);
      } else if (character == '@') {
        addCharEntity(64, result);
      } else if (character == '[') {
        addCharEntity(91, result);
      } else if (character == '\\') {
        addCharEntity(92, result);
      } else if (character == ']') {
        addCharEntity(93, result);
      } else if (character == '^') {
        addCharEntity(94, result);
      } else if (character == '_') {
        addCharEntity(95, result);
      } else if (character == '`') {
        addCharEntity(96, result);
      } else if (character == '{') {
        addCharEntity(123, result);
      } else if (character == '|') {
        addCharEntity(124, result);
      } else if (character == '}') {
        addCharEntity(125, result);
      } else if (character == '~') {
        addCharEntity(126, result);
      } else {
        // the char is not a special one
        // add it to the result as is
        result.append(character);
      }
      characterBefore = character;
      character = iterator.next();
    }
    return result.toString();
  }

  private static void addCharEntity(Integer aIdx, StringBuilder aBuilder) {
    String padding = EMPTY_STRING;
    if (aIdx <= 9) {
      padding = "00"; //$NON-NLS-1$
    } else if (aIdx <= 99) {
      padding = "0"; //$NON-NLS-1$
    } else {
      // no prefix
    }
    String number = padding + aIdx.toString();
    aBuilder.append("&#" + number + ";"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public static String forHTML(String aText, boolean useHtmlLineBreaks) {
    if (useHtmlLineBreaks) {
      return htmlLineBreaks(forHTML(aText));
    }
    return forHTML(aText);
  }

  public static String htmlLineBreaks(final String text) {
    String result = text;
    result = result.replaceAll("&lt;br/&gt;", "<br/>"); //$NON-NLS-1$ //$NON-NLS-2$
    result = result.replaceAll("&lt;br&#047;&gt;", "<br/>"); //$NON-NLS-1$ //$NON-NLS-2$
    result = result.replaceAll("&lt;br /&gt;", "<br />"); //$NON-NLS-1$ //$NON-NLS-2$
    result = result.replaceAll("&lt;br &#047;&gt;", "<br />"); //$NON-NLS-1$ //$NON-NLS-2$
    return result;
  }

  /**
   * @param request HTTP-Request
   * @return Bezeichnung der aktuellenKonfiguration in Bezug auf
   *         Nachwahl/Erststimmengleichheit/Restanteilsgleichheit ACHTUNG: Etwas unsauber aber wegen
   *         der Multiplizität der Ausgabe gerechtfertigt: Hintergrundfarbe wird in dieser Methode
   *         festgelegt
   */
  public static String getKonfigurationsString(HttpServletRequest request) {
    String result = getApplicationBean(request).getKonfigurationsString(request);
    result = "<p style='background-color:#FC0;'>" + result + "</p>"; //$NON-NLS-1$ //$NON-NLS-2$

    return result;
  }

  /**
   * @param request HTTP-Request
   * @return Bezeichnung der aktuellenKonfiguration in Bezug auf
   *         Nachwahl/Erststimmengleichheit/Restanteilsgleichheit ACHTUNG: Etwas unsauber aber wegen
   *         der Multiplizität der Ausgabe gerechtfertigt: Hintergrundfarbe wird in dieser Methode
   *         festgelegt
   */
  public static String getKonfigurationsStringDrucker(HttpServletRequest request) {
    String result = getApplicationBean(request).getKonfigurationsString(request);

    return result;
  }

  /**
   * @return ApplicationBean aus der Session
   */
  private static ApplicationBean getApplicationBean(HttpServletRequest request) {
    return (ApplicationBean) request.getSession(false).getAttribute("appBean"); //$NON-NLS-1$
  }

  /**
   * liefert einen boolean-Wert "1" = true / "0" = false
   * 
   * @param view
   * @return Boolean
   * @exception RuntimeException Exception
   */
  public static boolean getBooleanParameter(String view) throws RuntimeException {
    return Integer.parseInt(view) == 1;
  }

  /**
   * liefert einen boolean-Wert "1" = true / "0" = false Im fehlerfall liefert es den übergebenen
   * Defaultwert
   * 
   * @param view
   * @param defaultwert
   * @return Boolean
   * @exception RuntimeException Exception
   */
  public static boolean getBooleanParameter(String view, boolean defaultwert)
      throws RuntimeException {
    if (view == null) {
      return defaultwert;
    }
    try {
      return Integer.parseInt(view) == 1;
    } catch (NumberFormatException e) {
      return defaultwert;
    }
  }

  public static String getSuffix(int view) {
    return ApplicationBeanKonstanten.VIEWIS + view;
  }

  public static String getSuffix(HttpServletRequest request) {
    String view = request.getParameter(ApplicationBeanKonstanten.VIEW);
    return ApplicationBeanKonstanten.VIEWIS + view;
  }

  public static String getSuffixLevel(int view, int level) {
    return ApplicationBeanKonstanten.VIEWIS + view + AMP + ApplicationBeanKonstanten.LEVELIS
        + level;
  }

  public static String getSuffixLevel(HttpServletRequest request, int level) {
    String view = request.getParameter(ApplicationBeanKonstanten.VIEW);
    return ApplicationBeanKonstanten.VIEWIS + view + AMP + ApplicationBeanKonstanten.LEVELIS
        + level;
  }

  public static String getSuffixWork(HttpServletRequest request, int work) {
    String view = request.getParameter(ApplicationBeanKonstanten.VIEW);
    String level = request.getParameter(ApplicationBeanKonstanten.LEVEL);

    return ApplicationBeanKonstanten.VIEWIS + view + AMP + ApplicationBeanKonstanten.LEVELIS
        + level + AMP + ApplicationBeanKonstanten.WORKIS + work;
  }

  /**
   * Liefert den String mit allen Parametern und Werten des requestrt
   * 
   * @param request HttpServletRequest
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  public static String getAllParameters(HttpServletRequest request) {
    return getAllParameters(request, true);
  }

  /**
   * Liefert den String mit allen Parametern und Werten des requests
   * 
   * @param request HttpServletRequest
   * @param cleanPrefix bollean, ob Prefix in URL gelöscht werden soll
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  public static String getAllParameters(HttpServletRequest request, boolean cleanPrefix) {
    return getAllParameters(null, null, request, cleanPrefix);
  }

  /**
   * Liefert den String mit allen Parametern und Werten des request, außer dem Parameter der
   * ignoriert werden soll. Wird für den ignoreParameter null übergeben, werden alle Parameter und
   * Werte geliefert
   * 
   * @param request HttpServletRequest
   * @param ignoreParameter Parameter dessen Wert nicht zurückgeliefert werden soll
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  public static String getAllParameters(HttpServletRequest request, String ignoreParameter) {
    return getAllParameters(request, ignoreParameter, true);
  }

  /**
   * Liefert den String mit allen Parametern und Werten des request, außer dem Parameter der
   * ignoriert werden soll. Wird für den ignoreParameter null übergeben, werden alle Parameter und
   * Werte geliefert
   * 
   * @param request HttpServletRequest
   * @param ignoreParameter Parameter dessen Wert nicht zurückgeliefert werden soll
   * @param cleanPrefix bollean, ob Prefix in URL gelöscht werden soll
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  public static String getAllParameters(HttpServletRequest request,
      String ignoreParameter,
      boolean cleanPrefix) {

    List<String> list = new ArrayList<String>();
    list.add(ignoreParameter);
    return getAllParameters(request, list, cleanPrefix);
  }

  /**
   * Liefert den String mit allen Parametern und Werten des request, außer dem Parameter der
   * ignoriert werden soll. Wird für den ignoreParameter null übergeben, werden alle Parameter und
   * Werte geliefert
   * 
   * @param request HttpServletRequest
   * @param ignoreParameters Parameter deren Wert nicht zurückgeliefert werden soll
   * @param cleanPrefix bollean, ob Prefix in URL gelöscht werden soll
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  public static String getAllParameters(HttpServletRequest request,
      List<String> ignoreParameters,
      boolean cleanPrefix) {

    return getAllParameters(ignoreParameters, null, request, cleanPrefix);
  }

  /**
   * Liefert den String mit allen Parametern und Werten des request, außer dem Parameter der
   * ignoriert werden soll. Wird für den ignoreParameter null übergeben, werden alle Parameter und
   * Werte geliefert
   * 
   * @param request HttpServletRequest
   * @param ignoreParameterPrefix Präfix für Parameter deren Wert nicht zurückgeliefert werden soll
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  public static String getParametersDoNotStartWith(HttpServletRequest request,
      String ignoreParameterPrefix) {

    return getParametersDoNotStartWith(request, ignoreParameterPrefix, true);
  }

  /**
   * Liefert den String mit allen Parametern und Werten des request, außer dem Parameter der
   * ignoriert werden soll. Wird für den ignoreParameter null übergeben, werden alle Parameter und
   * Werte geliefert
   * 
   * @param request HttpServletRequest
   * @param ignoreParameterPrefix Präfix für Parameter deren Wert nicht zurückgeliefert werden soll
   * @param clearPrefix bollean, ob Prefix in URL gelöscht werden soll
   * @return ein String, welcher an eine URL gehängt werden kann.
   */
  public static String getParametersDoNotStartWith(HttpServletRequest request,
      String ignoreParameterPrefix,
      boolean clearPrefix) {

    return getAllParameters(null, ignoreParameterPrefix, request, clearPrefix);
  }

  /**
   * Schnelle Lösung zum zusätzlichen Entfernen des Work-Parameters
   * 
   * @param request HTTP-Request
   * @param ignoreParameterPrefix Präfix für Parameter deren Wert nicht zurückgeliefert werden soll
   * @return ein String, welcher an eine URL gehängt werden kann.
   */
  public static String getParametersDoNotStartWithAndWithoutWork(HttpServletRequest request,
      String ignoreParameterPrefix) {

    List<String> workList = new ArrayList<String>();
    workList.add(ApplicationBeanKonstanten.WORK);
    return getAllParameters(workList, ignoreParameterPrefix, request, true);
  }

  /**
   * Liefert den String mit allen Parametern und Werten des request, außer dem Parameter der
   * ignoriert werden soll. Wird für den ignoreParameter null übergeben, werden alle Parameter und
   * Werte geliefert
   * 
   * @param ignoreParameter Parameter dessen Wert nicht zurückgeliefert werden soll
   * @param ignoreParameterPrefix Präfix für Parameter deren Wert nicht zurückgeliefert werden soll
   * @param request HttpServletRequest
   * @param cleanPrefix bollean, ob Prefix in URL gelöscht werden soll
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  private static String getAllParameters(List<String> ignoreParameters,
      String ignoreParameterPrefix,
      HttpServletRequest request,
      boolean cleanPrefix) {

    StringBuilder all = new StringBuilder();
    Enumeration<?> enumeration = request.getParameterNames();
    boolean first = true;
    while (enumeration.hasMoreElements()) {
      String paramName = enumeration.nextElement().toString();
      if (!isIgnorable(paramName, cleanPrefix) && !isIgnorable(ignoreParameters, paramName)
          && !isIgnorable(ignoreParameterPrefix, paramName)) {
        if (!first) {
          all.append(AMP);
        }
        all.append(encodeURL(paramName));
        all.append('=');
        all.append(encodeURL(request.getParameter(paramName)));
        first = false;
      }
    }
    return all.toString();
  }

  private static String encodeURL(String paramName) {
    try {
      return URLEncoder.encode(paramName, Konstanten.ENCODING);
    } catch (UnsupportedEncodingException e) {
      // Bad JVM, no UTF-8 (guaranteed!)
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * Commands werden nur beim ersten Aufruf benötigt. HMAC's müssen immer entfern werden, da sie neu
   * berechnet werden StepId muss immer neu hinzugefügt werden über den Parameter cleanPrefix wird
   * entschieden, ob die Prefixparameter entfernt werden
   */

  private static boolean isIgnorable(String paramName, boolean clearPrefix) {
    return paramName.startsWith("cmd") || paramName.equalsIgnoreCase("HMAC") //$NON-NLS-1$ //$NON-NLS-2$
        || paramName.equalsIgnoreCase(Step.DEFAULT_PARAMETER_NAME)
        || paramName.equalsIgnoreCase(ApplicationBean.USER_ATTRIBUTE)
        || paramName.equalsIgnoreCase(ApplicationBean.PASSWORD_ATTRIBUTE)
        || paramName.equalsIgnoreCase("x") || paramName.equalsIgnoreCase("y") //$NON-NLS-1$ //$NON-NLS-2$
        || (clearPrefix && paramName.startsWith(ApplicationBeanKonstanten.PREFIX));
  }

  private static boolean isIgnorable(List<String> ignoreParameters, String paramName) {
    return ignoreParameters != null && ignoreParameters.contains(paramName);
  }

  private static boolean isIgnorable(String ignoreParameters, String paramName) {
    return (ignoreParameters != null && paramName.startsWith(ignoreParameters));
  }

  public final static String SESSION = "SESSION"; //$NON-NLS-1$
  public final static String STEP = "STEP"; //$NON-NLS-1$

  /**
   * key für das Attribut im request, welches den gerade aktuellen Step hält. Alle Aktionen finden
   * auf diesem Step statt!
   */
  public final static String CURRENT_STEP = "CURRENT_STEP"; //$NON-NLS-1$

  /** key für das Attribut in der session, welches den gerade aktuellen MAC Schlüssel hält. */
  public final static String MAC_KEY = "de.ivu.wahl.MAC_KEY"; //$NON-NLS-1$

  protected final static SecureRandom random = new SecureRandom();

  private static boolean __firstNSAE = true;

  private static boolean __firstGSE = true;

  /**
   * @return sicheren Zufallszahlengenerator
   */
  public static SecureRandom getSecureRandom() {
    return random;
  }

  /**
   * Ermittelt den SessionState für die Anwendung
   * 
   * @param request HTTP request
   * @return The sessionState value
   * @exception ServletException Description of Exception
   */
  public static SessionState getSessionState(HttpServletRequest request) throws ServletException {

    HttpSession session = (HttpSession) request.getAttribute(SESSION);

    SessionState state = (SessionState) session.getAttribute(SessionState.DEFAULT_KEY);
    if (state == null) {
      // this is a serious software error and may not happen during normal use
      throw new ServletException(Messages.getString(MessageKeys.Error_ThereIsNoSessionState));
    }
    return state;
  }

  /**
   * Returns the current step
   * 
   * @param request HTTP request
   * @return The current step
   */
  public static Step getStep(HttpServletRequest request) {
    HttpSession session = request.getSession();
    Step step = (Step) session.getAttribute(CURRENT_STEP);
    return step;
  }

  /**
   * Returns a string containing the current step id as parameter i.e. stepID=987654321, optionally
   * prepended by an ampersand.
   * 
   * @param request HTTP request
   * @param withAmp prepend '&' sign
   * @return step id as parameter i.e. stepID=987654321
   */
  public static String getStepParameter(HttpServletRequest request, boolean withAmp) {
    try {
      return (withAmp ? AMP : EMPTY_STRING) + Step.DEFAULT_PARAMETER_NAME + EQUALS
          + getStep(request).getId();
    } catch (NullPointerException npe) {
      LOGGER.error("Step is null ", npe); //$NON-NLS-1$
      throw npe;
    }
  }

  /**
   * Returns a string containing the current step id as parameter i.e. stepID=987654321, prepended
   * by an ampersand.
   * 
   * @param request HTTP request
   * @return step id as parameter i.e. &stepID=987654321
   */
  public static String getStepParameter(HttpServletRequest request) {
    return getStepParameter(request, true);
  }

  /**
   * entfernt alle älteren Steps, so dass ein Back immer auf der Fehlerseite endet
   * 
   * @param request HTTP request
   */
  public static void removeStepHistory(HttpServletRequest request) {

    try {
      Step step = getStep(request);
      SessionState state = (SessionState) request.getSession()
          .getAttribute(SessionState.DEFAULT_KEY);
      if (step != null && state != null) {
        state.invalidateStepsBefore(step.getId());
      } else {
        LOGGER
            .warn(Messages
                .getString(MessageKeys.Logger_CannotInvalidateBeforeInvalidStepsOrInInvalidSessionState));
      }
    } catch (SessionStateException sse) {
      LOGGER.warn(sse);
    }
  }

  /**
   * Datum mit dem Standardformat formatieren
   * 
   * @param date zu formatierendes Datum
   * @return formatiertes Datum als String
   */
  public static String dateToString(Date date) {
    // Wenn Datum kein echtes Datum
    if (date == null || date.getTime() == -1 || date.getTime() == 1 || date.getTime() == 0) {
      return "-"; //$NON-NLS-1$
    }
    return STFM.format(date);
  }

  /**
   * Datum als Zeitstempel in Millisekunden mit dem Standardformat formatieren
   * 
   * @param time Zeitstempel in Millisekunden
   * @return formatiertes Datum als String
   */
  public static String dateToString(long time) {
    return dateToString(new Date(time));
  }

  /**
   * Methode zur Ausgabe besonderer Datumsformate
   * 
   * @param date Datum
   * @param format gewünschtes Format
   * @return formattiertes Datum
   */
  public static String dateToString(Date date, String format) {
    return new SimpleDateFormat(format).format(date);
  }

  /**
   * Methode zur Ausgabe besonderer Datumsformate
   * 
   * @param time Zeitstempel in Millisekunden
   * @param format gewünschtes Format
   * @return formattiertes Datum
   */
  public static String dateToString(long time, String format) {
    return dateToString(new Date(time), format);
  }

  public static String getRandomString() {
    return Integer.toString(random.nextInt(), Character.MAX_RADIX);
  }

  public static String objectToString(Object o, String defaultIfNull) {
    return o != null ? o.toString() : defaultIfNull;
  }

  /**
   * URL umschreiben und mit Prüf-Hash versehen
   * 
   * @param pUrl Original-URL als String
   * @param request HTTP-Request
   * @param response HTTP-Response
   * @return umschriebene URL als String
   */
  public static String rewriteURL(String pUrl,
      HttpServletRequest request,
      HttpServletResponse response) {
    return rewriteURL(pUrl, request, response, true);
  }

  /**
   * URL umschreiben und mit Prüf-Hash versehen
   * 
   * @param pUrl Original-URL als String
   * @param request HTTP-Request
   * @param response HTTP-Response
   * @param rewriteAmp &amp;-Zeichen zu &amp;amp; umschreiben
   * @return umschriebene URL als String
   */
  public static String rewriteURL(String pUrl,
      HttpServletRequest request,
      HttpServletResponse response,
      boolean rewriteAmp) {
    return rewriteURL(pUrl, request, response, rewriteAmp, false);
  }

  /**
   * URL umschreiben und mit Prüf-Hash versehen
   * 
   * @param pUrl Original-URL als String
   * @param request HTTP-Request
   * @param response HTTP-Response
   * @param rewriteAmp &amp;-Zeichen zu &amp;amp; umschreiben
   * @param withoutContextpath with or without Contextpath
   * @return umschriebene URL als String
   */
  public static String rewriteURL(String pUrl,
      HttpServletRequest request,
      HttpServletResponse response,
      boolean rewriteAmp,
      boolean withoutContextpath) {

    String url = pUrl;
    // sanity check: do not rewrite "javascript:"-URLs
    if (url.toLowerCase().startsWith("javascript:")) { //$NON-NLS-1$
      return url;
    }
    int fragmentIdx = url.indexOf('#');
    String fragment = null;
    if (fragmentIdx >= 0) {
      fragment = url.substring(fragmentIdx);
      url = url.substring(0, fragmentIdx);
    }

    // add stepId if known and only if not already contained
    if (url.indexOf(Step.DEFAULT_PARAMETER_NAME + EQUALS) < 0) {
      Step step = getStep(request);
      if (step != null) {
        String stepId = step.getId();
        if (stepId != null) {
          boolean hasQM = url.indexOf('?') >= 0;
          url += (hasQM ? '&' : '?') + Step.DEFAULT_PARAMETER_NAME + EQUALS + stepId;
        } else {
          LOGGER.debug("Step ID is null"); //$NON-NLS-1$
        }
      } else {
        LOGGER.debug("Step was null"); //$NON-NLS-1$
      }
    }

    url = appendHMAC(url, request);

    if (rewriteAmp) {
      // & in &amp; umschreiben
      // das muss NACH der Berechnung des MAC erfolgen, da der Browser &amp; wieder als & schicken
      // wird
      String sub = url;
      StringBuilder urlBuffer = new StringBuilder();
      int idx = sub.indexOf(AMP_CH);
      while (idx >= 0) {
        urlBuffer.append(sub.substring(0, idx));
        sub = sub.substring(idx);
        if (sub.startsWith(AMP_ENTITY)) {
          sub = sub.substring(AMP_LENGTH);
        } else {
          sub = sub.substring(1);
        }
        urlBuffer.append(AMP_ENTITY);

        idx = sub.indexOf(AMP_CH);
      }
      urlBuffer.append(sub);
      url = urlBuffer.toString();
    }
    // prepend context path to absolute URLs
    if (url.startsWith("/") && !withoutContextpath) { //$NON-NLS-1$
      url = request.getContextPath() + url;
    }

    // check for remains from old jsp code
    if (url.indexOf("<%=") != -1) { //$NON-NLS-1$
      throw new RuntimeException("URL not clean!:" + url); //$NON-NLS-1$
    }
    if (fragment != null) {
      url += fragment;
    }

    String rUrl = response.encodeURL(url);
    if (LOGGER.isDebugEnabled()) {
      HttpSession session = request.getSession(false);
      Key macKey = (Key) session.getAttribute(MAC_KEY);
      String key = macKey == null ? EMPTY_STRING : new BigInteger(1, macKey.getEncoded())
          .toString(Character.MAX_RADIX);
      LOGGER.debug("Rewritten URL: " + rUrl + " >>> macKey = " + key); //$NON-NLS-1$ //$NON-NLS-2$
    }
    return rUrl;
  }

  public static String appendHMAC(String url, HttpServletRequest request) {
    String resultUrl = url;
    Key macKey = null;
    HttpSession session = request.getSession(false);
    if (session != null) {
      try {
        macKey = (Key) session.getAttribute(MAC_KEY);
        if (macKey == null) {
          macKey = KeyGenerator.getInstance("HMACMD5").generateKey(); //$NON-NLS-1$
          session.setAttribute(MAC_KEY, macKey);
        }
        int qsIndex = url.indexOf('?');
        if (qsIndex >= 0) { // will alway be the case, because we have at least stepId
          String queryString = url.substring(qsIndex + 1);
          boolean hasQM = url.indexOf('?') >= 0;
          resultUrl += (hasQM ? '&' : '?') + "HMAC=" + calculateHMAC(queryString, macKey); //$NON-NLS-1$
        }
      } catch (NoSuchAlgorithmException nsae) {
        // should REALLY NEVER happen
        if (__firstNSAE) {
          LOGGER.fatal(nsae, nsae);
          __firstNSAE = false;
        } else {
          LOGGER.debug(nsae);
        }
      }
    }
    return resultUrl;
  }

  /**
   * Hook for URL tampering check
   * 
   * @param pQueryString Description
   * @param request Description
   * @param session Description
   * @return Description
   */
  public static boolean checkURLQueryString(String pQueryString,
      HttpServletRequest request,
      HttpSession session) {
    Key macKey = (Key) session.getAttribute(MAC_KEY);
    if (macKey == null) { // will not normally happen, but just in case
      return true;
    }
    String queryString = pQueryString;
    // check for target (maybe not necessary)
    int targetIdx = queryString.indexOf('#');
    if (targetIdx >= 0) {
      queryString = queryString.substring(0, targetIdx);
      LOGGER.info(Messages.getString(MessageKeys.Logger_ThereISaTargetInTheQueryString));
    }

    String hmac = request.getParameter("HMAC"); //$NON-NLS-1$
    if (hmac != null) {
      if (LOGGER.isDebugEnabled()) {
        String key = new BigInteger(1, macKey.getEncoded()).toString(Character.MAX_RADIX);
        LOGGER.debug("QueryString: " + queryString + " >>> macKey = " + key); //$NON-NLS-1$ //$NON-NLS-2$
      }
      int hmacIndex = queryString.indexOf("&HMAC"); //$NON-NLS-1$
      if (hmacIndex >= 0) {
        if (queryString.indexOf('&', hmacIndex + 1) > 0) {
          LOGGER
              .warn(Messages
                  .getString(MessageKeys.Logger_NachDemHMACkommenWeitereParameterVorDieURLwurdeManipuliert)
                  + request.getRequestURI() + '?' + queryString + "<"); //$NON-NLS-1$
          return false;
        }
        queryString = queryString.substring(0, hmacIndex);
        String hmacMsg = calculateHMAC(queryString, macKey);
        return hmacMsg.equals(hmac);
      } else {
        LOGGER.warn(Messages.getString(MessageKeys.Logger_KeinHMACInDerURL)
            + request.getRequestURI() + '?' + queryString);
        return true;
      }
    }
    return false;
  }

  private static String calculateHMAC(String plainText, Key key) {
    try {
      // standard HMAC-MD5 algorithm as descirbed in RFC 2104:
      Mac mac = Mac.getInstance("HMACMD5"); //$NON-NLS-1$
      mac.init(key);
      return new BigInteger(1, mac.doFinal(plainText.getBytes())).toString(Character.MAX_RADIX);
    } catch (GeneralSecurityException gse) {
      // should REALLY NEVER happen
      if (__firstGSE) {
        LOGGER.fatal(gse, gse);
        __firstGSE = false;
      } else {
        LOGGER.debug(gse);
      }
      return EMPTY_STRING;
    }
  }

  /**
   * Liefert die Nummer des LEVEL parameters
   * 
   * @param request HTTP-Request
   * @return aus dem Request ermittelter Level (meistens Gebietsart)
   */
  public static int getLevel(HttpServletRequest request) {
    int level = -1;
    if (request.getParameter(ApplicationBeanKonstanten.LEVEL) != null) {
      level = getIntParameter(request.getParameter(ApplicationBeanKonstanten.LEVEL));
    } else {
      throw new RuntimeException(
          "LEVEL" + Messages.getString(MessageKeys.Error_StehtNichtImRequest)); //$NON-NLS-1$
    }
    return level;
  }

  /**
   * Liefert die Nummer des LEVEL parameters
   * 
   * @param request HTTP-Request
   * @param vorgabe Vorgabewert, wenn der Level nicht aus dem Request ermittelt werden kann
   * @return aus dem Request ermittelter Level (meistens Gebietsart)
   */
  public static int getLevel(HttpServletRequest request, int vorgabe) {
    int result;
    try {
      result = getLevel(request);
    } catch (Exception e) {
      result = vorgabe;
    }
    return result;
  }

  /**
   * Liefert die Nummer des LEVEL parameters
   * 
   * @param request HTTP-Request
   * @return aus dem Request ermittelter aufrufender Level (meistens Gebietsart)
   */
  public static int getLevelAufrufender(HttpServletRequest request) {
    int level = -1;
    if (request.getParameter(ApplicationBeanKonstanten.LEVELAUFRUFENDER) != null) {
      level = getIntParameter(request.getParameter(ApplicationBeanKonstanten.LEVELAUFRUFENDER));
    } else {
      throw new RuntimeException(
          "LEVELAUFRUFENDER" + Messages.getString(MessageKeys.Error_StehtNichtImRequest)); //$NON-NLS-1$
    }
    return level;
  }

  /**
   * Liefert die Nummer des LEVEL parameters
   * 
   * @param request HTTP-Request
   * @param vorgabe Vorgabewert, wenn der Level nicht aus dem Request ermittelt werden kann
   * @return aus dem Request ermittelter aufrufender Level (meistens Gebietsart)
   */
  public static int getLevelAufrufender(HttpServletRequest request, int vorgabe) {
    int result;
    try {
      result = getLevelAufrufender(request);
    } catch (Exception e) {
      result = vorgabe;
    }
    return result;
  }

  /**
   * Liefert die Nummer des Work parameters
   * 
   * @param request HTTP-Request
   * @return aus dem Request ermittelter Work
   */
  public static int getWork(HttpServletRequest request) {
    int work = -1;
    if (request.getParameter(ApplicationBeanKonstanten.WORK) != null) {
      work = getIntParameter(request.getParameter(ApplicationBeanKonstanten.WORK));
    } else {
      throw new RuntimeException("Work" + Messages.getString(MessageKeys.Error_StehtNichtImRequest)); //$NON-NLS-1$
    }
    return work;
  }

  /**
   * Liefert die Nummer des Work parameters
   * 
   * @param request HTTP-Request
   * @param vorgabe Vorgabewert, wenn Work nicht aus dem Request ermittelt werden kann
   * @return aus dem Request ermittelter Work
   */
  public static int getWork(HttpServletRequest request, int vorgabe) {
    int result;
    try {
      result = getWork(request);
    } catch (Exception e) {
      result = vorgabe;
    }
    return result;
  }

  /**
   * Liefert die Nummer des aufrufender Work parameters
   * 
   * @param request HTTP-Request
   * @param vorgabe Vorgabewert, wenn Work nicht aus dem Request ermittelt werden kann
   * @return aus dem Request ermittelter aufrufender Work
   */
  public static int getWorkAufrufender(HttpServletRequest request, int vorgabe) {
    int result;
    try {
      result = getWorkAufrufender(request);
    } catch (Exception e) {
      result = vorgabe;
    }
    return result;
  }

  /**
   * Liefert die Nummer des aufrufender Work parameters
   * 
   * @param request HTTP-Request
   * @return aus dem Request ermittelter aufrufender Work
   */
  public static int getWorkAufrufender(HttpServletRequest request) {
    int work = -1;
    if (request.getParameter(ApplicationBeanKonstanten.WORKAUFRUFENDER) != null) {
      work = getIntParameter(request.getParameter(ApplicationBeanKonstanten.WORKAUFRUFENDER));
    } else {
      throw new RuntimeException(
          "WORKAUFRUFENDER" + Messages.getString(MessageKeys.Error_StehtNichtImRequest)); //$NON-NLS-1$
    }
    return work;
  }

  /**
   * @param request HTTP-Request
   * @return die ID des angemeldeten Anwenders
   */
  public static String getAnwenderID(HttpServletRequest request) {
    try {
      return ApplicationBean.getAnwContext(request).getID_Anwender();
    } catch (Exception e) {
      LOGGER.error(e, e);
      return null;
    }
  }

  /**
   * Liefert den String mit allen Parametern und Werten des request, außer dem Parameter der
   * ignoriert werden soll. Wird für den ignoreParameter null übergeben, werden alle Parameter und
   * Werte geliefert
   * 
   * @param request HttpServletRequest
   * @param work wenn != -1 wird dieser Wert für work eingesetzt ( Wenn -2 wird der Parameter work
   *          nicht mit eingesetzt (für die Navigation z.B.) DAMIT KOMMEN ABER NICHT ALLE SEITEN
   *          KLAR DAHER NICHT VERWENDEN
   * @param clean wenn true werden auch alle arbeitsparemeter entfernt
   * @return einen String, welcher an eine URL gehängt werden kann.
   */
  private static String getParametersSpezial(HttpServletRequest request, int work, boolean clean) {
    StringBuilder all = new StringBuilder();
    Enumeration<?> enumeration = request.getParameterNames();
    boolean first = true;
    boolean workDone = false; // schon work gehabt?
    while (enumeration.hasMoreElements()) {
      String paramName = enumeration.nextElement().toString();
      // Parameter die eh auf der liste stehen werden immer ignoriert
      if (!isIgnorable(paramName, clean)) {
        if (!first) {
          all.append(AMP_CH);
        }
        all.append(encodeURL(paramName));
        all.append(EQUALS);
        if (work == -1 || !paramName.equals(ApplicationBeanKonstanten.WORK)) {
          all.append(encodeURL(request.getParameter(paramName)));
        } else {
          // work parameter soll manipuliert werden und ist gerade dran
          all.append(work);
          workDone = true;
        }
        first = false;
      }
    }
    // jetzt könnte es sein, dass es keinen work Parameter gab. Der muss dann noch angehängt werden
    if (work != -1 && !workDone) {
      all.append(AMP_CH);
      all.append(ApplicationBeanKonstanten.WORKIS);
      all.append(work);
    }
    return all.toString();
  }

  /**
   * Methode zum optimieren der Druckanzeige. Wenn ein String die geforderte Länge überschreitet,
   * wird er bis auf die Länge abgeschnitten.
   * 
   * @param str der zu überprüfende String
   * @param laenge die Länge, auf welche der String gefüllt oder gestutzt wird
   * @return der notfalls gekürzte String
   * @author apa@ivu.de
   */
  public static String cutStr(String str, int laenge) {
    if (str.length() > laenge) {
      return str.substring(0, laenge);
    } else {
      String fillStr = fillStringWithSpace(laenge);
      try {
        return str + fillStr.substring(0, (fillStr.length() - str.length()));
      } catch (IndexOutOfBoundsException iex) {
        return str;
      }
    }
  }

  public static String cutStrHTML(String str, int laenge) {
    if (str.length() > laenge) {
      return str.substring(0, laenge);
    } else {
      String fillStr = fillStringWithHTMLSpace(laenge - str.length());
      try {
        return str + fillStr;
      } catch (IndexOutOfBoundsException iex) {
        return str;
      }

    }
  }

  /**
   * Helpmethod to create a String to fill a certain length
   */
  private static String fillStringWithHTMLSpace(int laenge) {
    return fillStringWithFillstring(laenge, NBSP);
  }

  /**
   * Helpmethod to create a String to fill a certain length
   * 
   * @param laenge Anzahl der aneinander zu kettenden Leerzeichen
   * @return String mit der vorgegebenen Länge Leerzeichen gefüllt
   */
  public static String fillStringWithSpace(int laenge) {
    return fillStringWithFillstring(laenge, SINGLE_SPACE);
  }

  private static String fillStringWithFillstring(int laenge, String fill) {
    StringBuilder fillStr = new StringBuilder();
    for (int i = 0; i < laenge; i++) {
      fillStr.append(fill);
    }
    return fillStr.toString();
  }

  /**
   * Should complete the numberFormat methods
   * 
   * @param num rechtsbündig zu formatierende Ausgabe
   * @param laenge GesamtLänge der Ausgabe
   * @return rechtsbündig formatierte Ausgabe mit führenden Leerzeichen
   */
  public static String formatStr(String num, int laenge) {
    int numLength = num.length();
    if (numLength >= laenge) {
      return num;
    } else {
      return fillStringWithSpace(laenge - numLength) + num;
    }
  }

  /**
   * Should complete the numberFormat methods
   * 
   * @param num linksbündig zu formatierende Ausgabe
   * @param laenge GesamtLänge der Ausgabe
   * @return linksbündig formatierte Ausgabe mit nachgestellten Leerzeichen
   */
  public static String formatStrLinksbuendig(String num, int laenge) {
    int numLength = num.length();
    if (numLength >= laenge) {
      return num;
    } else {
      return num + fillStringWithSpace(laenge - numLength);
    }
  }

  /**
   * Erzeugt eine URL zur Verwendung im WAS aus einer basis (optional) und einem Command (optional)
   * und einem neuen work-Parametr (optional). Es werden ansonsten alle Parameter aus dem request
   * wieder angehängt, die nicht durch isIgnorable() ausgeschlossen werden. Wenn clean
   * <code>true</code> ist, werden noch Parametr mit dem Prefix ApplicationBeanKonstanten.Prefix
   * rausgeworfen
   * 
   * @param request HttpRequest
   * @param basisURL mögliche BasisURL null für default
   * @param cmd command null für keinen
   * @param work -1 für keine festlegung (Übernahme) ansonsten wird der eingesetzt
   * @param clean wenn true, werden parameter mit dem Prefix auch rausgeworfen
   * @return URL zur Verwendung im WAS anhand des Requests
   */
  public static String generateURL(HttpServletRequest request,
      String basisURL,
      String cmd,
      int work,
      boolean clean) {

    String result = basisURL != null ? basisURL : "/osv"; //$NON-NLS-1$

    String allParameters = getParametersSpezial(request, work, clean);

    if (allParameters.length() > 0) {
      result += QUESTION_MARK;
    }

    result += allParameters;
    if (cmd != null) {
      // unwahrscheinlich aber möglich: es gibt keinen einzigen Parameter
      // es soll aber ein cmd angehängt werden
      if (allParameters.length() != 0) {
        result += AMP;
      } else {
        // ... dann ist & falsch und es muss "?" sein
        result += QUESTION_MARK;
      }
      result += "cmd=" + cmd; //$NON-NLS-1$
    }
    return result;
  }

  // Voreinstellung für das Entfernen von Parametern mit PREFIX
  private final static boolean CLEANDEFAULT = false;

  public static String generateURL(HttpServletRequest request, String basisURL, String cmd, int work) {
    return generateURL(request, basisURL, cmd, work, CLEANDEFAULT);
  }

  public static String generateURL(HttpServletRequest request, String basisURL, String cmd) {
    return generateURL(request, basisURL, cmd, -1, CLEANDEFAULT);
  }

  public static String generateURL(HttpServletRequest request, String basisURL) {
    return generateURL(request, basisURL, null, -1, CLEANDEFAULT);
  }

  public static String generateURL(HttpServletRequest request) {
    return generateURL(request, null, null, -1, CLEANDEFAULT);
  }

  public static String generateURL(HttpServletRequest request, int work, boolean clean) {
    return generateURL(request, null, null, work, clean);
  }

  /**
   * liefert die Nummer des Gebietes aus dem Request oder wirft eine Exception (sollte selten
   * passieren!)
   * 
   * @param request HTTP-Request
   * @return Nummer des Gebietes aus dem Request
   */
  public static int getGebietNr(HttpServletRequest request) {
    int nr = -1;
    if (request.getParameter(ApplicationBeanKonstanten.GEBIETNR) != null) {
      nr = getIntParameter(request.getParameter(ApplicationBeanKonstanten.GEBIETNR));
    } else {
      throw new RuntimeException(ApplicationBeanKonstanten.GEBIETNR
          + Messages.getString(MessageKeys.Error_StehtNichtImRequest));
    }
    return nr;
  }

  /**
   * @param request HTTP-Request
   * @param vorgabe Vorgabewert, falls die Gebietsnummer nicht aus dem Request ermittelt werden kann
   * @return Nummer aus request, Vorgabe, wenn keine GebietNr im request enthalten ist
   */
  public static int getGebietNr(HttpServletRequest request, int vorgabe) {
    int result;
    try {
      result = getGebietNr(request);
    } catch (Exception e) {
      result = vorgabe;
    }
    return result;
  }

  public static String getStimmanzahlString(int stimmen) {
    return stimmen < 0 ? NBSP : EMPTY_STRING + stimmen;
  }

  public static String getStimmanzahlString(int stimmen, NumberFormat nf) {
    return stimmen < 0 ? NBSP : nf.format(stimmen);
  }

  public static String getStimmProzentString(double stimmen, NumberFormat nf) {
    return stimmen < 0 ? NBSP : nf.format(stimmen);
  }

  public static String getStimmProzentDiffString(double prozentAktuell,
      double prozentVergleich,
      NumberFormat nf) {
    return prozentAktuell < 0 && prozentVergleich < 0
        ? NBSP
        : (prozentAktuell >= 0 && prozentVergleich >= 0) ? nf.format(prozentAktuell
            - prozentVergleich) : QUESTION_MARK;
  }

  // public static String getFehlerMeldungErgebnisAnzeige(GebietInfo gebietInfo) {
  // String msg = null;
  // if (gebietInfo.hatLetztenGueltigenEingang()) {
  // if (gebietInfo.isErfassungseinheit()) {
  // if (gebietInfo.getID_LetzterEingang() != gebietInfo.getID_LetzterGueltigerEingang()) {
  // if (gebietInfo.getStatusLetzterEingang() != ErgebniseingangKonstanten.STATE_OK) {
  // msg = Messages
  // .getString(MessageKeys.Msg_EsLiegtEinWeitererNichtVollstaendigBearbeiteterErgebniseingangVor);
  // }
  // }
  // }
  // } else {
  // msg = Messages.getString(MessageKeys.Msg_NochKeinErgebnisEeingegangen);
  // if (gebietInfo.isErfassungseinheit() && gebietInfo.hatLetztenEingang()) {
  // if (gebietInfo.getStatusLetzterEingang() != ErgebniseingangKonstanten.STATE_OK) {
  // msg = Messages
  // .getString(MessageKeys.Msg_NochKeinKorrektesErgebnisEingegangenEsliegtBisherNurEinNichtVollstaendigBearbeiteterErgebniseingangVor);
  // }
  // }
  // }
  // return msg;
  // }

  static final String ESCAPE = "\\-"; //$NON-NLS-1$

  /**
   * Methode zum Separieren von Bezeichnern in ursprüngliche Teilstrings
   * 
   * @param pInput Eingabestring mit Delimiter
   * @return Array der Teilstrings
   */
  public static Collection<String> decomposeString(final String pInput) {
    Collection<String> ret = new ArrayList<String>();
    if (pInput == null || pInput.length() == 0) {
      ret.add(EMPTY_STRING);
      return ret;
    }
    String del = ESCAPE;
    int len = del.length();
    int idx;
    String input = pInput;
    while ((idx = input.indexOf(del)) >= 0) {
      ret.add(input.substring(0, idx));
      input = input.substring(idx + len);
    }
    ret.add(input);
    return ret;
  }

  public static String resizeString(String value, int length) {
    if (value == null || value.isEmpty()) {
      return EMPTY_STRING;
    }
    if (value.length() <= length) {
      return value;
    }
    return value.substring(0, length) + "..."; //$NON-NLS-1$
  }

  public static String getEmptyStringIfBlank(String value) {
    if (StringUtils.isBlank(value)) {
      return EMPTY_STRING;
    }
    return value;
  }

  public static String getEmptyStringIfBlank(String value, String defaultString) {
    if (StringUtils.isBlank(value)) {
      return defaultString;
    }
    return value;
  }

  public static synchronized String formatDateTimeForExport(Date date) {
    return EXPORT_FORMAT.format(date);
  }

  public static synchronized String formatDateTime(Date date) {
    return SDTFM.format(date);
  }

  public static synchronized Date parseDateTime(String dateStr) throws ParseException {
    return EXPORT_FORMAT.parse(dateStr);
  }

  public static synchronized String formatDateForExport(Date date) {
    return DATE_FORMAT.format(date);
  }

  public static synchronized String formatYearForExport(Date date) {
    return EXPORT_YEAR_FORMAT.format(date);
  }

  public static synchronized Timestamp parseYear(String dateStr) throws ParseException {
    return new Timestamp(EXPORT_YEAR_FORMAT.parse(dateStr).getTime());
  }

  public static String getProzentString4Export(double wert) {
    return (wert == -1 ? XMLTags.NOT_APPLICABLE : getDecimalFormat().format(wert));
  }

  private final static int NUMBER_OF_EXPORT_DIGITS = 6;
  private final static double FORMAT_HELPER = Math.pow(10, NUMBER_OF_EXPORT_DIGITS);

  public static synchronized String getProzentString4Export(double wert, double huerde) {
    if (wert == -1) {
      return XMLTags.NOT_APPLICABLE;
    }
    if (huerde > wert && Math.round(huerde * FORMAT_HELPER) == Math.round(wert * FORMAT_HELPER)) {
      return getDecimalFormat().format(Math.floor(wert * FORMAT_HELPER) / FORMAT_HELPER);
    }
    return getDecimalFormat().format(wert);
  }

  public static String getAnzahlString4Export(int wert) {
    return (wert == -1 ? XMLTags.NOT_APPLICABLE : String.valueOf(wert));
  }

  public static String getProzentString4Export(BigDecimal wert) {
    return getProzentString4Export(wert.doubleValue());
  }

  private static DecimalFormat __prozentFormat = null;

  private static DecimalFormat getDecimalFormat() {
    if (__prozentFormat == null) {
      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator('.');
      symbols.setGroupingSeparator(',');
      __prozentFormat = new DecimalFormat("#0.000000", symbols); //$NON-NLS-1$
      __prozentFormat.setMinimumIntegerDigits(1);
      __prozentFormat.setMaximumFractionDigits(NUMBER_OF_EXPORT_DIGITS);
      __prozentFormat.setMinimumFractionDigits(NUMBER_OF_EXPORT_DIGITS);
    }
    return __prozentFormat;
  }

  public static String getWahlDir4Kurzname(String wahlkurzname) {
    return wahlkurzname.replace(' ', '_');
  }

  private static Set<Character> __vovelSet = new HashSet<Character>() {
    private static final long serialVersionUID = 352266330189160021L;

    {
      add('A');
      add('E');
      add('I');
      add('O');
      add('U');
      add('R');
    }
  };

  private static final String BR = "<br />"; //$NON-NLS-1$
  private static final int BR_LEN = BR.length();
  private static final String MBR = "-<br />"; //$NON-NLS-1$
  private static final int MBR_LEN = MBR.length();

  public static String trenneHTML(String str, int maxLen) {
    StringBuilder sb = new StringBuilder(str.trim());
    int lastSpace = -1;
    int lastVovel = -1;
    int currentLineLen = 0;
    for (int i = 0; i < sb.length() - 1; i++) {
      char c = sb.charAt(i);
      if (Character.isWhitespace(c)) {
        lastSpace = i;
      }
      if (__vovelSet.contains(new Character(Character.toUpperCase(c)))) {
        lastVovel = i;
      }
      currentLineLen++;
      if (currentLineLen > maxLen) {
        if (lastSpace > 0) {
          sb.replace(lastSpace, lastSpace + 1, BR);
          i += BR_LEN - 1;
          if (lastVovel <= lastSpace) {
            lastVovel = -1;
          } else if (lastVovel >= 0) {
            lastVovel += BR_LEN - 1;
          }
          currentLineLen = 0;
          lastSpace = -1;
        } else if (lastVovel > 0 && !Character.isWhitespace(sb.charAt(i + 1))) {
          sb.insert(lastVovel + 1, MBR);
          i += MBR_LEN;
          if (lastSpace <= lastVovel) {
            lastSpace = -1;
          } else if (lastSpace >= 0) {
            lastSpace += MBR_LEN;
          }
          currentLineLen = 0;
          lastVovel = -1;
        }
      }
    }
    return sb.toString();
  }

  private static final int areaNameMaxSize = 30;

  public static String trimAreaName(String name) {
    if (name != null && areaNameMaxSize < name.length()) {
      return name.substring(0, areaNameMaxSize) + ".."; //$NON-NLS-1$
    }
    return name;
  }

  /**
   * @param headerString
   * @param headerString
   * @return
   */
  public static int getIntParamValueFromHeader(String headerString, String paramname) {
    String value = getStringParamValueFromHeader(headerString, paramname);
    if (value != null) {
      return Integer.parseInt(value);
    }
    return -1;
  }

  /**
   * @param headerString
   * @param paramname
   * @return
   */
  public static String getStringParamValueFromHeader(String headerString, String paramname) {
    if (headerString != null) {
      final String[] split = headerString.split(AMP);
      for (String keyValue : split) {
        if (keyValue.contains(paramname)) {
          String value = keyValue.substring(keyValue.indexOf(paramname + EQUALS)
              + paramname.length() + EQUALS.length());
          return value;
        }
      }
    }
    return null;
  }

  public static String intsToString(Collection<Integer> col) {
    boolean first = true;
    String result = ""; //$NON-NLS-1$
    for (Integer integer : col) {
      if (first) {
        first = false;
      } else {
        result += ", "; //$NON-NLS-1$
      }
      result += Integer.toString(integer);
    }

    return result;
  }

  public static int changeSessionTimeout(HttpServletRequest request, int minuten) {
    final HttpSession session = request.getSession(false);
    int defaultSessionTimeout = session.getMaxInactiveInterval();
    session.setAttribute("defaultSessionTimeout", Integer.toString(defaultSessionTimeout)); //$NON-NLS-1$
    session.setMaxInactiveInterval(minuten * 60); // set to 60 minutes
    return defaultSessionTimeout;
  }

  public static void setSessionTimeoutBackToDefaultPlus(HttpServletRequest request,
      int defaultSessionTimeout) {
    final HttpSession session = request.getSession(false);
    long interval = System.currentTimeMillis() - session.getLastAccessedTime();
    session.setMaxInactiveInterval((int) (interval / 1000 + defaultSessionTimeout));
  }

  public static String workIs(Command command) {
    return ApplicationBeanKonstanten.WORKIS + command.getId();
  }
}
