<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.AnwContext"%>

<%--
 *******************************************************************************
 * Passwort ändern
 * Ändert das Passwort des angemeldeten Benutzers
 *
 * author:  tst@ivu.de mur@ivu.de cos@ivu.de  Copyright (c) 2002-9 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: adm_anwender_change_pw.jsp,v 1.15 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "admAnwenderChangePW"; //$NON-NLS-1$

   response.setHeader("Cache-Control","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ //HTTP 1.0
   response.setDateHeader ("Expires", 0); //$NON-NLS-1$ //prevents caching at the proxy server 
   String prefix = ApplicationBeanKonstanten.PREFIX;
   String urlChange    = "/osv?cmd=adm_change_pw&" + ClientHelper.getParametersDoNotStartWith(request, prefix); //$NON-NLS-1$
   String breite = "100%"; //$NON-NLS-1$
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
%>
<html>
<head>
   <title><ivu:int key="Passwort_veraendern_titel"/> </title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery-entropizer.css">
   <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.12.4.js"></script>
   <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
   <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/capslock.js"></script>
   <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/entropizer.js"></script>
   <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-entropizer.js"></script>
</head>

<script language="JavaScript">

   function check(){
      <%-- checke old Login--%>
         if (document.Anwender.<%=prefix%>anw_old_pw.value == "") {
	         alert("<%= BundleHelper.getBundleString("Passwort_veraendern_error_Passwort_alt")%>");
            return false;
         }
      <%-- checke Name--%>
      <%-- ein neues Passwort muss eingegeben werden--%>
         if (document.Anwender.<%=prefix%>anw_new_pw_1.value == "") {
         	alert("<%= BundleHelper.getBundleString("Passwort_veraendern_error_Passwort_neu")%>");
            return false;
         }
      <%-- checke Passwortgleichheit--%>
      if (document.Anwender.<%=prefix%>anw_new_pw_1.value != document.Anwender.<%=prefix%>anw_new_pw_2.value) {
         alert("<%= BundleHelper.getBundleString("Passwort_veraendern_error_Passwoerter_identisch")%>");
         return false;
      }
      return true;
  }
</script>

<body class="hghell" onLoad="sc()">
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <%@include file="/jsp/fragments/help_row.jspf"%>
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20"><ivu:int key="Passwort_veraendern_titel"/></td>
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
                        <fieldset style="border: 1px solid #093C69; padding: 15px; padding-right: 15px; padding-left: 15px">
                           <legend><b><ivu:int key="Passwort_veraendern"/></b></legend><br />
                           <p>
                           	<ivu:int key="Passwort_veraendern_info"/>
                           </p>
                           <% if (errorMsg != null){%>
										<p style="color:red;"><b><%= errorMsg %></b></p>
						   <% } %> 
                           <% if (confirmationMsg != null){%>
										<p style="color:green;"><b><%= confirmationMsg %></b></p>
						   <% } %> 
                           <ivu:form name="Anwender" action="<%= urlChange %>" onsubmit="return check()" autocomplete="off">
                              <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0">
                                 <tr>
                                    <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                                 </tr>
                                 <tr>
                                    <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                                    <td valign="top">
                                       <table cellspacing="4" cellpadding="2" border="0">
                                          <tr>
                                             <td>
						                           	<ivu:int key="Passwort_veraendern_altes_Passwort"/>
                                             </td>
                                             <td>
                                             	<input type="password" style="font-size: 1em;width:148px" name="<%=prefix%>anw_old_pw" value="" id="oldOne"/ onkeypress="capsLock(event)">
                                             </td>
                                          </tr>
                                          <tr>
                                             <td>
						                           	<ivu:int key="Passwort_veraendern_neues_Passwort"/>
                                             </td>
                                             <td>
                                             	<input type="password" style="font-size: 1em;width:148px" name="<%=prefix%>anw_new_pw_1" value="" id="newOne1"/ onkeypress="capsLock(event)" autocomplete="off">
                                             	<div id="meter2"></div>
                                             </td>
                                          </tr>
                                          <tr>
                                             <td>
						                           	<ivu:int key="Passwort_veraendern_neues_Passwort_wiederholung"/>
                                             </td>
                                             <td>
                                             	<input type="password" style="font-size: 1em;width:148px" name="<%=prefix%>anw_new_pw_2" value="" id="newOne2"/ onkeypress="capsLock(event)" autocomplete="off">
                                             	<span id="capsLock" style="visibility:hidden"><%= BundleHelper.getBundleString("Caps_Lock_Hint") %></span>
                                             </td>
                                          </tr>
                                          <tr>
                                             <td>&nbsp;</td>
                                             <td>&nbsp;</td>
                                          </tr>
                                          <tr>
                                             <td>&nbsp;</td>
                                             <td>
                                                <br /><input id="box2" style="cursor:pointer" type="submit" value="<%=BundleHelper.getBundleString("Bestaetigen") %>" name="<%=prefix+"bestaetigen"%>">
                                             </td>
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
         </table>
      </td>
   </tr>
</table>
</body>
</html>
