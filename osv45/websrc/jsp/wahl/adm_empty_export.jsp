<%@page import="de.ivu.wahl.client.beans.ExportP4Commands"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.Command" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="java.util.List"%>
<%@ page import="de.ivu.wahl.client.util.GUICommand"%>
<%@ page import="de.ivu.wahl.modell.GruppeModel"%>
<%@ page import="java.util.Collection"%>
<%@ page import="de.ivu.wahl.client.beans.AdministrationBean"%>
<%@ page import="de.ivu.wahl.Basiseinstellung"%>
<%@ page import="de.ivu.wahl.Basiseinstellung.Typ"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.client.beans.RepositoryPropertyHandler"%>
<%@ page import="de.ivu.wahl.admin.DialogStateHolder"%>
<%@ page import="de.ivu.wahl.admin.P4ExportStateEmptyEml"%>
<%@ page import="de.ivu.wahl.wus.electioncategory.ElectionCategory"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<jsp:useBean id="expP4Bean" scope="session" class="de.ivu.wahl.client.beans.ExportP4Bean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%  
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "ExpEmpty"; //$NON-NLS-1$

String breite = "100%"; //$NON-NLS-1$
DialogStateHolder p4ES = expP4Bean.getP4ExportStateEmptyEml();
WahlInfo wahlInfo = WahlInfo.getWahlInfo();
SystemInfo systemInfo = SystemInfo.getSystemInfo();

String i18nName;
String i18nTitle;
String i18nText;
if (wahlInfo.getElectionCategory().equals(ElectionCategory.GR) || wahlInfo.getElectionCategory().equals(ElectionCategory.BC) || wahlInfo.getElectionCategory().equals(ElectionCategory.GC)) {
	i18nName = "Export_P4_EmptyTellingTotaaltelling"; //$NON-NLS-1$
	i18nTitle = "Export_P4_titel_EmptyTellingTotaaltelling"; //$NON-NLS-1$
	i18nText = "Export_P4_text_EmptyTellingTotaaltelling"; //$NON-NLS-1$
} else if (systemInfo.getWahlEbene() == GebietModel.EBENE_CSB) {
	i18nName = "Export_P4_EmptyTotaaltelling"; //$NON-NLS-1$
	i18nTitle = "Export_P4_titel_EmptyTotaaltelling"; //$NON-NLS-1$
	i18nText = "Export_P4_text_EmptyTotaaltelling"; //$NON-NLS-1$
} else {
	i18nName = "Export_P4_EmptyTelling"; //$NON-NLS-1$
	i18nTitle = "Export_P4_titel_EmptyTelling"; //$NON-NLS-1$
	i18nText = "Export_P4_text_EmptyTelling"; //$NON-NLS-1$
}

%>
<html>
<head>
   <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
   <title><ivu:int key="<%=i18nName%>"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
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
			
		  
		 </script>
	</head>
