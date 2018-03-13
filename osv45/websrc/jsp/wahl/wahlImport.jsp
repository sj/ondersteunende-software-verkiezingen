<jsp:directive.page import="de.ivu.wahl.client.util.ClientHelper" />
<jsp:directive.page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" />
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<%@ page import="de.ivu.wahl.modell.Gebietsart"%>
<%@ page import="de.ivu.wahl.dataimport.AbstractImportEML"%>
<%@ page import="de.ivu.wahl.dataimport.HashCodeSplitter"%>
<%@ page import="de.ivu.wahl.dataimport.IImportEML"%>
<%@ page import="de.ivu.wahl.dataimport.ImportType"%>
<%@ page import="de.ivu.wahl.dataimport.SecurityLevel"%>
<%@ page import="de.ivu.wahl.client.beans.Command"%>
<%@ page import="de.ivu.wahl.client.beans.WahlImportBean"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.client.beans.InitGuiCommand"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.modell.ejb.Wahl"%>
<%@ page import="de.ivu.wahl.modell.ejb.WahlBean"%>

<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="impBean" scope="session" class="de.ivu.wahl.client.beans.WahlImportBean" />
<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "wahlImp";
%>
<html>
 <head>
  <title><ivu:int key="Neue_Wahl_importieren_titel"/></title>
	<script>
		var contextPath = "<%=request.getContextPath()%>";
	</script>
	<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/osv.js"></script>
	<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
  <%
    response.setDateHeader("Last-Modified", -1);
    response.setDateHeader ("Expires", 0); // prevents caching at the proxy server
    response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
    response.setHeader("Pragma","no-cache"); // HTTP 1.0
    
    
    IImportEML impDef = impBean.getImportMetadata();
    SystemInfo systemInfo =  SystemInfo.getSystemInfo();
    WahlInfo wahlInfo = appBean.getWahlInfo();

	if (impDef != null && ImportType.EML630.equals(impDef.getImportType())) {
		helpKey = "refImp";
	}
 %>
   <style type="text/css">
     td {line-height: 15px}
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
  <div class="hgeeeeee" style="height: 14px; width: 100%;" align="right" style="background-color: <%=backgroundColor%>;">
    <ivu:help key="<%=helpKey%>"/>
