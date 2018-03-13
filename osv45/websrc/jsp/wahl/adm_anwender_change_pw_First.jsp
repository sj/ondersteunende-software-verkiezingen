<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBean"%>
<%@ page import="de.ivu.wahl.AnwContext"%>
<%@ page import="de.ivu.wahl.Konstanten"%>

<%--
 *******************************************************************************
 * Seite zum initialen Ändern des Passworts des default-Anwenders ("osv")
 * Ändert das Passwort des angemeldeten Benutzers
 *
 * author:  tst@ivu.de mur@ivu.de cos@ivu.de  Copyright (c) 2002-2013 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
   String arch = ""; //$NON-NLS-1$
   String val = appBean.getPropertyHandling().getProperty(Konstanten.PROP_SYSTEM_ARCHITECTURE_BITS);
   if (val != null) {
      arch = " (" + val + BundleHelper.getBundleString("SystemArchitectureBits") + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }

   response.setHeader("Cache-Control","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ //HTTP 1.0
   response.setDateHeader ("Expires", 0); //$NON-NLS-1$ //prevents caching at the proxy server 
   String prefix = ApplicationBeanKonstanten.PREFIX;
   AnwContext ac = appBean.getAnwContext();
   String urlChange = appBean.getInitialURL(request, response, null, false); 
   boolean check = true;
   if ((ac != null &&  ac.isChangePasswordForced()) || appBean.isBeforeInitialPasswordChange()) {
      urlChange = appBean.getInitialURL(request, response, "cmd=adm_change_pw", false);    //$NON-NLS-1$ 
      System.out.println("--1.---------------------------------------------------" + urlChange);
   } else {
      // Disable input of new password, show success message
	  check = false; 
      System.out.println("--2.---------------------------------------------------" + urlChange);
	   %>
<%--	   <jsp:forward page="<%= urlChange %>"></jsp:forward>--%>
   <% }
   
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
   <link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
   <link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
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

<body onload="sc()">
<table border="0" width="<%=breite%>" height="100%">
   <tr>
      <td>
         <form name="Anwender" action="<%= urlChange %>" <%= check ? "onsubmit='return check()'" : "" %> autocomplete="off" method="post">
            <table width="<%=breite%>" height="100%" style="background-image:url(<%= request.getContextPath() %>/img/logo/<%= BundleHelper.getBundleString("Background")%>); background-repeat:no-repeat; background-position:center">
               <tr>
                  <td align="center">
                     <table border="0" cellspacing="0" cellpadding="0" width="400" height="375" >
                        <tr>
                           <td height="150" colspan="4">
                              &nbsp;
                           </td>
                        </tr>
                        <tr>
                           <td>&nbsp;</td>
                           <td colspan="3"><b><ivu:int key="Passwort_veraendern_First"/></b><br />
	                           <p><ivu:int key="Passwort_veraendern_First_info"/></p><br/><br/></td>
                        </tr>
                        <tr align="left">
                           <td width="65">&nbsp;</td>
                           <td><b><ivu:int key="Passwort_veraendern_neues_Passwort" />:</b></td>
                           <td>&nbsp;</td>
                           <td align="left">
                              <input id="newOne1" <%= !check ? "class='ergdisable' disabled='disabled'": "" %> type="password" name="<%=prefix%>anw_new_pw_1" value="" onkeypress="capsLock(event)" autocomplete="off">
                              <div id="meter2"></div>
                           </td>
                        </tr>
                        <tr align="left">
                           <td>&nbsp;</td>
                           <td><b><ivu:int key="Passwort_veraendern_neues_Passwort_wiederholung" />:</b></td>
                           <td>&nbsp;</td>
                           <td align="left">
                              <input <%= !check ? "class='ergdisable' disabled='disabled'": "" %>  type="password" name="<%=prefix%>anw_new_pw_2" value="" onkeypress="capsLock(event)" autocomplete="off">
                              <span id="capsLock" style="visibility:hidden"><%= BundleHelper.getBundleString("Caps_Lock_Hint") %></span>
                           </td>
                        </tr>
	                     <% if (check){ %>
   	                     <tr>
	                        	<td>&nbsp;</td>
	                           <td align="right">
   	                           <input style="background-color:#000000;" type="image" style="border : 0px solid black;" src="<%= request.getContextPath() %>/img/icon/pfeil_logon.gif" border="0" title="aanmelden" alt="aanmelden"/>
		                        </td>
	                           <td>&nbsp;</td>
	                        </tr>
                        <% } else { %>
                            <% if (errorMsg != null){%>
		                     	<tr>
		                     	  	<td>&nbsp;</td>
		                           <td align="left" colspan="3">
												<h2><p style="color:red;"><b><%= errorMsg %></b></p></h2>
	         		               </td>
		                        </tr>
							<% } %>
                            <% if (confirmationMsg != null){%>
		                     	<tr>
		                     	  	<td>&nbsp;</td>
		                           <td align="left" colspan="3">
												<h2><p style="color:green;"><b><%= confirmationMsg %></b></p></h2>
	         		               </td>
		                        </tr>
							<% } %>
	                     	<tr>
	                     	  	<td>&nbsp;</td>
	                           <td align="center" colspan="3">
                                 <input id="box2" style="cursor:pointer" type="submit" value="<%=BundleHelper.getBundleString("weiter") %>" name="<%=prefix+"bestaetigen"%>">
         		               </td>
	                        </tr>
                        <% } %>
                        <tr>
                           <td height="50%" colspan="4">
                        
                           </td>
                        </tr>
                     </table>
                     <p align="right">
                        <b><ivu:int key="Version" /> <%=ApplicationBean.getVersionString() + arch%><br />Build <%=ApplicationBean.getBuildDatumString()%>
                        </b>
                     </p>
                     <% if (check && errorMsg != null){%>
								<h2><p style="color:red;"><b><%= errorMsg %></b></p></h2>
					 <% } %>
                     <% if (check && confirmationMsg != null){%>
								<h2><p style="color:green;"><b><%= confirmationMsg %></b></p></h2>
					 <% } %>
                  </td>
               </tr>
            </table>
         </form>
      </td>
   </tr>
</table>
</body>
</html>