
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.AnwContext"%>
<%@ page import="de.ivu.wahl.Konstanten"%><%--
 *******************************************************************************
 * Eingangshistorie
 * Alle Ergebniseingänge chronologisch sortiert.
 * Veränderung der Wahlkreis-, Bundes-, und Landes-ergebnisse
 *
 * Die Unterscheidung erfolgt durch den LEVEL
 *
 * author:  mur@ivu.de  Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: Status_Gebiet.jsp,v 1.17 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<%@ page import="de.ivu.wahl.auswertung.*" %>
<%@ page import="de.ivu.wahl.modell.*" %>
<%@ page import="de.ivu.wahl.auswertung.erg.*" %>
<%@ page import="de.ivu.wahl.client.beans.*" %>
<%@ page import="de.ivu.wahl.client.util.*" %>
<%@ page import="org.apache.log4j.Category" %>
<%@ page import="de.ivu.wahl.GebietsBaum" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>

<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />

<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "statusGebiet"; //$NON-NLS-1$

    WahlInfo wahlInfo = appBean.getWahlInfo();
    EingangsHistorieErgebnis erg;
	String idGebiet;
	if (request.getParameter("id_Gebiet") != null && !request.getParameter("id_Gebiet").isEmpty() ){ //$NON-NLS-1$ //$NON-NLS-2$
	  idGebiet =  request.getParameter("id_Gebiet"); //$NON-NLS-1$
	  erg =  appBean.getAuswertungHandling().getEingangshistorie(idGebiet);
	} else {
		GebietsBaum gebietsBaum = appBean.getGebietsBaum();   
	   int level = ApplicationBeanKonstanten.LEVEL_INITIAL;
	   int gebietNr = ApplicationBeanKonstanten.GEBIETNR_INITIAL;
	   if (request.getParameter(ApplicationBeanKonstanten.GEBIETNR) != null && 
	       request.getParameter(ApplicationBeanKonstanten.LEVEL) != null){
	       gebietNr = ClientHelper.getIntParameter(request.getParameter(ApplicationBeanKonstanten.GEBIETNR));
	       level = ClientHelper.getIntParameter(request.getParameter(ApplicationBeanKonstanten.LEVEL));
	       idGebiet = appBean.getWahlInfo().getID4Gebiet(level, gebietNr);
	   }
	   else{
	       // Auf Root wenn keine Daten im Request
	       GebietInfo info = (GebietInfo)gebietsBaum.getWurzel().getUserObject();
	       gebietNr = info.getNummer();
	       level = info.getGebietsart();
	       idGebiet = info.getID_Gebiet();
	   }
	   erg =  appBean.getEingangshistorie(level,gebietNr);
	}
   
   String breite ="100%"; //$NON-NLS-1$
   
  boolean isReferendum = appBean.getWahlInfo().isReferendum();
  if (isReferendum) {
     helpKey = "statusGebietRef"; //$NON-NLS-1$
  }

   %>
<html>
<head>
   <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
   <title><ivu:int key="Gebiet_Status_titel"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <style type="text/css">
       a:link { font-family: Arial, Verdana, Helvetica;
               font-size: 11px;
               color: #093C69;

      }
      a:hover { font-family: Arial, Verdana, Helvetica;
               font-size: 11px;
               color: #093C69;
               text-decoration:underline;

      }
      a:visited { font-family: Arial, Verdana, Helvetica;
                  font-size: 11px;
                  color: #093C69;

      }
   </style>
</head>

<body class='hghell'>
	<%@include file="/jsp/fragments/help_div.jspf"%>
	<div class="hgschwarz"
		style="height: 1px; line-height: 1px; width: 100%;">
		&nbsp;
	</div>
	<div class="hggrau"
		style="height: 20px; width: 100%; text-indent: 5px; line-height: 18px;">
		<%=erg.getErgBezeichnung()%> <%= wahlInfo.isReferendum() || StringUtils.isEmpty(SystemInfo.getSystemInfo().getInputmodusCompleteKlartext()) ? "" : "(" + SystemInfo.getSystemInfo().getInputmodusCompleteKlartext() + ")" %>
	</div>
	<div class="hgschwarz"
		style="height: 1px; line-height: 1px; width: 100%;">
		&nbsp;
	</div>


