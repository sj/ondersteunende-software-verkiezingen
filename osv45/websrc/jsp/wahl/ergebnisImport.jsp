<jsp:directive.page import="de.ivu.wahl.client.util.ClientHelper" />
<jsp:directive.page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" />
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.client.beans.OutputCommand"%>
<%@ page import="de.ivu.wahl.dataimport.AbstractImportEML"%>
<%@ page import="de.ivu.wahl.dataimport.HashCodeSplitter"%>
<%@ page import="de.ivu.wahl.dataimport.ImportEML510"%>
<%@ page import="de.ivu.wahl.dataimport.SecurityLevel"%>
<%@ page import="de.ivu.wahl.client.beans.ErgebnisImportBean"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.GebietsBaum"%>
<%@ page import="de.ivu.wahl.modell.GebietInfo"%>
<%@ page import="de.ivu.wahl.client.beans.InitGuiCommand"%>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="ergImpBean" scope="session" class="de.ivu.wahl.client.beans.ErgebnisImportBean" />

<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "ergImp"; //$NON-NLS-1$

GebietsBaum gebietsBaum = appBean.getGebietsBaum();
GebietInfo rootInfo = (GebietInfo)gebietsBaum.getWurzel().getUserObject();
int level = ClientHelper.getLevel(request, rootInfo.getGebietsart());
int gebNr = ClientHelper.getGebietNr(request, rootInfo.getNummer());  
DefaultMutableTreeNode gebietNode = gebietsBaum.getGebietsNode(level, gebNr);
GebietInfo gebietInfo = gebietNode != null ? (GebietInfo)gebietNode.getUserObject() : rootInfo;
response.setBufferSize(128); 
ImportEML510 impDef = ergImpBean.getImportEML510(level, gebNr);
WahlInfo wahlInfo = WahlInfo.getWahlInfo();
SystemInfo systemInfo = SystemInfo.getSystemInfo();
String breite = "100%";

