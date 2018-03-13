<%-- RW:1 NS:1 JS:0 IS:0

Logon Seite des Wahlabwicklungssystems

Checkt, dass es eine neue Session gibt und führt sonstige Initialisierungen durch

Es erfolgt die Weiterleitung auf login_dialog.jsp
--%>
<%@ page import="de.ivu.util.session.Step" %>
<%@ page import="de.ivu.util.session.SessionHandler" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.util.session.SessionState" %>
<%@ page import="java.util.Locale" %>
<%@ page import="de.ivu.wahl.Konstanten" %>
<jsp:directive.page import="org.apache.log4j.Logger"/>
<jsp:directive.page import="de.ivu.ejb.EJBUtil"/>
<jsp:directive.page import="de.ivu.wahl.modell.ejb.AnwenderHome"/>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten"%>
<%
      Locale.setDefault(Konstanten.LOCALE);
      Logger log = Logger.getLogger( "jsp.logon" ); //$NON-NLS-1$
      HttpSession mysession;
      // invalidate old session if exists (cause of empty browser view)
      if ( request.isRequestedSessionIdValid() ) {
        log.info( "Invalidating old existing session." ); //$NON-NLS-1$
        request.getSession().invalidate();
      } else {
        log.info( "Creating a new session first time" ); //$NON-NLS-1$
      }
      mysession = request.getSession();
      if ( request.getAttribute( "SESSION_LOST" ) != null ) { //$NON-NLS-1$
         log.info( "Session lost." ); //$NON-NLS-1$
         request.setAttribute( "ERROR_KEY", "LOST_SESSION" ); //$NON-NLS-1$ //$NON-NLS-2$
      }

      log.debug( "Creating a session state and first step" ); //$NON-NLS-1$
      SessionState state = new SessionHandler( 30 );
      mysession.setAttribute( SessionState.DEFAULT_KEY, state );
      Step step = state.createStep();
      request.setAttribute(ClientHelper.CURRENT_STEP, step);
      mysession.setAttribute(ClientHelper.CURRENT_STEP, step);

      try {
         mysession.removeAttribute(ApplicationBeanKonstanten.CUR_ANW_KEY);
      } catch (Exception e) {
      // macht nix
      }
      
     // in der Datenbank sind noch gar keine Anwender (Einloggen wäre gar nicht möglich!)?
     AnwenderHome anwenderHome = EJBUtil.findLocalHomeNoCache("Anwender"); //$NON-NLS-1$
      String url = anwenderHome.findAll().isEmpty() ? "adminInit.jsp?anwender=true" : "login_dialog.jsp"; //$NON-NLS-1$ //$NON-NLS-2$
      
      response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 //$NON-NLS-1$ //$NON-NLS-2$
      response.setHeader("Pragma","no-cache"); //HTTP 1.0 //$NON-NLS-1$ //$NON-NLS-2$
      response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  //$NON-NLS-1$
      response.setCharacterEncoding(Konstanten.ENCODING);
%>
<jsp:forward page="<%=url%>"/>
