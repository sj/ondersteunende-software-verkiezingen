<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode" %>
<%@ page import="de.ivu.wahl.WahlInfo" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.GebietsBaum" %>
<%@ page import="de.ivu.wahl.anwender.Rechte"%>
<%@ page import="de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatenListe" %>
<%@ page import="de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatInfo"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBean" %>
<%@ page import="de.ivu.wahl.client.beans.Command" %>
<%@ page import="de.ivu.wahl.client.beans.NavigationBean" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.modell.GebietModel" %>
<%@ page import="de.ivu.wahl.modell.GebietInfo" %>
<%@ page import="de.ivu.wahl.modell.PersonendatenKonstanten"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%--
 *******************************************************************************
 * Namesliste alphabetisch geordnet
 *
 * author:  mur@ivu.de  Copyright (c) 2009 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<% 
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "gewKandAlpha"; //$NON-NLS-1$

 WahlInfo wahlInfo = appBean.getWahlInfo();
 String idErgebniseingang = wahlInfo.getWahl().getWurzelgebiet().getID_LetzterEingang();	 

 DefaultMutableTreeNode node = NavigationBean.getTreeNode(appBean,request.getParameter(ApplicationBeanKonstanten.NAVI_ANKER),true);
 GebietInfo gebietInfo = (GebietInfo)node.getUserObject();
   
 KandidatenListe kandidatenListe = appBean.getGewaehltKandidatenForGebietAlphabetisch(idErgebniseingang, gebietInfo.getID_Gebiet()); 
 String titel = kandidatenListe.getErgBezeichnung();  
 String breite = "100%";
 AnwContext anwContext = appBean.getAnwContext();
 boolean darfBerechnen = anwContext.checkRight(Rechte.R_SITZVERTEILUNG_BERECHNEN);
