package de.ivu.wahl.client.servlet;

import static de.ivu.wahl.client.beans.Command.ADM_ANW_LISTE;
import static de.ivu.wahl.client.beans.Command.ADM_EMPTY_EML_EXPORT;
import static de.ivu.wahl.client.beans.Command.ADM_N10_1_EXPORT;
import static de.ivu.wahl.client.beans.Command.ADM_PROPS;
import static de.ivu.wahl.client.beans.Command.ADM_RE_INDEX_DATABASE;
import static de.ivu.wahl.client.beans.Command.ANWENDER_ANLEGEN;
import static de.ivu.wahl.client.beans.Command.ANWENDER_ANZEIGEN;
import static de.ivu.wahl.client.beans.Command.ANWENDER_LOESCHEN;
import static de.ivu.wahl.client.beans.Command.ANWENDER_VERAENDERN_1_AUSWAHLEN;
import static de.ivu.wahl.client.beans.Command.ANWENDER_VERAENDERN_2_EDIT;
import static de.ivu.wahl.client.beans.Command.ANWENDER_VERAENDERN_PASSWORT;
import static de.ivu.wahl.client.beans.Command.EXPORT_VERZEICHNIS;
import static de.ivu.wahl.client.beans.Command.GEBE;
import static de.ivu.wahl.client.beans.Command.GEW_BEN;
import static de.ivu.wahl.client.beans.Command.IMPORT_ERGEBNISSE;
import static de.ivu.wahl.client.beans.Command.N11;
import static de.ivu.wahl.client.beans.Command.O3;
import static de.ivu.wahl.client.beans.Command.OSV4_1;
import static de.ivu.wahl.client.beans.Command.OSV4_4;
import static de.ivu.wahl.client.beans.Command.OSV4_5;
import static de.ivu.wahl.client.beans.Command.P22;
import static de.ivu.wahl.client.beans.Command.PROTOCOL_APPENDIX;
import static de.ivu.wahl.client.beans.Command.STATUS;
import static de.ivu.wahl.client.beans.Command.STATUS_GEB;
import static de.ivu.wahl.client.beans.Command.T11;
import static de.ivu.wahl.client.beans.Command.U16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.NDC;

import de.ivu.util.session.SessionState;
import de.ivu.util.session.SessionStateException;
import de.ivu.util.session.Step;
import de.ivu.wahl.AnwContext;
import de.ivu.wahl.Konstanten;
import de.ivu.wahl.client.LogoutException;
import de.ivu.wahl.client.beans.ApplicationBean;
import de.ivu.wahl.client.beans.Command;
import de.ivu.wahl.client.beans.EingabeBean;
import de.ivu.wahl.client.beans.Executer;
import de.ivu.wahl.client.util.ClientHelper;
import de.ivu.wahl.i18n.MessageKeys;
import de.ivu.wahl.i18n.Messages;

/**
 * Ueberschrift: WAS <BR>
 * Beschreibung: Dieses Servlet ist der zentrale Anlaufpunkt fuer alle Requests des WahlSystems
 * <p>
 * Gemaess URI wird eine JSP angesprochen, gemaess der Parameter werden die entsprechenden Handler
 * in den Beans aufgerufen (execute command)<BR>
 * Copyright (c) 2002-2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 * 
 * @author cos, klie, tdu
 */

public class WahlServlet extends AbstractWahlServlet {
  private static final long serialVersionUID = 8095360409247575077L;

  private final static String PARAM_CMD = "cmd"; //$NON-NLS-1$
  private final static String LETZTE_AENDERUNG = "letzte.aenderung"; //$NON-NLS-1$

  public final static String SESSION = "SESSION"; //$NON-NLS-1$
  public final static String STEP = "STEP"; //$NON-NLS-1$
  public final static String LAST_VALID_URL = "LAST_VALID_URL"; //$NON-NLS-1$

  protected final static String TRUE = "true"; //$NON-NLS-1$
  protected final static String FALSE = "false"; //$NON-NLS-1$
  protected final static String ERROR_KEY = "error"; //$NON-NLS-1$
  protected final static String POST_USED = "POST_USED"; //$NON-NLS-1$

  protected String _uriBase = "/src/"; //$NON-NLS-1$

  protected boolean _secure = true;

