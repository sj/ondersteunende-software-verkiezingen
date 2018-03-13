<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="de.ivu.wahl.WahlInfo" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.GebietsBaum" %>
<%@ page import="de.ivu.wahl.modell.GebietModel" %>
<%@ page import="de.ivu.wahl.modell.GebietInfo" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBean" %>
<%@ page import="de.ivu.wahl.client.beans.NavigationBean" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.modell.ejb.Gebiet"%>
<%--
 *******************************************************************************
 * List of all provinces with the correspondig vote values
 *
 * author:  jon@ivu.de  Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<% 
 String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
 String helpKey = "admVoteValues"; //$NON-NLS-1$
 
 Collection<Gebiet> provincesListe = appBean.getProvinces();
 
 String formurl = ClientHelper.generateURL(request, null, AdministrationBean.CMD_ADM_VOTE_VALUES, -1, true);
 String titel = BundleHelper.getBundleString("Vote_Values_titel"); //$NON-NLS-1$ 
 String breite = "100%"; //$NON-NLS-1$
 WahlInfo wahlInfo = WahlInfo.getWahlInfo();

 String confirmationMsg = null;
 if (admBean._confirmationMsg != null && !admBean._confirmationMsg.isEmpty()) {
    confirmationMsg = admBean._confirmationMsg;
    admBean._confirmationMsg = null;
 }
%>

<%@ page import="de.ivu.wahl.client.beans.AdministrationBean"%><html>
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
		<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/osv.js"></script>
		<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
	</head>
	<jsp:element name="body">
	<jsp:attribute name="onload">sc();</jsp:attribute>
	<jsp:attribute name="class">hghell</jsp:attribute>
	<jsp:attribute name="topmargin">0</jsp:attribute>
	<jsp:attribute name="marginwidth">0</jsp:attribute>
	<jsp:attribute name="marginheight">0</jsp:attribute>
	<jsp:body>   
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
	       <table width="<%=breite %>" cellspacing="0" cellpadding="0" border="0" align="center" class="hghell">
				<tbody>
					<tr>
						<td valign="top">
							<table width="<%=breite %>" cellspacing="0" cellpadding="0" border="0" class="hghell">
								<tbody>
									<tr>
										<td colspan="3"><img height="10" width="1" alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" /></td>
									</tr>
									<tr>
										<td width="10"><img height="1" width="1" src="<%= request.getContextPath() %>/img/icon/blind.gif" alt="" /></td>
										<td valign="top">
											<table width="<%=breite %>" cellspacing="0" cellpadding="1" border="0">
												<tbody>
													<% if (confirmationMsg != null){%>
														<tr>
															<td valign="top" height="30" colspan="3">
																<div style="height: 19px; width: 100%; text-indent: 5px; line-height: 18px;">
																	<p style="color:green;"><b><%= ClientHelper.forHTML(confirmationMsg) %></b></p>
																</div>
															</td>
														</tr>
													<% } %>
													<tr class="hgrot">
														<td valign="top">
															<table width="<%=breite %>" cellspacing="0" cellpadding="0"
																border="0" class="hgweiss">
																<tbody>
     																<% if(WahlModel.STATE_CALCULATION_SUCCESSFUL == wahlInfo.getStatus() ){ %>
																		<tr class="hgeeeeee">
																			<td height="20" width="15">
																				&nbsp;
																			</td>
																			<td>
																				<br/>
																				<ivu:int key="Sitzverteilung_bereits_erfolgt_Vote_Values_1"></ivu:int><br/>
																				<ivu:int key="Sitzverteilung_bereits_erfolgt_Vote_Values_2"></ivu:int><br/>
																				<ivu:int key="Sitzverteilung_bereits_erfolgt_Vote_Values_3"></ivu:int><br/><br/>
																			</td>
																			<td width="5">
																				&nbsp;
																			</td>
																		</tr>
     																<% } %>
     																<tr class="hgeeeeee">
																		<td width="5" height="20">
																			&nbsp;
																		</td>
																		<td>
																			<b><ivu:int key="Vote_Values_table_header"/></b>
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
																					<tr class="hgformular">
																						<td colspan="2">
																							<ivu:int key="GebietModel.Klartext_Bundesland"/>
																						</td>
																						<td align="center" style="width: 100px;">
																							<ivu:int key="Vote_Values"/>
																						</td>
																					</tr>
																					<% }
																					int i = 1;
																					int index = 0;
																    				for (Gebiet gebiet : provincesListe) {
																    					String inputId = "id_" + index + "_0"; //$NON-NLS-1$ //$NON-NLS-2$
   																	      				%>
   																							<tr class='<%=i < 1 ? "hgeeeeee" : "hgweiss"%>'>
   																								<td style="width: 3%;" height="20">
   																									<ivu:nc value="<%= String.valueOf(gebiet.getNummer()) %>" />
   																								</td>
   																								<td>
   																									<ivu:nc value="<%= gebiet.getName() %>" />
   																								</td>
   																								<td align="center">
   																									<% if (WahlModel.STATE_CALCULATION_SUCCESSFUL == wahlInfo.getStatus()) { %>
   																										<ivu:nc value="<%= String.valueOf(gebiet.getVoteValue()) %>"/>
   																									<%	
   																									} else {
                                                                                                       %>
   																										<input id="<%=inputId%>" class="erg" type="text" name="<%=ApplicationBeanKonstanten.PREFIX + gebiet.getID_Gebiet() %>" value="<%= String.valueOf(gebiet.getVoteValue()) %>" size="10" onkeypress="return navigateEnter('<%=inputId%>', event)" onkeydown="return navigateCursor('<%=inputId%>', event)" />
   																									<% } %>
   																								</td>
   																							</tr><%
   																							i = -i;
   																							index++;
   																					     } %>
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
																</tbody>
															</table>
														</td>
													</tr>
                                                   <% if (WahlModel.STATE_CALCULATION_SUCCESSFUL != wahlInfo.getStatus()) { %>
														<tr>
															<td height="30" align="center" colspan="3">
																<input id="id_<%=index%>_0" class="box2a" style="cursor:pointer" type="submit" name="vote_values" value="<%= BundleHelper.getBundleString("Speichern") %>"  onkeydown="return navigateCursor('id_<%=index%>_0', event)" />
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
										<td height="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</ivu:form>
	</jsp:body>
	</jsp:element>
</html>
