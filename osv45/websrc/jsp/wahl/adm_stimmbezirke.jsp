<jsp:directive.page import="de.ivu.wahl.client.util.ClientHelper" />
<jsp:directive.page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" />
<%@ page import="de.ivu.wahl.dataimport.AbstractImportEML"%>
<%@ page import="de.ivu.wahl.dataimport.IImportEML"%>
<%@ page import="de.ivu.wahl.dataimport.ImportElectionMetadata"%>
<%@ page import="de.ivu.wahl.client.beans.AdministrationBean"%>
<%@ page import="de.ivu.wahl.client.beans.Command"%>
<%@ page import="de.ivu.wahl.client.beans.WahlImportBean"%>
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.modell.ejb.Gebiet"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="impBean" scope="session" class="de.ivu.wahl.client.beans.WahlImportBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "admStimmbez"; //$NON-NLS-1$

	WahlInfo wahlInfo = WahlInfo.getWahlInfo();
	
	boolean newSBs = true;
	int work = ClientHelper.getWork(request, -1);
	if (work != -1) {
		if (Command.ADM_STIMMBEZIRKE_EDIT.hasId(work)) {
			newSBs = false; 
		}
	}
	
	// check for locks of other users
	boolean lockByOtherUser = false;
	String keyForViewlock = appBean.getAnwContext().getKeyForViewlock();
	for (String value : appBean.getINPUT_MAP().values()) {
		if (!value.equals(keyForViewlock)) {
			lockByOtherUser = true;
			break;
		}
	}