<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
            </tr>
            <% if (appBean.getINPUT_MAP().containsKey(idGebiet)){ %>
	            <tr>
	               <td width="10"><img height="1" width="1" src="/P4_PSB/img/icon/blind.gif"/></td>
	               <td colspan="2"><ivu:int key="InUse"/> <%= ClientHelper.forHTML(AnwContext.getUsernameFromLockvalue(appBean.getINPUT_MAP().get(idGebiet))) %>  </td>
	            </tr>
	            <tr>
	               <td colspan="3"><img height="10" width="1" src="/P4_PSB/img/icon/blind.gif"/></td>            
	            </tr>
            <% } %>
            <tr>
               <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
               <td valign="top">
                  <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
                     <tr class="hgrot">
                        <td valign="top">
                           <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
                              <tr>
                                 <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                              </tr>
                              <tr>
                                 <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                                 <td valign="top">
                                    <table border="0" cellspacing="2" cellpadding="4" class="hgweiss" width="<%= breite %>">
                                    <tr class="hgformular">
                                       <td><b><ivu:int key="UhrzeitDatum"/></b></td>
                                       <td><b><ivu:int key="Gebietname"/></b></td>
                                       <td><b><ivu:int key="Anwender"/></b></td>
                                       <td><b><ivu:int key="Input"/></b></td>
                                       <td><b><ivu:int key="StatusText"/></b></td>
                                       <td><b><ivu:int key="Status"/></b></td>
                                    </tr>
                                    <%
                                    if (erg.getErgebniseingaenge().isEmpty()){%>
	                                   	<tr class="hgweiss">
	                                      	<td valign="top" colspan="5" align="center"><ivu:int key="BisherKeinErgebniseingang"/></td>
	                                  	</tr>
                                    <% } else {
	                                    int j = 1;
	                                    for (EingangsHistorieErgebnis.EingangsContainer cont : erg.getErgebniseingaenge()){
	                                       %>
	                                       <tr class="<%= j < 1?"hgeeeeee":"hgweiss"%>">
	                                          <td width="15%" valign="top"><nobr><%= cont.getZeitstempel() %></nobr></td>
	                                          <td width="20%" valign="top"><nobr><%= ClientHelper.forHTML(cont.getGebietBez()) %></nobr></td>
	                                          <td width="7%"  valign="top"><%= ClientHelper.forHTML(cont.getUserID()) %></td>
	                                          <td width="3%" valign="top" align="center"><%=cont.getWahlergebnisart()%><img src="<%= request.getContextPath() %>/img/icon/<%= cont.getInputTypeImg()%>" width="10" height="10"><%= cont.getInputSequenceType()%></td>
	                                          <td valign="top"><%= ClientHelper.forHTML(cont.getStatusMeldungWithoutMultipleInputs(), true) %></td>
	                                          <td width="3%" valign="top" align="center"><img src="<%= request.getContextPath() %>/img/icon/<%= cont.getStatusIcon()%>"></td>
	                                       </tr>
	                                       <%
	                                    j = -j;
	                                    }
                                    }
                                    %>
                                    </table>
                                 </td>
                                 <td width="10">&nbsp;</td>
                              </tr>
                              <tr>
                                 <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                              </tr>
                           </table>
                        </td>
                     </tr>
                  </table>
               </td>
               <td width="10">&nbsp;</td>
            </tr>
            <% if (SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_PSB) { %>
	            <tr>
	               <td height="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
	               <td colspan="2" height="10"><%= "P=" + Konstanten.PARTEINIVEAU +" / K=" + Konstanten.KANDIDATENNIVEAU %></td>
	            </tr>
            <% } %>
            <tr>
               <td colspan="3" height="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
            </tr>
         </table>
      </td>
   </tr>
</table>
</body>
</html>
