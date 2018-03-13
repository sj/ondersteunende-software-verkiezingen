<%--
 *******************************************************************************
 * Eingabe des Ergebnisses einer Erfassungseinheit
 *
 * author:  cos@ivu.de, IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ page pageEncoding="ISO-8859-1" contentType="text/html; charset=UTF-8"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.anwender.Rechte"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<%@ page import="de.ivu.wahl.eingang.GUIEingangMsg " %>
<%@ page import="de.ivu.wahl.modell.GebietInfo" %>
<%@ page import="de.ivu.wahl.client.beans.*" %>
<%@ page import="de.ivu.wahl.client.util.*"%>
<%@ page import="org.apache.log4j.Logger"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.modell.ErgebniseingangKonstanten"%>
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<%@ page import="de.ivu.wahl.modell.GruppeKonstanten.GruppeAllgemein"%>
<%@ page import="java.lang.StringBuffer"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>

<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="eingabeBean" scope="session" class="de.ivu.wahl.client.beans.EingabeBean" />
<jsp:useBean id="map" scope="page" class="java.util.HashMap" type="java.util.Map"/>
<jsp:useBean id="applicationBeanKonstanten" scope="application"
   class="de.ivu.wahl.client.beans.ApplicationBeanKonstantenMap" />
<jsp:useBean id="ergebniseingangModelKonstanten" scope="application"
   class="de.ivu.wahl.client.beans.ErgebniseingangModelKonstantenMap" />
<jsp:useBean id="guiEingangMsgKonstanten" scope="application"
   class="de.ivu.wahl.client.beans.GUIEingangMsgKostantenMap" />
<jsp:useBean id="repHandler" scope="session" class="de.ivu.wahl.client.beans.RepositoryPropertyHandler" />
   
<c:set var="nr" value="${applicationBeanKonstanten.GEBIETNR_INITIAL}" scope="page"/>
<c:set var="gebietsNr" value="${param[applicationBeanKonstanten.GEBIETNR]}" scope="page"/>
<c:if test="${not empty gebietsNr}">
   <c:set var="nr" value="${gebietsNr}" scope="page"/>
</c:if>
<c:set var="gebietsArt" value="${param[applicationBeanKonstanten.LEVEL]}" scope="page"/>
<c:set var="gebietsBaum" value="${appBean.gebietsBaum}" scope="page"/>
<c:set var="gebietNodes" value="${gebietsBaum.gebietsNodes[gebietsArt]}" scope="page"/>
<c:if test="${not empty gebietNodes}">
   <c:set var="gebietNode" value="${gebietNodes[nr]}" scope="page"/>
</c:if>
<c:if test="${empty gebietNode}">
   <c:set var="gebietNode" value="${gebietsBaum.gebietsNodeRoot}" scope="page"/>
</c:if>
<c:set var="gebietInfo" value="${gebietNode.userObject}" scope="page"/>
<c:set target="${map}" property="gebietInfo" value="${gebietInfo}"/>
<c:set var="gebietName" value="<%= ClientHelper.forHTML(((GebietInfo)map.get("gebietInfo")).getName()) %>" scope="page"/>

<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "ergEingabe"; //$NON-NLS-1$

   SystemInfo systemInfo = SystemInfo.getSystemInfo();
   WahlInfo wahlInfo = appBean.getWahlInfo();
   
   String eingInput = ApplicationBeanKonstanten.PREFIX;
   String aktUrl = "/osv?cmd=eingabe_eingabe&" + ClientHelper.getParametersDoNotStartWith(request, eingInput); //$NON-NLS-1$ 
   String reloadUrl = "/osv?" + ClientHelper.getAllParameters(request); //$NON-NLS-1$
   String erlaubenUrl = "/osv?cmd=eingabe_erlauben&" + ClientHelper.getParametersDoNotStartWith(request, eingInput); //$NON-NLS-1$
   // holen einer vorbereiteten Eingangsmessage für eine Erfassungseinheit
   GUIEingangMsg guiEingangMsg = eingabeBean.getGUIMsg(request, (GebietInfo)map.get("gebietInfo"), false); //$NON-NLS-1$
   
   String infoText = ClientHelper.forHTML(guiEingangMsg.getInfoText());
   String confirmationText = ClientHelper.forHTML(guiEingangMsg.getConfirmationText());
   guiEingangMsg.setInfoText(null);
   guiEingangMsg.setConfirmationText(null);
   String breite= "100%"; //$NON-NLS-1$
   int trimSizeRef = 1000;
   int trimSizeRefAnswer = 300;
   
   boolean isReferendum = appBean.getWahlInfo().isReferendum();
   if (isReferendum) {
   	   helpKey = "ergEingabeRef"; //$NON-NLS-1$
   }

   boolean isEbenePSB = false;
   if (systemInfo.getWahlEbene() == GebietModel.EBENE_PSB) {
      isEbenePSB = true;
   }
   
   Set<String> errorList = new HashSet<String>();
%>

<c:set var="wahlInfo" value="<%= wahlInfo %>" scope="page"/>
<c:set var="systemInfo" value="<%= systemInfo %>" scope="page"/>
<c:set var="guiEingangMsg" value="<%= guiEingangMsg %>" scope="page"/>
<c:set var="infoText" value="<%= infoText %>" scope="page"/>
<c:set var="confirmationText" value="<%= confirmationText %>" scope="page"/>
<c:set var="aktUrl" value="<%= aktUrl %>" scope="page"/>
<c:set var="reloadUrl" value="<%= reloadUrl %>" scope="page"/>
<c:set var="erlaubenUrl" value="<%= erlaubenUrl %>" scope="page"/>
<c:set var="isEbenePSB" value="<%= isEbenePSB %>" scope="page"/>
<c:set var="lastStatus" value="${guiEingangMsg.status}" scope="page"/>
<c:set var="lastStatus" value="${guiEingangMsg.status}" scope="page"/>
<c:set var="isDoubleInput" value="${not systemInfo.singleInput}" scope="page"/>
<c:set var="isFileImportNeededAtFirst" value="${systemInfo.fileInputWithManualConfirmation and guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_1}" scope="page"/>
<c:set var="keyForViewlock" value="${appBean.anwContext.keyForViewlock}" scope="page"/>
<c:set var="isLockedByAnotherUser" value="${ appBean.logLocking && !(appBean.INPUT_MAP[gebietInfo.idGebiet] eq keyForViewlock) }" scope="page"/>

<c:set var="neueingabe" value="${(not empty infoText || not empty confirmationText) && isDoubleInput}" scope="page"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
   <head>
      <title><ivu:int key="GebietseingabeImDialog"/></title>
      <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
      <style type="text/css">