</div>

  <div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
  	&nbsp;
  </div>
  <div class="hggrau" style="height: 20px; width: 100%; text-indent: 5px; line-height: 18px;">
   <ivu:int key="Neue_Wahl_importieren_titel"/>
  </div>
  <div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
   &nbsp;
  </div>
  <div class="hghell">
   <fieldset style="border: 1px solid #093C69; padding: 15px; margin: 15px;">
    <legend>
     <b><ivu:int key="Neue_Wahl_importieren"/></b>
    </legend>
    <%
    	String urlWahl = "/osv?"
			+ "cmd=imp_import_Wahl&"
			+ "ts=" + System.currentTimeMillis() + "&"
 			+ ClientHelper.getParametersDoNotStartWith(request, "ts", true);
 			
 	    String urlRef = "/osv?"
			+ "cmd=imp_import_Referendum&"
			+ "ts=" + System.currentTimeMillis() + "&"
 			+ ClientHelper.getParametersDoNotStartWith(request, "ts", true);
 		
 		String url = urlWahl;
 		if (impDef.getImportType() != null) {
	 		switch (impDef.getImportType()) {
	 			case EML110A_AND_EML230:
	 				url = urlWahl;
	 				break;
	 			case EML630:
	 				url = urlRef;
	 				break;
	 			default:
	 				throw new RuntimeException("unexpected metadata import type");
	 		}
 		}
    %>
    <% if (null != impDef.getFehlermeldung()) { %>
	     <div style="padding: 0.5em; color: red;">
	      <%= impDef.getFehlermeldung() %>
	     </div>
     <% } %>
     <% if (AbstractImportEML.STATUS_INIT == impDef.getStatus() && appBean.getWahlen().size() == 0) { %>
		<ivu:form action="<%=urlWahl%>" enctype="multipart/form-data" method="post" onsubmit="transp();">
	     <div style="padding: 0.5em;">
	      <ivu:int key="Neue_Wahl_importieren_METADATEN_info"/>
	     </div>
	     <div style="padding: 0.5em; width=100%;">
	      <input type="file" name="<%=WahlImportBean.FELD_WAHLDEFINITION%>" size="100" style="font-size:11px; width: 100%;" accept="*">
	     </div>
	     <div style="padding: 0.5em;">
	      <%= BundleHelper.getBundleString("Neue_Wahl_importieren_EML230_info_"+systemInfo.getEbenenklartext()+"_"+systemInfo.getModusklartext()) %>
	     </div>
	     <div style="padding: 0.5em; width=100%;">
	      <input type="file" name="<%=WahlImportBean.FELD_EML230 %>" size="100" style="font-size:11px; width: 100%;" accept="*">
	     </div>
	     <div style="padding: 0.5em; text-align: center;">
	      <input id="box2" style="cursor:pointer" type="submit"
	       name="<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>" value="<%=BundleHelper.getBundleString("Neue_Wahl_importieren_button") %>">
	     </div>
	   </ivu:form>
		   <% if (systemInfo.getWahlModus() == AbstractImportEML.MODE_DB_P4) { %>
			   <hr/>
			   <ivu:form action="<%=urlRef%>" enctype="multipart/form-data" method="post" onsubmit="transp();">
			     <div style="padding: 0.5em;">
			      <%= BundleHelper.getBundleString("Neues_Referendum_importieren_EML630_info") %>
			     </div>
			     <div style="padding: 0.5em; width=100%;">
			      <input type="file" name="<%=WahlImportBean.FELD_EML630%>" size="100" style="font-size:11px; width: 100%;" accept="*">
			     </div>
			     <div style="padding: 0.5em; text-align: center;">
			      <input id="box2" style="cursor:pointer" type="submit"
			       name="<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>" value="<%=BundleHelper.getBundleString("Neues_Referendum_importieren_button") %>">
			     </div>
			   </ivu:form>
		   <% } %>
	   
       <% } else if (AbstractImportEML.STATUS_URL_KOMPLETT == impDef.getStatus()) { %>
      	
      	<ivu:form name="wahlImport" action="<%=url%>" onsubmit="transp();">
	   		<div style="padding: 0.5em; padding-bottom: 2.5em;">
				<br/>
				<%
				String hashCode = impDef.getHashWert230();
				String breite ="100%";
				%>
		   		<table border="0" cellspacing="0" cellpadding="1" width="99%">
		            <tr class="hgrot">
		               <td valign="top">
		                  <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
		                     <tr class="hgeeeeee">
		                        <td width="5" height="18">&nbsp;</td>
		                        <td><b><ivu:int key="ElectionDetails_headline" /></b></td>
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
		                           <table border="0" width="<%= breite %>" cellspacing="2" cellpadding="4" align="center">
				                     <%
				                     	WahlModel electionDetails = impDef.getElectionDetails();
				                     	int j = -1;
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="ElectionID" />:</b></td>
										 <td><%= electionDetails.getID_Wahl() %></td>
									 </tr>
				                     <%
				                     	j = -j;
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="ElectionName" />:</b></td>
										 <td><%= electionDetails.getName() %></td>
									 </tr>
				                     <%
				                     	j = -j;
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="ElectionCategory" />:</b></td>
										 <td><%= electionDetails.getWahlkategorie() %></td>
									 </tr>
				                     <%
				                     	j = -j;
				                     	String electionDomain = electionDetails.getElectionDomain();
				                     	if (electionDomain != null && electionDomain.length() > 0) {
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="<%= "ElectionDomain" + electionDetails.getWahlkategorie().substring(0, 2) %>" />:</b></td>
										 <td><%= electionDomain %></td>
									 </tr>
				                     <%
				                     		j = -j;
				                     	}
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="ElectionDate" />:</b></td>
										 <td><%= electionDetails.getTermin().toString().substring(0, 10) %></td>
									 </tr>
				                     <%
				                     	j = -j;
				                     	if (electionDetails.getDatumNominierung() != null) {
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="NominationDate" />:</b></td>
										 <td><%= electionDetails.getDatumNominierung().toString().substring(0, 10) %></td>
									 </tr>
				                     <%
				                     		j = -j;
				                     	}
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="NumberOfSeats" />:</b></td>
										 <td><%= electionDetails.getAnzahlSitze() %></td>
									 </tr>
				                     <%
				                     	j = -j;
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="PreferenceThreshold" />:</b></td>
										 <td><%= electionDetails.getVorrangschwelle() %></td>
									 </tr>
				                     <%
				                     	j = -j;
				                     %>
									 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
										 <td><b><ivu:int key="ElectionDefinitionCreationDateTime" />:</b></td>
										 <td><%= electionDetails.getStandMetadaten().toString().substring(0, 19) %></td>
									 </tr>
		                           </table>
		                        </td>
		                        <td width="5">&nbsp;</td>
		                     </tr>
		                     <tr>
		                        <td height="5" colspan="5"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
		                     </tr>
		                  </table>
		               </td>
		            </tr>
		            <tr>
		               <td height="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
		            </tr>
		         </table>
				<%= BundleHelper.getBundleString("Neue_Wahl_ElectionDefinition_Confirm") %>
				<input type="hidden" size="2" maxlength="2" name="<%=WahlImportBean.FELD_ACCEPT_ELECTIONDEFINITION %>" value="0" />
			</div>
			<div style="padding: 0.5em; text-align: center;">
		      <input id="box2" style="cursor:pointer" type="submit" name="<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>" value="<%= BundleHelper.getBundleString("Neue_Wahl_importieren_confirm_button") %>">
		      <input id="box2" style="cursor:pointer" type="submit" name="<%=WahlImportBean.FELD_RESET%>" value="<%= BundleHelper.getBundleString("Neue_Wahl_importieren_confirm_reset_button") %>">
			</div>
		 </ivu:form>

       <% } else if (AbstractImportEML.STATUS_ELECTIONDEFINITION_ACCEPTED == impDef.getStatus()) { %>

      	<ivu:form name="wahlImport" action="<%=url%>" onsubmit="transp();">
	   		<div style="padding: 0.5em; padding-bottom: 2.5em;">
				<br/>
				<% if (SecurityLevel.CONFIRM_HASH_CODE.equals(impDef.getSecurityLevel())) {
					String hashCode = impDef.getHashWert230(); %>
			    	<!-- Only confirmation of complete hashcode, no user entry: Show complete hashcode, add hidden field with the (unneccessary) user input --> 
					<%= BundleHelper.getBundleString("Neue_Wahl_HashWert_Confirm_230_" + systemInfo.getEbenenklartext() + "_" + systemInfo.getModusklartext()) %><br/><br/>
					<%= hashCode %>
					<input type="hidden" size="4" maxlength="4" name="<%=WahlImportBean.FELD_HASHCODE_230_INPUT_0 %>" value="<%= HashCodeSplitter.HIDDEN_INPUT %>" />&nbsp;
					<input type="hidden" size="4" maxlength="4" name="<%=WahlImportBean.FELD_HASHCODE_230_INPUT_1 %>" value="<%= HashCodeSplitter.HIDDEN_INPUT %>" />&nbsp;
				<% } else { %>
				    <!-- Input of parts of the hashcode is required: Show parts of hashcode, add field for the user input --> 
					<%= BundleHelper.getBundleString("Neue_Wahl_HashWert_230_" + systemInfo.getEbenenklartext() + "_" + systemInfo.getModusklartext()) %><br/><br/>
					<%= impDef.getHashWert230ToConfirm(0) %>
					<input type="text" size="4" maxlength="4" name="<%=WahlImportBean.FELD_HASHCODE_230_INPUT_0 %>" onkeyup="this.value=this.value.toUpperCase(); if (this.value.length >= 4) document.wahlImport.<%=WahlImportBean.FELD_HASHCODE_230_INPUT_1 %>.focus();" autocomplete="off" />&nbsp;
					<%= impDef.getHashWert230ToConfirm(1) %>
					<input type="text" size="4" maxlength="4" name="<%=WahlImportBean.FELD_HASHCODE_230_INPUT_1 %>" onkeyup="this.value=this.value.toUpperCase(); if (this.value.length >= 4) document.wahlImport.<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>.focus();" autocomplete="off" />&nbsp;
					<%= impDef.getHashWert230ToConfirm(2) %>
				<% } %>
			</div>
			<div style="padding: 0.5em; text-align: center;">
		      <input id="box2" style="cursor:pointer" type="submit" name="<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>" value="<%= BundleHelper.getBundleString("Hashcode_bestaetigen_button") %>">
		      <input id="box2" style="cursor:pointer" type="submit" name="<%=WahlImportBean.FELD_RESET%>"                        value="<%= BundleHelper.getBundleString("Hashcode_ablehnen_button") %>">
			</div>
		 </ivu:form>
		 
	<% } else if (AbstractImportEML.STATUS_HASH_CHECKED == impDef.getStatus()) { %>
       <ivu:form name="wahlImport" action="<%=url%>" onsubmit="transp();">
	   	<div style="padding: 0.5em;">
	      	<ivu:int key="Neue_Wahl_Gebietsnummer_Label"/>:
	      	<select name="<%=WahlImportBean.FELD_GEBIETSNUMMER %>" style="font-size:11px; width: 300; left: 200px; position: absolute;">
					<% for (GebietModel gebiet: impDef.getGebietsauswahl()) { %>
						<option value="<%=gebiet.getNummer()%>"><%= gebiet.getName() %> (<%=Gebietsart.getGebietsartKlartext(gebiet) %>)</option>
              	<% }%>
				</select>
      	</div>
			<div style="padding: 0.5em; text-align: center;">
	      	<input id="box2" style="cursor:pointer" type="submit" name="<%=ApplicationBeanKonstanten.PREFIX + "importieren"%>" value="<%=BundleHelper.getBundleString("Neue_Wahl_importieren_button") %>">
	      	<input id="box2" style="cursor:pointer" type="submit" name="<%=WahlImportBean.FELD_RESET%>" value="<%=BundleHelper.getBundleString("Neue_Wahl_importieren_reset_button") %>">
			</div>
		</ivu:form>
     <% } else if (AbstractImportEML.STATUS_KOMPLETT == impDef.getStatus() || appBean.getWahlen().size() > 0) { %>
       	<div style="padding: 0.5em;">
       		<% if (ImportType.EML630.equals(impDef.getImportType()) || wahlInfo.isReferendum()) { %>
	      		<ivu:int key="ReferendumimportErfolgreich"/>
       		<% } else { %>
	      		<ivu:int key="WahlimportErfolgreich"/>
	      	<% } %>
	      	<%if (AbstractImportEML.MODE_DB_P4 == systemInfo.getWahlModus() && 
	      	    GebietModel.EBENE_PSB == systemInfo.getWahlEbene() ){
	      	    String newLocation =  ClientHelper.getSuffixLevel(request, ApplicationBeanKonstanten.LEVEL_ADMIN)+
                "&" + ApplicationBeanKonstanten.WORKIS + Command.ADM_STIMMBEZIRKE.getId();
	      	    %>
					<br/><br><ivu:int key="GeheZuStimmbezirksadministrationInfo"></ivu:int><br/><br/>
					<ivu:a id="box2a" href="<%="/osv?"+newLocation %>"><ivu:int key="GeheZuStimmbezirksadministration"></ivu:int></ivu:a>
				<% } else { %>
		      	<ivu:form name="logout" action="/osv?cmd=app_logout">
			      	<br/>
	   	        	<b><ivu:int key="Abmelden_von"/> <%=appBean.getAnwContext().getAnmeldename()%></b>
	      	   	<div style="padding: 1em; text-align: center">
	               	<input id="box2" style="cursor:pointer" type="submit" value="<%=BundleHelper.getBundleString("Abmelden_jetzt") %>" name="logout">
	             	</div>
					</ivu:form>
				<% } %>
      	</div>
     <% } %>
   </fieldset>
  </div>
  		</div>
	</div>
</body>
</html>