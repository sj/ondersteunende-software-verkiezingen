<%--
 *******************************************************************************
 * Sitzverteilung
 *
 * author:  mur@ivu.de  Copyright (c) 2002-10 Statistisches Bundesamt und IVU Traffic Technologies AG

 *******************************************************************************
 --%>

<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.WahlInfo" %>
<%@ page import="de.ivu.wahl.GebietsBaum"%>
<%@ page import="de.ivu.wahl.anwender.Rechte"%>
<%@ page import="de.ivu.wahl.auswertung.erg.sv.SitzverteilungErg" %>
<%@ page import="de.ivu.wahl.auswertung.erg.sv.Gruppenzeile"%>
<%@ page import="de.ivu.wahl.auswertung.erg.sv.SitzverteilungErg"%>
<%@ page import="de.ivu.wahl.auswertung.sv.SitzverteilungStatus"%>
<%@ page import="de.ivu.wahl.auswertung.erg.Besonderheiten"%>
<%@ page import="de.ivu.wahl.auswertung.erg.Besonderheiten.KonfliktAnzeige"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten"%>
<%@ page import="de.ivu.wahl.modell.AlternativeModel"%>
<%@ page import="de.ivu.wahl.modell.GebietInfo"%>
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<%@ page import="de.ivu.wahl.modell.WahlModel" %>
<%@ page import="de.ivu.wahl.modell.ejb.Gebiet"%>
<%@ page import="de.ivu.wahl.modell.ejb.Wahl"%>
<%@ page import="de.ivu.wahl.modell.ejb.SitzberechnungErgebnis"%>
<%@ page import="de.ivu.wahl.modell.ejb.Alternative"%>
<%@ page import="de.ivu.wahl.result.drawlots.DecisionType"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>

<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />

<% 
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "sitzverErg"; //$NON-NLS-1$

WahlInfo wahlInfo = appBean.getWahlInfo();
   String id_ergebniseingang = wahlInfo.getWahl().getWurzelgebiet().getID_LetzterEingang();
   String breite = "100%";
   
   GebietsBaum gebietsBaum = appBean.getGebietsBaum();
   GebietInfo rootInfo = (GebietInfo)gebietsBaum.getWurzel().getUserObject();
   int art = ClientHelper.getLevel(request, rootInfo.getGebietsart());
   int gebNr = ClientHelper.getGebietNr(request, rootInfo.getNummer());  
   String id_gebiet = wahlInfo.getID4Gebiet(art, gebNr);
   
   NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);
   nf.setMaximumFractionDigits(2);
   nf.setMinimumFractionDigits(2);
   AnwContext anwContext = appBean.getAnwContext();
   boolean darfBerechnen = anwContext.checkRight(Rechte.R_SITZVERTEILUNG_BERECHNEN);
  %>

