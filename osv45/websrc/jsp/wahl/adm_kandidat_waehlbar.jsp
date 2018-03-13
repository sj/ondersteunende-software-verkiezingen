<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode" %>
<%@ page import="de.ivu.wahl.WahlInfo" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.GebietsBaum" %>
<%@ page import="de.ivu.wahl.modell.GebietModel" %>
<%@ page import="de.ivu.wahl.modell.GebietInfo" %>
<%@ page import="de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatenListe" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBean" %>
<%@ page import="de.ivu.wahl.client.beans.NavigationBean" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.auswertung.erg.sv.kandidat.KandidatInfo"%>
<%@ page import="de.ivu.wahl.modell.ejb.Personendaten"%>
<%@ page import="de.ivu.wahl.modell.ejb.Listenkandidatur"%>
<%@ page import="de.ivu.wahl.modell.PersonendatenKonstanten.Geschlecht"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="java.util.List"%>
<%--
 *******************************************************************************
 * Namesliste der Kandidaten alphabetisch geordnet
 *
 * author:  mur@ivu.de  Copyright (c) 2008 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<% 
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "admKandidatWaehlbar"; //$NON-NLS-1$
 
 Collection<Personendaten> personendatenListe = appBean.getPersonenAlphabetisch(); 
 String formurl = ClientHelper.generateURL(request, null, "adm_Kandidaten_waehlbar", -1, true);
 String titel = BundleHelper.getBundleString("Kandidat_waehlbar_titel");  
 String breite = "100%";
 WahlInfo wahlInfo = WahlInfo.getWahlInfo();
