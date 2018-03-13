<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.auswertung.erg.NavigationErgebnis" %>
<%@ page import="de.ivu.wahl.anwender.AnwenderHandling" %>
<%@ page import="de.ivu.wahl.anwender.Rechte" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBean" %>
<%@ page import="de.ivu.wahl.client.beans.Command" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.modell.AnwenderModel" %>
<%@ page import="de.ivu.wahl.modell.GebietInfo" %>
<%@ page import="de.ivu.wahl.modell.RechtegruppeModel" %>
<%@ page import="de.ivu.wahl.modell.ejb.Anwender" %>
<%@ page import="de.ivu.wahl.modell.ejb.Gebiet" %>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>

<%--
 *******************************************************************************
 * Anwender anlegen / editieren
 * Anlegen / editieren eines neuen / vorhandenen Anwenders
 *
 * author:  tst@ivu.de mur@ivu.de cos@ivu.de  Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="navBean" scope="request" class="de.ivu.wahl.client.beans.NavigationBean" />
<%
   response.setHeader("Cache-Control","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ //HTTP 1.0
   response.setDateHeader ("Expires", 0); //$NON-NLS-1$ //prevents caching at the proxy server 
   Logger log = Logger.getLogger(this.getClass());
   
   String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
   AnwContext anwContext = appBean.getAnwContext();
   String prefix = ApplicationBeanKonstanten.PREFIX;
   String breite = "100%"; //$NON-NLS-1$
   Anwender anwender = null;
   AnwenderModel model = null;
   boolean newAnw = false;
   int work = ClientHelper.getWork(request, -1);
   Command nextStep = Command.ANWENDER_ANLEGEN;
   // initialisiert mit den Defaultwert für Bund
   int gebietschluessel = -99;
   String id_anw_param = "ANW_"+ prefix +"id_anwender"; //$NON-NLS-1$ //$NON-NLS-2$
   if (work != -1) {
      if (Command.ANWENDER_VERAENDERN_2_EDIT.hasId(work)) {
        String id_anw = request.getParameter(id_anw_param);
        anwender = admBean.getAnwenderBean(id_anw);
         model = anwender.getDetails();
         newAnw = false;
         Gebiet gebiet = anwender.getGebiet();
         if (gebiet != null) {
            gebietschluessel = gebiet.getNummer();
         }
         nextStep = Command.ANWENDER_VERAENDERN_1_AUSWAHLEN;
      } else {
         newAnw = true;
      }
   }
   List<String> ignoreList = new ArrayList<String>();
   ignoreList.add(ApplicationBeanKonstanten.WORK);
   ignoreList.add(id_anw_param);
   String urlSave = "/osv?cmd=adm_saveAnwender&" + ClientHelper.workIs(nextStep) + '&' + ClientHelper.getAllParameters(request, ignoreList, true); //$NON-NLS-1$
   AnwenderHandling anwHandling = appBean.getAnwenderHandling();
   Collection<RechtegruppeModel> rgs =  anwHandling.getAllRechtegruppen();
   String errorMsg = null;
   String confirmationMsg = null;
   if (admBean._adminMsg != null && !admBean._adminMsg.isEmpty()) {
   	errorMsg = admBean._adminMsg;
  	admBean._adminMsg = null;
   }
   if (admBean._confirmationMsg != null && !admBean._confirmationMsg.isEmpty()) {
  	confirmationMsg = admBean._confirmationMsg;
  	admBean._confirmationMsg = null;
   }

	String helpKey = "admAnwenderSelect"; //$NON-NLS-1$
	if (newAnw) {
		helpKey = "admAnwenderEdit"; //$NON-NLS-1$
	}
   %>
<html>
   <head>
      <% if (newAnw) { // %>
      <title><ivu:int key="Anwender_anlegen_titel"/></title>
      <% } else { // %>
      <title><ivu:int key="Anwender_veraendern_titel"/></title>
      <% } %>
      <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
      <link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery-entropizer.css">
      <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.12.4.js"></script>
      <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
      <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/entropizer.js"></script>
      <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-entropizer.js"></script>
   </head>
   
   <script language="JavaScript">
   
   function save(){
   
   <%-- checke Login--%>
   <% if (newAnw) { %>
   if (document.edit_Anwender.<%=prefix%>anw_anwendername.value == "") {
   alert("<%= BundleHelper.getBundleString("Anwender_bearbeiten_error_Loginname")%>");
   return false;
   }
   <% } %>
   <%-- checke Name--%>
   if (document.edit_Anwender.<%=prefix%>anw_name.value == "") {
   alert("<%= BundleHelper.getBundleString("Anwender_bearbeiten_error_Anwendername")%>");
   return false;
   }
   
   <%-- bei neuen Anwender muss ein Passwort eingegeben werden--%>
   <% if (newAnw){ %>
   if (document.edit_Anwender.<%=prefix%>anw_passwort1.value == "") {
   alert("<%= BundleHelper.getBundleString("Anwender_bearbeiten_error_Passwort")%>");
   return false;
   }
   <% } %>
   <%-- checke Passwortgleichheit--%>
   if (document.edit_Anwender.<%=prefix%>anw_passwort1.value != document.edit_Anwender.<%=prefix%>anw_passwort2.value) {
   alert("<%= BundleHelper.getBundleString("Anwender_bearbeiten_error_Passwort_wiederholung")%>");
   return false;
   }
   
   return true;
   }
   </script>
   
   <body class="hghell" onload="sc()">
      
      <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
         <%@include file="/jsp/fragments/help_row.jspf"%>
         <tr>
            <td valign="top">
               <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
                  <tr>
                     <td width="5" class="hggrau">&nbsp;</td>
                     <td colspan="2" class="hggrau" height="20">
                        <% if (newAnw) { // %>
                        	<ivu:int key="Anwender_anlegen"/>
                        <% } else { // %>
                        	<ivu:int key="Anwender_veraendern"/>
                        <% } %>
                     </td>
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
                                 <fieldset style="border: 1px solid #093C69; padding-top: 15px; padding-left: 15px; padding-right: 15px">
                                    <% if (errorMsg != null){%>
										<p style="color:red;"><b><%= ClientHelper.forHTML(errorMsg) %></b></p>
									<% } %> 
                                    <% if (confirmationMsg != null){%>
										<p style="color:green;"><b><%= ClientHelper.forHTML(confirmationMsg) %></b></p>
									<% } %> 
                                    <% if (newAnw) {  // %>
                                    <legend><b><ivu:int key="Anwender_anlegen_titel"/></b></legend><br />
                                    <p><ivu:int key="Anwender_anlegen_info"/></p>
                                    <% } else { // %>
                                    <legend><b><ivu:int key="Anwender_veraendern_titel"/></b></legend><br />
                                    <p><ivu:int key="Anwender_veraendern_info"/></p>
                                    <% } %>
                                    <ivu:form name="edit_Anwender" action="<%= urlSave %>" onsubmit="return save()" autocomplete="off">
                                       <%
                                       String aktuellerADMNodePath = request.getParameter(ApplicationBeanKonstanten.ADM_NAVI_ANKER);
                                       if (aktuellerADMNodePath == null){
                                          if (newAnw || anwender.getID_Gebiet() == null){
                                             aktuellerADMNodePath = "0";
                                          } else {
                                             aktuellerADMNodePath = "0_" + navBean.getGebietsNodePath(appBean, anwender.getID_Gebiet());                                  
                                          }
                                       } 
                                       
                                       int counterTree = 0;
                                       int width = 18;
                                       int classInt = 1;
                                       NavigationErgebnis navErgebnis = navBean.getAdminTreeFilterAnwGebiet(appBean, aktuellerADMNodePath);
                                       List<DefaultMutableTreeNode> treeList = navErgebnis.getList();
                                       int rootNodeCount = treeList != null ? treeList.size() : 0;
                                             
                                       if (!anwHandling.hasAnwenderGebiet(anwContext.getID_Anwender())){%>
														<input type="hidden" name="<%= prefix + "anw_gebiet" %>" value="-99" />
                                       <%
                                       }
                                       %>
                                       <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                             <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                                             <td valign="top">
                                                <table cellspacing="4" cellpadding="2" border="0">
                                                   <tr>
                                                      <td><ivu:int key="Anwender_bearbeiten_Loginname"/></td>
                                                      <% if (newAnw) {
                                                            String anwname = "";
                                                            //request.getParameter(prefix+"anw_anwendername");
                                                            //anwname = anwname != null ? anwname : "";
                                                            %>
                                                      <td><input type="text" style="font-size:11px" name="<%=prefix%>anw_anwendername" value="<%= (newAnw)? anwname : ClientHelper.forHTML(model.getAnwendername()) %>" /></td>
                                                      <% } else { %>
                                                      <td>
                                                         <input type="hidden" name="<%=prefix%>anw_anwendername" value="<%= ClientHelper.forHTML(model.getAnwendername()) %>" />
                                                         <b><%= ClientHelper.forHTML(model.getAnwendername()) %></b>
                                                         
                                                      </td>
                                                      <% } %>
                                                   </tr>
                                                   <tr>
                                                      <%
                                                      String name = ""; //$NON-NLS-1$
                                                      %>
                                                      <td><ivu:int key="Anwender_bearbeiten_Anwendername"/></td>
                                                      <td><input type="text" style="font-size:11px;width:100%" name="<%=prefix%>anw_name" value="<%= (newAnw)? name : ClientHelper.forHTML(model.getName()) %>" /></td>
                                                   </tr>
                                                   <tr>
                                                      <td><ivu:int key="Anwender_bearbeiten_Passwort"/></td>
                                                      <td>
                                                      	<input type="password" style="font-size:11px;width:100%" name="<%=prefix%>anw_passwort1" value=""  id="newOne1"/>
                                                      	<div id="meter2">
                                                      </td>
                                                   </tr>
                                                   <tr>
                                                      <td><ivu:int key="Anwender_bearbeiten_Passwort_wiederholung"/></td>
                                                      <td><input type="password" style="font-size:11px;width:100%" name="<%=prefix%>anw_passwort2" value="" id="newOne2"/></td>
                                                   </tr>
                                                   <tr>
                                                      <td><br /><b><ivu:int key="Anwender_bearbeiten_Rechtegruppe_zuweisen"/></b></td>
                                                      <td>&nbsp;</td>
                                                   </tr>
                                                   <%-- alle rechtegruppen --%>
                                                   <%
                                                   for (RechtegruppeModel rgm : rgs) {
                                                      boolean hatEr = false;
                                                      String recht = request.getParameter(prefix + rgm.getName());
                                                      String id_Rechtegruppe = rgm.getID_Rechtegruppe();
                                                      boolean sollEr = Boolean.parseBoolean(recht) || (newAnw && Rechte.RG_ANWENDER.equals(rgm.getID_Rechtegruppe()));
                                                      if (anwender != null) {
                                                         hatEr = ApplicationBean.isAnwenderMemberOfRechtegruppe(anwender, id_Rechtegruppe);   
                                                      }
	                                                   %>
	                                                   <tr>
	                                                      <td><%= rgm.getName()%>&nbsp;(<%=rgm.getBeschreibung()%>)</td>
	                                                      <td><input type="checkbox" name="<%=prefix + rgm.getID_Rechtegruppe()%>" <%=(hatEr || sollEr)?"checked":""%> value="true" / ></td>
	                                                   </tr>
	                                                   <%
                                                   } %>
                                                   <% if (!newAnw) { %>
                                                   <tr>
                                                      <td><ivu:int key="Anwender_bearbeiten_Anzahl_Fehlanmeldungen"/></td>
                                                      <td><input type="text" name="<%=prefix%>anw_fehlanmeldungen" value="<%=model.getFehlversucheAnmeldung()%>" /></td>
                                                   </tr>
                                                   
                                                   <%-- ein versteckter Eintrag zum übergeben der Anwender ID--%>
                                                   <tr><td><input type="hidden" name="<%=prefix + "id_anwender"%>" value="<%=model.getID_Anwender() %>" / ></td></tr>
                                                   <% } %>
                                                   <tr>
                                                      <td>&nbsp;</td>
                                                      <td>&nbsp;</td>
                                                   </tr>
                                                   <tr>
                                                      <td>&nbsp;</td>
                                                      <td><br /><input id="box2" style="cursor:pointer" type="submit" value="<%=BundleHelper.getBundleString("Bestaetigen") %>" name="<%=prefix+"bestaetigen"%>"></td>
                                                   </tr>
                                                </table>
                                             </td>
                                             <td width="10">&nbsp;</td>
                                          </tr>
                                       </table>
                                    </ivu:form>
                                 </fieldset>
                              </td>
                           </tr>
                        </table>
                     </td>
                     <td width="10">&nbsp;</td>
                  </tr>
                  <tr>
                     <td height="10">&nbsp;</td>
                  </tr>
               </table>
            </td>
         </tr>
      </table>
   </body>
</html>
