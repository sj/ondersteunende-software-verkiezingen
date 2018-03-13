<%--
 *******************************************************************************
 * Angemeldete Anwender anzeigen
 * Zeigt alle angemeldeten Anwender an
 *
 * Auf der Seite ist noch einmal ein rechteCheck, da die Seite ansonsten als Admin-Default durchgehen darf
 * author:  klie@ivu.de  Copyright (c) 2002 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ page import="de.ivu.wahl.anwender.Anmeldung" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.text.DateFormat" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>

<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "admAngemeldeteAnw"; //$NON-NLS-1$

   response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
   response.setHeader("Pragma","no-cache"); //HTTP 1.0
   response.setDateHeader ("Expires", 0); //prevents caching at the proxy server 
   String breite = "100%";
   %>
<html>
<head>
   <title><ivu:int key="Anwender_angemeldet"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
      <style type="text/css">
         td {line-height: 18px}
      </style>
</head>

<body class="hghell">
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <%@include file="/jsp/fragments/help_row.jspf"%>
   <tr>
      <td valign="top">
      <% if (!appBean.checkRight(de.ivu.wahl.anwender.Rechte.R_ADM_ANGEMELDETE))  { // %>
         <p>
            <%-- möglicherweise hier was nettes reinschreiben für normale Anwender, die in die Admjnistration wechseln --%>
         </p>
      <% } else { %>
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20"><ivu:int key="Anwender_angemeldet"/></td>
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
                     <ivu:int key="Anwender_angemeldet_info_1"/> <%= session.getMaxInactiveInterval()/60 %> <ivu:int key="Anwender_angemeldet_info_2"/>
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
                                 DateFormat df =  DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
                                 //Collection col = appBean.getAngemeldeteAnwenderForWahl();
                                 Collection col = appBean.getAngemeldeteAnwender();
                                 Iterator it = col.iterator();
                                 Vector v = new Vector();
                                 int i = 1;
                                 while (it.hasNext()) {
                                    Anmeldung an = (Anmeldung)it.next();
                                    String str = an._loginName+" "+df.format(new java.util.Date(an._time));
                                    String wahlname = an._wahlName;
                                    %>
                                    <tr class="<%= i < 1?"hgweiss":"hgeeeeee"%>">
                                       <td>
                                          <%= ClientHelper.forHTML(str) %>
                                       </td>
                                       <td>
                                          <%= wahlname %>
                                       </td>
                                    </tr>
                                    <%
                                    i = -i;
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
         <% } %>
      </td>
   </tr>
</table>
</body>
</html>
