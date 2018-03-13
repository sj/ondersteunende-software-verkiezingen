<%--
 *******************************************************************************
 * Eingabe des Ergebnisses einer Erfassungseinheit
 *
 * author:  cos@ivu.de  Copyright (c) 2005-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: referendum.jsp,v 1.4 2010/11/19 09:45:31 jon Exp $
 *******************************************************************************
 --%>
<%@ page pageEncoding="ISO-8859-1" contentType="text/html; charset=UTF-8"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
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
   

<c:set var="wahlInfo" value="${appBean.wahlInfo}" scope="page"/>
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
<c:set var="systemInfo" value="<%= SystemInfo.getSystemInfo() %>" scope="page"/>
				                                              	
<c:set target="${map}" property="gebietInfo" value="${gebietInfo}"/>

<c:set var="gebietName" value="${gebietInfo.name}" scope="page"/>
<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "referendum"; //$NON-NLS-1$

   // holen einer vorbereiteten Eingangsmessage für eine Erfassungseinheit
   GUIEingangMsg guiEingangMsg = eingabeBean.getGUIMsg(request, (GebietInfo)map.get("gebietInfo"), false); //$NON-NLS-1$
   String breite= "100%"; //$NON-NLS-1$

%>
<c:set var="guiEingangMsg" value="<%= guiEingangMsg %>" scope="page"/>
<c:set var="lastStatus" value="${guiEingangMsg.status}" scope="page"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
    /*]]>*/
      </style>
       <script>
			var contextPath = "<%=request.getContextPath()%>";
		</script>
   </head>
   
   <jsp:element name="body">
    <jsp:attribute name="class">hghell</jsp:attribute>
    <jsp:attribute name="topmargin">0</jsp:attribute>
    <jsp:attribute name="marginwidth">0</jsp:attribute>
    <jsp:attribute name="marginheight">0</jsp:attribute>
    <jsp:body>   
  	<div id="content">
  		<div>
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
								<td colspan="2" class="hggrau" height="20"><span class="abstand1"><ivu:int key="Referendum"/></span></td>
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
									<table border="0" cellspacing="0" cellpadding="0" align="center" class="hgformular" width="92%">
										<tr>
											<td><img src="<%= request.getContextPath() %>/img/icon/eck_oben_links.gif" width="20" height="20"></td>
											<td>&nbsp;</td>
											<td align="right"><img src="<%= request.getContextPath() %>/img/icon/eck_oben_rechts.gif" width="20" height="20"></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td class="hgformular" align="left">
												<div id="erg">
													<table class="max" border="0" cellpadding="2" cellspacing="2" >
														<tr>
															<td valign="top" colspan="4">
																<h3><ivu:int key="Referendum"/>:</h3>	  	  		
															</td>
														</tr>
														<tr>
															<td valign="top" colspan="4">
																<b><%= repHandler.getProperty(Konstanten.KEY_REFERENDUM_TEXT) %></b>	  	  		
															</td>
														</tr>
														<tr>
															<td colspan="4"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="15"></td>
														</tr>
														<%-- Hauptbereich --%>
														<c:set var="gruppendaten" value="${guiEingangMsg.gruppendaten}" scope="page" />
														<c:forEach var='item' items='${gruppendaten}' varStatus="s">
															<c:set var="clazzI" value=""/>
															<c:set var="styleT" value=""/>
															<c:if test="${s.count == 1}">
																<c:set var="clazzI" value="bordercolorWahlberechtigte"/>
																<c:set var="styleT" value="color: #C4974B; "/>
															</c:if>
															<c:set var="gd" value="1"/>
															<c:set var="gruppe" value="${gruppendaten[item.key]}"  />
															<c:set var="first" value="true" scope="page"/>
															<c:set var="position" value="${gruppe.position}" scope="page"/>
															<c:set var="name" value="${gruppe.name}" scope="page"/>
															<c:set var="farbe" value="${gruppe.farbe}" scope="page"/>
															<c:set var="gruppenergebnis" value="${guiEingangMsg.gruppenergebnisse[position]}" scope="page"/>
															<c:set var="gruppefehler" value="${gruppenergebnis.gruppefehler}" scope="page"/>
															<c:set var="gruppenstimmen" value="${gruppenergebnis.gesamtstimmen}" scope="page"/>
															<c:if test="${position > 0 && first}">
																<tr height="1">
																	<td colspan="4" height="1"><span class="spacer"></span></td>
																</tr>
																<c:set var="kandidaten" value="${gruppe.kandidaten}" scope="page"/>
																<c:forEach var='kitem' items='${kandidaten}'>
																	<c:set var="gd" value="${-gd}"/>
																	<c:set var="kandidat" value="${kandidaten[kitem.key]}"  />
																	<c:set var="kname" value="${kandidat.referendumName}" scope="page"/>
																	<c:set var="kposition" value="${position}" scope="page"/>
																	<c:set var="classK" value="hgweiss" scope="page"/>
																	<c:if test="${gd > 0}">
																		<c:set var="classK" value="hghell" scope="page"/>
																	</c:if>
																	<tr>
																		<td width="3%">&nbsp;</td>
																		<td class="${classK}" width="3%"><b><small>${kposition}</small></b></td>
																		<td class="${classK}"><small><b>${kname}</b></small></td>
																		<td class="${classK}" width="3%"><small><b>${kposition}</b></small></td>
																	</tr>
																</c:forEach>
															</c:if>
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
								</td>
							</tr>
							<tr><td height="10">&nbsp;</td></tr>
						</table>
					</td>
				</tr>
			</table>

	
		   </div>
		</div>
    </jsp:body>
   </jsp:element>
</html>