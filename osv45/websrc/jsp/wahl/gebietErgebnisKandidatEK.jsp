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
<%@ page import="de.ivu.wahl.AnwContext"%>
<%@ page import="de.ivu.wahl.GebietsBaum"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.auswertung.AuswertungHandling"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten"%>
<%@ page import="de.ivu.wahl.client.beans.RepositoryPropertyHandler"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.eingang.GUIEingangMsg.Kandidat"%>
<%@ page import="de.ivu.wahl.eingang.GUIEingangMsg"%>
<%@ page import="de.ivu.wahl.eingang.GUIEingangMsgEK"%>
<%@ page import="de.ivu.wahl.modell.BasicEingangMsg"%>
<%@ page import="de.ivu.wahl.modell.BasicEingangMsg.Gruppenergebnis"%>
<%@ page import="de.ivu.wahl.modell.GebietInfo"%>
<%@ page import="de.ivu.wahl.modell.GruppeGebietsspezifischGruppeComposite"%>
<%@ page import="de.ivu.wahl.modell.GruppeKonstanten"%>
<%@ page import="de.ivu.wahl.modell.PersonendatenModel"%>
<%@ page import="de.ivu.wahl.modell.StimmergebnisModel"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<jsp:directive.page import="org.apache.log4j.Logger"/>

<%--
 *******************************************************************************
 * Gebietsergebnis
 * Stimmanteile aller Parteien und Wählergruppen
 *
 * Enthält die Prüfung, ob eine Wahleinheit zum Ausbleiben oder zur Nachwahl markiert ist.
 * Hat der Anwender nicht das entsprechende Recht, erhält er den entsprechenden Hinweis
 *
 * author:  mur@ivu.de bae@ivu.de cos@ivu.de Copyright (c) 2004-10 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="eingabeBean" scope="session" class="de.ivu.wahl.client.beans.EingabeBean" />
<jsp:useBean id="repHandler" scope="session" class="de.ivu.wahl.client.beans.RepositoryPropertyHandler" />
<%
   String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
   WahlInfo wahlInfo = appBean.getWahlInfo();
   Logger log = Logger.getLogger("jsp.gebietErgebnisKandidatEK");

   GebietsBaum gebietsBaum = appBean.getGebietsBaum();
   GebietInfo rootInfo = (GebietInfo)gebietsBaum.getWurzel().getUserObject();
   int gebietsArt = ClientHelper.getLevel(request, rootInfo.getGebietsart());
   int nr = ClientHelper.getGebietNr(request, rootInfo.getNummer());  
   String breite ="100%";

   AuswertungHandling ah = appBean.getAuswertungHandling();

   // Ergebnis abholen
   DefaultMutableTreeNode node = gebietsBaum.getGebietsNode(gebietsArt, nr);
   GebietInfo gebietInfo = node != null ? (GebietInfo)node.getUserObject() : rootInfo;
   
   AnwContext anwContext = appBean.getAnwContext(); 
   
   NumberFormat nf = NumberFormat.getNumberInstance();
   nf.setMaximumFractionDigits(ApplicationBeanKonstanten.MAXIMUM_DIGET_PROZ);
   nf.setMinimumFractionDigits(ApplicationBeanKonstanten.MINIMUM_DIGET_PROZ);
    
   GUIEingangMsgEK msg = eingabeBean.getGUIMsgEK(request, gebietInfo, true);

   boolean isReferendum = wahlInfo.isReferendum();
   String helpKey = "gebietErgKand";
   %>
<html>
<head>
   <title><ivu:int key="XXX_1"/> </title>
   <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
      <style type="text/css">
      td {font-size:10px; line-height:15px}
      </style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="hgeeeeee">
