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
<%@ page import="de.ivu.wahl.modell.BasicEingangMsg"%>
<%@ page import="de.ivu.wahl.modell.BasicEingangMsg.Gruppenergebnis"%>
<%@ page import="de.ivu.wahl.modell.ErgebniseingangKonstanten"%>
<%@ page import="de.ivu.wahl.modell.GebietInfo"%>
<%@ page import="de.ivu.wahl.modell.GruppeGebietsspezifischGruppeComposite"%>
<%@ page import="de.ivu.wahl.modell.GruppeKonstanten"%>
<%@ page import="de.ivu.wahl.modell.PersonendatenModel"%>
<%@ page import="de.ivu.wahl.modell.StimmergebnisModel"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
   String breite ="100%"; //$NON-NLS-1$

   WahlInfo wahlInfo = appBean.getWahlInfo();
   Logger log = Logger.getLogger("jsp.gebietErgebnisKandidat"); //$NON-NLS-1$

   GebietsBaum gebietsBaum = appBean.getGebietsBaum();
   GebietInfo rootInfo = (GebietInfo)gebietsBaum.getWurzel().getUserObject();
   int gebietsArt = ClientHelper.getLevel(request, rootInfo.getGebietsart());
   int nr = ClientHelper.getGebietNr(request, rootInfo.getNummer());  
   
   AuswertungHandling ah = appBean.getAuswertungHandling();

   // Ergebnis abholen
   DefaultMutableTreeNode node = gebietsBaum.getGebietsNode(gebietsArt, nr);
   GebietInfo gebietInfo = node != null ? (GebietInfo)node.getUserObject() : rootInfo;
   
   AnwContext anwContext = appBean.getAnwContext(); 
   
   NumberFormat nf = NumberFormat.getNumberInstance();
   nf.setMaximumFractionDigits(ApplicationBeanKonstanten.MAXIMUM_DIGET_PROZ);
   nf.setMinimumFractionDigits(ApplicationBeanKonstanten.MINIMUM_DIGET_PROZ);
    
   GUIEingangMsg msg = eingabeBean.getGUIMsg(request, gebietInfo, true);

   boolean isReferendum = wahlInfo.isReferendum();
   String helpKey = "gebietErgKand"; //$NON-NLS-1$
   if (isReferendum) {
   	   helpKey = "gebietErgRef"; //$NON-NLS-1$
   }
   boolean isErfassungseinheit = gebietInfo.isErfassungseinheit();
   boolean isFirstInput = false;
   if (gebietInfo.getStatusLetzterEingang() == ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK) {
      isFirstInput = true;
   }
   String urlToGebietEingang = ClientHelper.generateURL(request, ApplicationBeanKonstanten.GEBE, true);
   %>

