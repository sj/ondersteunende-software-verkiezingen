<%-- RW:1 NS:1 JS:0 IS:0

Login-Seite des Wahlabwicklungssystems.
Ist in der Session ein gültiger Anwender gespeichert, so wird auf die Hauptseite des
Wahlabwicklungssystems weitergeleitet.

Als Fehlermeldung wird ein Text aus der Session (LOGIN_ERROR) angezeigt, wenn vorhanden.
--%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBean" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten"%>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>

<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />

<% 
String arch = ""; //$NON-NLS-1$
String val = appBean.getPropertyHandling().getProperty(Konstanten.PROP_SYSTEM_ARCHITECTURE_BITS);
if (val != null) {
	arch = " (" + val + BundleHelper.getBundleString("SystemArchitectureBits") + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
}

String breite = "100%";  //$NON-NLS-1$

response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 //$NON-NLS-1$ //$NON-NLS-2$
response.setHeader("Pragma","no-cache"); //HTTP 1.0 //$NON-NLS-1$ //$NON-NLS-2$
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  //$NON-NLS-1$
%>

<html>
<head>
<title><%=SystemInfo.getSystemInfo().getEbenenklartextTitel() %> (<%=SystemInfo.getSystemInfo().getModusklartext() %>) <%=appBean.getWahlName()%></title>
<link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
<link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/capslock.js"></script>
<style type="text/css">
   td { font-family : verdana,arial;
        font-size : 12px;
        font-weight : bold;
        color: #093C69
   }

   table {
         font-family : verdana,arial;
         text-align : right;
         color: #093C69
   }
   input {
         border : 0px solid black;
         border-bottom: 1px solid #333;
         background-color: #F9FAFD;
         color : black;
         font-size : 1.1em;
         color : #093C69;
   }
</style>
<%
   AnwContext anw = appBean.getAnwContext();
   int gebietnr = appBean.getGebietNrAngemAnw(); 
   int gebietart = appBean.getGebietArtAngemAnw();
   // Ausnahmsweise wird von hier der Command ausgeführt wenn vorhanden
   // Vielleicht noch mal durch das Servlet routen (nicht unbedingt notwendig)
   if ((anw == null || anw.getID_Wahl() == null) && request.getParameter("cmd") != null ) { //$NON-NLS-1$
      // Geht nur für Applicationbean
      appBean.executeCommand(request, 0);
   }

   // jetzt vielleicht ein Anwender da?
   anw = appBean.getAnwContext();
   // Wenn schon ein Anwender da ist wird sofort weitergeleitet auf das eigentliche System
   if (anw != null) {
    response.sendRedirect(appBean.getInitialURL(request, response));
   } else {
    // init Methoden der Client beans aufrufen
    // d.h. zunächst nur das Application bean, da alle anderen kein init benötigen!
    // (und auch erst später instanziiert werden)
    appBean.init(request);
   }

   if (appBean.isBeforeInitialPasswordChange()) {
    // The default user (still) has the initial password. Login automatically and redirect to password change page
    appBean.loginDefaultUser(request);
    response.sendRedirect(appBean.getPasswordChangeURL(request, response));
   }
%>
</head>

<body onload="sc()">
<table border="0" width="<%=breite%>" height="100%">
   <tr>
      <td>
         <% String actionUrl = ClientHelper.rewriteURL("/jsp/wahl/login_dialog.jsp", request, response); //$NON-NLS-1$
         %>
         <form name="Login" action="<%=actionUrl%>" method="POST" enctype="application/x-www-form-urlencoded" autocomplete="off">
            <table width="<%=breite%>" height="100%" style="background-image:url(<%= request.getContextPath() %>/img/logo/<%= BundleHelper.getBundleString("Background")%>); background-repeat:no-repeat; background-position:center">
               <tr>
                  <td align="center">
                     <table border="0" cellspacing="0" cellpadding="0" width="600" height="375" >
                        <tr>
                           <td height="52%" colspan="4">
                              <input type="hidden" name="cmd" value="app_login"/>
                           </td>
                        </tr>
                        <tr>
                           <td width="32%">&nbsp;</td>
                           <td width="10%"><ivu:int key="Anwender" />:</td>
                           <td width="8">&nbsp;</td>
                           <td align="left">
                              <input style="background-color:#FFFFFF;" type="text" name="User2017" title="<%=BundleHelper.getBundleString("Anwender_titel") %>" value="" autocomplete="off"/>
                           </td>
                        </tr>
                        <tr align="left">
                           <td>&nbsp;</td>
                           <td><ivu:int key="Passwort" />:</td>
                           <td>&nbsp;</td>
                           <td align="left">
                              <input style="background-color:#FFFFFF;" type="password" name="Password2017" title="<%=BundleHelper.getBundleString("Passwort_titel") %>" value="" autocomplete="off" onkeypress="capsLock(event)">
                              <span id="capsLock" style="visibility:hidden"><%= BundleHelper.getBundleString("Caps_Lock_Hint") %></span>
                           </td>
                        </tr>
                        <tr>
                           <td>&nbsp;</td>
                           <td align="right">
                              <input style="background-color:#000000;" type="image" style="border : 0px solid black;" src="<%= request.getContextPath() %>/img/icon/pfeil_logon.gif" border="0" title="aanmelden" alt="aanmelden"/>
                          </td>
                          <td>&nbsp;</td>
                        </tr>
                         <tr>
                           <td height="50%" colspan="4">
                              <input type="hidden" name="cmd" value="app_login"/>
                           </td>
                        </tr>
                     </table>
                     <p align="right">
                        <b><ivu:int key="Version" /> <%=ApplicationBean.getVersionString() + arch%><br />Build <%=ApplicationBean.getBuildDatumString()%>
                        </b>
                     </p>
                     <%
                     if (session.getAttribute(ApplicationBeanKonstanten.LOGIN_ERROR) != null) { %>
                        <h2><%= session.getAttribute(ApplicationBeanKonstanten.LOGIN_ERROR)%></h2>
                        <% } %>
                  </td>
               </tr>
            </table>
         </form>
      </td>
   </tr>
</table>
</body>
</html>
