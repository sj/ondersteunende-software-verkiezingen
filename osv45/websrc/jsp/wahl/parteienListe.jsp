
<%@ page import="de.ivu.wahl.modell.GruppeModel"%><%--
 *******************************************************************************
 * Liste der Parteien
 * ist eine Liste mit den Parteienkurz- bzw Langnamen
 * und kann als Legende der Parteikurznamen betrachtet werden
 *
 * author:  mur@ivu.de  Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: parteienListe.jsp,v 1.11 2011/03/31 12:36:04 tdu Exp $
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page import="de.ivu.wahl.modell.GruppeGebietsspezifischModel" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
   String breite = "100%";
   %>
<html>
<head>
   <title><ivu:int key="AbkuerzungsverzeichnisParteien"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
    <style type="text/css">body { overflow:auto }</style>
</head>

<body class="hghell">
<%-- Zum Drucken des aktuellen Frames --%>
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgeeeeee">
   <tr class="print-hidden">
      <td align="right">
         <a href="javascript:window.print()" style="text-decoration:none">
            <span class="linkdklrot">
               <img src="<%= request.getContextPath() %>/img/icon/drucken.gif" width="24" height="9" align="middle" alt="" border="0" /><ivu:int key="SeiteDrucken"/>
            </span>
         </a>
      </td>
   </tr>
    <tr>
      <td class="hgschwarz"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
</table>

<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20"><ivu:int key="Liste_Parteien"/></td>
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
                     <tr class="hgrot">
                        <td valign="top">
                           <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
                              <tr class="hgeeeeee">
                                 <td width="5" height="18">&nbsp;</td>
                                 <td><ivu:int key="Langname"/></td>
                                 <td width="5" height="18">&nbsp;</td>
                              </tr>
                              <tr>
                                 <td colspan="3" class="hgrot"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                              </tr>
                              <tr>
                                 <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                                 <td valign="top">
                                    <table border="0" cellspacing="2" cellpadding="4" class="hgweiss" width="<%= breite %>">
                                       <%
                                       int i = -1;
                                       for (GruppeModel n : appBean.getParteienForWahl()) {
                                          i = -i;
                                          String nameLang = "";
                                          if( n.getNameLang() != null ){
                                              nameLang = n.getNameLang().trim();
	                                          if (nameLang.endsWith("-")) {
	                                             nameLang = nameLang.substring(0, nameLang.length()-1);
	                                          }
                                          }
                                          %>
                                          <tr class="<%= i < 1?"hgweiss":"hgeeeeee"%>">
                                             <td>
                                                <%=nameLang%>
                                             </td>
                                          </tr>
                                          <%
                                       }
                                       %>
                                    </table>
                                 </td>
                                 <td width="10">&nbsp;</td>
                              </tr>
                              <tr>
                                 <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                              </tr>
                           </table>
                        </td>
                     </tr>
                  </table>
               </td>
               <td width="10">&nbsp;</td>
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