/*<![CDATA[*/
          table.hghell {
            width: 100%;
            background-color: #CCC;
            font-size: 11px;
            color: black;
          }

          table.max {
            width: 100%;
          }
          
          fieldset {
             border: 1px solid #093C69; 
             padding-left: 15px;
          }
          
          .spacer {
             height: 0px;
             line-height: 0px;
             font-size: 0px;
          }
          
          .imagebutton {
			 background-color: #FFFFFF;
			 border: 0px;
			 width: 18px;
			 height: 18px;
          }
    /*]]>*/
      </style>
      <script>
			var contextPath = "<%=request.getContextPath()%>";
		</script>
      <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/osv.js"></script>
      <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
      <script language="JavaScript" type="text/javascript"><!--
//<![CDATA[
        function validate(what) {
            for (var i = 0; i < what.elements.length; i++) {
               var val = what.elements[i].value;
               if (val == ""){
                  alert(<%=BundleHelper.getBundleString("FeldMussMitWertGefuelltSein") //$NON-NLS-1$
                  %>);
                  return false;
               }
               // führende Nullen werden abgeschnitten
               while (val != "0" &&  val.indexOf("0") == 0){
                  val = val.substr(val.indexOf("0")+1);
               }
               if ( what.elements[i].type == "text"  && val != parseInt(val)){
                  alert(<%= BundleHelper.getBundleString("WertIstKeineZahl") //$NON-NLS-1$
                  %>);
                  return false;
               }
            }
            return true;
        }
        
        function collapseSystemGroups() {
        	return toggleSystemGroups(false);
        }
         
        function expandSystemGroups() {
        	return toggleSystemGroups(true);
        }
         
        function toggleSystemGroups(expand) {
        	for (var i = -50; i <= 0; i++) {
        		var row = document.getElementById("row0_" + i);
        		if (row != null) {
                	var rowError = document.getElementById("row" + i + "_0_error");
                	if (rowError != null) {
                		toggleRow(rowError, expand);
                	}
            		toggleRow(row, expand);
            		if (!expand) {
                		clearInputValue(row);
            		}
        		}
        	}
        	return true;
        }
        
        function toggleRow(row, expand) {
			if (expand) {
				row.style.display = "";
			} else {
				row.style.display = "none";
			}
        }
        
        function clearInputValue(row) {
        	for (var i = 0; i <= 50; i++) {
        		var inputField = document.getElementById("id_0_" + i);
        		if (inputField != null && row == inputField.parentNode.parentNode) {
        			inputField.value = 0;
        			return;
        		}
        	}
        }
         
		function toggledisplay(group, numberOfChildren) {
			for (var i = 1; i <= numberOfChildren + 1; i++) {
					var rowError = document.getElementById("row" + group + "_" + i + "_error");
				if (rowError != null) {
					if (rowError.style.display == "") {
						rowError.style.display = "none";
					} else {
						rowError.style.display = "";
					}
				}
				var row = document.getElementById("row" + group + "_" + i);
				var votes = document.getElementById("id_" + group + "_" + i);
				if (row.style.display == "") {
					row.style.display = "none";
					votes.style.display = "none";
				} else {
					votes.style.display = "";
					row.style.display = "";
				}
			}
			var imgCollapseExpand = document.getElementById("id_" + group + "_0");
			var sum = document.getElementById("id_" + group + "_" + (numberOfChildren + 1));
			var headSum = document.getElementById("headsum" + group);
			if (imgCollapseExpand.src.match(/collapse.gif/)) {
				imgCollapseExpand.src = "<%= request.getContextPath() %>/img/icon/expand.gif";
				if (sum != null && headSum != null) {
					headSum.innerHTML = "<small><b>" + sum.value + "</b></small>";
				}
			} else {
				imgCollapseExpand.src = "<%= request.getContextPath() %>/img/icon/collapse.gif"
				if (sum != null) {
					headSum.innerHTML = "&nbsp;";
				}
			}
			return false;
		}
        //]]>
      --></script>
   </head>
   
   <jsp:element name="body">
    <jsp:attribute name="onload">scWithCount(0);</jsp:attribute>
    <jsp:attribute name="class">hghell</jsp:attribute>
    <jsp:attribute name="topmargin">0</jsp:attribute>
    <jsp:attribute name="marginwidth">0</jsp:attribute>
    <jsp:attribute name="marginheight">0</jsp:attribute>
    <jsp:body>   
  	<div id="content">
		<div id="statusbalken" style="display:none;" class="status">
	  		<div style="margin: 13px; padding: 13px 0px 0px 0px; width: 360px; border: 1px solid;">
			  <div style="padding: 0px 13px 4px 13px;"></div>
			  <table align="center" style="background-color: rgb(9, 60, 105);">
				 <tr>
			      <td style="color: white">
						<ivu:int key="ErgebnisimportInWenigenSekunden"/>
			      </td>
			    </tr>
				 <tr>
			      <td style="height: 30px;">
						<img src="<%= request.getContextPath() %>/img/leer.gif" name="p0" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p1" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p2" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p3" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p4" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p5" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p6" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p7" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p8" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p9" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p10" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p11" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p12" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p13" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p14" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p15" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p16" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p17" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p18" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p19" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p20" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p21" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p22" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p23" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p24" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p25" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p26" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p27" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p28" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p29" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p30" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p31" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p32" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p33" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p34" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p35" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p36" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p37" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p38" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p39" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p40" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p41" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p42" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p43" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p44" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p45" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p46" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p47" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p48" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p49" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p50" height="12" width="5"/><img src="<%= request.getContextPath() %>/img/leer.gif" name="p51" height="12" width="5"/>
			      </td>
			    </tr>
			  </table>
			  <div style="padding: 0px 13px 4px 13px;">
					&nbsp;
			  </div>
			</div>
		</div>
		<div id="trans">
		   <c:choose>
		   	  <%-- Gebiet gesperrt für diesen Anwender --%>
		      <%-- Wenn Eingabesperre entsprechende Auskunft --%>
		      <%-- Input by hand is locked --%>
		      <c:when test="${(!gebietInfo.guiEingabeErlaubt and systemInfo.inputmodusComplete) or wahlInfo.freigegeben or appBean.inputDisabled or isLockedByAnotherUser or isFileImportNeededAtFirst}">
			      	<%@include file="/jsp/fragments/print_and_help_div.jspf"%>
					<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
						&nbsp;
					</div>
					<div class="hggrau" style="height: 20px; width: 100%; text-indent: 5px; line-height: 18px;">
						 <ivu:int key="EingabeErfassungseinheit"/> ${gebietInfo.number4Display}: ${gebietName}
					</div>
					<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
						&nbsp;
					</div>
					<table width="<%=breite %>" cellspacing="0" cellpadding="0" border="0" class="hghell">
						<tbody>
							<tr>
								<td valign="top">
									<table width="<%=breite%>" cellspacing="0" cellpadding="1" border="0">
										<tbody>
											<tr class="hghell" >
										      <td colspan="3" ><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
										   </tr>
											<tr class="hghell" >
												<td width="10"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="1" width="1"></td>
												<td valign="top">
										         <fieldset>
										            <legend><b><ivu:int key="EingabeNichtMoeglich"/></b></legend><br />
										            <table class="max" border="0" cellspacing="0" cellpadding="0">
										                <tr>
										                   <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
										                   <td valign="top">
															 <c:choose>
															 	<c:when test="${wahlInfo.freigegeben}">
																	<ivu:int key="Freigabe_bereits_erfolgt"/><br /><ivu:int key="Freigabe_bereits_erfolgt_Kein_Ergimport"/>
															 	</c:when>
															 	<c:when test="${!(gebietInfo.guiEingabeErlaubt) }">
										                      		<ivu:int key="GebietBereitsPerDateiGefuellt"/>
										                      		<%  /* if admin, allow opening for manual input */ 
										                      			if (appBean.getAnwContext().checkRight(Rechte.R_EINGABE_ERLAUBEN)) { %>
										                      				<br /><br /><ivu:int key="ManuelleEingabenFreigebenHinweis"/>
										                      				<ivu:form name="gebieteingabe" action="${erlaubenUrl}">
												                                <div align="center">
										                      					    <input id="box2" style="cursor:pointer" type="submit" value="<%= BundleHelper.getBundleString("ManuelleEingabenFreigeben") %>" name="${applicationBeanKonstanten.PREFIX}erlauben">
										                      					</div>
										                      				</ivu:form> <%
										                      			}
										                      		 %>
															 	</c:when>
															 	<c:when test="${appBean.inputDisabled}">
										                      		<ivu:int key="EingabeNichtMoeglichDaEingabesperre"></ivu:int>
															 	</c:when>
											                    <c:when test="${isLockedByAnotherUser}">
											                      <ivu:int key="EingabeWahlgebietNichtMoeglichDa"></ivu:int><br />
											                      <%-- Grund kann nur nachwahl sein --%>
											                      <ivu:int key="GebietWirdVonAnderemAnwenderBearbeitet"/>
											                    </c:when>
											                    <c:when test="${isFileImportNeededAtFirst}">
											                      <ivu:int key="GebietBenoetigtZunaechstErgebnisimport"/>
											                    </c:when>
															 </c:choose>
										                     <br />
										                   </td>
										                   <td width="10">&nbsp;</td>
										                </tr>
										             </table>
										         </fieldset>
					      					</td>
												<td width="10">
													&nbsp;
												</td>
											</tr>
											<tr class="hghell" >
										      <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
										   </tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
		      </c:when>
		      
		     <%-- Input --%> 
		      <c:otherwise>
                 <c:set var="isCollapsibleGroupErrorExists" value="false" scope="page"/>
		         <c:if test="${appBean.INPUT_MAP[gebietInfo.idGebiet] eq appBean.anwContext.keyForViewlock}">
						<%@include file="/jsp/fragments/print_and_help_row.jspf"%>
						<table border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
			            <tr>
			               <td valign="top">
			                  <table border="0" cellspacing="0" cellpadding="0" class="hghell">
			                     <tr>
			                        <td colspan="3" class="hgschwarz"><img alt="nix" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
			                     </tr>
			                     <tr>
			                        <td width="5" class="hggrau">&nbsp;</td>
			                        <td colspan="2" class="hggrau" height="20"><span class="abstand1">
			                        <ivu:int key="EingabeErfassungseinheit"/> ${gebietInfo.number4Display}: ${gebietName}</span></td>
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
			                           <ivu:form name="gebieteingabe" action="${aktUrl}" onsubmit="transp();">
			                             
			                              <table border="0" cellspacing="0" cellpadding="0" align="center" class="hgformular" width="92%">
			                                 <tr>
			                                    <td><img src="<%=request.getContextPath()%>/img/icon/eck_oben_links.gif" width="20" height="20" /></td>
			                                    <td style="text-align: right; color: grey; font-size: 9px;"><ivu:int key="Grundeinstellungen"/>: <%= wahlInfo.isReferendum() ? "" : systemInfo.getInputmodusCompleteKlartext() %><%= (wahlInfo.isReferendum() || StringUtils.isEmpty(systemInfo.getInputmodusCompleteKlartext())) ? "" : ", " %><%= systemInfo.getDoubleInputKlartext() %></td>
			                                    <td align="right"><img src="<%=request.getContextPath()%>/img/icon/eck_oben_rechts.gif" width="20" height="20" /></td>
			                                 </tr>
			                                 <c:if test="${not empty confirmationText}">
	                                     		<tr>
				                                    <td></td>
				                                    <td style="color: green; padding-top: 20px; padding-bottom: 30px;">${confirmationText}</td>
				                                    <td></td>
				                             	</tr>
	                                         </c:if>
			                                 <c:if test="${not empty infoText}">
	                                     		<tr>
				                                    <td></td>
				                                    <td style="color: red; padding-top: 20px; padding-bottom: 30px;">${infoText}</td>
				                                    <td></td>
				                             	</tr>
	                                         </c:if>
			                                 <tr>
			                                    <td>&nbsp;</td>
			                                    <td class="hgformular" align="left">
			                                       <div id="erg">
				                                       <table class="max" border="0" cellpadding="2" cellspacing="2" >
				                                          <%-- MeldungsBox Start --%>                      
				                                          <tr>
				                                             <td  valign="top" colspan="7">
				                                                <span id='layer4'>
				                                                   <c:set var="hatErgebnis" value="false" scope="page"/>
				                                                   <c:set var="eem" value="${guiEingangMsg.ergebniseingangModel}" scope="page"/>
				                                                   <c:choose>
				                                                      <c:when test="${empty eem}">
				                                                         <c:set var="statusString" scope="page">
				                                                            <ivu:int key="BisherKeinErgebnisEingegangen"/>
				                                                         </c:set>
				                                                      </c:when>
				                                                      <c:otherwise>
				                                                         <c:choose>
				                                                            <c:when test="${not gebietInfo.vollstaendig}">
				                                                               <c:set var="statusString" scope="page">
				                                                                  <ivu:int key="KeinGueltigesErgebnisLiegtVor"/>
				                                                               </c:set>
				                                                            </c:when>
				                                                            <c:otherwise>
				                                                               <c:set var="statusString" scope="page">
				                                                                  <ivu:int key="GueltigesErgebnisLiegtVor"/>
				                                                               </c:set>
				                                                               <c:set var="hatErgebnis" value="true" scope="page"/>
				                                                            </c:otherwise>
				                                                         </c:choose>
				                                                      </c:otherwise>
				                                                   </c:choose>
				                                                   <c:if test="${hatErgebnis}">
				                                                      <img src='<%= request.getContextPath() %>/img/icon/check_ja_schmal.gif' border='0' alt='OK' />
				                                                   </c:if>
				                                                   <c:choose>
				                                                      <c:when test="${lastStatus == ergebniseingangModelKonstanten.STATE_ERROR}">
				                                                         <img src="<%= request.getContextPath() %>/img/icon/achtungRot.gif" alt="<%= BundleHelper.getBundleString("Achtung") %>" vspace="0" hspace="0" width="5" height="13" />
				                                                      </c:when>
				                                                      <c:when test="${lastStatus == ergebniseingangModelKonstanten.STATE_WARNING}">
				                                                         <img src="<%= request.getContextPath() %>/img/icon/achtungGelb.gif" alt="<%= BundleHelper.getBundleString("Achtung") %>" vspace="0" hspace="0" width="5" height="13" />
				                                                      </c:when>
				                                                   </c:choose>
				                                                   &nbsp;${statusString}
				                                                </span>
				                                             </td>
				                                          </tr>
				                                          <%-- MeldungsBox Ende --%>
				                                          <tr>
				                                             <td colspan="7" height="30">&nbsp;</td>
				                                          </tr>
				                                          <% if (isReferendum){ 
				                                          	String refText = repHandler.getProperty(Konstanten.KEY_REFERENDUM_TEXT);%>
															<tr>
					                                        	<td colspan="7" height="50">&nbsp;</td>
										                    </tr>
														 	<tr>
																<td valign="top" colspan="7">
																	<h3><ivu:int key="Referendum"/>:</h3>	  	  		
														    	</td>
														 	</tr>
														 	<tr>
																<td valign="top" colspan="7" title="<%=ClientHelper.getEmptyStringIfBlank(refText) %>">
																	<b><%= ClientHelper.resizeString(refText, trimSizeRef) %></b>	  	  		
														    	</td>
														 	</tr>
														 	<tr>
														 		<td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="15"></td>
														 	</tr>
													      <% } %>
				                                          
														  <tr>
				                                              <td colspan="3">&nbsp;</td>
	                                                         <c:choose>
				                                              		<c:when test="${isDoubleInput}">
						                                              	<c:choose>
					                                              			<c:when test="${guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_1}">
								                                              <td align="center">
																				<b><ivu:int key="ErsteEingabe"/></b>
																			  </td>
						                                              		</c:when>
						                                              		<c:otherwise>
								                                              <td align="center">
				                                              					<b style="color: gray;"><ivu:int key="ErsteEingabe"/></b>
				                                              				  </td>
								                                              <td align="center">
																				<b><ivu:int key="ZweiteEingabe"/></b>
																			  </td>
																			</c:otherwise>
						                                              	</c:choose>
				                                              		</c:when>
				                                              		<c:otherwise>
						                                              <td align="center">
																		<b><ivu:int key="Eingabe"/></b>
																	  </td>
				                                              		</c:otherwise>
				                                              	</c:choose>
				                                              </td>			                                              
				                                              <td colspan="3">&nbsp;</td>
				                                           </tr>
				                                           
				                                          <%-- Hauptbereich --%>
				                                          <c:set var="gruppendaten" value="${guiEingangMsg.gruppendaten}" scope="page" />
				                                          <c:set var="aktuelleGruppenNr" value="0" scope="page" />
				                                          <c:set var="aktuelleKategorie" value="" scope="page" />
				                                          <c:set var="aktuelleUnterkategorie" value="" scope="page" />
				                                          <c:set var="positionWahlberechtigte" value="<%= GruppeAllgemein.WAHLBERECHTIGTE.getPosition() %>" scope="page" />
				                                          <% int groupCounter = 0; %>
			                                          	  <c:set var="gb" value="1"/>
				                                          
				                                          <c:forEach var='item' items='${gruppendaten}' varStatus="s">
				                                          	<c:set var="gd" value="1"/>
				                                            <c:set var="gruppe" value="${gruppendaten[item.key]}"  />
				                                            <c:set var="first" value="true" scope="page"/>
				                                            <c:set var="position" value="${gruppe.position}" scope="page"/>
				                                            <c:set var="aktuelleGruppenNr" value="${position}" scope="page" />
				                                            <c:set var="name" value="${gruppe.name}" scope="page"/>
				                                            <c:set var="helptext" value="${gruppe.helptext}" scope="page"/>
				                                            <c:set var="kategorie" value="${gruppe.kategorie}" scope="page"/>
				                                            <c:set var="unterkategorie" value="${gruppe.unterkategorie}" scope="page"/>
				                                            <c:set var="isRadioButtons" value="${gruppe.radioButtons}" scope="page"/>
				                                            <c:set var="isCollapsible" value="${gruppe.collapsible}" scope="page"/>
				                                            <c:set var="buchstabe" value="${gruppe.buchstabe}" scope="page"/>
				                                            <c:set var="isSmallFont" value="${gruppe.smallFontSize}" scope="page"/>
				                                            <c:set var="isSmallGapAfterwards" value="${gruppe.smallGapAfterwards}" scope="page"/>
				                                            <c:set var="isVisible" value="${gruppe.visible}" scope="page"/>
				                                            <c:set var="farbe" value="${gruppe.farbe}" scope="page"/>
				                                            <c:set var="unterschiedeVorhanden" value="${guiEingangMsg.unterschiedeVorhanden}" scope="page"/>
				                                            <c:set var="gruppenergebnis" value="${guiEingangMsg.gruppenergebnisse[position]}" scope="page"/>
				                                            <c:set var="gruppefehler" value="${gruppenergebnis.gruppefehler}" scope="page"/>
															<c:set target="${map}" property="gruppefehler" value="${gruppefehler}"/>
				                                            <c:set var="gruppenstimmen" value="${gruppenergebnis.gesamtstimmen}" scope="page"/>
				                                          	<c:set var="clazzI" value=""/>
				                                          	<c:set var="styleT" value=""/>
				                                          	<c:if test="${s.count == 1}">
					                                          	<c:set var="styleT" value="color: #8F6E36; "/>
					                                          	<c:choose>
						                                          	<c:when test="${position == positionWahlberechtigte && isEbenePSB}">
							                                          	<c:set var="clazzI" value="readonlyWahlberechtigte"/>
						                                          	</c:when>
						                                          	<c:otherwise>
							                                          	<c:set var="clazzI" value="bordercolorWahlberechtigte"/>
						                                          	</c:otherwise>
					                                          	</c:choose>
				                                          	</c:if>
				                                          	<c:set var="gb" value="${isVisible ? -gb : gb}"/>
				                                        	<c:set var="classG" value="hghell" scope="page"/>
			                                             	<c:if test="${gb > 0}">
							                                    <c:set var="classG" value="hgheller" scope="page"/>
				                                            </c:if>
			                                             	<c:if test="${position == positionWahlberechtigte or position > 0}">
							                                    <c:set var="classG" value="" scope="page"/>
				                                            </c:if>
				                                             
			                                            	<c:if test="${position > 0 && first}">
				                                             <c:set var="first" value="false" scope="page"/>
				                                             <tr height="2">
				                                              	<c:choose>
				                                              		<c:when test="${isDoubleInput}">
						                                                <td colspan="8" height="1"><span class="spacer"></span></td>
						                                            </c:when>
						                                            <c:otherwise>
						                                                <td colspan="7" height="1"><span class="spacer"></span></td>
						                                            </c:otherwise>
						                                        </c:choose>
				                                             </tr>
			                                            	</c:if>
			                                            	<%-- Kategorie --%>
			                                            	<c:if test="${isVisible and kategorie != aktuelleKategorie}">
				                                             <c:set var="aktuelleKategorie" value="${kategorie}" scope="page"/>
				                                             <tr class="eingangKategorie">
				                                                <td colspan="${isDoubleInput ? '8' : '7'}" style="${position > 0 ? 'height:8px;' : ''}"><span>${kategorie}</span></td>
				                                             </tr>
			                                            	</c:if>
			                                            	<%-- Unterkategorie --%>
			                                            	<c:if test="${isVisible and not empty unterkategorie and unterkategorie != aktuelleUnterkategorie}">
				                                             <c:set var="aktuelleUnterkategorie" value="${unterkategorie}" scope="page"/>
				                                             <tr valign="top" style="height: 18px;">
				                                                <td colspan="${isDoubleInput ? '8' : '7'}"><span>${unterkategorie}</span></td>
				                                             </tr>
			                                            	</c:if>
			                                            	<%-- Radio buttons --%>
			                                            	<c:if test="${isVisible and isRadioButtons}">
				                                             <tr valign="top" style="height: 18px;">
				                                             	<td><input type="radio" name="<%= ApplicationBeanKonstanten.PREFIX + ErgebniseingangKonstanten.UNTERSCHIEDE_FORM_ELEMENT_NAME %>" value="0" ${empty unterschiedeVorhanden or not unterschiedeVorhanden ? "checked=\"checked\" " : ""} onclick="return collapseSystemGroups()" /></td>
				                                             	<td colspan="${isDoubleInput ? '7' : '6'}"><span><ivu:int key="yes"/></span></td>
				                                             </tr>
				                                             <tr valign="top" style="height: 18px;">
				                                             	<td><input type="radio" name="<%= ApplicationBeanKonstanten.PREFIX + ErgebniseingangKonstanten.UNTERSCHIEDE_FORM_ELEMENT_NAME %>" value="1" ${not empty unterschiedeVorhanden and unterschiedeVorhanden ? "checked=\"checked\" " : ""} onclick="return expandSystemGroups()" /></td>
				                                             	<td colspan="${isDoubleInput ? '7' : '6'}"><span><ivu:int key="no"/></span></td>
				                                             </tr>
				                                            </c:if>
			                                            	<%-- Fehlerbox Gruppe --%>
			                                            	<c:if test="${not empty gruppefehler and (!systemInfo.inputmodusComplete or position <= 0)}">
                                                               <% errorList.add((String) map.get("gruppefehler")); %>
				                                               <c:set var="isCollapsibleGroupErrorExists" value="${isCollapsibleGroupErrorExists or isCollapsible}" />
			                                                   <tr id="row${position}_0_error">
			                                                     <td><div class="spacer"></div></td>
			                                                     <td align="right" colspan="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2 ? '7' : '6'}">
			                                                       <table border="0" cellspacing="0" cellpadding="0">
			                                                         <tr>
                                                                       <td>
                                                                          <img src="<%= request.getContextPath() %>/img/icon/warnung.gif" width="20" height="20" alt="<%= BundleHelper.getBundleString("Warnung")%>" align="middle">
                                                                       </td>
                                                                       <td>
                                                                          <font color="red">${gruppefehler}</font>
                                                                       </td>
                                                                     </tr>
                                                                   </table>
			                                                   	 </td>
		                                                	   </tr>
			                                                </c:if>
			                                                <c:choose>
			                                                   <c:when test="${position > 0 and not wahlInfo.referendum}">
					                                           		<c:set var="numberofcandidates" value="${gruppe.kandidatenanzahl}" scope="page"/>
					                                           		<c:choose>
					                                              		<c:when test="${systemInfo.inputmodusComplete}">
			                                            		  			<tr class="hgweiss" style="height:18px" onclick="return toggledisplay(${position}, ${numberofcandidates})">
			                                            		  		</c:when>
			                                            		  		<c:otherwise>
			                                            		  			<tr class="hgweiss" style="height:18px">
			                                            		  		</c:otherwise>
			                                            		  	</c:choose>
			                                                        <td width="3%"><small><b>${position}</b></small></td>
			                                                        <td colspan="2"><small><b>${name}</b></small>
			                                                        	<c:if test="${systemInfo.inputmodusComplete}">
																			<c:choose>
					                                                        	<c:when test="${gruppenergebnis.fehlerVorhanden}">
								                                                   <c:choose>
								                                                      <c:when test="${lastStatus == ergebniseingangModelKonstanten.STATE_WARNING}">
						                                                        		<img src="<%= request.getContextPath() %>/img/icon/achtungGelb.gif" alt="<%= BundleHelper.getBundleString("Achtung") %>" vspace="0" hspace="0" width="5" height="13" align="top" />
						                                                        	  </c:when>
						                                                        	  <c:otherwise>
						                                                        		<img src="<%= request.getContextPath() %>/img/icon/achtungRot.gif" alt="<%= BundleHelper.getBundleString("Achtung") %>" vspace="0" hspace="0" width="5" height="13" align="top" />
						                                                        	  </c:otherwise>
						                                                           </c:choose>
					                                                        	</c:when>
					                                                        	<c:when test="${!gruppenergebnis.fehlerVorhanden && gruppenergebnis.eingabeGroesserNullVorhanden}">
								                                                    <img src='<%= request.getContextPath() %>/img/icon/check_ja_schmal.gif' border='0' alt='OK' align="top" />
					                                                        	</c:when>
					                                                        	<c:otherwise></c:otherwise>
					                                                        </c:choose>
				                                                        </c:if>
			                                                        </td>
			                                                   </c:when>
			                                                   <c:when test="${position > 0 and wahlInfo.referendum}">
			                                                   </c:when>
			                                                   <c:otherwise>
			                                                      <c:if test="${position == -4 and isEbenePSB}">
			                                                         <tr>
			                                                            <td colspan="3" style="height: 18px; color: grey; vertical-align: top;"><ivu:int key="GruppeKonstanten.hint"/></td>
			                                                         </tr>
			                                                      </c:if>
			                                            		  <tr valign="top" style="${not isVisible or isCollapsible and (empty unterschiedeVorhanden or not unterschiedeVorhanden) ? 'display:none' : ''}" id="${isCollapsible && isVisible ? 'row0_' : 'row'}${position}">
			                                            		  	<c:if test="${not empty unterkategorie}"><td /></c:if>
			                                            		  	<td class="${classG}" colspan="${not empty unterkategorie ? '2' : '3'}" style="${styleT}${isSmallGapAfterwards ? 'height: 22px;' : 'height: 18px;'}">
			                                            		      <c:if test="${isSmallFont}"><small>${name}</small></c:if>
			                                            		      <c:if test="${not isSmallFont}">${name}</c:if>
			                                                        </td>
			                                                   </c:otherwise>
			                                                </c:choose>
			                                                <c:if test="${gruppenstimmen == -1}">
						                                         <c:set var="gruppenstimmen" value="" scope="page"/>
			                                                </c:if>
			                                                <c:if test="${not (position > 0 and wahlInfo.referendum)}">
	                                                         <c:choose>
	                                                            <c:when test="${empty farbe}">
	                                                               	<c:if test="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2}">
		                                                               <td class="${classG}" align="center" nowrap="nowrap">
					                                                	<c:choose>
						                                                	<c:when test="${!systemInfo.inputmodusComplete or position <= 0}">
				                                                                <img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               	<input class="ergdisable ${clazzI}" type="text" size="10" disabled="disabled" />
				                                                                <img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                            </c:when>
				                                                            <c:otherwise>
				                                                            	&nbsp;
				                                                            </c:otherwise>
				                                                         </c:choose>
		                                                               </td>
																	</c:if>
																	<td class="${classG}" id="headsum${position}" align="center" nowrap="nowrap">
				                                                	<c:choose>
					                                                	<c:when test="${!systemInfo.inputmodusComplete or position <= 0}">
			                                                                <img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                               	<input id="id_0_<%=groupCounter%>" class="${neueingabe ? 'ergdisable':'erg'}  ${clazzI}" ${neueingabe ? 'disabled="disabled"' : ''} ${(position == positionWahlberechtigte && isEbenePSB) ? 'readonly="readonly"' : ''} type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>GRUPPE_${position}" value="${gruppenstimmen}" onkeypress="return navigateEnter('id_0_<%=groupCounter%>', event)" onkeydown="return navigateCursor('id_0_<%=groupCounter%>', event)" autocomplete="off" />
			                                                                <img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                            </c:when>
			                                                            <c:otherwise>
			                                                            	&nbsp;
			                                                            </c:otherwise>
			                                                         </c:choose>
	                                                               </td>
	                                                            </c:when>
	                                                            <c:otherwise>
                                                                     <c:if test="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2}">
		                                                               <td class="${classG}" bgcolor='${farbe}' align="center" nowrap="nowrap">
		                                                                <img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
		                                                               	<input class="ergdisable" type="text" size="10" disabled="disabled" />
		                                                               	<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
		                                                               </td>
																	 </c:if>
	                                                               <td class="${classG}" bgcolor='${farbe}' align="center" nowrap="nowrap">
	                                                                 <img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
	                                                               	 <input id="id_0_<%=groupCounter%>" class="${neueingabe ? 'ergdisable':'erg'}" ${neueingabe ? 'disabled="disabled"' : ''} ${(position == positionWahlberechtigte && isEbenePSB) ? 'readonly="readonly"' : ''} type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>GRUPPE_${position}" value="${gruppenstimmen}" onkeypress="return navigateEnter('id_0_<%=groupCounter%>', event)" onkeydown="return navigateCursor('id_0_<%=groupCounter%>', event)" autocomplete="off" />
	                                                               	 <img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
	                                                               </td>
	                                                            </c:otherwise>
	                                                         </c:choose>
			                                                 <c:choose>
			                                                   <c:when test="${systemInfo.inputmodusComplete and position > 0 and not wahlInfo.referendum}">
	                                                        		<td valign="top" colspan="3" align="center">
				                                                   		<script type="text/javascript">
  																			document.write('<input id="id_${position}_0" class="imagebutton" type="image" onkeydown="return navigateCursorWithToggle(\'id_${position}_0\', event, ${position}, ${numberofcandidates})" src="<%= request.getContextPath() %>/img/icon/collapse.gif" alt="<%= BundleHelper.getBundleString("collapse_expand")%>" />');
  																		</script>
  																		<noscript>&nbsp;</noscript>
			                                                		</td>
  															   </c:when>
			                                                   <c:otherwise>
			                                                		<td class="${classG}" valign="top" colspan="3" align="left">
			                                                   		<c:choose>
					                                                	<c:when test="${position > 0}">
					                                                		<small><b>${position}</b></small>
					                                                	</c:when>
					                                                	<c:when test="${not empty buchstabe}">
					                                                		<b>${buchstabe}</b>
					                                                	</c:when>
				                                                		<c:otherwise>
					                                                		&nbsp;
					                                                	</c:otherwise>
			                                                      	</c:choose>
			                                                		</td>
				                                                   </c:otherwise>
			                                                  </c:choose>
			                                                </tr>
			                                               </c:if>
				                                            	
			                                                <c:set var="kandidaten" value="${gruppe.kandidaten}" scope="page"/>
				                                          	<c:forEach var='kitem' items='${kandidaten}'>
				                                             	<c:set var="gd" value="${-gd}"/>
				                                             	<c:set var="kandidat" value="${kandidaten[kitem.key]}"  />
				                                                <c:set var="kname" value="${wahlInfo.referendum ? kandidat.referendumNameCutOff : kandidat.name}" scope="page"/>
				                                                <c:if test="${wahlInfo.referendum}">
					                                           		<c:set var="kposition" value="${position}" scope="page"/>
					                                           		<c:set var="inputId" value="id_${position}_0" scope="page"/>
					                                           		<c:set var="kpositionForDisplay1" value="${position}" scope="page"/>
				                                                	<c:if test="${kname == 'Voor' && isEbenePSB}">
					                                           			<c:set var="kpositionForDisplay2" value="E" scope="page"/>
				                                                	</c:if>
				                                                	<c:if test="${kname == 'Tegen' && isEbenePSB}">
					                                           			<c:set var="kpositionForDisplay2" value="F" scope="page"/>
				                                                	</c:if>
					                                            </c:if>
					                                            <c:if test="${!wahlInfo.referendum}">
					                                            	<c:set var="kposition" value="${position}_${kandidat.listenposition}" scope="page"/>
					                                           		<c:set var="inputId" value="id_${kposition}" scope="page"/>
					                                            	<c:set var="kpositionForDisplay1" value="${position}.${kandidat.listenposition}" scope="page"/>
					                                           		<c:set var="kpositionForDisplay2" value="${position}.${kandidat.listenposition}" scope="page"/>
					                                            </c:if>
				                                                <c:set var="kstimmen" value="${gruppenergebnis.daten[kandidat.listenposition]}" scope="page"/>
				                                                <c:set var="kfehler" value="${gruppenergebnis.kandidatenfehlermap[kandidat.listenposition]}" scope="page"/>
																<c:set target="${map}" property="kfehler" value="${kfehler}"/>
					                                        	<c:set var="classK" value="hghell" scope="page"/>
				                                             	<c:if test="${gd > 0 || wahlInfo.referendum && position % 2 == 1}">
								                                    <c:set var="classK" value="hgheller" scope="page"/>
					                                            </c:if>
					                                             
					                                             <%-- Fehlerbox Kandidat --%>
					                                             <c:if test="${not empty kfehler}">
				                                                	<% errorList.add((String) map.get("kfehler")); %>
					                                                <tr id="row${kposition}_error">
					                                                   <td><div class="spacer"></div></td>
				                                                      <td align="right" colspan="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2 ? '7' : '6'}">
	                                                                  <table border="0" cellspacing="0" cellpadding="0">
	                                                                  	<tr>
	                                                                        <td class="${classK}">
	                                                                           &nbsp;<img src="<%= request.getContextPath() %>/img/icon/warnung.gif" width="20" height="20" alt="<%= BundleHelper.getBundleString("Warnung")%>" align="middle">
	                                                                        </td>
	                                                                        <td class="${classK}">
	                                                                           <font color="red">${kfehler}</font>
	                                                                        </td>
	                                                                     </tr>
	                                                                  </table>
				                                                   	</td>
				                                                	</tr>
					                                             </c:if>
					                                             <c:if test="${kstimmen == -1}">
							                                         <c:set var="kstimmen" value="" scope="page"/>
				                                                </c:if>
					                                             <tr id="row${kposition}">
					                                                <td width="3%">&nbsp;</td>
				 	                                                <td class="${classK}" width="3%"><b><small>${kpositionForDisplay1}</small></b></td>
					                                                <td class="${classK}"><small><b>${kname}</b></small></td>
					                            
			                                                         <c:choose>
			                                                            <c:when test="${empty kfarbe}">
			                                                      	         <c:if test="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2}">
			                                                               		<td class="${classK}" align="center" nowrap="nowrap">
				                                                                	<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               		<input class="ergdisable" type="text" size="10" disabled="disabled" />
				                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               	</td>
																			</c:if>
		                                                               		<td class="${classK}" align="center" nowrap="nowrap">
		                                                               			<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               	<c:if test="${!wahlInfo.referendum}">
				                                                               	    <input id="${inputId}" class="${neueingabe ? 'ergdisable':'erg'}" ${neueingabe ? 'disabled="disabled"' : ''} type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>KANDIDAT_${position}_${kandidat.listenposition}" value="${kstimmen}" onkeypress="return navigateEnter('${inputId}', event)" onkeydown="return navigateCursor('${inputId}', event)" autocomplete="off" />
				                                                               	</c:if>
				                                                               	<c:if test="${wahlInfo.referendum}">
				                                                               	    <input id="id_0_<%=groupCounter%>" class="${neueingabe ? 'ergdisable':'erg'}" ${neueingabe ? 'disabled="disabled"' : ''} type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>KANDIDAT_${position}_${kandidat.listenposition}" value="${kstimmen}" onkeypress="return navigateEnter('id_0_<%=groupCounter%>', event)" onkeydown="return navigateCursor('id_0_<%=groupCounter%>', event)" autocomplete="off" />
				                                                               	</c:if>
				                                                               	<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                                </td>
			                                                            </c:when>
			                                                            <c:otherwise>
		                                                                     <c:if test="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2}">
				                                                               <td bgcolor='${farbe}' align="center" nowrap="nowrap">
				                                                                	<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               		<input class="ergdisable" type="text" size="10" disabled="disabled" />
				                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               </td>
																			 </c:if>
			                                                               <td bgcolor='${farbe}' align="center" nowrap="nowrap">
			                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               	<c:if test="${!wahlInfo.referendum}">
			                                                               		    <input id="${inputId}" class="erg" type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>KANDIDAT_${position}_${kandidat.listenposition}" value="${kstimmen}" onkeypress="return navigateEnter('${inputId}', event)" onkeydown="return navigateCursor('${inputId}', event)" autocomplete="off" />
				                                                               	</c:if>
				                                                               	<c:if test="${wahlInfo.referendum}">
				                                                               	    <input id="id_0_<%=groupCounter%>" class="erg" type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>KANDIDAT_${position}_${kandidat.listenposition}" value="${kstimmen}" onkeypress="return navigateEnter('id_0_<%=groupCounter%>', event)" onkeydown="return navigateCursor('id_0_<%=groupCounter%>', event)" autocomplete="off" />
				                                                               	</c:if>
			                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                               </td>
			                                                            </c:otherwise>
			                                                         </c:choose>
					                                                <td class="${classK}" colspan="3"><small><b>${kpositionForDisplay2}</b></small></td>
					                                             </tr>
					                                          </c:forEach>
				                                         		<%-- Bei kompletter Eingabe der Stimmen (Kandidaten und Parteien) , erfolg die Eingebe der Parteistimmen erst nach den Kandidaten --%>
				                                         		<c:if test="${systemInfo.inputmodusComplete and position > 0 and not wahlInfo.referendum}" >
					                                         		<c:if test="${not empty gruppefehler}">
					                                                <tr id="row${position}_${numberofcandidates + 1}_error">
					                                                   <td><div class="spacer"></div></td>
					                                                   <td align="right" colspan="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2 ? '7' : '6'}">
					                                                      <table border="0" cellspacing="0" cellpadding="0">
					                                                          <tr>
		                                                                       <td>
		                                                                          <img src="<%= request.getContextPath() %>/img/icon/warnung.gif" width="20" height="20" alt="<%= BundleHelper.getBundleString("Warnung")%>" align="middle">
		                                                                       </td>
		                                                                       <td>
		                                                                          <font color="red">${gruppefehler}</font>
		                                                                       </td>
		                                                                    </tr>
		                                                                 </table>
					                                                   	</td>
				                                                	</tr>
					                                             </c:if>
					                                         	<c:set var="classK" value="hgweiss" scope="page"/>
				                                             	<c:set var="gd" value="${-gd}"/>
					                                             <c:if test="${gd > 0}">
							                                          <c:set var="classK" value="hghell" scope="page"/>
						                                         </c:if>
				                                         		 <tr id="row${position}_${numberofcandidates + 1}">
			                                                      <td width="3%"><b><small>&nbsp;</small></b></td>
			                                                      <td colspan="2" align="right"><small><b>${name} (<ivu:int key="Summe"/>)</b></small></td>
					                                                <c:if test="${gruppenstimmen == -1}">
								                                         <c:set var="gruppenstimmen" value="" scope="page"/>
					                                                </c:if>
			                                                         <c:choose>
			                                                            <c:when test="${empty farbe}">
			                                                               	<c:if test="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2}">
			                                                               		<td align="center" nowrap="nowrap">
			                                                               			<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               		<input class="ergdisable" type="text" size="10" disabled="disabled" />
				                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               	</td>
																			</c:if>
																			<td align="center" nowrap="nowrap">
																				<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                               		<input id="id_${position}_${numberofcandidates + 1}" class="${neueingabe ? 'ergdisable':'erg'}" ${neueingabe ? 'disabled="disabled"' : ''} type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>GRUPPE_${position}" value="${gruppenstimmen}" onkeypress="return navigateEnter('id_${position}_${numberofcandidates + 1}', event)" onkeydown="return navigateCursor('id_${position}_${numberofcandidates + 1}', event)" autocomplete="off" />
			                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                               </td>
			                                                            </c:when>
			                                                            <c:otherwise>
		                                                                     <c:if test="${isDoubleInput && guiEingangMsg.source == ergebniseingangModelKonstanten.SOURCE_GUI_2}">
			                                                               		<td bgcolor='${farbe}' align="center" nowrap="nowrap">
			                                                               			<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               		<input class="ergdisable" type="text" size="10" disabled="disabled" />
				                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
				                                                               	</td>
																			</c:if>
			                                                               	<td bgcolor='${farbe}' align="center" nowrap="nowrap">
			                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                               		<input id="id_${position}_${numberofcandidates + 1}" class="${neueingabe ? 'ergdisable':'erg'}" ${neueingabe ? 'disabled="disabled"' : ''} type="text" size="10" name="<%=ApplicationBeanKonstanten.PREFIX %>GRUPPE_${position}" value="${gruppenstimmen}" onkeypress="return navigateEnter('id_${position}_${numberofcandidates + 1}', event)" onkeydown="return navigateCursor('id_${position}_${numberofcandidates + 1}', event)" autocomplete="off" />
			                                                               		<img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="2" height="18" align="top" />
			                                                                </td>
			                                                            </c:otherwise>
			                                                         </c:choose>
				                                                   <td colspan="3"><small><b>&nbsp;</b></small></td>
				                                             	</tr>
				                                         		</c:if>
				                                         		<% groupCounter++; %>
				                                          </c:forEach>
				                                       </table>
			                                       </div>
			                                    </td>
			                                    <td>&nbsp;</td>
			                                 </tr>
			                                 <tr>
			                                    <td><img src="<%= request.getContextPath() %>/img/icon/eck_unten_links.gif" width="20" height="20"></td>
			                                    <td>&nbsp;</td>
			                                    <td align="right"><img src="<%= request.getContextPath() %>/img/icon/eck_unten_rechts.gif" width="20" height="20"></td>
			                                 </tr>
			                              </table>
			                             <p/>
			                              <div align="center">
			                                 <c:set var="submitString" scope="page">
			                                    <ivu:int key="ErgebnisAufnehmen"></ivu:int>
			                                 </c:set>
			                           		<c:choose>
			                           			<c:when test="${lastStatus == ergebniseingangModelKonstanten.STATE_WARNING}">
				                                    <input id="id_${aktuelleGruppenNr + 1}_0" class="box2a" style="cursor:pointer" type="submit" value="<%= BundleHelper.getBundleString("EingabenBestaetigen") %>" name="${applicationBeanKonstanten.PREFIX}bestaetigen" onkeydown="return navigateCursor('id_${aktuelleGruppenNr + 1}_0', event)" />
			                           			</c:when>
			                           			<c:when test="${neueingabe}">
				                                    <ivu:a href="<%= reloadUrl %>" id="box2a" style="cursor:pointer" target="_top"><%= BundleHelper.getBundleString("Neueingabe") %></ivu:a>
			                           			</c:when>
			                           			<c:otherwise>
					                           		<input id="id_${aktuelleGruppenNr + 1}_0" class="box2a" style="cursor:pointer" type="submit" value="${submitString}" name="${applicationBeanKonstanten.PREFIX}abs" onkeydown="return navigateCursor('id_${aktuelleGruppenNr + 1}_0', event)" />
			                           			</c:otherwise>
			                           		</c:choose>
			                              </div>
			                           </ivu:form>
			                        </tr>
			                        <tr><td height="10">&nbsp;</td></tr>
			                     </table>
			                  </td>
			               </tr>
			            </table>
			         </c:if>
		           <%-- FehlerBox start --%>
		            <c:choose>
		               <c:when test="${lastStatus == ergebniseingangModelKonstanten.STATE_ERROR}">
		                  <c:set var="bgcol" value="#F00" scope="page"/>
		                  <c:set var="fgcol" value="white" scope="page"/>
		                  <c:set var="id" value="layer1" scope="page"/>
		                  <c:set var="icon" value="/img/icon/warnung.gif" scope="page"/>
		                  <c:set var="warnung" value="true" scope="page"/>
		               </c:when>
		               <c:when test="${lastStatus == ergebniseingangModelKonstanten.STATE_WARNING}">
		                  <c:set var="bgcol" value="#FC6" scope="page"/>
		                  <c:set var="fgcol" value="inherit" scope="page"/>
		                  <c:set var="id" value="layer1b" scope="page"/>
		                  <c:set var="icon" value="/img/icon/warnung_blau.gif" scope="page"/>
		                  <c:set var="warnung" value="true" scope="page"/>
		               </c:when>
		               <c:otherwise>
		                  <c:set var="warnung" value="false" scope="page"/>
		               </c:otherwise>
		            </c:choose>
		            <c:if test="${warnung}">
		               <%
		                  StringBuffer errorSB = new StringBuffer();
		                  if (!errorList.isEmpty()) {
		                     Iterator errorIterator = errorList.iterator();
		                     while (errorIterator.hasNext()) {
		                        errorSB.append(ErgebniseingangKonstanten.THREE_ASTERISKS);
		                        errorSB.append(ClientHelper.forHTML((String) errorIterator.next(), true));
		                        errorSB.append("<br />"); //$NON-NLS-1$
		                     }
		                  }
		                  if (errorSB.length() == 0) {
		                     errorSB.append(ClientHelper.forHTML(guiEingangMsg.getFehler(), true));
		                  }
		               %>
		              <div id='${id}'>
		                <img src="<%= request.getContextPath() %>${icon}" width="20" height="20" alt="<%= BundleHelper.getBundleString("Warnung") %>" />
		                <c:out value="<%= errorSB.toString() %>" escapeXml="false"/>
		              </div>
		            </c:if>
		          <%-- FehlerBox Ende --%>
		          <%-- Expand system groups if one of them has an error or a warning --%>
		          <c:if test="${isCollapsibleGroupErrorExists and (empty unterschiedeVorhanden or not unterschiedeVorhanden)}">
		          	<script language="JavaScript" type="text/javascript">
//<![CDATA[
	expandSystemGroups();
//]]>
      				</script>
		          </c:if>
		         </c:otherwise>
		      </c:choose>
		   </div>
		</div>
    </jsp:body>
   </jsp:element>
</html>