  /**
   * Returns information about the servlet, such as author, version, and copyright.
   * 
   * @return servlet info
   */
  @Override
  public String getServletInfo() {
    return getClass().getName()
        + " $Id: WahlServlet.java,v 1.43 2011/03/24 08:29:31 jon Exp $ by IVU Traffic Technologies AG"; //$NON-NLS-1$
  }

  @Override
  public void init(ServletConfig config) throws ServletException {

    super.init(config);

    String tmpURIBase = config.getInitParameter("uriBase"); //$NON-NLS-1$
    if (tmpURIBase != null) {
      _uriBase = tmpURIBase;
    }
    String tempSecure = config.getInitParameter("secure"); //$NON-NLS-1$
    if (tempSecure != null) {
      _log.info(Messages.getString(MessageKeys.Logger_GotSecure) + ':' + tempSecure);
      _secure = "true".equalsIgnoreCase(tempSecure) || "on".equalsIgnoreCase(tempSecure); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  protected ApplicationBean getApplicationBean(HttpSession session) {
    ApplicationBean appBean = (ApplicationBean) session.getAttribute("appBean"); //$NON-NLS-1$
    if (appBean == null) {
      _log.warn(Messages
          .getString(MessageKeys.Logger_KonnteApplicationBeanNichtErmittelnFuerDieErmittlungDesZeitstempels));
    }
    return appBean;
  }

  protected long getZeitstempelLetzteAenderungServer(HttpSession session) {
    ApplicationBean appBean = getApplicationBean(session);
    if (appBean == null) {
      return System.currentTimeMillis();
    }

    return appBean.getZeitstempelLetzteAenderung();
  }

  protected long getZeitstempelLetzteAenderungClient(HttpSession session) {
    Long zsObj = (Long) session.getAttribute(LETZTE_AENDERUNG);
    if (zsObj == null) {
      return 0;
    }

    return zsObj.longValue();
  }

  protected void setZeitstempelLetzteAenderungClient(HttpSession session, long zs) {
    session.setAttribute(LETZTE_AENDERUNG, new Long(zs));
  }

  protected String getStepId(HttpServletRequest request) {
    // Normally, there is only one stepId in one particular request.
    // However, when forwarding after server restart (session lost),
    // an old stepId still may be kept by the browser.
    String[] stepIds = request.getParameterValues(Step.DEFAULT_PARAMETER_NAME);
    if (stepIds == null) {
      _log.warn(Messages.getString(MessageKeys.Logger_DidNotGetPreviousStepId));
      return null;
    }
    String stepId = stepIds[0];
    for (int i = 1; i < stepIds.length; i++) {
      if (stepId.compareTo(stepIds[i]) < 0) {
        stepId = stepIds[i];
      }
    }
    if (stepIds.length > 1) {
      _log.debug(Messages.getString(MessageKeys.Logger_SelectingStepId) + stepId);
    }
    return stepId;
  }

  protected Step getAndStoreStep(HttpServletRequest request) throws SessionStateException {
    String stepId = getStepId(request);
    request.setAttribute(Step.DEFAULT_PARAMETER_NAME, stepId);
    Step step = getSessionState(request).getStep(stepId);
    request.setAttribute(STEP, step);
    return step;
  }

  private String getPrevQueryAttrKey(HttpServletRequest request) {
    String qs = request.getQueryString();
    String key = "prevQuery:" + qs; //$NON-NLS-1$
    if ("POST".equals(request.getMethod())) { //$NON-NLS-1$
      key += "§" + request.getParameterMap().hashCode(); //$NON-NLS-1$
      _log.info(Messages.getString(MessageKeys.Logger_PageKey) + key);
    }

    return key;
  }

  class PrevQueryVal {
    long _lastModified;
    String _followupStepID;
  }

  private PrevQueryVal getPrevQueryVal(HttpServletRequest request) {
    // get data set stored for this request previously into the session
    String queryAttrKey = getPrevQueryAttrKey(request);
    HttpSession session = request.getSession(false);
    PrevQueryVal prevQueryVal = (PrevQueryVal) session.getAttribute(queryAttrKey);
    if (prevQueryVal != null) {
      _log.debug(Messages.getString(MessageKeys.Logger_IdenticalRequestReturningPreviousResult));
    } else {
      prevQueryVal = new PrevQueryVal();
      session.setAttribute(queryAttrKey, prevQueryVal);
    }

    return prevQueryVal;
  }

  private boolean isNoCache(HttpServletRequest request) {

    List<Command> noCachedWorksCommands = Arrays.asList(ANWENDER_ANLEGEN,
        ANWENDER_ANZEIGEN,
        ANWENDER_VERAENDERN_1_AUSWAHLEN,
        ANWENDER_VERAENDERN_2_EDIT,
        ANWENDER_VERAENDERN_PASSWORT,
        ADM_ANW_LISTE,
        ANWENDER_LOESCHEN,
        ADM_PROPS,
        P22,
        PROTOCOL_APPENDIX,
        U16,
        GEW_BEN,
        O3,
        OSV4_4,
        ADM_N10_1_EXPORT,
        ADM_EMPTY_EML_EXPORT,
        N11,
        T11,
        OSV4_1,
        OSV4_5,
        GEBE,
        IMPORT_ERGEBNISSE,
        STATUS_GEB,
        STATUS,
        EXPORT_VERZEICHNIS,
        ADM_RE_INDEX_DATABASE);
    Set<Integer> noCachedWorks = new HashSet<Integer>();
    for (Command command : noCachedWorksCommands) {
      noCachedWorks.add(command.getId());
    }

    String pathInfo = request.getPathInfo();
    int work = ClientHelper.getWork(request, 0);

    if (isLogonPage(request) || pathInfo == null
        || pathInfo.indexOf("befehl") >= 0 || noCachedWorks.contains(work)) { //$NON-NLS-1$
      return true;
    }

    return false;
  }

  private boolean isLogonPage(HttpServletRequest request) {
    return "/logon".equals(request.getPathInfo()); //$NON-NLS-1$
  }

  @Override
  protected long getLastModified(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    checkLocks(request);
    if (session != null && !isNoCache(request)) {
      String qs = request.getQueryString();
      _log.debug("Query string = " + qs); //$NON-NLS-1$
      if (qs != null) {
        long zs = getZeitstempelLetzteAenderungServer(session);
        long zsSeite = getPrevQueryVal(request)._lastModified;
        if (zsSeite >= zs && zs != 0) {
          return zsSeite;
        }
      }
    }
    return System.currentTimeMillis();
  }

  private void checkLocks(HttpServletRequest request) {
    final ApplicationBean applicationBean = getApplicationBean(request.getSession());
    if (applicationBean != null) {
      applicationBean.lockOrUnlockRegion(request);
    }
  }

  protected SessionState getSessionState(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }

    SessionState state = (SessionState) session.getAttribute(SessionState.DEFAULT_KEY);
    return state;
  }

  @SuppressWarnings("unchecked")
  private Set<String> getPostUsed(HttpSession session) {
    Set<String> postUsed = (Set<String>) session.getAttribute(POST_USED);
    if (postUsed == null) {
      postUsed = new TreeSet<String>();
      session.setAttribute(POST_USED, postUsed);
    }
    return postUsed;
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setCharacterEncoding(Konstanten.ENCODING);
    try {
      HttpSession session = req.getSession();
      ApplicationBean applicationBean = getApplicationBean(session);
      StringBuilder ndcMessage = new StringBuilder();
      if (applicationBean != null) {
        final AnwContext anwContext = applicationBean.getAnwContext();
        if (anwContext != null) {
          ndcMessage.append(anwContext.getAnmeldename());
          ndcMessage.append('@');
        }
      }
      ndcMessage.append(req.getRemoteHost());
      NDC.push(ndcMessage.toString());

      super.service(req, resp);
    } finally {
      NDC.pop();
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    doPostOrGet(request, response, false);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {

    doPostOrGet(request, response, true);
  }

  protected void doPostOrGet(HttpServletRequest request,
      HttpServletResponse response,
      boolean isPost) {

    if (_log.isDebugEnabled()) {
      doEntryLogs(request);
    }

    HttpSession session = request.getSession(false);
    if (session != null) {
      String dst = (String) session.getAttribute("defaultSessionTimeout"); //$NON-NLS-1$
      if (dst != null) {
        session.setMaxInactiveInterval(Integer.parseInt(dst));
      }
    }

    if (isLogonPage(request)) {
      forwardRequest(request, response, _uriBase + "jsp/wahl/logon.jsp"); //$NON-NLS-1$
    } else {
      if (getSessionState(request) != null) {
        try {
          processURLQueryString(request, response, isPost, session);
        } catch (SessionStateException sse) {
          _log.warn(Messages
              .getString(MessageKeys.Logger_UserHasProbablyTriedToUseAnInvalidatedState) + sse);
          forwardRequest(request, response, _uriBase + "jsp/NoBack.jsp"); //$NON-NLS-1$
        } catch (LogoutException le) {
          _log.info(Messages.getString(MessageKeys.Logger_UserWantsToLogOut) + le);
          if (request.isRequestedSessionIdValid()) {
            session.removeAttribute(ClientHelper.MAC_KEY);
            session.invalidate();
          }
          forwardRequest(request, response, _uriBase + "jsp/Logout_bestaetigung.jsp"); //$NON-NLS-1$
        } catch (RuntimeException re) {
          _log.error(re, re);
          request.setAttribute("EXCEPTION", re); //$NON-NLS-1$
          forwardRequest(request, response, _uriBase + "jsp/MainErrorPage.jsp"); //$NON-NLS-1$
        }
      } else {
        // forward to an intermediate page which has a link to the logon page
        _log.warn(Messages.getString(MessageKeys.Logger_RequestReferencesAnOldIinvalidSession));
        forwardRequest(request, response, _uriBase + "jsp/SessionOver.jsp"); //$NON-NLS-1$
      }
    }
  }

  private void processURLQueryString(HttpServletRequest request,
      HttpServletResponse response,
      boolean isPost,
      HttpSession session) throws SessionStateException {

    String stepID = getStepId(request);

    // filter out identical request
    String qs = request.getQueryString();
    if (qs != null) {
      // ensure that the QueryString part of the URL was not tempered with
      if (!_secure || ClientHelper.checkURLQueryString(qs, request, session)) {
        checkLocks(request);

        // get followup step stored for this request previously into the session
        PrevQueryVal prevQueryVal = (PrevQueryVal) session
            .getAttribute(getPrevQueryAttrKey(request));
        final boolean followupStepExists = prevQueryVal != null
            && prevQueryVal._followupStepID != null;
        if (followupStepExists && request.getPathInfo() != null) {
          checkStoredStep(request, prevQueryVal);
        } else {
          Set<String> postUsed = getPostUsed(session);
          if (isPost && (stepID == null || postUsed.contains(stepID))) {
            if (followupStepExists) {
              // probably a double click, but data was not changed
              checkStoredStep(request, prevQueryVal);
            } else {
              throw new SessionStateException("User tried a second post with the same step."); //$NON-NLS-1$
            }
          } else {
            processAndDisplay(request, response, isPost, prevQueryVal);
          }
        }
        dispatchRequest(request, response);
      } else {
        _log.warn(Messages.getString(MessageKeys.Logger_UserProbablyManipulatedTheQueryString) + qs);
        forwardRequest(request, response, _uriBase + "jsp/UserManipulatedURL.jsp"); //$NON-NLS-1$
      }
    } else {
      dispatchRequest(request, response);
    }
  }

  /**
   * Just use previous result, no model change
   * 
   * @param request
   * @param prevQueryVal
   * @throws SessionStateException
   */
  private void checkStoredStep(HttpServletRequest request, PrevQueryVal prevQueryVal)
      throws SessionStateException {

    if (getSessionState(request).getStep(prevQueryVal._followupStepID) == null) {
      // very seldom, will not normally happen, except for back..back..reload:
      // same handling as with no back button is the best we can do
      throw new SessionStateException(
          Messages.getString(MessageKeys.Logger_CannotRetrieveOldFollowupStep));
    }

    // do not use the stored step, but the most current one (also used for URL rewriting)
    Object currentStep = request.getSession(false).getAttribute(ClientHelper.CURRENT_STEP);
    synchronized (currentStep) { // blocks if command not yet ready
      request.setAttribute(ClientHelper.CURRENT_STEP, currentStep);
    }
  }

  private void processAndDisplay(HttpServletRequest request,
      HttpServletResponse response,
      boolean isPost,
      PrevQueryVal pPrevQueryVal) throws SessionStateException {

    HttpSession session = request.getSession(false);
    SessionState sstate = getSessionState(request);
    String stepID = getStepId(request);
    Step step;
    long zs = getZeitstempelLetzteAenderungServer(session);
    if ((zs > getZeitstempelLetzteAenderungClient(session)) || isPost) {
      step = sstate.createNextStep(stepID);
      _log.info(Messages.bind(MessageKeys.Logger_GeneriereNeuenStep_0_FuerDieURL_1,
          step.getId(),
          request.getRequestURI()));
      setZeitstempelLetzteAenderungClient(session, zs);
      calculateLastValidURL(request, response, step);
    } else {
      step = sstate.getStep(stepID);
      if (step == null) {
        throw new SessionStateException(
            Messages.getString(MessageKeys.Error_SessionStepWasAlreadyDiscardedOrDidNotExistAtAll));
      }
    }
    request.setAttribute(ClientHelper.CURRENT_STEP, step);
    session.setAttribute(ClientHelper.CURRENT_STEP, step);

    storeFollowupStep(request, pPrevQueryVal, step, zs);

    synchronized (step) { // causes a repeated request to block
      dispatchCommand(request);
      if (isPost) {
        getPostUsed(session).add(stepID);
      }
    }
  }

  /**
   * Store followup step for this request into the session
   * 
   * @param request
   * @param pPrevQueryVal
   * @param step
   * @param zs
   */
  private void storeFollowupStep(HttpServletRequest request,
      PrevQueryVal pPrevQueryVal,
      Step step,
      long zs) {

    PrevQueryVal prevQueryVal = pPrevQueryVal == null ? getPrevQueryVal(request) : pPrevQueryVal;
    prevQueryVal._lastModified = zs;
    prevQueryVal._followupStepID = step.getId();
  }

  private void calculateLastValidURL(HttpServletRequest request,
      HttpServletResponse response,
      Step step) {

    HttpSession session = request.getSession(false);
    final String url = ClientHelper.rewriteURL(calculateRequestURL(request, step.getId())
        .toString(), request, response);
    session.setAttribute(LAST_VALID_URL, url);
  }

  private void doEntryLogs(HttpServletRequest request) {
    // some CGI vars
    _log.debug("REQUEST:" + request); //$NON-NLS-1$
    _log.debug("REQUEST_METHOD: " + request.getMethod()); //$NON-NLS-1$
    _log.debug("CONTENT_TYPE: " + request.getContentType()); //$NON-NLS-1$
    _log.debug("CONTENT_LENGTH: " + request.getContentLength()); //$NON-NLS-1$
    _log.debug("PATH_INFO: " + request.getPathInfo()); //$NON-NLS-1$
    _log.debug("PATH_TRANSLATED: " + request.getPathTranslated()); //$NON-NLS-1$
    _log.debug("SCRIPT_NAME: " + request.getServletPath()); //$NON-NLS-1$
    _log.debug("CONTEXT_PATH: " + request.getContextPath()); //$NON-NLS-1$
    _log.debug("QUERY_STRING: " + request.getQueryString()); //$NON-NLS-1$
    HttpSession session = request.getSession(false);
    _log.debug("SESSION: " + session); //$NON-NLS-1$
    if (session != null) {
      _log.debug("SESSION created: " + new java.util.Date(session.getCreationTime())); //$NON-NLS-1$
      _log.debug("SESSION last accessed: " + new java.util.Date(session.getLastAccessedTime())); //$NON-NLS-1$
    }
    // all parameters
    Enumeration<?> en = request.getParameterNames();
    while (en.hasMoreElements()) {
      String name = (String) en.nextElement();
      String[] values = request.getParameterValues(name);
      StringBuilder temp = new StringBuilder();
      for (String value : values) {
        temp.append('[').append(value).append(']');
      }
      _log.debug(name + " --> " + temp); //$NON-NLS-1$
    }

    _log.debug(Messages.getString(MessageKeys.Logger_ServletPathIs) + request.getServletPath());
  }

  private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String url) {
    try {
      if ((url != null) && (url.length() > 0)) {
        _log.info("forwardRequest:  " + url); //$NON-NLS-1$

        getServletContext().getRequestDispatcher(url).forward(request, response);
      }
    } catch (Throwable t) {
      _log.error(Messages
          .getString(MessageKeys.Logger_ThrowableExceptionTryingToForwardToJSPMESSAGE)
          + t.getMessage(),
          t);
    }
  }

  /**
   * Extract the command and dispatch it to the corresponding bean.
   * 
   * @param request HTTP request
   */
  private void dispatchCommand(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String cmd = request.getParameter(PARAM_CMD);
    if (cmd != null) {
      String prefix = cmd.substring(0, cmd.indexOf('_'));
      Executer executer = (Executer) session.getAttribute(prefix + "Bean"); //$NON-NLS-1$
      if (executer == null) {
        throw new RuntimeException(Messages.bind(MessageKeys.Logger_BeanForCmd_0_NotFound, cmd));
      }
      try {
        _log.info(Messages.getString(MessageKeys.Logger_Executing) + " " + cmd); //$NON-NLS-1$
        executer.executeCommand(request, 0);
      } catch (LogoutException le) {
        throw le;
      } catch (Exception e) {
        _log.error(Messages.getString(MessageKeys.Logger_ErrorExcutingCommand) + cmd, e);
      }
    }
    // Check and execute multiple commands
    int i = 1;
    cmd = request.getParameter("cmd1"); //$NON-NLS-1$
    while (cmd != null) {
      String prefix = cmd.substring(0, cmd.indexOf("_")); //$NON-NLS-1$
      Executer executer = (Executer) session.getAttribute(prefix + "Bean"); //$NON-NLS-1$
      if (executer == null) {
        throw new RuntimeException(Messages.bind(MessageKeys.Logger_BeanForCmd_0_NotFound, cmd));
      }
      try {
        _log.info(Messages.bind(MessageKeys.Logger_0_isExecuting_1,
            executer.getClass().getName(),
            cmd));
        executer.executeCommand(request, i);
      } catch (LogoutException e) {
        throw e;
      } catch (Exception e) {
        _log.error(Messages.getString(MessageKeys.Logger_ErrorExecutingCommand) + cmd, e);
      }
      i++;
      cmd = request.getParameter(PARAM_CMD + i);
    }
  }

  /**
   * Dispatch the request to the JSP/HTML page it corresponds to.<BR>
   * Basis ist die URI unter der das Servlet angesprochen wird. Der Name der anzuzeigenden Seite
   * wird direkt aus der URi ermittelt. Extension ist jsp. Ist neben der Lokation des Servlets kein
   * weiterer Pfad in der Anfrage, wird auf die Hauptseite des was verwiesen.
   * 
   * @param request HTTP request
   * @param response HTTP response
   */
  private void dispatchRequest(HttpServletRequest request, HttpServletResponse response) {
    String redirect = (String) request.getAttribute(EingabeBean.REQUEST_PARAMETER_REDIRECT);
    if (!StringUtils.isEmpty(redirect)) {
      // redirect
      try {
        String redirectURL = getRedirectURL(request, response);
        if (redirectURL != null) {
          response.sendRedirect(redirectURL);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      // forward
      String pathInfo = request.getPathInfo();

      if (pathInfo == null) {
        pathInfo = "/wahl/osv"; //$NON-NLS-1$
      } else {
        _log.debug(Messages.getString(MessageKeys.Logger_PathInfo) + pathInfo);
      }
      response.setCharacterEncoding(Konstanten.ENCODING);
      response.setDateHeader("Last-Modified", getZeitstempelLetzteAenderungClient(request //$NON-NLS-1$
          .getSession()));
      boolean isStylesheet = pathInfo.endsWith(".xslt"); //$NON-NLS-1$
      String target = _uriBase + "jsp" + pathInfo + (isStylesheet ? "" : ".jsp?") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          + ClientHelper.getAllParameters(request);

      forwardRequest(request, response, target);
    }
  }

  private String getRedirectURL(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    String redirect = (String) request.getAttribute(EingabeBean.REQUEST_PARAMETER_REDIRECT);
    String redirectURL = calculateRequestURL(request, getStepId(request)).toString();
    String[] replaceForRequest = redirect.split(EingabeBean.REPLACE_FOR_REDIRECT_SEPARATOR);
    for (int i = 0; i < replaceForRequest.length - 1; i = i + 2) {
      redirectURL = redirectURL.replace(replaceForRequest[i], replaceForRequest[i + 1]);
    }
    session.setAttribute(LAST_VALID_URL, ClientHelper.rewriteURL(redirectURL, request, response));
    redirectURL = ClientHelper.appendHMAC(redirectURL, request);
    return response.encodeURL(request.getContextPath() + redirectURL);
  }

  private StringBuilder calculateRequestURL(HttpServletRequest request, String stepId) {
    StringBuilder requestURL = new StringBuilder();
    requestURL.append(request.getServletPath());
    List<String> ignore = new ArrayList<String>();
    ignore.add("HMAC"); //$NON-NLS-1$
    ignore.add(Step.DEFAULT_PARAMETER_NAME);
    final String allParameters = ClientHelper.getAllParameters(request, ignore, true);
    requestURL.append('?').append(allParameters).append('&');
    requestURL.append(Step.DEFAULT_PARAMETER_NAME).append('=').append(stepId);
    return requestURL;
  }
}