<html>
 <head>
  <title><ivu:int key="Sitzverteilung_titel"/></title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
  <style type="text/css">body { overflow:auto }</style>
  <script>
		var contextPath = "<%=request.getContextPath()%>";
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
							<ivu:int key="BerechnungInWenigenSekunden"/>
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
 			<%@include file="/jsp/fragments/print_and_help_div.jspf"%>
			<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
				&nbsp;
			</div>
			<div class="hggrau" style="height: 20px; width: 100%; text-indent: 5px; line-height: 18px;">
				<ivu:int key="Sitzverteilung"/>
			</div>
			<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
				&nbsp;
			</div>
			<jsp:include page="zwischenstand.jsp"></jsp:include>
			<table width="<%=breite %>" cellspacing="0" cellpadding="0" border="0" class="hghell">
				<tbody>
					<tr>
						<td valign="top">
							<table width="<%=breite%>" cellspacing="0" cellpadding="1" border="0">
								<tbody>
									<tr>
								      <td colspan="3" class="hghell"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
								   </tr>
									<tr>
										<td width="10"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="1" width="1" /></td>
										<td valign="top">
											<table width="<%=breite %>" cellspacing="0" cellpadding="1" border="0">
												<tbody>
													<tr class="hgrot">
														<td valign="top">
				                             <% if (wahlInfo.getStatus() == WahlModel.STATE_CALCULATING){ %>  
					                            <table class="hgweiss" border="0" cellpadding="0" cellspacing="0"
					                            width="<%= breite %>">
					                              <tbody>
				                                		<tr class="hgeeeeee">
																	<td height="20" width="5">
																		&nbsp;
																	</td>
																	<td>
																		<ivu:int key="Sitzverteilung_Berechnung_durch_anderen_User"/>
																		<br/>
																	</td>
																	<td width="5">
																		&nbsp;
																	</td>
																</tr>
										               </tbody>
					                            </table>
				                             <%} else if (wahlInfo.getStatus() == WahlModel.STATE_METADATA_P5){ %>  
					                            <table class="hgweiss" border="0" cellpadding="0" cellspacing="0"
						                            width="<%= breite %>">
						                              <tbody>
					                                		<tr class="hgeeeeee">
																		<td height="20" width="5">
																			&nbsp;
																		</td>
																		<td>
																			<ivu:int key="Sitzverteilung_Nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Kein_Ergebniseingang"/>
																			<br/>
																		</td>
																		<td width="5">
																			&nbsp;
																		</td>
																	</tr>
											               </tbody>
						                            </table>
					                             <%} else if (wahlInfo.getStatus() == WahlModel.STATE_NEW_RESULT){ 
				                               String formurl = "/osv?cmd=app_startSitzberechnung&"+ ClientHelper.getParametersDoNotStartWith(request, ApplicationBeanKonstanten.PREFIX); %>
				                               <ivu:form action="<%=formurl%>" style="margin: 0px;" onsubmit="transp();">
						                            <table class="hgweiss" border="0" cellpadding="0" cellspacing="0"
							                            width="<%= breite %>">
						                              <tbody>
					                                		<tr class="hgeeeeee">
																		<td width="5">
																			&nbsp;
																		</td>
																		<td>
																			<ivu:int key="Sitzverteilung_Nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Anstossen"/><br/><br/>
																		</td>
																		<td width="5">
																			&nbsp;
																		</td>
																	</tr>
																	<% if (appBean.getSitzverteilungErrorMsg() != null && !appBean.getSitzverteilungErrorMsg().isEmpty()){ %>
						                                		<tr class="hgeeeeee">
																			<td width="5">
																				&nbsp;
																			</td>
																			<td style="color: red">
																				<%=ClientHelper.forHTML(appBean.getSitzverteilungErrorMsg()) %><br/><br/>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr>
																	<% } %>
																	<%if (darfBerechnen){ %>
																		<tr class="hgeeeeee">
																			<td height="20" width="5">
																				&nbsp;
																			</td>
																			<td>
																				<ivu:int key="BerechnungSitzverteilungAnstossen"/>: &nbsp;&nbsp;&nbsp; 
																				<input id="box2" type="submit" value="Start" name="<%=ApplicationBeanKonstanten.PREFIX%>start"/>
																				<input type="hidden" value="<%=id_ergebniseingang %>" name="<%=ApplicationBeanKonstanten.PREFIX%>id_Ergebniseingang"/>
																				<br/><br/>
																				<ivu:int key="BerechnungSitzverteilungHinweisVerstorbeneKandidaten"/>
																				<br/><br/>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr>
																	<% } %>
															   </tbody>
						                            </table>
					                            </ivu:form>
					                          <%} else if (wahlInfo.getStatus() == WahlModel.STATE_CALCULATION_CONFLICT) { 
				                           			if (!darfBerechnen){ %>
						                            	<table class="hgweiss" border="0" cellpadding="0" cellspacing="0"
							                            width="<%= breite %>">
							                              <tbody>
						                                		<tr class="hgeeeeee">
																			<td height="20" width="5">
																				&nbsp;
																			</td>
																			<td>
																				<br/>
																				<ivu:int key="Sitzverteilung_Nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Konflikt"/>
																				<br/>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr>
												               </tbody>
							                            </table>
						                         	<% } else {
						                         	   SitzverteilungStatus status = appBean.getSitzverteilungStatus(id_ergebniseingang, request);
						                              String formurl = "/osv?cmd=app_setKonfliktAlternative&"+ ClientHelper.getParametersDoNotStartWith(request, ApplicationBeanKonstanten.PREFIX); %>
						                              <ivu:form action="<%=formurl%>" style="margin: 0px;" onsubmit="transp();">
									                        <table class="hgweiss" border="0" cellpadding="0" cellspacing="0"
								                            width="<%= breite %>">
								                              <tbody>
								                                <tr class="hgeeeeee">
								                                  <td height="20" width="5">
								                                    &nbsp;
								                                  </td>
								                                  <td><ivu:int key="Sitzverteilung_Losentscheidung"/>:</td>								
								                                  <td width="5">
								                                    &nbsp;
								                                  </td>
								                                </tr>
								                                
								                                
								                                <% if (appBean.getSitzverteilungErrorMsg() != null && !appBean.getSitzverteilungErrorMsg().isEmpty()){ %>
						                                		<tr class="hgeeeeee">
																			<td width="5">
																				&nbsp;
																			</td>
																			<td style="color: red">
																				<%=ClientHelper.forHTML(appBean.getSitzverteilungErrorMsg()) %><br/><br/>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr>
																<% } %>
								                                <tr>
												                    		<td colspan="3" class="hgrot"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
												                    </tr>
								                                <tr>
								                                  <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="10" width="1" /></td>
								                                </tr>
								                                <tr>
								                                  <td width="10"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="1" width="1" /></td>
								
								                                  <td valign="top">
								                                    <table class="hgweiss" border="0" cellpadding="4" cellspacing=
								                                    "2" width="<%= breite %>">
								                                      <tbody>
								                                        <tr class="hgweiss">
								                                          <td colspan="3">
								                                            <%=DecisionType.byId(status.getKonflikt().getKonfliktart()).getReasonAndAppendix(wahlInfo.getElectionSubcategory())  %>
								                                            <br/><br/><br/><br/>
								                                          </td>
								                                        </tr>
								                                        <% 
								                                        int i = -1;
								                                        int zaehler = 1;
								                                        for (SitzverteilungStatus.Alternative alternative: status.getAlternativen()) { %>
									                                        <tr>
									                                          <td class="<%= i > 0 ? "hgweiss":"hgeeeeee" %>" width="5%">
									                                            <%=zaehler %>.
									                                          </td>
									                                          <td class="<%= i > 0 ? "hgweiss":"hgeeeeee" %>" width="5%" align="center">
									                                             <input type="radio" value="<%= alternative.getId_Alternative()%>" name="<%=ApplicationBeanKonstanten.PREFIX%>alternative"/>
									                                          </td>
									                                          <td class="<%= i > 0 ? "hgweiss":"hgeeeeee" %>">
									                                            <%=ClientHelper.forHTML(alternative.getAlternativeText())%>
									                                          </td>
									                                        </tr>
								                                        <% i = -i;
								                                        zaehler++;
								                                        	 } %>
								                                        	 
								                                         <tr class="<%= i > 0 ? "hgweiss":"hgeeeeee" %>">
								                                          <td colspan="3">
								                                            <br/><br/>
								                                            <%=DecisionType.byId(status.getKonflikt().getKonfliktart()).getMessage() %>
								                                          </td>
								                                        </tr>	 
								                                        <tr class="<%= i < 0 ? "hgweiss":"hgeeeeee" %>">
								                                          <td colspan="3">
								                                            	<br/><br/>
																							<input type="hidden" value="<%=id_ergebniseingang %>" name="<%=ApplicationBeanKonstanten.PREFIX%>id_Ergebniseingang"/>
								                                            	<input id="box2" type="submit" name="<%= BundleHelper.getBundleString("Sitzverteilung_Losentscheidung_submit") %>" value="<%= BundleHelper.getBundleString("Sitzverteilung_Losentscheidung_submit") %>" />
								                                          </td>
								                                        </tr>	 
								                                      <tbody>
								                                    </table>
								                                  </td>
								                                  <td width="10">
								                                    &nbsp;
								
								                                  </td>
								                                </tr>
								                                <tr>
								                                  <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="10" width="1" /></td>
								                                </tr>
								                              </tbody>
								                            </table>
								                         </ivu:form><%
						                         		}
					                              } 
													if (wahlInfo.getStatus() == WahlModel.STATE_CALCULATION_SUCCESSFUL || wahlInfo.getStatus() == WahlModel.STATE_CALCULATION_CONFLICT ) {   	
		                              				SitzverteilungErg erg = appBean.getSitzverteilungErgebnis(id_ergebniseingang, request);
		                             					Set<Gruppenzeile> gruppenzeilen = erg.getGruppenzeileCol();
		                                				int i = 1;
						                            %> <table class="hgweiss" border="0" cellpadding="0" cellspacing="0" width="<%= breite %>">
							                            <tbody>
							                                <tr>
													            <td class="hgrot" colspan="3"><img width="1" height="1" src="/P5/img/icon/blind.gif" alt=""/></td>
													        </tr>
							                                <tr class="hgeeeeee">
								                                <td height="20" width="5">
								                                    &nbsp;
								                                </td>
							                                  	<td><ivu:int key="Sitzverteilung_titel"/></td>
																<td width="5">
							                                    	&nbsp;
							                                	</td>
							                                </tr>
							                                <tr>
									                        	<td colspan="3" class="hgrot"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
									                        </tr>
							                                <tr>
							                                  <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="10" width="1"></td>
							                                </tr>
							                                <tr>
							                                  <td width="10"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="1" width="1"></td>
							                                  <td valign="top">
							                                    <table class="hgweiss" border="0" cellpadding="4" cellspacing=
							                                    "2" width="<%= breite %>">
							                                      <tbody>
							                                        <tr class="hgformular">
							                                          <td colspan="2" style="width: 497px;">
							                                          	<% if (wahlInfo.getWahl().getWurzelgebiet().getGebietsart() == GebietModel.GEBIETSART_BUND) { %>
							                                            	<ivu:int key="EndergebnisNiederlande"/>
							                                            <% } else { %> 
							                                            	<ivu:int key="Endergebnis"/> <%= wahlInfo.getWahl().getWurzelgebiet().getBezeichnung() %>
							                                            <% } %>
							                                          </td>
							                                          <td style="width: 154px; text-align: right;">
							                                            <ivu:int key="Sitzverteilung_Anzahl_Stimmen"/>
							                                          </td>
							
							                                          <td style="width: 105px; text-align: right;">
							                                            <ivu:int key="Sitzverteilung_Prozent"/>
							                                          </td>
							                                          <td style="text-align: right;">
							                                            <ivu:int key="Sitzverteilung_Sitze"/>
							                                          </td>
							                                        </tr>
							                                        
							                                        <% 
							                                        boolean first = true;
							                                        for (Gruppenzeile gruppenzeile : gruppenzeilen) { 
							                                        	int position = gruppenzeile.getGruppeGebietsspezifisch().getPosition();%>
							                                           <tr class="<%= i > 0 ? "hgweiss":"hgeeeeee" %>">
								                                          <% if (position > 0 ){ %>
									                                          <td style="width: 50px;">
									                                            <%= position %>
									                                          </td>
									                                          <td style="width: 447px;">
									                                            <%= ClientHelper.forHTML(gruppenzeile.getGruppe().getNameLang()) %>
									                                          </td>
									                                          <td style="width: 154px; text-align: right;">
									                                            <%= ClientHelper.getStimmanzahlString(gruppenzeile.getStimmenanzahl(), ClientHelper.DF) %>
									                                          </td>
									                                          <td style="width: 105px; text-align: right;">
																								<%= nf.format(gruppenzeile.getProzetualerAnteil()) %>			
									                                          </td>
									                                          <td style="text-align: right;">
									                                            <%= gruppenzeile.getSitzanzahl() %>
									                                          </td>
								                                          <% } %>
								                                        </tr>
							                                        <% i = -i;
							                                        	 } %>
							                                      <tbody>
							                                    </table>
							                                  </td>
							                                  <td width="10">
							                                    &nbsp;
							
							                                  </td>
							                                </tr>
							                                <tr>
							                                  <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="10" width="1" /></td>
							                                </tr>
							                              </tbody>
							                            </table>
															<%} %>
															
														</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td width="10">
											&nbsp;
										</td>
									</tr>
									<tr>
										<td height="10" colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1" /></td>
									</tr>
									<%
								   Besonderheiten besonderheiten = appBean.getBesonderheiten(id_ergebniseingang);
	                        List<Besonderheiten.KonfliktAnzeige> kas = besonderheiten.getKonflikte();
	                        if (kas != null && !kas.isEmpty()){ %>
		                        <tr>
											<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
											<td valign="top">
									    		<fieldset style="border: 1px solid #093C69; padding: 15px">
		                           		<legend><b><ivu:int key="Sitzverteilung_Losentscheidungen"/></b></legend>
			                            	<ol start="1">
			                            		<% for (Besonderheiten.KonfliktAnzeige ka: kas) { %>
					                            	<li style="margin-bottom: 15px;">
					                            	  <%=DecisionType.byId(ka.getArt()).getReasonAndAppendix(wahlInfo.getElectionSubcategory()) %><br/>
			                                      <br/><span style="text-decoration:underline;"><ivu:int key="Sitzverteilung_Losentscheidungsoptionen"/></span><br/><br/>
			                                      <ol type="a">
					                                    <%
					                                    int z = 0; 
					                                    for (String alternative:  ka.getAlternativen()) { %>
			                                        	  <li>
			                                        	  		<%if (ka.getLosgewinner() == z){%><b><%}%>
			                                        	  		<%= ClientHelper.forHTML(alternative) %>
			                                        	  		<%if (ka.getLosgewinner() == z){%>(*)</b><%}%>
			                                        	  </li>
				                                       <%
			                                        	z++;
					                                    } %>
		                                        	</ol>
		                                        	<br/>
		                                        	<%=DecisionType.byId(ka.getArt()).getMessage() %>
			                                    </li>
                                       	<% } %>
				                           </ol>
					                        (*) <ivu:int key="Sitzverteilung_Losentscheidung_gewaehlt"/>  
			                            </fieldset>	
											</td>
											<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
										</tr>
										<tr>
											<td height="10" colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1" /></td>
										</tr>
                           <% }
                           List<String> deceasedCandidates = besonderheiten.getDeceasedCandidates();
	                        if (deceasedCandidates != null && !deceasedCandidates.isEmpty()){ %>
		                        <tr>
											<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
											<td valign="top">
									    		<fieldset style="border: 1px solid #093C69; padding: 15px">
		                           		<legend><b><ivu:int key="Sitzverteilung_KandidatenNichtWaehlbar"/></b></legend>
			                            	<ol start="1">
			                            		<% for (String deceasedCandidate: deceasedCandidates) { %>
					                            	<li style="margin-bottom: 15px;">
					                            	  <%= ClientHelper.forHTML(deceasedCandidate) %>
			                                    </li>
                                       	<% } %>
				                           </ol>
					                      </fieldset>	
											</td>
											<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
										</tr>
										<tr>
											<td height="10" colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1" /></td>
										</tr>
                           <% }
	                        if (wahlInfo.isEK()) {
                            	List<Gebiet> provinces = appBean.getProvinces(); %>
		                        <tr>
									<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
									<td valign="top">
								   		<fieldset style="border: 1px solid #093C69; padding: 15px">
		                        	   		<legend><b><ivu:int key="Sitzverteilung_VoteValues"/></b></legend>
											<table width="<%=breite %>" cellspacing="2" cellpadding="4"
												border="0" class="hgweiss">
												<tbody>
													<tr class="hgformular">
														<td colspan="2">
															<ivu:int key="GebietModel.Klartext_Bundesland"/>
														</td>
														<td style="width: 100px; text-align: center;">
															<ivu:int key="Vote_Values"/>
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
													<% 
													int i = 1;
	                                                for (Gebiet province : provinces) {
														%>
														<tr height="20" class='<%=i < 1 ? "hgeeeeee" : "hgweiss"%>'>
															<td style="width: 3%;">
																<ivu:nc value="<%= String.valueOf(province.getNummer()) %>" />
															</td>
															<td style="width: 250px;">
																<ivu:nc value="<%= province.getName() %>" />
															</td>
															<td style="text-align: center;">
																<div style="width: 60px; text-align: right;">
																	<ivu:nc value="<%= String.valueOf(province.getVoteValue()) %>"/>
																</div>
															</td>
															<td />
														</tr><%
														i = -i;
												    } %>
												</tbody>
											</table>
					                    </fieldset>	
									</td>
									<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
								</tr>
								<tr>
									<td height="10" colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1" /></td>
								</tr>
                           <% }
                           List<String> besondList = besonderheiten.getBesonderheiten();
	                        if (besondList != null && !besondList.isEmpty()){ %>
		                        <tr>
											<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
											<td valign="top">
									    		<fieldset style="border: 1px solid #093C69; padding: 15px">
		                           		<legend><b><ivu:int key="Sitzverteilung_Besonderheiten"/></b></legend>
			                            	<ul>
			                            		<% for (String besond: besondList) { %>
					                            	<li style="margin-bottom: 15px;">
					                            	  <%= ClientHelper.forHTML(besond) %>
			                                    </li>
                                       	<% } %>
				                           </ul>
					                      </fieldset>	
											</td>
											<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
										</tr>
										<tr>
											<td height="10" colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1" /></td>
										</tr>
                           <% } %>
									
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</body>
</html>
					
