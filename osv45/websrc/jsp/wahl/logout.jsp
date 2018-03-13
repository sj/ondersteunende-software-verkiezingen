
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%><%--
 *******************************************************************************
 * Abmelden
 * Seite, die vor dem tatsächlichen Logout angezeigt wird
 *
 * author:  klie@ivu.de cos@ivu.de  Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
String backgroundColor = appBean.getBackgroundColor();
String breite = "100%";
%>
<html>
 <head>
  <title><ivu:int key="Logout"/></title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
 </head>

 <body class="hghell">
  <table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr class="hgeeeeee">
    <td height="14" style="background-color: <%=backgroundColor%>;"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr class="hgeeeeee">
    <td class="hgschwarz"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
    <td valign="top">
     <table width="<%=breite%>" border="0" cellspacing="0" cellpadding="0" class="hghell">
      <tr>
       <td width="5" class="hggrau">
        &nbsp;
       </td>
       <td colspan="2" class="hggrau" height="20">
        <ivu:int key="Abmelden"/>
       </td>
      </tr>
      <tr class="hgeeeeee">
       <td colspan="3" class="hgschwarz"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
      </tr>
      <tr>
       <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
      </tr>
      <tr>
       <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
       <td valign="top">
        <table border="0" cellspacing="0" cellpadding="1" width="<%=breite%>">
         <tr>
          <td valign="top">
           <ivu:form name="logout" action="/osv?cmd=app_logout">
            <fieldset style="border: 1px solid #093C69; padding-left: 15px; padding-top: 15px; width: 100%;">
             <legend>
              <b><ivu:int key="Abmelden_von"/> <%= ClientHelper.forHTML(appBean.getAnwContext().getAnmeldename()) %>
              </b>
             </legend>
             <p style="text-align: left;">
              	<ivu:int key="Abmelden_wirklich"/>
             </p>
             <div style="padding: 1em; text-align: center">
               <input id="box2" style="cursor:pointer" type="submit" value="<%=BundleHelper.getBundleString("Abmelden_jetzt") %>" name="logout">
             </div>
            </fieldset>
           </ivu:form>
          </td>
         </tr>
        </table>
       </td>
       <td width="10">
        &nbsp;
       </td>
      </tr>
     </table>
    </td>
   </tr>
  </table>
 </body>
</html>
