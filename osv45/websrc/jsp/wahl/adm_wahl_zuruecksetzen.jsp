<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>

<%--
 *******************************************************************************
 * Wahl zurücksetzen
 *
 * Die Funktion soll Administratoren vorbehalten bleiben
 *
 * author:  mur@ivu.de  Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: adm_wahl_zuruecksetzen.jsp,v 1.7 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%>

<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
   String prefix = ApplicationBeanKonstanten.PREFIX;
   String breite = "100%";
   String info = "Wahl zurücksetzen";
   String formurl = "/osv?cmd=adm_wahlZurueck&" + ClientHelper.getParametersDoNotStartWithAndWithoutWork(request, prefix)+"&work="+ApplicationBeanKonstanten.GEB_ERG;
%>
<html>
<head>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <style type="text/css">
    body { overflow:auto }
   </style>
</head>

<body class="hghell">
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr class="hgeeeeee">
      <td height="14"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
    <tr class="hgeeeeee">
      <td class="hgschwarz"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20"><ivu:int key="Wahl_zuruecksetzen_titel"/></td>
            </tr>
            <tr>
               <td colspan="3" class="hgschwarz"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
            </tr>
            <tr>
               <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
            </tr>
            <tr>
               <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
               <td valign="top">
                  <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
                     <tr>
                        <td valign="top">
                        <fieldset style="border: 1px solid #093C69; padding: 15px">
                           <legend><b>
                              <ivu:int key="Wahl_zuruecksetzen"/>
                             </b>
                           </legend><br />
                           <p>
                           	<ivu:int key="Wahl_zuruecksetzen_info"/>
                           </p>
                           <ivu:form name="wkzurueck_allg" action="<%= formurl %>" >
                              <p align="center">
                                 <br /><br /><input id="box2" style="cursor: pointer" type="submit" value="<%=BundleHelper.getBundleString("Wahl_zuruecksetzen") %>" name="<%=prefix%>wkclear">
                              </p>
                           </ivu:form>
                        </fieldset>
                        </td>
                     </tr>
                  </table>
               </td>
               <td width="10">&nbsp;</td>
            </tr>
         </table>
      </td>
   </tr>
</table>
</body>
</html>
