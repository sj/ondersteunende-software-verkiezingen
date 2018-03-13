<%@ page import="de.ivu.wahl.modell.ejb.Rechtegruppe" %>
<%@ page import="de.ivu.wahl.modell.ejb.Anwender" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="de.ivu.wahl.anwender.AnwenderHandling" %>
<%--
 *******************************************************************************
 * DEBUG: Rechte
 * Zeigt alle Rechte des angemeldeten Anwenders an
 *
 * author:  tst@ivu.de  Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: debug_rechte.jsp,v 1.3 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%>
<%@ page errorPage="/jsp/MainErrorPage.jsp" %>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<%
   AnwenderHandling anwHandling = appBean.getAnwenderHandling();
   String id_anw = appBean.getAnwContext().getID_Anwender();
   String breite = "100%";
   %>
<html>
<head>
   <title><ivu:int key="Rechte_titel"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
</head>
<body class="hghell">
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20"><ivu:int key="Rechte_titel"/></td>
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
                  <p>
                     <ivu:int key="Rechte_info"/>
                  </p>
                  <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
                     <tr class="hgrot">
                        <td valign="top">
                           <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
                              <tr>
                                 <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                              </tr>
                              <tr>
                                 <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                                 <td valign="top">
                                    <table border="0" cellspacing="2" cellpadding="4" class="hgweiss" width="<%= breite %>">
                                 <% 
                                    int i = 1;
                                    for (Rechtegruppe rg : admBean.getAnwenderBean(id_anw).getRechtegruppeCol()) {
                                       String str = rg.getName();
                                       %>
                                       <tr class="<%= i < 1?"hgweiss":"hgeeeeee"%>">
                                          <td>
                                             <%=str%>
                                          </td>
                                       </tr>
                                    <%
                                    i = i*(-1);
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