<body class="hghell">
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
			               <td colspan="2" class="hggrau" height="20"><ivu:int key="<%=i18nTitle%>"/></td>
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
			                         <%String exportVerz = expP4Bean.getProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR); 
			                         if (exportVerz == null || exportVerz.isEmpty()){ %>
				                     		<fieldset style="border: 1px solid #093C69; padding: 15px">
				                          		<legend><b><ivu:int key="Export_Verzeichnis_titel"/></b></legend><br />
				                              <ivu:int key="KeinZielverzeichnisAngegeben"></ivu:int>
				                        	</fieldset>
				                      	<% } else {
												if (P4ExportStateEmptyEml.STATUS_P4_D1 == p4ES._modus){ 
													String urlExp = "/osv?cmd=" + ExportP4Commands.EXP_P4_EXPORT_P4_EMPTY_EML + "&" +ClientHelper.getAllParameters (request); //$NON-NLS-1$//$NON-NLS-2$
												   %>
							                  <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
							                     <tr>
							                        <td valign="top">
						         	                  <fieldset style="border: 1px solid #093C69; padding: 15px">
							                           	<legend><b><ivu:int key="<%=i18nName%>"/></b></legend>
							                            	<% if (expP4Bean._adminMsgExport != null && !expP4Bean._adminMsgExport.isEmpty() ){%>
																		<p style="color:red;"><b><%= expP4Bean._adminMsgExport %></b></p>
																		<% 
																		expP4Bean._adminMsgExport = null;
							                            	}
							                            	if (expP4Bean._adminMsgExportConfirmation != null && !expP4Bean._adminMsgExportConfirmation.isEmpty() ){%>
																		<p style="color:green;"><b><%= expP4Bean._adminMsgExportConfirmation %></b></p>
																		<% 
																		expP4Bean._adminMsgExportConfirmation = null;
							                            	} %>
							                            	<br/>
								                           <ivu:form action="<%=urlExp%>" onsubmit="transp();">
								                           	<ivu:int key="<%=i18nText%>"/>
							                                 <div style="margin-left: 1em; margin-top: 1em; margin-bottom: 1em;">
										                         	<% if (expP4Bean._adminWarningOverride != null && !expP4Bean._adminWarningOverride.isEmpty() ){%>
																				<p class="warningMessage"><b><%= expP4Bean._adminWarningOverride %><br/>
																				<ivu:int key="Datei_Ueberschreiben"/> </b></p>
																				<% expP4Bean._adminWarningOverride = null; %> 
																				<input type="hidden" value="1" name="<%=ApplicationBeanKonstanten.PREFIX%>force"/>
																				<input id="box2" type="submit" value="<%=RepositoryPropertyHandler.RESET %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
																				<input id="box2" type="submit" value="<%=RepositoryPropertyHandler.WEITER %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
									                            	<% } else { %>
												                         <input id="box2" type="submit" value="<%=RepositoryPropertyHandler.WEITER %>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
										                         	<% } %>
																		</div>
																	</ivu:form>
							                           </fieldset>
							                        </td>
							                     </tr>
							                  </table>
			                         <% } else if (P4ExportStateEmptyEml.STATUS_P4_D2 == p4ES._modus){
			                         				// reset export status
													expP4Bean.resetExportStateEmptyEml();
													//forward to Werkmap
													String urlExp = "/osv?cmd=" + ExportP4Commands.EXP_P4_EXPORT_P4_EMPTY_EML + "&" + ClientHelper.workIs(Command.EXPORT_VERZEICHNIS) +"&" +ClientHelper.getAllParameters(request, ApplicationBeanKonstanten.WORK); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				                       		%>
				                       		<fieldset style="border: 1px solid #093C69; padding: 15px">
												 		<legend><b><ivu:int key="<%=i18nName%>"/></b></legend>
														<p><ivu:int key="Export_erfolgreich"/></p>
														<%
														  if (expP4Bean._adminMsgExport != null && !expP4Bean._adminMsgExport.isEmpty() ){
														%>
															<p style="color:red;"><b><%=expP4Bean._adminMsgExport%></b></p>
															<% expP4Bean._adminMsgExport = null;
														  
														  }
														  if (expP4Bean._adminMsgExportConfirmation != null && !expP4Bean._adminMsgExportConfirmation.isEmpty() ){
														%>
															<p style="color:green;"><b><%=expP4Bean._adminMsgExportConfirmation%></b></p>
															<% expP4Bean._adminMsgExportConfirmation = null;
														  
														  }
														%>
														<ivu:form action="<%=urlExp%>" onsubmit="transp();">
								                     <div style="margin-left: 1em; margin-top: 1em; margin-bottom: 1em;">
														 <input id="box2" type="submit" value="<%=AdministrationBean.NEU%>" name="<%=ApplicationBeanKonstanten.PREFIX%>uebernehmen"/>
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
			         </table>
			      </td>
			   </tr>
			</table>
		</div>
	</div>
</body>
</html>