<%@ page import="de.ivu.wahl.modell.SchwellwertModel" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.WahlInfo" %>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>

<%--
 *******************************************************************************
 * Schwellwerte ändern (allg)
 * Arbeitsjsp für die Administration von allgemeinen (= Parteiunabhängigen) Schwellwerten
 * Es werden immer alle Schwellwerte geschrieben!
 *
 * author:  mur@ivu.de  Copyright (c) 2004-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: adm_schwellwerte_allgemein.jsp,v 1.9 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
   String breite = "100%";
   WahlInfo wahlInfo = appBean.getWahlInfo();
   
%>

<html>
<head>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
      <style type="text/css">
         td {line-height: 15px}
      </style>
   <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
</head>
<body class="hghell" onload="sc()">
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align=center class="hghell">
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
               <td colspan="2" class="hggrau" height="20"><ivu:int key="Schwellwerte_aendern_titel"/></td>
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
                        <p>
                           <ivu:int key="Schwellwerte_aendern_info"/>
                           <%
                              String prefix = ApplicationBeanKonstanten.PREFIX;
                              String formurl = "/osv?cmd=adm_swEingabeAllg&"+ClientHelper.getParametersDoNotStartWith(request, prefix);
                           %>
                           <ivu:form name="schwellwerte_allg" action="<%= formurl %>" >
                            <fieldset style="border: 1px solid #093C69; padding-top: 15px; padding-left: 15px; padding-right: 15px">
                              <legend><b><ivu:int key="Schwellwerte_aendern" /></b></legend><br />
                              <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0">
                                 <tr>
                                    <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                                 </tr>
                                 <tr>
                                    <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                                    <td valign="top">
                                       <table cellspacing="4" cellpadding="2" border="0">
                                       <% for (int i =0; i< SchwellwertModel.SWERT_ALLG_KEYS.length; i++) { 
                                            %>
                                          <tr>
                                             <td>
                                                <%=SchwellwertModel.SWERT_ALLG_KEYS[i][1]%>
                                             </td>
                                             <td>
                                                <%
                                                   String bezeichnung = SchwellwertModel.SWERT_ALLG_KEYS[i][0];
                                                   int wert = admBean.getSchwellwert(bezeichnung, request);
                                                %>
                                                <input type="text" style="font-size: 1em" name="<%=prefix+bezeichnung%>" value="<%=Integer.toString(wert)%>" />
                                             </td>
                                          </tr>
                                          <%
                                          } %>
                                       </table>
                                    </td>
                                    <td width="10">&nbsp;</td>
                                 </tr>
                                 <tr>
                                    <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
                                 </tr>
                              </table>
                              <div align="center">
                                 <br /><br />
                                 <input id="box2" type="submit" style="cursor:pointer" value="<%=BundleHelper.getBundleString("Uebernehmen") %>" name="swuebernehmen">
                              </div>
                           </fieldset>
                          </ivu:form>
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
