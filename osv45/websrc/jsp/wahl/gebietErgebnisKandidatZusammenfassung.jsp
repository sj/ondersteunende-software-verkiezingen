<%@ page import="java.util.Locale"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.TreeMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.StringReader"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.GebietsBaum"%>
<%@ page import="de.ivu.wahl.AnwContext"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten"%>
<%@ page import="de.ivu.wahl.auswertung.AuswertungHandling"%>
<%@ page import="de.ivu.wahl.auswertung.erg.CandidateVotesPerRegion"%>
<%@ page import="de.ivu.wahl.auswertung.erg.PartyWithCandidates"%><html>
<%@ page import="de.ivu.wahl.auswertung.erg.ResultSummary"%>
<%@ page import="de.ivu.wahl.modell.BasicEingangMsg.Gruppenergebnis"%>
<%@ page import="de.ivu.wahl.modell.BasicEingangMsg"%>
<%@ page import="de.ivu.wahl.modell.GebietInfo"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.modell.StimmergebnisModel"%>
<%@ page import="de.ivu.wahl.modell.PersonendatenModel"%>
<%@ page import="de.ivu.wahl.modell.GruppeGebietsspezifischGruppeComposite"%>
<%@ page import="de.ivu.wahl.modell.GruppeKonstanten"%>
<%@ page import="de.ivu.wahl.modell.ejb.Gebiet"%>
<%@ page import="de.ivu.wahl.eingang.GUIEingangMsg"%>
<%@ page import="de.ivu.wahl.eingang.GUIEingangMsg.Kandidat"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<jsp:directive.page import="org.apache.log4j.Logger"/>

<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="eingabeBean" scope="session" class="de.ivu.wahl.client.beans.EingabeBean" />
<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "gebietErgKand"; //$NON-NLS-1$

   WahlInfo wahlInfo = appBean.getWahlInfo();
   Logger log = Logger.getLogger("jsp.gebietErgebnis");

   GebietsBaum gebietsBaum = appBean.getGebietsBaum();
   GebietInfo rootInfo = (GebietInfo)gebietsBaum.getWurzel().getUserObject();
   int gebietsArt = ClientHelper.getLevel(request, rootInfo.getGebietsart());
   int nr = ClientHelper.getGebietNr(request, rootInfo.getNummer());  
   String breite ="100%";
   
   DefaultMutableTreeNode node = gebietsBaum.getGebietsNode(gebietsArt, nr);
   GebietInfo gebietInfo = node != null ? (GebietInfo)node.getUserObject() : rootInfo;
%>
<html>
<head>
   <title><ivu:int key="XXX_1"/> </title>
   <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/wahl2002.css">
      <style type="text/css">
      td {font-size:10px; line-height:15px}
      </style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="hgeeeeee">