%>
<html>
 <head>
  <title><ivu:int key="<%= newSBs ? "Neue_Wahl_importieren_titel" : "Stimmbezirke_bearbeiten_titel" %>"/></title>
  <%
    response.setDateHeader("Last-Modified", -1); //$NON-NLS-1$
    response.setDateHeader ("Expires", 0); // prevents caching at the proxy server //$NON-NLS-1$
    response.setHeader("Cache-Control","no-cache"); // HTTP 1.1 //$NON-NLS-1$ //$NON-NLS-2$
    response.setHeader("Pragma","no-cache"); // HTTP 1.0 //$NON-NLS-1$ //$NON-NLS-2$
    
    IImportEML impDef = impBean.getImportMetadata();
    SystemInfo systemInfo =  SystemInfo.getSystemInfo();
    String wurzelgebietName = BundleHelper.getBundleString("Stimmbezirke_bearbeiten"); //$NON-NLS-1$
    if (newSBs) {
    	wurzelgebietName = BundleHelper.getBundleString("Neue_Stimmbezirke_erzeugen"); //$NON-NLS-1$
    }
    try{
	  if (wahlInfo != null) {
      	wurzelgebietName = wahlInfo.getWahl().getWurzelgebiet().getName();
      }
    } catch(Exception e){
      //nothing to do
    }
    String finalizeButtonLabel = BundleHelper.getBundleString("Stimmbezirke_bearbeiten_abschließen_button"); //$NON-NLS-1$
    if (newSBs) {
    	finalizeButtonLabel = BundleHelper.getBundleString("Stimmbezirke_abschließen_button"); //$NON-NLS-1$
    }
    if (admBean._adminMsgStimmbezWarning != null && !admBean._adminMsgStimmbezWarning.isEmpty()
    	&& (admBean._adminMsgStimmbez == null || admBean._adminMsgStimmbez.isEmpty())) {
    	if (newSBs) {
    		finalizeButtonLabel = BundleHelper.getBundleString("Stimmbezirke_abschließen_bestaetigen_button"); //$NON-NLS-1$
    	} else {
    		finalizeButtonLabel = BundleHelper.getBundleString("Stimmbezirke_bearbeiten_abschließen_bestaetigen_button"); //$NON-NLS-1$
    	}
    }
    
    boolean errorExists = false;
    boolean postalVoteOfficeSelectable = false;
    if (wahlInfo != null) {
    	postalVoteOfficeSelectable = wahlInfo.isPostalVoteOfficeSelectable();
    }
  	
  	List<GebietModel> stimmbezirke = admBean.getStimmbezirkeSortedByNummer();
  	String prefix = ApplicationBeanKonstanten.PREFIX;
  	
  %>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css" />
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery.autocomplete.css">
  <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.3.2.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.autocomplete.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath() %>/js/gbadata.js"></script>
  <script>
		var contextPath = "<%=request.getContextPath()%>";

	<% if (!stimmbezirke.isEmpty()) {%>
		// GBA support
		$(document).ready(function(){<%
   		for (int row = 0; row < stimmbezirke.size(); row++) { %>
			$("#id_<%= row %>_1").autocomplete(gbaData);<%
		}%>
		});<%
	}%>		  	
  </script>
  <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/osv.js"></script>
  <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
 </head>
 <body class="hghell" onload="sc()"> 
	<div id="content">
		<div id="statusbalken" style="display:none;" class="status">
		  	<div style="margin: 13px; padding: 13px 0px 0px 0px; width: 360px; border: 1px solid;">
				<div style="padding: 0px 13px 4px 13px;"></div>
		  		<table align="center" style="background-color: rgb(9, 60, 105);">
					<tr>
				   	<td style="color: white">
							<ivu:int key="MetadatenimportInWenigenSekunden"/>
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
  <div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
   &nbsp;
  </div>
  <div class="hggrau" style="height: 20px; width: 100%; text-indent: 5px; line-height: 18px;">
   <ivu:int key="<%= newSBs ? "Neue_Stimmbezirke_erzeugen_titel" : "Stimmbezirke_bearbeiten_titel" %>"/>
  </div>
  <div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
   &nbsp;
  </div>
  
  <% 
	String url = "/osv?"
	+ "cmd=adm_import_Stimmbezirke&"
	+ "ts=" + System.currentTimeMillis() + "&"
		+ ClientHelper.getParametersDoNotStartWith(request, ApplicationBeanKonstanten.PREFIX);
	
	String formurlAnzahl = ClientHelper.generateURL(request, null, AdministrationBean.CMD_ADM_STIMMBEZIRK_ANZAHL, -1, true);
	String breite = "100%"; 
  
	if (wahlInfo == null || wahlInfo.isFreigegeben() || lockByOtherUser){%>
  <div class="hghell">
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
										            <legend><b><ivu:int key="Stimmbezirke_bearbeiten_NichtMoeglich"/></b></legend><br />
										            <table class="max" border="0" cellspacing="0" cellpadding="0">
										                <tr>
										                   <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
										                   <td valign="top">
										                      <ivu:int key="Stimmbezirke_bearbeiten_NichtMoeglichDa"></ivu:int><br />
										                      <% if (wahlInfo == null) { %>
										                      	<ivu:int key="FuerStimmbezirkeWahldefinitionNotwendig"/>
										                      <% } else if (wahlInfo.isFreigegeben()) { %>
										                      	<ivu:int key="Freigabe_bereits_erfolgt"/>
										                      <% } else if (lockByOtherUser) { %>
										                      	<ivu:int key="EinGebietWirdVonAnderemAnwenderBearbeitet"/>
										                      <% } %>
										                      <br/>
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
  </div>
<% } else { %>
  <div class="hghell">
   <fieldset style="border: 1px solid #093C69; padding: 15px; margin: 15px;">
    <legend>
     <b><%= wurzelgebietName %></b>
    </legend>
    	<% if (admBean._adminMsgStimmbezFile != null && !admBean._adminMsgStimmbezFile.isEmpty()){%>
			<p style="color:red;"><b><%= ClientHelper.forHTML(admBean._adminMsgStimmbezFile, true) %></b></p>
		<% admBean._adminMsgStimmbezFile = null;
		   errorExists = true;
		} %>
    	<% if (appBean.getWahlen().size() == 0) { %>
	     <div style="padding: 0.5em;">
		      <ivu:int key="Neue_Stimmbezirke_Hinweis_Wahlmetadatenimport"/>
	     </div>
      <% } else if (newSBs && appBean.getWahlen().size() == 1 && (WahlModel.STATE_METADATA_P4 == wahlInfo.getStatus() || WahlModel.STATE_METADATA_P5 == wahlInfo.getStatus())) { %>
       	<div style="padding: 0.5em;">
       		<% if (wahlInfo.isReferendum()) { %>
	      		<ivu:int key="ReferendumimportErfolgreich"/>
       		<% } else { %>
	      		<ivu:int key="WahlimportErfolgreich"/>
	      	<% } %>
	         	<ivu:form name="logout" action="/osv?cmd=app_logout">
			      	<br/>
	   	        	<b><ivu:int key="Abmelden_von"/> <%=appBean.getAnwContext().getAnmeldename()%></b>
	      	   	<div style="padding: 1em; text-align: center">
	               	<input id="box2" style="cursor:pointer" type="submit" value="<%=BundleHelper.getBundleString("Abmelden_jetzt") %>" name="logout">
	             	</div>
					</ivu:form>
			</div>
      <% } else { 
 			if (stimmbezirke.isEmpty()) { %>
				<ivu:form action="<%=url%>" enctype="multipart/form-data" onsubmit="transp();">
					<div style="padding: 0.5em;">
						<ivu:int key="Neue_Stimmbezirke_importieren_METADATEN_info"/>
					</div>
					<div style="padding: 0.5em; width=100%;">
						<input type="file" name="<%=AdministrationBean.FELD_STIMMBEZIRKDEFINITION%>" size="100" style="font-size:11px; width: 100%;" accept="*">
					</div>
					<div style="padding: 0.5em; text-align: right; margin-top: 0px;">
						<input id="box2" style="cursor:pointer" type="submit"
					  	name="<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>" value="<%=BundleHelper.getBundleString("Neue_Stimmbezirke_importieren_button") %>">
					</div>
				</ivu:form>
				<div style="padding: 2em 0.5em 4em;">
					<hr/><hr/>
				</div>
	   	<%} %>
	      <ivu:form name="appstate" action="<%= formurlAnzahl %>" onsubmit="transp();">
	   	<% if (admBean._adminMsgStimmbez != null && !admBean._adminMsgStimmbez.isEmpty()){%>
				<p style="color:red;"><b><%= ClientHelper.forHTML(admBean._adminMsgStimmbez, true) %></b></p>
			<% admBean._adminMsgStimmbez = null;
			   errorExists = true;
			}
	   	   if (admBean._adminMsgStimmbezWarning != null && !admBean._adminMsgStimmbezWarning.isEmpty()){%>
				<p class="warningMessage"><b><%= ClientHelper.forHTML(admBean._adminMsgStimmbezWarning, true) %></b></p>
				<% if (!errorExists) { %>
					<input type="hidden" value="1" name="<%=ApplicationBeanKonstanten.PREFIX%>force"/>
				<% } %>
			<% admBean._adminMsgStimmbezWarning = null;	
			}
	   	   if (admBean._adminMsgStimmbezConfirmation != null && !admBean._adminMsgStimmbezConfirmation.isEmpty()){%>
				<p style="color:green;"><b><%= ClientHelper.forHTML(admBean._adminMsgStimmbezConfirmation, true) %></b></p>
			<% admBean._adminMsgStimmbezConfirmation = null;	
			} %>
				<% if (!stimmbezirke.isEmpty()) { %>	
		   		<table border="0" cellspacing="0" cellpadding="1" width="100%">
		            <tr class="hgrot">
		               <td valign="top">
		                  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
		                     <tr class="hgeeeeee">
		                        <td width="5" height="18">&nbsp;</td>
		                        <td><ivu:int key="Neue_Stimmbezirke_Tabellenueberschrift"/></td>
		                        <td width="5" height="18">&nbsp;</td>
		
		                     </tr>
		                     <tr>
		                         <td colspan="3" class="hgrot"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
		                     </tr>
		                     <tr>
		                        <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
		                     </tr>
		                     <tr>
		                        <td width="5"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
		
		                        <td valign="top">
		                           <table border="0" width="100%" cellspacing="2" cellpadding="4" align="center">
		                              <%
											  	boolean first = true;
												Iterator<GebietModel> iterator = stimmbezirke.iterator();
												int i = 1;
												int rowId = 0;
												int summeWahlberechtigte = 0;
									    		while (iterator.hasNext()){
													GebietModel stimmbezirk  = iterator.next();
													String urlLoeschen = ClientHelper.generateURL(request, null, "adm_Stimmbezirk_loeschen", -1, true)+"&"+prefix+"id="+stimmbezirk.getID_Gebiet();
													summeWahlberechtigte = summeWahlberechtigte + stimmbezirk.getWahlberechtigte();
									      		if (first) { %>
											     	  <tr class="hgformular">
															<td style="width: 10%;">
																<ivu:int key="Neue_Stimmbezirke_Nr"/>
															</td>
															<td>
																<ivu:int key="Neue_Stimmbezirke_Name"/>
															</td>
															<td>
																<ivu:int key="Neue_Stimmbezirke_Zipcode"/>
															</td>
															<td style="text-align: right;">
																<ivu:int key="Neue_Stimmbezirke_Wahlberechtigte"/>
															</td>
															<% if (postalVoteOfficeSelectable) { %>
																<td style="text-align: center; width: 10%;">
																	<ivu:int key="Neue_Stimmbezirke_postalvote"/>
																</td>
															<% }
															   if (newSBs) {
															%>
																<td style="text-align: center; width: 10%;">
																	<ivu:int key="Neue_Stimmbezirke_Loeschen"/>
																</td>
															<% } %>
														</tr>
										        	  <% 
										        	  first = false;
						        					}%>
													<tr class='<%=i < 1 ? "hgeeeeee" : "hgweiss"%>'>
														<td>
															<input id="id_<%= rowId %>_0" type="text" size="10" name="<%= prefix+stimmbezirk.getID_Gebiet() %>_nr" value="<%= stimmbezirk.getNummer() %>" onkeydown="return navigateCursorThroughTable('id_<%= rowId %>_0', event, true)" />
														</td>
														<td>
															<input id="id_<%= rowId %>_1" type="text" size="50" name="<%= prefix+stimmbezirk.getID_Gebiet() %>_name" value="<%=ClientHelper.forHTML(stimmbezirk.getName()) %>" onkeydown="return navigateCursorThroughTable('id_<%= rowId %>_1', event, true)" />
														</td>
														<td>
															<input id="id_<%= rowId %>_2" type="text" size="12" name="<%= prefix+stimmbezirk.getID_Gebiet() %>_zipcode" value="<%=ClientHelper.forHTML(stimmbezirk.getZipcode()) %>" onkeydown="return navigateCursorThroughTable('id_<%= rowId %>_2', event, true)" />
														</td>
														<td style="text-align: right;">
															<input id="id_<%= rowId %>_3" type="text" size="10" name="<%= prefix+stimmbezirk.getID_Gebiet() %>_wahlberechtigte" value="<%=stimmbezirk.getWahlberechtigte() %>" style="text-align: right" onkeydown="return navigateCursorThroughTable('id_<%= rowId %>_3', event, true)" onkeyup="return sumEligibleVotes()" />
														</td>
														<% int colId = 4;
														   if (postalVoteOfficeSelectable) { %>
															<td style="text-align: center">
																<input id="id_<%= rowId %>_<%= colId %>" type="checkbox" name="<%= prefix+stimmbezirk.getID_Gebiet() %>_postalvote" value="<%= stimmbezirk.getID_Gebiet() %>" <%= stimmbezirk.isPostalvote() ? "checked=\"checked\" " : "" %> onkeydown="return navigateCursorThroughTable('id_<%= rowId %>_<%= colId %>', event, false)" />
															</td>
														<%  colId++;
														   }
														   if (newSBs) {
													    %>
															<td style="text-align: center">
																<input id="id_<%= rowId %>_<%= colId %>" type="checkbox" name="<%= prefix+stimmbezirk.getID_Gebiet() %>Del" value="<%= stimmbezirk.getID_Gebiet() %>"  onkeydown="return navigateCursorThroughTable('id_<%= rowId %>_<%= colId %>', event, false)" />
															</td>
														<%  colId++;
														   }
														%>														
													</tr><%
													i = -i;
													rowId++;
												}%>
												<tr class='<%=i < 1 ? "hgeeeeee" : "hgweiss"%>'>
														<td colspan="3" style="text-align: right;">
															<ivu:int key="Neue_Stimmbezirke_Summe_Wahlberechtigte"></ivu:int>
															<noscript>(wordt berekend bij opslaan)</noscript>
														</td>
														<td id="id_sum" align="right">
															<%=summeWahlberechtigte %>
														</td>
														<% if (postalVoteOfficeSelectable) { %>
															<td>
																&nbsp;
															</td>
														<% } 
														   if (newSBs) {
													    %>
															<td>
																&nbsp;
															</td>
														<% } %>
													</tr>
							            </table>
		                        </td>
		                        <td width="5">&nbsp;</td>
		                     </tr>
		                     <tr>
		                        <td height="5" colspan="4"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
		                     </tr>
		                  </table>
		               </td>
		            </tr>
		            <tr>
		               <td height="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
		            </tr>
		         </table>
	         <% } %>
	         <% if (newSBs) {
		         	if (!stimmbezirke.isEmpty()) { %>	
						<div style="padding-right: 1em; text-align: right; margin-top: 0px;" >
							<input id="box2" type="submit" name="<%= prefix%>los" value="<%= BundleHelper.getBundleString("StimmbuerosAbsenden") %>"/>
						</div>
					<% } %>
					<div style="padding: 0em 0.5em 0.5em;">
					<% if (stimmbezirke.isEmpty()) { %>	
						<ivu:int key="Stimmbezirke_weitere_erzeugen"></ivu:int>
					<% } else { %>
						<ivu:int key="Stimmbezirke_weitere_erzeugen_weitere"></ivu:int>
					<% } %>
					</div>
					<div style="padding-left: 1.5em; width=100%;">
						<input type="text" name="<%= prefix%>anzahlNeueGebiete" size="10"/>&nbsp;&nbsp;&nbsp;<input id="box2" type="submit" name="<%= prefix%>los" value="<%= BundleHelper.getBundleString("Stimmbezirke_Speichern_button") %>"/>
					</div>
				   <div style="padding: 5em 0.5em 0.5em; text-align: center;">
						<ivu:int key="Stimmbezirke_abschließen"></ivu:int>
					</div>
			<% } %>
			<% if (!stimmbezirke.isEmpty()) { %>	
					<div style="padding: 1em; text-align: center;" >
						<input id="box2" type="submit" name="<%= prefix%>readyWithEdit" value="<%= finalizeButtonLabel %>"/>
					</div>
			<% } %>
			</ivu:form>
	  	<% } %>
    </fieldset>
  </div>
<% } %>
		</div>
	</div>
</body>
</html>