boolean isReferendum = wahlInfo.isReferendum();
if (isReferendum) {
   helpKey = "ergImpRef";
}
String shortMessageKeySuffix = systemInfo.getEbenenklartext() + "_" + systemInfo.getModusklartext(); //$NON-NLS-1$
String messageKeySuffix = shortMessageKeySuffix;
if ("P4".equals(systemInfo.getModusklartext()) && wahlInfo.isEK()) { //$NON-NLS-1$
  messageKeySuffix += "_EK"; //$NON-NLS-1$
}
String urlToGebietEingang = ClientHelper.generateURL(request, ApplicationBeanKonstanten.GEBE, true);
%>
<c:set var="gebietInfo" value="<%= gebietInfo %>" scope="page"/>
<c:set var="isShowButtonToGebietEingang" value="<%= systemInfo.isFileInputWithManualConfirmation() %>" scope="page"/>
<c:set var="titel">
<%= BundleHelper.getBundleString("Ergebnisse_importieren_titel_" + messageKeySuffix) %>
</c:set>
<html>
   <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
	<head>
		<title>${titel}</title>
		<script>
			var contextPath = "<%=request.getContextPath()%>";
		</script>
		<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
		<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/osv.js"></script>
  		<link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
		<%
			response.setDateHeader("Last-Modified", -1);
			response.setDateHeader("Expires", 0); // prevents caching at the proxy server
			response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		%>
		<style type="text/css">
		td { line-height: 15px }
		</style>
	</head>
	<body class="hghell" onload="sc()">
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
		<%@include file="/jsp/fragments/help_div.jspf"%>
		<div class="hgschwarz"
			style="height: 1px; line-height: 1px; width: 100%;">
			&nbsp;
		</div>
		<div class="hggrau"
			style="height: 20px; width: 100%; text-indent: 5px; line-height: 18px;">
			${titel}
		</div>
		<div class="hgschwarz"
			style="height: 1px; line-height: 1px; width: 100%;">
			&nbsp;
		</div>
		<div class="hghell">
			<%-- Gebiet gesperrt für diesen Anwender --%>
	      <% if ((!(appBean.getAnwContext().getKeyForViewlock().equals(appBean.getINPUT_MAP().get(gebietInfo.getID_Gebiet())))) 
	          || (wahlInfo.isFreigegeben())
	          || (!systemInfo.isInputmodusComplete())) { %>
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
									                     <% if (wahlInfo.isFreigegeben()) { %>
										                     <br/>
																	<ivu:int key="Freigabe_bereits_erfolgt"/><br/>
																	<ivu:int key="Freigabe_bereits_erfolgt_Kein_Ergimport"/>
																	<br/><br/>
									                     <% } else if (!systemInfo.isInputmodusComplete())  { %>
										                     <br/>
										                  	<ivu:int key="EingabeWahlgebietNichtMoeglichDa"></ivu:int><br />
																	<ivu:int key="Inputmudus_Nicht_Komplett"/>
																	<br/><br/>
									                     <% } else if (!(appBean.getAnwContext().getKeyForViewlock().equals(appBean.getINPUT_MAP().get(gebietInfo.getID_Gebiet())))){ %>
										                  	<ivu:int key="EingabeWahlgebietNichtMoeglichDa"></ivu:int><br />
										                     <%-- Grund kann nur nachwahl sein --%>
										                     <ivu:int key="GebietWirdVonAnderemAnwenderBearbeitet"/>
										                     <br/>
																<% } %>
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
			<% } else { %>
				<fieldset style="border: 1px solid #093C69; padding: 15px; margin: 15px;">
					<legend>
						<b><%= BundleHelper.getBundleString("Ergebnisse_importieren_"+systemInfo.getModusklartext()) %></b>
					</legend><%
					String url = "/osv?"
							+ "cmd=ergImp_import_Ergebnisse&"
							+ "ts="
							+ System.currentTimeMillis()
							+ "&"
							+ ClientHelper.getParametersDoNotStartWith(request,
									ApplicationBeanKonstanten.PREFIX);
				%> 
					
					<% if (null != impDef.getFehlermeldung()) { %>
				     	<div style="padding: 0.5em; color: red;">
				     		<%= ClientHelper.forHTML(impDef.getFehlermeldung()) %>
				     	</div>
			      <% } else if (AbstractImportEML.STATUS_KOMPLETT == impDef.getStatus()) { %>
			       	<div style="padding: 0.5em; font-style: oblique; font-weight: bold; color: green;">
							<ivu:int key="Ergebnisse_importieren_EML510_import_erfolgreich" /><br/><br/>
		      	</div>
					<% }
					if (AbstractImportEML.STATUS_INIT == impDef.getStatus()) { %>
						<ivu:form action="<%=url%>" enctype="multipart/form-data" onsubmit="transp();">
						<% if (null != impDef.getLastFileName() && null != impDef.getLastImport() && null != impDef.getLastGebietName()) { %> 
				     		<div style="padding: 0.5em;">
						      <table style="font-style: oblique;">
						      	<tr>
						      		<td width="125"><ivu:int key="Ergebnisse_importieren_EML510_Letzter_Import" />:</td>
						      		<td><%= impDef.getLastImport()%></td>
						      	</tr>
						      	<tr>
						      		<td><ivu:int key="Ergebnisse_importieren_EML510_Letzter_Dateiname" />:</td>
						      		<td><%= ClientHelper.forHTML(impDef.getLastFileName()) %></td>
						      	</tr>
						      	<tr>
						      		<td><ivu:int key="Ergebnisse_importieren_EML510_Letztes_Gebiet" />:</td>
						      		<td><%= ClientHelper.forHTML(impDef.getLastGebietName()) %></td>
						      	</tr>
						     	</table>
					     </div>
					     <br/><br/>
					     <div style="padding: 0.5em;">
								<ivu:int key="Ergebnisse_weiteres_importieren_EML510_info" />
							</div>
				      <% } else { %>
							<div style="padding: 0.5em;">
								<%= BundleHelper.getBundleString("Ergebnisse_importieren_EML510_info_" + messageKeySuffix) %>
							</div>
						<% } %>
							<div style="padding: 0.5em;">
								<ol>
	                         <li><ivu:int key="ErgebnisdateiAuswaehlen"/></li>
	                         <li><%=BundleHelper.getBundleString("ErgebnisdateiImportieren_" + shortMessageKeySuffix)%></li>
	                         <li><ivu:int key="VorgangZeitInAnspruchNehmen"/></li>
	                     </ol>
                     </div>
							<div style="padding: 0.5em;">
								<input type="file" name="<%=ApplicationBeanKonstanten.PREFIX + "eml510"%>"
									size="100" style="font-size: 11px; width: 100%;" accept="*">
							</div>
							<div style="padding: 0.5em; text-align: center;">
								<input id="box2" style="cursor: pointer" type="submit"
									name="<%=ApplicationBeanKonstanten.PREFIX
									+ "importieren"%>"
									value="<%=BundleHelper
													.getBundleString("Ergebnisse_importieren_button_" + messageKeySuffix)%>">
							</div>
						</ivu:form>
					<% } else if (AbstractImportEML.STATUS_URL_KOMPLETT == impDef.getStatus()) { %>
				   	<ivu:form name="myform" action="<%=url%>" onsubmit="<%= "transp();" %>">
					   	<div style="padding: 0.5em; padding-bottom: 2.5em;">
								<br/>
								<br/>
								<% if (SecurityLevel.CONFIRM_HASH_CODE.equals(impDef.getSecurityLevel())) {
									String hashCode = impDef.getHashWert510(); %>
							    	<!-- Only confirmation of complete hashcode, no user entry: Show complete hashcode, add hidden field with the (unneccessary) user input --> 
									<%= BundleHelper.getBundleString("Ergebnisse_importieren_HashWert_Confirm_" + shortMessageKeySuffix) %><br/><br/>
									<%= hashCode %>
									<input type="hidden" size="4" maxlength="4" name="<%=ErgebnisImportBean.FELD_HASHCODE_510_INPUT_0 %>" value="<%= HashCodeSplitter.HIDDEN_INPUT %>" />&nbsp;
									<input type="hidden" size="4" maxlength="4" name="<%=ErgebnisImportBean.FELD_HASHCODE_510_INPUT_1 %>" value="<%= HashCodeSplitter.HIDDEN_INPUT %>" />&nbsp;
								<% } else { %>
									<%= BundleHelper.getBundleString("Ergebnisse_importieren_HashWert_" + shortMessageKeySuffix) %><br/><br/>
									<%= impDef.getHashWert510ToConfirm(0) %>
									<input type="text" size="4" maxlength="4" name="<%=ErgebnisImportBean.FELD_HASHCODE_510_INPUT_0 %>" 
										onkeyup="this.value=this.value.toUpperCase(); if (this.value.length >= 4) document.myform.<%=ErgebnisImportBean.FELD_HASHCODE_510_INPUT_1 %>.focus();" autocomplete="off" />&nbsp;
									<%= impDef.getHashWert510ToConfirm(1) %>
									<input type="text" size="4" maxlength="4" name="<%=ErgebnisImportBean.FELD_HASHCODE_510_INPUT_1 %>" 
										onkeyup="this.value=this.value.toUpperCase(); if (this.value.length >= 4) document.myform.<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>.focus();" autocomplete="off" />&nbsp;
									<%= impDef.getHashWert510ToConfirm(2) %>
								<% } %>
							</div>
							<div style="padding: 0.5em; text-align: center;">
						      <input id="box2" style="cursor:pointer" type="submit" name="<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>" value="<%= BundleHelper.getBundleString("Hashcode_bestaetigen_button") %>">
						      <input id="box2" style="cursor:pointer" type="submit" name="<%=ErgebnisImportBean.FELD_RESET%>"                    value="<%= BundleHelper.getBundleString("Hashcode_ablehnen_button") %>">
							</div>
						</ivu:form>
		   	  <% } else if (AbstractImportEML.STATUS_KOMPLETT == impDef.getStatus()) { %>
						<ivu:form action="<%=url%>" onsubmit="transp();">
						<% if (null != impDef.getLastFileName() && null != impDef.getLastImport() && null != impDef.getLastGebietName()) { %> 
				     		<div style="padding: 0.5em;">
						      <table style="font-style: oblique;">
						      	<tr>
						      		<td width="125"><ivu:int key="Ergebnisse_importieren_EML510_Letzter_Import" />:</td>
						      		<td><%= impDef.getLastImport()%></td>
						      	</tr>
						      	<tr>
						      		<td><ivu:int key="Ergebnisse_importieren_EML510_Letzter_Dateiname" />:</td>
						      		<td><%= ClientHelper.forHTML(impDef.getLastFileName()) %></td>
						      	</tr>
						      	<tr>
						      		<td><ivu:int key="Ergebnisse_importieren_EML510_Letztes_Gebiet" />:</td>
						      		<td><%= ClientHelper.forHTML(impDef.getLastGebietName()) %></td>
						      	</tr>
						     	</table>
					     </div>
					     <br/><br/>
				     <% } %>
					   <c:if test="${isShowButtonToGebietEingang}">
				         <div style="padding: 0.5em;">
				            <c:if test="${gebietInfo.vollstaendig}">
				               <ivu:int key="NeueErsteingabeAbgeschlossen"/>
				            </c:if>
				      	    <c:if test="${!gebietInfo.vollstaendig}">
				               <ivu:int key="ErsteingabeAbgeschlossen"/>
				            </c:if>
				            <ivu:a href="<%= urlToGebietEingang %>" id="box2a" style="cursor:pointer" target="_top"><%= BundleHelper.getBundleString("Zweiteingabe") %></ivu:a>
				         </div>
					   </c:if>
					   <c:if test="${not isShowButtonToGebietEingang}">
						<div style="padding: 0.5em; text-align: center;">
							<input id="box2" style="cursor:pointer" type="submit" name="<%=ErgebnisImportBean.FELD_RESET%>" value="<%=BundleHelper.getBundleString("Neue_Wahl_importieren_reset_button") %>">
						</div>
					   </c:if>
					</ivu:form> 
		   	 <% } %>
			</fieldset>
	   	<% } %>
		</div>
	</div>
	</div>
	</body>
</html>