<%@include file="/jsp/fragments/print_and_help_row.jspf"%>
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr>
      <td colspan="3" class="hgschwarz"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td class="hggrau">&nbsp;</td>
      <td class="hggrau" height="19" colspan="2">
  	  <ivu:int key="Gebiet_Ergebnis_Kandidat_titel"/> <%= StringUtils.isEmpty(SystemInfo.getSystemInfo().getInputmodusCompleteKlartext()) ? "" : "(" + SystemInfo.getSystemInfo().getInputmodusCompleteKlartext() + ")" %>
      </td>
   </tr>
   <tr>
      <td colspan="3" class="hgschwarz"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td colspan="3" class="hghell"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
   </tr>
   <tr>
      <td width="10"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
      <td valign="top" colspan="2">
   		<table border="0" cellspacing="0" cellpadding="1" width="99%">
            <tr class="hgrot">
               <td valign="top">
                  <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
                     <tr class="hgeeeeee">
                        <td width="5" height="18">&nbsp;</td>
                        <td><b><%=ClientHelper.forHTML(gebietInfo.getCompleteDisplay(", "))%></b></td>
                        <td align="right"><b><%= wahlInfo.getWahlName() %></b></td>
                        <td width="5" height="18">&nbsp;</td>
                     </tr>
                     <tr>
                         <td colspan="4" class="hgrot"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                     </tr>
                     <tr>
                        <td colspan="4"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
                     </tr>
                     
                     <tr>
                        <td width="5"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                        <td colspan="2" valign="top">
                           <table border="0" width="<%= breite %>" cellspacing="2" cellpadding="4" align="center">
                              <tr class="hgformular">
                                 <td valign="top" rowspan="2" align="center" colspan="4">
                                    <strong><ivu:int key="Subjekt"/></strong>
                                 </td>
                                 <td valign="top" colspan="4" align="center">
                                    <strong><ivu:int key="Stimmen"/></strong>
           	                     </td>
                              </tr>
                              <tr class="hgformular">
	                              <td class="einrue"><div class="einrue"><b><ivu:int key="Anzahl"/></b></div></td>
	                              <td class="einrue" nowrap="nowrap"><div class="einrue"><b><ivu:int key="AnzahlGewichtet"/></b></div></td>
                                  <td valign="top" align="center"><strong>%</strong></td>
                                  <td valign="top" align="center">&nbsp;</td>
                              </tr>
                              <%-- parteien  --%>
                              <%
                              if (msg != null){
	                              int j = 1;
	                              String aktuelleKategorie = "";
	                              for (GUIEingangMsgEK.GruppendatenEK gErg : msg.getGruppendaten().values()){
	                                int gruppenposition = gErg.getPosition();
	                                String kategorie = gErg.getKategorie();
	                                boolean isSmallFontSize = gErg.isSmallFontSize();
	                                boolean isVisible = gErg.isVisibleInOverview();
	                                if (gruppenposition < 0){
	                                	if (isVisible && !kategorie.equals(aktuelleKategorie)){ %>
			                              	<tr class="<%= j > 0 ?"hgeeeeee":"hgweiss" %>">
				                              	<td colspan="5" height="20"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" ></td>
			                                </tr><%
		                              		aktuelleKategorie = kategorie;
		                              	}%>
			                              <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>"<%= isVisible ? "" : " style=\"display:none\"" %>>
		                                 	<td colspan="4"><b><%= isSmallFontSize ? "<small>":"" %><%= gErg.getName() %><%= isSmallFontSize ? "</small>":"" %></b></td>
		                                    <td class="einrue"><div class="einrue"><b><%=ClientHelper.getStimmanzahlString(msg.getGruppenstimmen(gruppenposition), ClientHelper.DF)%></b></div></td>
		                                    <td class="einrue"><div class="einrue"><b><%=gErg.hasWeightedValue() ? ClientHelper.getStimmanzahlString(msg.getGruppenstimmenGewichtet(gruppenposition), ClientHelper.DF) : "&nbsp;"%></b></div></td>
		                                    <td width="35px" align="right"><b><%=ClientHelper.getStimmProzentString(gErg.getStimmenprozent(), nf )%></b></td>
		                                    <td><%=gErg.getHelptext()%></td>
 			                              </tr> <% 
 			                         } else {
 			                         	j=-1;
 			                            Map<Integer, GUIEingangMsg.Kandidat> kandidaten = gErg.getKandidaten();
		                              	int size = kandidaten.size() +1 ;
		                              	Iterator<Integer> kKey = kandidaten.keySet().iterator();
			                              	%>
			                              	<tr class="hgweiss">
				                              	<td colspan="8" height="20"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" ></td>
			                                 </tr>
			                              	<tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
				                              	<td><b><%= gErg.getPosition() %></b></td>
				                              	<td colspan="2"><b><%= gErg.getName() %></b></td>
				                              	<td width="3%" align="center"><span style="background-color:<%=gErg.getFarbe()%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			                                    <td class="einrue"><div class="einrue"><b><%=ClientHelper.getStimmanzahlString(msg.getGruppenstimmen(gruppenposition), ClientHelper.DF)%></b></div></td>
			                                    <td class="einrue"><div class="einrue"><b><%=ClientHelper.getStimmanzahlString(msg.getGruppenstimmenGewichtet(gruppenposition), ClientHelper.DF)%></b></div></td>
			                                    <td align="right"><b><%=ClientHelper.getStimmProzentString(gErg.getStimmenprozent(), nf )%></b></td>
			                                    <td>&nbsp;</td>
			                                 </tr><%
		                              	 // Kandidatenzeilen
			                             while (kKey.hasNext()){
			                             	j=-j;
			                                GUIEingangMsg.Kandidat kandidat =  kandidaten.get(kKey.next());
			                                int listenposition = kandidat.getListenposition();
		                              		%>
			                             	<tr >
			                              	  	<td class="hgweiss" width="5%">&nbsp;</td>
			                              	  	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>"><%= gruppenposition %>.<%= listenposition %></td>
			                             		<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>"><%= kandidat.getName() %></td>
			                              	  	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">&nbsp;</td>
			                              	  	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %> einrue"><div class="einrue"><span><%= ClientHelper.getStimmanzahlString(msg.getStimmen(gruppenposition, listenposition), ClientHelper.DF) %></span></div></td>
			                              	  	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %> einrue"><div class="einrue"><span><%= ClientHelper.getStimmanzahlString(msg.getStimmenGewichtet(gruppenposition, listenposition), ClientHelper.DF) %></span></div></td>
			                              	  	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">&nbsp;</td>
			                                    <td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">&nbsp;</td>
			                              	</tr><%
			                             }
		                          	}
	                                if (isVisible) {
	                                	j=-j;
	                                }
                                 }
                              }
                              %>
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
      </td>
   </tr>
</table>
</body>
</html>