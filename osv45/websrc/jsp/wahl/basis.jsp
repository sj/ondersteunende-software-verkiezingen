<%--
 *******************************************************************************
 * basis
 * JSP-Seite enthält das Frameset des Basis-Systems
 *
 * author:  mur@ivu.de  Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: basis.jsp,v 1.19 2010/11/19 09:45:18 jon Exp $
 *******************************************************************************
 --%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<jsp:directive.page import="de.ivu.wahl.client.beans.InitGuiCommand"/>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN"
   "http://www.w3.org/TR/html4/frameset.dtd">
<html lang="nl">
 <head>
  <link rel='SHORTCUT ICON' href='<c:url value="/FAVICON.ICO"/>' type='image/ico'>
  <link rel='icon' href='<c:url value="/FAVICON.ICO"/>' type='image/ico'>
  <title><%=SystemInfo.getSystemInfo().getEbenenklartextTitel() %> (<%=SystemInfo.getSystemInfo().getModusklartext() %>) <%=appBean.getWahlName()%></title>
 </head>
<%
   response.setCharacterEncoding(Konstanten.ENCODING);
   if (appBean.getAnwContext() == null) { //
      %><jsp:forward page="/osv/SessionOver"/><%
   }
   String base = "/osv/wahl/";
   String v0 =  request.getContextPath() + "/img/logo/" + appBean.getLogoForWahl();
   String vLeer = null;
   String v1 = null;
   boolean naviunten = false;

   if (request.getParameter(ApplicationBeanKonstanten.NAVI_UNTEN) != null) {
      naviunten = ClientHelper.getBooleanParameter(request.getParameter(ApplicationBeanKonstanten.NAVI_UNTEN));
   }
   int gebNr    = -1;
   if (naviunten) {
      gebNr = ClientHelper.getGebietNr(request);
   }

   // hier wird ein Request-Parameter in einen Anker umgewandelt

   if (request.getParameter(ApplicationBeanKonstanten.NAVI_ANKER)!= null){
       v1 = base + "navigation?"+ClientHelper.getAllParameters(request, true)+"#"+request.getParameter(ApplicationBeanKonstanten.NAVI_ANKER);
       vLeer = base + "leer?"+ClientHelper.getAllParameters(request, true)+"#"+request.getParameter(ApplicationBeanKonstanten.NAVI_ANKER);
   } else {
      if (naviunten){
         v1 =  base + "navigation?"+
               ClientHelper.getAllParameters(request, ApplicationBeanKonstanten.NAVI_UNTEN)+
               "&"+ApplicationBeanKonstanten.GEBIETNRIS+gebNr+
               "&"+ApplicationBeanKonstanten.WORKIS+ApplicationBeanKonstanten.GEB_ERG;
         vLeer =  base + "leer?"+
               ClientHelper.getAllParameters(request, ApplicationBeanKonstanten.NAVI_UNTEN)+
               "&"+ApplicationBeanKonstanten.GEBIETNRIS+gebNr+
               "&"+ApplicationBeanKonstanten.WORKIS+ApplicationBeanKonstanten.GEB_ERG;
       } else {
         v1 = ClientHelper.generateURL(request, base + "navigation", null, -1, true);
         vLeer = ClientHelper.generateURL(request, base + "leer", null, -1, true);
       }
   }

   String v2;
   String v3;
   String v4;
   String v5;
   String vRefresh;
   
   if (naviunten){
      String extraURL = ClientHelper.getAllParameters(request, ApplicationBeanKonstanten.NAVI_UNTEN, true)+
                        "&"+ApplicationBeanKonstanten.GEBIETNRIS+gebNr+
                        "&"+ApplicationBeanKonstanten.WORKIS+ApplicationBeanKonstanten.GEB_ERG;
      v2 = base + "befehl?"+extraURL;
      v3 = base + "arbeit?"+extraURL;
      v4 = base + "navi_unten?"+extraURL;
      v5 = base + "nachrichten?"+extraURL;
      vRefresh = base + "refresh?"+extraURL;
   } else {
      v2 = ClientHelper.generateURL(request, base + "befehl", null, -1, true);
      v3 = ClientHelper.generateURL(request, base + "arbeit", null, -1, true);
      v4 = ClientHelper.generateURL(request, base + "navi_unten", null, -1, true);
      v5 = ClientHelper.generateURL(request, base + "nachrichten", null, -1, true);
      vRefresh = ClientHelper.generateURL(request, base + "refresh", null, -1, true);
   }

   // Hier für absolute Pfade
   
   int work = -1;
   try {
      work = ClientHelper.getWork(request);
   } catch (Exception ignore) {/**/}
   if (work != -1) {
      String workurl = appBean.getJspForWork(work);
      if (workurl != null && workurl.startsWith("/")) {
         v3 = workurl;
      }
   }
   
   InitGuiCommand gui = appBean.getInitGuiCommand();
%>
 <frameset cols='210,*' border='0' frameborder='no' onload='history.forward()'><%
  if (gui.isGebieteVorhanden()) { // %>
  <frameset rows='124,*,40' border='0' frameborder='no'>
    <frame src='<%= v0 %>' name='Logo' scrolling='no' marginwidth='0' marginheight='0'>
    <ivu:frame src='<%= v1 %>' name='LinkesFenster'/>
    <ivu:frame src='<%= v4 %>' name='Erfassungseinheitsnavigation' scrolling='no'/>
   </frameset><%
  } else { // Basisadministration %>
   <frameset rows='124,*' border='0' frameborder='no'>
    <frame src='<%= v0 %>' name='Logo' scrolling='no' marginwidth='0' marginheight='0'>
    <ivu:frame src='<%= vLeer %>' name='LinkesFenster'/>
   </frameset><%
  }%>
  <frameset rows='110,*,0' border='0' frameborder='no'>
   <ivu:frame src='<%= v2 %>' name='BefehlFenster'/>
   <ivu:frame src='<%= v3 %>' name='UnteresFenster'/>
   <ivu:frame src='<%= vRefresh %>' name='Automatische_Aktualisierung_Der_Session'/>
  </frameset>
 </frameset>
</html>