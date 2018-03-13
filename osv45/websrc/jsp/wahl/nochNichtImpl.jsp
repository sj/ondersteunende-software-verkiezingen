<%--
 *******************************************************************************
 * Noch nicht Implementiert
 *
 * author:  mur@ivu.de  Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: nochNichtImpl.jsp,v 1.5 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%> 

<%@ page errorPage="/jsp/MainErrorPage.jsp"%>

<%
   String breite = "100%";
   String oeffsch = "Info";
%>
<html>
<head>
   <title><ivu:int key="AuswertungNochNichtImplementiert"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
</head>
<body class="hghell">

<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr class="hgeeeeee">
      <td height="14"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
    <tr class="hgeeeeee">
      <td class="hgschwarz"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20"><ivu:int key="AuswertungNochNichtImplementiert"/></td>
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
                        <fieldset style="border: 1px solid #093C69; padding: 15px">
                           <legend><b><%=oeffsch%></b></legend><br />
                              <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0">
                                 <tr>
                                    <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                                 </tr>
                                 <tr>
                                    <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                                    <td valign="top">
                                       <p>
                                        <ivu:int key="AuswertungNochNichtImplementiert_UmsetzungInKuerze"/>     
                                    </td>
                                    <td width="10">&nbsp;</td>
                                 </tr>
                                 <tr>
                                    <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                                 </tr>
                              </table>
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
