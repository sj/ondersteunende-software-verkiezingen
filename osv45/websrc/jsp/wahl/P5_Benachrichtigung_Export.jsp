<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ page import="de.ivu.wahl.Basiseinstellung"%>
<%@ page import="de.ivu.wahl.Basiseinstellung.Typ"%>
<%@ page import="de.ivu.wahl.BasiseinstellungMultiMap"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.admin.DialogStateHolder"%>
<%@ page import="de.ivu.wahl.admin.P5ExportStateKanBen"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.AdministrationBean"%>
<%@ page import="de.ivu.wahl.client.beans.Command" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.client.util.GUICommand"%>
<%@ page import="de.ivu.wahl.export.XMLTags"%>
<%@ page import="de.ivu.wahl.modell.GruppeModel"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.wus.electioncategory.ElectionCategory"%>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%  
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "BenachrichigungExport"; //$NON-NLS-1$

String breite = "100%";
String prefix = ApplicationBeanKonstanten.PREFIX;
int subwork = ClientHelper.getIntParameter(request.getParameter(prefix + "subwork"), 0); //$NON-NLS-1$
DialogStateHolder p5ESKanBen = admBean.getP5ExportStateKanBen();
BasiseinstellungMultiMap bmm = Konstanten.PROP_BENACHRICHTIGUNG_GEWAEHLTE;
WahlInfo wahlInfo = WahlInfo.getWahlInfo();
boolean legende = false;
%>
<html>
<head>
   <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
   <title>P22_1</title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery.autocomplete.css">
   <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.3.2.js"></script>
   <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.autocomplete.js"></script>
   <script type="text/javascript" src="<%= request.getContextPath() %>/js/gbadata.js"></script>
   <script type="text/javascript">
		  function doit(i, j, msec)
			{
			  //alert("doit");
			  var k = i;
			  var l = j;
			  if (k <= 50)
			  {
				 //alert("doit");
			    eval("document.p" + k + ".src = '<%= request.getContextPath() %>/img/lila.gif'");
			    k++;
			    window.setTimeout("doit(" + k + "," + l + "," + msec + ")", msec);
			  } else {
				 eval("document.p" + l + ".src = '<%= request.getContextPath() %>/img/leer.gif'");
			    if (k == 101) {
					k = -1;
					l = -1;
			  	 }
			    k++;
			  	 l++;
			  	 window.setTimeout("doit(" + k + "," + l + "," + msec + ")", msec);
			  } 
			}
			
			function transp() {
	   		var divTag  = document.getElementById("content");
				divTag.style.filter = "alpha(Opacity=60, FinishOpacity=0, Style=0, StartX=0, StartY=0, FinishX=1000, FinishY=1000)";
				
				var divTag  = document.getElementById("trans");
				divTag.className = "trans";
				
				var statusbalken  = document.getElementById("statusbalken");
				statusbalken.style.display = 'inline';
				doit(0,0,100);       	
		
			}
			
			// GBA support
			$(document).ready(function(){
				$("#<%= ApplicationBeanKonstanten.PREFIX + XMLTags.RG_CHAIRPERSON %>").autocomplete(gbaData);
				$("#<%= ApplicationBeanKonstanten.PREFIX + XMLTags.RG_ACCEPTANCE_ADDRESS %>").autocomplete(gbaData);
				$("#<%= ApplicationBeanKonstanten.PREFIX + XMLTags.RG_ACCEPTANCE_LOCATION %>").autocomplete(gbaData);
				$("#<%= ApplicationBeanKonstanten.PREFIX + XMLTags.RG_REJECTION_ADDRESS %>").autocomplete(gbaData);
				$("#<%= ApplicationBeanKonstanten.PREFIX + XMLTags.RG_REJECTION_LOCATION %>").autocomplete(gbaData);
			});
		  	
   </script>
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
							<ivu:int key="ExportInWenigenSekunden"/>
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
			<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
				<%@include file="/jsp/fragments/help_row.jspf"%>
			   <tr>
			      <td valign="top">
			         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
			            <tr>
			               <td width="5" class="hggrau">&nbsp;</td>
			               <td colspan="2" class="hggrau" height="20"><ivu:int key="Export_Gew_Ben_titel"/></td>
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
			                        <%String exportVerz = admBean.getProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR); 
			                        if (exportVerz == null || exportVerz.isEmpty()){ %>
			                     		<fieldset style="border: 1px solid #093C69; padding: 15px">
			                          		<legend><b><ivu:int key="Export_Verzeichnis_titel"/></b></legend><br />
			                              <ivu:int key="KeinZielverzeichnisAngegeben"></ivu:int>
			                        	</fieldset>
			                      	<% } else if (wahlInfo.getStatus() != WahlModel.STATE_CALCULATION_SUCCESSFUL){ %>
			                      	  	<table width="100%" cellspacing="0" cellpadding="1" border="0">
													<tbody>
														<tr class="hgrot">
															<td valign="top">
								                     	<table class="hgweiss" border="0" cellpadding="0" cellspacing="0" width=100%">
											               	<tbody>
											                   	<tr class="hgeeeeee">
																			<td height="20" width="5">
																				&nbsp;
																			</td>
																			<td>
																				<br/>
																				<b><ivu:int key="Export_Nicht_Moeglich"/></b>
																				<br/><br/>
																				<ivu:int key="Sitzverteilung_Nicht_erfolgt_info"/>
																				<br/><br/>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>
													</tbody>
												</table>  
			                      	<% } else {
			                      	   legende = true;
				                        if (!wahlInfo.isFreigegeben()){ %>
			                           	<table width="100%" cellspacing="0" cellpadding="1" border="0">
														<tbody>
															<tr class="hgrot">
																<td valign="top">
									                     	<table class="hgweiss" border="0" cellpadding="0" cellspacing="0" width=100%">
												               	<tbody>
												                   	<tr class="hgeeeeee">
																				<td height="20" width="5">
																					&nbsp;
																				</td>
																				<td>
																					<br/>
																					<b><ivu:int key="Freigabe_nicht_erfolgt_Konzeptausdruck"/></b>
																					<br/><br/>
																					<ivu:int key="Freigabe_nicht_erfolgt_Konzeptausdruck_info"/>
																					<br/><br/>
																				</td>
																				<td width="5">
																					&nbsp;
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</td>
															</tr>
														</tbody>
													</table>
													<br/>
													<br/>
													<br/>
						                  <% }	
				                        if (P5ExportStateKanBen.STATUS_KanBen_D1 == p5ESKanBen._modus){  %>
												 <fieldset style="border: 1px solid #093C69; padding: 15px">
												 <legend><b><ivu:int key="Export_Gew_Ben"/></b></legend>
														<p><ivu:int key="Export_Gew_Ben_titel"/></p>
														<%
														  if (admBean._adminMsgProps != null && !admBean._adminMsgProps.isEmpty() ){
														%>
															<p style="color:red;"><b><%=admBean._adminMsgProps%></b></p>
															
														<% 
															admBean._adminMsgProps = null;
														  }
														  if (admBean._adminMsgPropsConfirmation != null && !admBean._adminMsgPropsConfirmation.isEmpty() ){
														%>
															<p style="color:green;"><b><%=admBean._adminMsgPropsConfirmation%></b></p>
															
														<% 
															admBean._adminMsgPropsConfirmation = null;
														  }
														  if (admBean._adminMsgExport != null && !admBean._adminMsgExport.isEmpty() ){
														%>
															<p style="color:red;"><b><%=admBean._adminMsgExport%></b></p>
														<% admBean._adminMsgExport = null;
														  }
														  if (admBean._adminMsgExportConfirmation != null && !admBean._adminMsgExportConfirmation.isEmpty() ){
														%>
															<p style="color:green;"><b><%=admBean._adminMsgExportConfirmation%></b></p>
														<% admBean._adminMsgExportConfirmation = null;
														  }
														%>
														<br/>
														<br/>
													<table border="0" cellspacing="0" cellpadding="0" width="<%= breite %>">
													   <tr>
													    <td height="20" valign="top" class="guiBefehle"><%
													     int buttonIdx = 0;
													     String selectedKey = null;
													     for (String buttonName : bmm.keySet()) {
													       boolean selected = buttonIdx++ == subwork;
													       if (selected) {
													        selectedKey = buttonName;
													       }
													       String styleClass =  GUICommand.GUI_CLASS_1; %> 
													       <span class='<%=  styleClass%>' ><%=buttonName%></span><%
													      }%>
													      </td>
													     </tr><%
													     if (selectedKey != null) {
													       
													       String formurl = "/osv?cmd=adm_propEingabeGewBen&" + ApplicationBeanKonstanten.PREFIX + "subwork=" + subwork 
													       + "&"+ClientHelper.getAllParameters(request, true); %>
													      <tr>
													       <td style="border: 1px solid rgb(9, 60, 105); padding-left: 1em width: 100%;">
													      <ivu:form action="<%= formurl %>">
													        <table border="0" cellspacing="4" cellpadding="1" width="<%= breite %>" style="padding: 1em;">
													          <colgroup width="50%" span="2"></colgroup><%
													         List<Basiseinstellung> basiseinstellungen = bmm.get(selectedKey);
													         for (Basiseinstellung basiseinstellung : basiseinstellungen) {
													          boolean restart = basiseinstellung.isRestart();
													          String styleClass = restart ? "frot" : "fgrau";
													          String styleClassChb = restart ? "checkbox_rot" : "checkbox"; 
													          String styleChb = restart ? "border: 1px solid #D00;" : "";%>
													          <tr title="<%= basiseinstellung.getHinweis() %>">
													           <td><%= basiseinstellung.getBeschreibung() %><%= basiseinstellung.isPflichtfeld() ? " *": XMLTags.RG_ACCEPTANCE_ADDRESS.equals(basiseinstellung.getProperty()) || XMLTags.RG_ACCEPTANCE_POSTALCODE.equals(basiseinstellung.getProperty()) || XMLTags.RG_ACCEPTANCE_LOCATION.equals(basiseinstellung.getProperty()) ? " **" : "" %></td>
													           <td>
													           <%
													            String property = basiseinstellung.getProperty();
													            String wert = ClientHelper.forHTML(admBean.getProperty(property));
													            session.setAttribute(property, wert);
													            property = ApplicationBeanKonstanten.PREFIX + property;
													            switch (basiseinstellung.getTyp()) {
													             case RelURL:
													              // Es können nicht gesetzte Werte als null kommen!
													              if (wert == null) {
													               wert = "";
													              }%>
													              <table border="0" cellspacing="0" cellpadding="0" width="100%" style="padding: 0px;">
													               <tr>
													                <td><input type="text" readonly="readonly" class="feld <%=styleClass%> lr" value="${admBean.baseURI}/"></td>
													                <td><input type="text" class="feld <%=styleClass%> r" style="border-left-width: 0px;" size="40" name="<%=property%>" value="<%=wert%>"></td>
													               </tr>
													              </table><%
													              break;
													              
													             case String:
													            // Es können nicht gesetzte Werte als null kommen!
														              if (wert == null) {
														               wert = "";
														              }%>
														              <input type="text" class="feld <%=styleClass%>" size="64" id="<%=property%>" name="<%=property%>" value="<%=wert%>"><%
														              break;
													             case Date:
													             case Time:
													             case ZIP:
													              // Es können nicht gesetzte Werte als null kommen!
													              if (wert == null) {
													               wert = "";
													              }%>
													              <input type="text" class="feld <%=styleClass%>" size="64" id="<%=property%>" name="<%=property%>" value="<%=wert%>"><%
													              break;
													              
													             case Integer:
													              // Es können nicht gesetzte Werte als null kommen!
													              if (wert == null) {
													                 wert = "0";
													              }%>
													              <input type="text" class="feld <%=styleClass%>" size="64" name="<%=property%>" value="<%=wert%>"><%
													              break;
													             case Textarea:
				                                         // Es können nicht gesetzte Werte als null kommen!
				                                         if (wert == null) {
				                                          wert = "";
				                                         }%>
				                                         <textarea class="feld <%=styleClass%>" cols="64" rows="10" id="<%=property%>" name="<%=property%>"><%=wert%></textarea><%
				                                         break;  
													             case Boolean:
													              boolean bWert = "true".equals(wert); %>
													              <div class="<%=styleClassChb%>">
													               <input type="checkbox" style="<%=styleChb %>" <%=bWert ? "checked" : ""%> id="<%=property%>" name="<%=property%>" value="x" />
													              </div><%
													              break;
													              
													             default: %>
													              <%= BundleHelper.getBundleString("FormatUnbekannt")%><%
													            } %>
													           </td>
													          </tr><%
													         } %>
									                   </table>
											             <div style="margin-left: 1em; margin-top: 1em; margin-bottom: 1em;">
							                         	<input id="box2" type="submit" value="<%=AdministrationBean.WEITER %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
															 </div>
												        </ivu:form>
												       </td>
												      </tr><%
												     } %>
												    </table>
												 </fieldset>
			                       		<% } else if (P5ExportStateKanBen.STATUS_KanBen_D2 == p5ESKanBen._modus){ 
			                       			String urlExp = "/osv?cmd=adm_exportKanBen&" +ClientHelper.getAllParameters (request);
												   %>
							                  <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
							                     <tr>
							                        <td valign="top">
						         	                  <fieldset style="border: 1px solid #093C69; padding: 15px">
							                           	<legend><b><ivu:int key="Export_Zusammenfassung"/></b></legend>
							                            	<% if (admBean._adminMsgExport != null && !admBean._adminMsgExport.isEmpty() ){%>
																		<p style="color:red;"><b><%= admBean._adminMsgExport %></b></p>
																	<% admBean._adminMsgExport = null; 
															   }
							                            	   if (admBean._adminMsgExportConfirmation != null && !admBean._adminMsgExportConfirmation.isEmpty() ){%>
																		<p style="color:green;"><b><%= admBean._adminMsgExportConfirmation %></b></p>
																	<% admBean._adminMsgExportConfirmation = null; 
																} %>
																	<br/>
								                           <ivu:form action="<%=urlExp%>" onsubmit="transp();">
								                           	<table cellspacing="4" cellpadding="2" border="0">
						                                       <tr>
							                                       <td colspan="2"><b><ivu:int key="Export_Gew_Ben"/></b></td>
							                                    </tr>
							                                    
							                                    <%	for (Basiseinstellung basiseinstellung : bmm.getBasis()) { 
								                            		   String property = basiseinstellung.getProperty();
						                                    		   String wert = admBean.getProperty(property); %>
						                                    		 <tr title="<%= basiseinstellung.getHinweis() %>">
										                                   <td><%= basiseinstellung.getBeschreibung() %><%= basiseinstellung.isPflichtfeld() ? " *": XMLTags.RG_ACCEPTANCE_ADDRESS.equals(basiseinstellung.getProperty()) || XMLTags.RG_ACCEPTANCE_POSTALCODE.equals(basiseinstellung.getProperty()) || XMLTags.RG_ACCEPTANCE_LOCATION.equals(basiseinstellung.getProperty()) ? " **" : "" %></td>
										                                   <td><% if (basiseinstellung.getTyp() == Typ.Textarea){ %>
									                                   			<textarea rows="5" cols="64" disabled="disabled"><%= ClientHelper.forHTML(ClientHelper.getEmptyStringIfBlank(wert, "-")) %></textarea>
									                                   		<% } else { %>
									                                   			<%= ClientHelper.forHTML(ClientHelper.getEmptyStringIfBlank(wert, "-")) %>
									                                   		<% } %>
												                             </td>
									                                  </tr>
										                          	<% } %>
					                                    		 <tr>
							                                       <td>&nbsp;</td>
							                                       <td>&nbsp;</td>
							                                    </tr>
							                                    <tr>
							                                       <td>&nbsp;</td>
							                                       <td>&nbsp;</td>
							                                    </tr>
							                                 </table>
							                                 <div style="margin-left: 1em; margin-top: 1em; margin-bottom: 1em;">
																			<input id="box2" type="submit" value="<%=AdministrationBean.ZURUECK %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
										                       		<% if (admBean._adminWarningOverride != null && !admBean._adminWarningOverride.isEmpty() ){%>
																				<p class="warningMessage"><b><%= admBean._adminWarningOverride %><br/>
																				<ivu:int key="Datei_Ueberschreiben"/> </b></p>
																				<% admBean._adminWarningOverride = null; %> 
																				<input type="hidden" value="1" name="<%=ApplicationBeanKonstanten.PREFIX%>force"/>
																				<input id="box2" type="submit" value="<%=AdministrationBean.RESET %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
																				<input id="box2" type="submit" value="<%=AdministrationBean.WEITER %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
									                            	<% } else { %>
												                         <input id="box2" type="submit" value="<%=AdministrationBean.WEITER %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
										                         	<% } %>
																		</div>
																	</ivu:form>
							                           </fieldset>
							                        </td>
							                     </tr>
							                  </table>
			                          <% }  else if (P5ExportStateKanBen.STATUS_KanBen_D3 == p5ESKanBen._modus){
											// reset export status
											admBean.resetExportStateKanBen();
											//forward to Werkmap
											String urlExp = "/osv?cmd=adm_exportKanBen&"+ClientHelper.workIs(Command.EXPORT_VERZEICHNIS)+"&" +ClientHelper.getAllParameters(request, ApplicationBeanKonstanten.WORK);
				                       		%>
				                       		<fieldset style="border: 1px solid #093C69; padding: 15px">
												 		<legend><b><ivu:int key="Export_Gew_Ben"/></b></legend>
														<p><ivu:int key="Export_erfolgreich"/></p>
														<%
														  if (admBean._adminMsgExport != null && !admBean._adminMsgExport.isEmpty() ){
														%>
															<p style="color:red;"><b><%=admBean._adminMsgExport%></b></p>
															<% admBean._adminMsgExport = null;
														  
														  }
														  if (admBean._adminMsgExportConfirmation != null && !admBean._adminMsgExportConfirmation.isEmpty() ){
														%>
															<p style="color:green;"><b><%=admBean._adminMsgExportConfirmation%></b></p>
															<% admBean._adminMsgExportConfirmation = null;
														  
															   if (!wahlInfo.getElectionCategory().equals(ElectionCategory.TK) && !wahlInfo.getElectionCategory().equals(ElectionCategory.EP) && !wahlInfo.getElectionCategory().equals(ElectionCategory.EK) && !ElectionCategory.BC.equals(wahlInfo.getElectionCategory()) && !ElectionCategory.GC.equals(wahlInfo.getElectionCategory()) && wahlInfo.isFreigegeben()) {
															%>
																<div><ivu:int key="Export_FinalMessage_I"/>
																	<ol>
																		<li><%= wahlInfo.getEML110aFilename() %></li>
																		<li><%= wahlInfo.getEML230bFilename() %> <ivu:int key="Export_FinalMessage_FromP3"/></li>
																		<li><%= wahlInfo.getEML510Filename(true) %> <ivu:int key="Export_FinalMessage_FromP4"/></li>
																		<li><%= wahlInfo.getEML510dFilename(true) %> <ivu:int key="Export_FinalMessage_FromP4"/></li>
																		<li><%= wahlInfo.getEML520Filename() %> <ivu:int key="Export_FinalMessage_FromP5"/></li>
																	</ol>
																	<ivu:int key="Export_FinalMessage_II"/>
																</div>
																<br />
															<% }
														  } %>
														<ivu:form action="<%=urlExp%>" onsubmit="transp();">
								                     <div style="margin-left: 1em; margin-top: 1em; margin-bottom: 1em;">
																<input id="box2" type="submit" value="<%=AdministrationBean.NEU %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
										               </div>
														</ivu:form>
													</fieldset>
				                         <% }
				                      }%>
			                      </td>
			                   </tr>
			                </table>
			               </td>
			               <td width="10">&nbsp;</td>
			            </tr>
			            <tr>
			              	<td></td>
			              	<td>&nbsp;<% if (legende) { %>* = <ivu:int key="Export_MandatoryFields"/><% } %></td>
			              	<td></td>
						</tr>
			            <tr>
			              	<td></td>
			              	<td>&nbsp;<% if (legende) { %>** = <ivu:int key="Export_Gew_Ben_DifferentAddressHint"/><% } %></td>
			              	<td></td>
						</tr>
			         </table>
			      </td>
			   </tr>
			</table>
		</div>
	</div>
</body>
</html>