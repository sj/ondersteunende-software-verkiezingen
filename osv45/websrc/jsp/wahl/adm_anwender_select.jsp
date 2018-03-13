<%@ page import="de.ivu.wahl.modell.ejb.Anwender" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.Command" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<%--
 *******************************************************************************
 * Anwender wählen
 * Zeigt alle Anwender zum Auswahlen
 *
 * author:  tst@ivu.de cos@ivu.de Copyright (c) 2002-8 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<%
   response.setHeader("Cache-Control","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ // HTTP 1.1
   response.setHeader("Pragma","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ // HTTP 1.0
   response.setDateHeader ("Expires", 0); //$NON-NLS-1$ // prevents caching at the proxy server 

   String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
   String prefix = ApplicationBeanKonstanten.PREFIX;
   String id_anw_param = "ANW_"+ prefix +"id_anwender";  //$NON-NLS-1$ //$NON-NLS-2$
   Collection<Anwender> anwender = admBean.getAllAnwenderBeans(appBean.getAnwContext());
   String breite = "100%"; //$NON-NLS-1$
   int work = new Integer(request.getParameter(ApplicationBeanKonstanten.WORK)).intValue();
   String urlNext;
   if (Command.ANWENDER_LOESCHEN.hasId(work)) {
      urlNext = "/osv?cmd=adm_delAnwender&" + ClientHelper.getParametersDoNotStartWith(request, id_anw_param, true); //$NON-NLS-1$
   } else {
     List<String> ignoreList = new ArrayList<String>();
     ignoreList.add(ApplicationBeanKonstanten.WORK);
     ignoreList.add(id_anw_param);
     urlNext = "/osv?" + ClientHelper.workIs(Command.ANWENDER_VERAENDERN_2_EDIT) + '&' + ClientHelper.getAllParameters(request, ignoreList, false); //$NON-NLS-1$
   }
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
   
	String helpKey = "admAnwenderSelect";
	if (Command.ANWENDER_LOESCHEN.hasId(work)) {
		helpKey = "admAnwenderDelete";
	}
   %>
<html>
<head>
   <% if (Command.ANWENDER_LOESCHEN.hasId(work)) { // %>
      <title><ivu:int key="Anwender_loeschen_titel"/></title>
   <% } else { // %>
      <title><ivu:int key="Anwender_veraendern_titel"/></title>
   <% } %>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
      <style type="text/css">
         td {line-height: 15px}
      </style>
</head>
<script language="JavaScript">

   function checkSel(){
      <%-- check selection--%>
      if (document.Anwender.<%=id_anw_param%>.selectedIndex == -1) {
         alert("<ivu:int key="Anwender_auswaehlen"/>");
         return false;
      }
      return true;
   }
</script>
<body class="hghell">

<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <%@include file="/jsp/fragments/help_row.jspf"%>
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20">
                  <% if (Command.ANWENDER_LOESCHEN.hasId(work)) { // %>
                    <ivu:int key="Anwender_loeschen_titel"/>
                  <% } else { // %>
                    <ivu:int key="Anwender_veraendern_titel"/>
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
                  <fieldset style="border: 1px solid #093C69; padding-top: 15px; padding-left: 15px">
                        <% if (Command.ANWENDER_LOESCHEN.hasId(work)) { // %>
                           <legend><b><ivu:int key="Anwender_loeschen"/></b></legend><br />
                        <% } else { // %>
                           <legend><b><ivu:int key="Anwender_veraendern"/></b></legend><br />
                        <% } %>
                        <% if (errorMsg != null){%>
									<p style="color:red;"><b><%= ClientHelper.forHTML(errorMsg) %></b></p>
						<% } %>
                        <% if (confirmationMsg != null){%>
									<p style="color:green;"><b><%= ClientHelper.forHTML(confirmationMsg) %></b></p>
						<% } %>
                        
                        <ivu:form name="Anwender" action="<%= urlNext %>" onsubmit="return checkSel()">
                        <table border="0" cellspacing="0" cellpadding="1">
                           <tr>
                              <td>
                                 <% if (Command.ANWENDER_LOESCHEN.hasId(work)) { // %>
                                    <ivu:int key="Anwender_loeschen_info_Auswahl"/>
                                 <% } else { // %>
                                    <ivu:int key="Anwender_veraendern_info_Auswahl"/>
                                 <% } %>
                              </td>
                           </tr>
                            <tr>
                              <td height="15">&nbsp;</td>
                           </tr>
                           <tr>
                              <td>
                                 <select name="<%=id_anw_param%>" style="width: 150px; font-size: 11px">
                                    <%
                                       for (Anwender anw : anwender) {
                                    %>
                                       <option  value="<%= anw.getID_Anwender() %>"><%= ClientHelper.forHTML(anw.getAnwendername()) %>
                                    <% } %>
                                 </select>
                              </td>
                           </tr>
                           <tr>
                              <td height="25">&nbsp;</td>
                           </tr>
                           <tr>
                              <td align="center">
                                 <% if (Command.ANWENDER_LOESCHEN.hasId(work)) { // %>
                                    <input id="box2" style="cursor:pointer" type="submit" value="<ivu:int key="Anwender_loeschen_button"/>" />
                                 <% } else { // %>
                                    <input id="box2" style="cursor:pointer" type="submit" value="<ivu:int key="Anwender_veraendern_button"/>" />
                                 <% } %>
                              </td>
                           </tr>
                        </table>
                     </ivu:form>
                  </fieldset>
               </td>
               <td width="10">&nbsp;</td>
            </tr>
         </table>
      </td>
   </tr> 
</table>
</body>
</html>
