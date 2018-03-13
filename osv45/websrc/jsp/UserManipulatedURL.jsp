<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<html>
   <head>
     <title><ivu:int key="URL_Manipulation.1"/></title>
    <style>
         table{border:2px solid #093C69;
         }
         td {font-family: Verdana, Arial, Helvetica; font-size: 11px; color: #093C69 }
      </style>
      <link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
      <link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico">
   </head>

 <body class="std" onload="javascript:if (window != top) {top.location.replace(window.location.href)};">
  <br>
   <table width="60%" align="center" border="0" bgcolor="#F9FAFD" cellpadding="5">
   <tr>
     <td colspan="2">
  <h3><ivu:int key="URL_Manipulation.2"/></h3></td>
  </tr>
  <tr>
     <td>
      <img src="<%= request.getContextPath() %>/img/icon/icon_warning_message.gif" border="0">
      </td>
      <td><ivu:int key="URL_Manipulation.3"/><br>
     </td>
  </tr>
  <tr>
     <td></td>
     <td height="80">
      <%
      String url = "/osv?"+ClientHelper.getSuffixLevel(request, ApplicationBeanKonstanten.LEVEL_INITIAL)+
                  "&"+ApplicationBeanKonstanten.WORKIS+ApplicationBeanKonstanten.GEB_ERG;
      %>
      <ivu:int key="URL_Manipulation.4"/><br />
      <ivu:int key="URL_Manipulation.5"/><br />
      <ivu:a href='<%=url%>'><ivu:int key="URL_Manipulation.6"/></ivu:a>
      </td>
   </tr>
   </table>
   </body>

</html>