%>
<html>
	<head>
		<title><%=titel%></title>
		<link rel="stylesheet"
			href="<%= request.getContextPath() %>/css/wahl2002.css" />
		<style type="text/css">
      		td {
      			font-size: 10px;
				line-height: 15px;
			}

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
		<div class="hggrau" style="height: 19px; width: 100%; text-indent: 5px; line-height: 18px;">
			<%=titel%>
		</div>
		<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
			&nbsp;
		</div>
		<ivu:form name="appstate" action="<%= formurl %>" >
	       <table width="<%=breite %>" cellspacing="0" cellpadding="0" border="0"
				align="center" class="hghell">
				<tbody>
					<tr>
						<td valign="top">
							<table width="<%=breite %>" cellspacing="0" cellpadding="0" border="0"
								class="hghell">
								<tbody>
									<tr>
										<td colspan="3"><img height="10" width="1" alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" /></td>
									</tr>
									<tr>
										<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
										<td valign="top">
											<table width="<%=breite %>" cellspacing="0" cellpadding="1" border="0">
												<tbody>
													<tr class="hgrot">
														<td valign="top">
															<table width="<%=breite %>" cellspacing="0" cellpadding="0"
																border="0" class="hgweiss">
																<tbody>
     																<%	if(WahlModel.STATE_CALCULATION_SUCCESSFUL == wahlInfo.getStatus() ){ %>
																		<tr class="hgeeeeee">
																			<td height="20" width="15">
																				&nbsp;
																			</td>
																			<td>
																				<br/>
																				<ivu:int key="Sitzverteilung_bereits_erfolgt"></ivu:int><br/>
																				<ivu:int key="Sitzverteilung_bereits_erfolgt_Tod_Kandidat"></ivu:int><br/>
																				<ivu:int key="Sitzverteilung_bereits_erfolgt_EML_Neu"></ivu:int><br/><br/>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr> 
     																
     																<% } 
     																if (personendatenListe != null && !personendatenListe.isEmpty()) { %>
																		<tr class="hgeeeeee">
																			<td width="5" height="20">
																				&nbsp;
																			</td>
																			<td><%
																	  			char letter = '0';
																				Iterator<Personendaten> iterator = personendatenListe.iterator();
																	    		while (iterator.hasNext()) {
																					Personendaten personendaten = iterator.next();
																	      			String nachname = personendaten.getNachname();
																	      			char firstLetter = ClientHelper.getAnfBuchstabeOhneUmlaut(nachname.charAt(0)); //Anfangsbuchstabe
																	      			if (letter != firstLetter && personendaten.hasListenkandidatur()) {
																	        			letter = firstLetter;%>
																					<a href="#<%= letter %>">[<%=letter%>]</a><%
																	  				}
																	    		}%>
																			</td>
																			<td width="5">
																				&nbsp; 
																			</td>
																		</tr>
																		<tr>
																			<td class="hgrot" colspan="3"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
																		</tr>
																		<tr>
																			<td colspan="3"><img height="10" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
																		</tr>
																		<tr>
																			<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
																			<td valign="top">
																				<table width="<%=breite %>" cellspacing="2" cellpadding="4"
																					border="0" class="hgweiss">
																					<tbody>
																					<% if (WahlModel.STATE_CALCULATION_SUCCESSFUL != wahlInfo.getStatus()) { %>
																							<tr>
																								<td height="30" align="right" colspan="4">
																									<input id="box2a" style="cursor:pointer" type="submit" name="kandidaten_waehlbar" value="<%= BundleHelper.getBundleString("Speichern") %>"/>
																								</td>
																							</tr>
																						<% } %>
																					
																					
																						<%
																						boolean firstTime = true; //Flag für den ersten Aufruf
																	    				char letterArea = '0';
																	    				int i = 1;
																	    				iterator = personendatenListe.iterator();
																			    		while (iterator.hasNext()){
																							Personendaten personendaten = iterator.next();
																							if (personendaten.hasListenkandidatur()) {
																								List<Integer> listen = personendaten.getListenkandidaturenGruppenSchluesselSortiert(); 																							
																								String nachname = personendaten.getNachname();
																		      					char firstLetter = ClientHelper.getAnfBuchstabeOhneUmlaut(nachname.charAt(0)); //Anfangsbuchstabe
																		      					String anker = "";
																          						int colspan = 5;
																						    	if (letterArea != firstLetter) {
																						      		letterArea = firstLetter;
																						        	String name = "name='" + firstLetter + "'";
																						        	if (firstTime) {
																		   								anker = "";
																		          						firstTime = false;
																		        					} else {
																		          						anker = "<a style='text-decoration:none' href='#oben'>[<img src='" + request.getContextPath()+ "/img/icon/pfeil_oben.gif' width='16' height='18' border='0' alt=''>"+ BundleHelper.getBundleString("NachOben") +" ]</a>"; %>
																										<tr>
																											<td colspan="<%=colspan%>" height="30" align="right">
																												<a <%=name%>><%=anker%></a>
																											</td>
																										</tr><%
																									}%>
																									<tr class="hgformular">
																										<td style="width: 398px;">
																											<ivu:int key="Kandidat_waehlbar_kandidat"/>
																										</td>
																										<td style="width: 150px;">
																											<ivu:int key="Kandidat_waehlbar_liste"/>
																										</td>
																										<td style="width: 334px;">
																											<ivu:int key="Kandidat_waehlbar_wohnort"/>
																										</td>
																										<td style="text-align: right;">
																											<ivu:int key="Kandidat_waehlbar_waehlbar"/>
																										</td>
																									</tr><%
																								}%>
																								<tr class='<%=i < 1 ? "hgeeeeee" : "hgweiss"%>'>
																									<td style="width: 398px;">
																										<ivu:nc value="<%= personendaten.getPraefix() %>" /> <ivu:nc value="<%= personendaten.getNachname() %>" />, <ivu:nc value="<%= personendaten.getInitialen() %>"/> <%=personendaten.getVorname()  != null ? "("+personendaten.getVorname()+")" :""%>
																									</td>
																									<td style="width: 150px;">
																										<ivu:nc value="<%=ClientHelper.intsToString(listen) %>" />
																									</td>
																									<td style="width: 206px;">
																										<ivu:nc value="<%= personendaten.getWohnort() %>"/><%if (personendaten.getLand()!= null ){%> (<%= personendaten.getLand() %>)<% } %>
																									</td>
																									<td style="text-align: right;">
																										<% if (WahlModel.STATE_CALCULATION_SUCCESSFUL == wahlInfo.getStatus()) { %>
																											<%if (personendaten.isBenennbar()){%>
																											  -
																											<% }else { %>
																											  <img class="c9" src="<%= request.getContextPath() %>/img/icon/achtungRot.gif" alt="<%=BundleHelper.getBundleString("Kandidat_waehlbar_waehlbar")%>" />
																										<%	}
																										} else { %>
																											<input type="checkbox" name="<%=ApplicationBeanKonstanten.PREFIX + personendaten.getID_Personendaten() %>" <%=personendaten.isBenennbar() ? "" : "checked=\"checked\""  %> />
																										<% } %>
																									</td>
																								</tr><%
																								i = -i;
																							}
																						}%>
																						<% if (WahlModel.STATE_CALCULATION_SUCCESSFUL != wahlInfo.getStatus()) { %>
																							<tr>
																								<td height="30" align="right" colspan="4">
																									<input id="box2a" style="cursor:pointer" type="submit" name="kandidaten_waehlbar" value="<%= BundleHelper.getBundleString("Speichern") %>"/>
																								</td>
																							</tr>
																						<% } %>
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
																	<% } %>
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
										<td height="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1" /></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</ivu:form>
	</body>
</html>