<c:set var="isShowButtonToGebietEingang" value="<%= isReferendum && isErfassungseinheit && isFirstInput %>" scope="page"/>

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
      	<% if (isReferendum){ %>
      		<ivu:int key="Referendum_Ergebnis_titel"/>
      	<% } else { %>
      		<ivu:int key="Gebiet_Ergebnis_Kandidat_titel"/> <%= StringUtils.isEmpty(SystemInfo.getSystemInfo().getInputmodusCompleteKlartext()) ? "" : "(" + SystemInfo.getSystemInfo().getInputmodusCompleteKlartext() + ")" %>
      	<% } %>
      </td>
   </tr>
   <tr>
      <td colspan="3" class="hgschwarz"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td colspan="3" class="hghell"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
   </tr>
   <c:if test="${not empty confirmationText}">
   	  <tr>
         <td></td>
         <td style="color: green; padding-top: 20px; padding-bottom: 30px;">${confirmationText}</td>
         <td></td>
	  </tr>
   </c:if>
   <c:if test="${not empty infoText}">
   	  <tr>
         <td></td>
         <td style="color: red; padding-top: 20px; padding-bottom: 30px;">${infoText}</td>
         <td></td>
	  </tr>
   </c:if>
   <c:if test="${isShowButtonToGebietEingang}">
      <tr>
         <td></td>
         <td style="padding-top: 20px; padding-bottom: 30px;">
            <c:if test="${gebietInfo.vollstaendig}">
               <ivu:int key="NeueErsteingabeAbgeschlossen"/>
            </c:if>
      	    <c:if test="${!gebietInfo.vollstaendig}">
               <ivu:int key="ErsteingabeAbgeschlossen"/>
            </c:if>
            <ivu:a href="<%= urlToGebietEingang %>" id="box2a" style="cursor:pointer" target="_top"><%= BundleHelper.getBundleString("Zweiteingabe") %></ivu:a>
         </td>
         <td></td>
      </tr>
   </c:if>
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
                     
                     <% if (isReferendum){ %>
						 <tr>
	                    	<td colspan="4"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="15"></td>
	                     </tr>
						 <tr>
							<td width="5"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="15"></td>
                            <td valign="top" colspan="3">
								<h3><ivu:int key="Referendum"/>:</h3>	  	  		
						    </td>
						 </tr>
						 <tr>
							<td width="5"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                            <td valign="top" colspan="3">
								<b><%= ClientHelper.resizeString(repHandler.getProperty(Konstanten.KEY_REFERENDUM_TEXT), 1000) %></b>	  	  		
						    </td>
						 </tr>
						 <tr>
						 	 <td colspan="4"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="15"></td>
						 </tr>
					   <% } %>
                     
                     <tr>
                        <td width="5"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                        <td colspan="2" valign="top">
                           <table border="0" width="<%= breite %>" cellspacing="2" cellpadding="4" align="center">
                              <tr class="hgformular">
                                 <td valign="top" rowspan="2" align="center" colspan="4">
                                    <strong><ivu:int key="Subjekt"/></strong>
                                 </td>
                                 <td valign="top" colspan="3" align="center">&nbsp;
                                    <strong><ivu:int key="Stimmen"/></strong>
           	                     </td>
                              </tr>
                              <tr class="hgformular">
	                              <td valign="top" align="center"><strong><ivu:int key="Anzahl"/></strong></td>
                                  <td valign="top" align="center"><strong>%</strong></td>
                                  <td valign="top" align="center">&nbsp;</td>
                              </tr>
                              <%-- parteien  --%>
                              <%
                              if (msg != null){
	                              Map<Integer, GUIEingangMsg.Gruppendaten> gd =  msg.getGruppendaten();
	                              Iterator<Integer> gdKey = gd.keySet().iterator();
	                              int j = 1;
	                              String aktuelleKategorie = "";
	                              while (gdKey.hasNext()){
	                                GUIEingangMsg.Gruppendaten gErg = gd.get(gdKey.next());
	                                int gruppenposition = gErg.getPosition();
	                                String kategorie = gErg.getKategorie();
	                                boolean isSmallFontSize = gErg.isSmallFontSize();
	                                boolean isVisible = gErg.isVisibleInOverview();
	                                Gruppenergebnis gruppenergebnis = msg.getGruppenergebnis(gruppenposition);
	                                String gruppefehler = gruppenergebnis.getGruppefehler();
	                                if (gruppenposition < 0){
	                                	if (isVisible && !kategorie.equals(aktuelleKategorie)){ %>
			                              	<tr class="<%= (j > 0 && !isReferendum) ?"hgeeeeee":"hgweiss" %>">
				                              	<td colspan="7" height="20"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" ></td>
			                                </tr><%
		                              		aktuelleKategorie = kategorie;
		                              	}
	                                  	if (gruppefehler != null && isReferendum) { %>
	                                  		<tr id="row${position}_0_error">
                                      	     <td align="left" colspan="6">
                                      	       <table border="0" cellspacing="0" cellpadding="0">
                                      	         <tr>
                                      	             <td>
                                      	                <img src="<%= request.getContextPath() %>/img/icon/warnung.gif" width="20" height="20" alt="<%= BundleHelper.getBundleString("Warnung")%>" align="middle">
                                      	             </td>
                                      	             <td>
                                      	                <font color="red"><%= gruppefehler %></font>
                                      	             </td>
                                      	         </tr>
                                      	       </table>
                                      	   	 </td>
                                   	  	   </tr><%
	                                  	}%>
			                              <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>"<%= isVisible ? "" : " style=\"display:none\"" %>>
		                                 	<td colspan="4"><b><%= isSmallFontSize ? "<small>":"" %><%= gErg.getName() %><%= isSmallFontSize ? "</small>":"" %></b></td>
		                                    <td class="einrue"><div class="einrue"><b><%=ClientHelper.getStimmanzahlString(msg.getGruppenstimmen(gruppenposition), ClientHelper.DF)%></b></div></td>
		                                    <td width="35px" align="right"><b><%=ClientHelper.getStimmProzentString(gErg.getStimmenprozent(), nf )%></b></td>
		                                    <td><%=gErg.getHelptext()%></td>
 			                              </tr> <% 
 			                         } else {
 			                         	j=-1;
 			                            Map<Integer, GUIEingangMsg.Kandidat> kandidaten = gErg.getKandidaten();
		                              	int size = kandidaten.size() +1 ;
		                              	Iterator<Integer> kKey = kandidaten.keySet().iterator();
			                            if (!isReferendum){
			                              	%>
			                              	<tr class="hgweiss">
				                              	<td colspan="7" height="20"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" ></td>
			                                 </tr>
			                              	<tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
				                              	<td><b><%= gErg.getPosition() %></b></td>
				                              	<td colspan="2"><b><%= gErg.getName() %></b></td>
				                              	<td width="3%" align="center"><span style="background-color:<%=gErg.getFarbe()%>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
			                                    <td class="einrue"><div class="einrue"><b><%=ClientHelper.getStimmanzahlString(msg.getGruppenstimmen(gruppenposition), ClientHelper.DF)%></b></div></td>
			                                    <td align="right"><b><%=ClientHelper.getStimmProzentString(gErg.getStimmenprozent(), nf )%></b></td>
			                                    <td>&nbsp;</td>
			                                 </tr><%
			                              	 //Kandidatenzeilen
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
				                              	  	<td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">&nbsp;</td>
				                                    <td class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">&nbsp;</td>
				                              	</tr><%
				                             }
			                              } else { %>
			                              	<tr class="hgweiss">
				                              	<td colspan="7" height="20"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" ></td>
			                                 </tr>
			                              	<% while (kKey.hasNext()){
			                                    GUIEingangMsg.Kandidat kandidat =  kandidaten.get(kKey.next());
				                                int listenposition = kandidat.getListenposition(); %>
				                              	<tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
					                              	<td><b><%= gruppenposition %></b></td>
					                              	<td colspan="3"><b><%= kandidat.getReferendumNameCutOff() %></b></td>
					                              	<td class="einrue"><div class="einrue"><b><%=ClientHelper.getStimmanzahlString(msg.getGruppenstimmen(gruppenposition), ClientHelper.DF)%></b></div></td>
				                                    <td align="right"><b><%=ClientHelper.getStimmProzentString(gErg.getStimmenprozent(), nf )%></b></td>
				                                    <td><%=gErg.getHelptext()%></td>
				                                 </tr><%
			                              	}
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
                        <td height="5" colspan="4"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
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