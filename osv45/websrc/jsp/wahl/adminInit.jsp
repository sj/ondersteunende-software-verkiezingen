<%--
 *******************************************************************************
 * Aufrufen von initMethoden
 *
 * Initialisiert Rechte und Administration values
 *
 * author:  klie@ivu.de cos@ivu.de  Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: adminInit.jsp,v 1.8 2010/11/19 09:45:17 jon Exp $
 *******************************************************************************
 --%>
<%@ page import="de.ivu.ejb.EJBUtil"%>
<%@ page import="de.ivu.wahl.anwender.AnwenderHandling"%>
<%@ page import="de.ivu.wahl.admin.AdminHandling"%>
<%@ page import="de.ivu.wahl.auswertung.AuswertungHandling"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBean"%>
<%@ page import="de.ivu.wahl.anwender.Rechte"%>
<%@ page import="de.ivu.wahl.AnwContext"%>
<%@ page import="de.ivu.wahl.modell.ejb.AnwenderHome"%>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.client.util.GUICommand" %>
<%@ page import="de.ivu.wahl.anwender.AnwenderHandlingBean"%>
<%@ page import="de.ivu.wahl.auswertung.AuswertungHandlingBean"%>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />

<html>
 <head>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
  <link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
  <link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
 </head>
 
    <script language="JavaScript" type="text/javascript">
   function doTheLink()
   {
      if (window != top) {
         alert("Anmeldung beim WAS nur aus eigenem Fenster!");
         history.back();
      } else {
         location.href="<%= request.getContextPath() %>/osv/logon";
      }
   }
 </script>
 <body class="hghell">
  <%
        // Berechtigung prüfen
        boolean berechtigt = false;
        AnwContext anwContext = ApplicationBean.getAnwContext(request);
        if (anwContext != null && anwContext.checkRight(Rechte.R_ADM_ANW_ANLEGEN)) {
          berechtigt = true;
        } else {
          // in der Datenbank sind noch gar keine Anwender (Einloggen wäre gar nicht möglich!)?
          AnwenderHome anwenderHome = EJBUtil.findLocalHomeNoCache("Anwender");
          berechtigt = anwenderHome.findAll().isEmpty();
        }
        AdminHandling admHandling = admBean.getAdminHandling();
        AnwenderHandling anwHandling = EJBUtil.lookupLocal(AnwenderHandlingBean.class.getSimpleName());
        if (berechtigt) {
          if (request.getParameter("wahlid") != null) {
            admHandling.initAdministrationValues(request.getParameter("wahlid"));
          }
          anwHandling.initRechteUndRechteGruppen();
          if (Boolean.parseBoolean(request.getParameter("anwender"))) {
            anwHandling.createWurzelAnwender();
          }
        }
  %>
  <script type="text/javascript">
	   doTheLink();
  </script>
 </body>
</html>
