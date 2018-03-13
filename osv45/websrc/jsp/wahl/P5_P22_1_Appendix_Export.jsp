<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ page import="de.ivu.wahl.Basiseinstellung"%>
<%@ page import="de.ivu.wahl.Basiseinstellung.Typ"%>
<%@ page import="de.ivu.wahl.BasiseinstellungMultiMap"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.admin.DialogStateHolder"%>
<%@ page import="de.ivu.wahl.admin.P5ExportStateP22_1Appendix"%><html>
<%@ page import="de.ivu.wahl.client.beans.AdministrationBean"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten"%>
<%@ page import="de.ivu.wahl.client.beans.Command" %>
<%@ page import="de.ivu.wahl.client.beans.ExportP5Commands"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.client.util.GUICommand"%>
<%@ page import="de.ivu.wahl.export.XMLTags"%>
<%@ page import="de.ivu.wahl.modell.GruppeModel"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "ProtocolAppendixExport"; //$NON-NLS-1$

String breite = "100%"; //$NON-NLS-1$
String prefix = ApplicationBeanKonstanten.PREFIX;
int subwork = ClientHelper.getIntParameter(request.getParameter(prefix + "subwork"), 0); //$NON-NLS-1$
DialogStateHolder state = admBean.getP5ExportStateP22_1Appendix();
BasiseinstellungMultiMap bmm = Konstanten.PROP_P22_1_APPENDIX;
WahlInfo wahlInfo = WahlInfo.getWahlInfo();
boolean legende = false;
boolean isEK = wahlInfo.getElectionCategory().isEK();
%>

