<%@ page isErrorPage="true"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <title><ivu:int key="NoBack.1"/></title>
      <style>
         table{border:3px solid #CC3333;
         }
         td {font-family: Verdana, Arial, Helvetica; font-size: 11px }
      </style>
      <link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico"/>
      <link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico"/>
   </head>
 <body class="std" onload="if (window != top) {top.location.replace(window.location.href)};">
  <br/>
   <table width="60%" align="center" border="0" bgcolor="#FAFAFA" cellpadding="5">
   <tr>
     <td colspan="2">
  <h3><ivu:int key="NoBack.2"/></h3></td>
  </tr>
  <tr>
     <td>
      <img src="<%= request.getContextPath() %>/img/icon/icon_warning_message.gif" border="0"/>
      </td>
      <td><ivu:int key="NoBack.3"/><br/>
     </td>
  </tr>
  <tr>
     <td></td>
     <td height="80">
      <ivu:int key="NoBack.4"/> <ivu:int key="NoBack.5"/>
      <a href="${LAST_VALID_URL}"><ivu:int key="NoBack.6"/></a>
      </td>
   </tr>
   </table>
   </body>
</html>
