
<%@page import="de.ivu.wahl.client.beans.AdministrationBean"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%><%--
 *******************************************************************************
 * Führt SQL-Kommandos aus, um die Indizes der Tabelle Stimmeingabe 
 * neu anzulegen.
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.anwender.Rechte" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />

<%
  response.setHeader("Cache-Control","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ // HTTP 1.1
  response.setHeader("Pragma","no-cache"); //$NON-NLS-1$ //$NON-NLS-2$ // HTTP 1.0
  response.setDateHeader ("Expires", 0); //$NON-NLS-1$ // prevents caching at the proxy server 

  String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
  String prefix = ApplicationBeanKonstanten.PREFIX;
  String formurl = ClientHelper.generateURL(request, null, AdministrationBean.CMD_ADM_RE_INDEX_DATABASE, -1, true);
  boolean hatFZRecht = appBean.getAnwContext().checkRight(Rechte.R_RE_INDEX_DATABASE);
  
  String helpKey = "admReIndexDatabase";
  String breite = "100%";

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
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <style type="text/css">
    body { overflow:auto }
    td {line-height: 15px}
   </style>
 </head>
 <body class='hghell'>
  <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <%@include file="/jsp/fragments/help_row.jspf"%>
   <tr>
    <td valign="top">
     <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
      <tr>
       <td width="5" class="hggrau">&nbsp;</td>
       <td colspan="2" class="hggrau" height="20"><ivu:int key="re_index_database_titel"/></td>
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
        <ivu:form name="appstate" action="<%= formurl %>" >
         <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
          <tr>
           <td valign="top">
            <fieldset style="border: 1px solid #093C69; padding: 15px">
              <% if (errorMsg != null){%>
				<p style="color:red;"><b><%= ClientHelper.forHTML(errorMsg) %></b></p>
			  <% } %> 
              <% if (confirmationMsg != null){%>
				<p style="color:green;"><b><%= ClientHelper.forHTML(confirmationMsg) %></b></p>
			  <% } %> 
            
             <legend><b><%=BundleHelper.getBundleString("re_index_database")%></b></legend><br />

             <table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0">
              <tr>
               <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
              </tr>
              <tr>
               <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
               <td valign="top"> 
	              <p><ivu:int key="re_index_database_info"/></p>
	              <div align="center">
	                <br /><br />
	                <input id="box2" type="submit" value="<%=BundleHelper.getBundleString("re_index_database_button")%>" name="<%=prefix%>chgAppState"/>
                  </div>
               </td>
               <td width="10">&nbsp;</td>
              </tr>
             </table>
            </fieldset>
           </td>
          </tr>
         </table>
        </ivu:form>
       </td>
       <td width="10">&nbsp;</td>
      </tr>
     </table>
    </td>
   </tr>
  </table>
 </body>
</html>
