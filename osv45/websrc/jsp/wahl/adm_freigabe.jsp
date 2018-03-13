
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.SystemInfo"%><%--
 *******************************************************************************
 * Eingestellte Wahl und Wahlergebnisart freigeben
 * Funktion für Bundeswahlleiter -> Sitzverteilung wird für alle sichtbar und kann
 * auf allen Veröffentlichungsschienen veröffentlicht werden
 *
 * ACHTUNG: Die Möglichkeit, die Freigabe zurückzunehmen ist ein spezielles Recht und eine
 * extra Rechtegruppe gebunden. Für den Wahlabend, sollten wirklich nur wenige diese Möglichkeit haben!
 *
 * author:  klie@ivu.de  Copyright (c) 2002 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: adm_freigabe.jsp,v 1.19 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.Konstanten" %>
<%@ page import="de.ivu.wahl.WahlInfo" %>
<%@ page import="de.ivu.wahl.anwender.Rechte" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />

<html>
 <head>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <style type="text/css">
    body { overflow:auto }
    td {line-height: 15px}
   </style>
 </head>
 <body class='hghell'><%
 
  String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
  String prefix = ApplicationBeanKonstanten.PREFIX;
  String formurl = ClientHelper.generateURL(request, null, "adm_freigabe", -1, true);
  SystemInfo systemInfo = SystemInfo.getSystemInfo();
  WahlInfo wahlInfo = appBean.getWahlInfo();
  boolean freigegeben = appBean.isFreigegeben(wahlInfo.getAktuelleWahlergebnisart());
  AnwContext anwContext = appBean.getAnwContext();
  boolean hatFZRecht = anwContext.checkRight(Rechte.R_FREIGABE_RUECK);
  
  boolean hatVarianten = false;
  boolean eeEntscheidungLiegtVor = false;

  boolean isReferendum = wahlInfo.isReferendum();
  String helpKey = "admFreigabe";
  if (isReferendum) {
     helpKey = "admFreigabeRef";
  }
  
  String titel = freigegeben ? BundleHelper.getBundleString("FreigabeZuruecknehmen") : BundleHelper.getBundleString("FreigabeFreigeben");
  String breite = "100%";%>

  <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <%@include file="/jsp/fragments/help_row.jspf"%>
   <tr>
    <td valign="top">
     <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
      <tr>
       <td width="5" class="hggrau">&nbsp;</td>
       <% if (isReferendum) { %>  
         <td colspan="2" class="hggrau" height="20"><ivu:int key="FreigabeAktuellesReferendum"/></td>
       <% } else { %>
         <td colspan="2" class="hggrau" height="20"><ivu:int key="FreigabeAktuelleWahl"/></td>
       <% } %>
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
        <ivu:form name="appstate" action="<%= formurl %>" >
         <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
          <tr>
           <td valign="top">
            <fieldset style="border: 1px solid #093C69; padding: 15px">
             <legend><b><%=titel%></b></legend><br />

             <table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0">
              <tr>
               <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
              </tr>
              <tr>
               <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
               <td valign="top"><% 
               	if (!wahlInfo.darfWahlFreigegebenWerden()){ %>  
    						<p><ivu:int key="Freigabe_nicht_erfolgt"/> <ivu:int key="Noch_Nicht_Alle_Ergebniseingaenge"/></p><%
	             	} else if (wahlInfo.getStatus() == WahlModel.STATE_METADATA_P5){ %>  
    						<p><ivu:int key="Freigabe_nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Kein_Ergebniseingang"/></p><%
	             	} else if (wahlInfo.getStatus() == WahlModel.STATE_NEW_RESULT){ %> 
							<p><ivu:int key="Freigabe_nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Anstossen"/></p><%
						} else if (wahlInfo.getStatus() == WahlModel.STATE_CALCULATION_CONFLICT){ %>
							<p><ivu:int key="Freigabe_nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Konflikt"/></p><%
						} else if (!systemInfo.isInputmodusComplete())  { %>
				          <p><ivu:int key="Freigabe_Inputmudus_Nicht_Komplett"/><%
						} else { 
						  	if (freigegeben) {
						  	  if (isReferendum) { %>
	                 	  	    <p><ivu:int key="EingabesichtFreigegebenReferendum"/></p>
						  	  <% } else { %>
	                 	  	    <p><ivu:int key="EingabesichtFreigegeben"/></p>
						  	  <% }
		                 	if (hatFZRecht) {
						  	  if (isReferendum) { %>
	                 	  	    <p><b><ivu:int key="FreigabeZuruecknehmenInfoReferendum"/></b></p>
						  	  <% } else { %>
	                 	  	    <p><b><ivu:int key="FreigabeZuruecknehmenInfo"/></b></p>
						  	  <% } 
		                 	} else {//%>
		                  	<p><ivu:int key="FreigabeBerechtigungFehlt"></ivu:int></p><%
		                 	}
	                	} else  {
	                 		if (hatVarianten && !eeEntscheidungLiegtVor) {//%>
	                  		<p><ivu:int key="VarianteAuswaehlen"></ivu:int></p><%
	                 		} else {%>
	                  		<p><ivu:int key="EingabeartWahlNochNichtFreigegeben"/></p>
			                  <p><b><ivu:int key="FreigabeWahlInfo"/></b></p><% 
	                 		}
	                	}%>
	                <div align="center">
	                 <br /><br /><%
	                 if (freigegeben ? hatFZRecht : (!hatVarianten || eeEntscheidungLiegtVor)) {%>
	                 		<input type="hidden" name="<%=prefix%>freigegeben" value="<%=!freigegeben%>" />
	                  	<input id="box2" type="submit" value="<%=titel%>" name="<%=prefix%>chgAppState"><%
	                 }
                 }%>
                </div>
               </td>
               <td width="10">&nbsp;</td>
              </tr>
             </table>
            </fieldset>
           </td>
          </tr>
         </table>
        </ivu:form>
       </td>
       <td width="10">&nbsp;</td>
      </tr>
     </table>
    </td>
   </tr>
  </table>
 </body>
</html>