%>
<html>
	<head>
		<title><%=titel%></title>
		<link rel="stylesheet"
			href="<%= request.getContextPath() %>/css/wahl2002.css" />
		<style type="text/css">
			a:link {
				color: #330000;
			}
			
			a:hover {
				color: #330000;
				text-decoration: underline;
			}
			
			a:visited {
				color: #330000;
			}
		</style>
	</head>
	<body class="hghell">
		<%@include file="/jsp/fragments/print_and_help_div.jspf"%>
		<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
			&nbsp;
		</div>
		<div class="hggrau" style="height: 20px; width: 100%; text-indent: 5px; line-height: 18px;">
			<%=titel%>
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
								<tr>
							      <td colspan="3" class="hghell"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
							   </tr>
								<tr>
									<td width="10"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" height="1" width="1"></td>
									<td valign="top">
										<table width="<%=breite %>" cellspacing="0" cellpadding="1" border="0">
											<tbody>
												<tr class="hgrot">
													<td valign="top">
														<table class="hgweiss" border="0" cellpadding="0"
															cellspacing="0" width="<%=breite%>">
															<tbody>
																<% if (wahlInfo.getStatus() == WahlModel.STATE_METADATA_P5){ %>  
					                        				<tr class="hgeeeeee">
																		<td height="20" width="5">
																			&nbsp;
																		</td>
																		<td>
																			<ivu:int key="Sitzverteilung_Nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Kein_Ergebniseingang"/>
																		</td>
																		<td width="5">
																			&nbsp;
																		</td>
																	</tr>
						                             <%} else if (wahlInfo.getStatus() == WahlModel.STATE_NEW_RESULT){ %> 
																	<tr class="hgeeeeee">
																		<td height="20" width="5">
																			&nbsp;
																		</td>
																		<td>
																			<ivu:int key="Sitzverteilung_Nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Anstossen"/>
																		</td>
																		<td width="5">
																			&nbsp;
																		</td>
																	</tr>
																	<%if (darfBerechnen){ %>
																		<tr class="hgeeeeee">
																			<td height="20" width="5">
																				&nbsp;
																			</td>
																			<td>
																				<%
																				String url = "/osv?" + ClientHelper.workIs(Command.AUSW_SITZVERTEILUNG_GEBIET) + "&"+ClientHelper.getAllParameters(request, ApplicationBeanKonstanten.WORK);
																				%>
																				<ivu:form action="<%= url %>">
																					<ivu:int key="wechseln_zu_sitzverteilungsansicht"/>&nbsp;&nbsp;&nbsp;<input type="submit" id="box2" value="<%= ClientHelper.getBundleString("wechseln_zu_sitzverteilungsansicht_button") %>">
																				</ivu:form>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr>
																	<% }
						                             } else if (wahlInfo.getStatus() == WahlModel.STATE_CALCULATION_CONFLICT){ %> 
																	<tr class="hgeeeeee">
																		<td height="20" width="5">
																			&nbsp;
																		</td>
																		<td>
																			<ivu:int key="Sitzverteilung_Nicht_erfolgt"/> <ivu:int key="Sitzverteilung_Konflikt"/>
																		</td>
																		<td width="5">
																			&nbsp;
																		</td>
																	</tr>
																<% } else if (wahlInfo.getStatus() == WahlModel.STATE_CALCULATION_SUCCESSFUL && kandidatenListe.getKandidaten() != null && !kandidatenListe.getKandidaten().isEmpty()) { %>
																		<tr class="hgeeeeee">
																			<td width="5" height="20">
																				&nbsp;
																			</td>
																			<td><%
																	  			char letter = '0';
																	    		for (KandidatInfo kandidatInfo : kandidatenListe.getKandidaten()) {
																	      		String nachname = kandidatInfo.getNachname();
																	      		char firstLetter = ClientHelper.getAnfBuchstabeOhneUmlaut(nachname.charAt(0)); //Anfangsbuchstabe
																	      		if (letter != firstLetter) {
																	        			letter = firstLetter;%>
																						<a href="<%= "#"+letter %>">[<%=letter%>]</a>
																					<%
																	  				}
																	    		}%>
																			</td>
																			<td width="5">
																				&nbsp; 
																			</td>
																		</tr>
																		<tr>
											                         <td colspan="3" class="hgrot"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
											                     </tr>
																		<tr>
																			<td colspan="3"><img height="10" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
																		</tr>
																		<tr>
																			<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
																			<td valign="top">
																				<table width="<%=breite %>" cellspacing="2" cellpadding="4"
																					border="0" class="hgweiss">
																					<tbody><%
																						boolean firstTime = true; //Flag für den ersten Aufruf
																	    				char letterArea = '0';
																	    				int i = 1;
																	    				for (KandidatInfo kandidatInfo : kandidatenListe.getKandidaten()) {
																	      				String partei = ClientHelper.forHTML(kandidatInfo.getGruppennameLang());
																	      				String nachname = kandidatInfo.getNachname();
																	      				char firstLetter = ClientHelper.getAnfBuchstabeOhneUmlaut(nachname.charAt(0)); //Anfangsbuchstabe
																	      				String anker = "";
															          					int colspan = 4;
																					      if (letterArea != firstLetter) {
																					      	letterArea = firstLetter;
																					        	String name = "name='" + firstLetter + "'";
																					        	if (firstTime){
																									anker = "";
																									firstTime = false;%>
																		                   <tr>
																		                    	<td colspan="5" height="20" align="left" style="vertical-align:bottom; font-weight: bold; font-style: italic;"><%=letterArea%></td>
																		                   </tr><%
				                  														} else {
				                   															anker = "<a style='text-decoration:none' href='#oben'>[<img src='"+ request.getContextPath()+"/img/icon/pfeil_oben.gif' width='16' height='18' border='0' alt=''>" + BundleHelper.getBundleString("NachOben") + " ]</a>"; %>
																									<tr>
																										<td colspan="4" height="45" align="left" style="vertical-align:bottom; font-weight: bold; font-style: italic";"><%=letterArea%></td>
																										<td colspan="1" height="45" align="right" style="vertical-align:bottom;"><a <%=name%> ><%=anker%></a></td>
																									</tr><%
				                 															}%>
																								<tr class="hgformular">
																									<td style="width: 30%;">
																										<ivu:int key="Gewaehlte_Kandidaten"/>
																									</td>
																									<td style="width: 20%;">
																										<ivu:int key="Gewaehlte_Kandidaten_Wohnort"/>
																									</td>
																									<td style="width: 5%;">
																										<ivu:int key="Gewaehlte_Kandidaten_Listenplatz"/>
																									</td>
																									<td style="width: 30%;">
																										<ivu:int key="Gewaehlte_Kandidaten_Parteiname"/>
																									</td>
																									<td style="text-align: right;">
																										<ivu:int key="Gewaehlte_Kandidaten_Anzahl_Stimmen"/>
																									</td>
																								</tr>
																								<%
																								i = -1;
																							}%>
																							<tr class='<%= i > 0 ? "hgeeeeee" : "hgweiss"%>'  style="font-weight: normal;"> 
																								<td>
																									<%= kandidatInfo.getPraefix() %> <%= nachname %>, <%= kandidatInfo.getInitialen() %><%=kandidatInfo.displayGeschlecht()%><%= kandidatInfo.isBevorzugtGewaehlt() ?  "<b>&nbsp;*</b>" : ""%>
																								</td>
																								<td>
																									<%= kandidatInfo.getWohnort() %> <%= kandidatInfo.getLand() %>
																								</td>
																								<td>
																									<%= kandidatInfo.getListenplatz() %>
																								</td>
																								<td>
																									<%= partei %>
																								</td>
																								<td style="text-align: right;">
																									<%= ClientHelper.getStimmanzahlString(kandidatInfo.getStimmenAnzahl(), ClientHelper.DF) %>
																								</td>
																							</tr><%
																							i = -i;
																						}%>
																						
																						<tr>
																							<td height="30" align="right" colspan="4"
																								style="width: 128px;">
																								<a name="C" /><a href="#oben" style="text-decoration: none;">[<img height="18" width="16" border="0" alt="" src="<%= request.getContextPath() %>/img/icon/pfeil_oben.gif" /><%= BundleHelper.getBundleString("NachOben") %> ]</a>
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
																			<td colspan="3"><img height="10" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
																		</tr>
																	<%  } %>
															</tbody>
														</table>
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
							      <td colspan="3" class="hghell"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
							   </tr>
								<% if (wahlInfo.getStatus() == WahlModel.STATE_CALCULATION_SUCCESSFUL && kandidatenListe.getKandidaten() != null && !kandidatenListe.getKandidaten().isEmpty()) { %>
									<tr>
								      <td class="hghell"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" ></td>
								      <td colspan="2" class="hghell">(*) <ivu:int key="Gewaehlte_Kandidaten_bevorzugt"/></td>
								   </tr>
								<% } %>
								<tr>
									<td height="10" colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
								</tr>
							</tbody>

						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>