<head>
   <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
   <title>P22_1_appendix</title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/wahl2002.css">
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
			    eval("document.p" + k + ".src = '<%=request.getContextPath()%>/img/lila.gif'");
			    k++;
			    window.setTimeout("doit(" + k + "," + l + "," + msec + ")", msec);
			  } else {
				 eval("document.p" + l + ".src = '<%=request.getContextPath()%>/img/leer.gif'");
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
				$("#<%= ApplicationBeanKonstanten.PREFIX + XMLTags.RG_OBJECTIONS %>").autocomplete(gbaData);
				$("#<%= ApplicationBeanKonstanten.PREFIX + XMLTags.RG_NOTES %>").autocomplete(gbaData);
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
							<img src="<%=request.getContextPath()%>/img/leer.gif" name="p0" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p1" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p2" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p3" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p4" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p5" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p6" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p7" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p8" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p9" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p10" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p11" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p12" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p13" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p14" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p15" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p16" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p17" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p18" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p19" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p20" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p21" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p22" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p23" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p24" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p25" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p26" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p27" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p28" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p29" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p30" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p31" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p32" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p33" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p34" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p35" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p36" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p37" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p38" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p39" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p40" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p41" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p42" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p43" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p44" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p45" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p46" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p47" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p48" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p49" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p50" height="12" width="5"/><img src="<%=request.getContextPath()%>/img/leer.gif" name="p51" height="12" width="5"/>
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
				<ivu:int key="Export_Protocol_Appendix_P5_titel"/>
			</div>
			<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
				&nbsp;
			</div>
			<jsp:include page="zwischenstand.jsp"></jsp:include>
			<table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
			   <tr>
			      <td valign="top">
			         <table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0" class="hghell">
				         <tr>
			               <td colspan="3"><img src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="10"></td>
			            </tr>
							<tr>
			               <td width="10"><img src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="1"></td>
			               <td valign="top">
			                 <table border="0" cellspacing="0" cellpadding="1" width="<%=breite%>">
			                   <tr>
			                      <td valign="top">
			                        <%
			                          String exportVerz = admBean.getProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR); 
			                    		if (exportVerz == null || exportVerz.isEmpty()){
			                        %>
			                     		<fieldset style="border: 1px solid #093C69; padding: 15px">
			                          		<legend><b><ivu:int key="Export_Verzeichnis_titel"/></b></legend><br />
			                              <ivu:int key="KeinZielverzeichnisAngegeben"></ivu:int>
			                        	</fieldset>
			                      	<% } else if (wahlInfo.getStatus() != WahlModel.STATE_CALCULATION_CONFLICT && wahlInfo.getStatus()!= WahlModel.STATE_CALCULATION_SUCCESSFUL){ %>
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
			                    		if (!wahlInfo.isFreigegeben() && wahlInfo.isWahlVollstaendig()){
			                      	%>
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
						                  <%
						                    }	
						                    if (P5ExportStateP22_1Appendix.STATUS_P22_1_APPENDIX_D1 == state._modus){
						                  %>
												 <fieldset style="border: 1px solid #093C69; padding: 15px">
												 <legend><b><ivu:int key="Export_Protocol_Appendix"/></b></legend>
												 		<% if (isEK) { %>
															<p><ivu:int key="Export_Protocol_Appendix_D2_EK"/></p>
												 		<% } else { %>
															<p><ivu:int key="Export_Protocol_Appendix_D2"/></p>
												 		<% } %>
														<%
														  if (admBean._adminMsgProps != null && !admBean._adminMsgProps.isEmpty() ){
														%>
															<p style="color:red;"><b><%=admBean._adminMsgProps%></b></p>
														<% admBean._adminMsgProps = null;
														  }
														  if (admBean._adminMsgPropsConfirmation != null && !admBean._adminMsgPropsConfirmation.isEmpty() ){
														%>
															<p style="color:green;"><b><%=admBean._adminMsgPropsConfirmation%></b></p>
														<% admBean._adminMsgPropsConfirmation = null;
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
													<table border="0" cellspacing="0" cellpadding="0" width="<%=breite%>">
													   <tr>
													    <td height="20" valign="top" class="guiBefehle"><%
													      int buttonIdx = 0;
													    	String selectedKey = null;
													    	for (String buttonName : bmm.keySet()) {
													    	boolean selected = buttonIdx++ == subwork;
													    	if (selected) {
													    	 selectedKey = buttonName;
													    	}
													    	String styleClass = GUICommand.GUI_CLASS_1;
													    %> 
													       <span class='<%=styleClass%>' ><%=buttonName%></span><%
 													         }
 													       %>
													      </td>
													     </tr><%
													       if (selectedKey != null) {
													     		String formurl = "/osv?cmd=" + ExportP5Commands.CMD_ADM_PROP_EINGABE_P22_1_APPENDIX_1 + "&" + ApplicationBeanKonstanten.PREFIX + "subwork=" + subwork 
													     		    + "&" + ClientHelper.getAllParameters(request, true);
													     %>
													      <tr>
													       <td style="border: 1px solid rgb(9, 60, 105); padding-left: 1em width: 100%;">
													      <ivu:form action="<%=formurl%>">
													        <table border="0" cellspacing="4" cellpadding="1" width="<%=breite%>" style="padding: 1em;">
													          <colgroup width="50%" span="2"></colgroup><%
													            List<Basiseinstellung> basiseinstellungen = bmm.get(selectedKey);
													          	for (Basiseinstellung basiseinstellung : basiseinstellungen) {
													          		boolean restart = basiseinstellung.isRestart();
													          		String styleClass = restart ? "frot" : "fgrau";
													          		String styleClassChb = restart ? "checkbox_rot" : "checkbox"; 
													          		String styleChb = restart ? "border: 1px solid #D00;" : "";
													          %>
													          <tr title="<%=basiseinstellung.getHinweis()%>">
													           <td><%=basiseinstellung.getBeschreibung()%><%=basiseinstellung.isPflichtfeld() ? " *": ""%></td>
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
  													              }								
													           %>
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
  							                                   }
													              %>
							                                   <input type="text" class="feld <%=styleClass%>" height="1" size="64" id="<%=property%>" name="<%=property%>" value="<%=wert%>"><%
							                                     break;
  							                               case Date:
  							                               case Time:
  							                               case ZIP:
  													              // Es können nicht gesetzte Werte als null kommen!
  													              if (wert == null) {
  													               	wert = "";
  													              }
							                                   %>
													              <input type="text" class="feld <%=styleClass%>" size="64" id="<%=property%>" name="<%=property%>" value="<%=wert%>"><%
													                break;
  							                               case Textarea:
  				                                         // Es können nicht gesetzte Werte als null kommen!
  				                                         if (wert == null) {
  				                                          wert = "";
  				                                         }
													              %>
				                                         <textarea class="feld <%=styleClass%>" cols="64" rows="10" id="<%=property%>" name="<%=property%>"><%=wert%></textarea><%
				                                           break;  
  													             case Integer:
  													              // Es können nicht gesetzte Werte als null kommen!
  													              if (wert == null) {
  													                 wert = "0";
  													              }
				                                         %>
													              <input type="text" class="feld <%=styleClass%>" size="64" name="<%=property%>" value="<%=wert%>"><%
													                break;
  													              
  													             case Boolean:
  													              boolean bWert = "true".equals(wert);
													              %>
													              <div class="<%=styleClassChb%>">
													               <input type="checkbox" style="<%=styleChb%>" <%=bWert ? "checked" : ""%> name="<%=property%>" value="x" />
													              </div><%
													                break;
													              													              
													            default:
													              %>
													              <%=BundleHelper.getBundleString("FormatUnbekannt")%><%
													                }
													              %>
													           </td>
													          </tr><%
													            }
													          %>
									                   </table>
											             <div style="margin-left: 1em; margin-top: 1em; margin-bottom: 1em;">
																<input id="box2" type="submit" value="<%=AdministrationBean.WEITER%>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
															 </div>
												        </ivu:form>
												       </td>
												      </tr><%
												        }
												      %>
												    </table>
												 </fieldset>
			                         <%
			                           } else if (P5ExportStateP22_1Appendix.STATUS_P22_1_APPENDIX_D2 == state._modus){
			                       			String urlExp = "/osv?cmd=" + ExportP5Commands.CMD_ADM_EXPORT_P22_1_APPENDIX + "&" + ClientHelper.getAllParameters (request);
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
															%>
							                            	<% if (admBean._adminMsgExportConfirmation != null && !admBean._adminMsgExportConfirmation.isEmpty() ){%>
																		<p style="color:green;"><b><%= admBean._adminMsgExportConfirmation %></b></p>
																	<% admBean._adminMsgExportConfirmation = null; 
																} 
															%>
																	<br/>
								                           <ivu:form action="<%=urlExp%>" onsubmit="transp();">
								                           	<table cellspacing="4" cellpadding="2" border="0">
						                                       <tr>
							                                       <td colspan="2"><b><ivu:int key="Export_Protocol_Appendix"/></b></td>
							                                    </tr>
							                                    <%	for (Basiseinstellung basiseinstellung : bmm.getBasis()) { 
								                            		   String property = basiseinstellung.getProperty();
						                                    		   String wert = admBean.getProperty(property); %>
						                                    		 <tr title="<%= basiseinstellung.getHinweis() %>">
									                                   <td valign="top"><%= basiseinstellung.getBeschreibung() %><%= basiseinstellung.isPflichtfeld() ? " *": "" %>:</td>
									                                   <td><textarea rows="5" cols="64" disabled="disabled"><%= ClientHelper.forHTML(ClientHelper.getEmptyStringIfBlank(wert)) %></textarea></td>
									                                  </tr>
										                          	<% } %>
						                        					<tr>
							                                       <td>&nbsp;</td>
							                                       <td>&nbsp;</td>
							                                    </tr>
							                                    <tr>
							                                       <td>&nbsp;</td>
							                                       <td>&nbsp;</td>
							                                    </tr><tr>
							                                    	<%  String wert = admBean.getProperty(Konstanten.PROP_LAST_EXPORT_FORMAT);
							                                    		if (wert == null) {
							                                    			wert = "";
							                                    		}
							                                    	%>
									                                   <td><ivu:int key="Export_RTF_PDF"/>:</td>
									                                   <td><select name="<%=ApplicationBeanKonstanten.PREFIX%>RTF_PDF">
									                                   			<option value="pdf"<%= wert.equals("pdf") ? " selected=\"selected\"" : "" %>>PDF</option>
									                                   			<option value="rtf"<%= wert.equals("rtf") ? " selected=\"selected\"" : "" %>>RTF</option>
									                                   		</select>
									                                   	</td>
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
			                         <%
			                           }  else if (P5ExportStateP22_1Appendix.STATUS_P22_1_APPENDIX_D3 == state._modus){
			                         											// reset export status
			                         											admBean.resetExportStateP22_2Appendix();
			                         											//forward to Werkmap
			                         											String urlExp = "/osv?cmd=" + ExportP5Commands.CMD_ADM_EXPORT_P22_1_APPENDIX + "&" + ClientHelper.workIs(Command.EXPORT_VERZEICHNIS) + "&" + ClientHelper.getAllParameters(request, ApplicationBeanKonstanten.WORK);
			                         %>
				                       		<fieldset style="border: 1px solid #093C69; padding: 15px">
												 		<legend><b><ivu:int key="Export_Protocol_Appendix"/></b></legend>
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
															
															   if (wahlInfo.isFreigegeben()) {
															%>
																<div>
																	<ivu:int key="Export_Protocol_Appendix_FinalMessage_I"/>
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
			         </table>
			      </td>
			   </tr>
			</table>
		</div>
	</div>
</body>
</html>