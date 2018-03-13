<%--
 *******************************************************************************
 * Arbeit
 * Spiegelt den Arbeitsberecih der Applikation wieder
 * Diese JSP dispatcht auf die durch den Parameter work kodierte tatsächliche
 * ArbeitsJSP. Die konkreten Namen der JSPs sind mit den Befehlen kodiert,
 * so dass sie beim ApplicationBean nachgefragt werden können.
 *
 * author:  mur@ivu.de  Copyright (c) 2002 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: arbeit.jsp,v 1.6 2010/11/19 09:45:17 jon Exp $
 *******************************************************************************
 --%>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />

<%
   int   work  = ApplicationBeanKonstanten.WORK_INITIAL;
   if (request.getParameter(ApplicationBeanKonstanten.WORK) != null){
      work = ClientHelper.getIntParameter(request.getParameter(ApplicationBeanKonstanten.WORK));
   }
   int   level  = 0;
   if (request.getParameter(ApplicationBeanKonstanten.LEVEL) != null){
      level = ClientHelper.getIntParameter(request.getParameter(ApplicationBeanKonstanten.LEVEL));
   }
%>
<html>
<head>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%

// URL der tasächlichen Seite abholen
String url = appBean.getJspForWork(work);
if (url == null) { //
   %>DEFAULT<%
} else {
   url += "?"+ClientHelper.getAllParameters(request);
   %>
   <jsp:forward page="<%= url %>"/>
<% } %>
</body>
</html>
