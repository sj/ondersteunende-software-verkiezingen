<%--
Abmeldebestätigung mit Link zur erneuten Anmeldung
--%>
<%@ page errorPage="/jsp/MainErrorPage.jsp" import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<html>
  <head>
    <title><ivu:int key="Abmeldung_erfolgt_titel"/></title>
    <link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico" />
    <link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico" />
      <style>
         table{border:2px solid #093C69;
         }
         td {font-family: Verdana, Arial, Helvetica; font-size: 11px; color: #093C69}
      </style>
  </head>

 <body class="std" onload="javascript:if (window != top) {top.location.replace(window.location.href)};">
  <table width="65%" align="center" border="0" bgcolor="#F9FAFD"  cellpadding="5">
   <tr>
     <td colspan="2">
  <h3><ivu:int key="Abmeldung_erfolgt"/></h3></td>
  </tr>
  <tr>
     <td>
      </td>
      <td><ivu:int key="Abmeldung_erfolgt_titel"/><br>
     </td>
  </tr>
  <tr>
     <td></td>
     <td height="80">
    		<ivu:int key="Abmeldung_erfolgt_info_1"/>
    		<a href='<%= request.getContextPath() + "/osv/logon?r=" + ClientHelper.getRandomString() %>' target='_top'><ivu:int key="Abmeldung_erfolgt_info_2"/></a>

   <%
      session = request.getSession(false);
      if (session != null) {
        session.invalidate();
      }
   %>
      </td>
   </tr>
   </table>
  </body>
</html>