<%@include file="/jsp/fragments/print_and_help_row.jspf"%>
<table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr>
      <td colspan="3" class="hgschwarz"><img alt="" src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td class="hggrau">&nbsp;</td>
      <td class="hggrau" height="19" colspan="2"><ivu:int key="Gebiet_Ergebnis_Kandidat_titel"/> <%= wahlInfo.isReferendum() || StringUtils.isEmpty(SystemInfo.getSystemInfo().getInputmodusCompleteKlartext()) ? "" : "(" + SystemInfo.getSystemInfo().getInputmodusCompleteKlartext() + ")" %></td>
   </tr>
   <tr>
      <td colspan="3" class="hgschwarz"><img alt="" src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td colspan="3" class="hghell"><img alt="" src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="5"></td>
   </tr>
   <tr>
      <td width="10"><img alt="" src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="1"></td>
      <td valign="top" colspan="2">
   		<table border="0" cellspacing="0" cellpadding="1" width="99%">
         <%
           if (!wahlInfo.isWahlVollstaendig()){
         %>  
			<tr>
               	<td valign="top">
               		<fieldset style="border: 1px solid #093C69; padding: 15px">
             			<legend><b><%=gebietInfo.getCompleteDisplay(" - ")%></b></legend><br /> 
				             <table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0">
					              <tr>
					               		<td colspan="3"><img src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="10"></td>
					              </tr>
					              <tr>
					               		<td width="10"><img src="<%=request.getContextPath()%>/img/icon/blind.gif" width="1" height="1"></td>
					               		<td valign="top"> 
						            		<p><ivu:int key="Noch_Nicht_Alle_Ergebniseingaenge_Kandidaten_Zusammenfassung"/></p>
	                					</td>
               							<td width="10">&nbsp;</td>
              					  </tr>
             				</table>
            			</fieldset>
            		</td>
            	</tr>			
                  
       		<%
			} else { 
				ResultSummary rs = eingabeBean.getResultSummary(request, gebietInfo);
			    int gebieteSize = rs.getGebiete().size();
			%>       
	             <tr class="hgrot">
	               	<td valign="top">
	                    <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
	                     <tr class="hgeeeeee">
	                        <td width="5" height="18">&nbsp;</td>
	                        <td><b><%=gebietInfo.getCompleteDisplay(", ")%></b></td>
	                        <td width="5" height="18">&nbsp;</td>
	                     </tr>
	                     <tr>
	                         <td colspan="3" class="hgrot"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
	                     </tr>
	                     <tr>
	                        <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
	                     </tr>
	                     <tr>
	                        <td>&nbsp;</td>
	                        <td colspan="2">
	                        	<% List<PartyWithCandidates> partysWithC = rs.getGruppenMitKandidaten();
	                               for(PartyWithCandidates partyWithCandidates : partysWithC){ %>
	                            	<a href="<%="#"+partyWithCandidates.getGruppenID() %>">[<%=partyWithCandidates.getGruppenName()%>]</a>
								<% }%>
							</td>
	                     </tr>
	                     <tr>
	                        <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
	                     </tr>
	                     <tr>
	                        <td width="5"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
	                        <td valign="top">
	                           <table border="0" width="<%= breite %>" cellspacing="2" cellpadding="4" align="center" >
	                              <%-- parteien  --%><%
	                              boolean first = true; 
	                              int partyPos = 1;
	                              int numOfParties = partysWithC.size();
	                              for(PartyWithCandidates partyWithCandidates : partysWithC){
	                            	int j = -1;
	                            	String anker = "<a style='text-decoration:none' href='#oben'>[<img src='"+request.getContextPath()+"/img/icon/pfeil_oben.gif' width='16' height='18' border='0' alt=''>" + BundleHelper.getBundleString("NachOben") + " ]</a>"; 
	                            	if (first) {
	                            		first = false;%>
		                            	<tr class="hgweiss">
				                          	<td colspan="<%= 3 + gebieteSize %>" height="20"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" ></td>
			                            </tr>
               						<%} else { %>
										<tr>
											<td colspan="<%= 3 + gebieteSize %>" height="45" align="right" style="vertical-align:bottom;"><a name="<%=partyWithCandidates.getGruppenID()%>" ><%=anker%></a></td>
										</tr>
	                             	<% } %>
	                              	<!--  Tableheader-->
	                              <tr class="hgformular">
	                                 <td valign="top" rowspan="2" align="center" colspan="2">
	                                    <strong><ivu:int key="Subjekt"/></strong>
	                                 </td>
	                                 <td valign="top" colspan="<%= gebieteSize +1 %>" align="center">
	                                    <strong><ivu:int key="Stimmen"/></strong>
	           	                     </td>
	                              </tr>
	                              <TR class="hgformular">
		                              	<td valign="top" align="center"><strong><ivu:int key="Summe"/></strong></td>
		                              <%for(Gebiet gebiet : rs.getGebiete()){ %>
		                              	<td valign="top" align="center"><strong><%= gebiet.getName() %></strong></td>
	                                  <% } %>
	                              	</TR>
	                               	<tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
		                              	<td><b><%= partyWithCandidates.getGruppenPosition() %></b></td>
		                              	<td><b><%= partyWithCandidates.getGruppenName() %></b></td>
		                              	<td><b><%= partyWithCandidates.getSumme() %></b></td>
		                              	<%for(Gebiet gebiet : rs.getGebiete()){ %>
			                              	<td><b><%= partyWithCandidates.getGruppenstimmeProGebiet(gebiet) %></b></td>
	                              		<% } %>
	                                </tr><%
	                                j = -j;
	                                int candidatePos = 1;
	                                int numOfCandidates = partyWithCandidates.getCandidateVotesPerRegion().size();
	                                for (CandidateVotesPerRegion candidateVotesPerRegion: partyWithCandidates.getCandidateVotesPerRegion()){
	                                	%>
	                             		<tr >
		                              	  	<td class="hgweiss" width="5%">&nbsp;</td>
		                              	  	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>"><%= candidateVotesPerRegion.getPersonenName()%></td>
		                             		<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>"><b><%= candidateVotesPerRegion.getSumme() %></b></td>
		                             		<%for(Gebiet gebiet : rs.getGebiete()){ %>
			                              		<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>"><%= candidateVotesPerRegion.getKandidatenstimmeProGebiet(gebiet) %></td>
			                              	<% } %>
		                              	</tr><%
		                              	j = -j;
		                              	if (candidatePos % 20 == 0 && (numOfCandidates - candidatePos >= 15 || partyPos == numOfParties)) { %>
		                              		<!-- repeat Tableheader after 20 candidates if at least 15 candidates follow or is last party -->
				                            <tr>
		                              	  	    <td class="hgweiss">&nbsp;</td>
		                              	  		<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">&nbsp;</td>
					                            <td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>" valign="top" align="center"><strong><ivu:int key="Summe"/></strong></td>
					                            <%for(Gebiet gebiet : rs.getGebiete()){ %>
					                            	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>" valign="top" align="center"><strong><%= gebiet.getName() %></strong></td>
				                                <% } %>
				                            </tr><%
			                              	j = -j; // invert background color again
		                              	}
	                                	candidatePos++;
	                              	}
	                              	partyPos++;
		                          } %>
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
           	<% } %>
            <tr>
               <td height="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
            </tr>
         </table>
      </td>
   </tr>
</table>
</body>
</html>