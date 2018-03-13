<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="uplBean" scope="session" class="de.ivu.wahl.client.beans.UploadBean" />
<%  
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "dateiUpload"; //$NON-NLS-1$

String breite = "100%";
String errorMsg = null;
String confirmationMsg = null;
if (uplBean._adminMsg != null && !uplBean._adminMsg.isEmpty()) {
 	errorMsg = uplBean._adminMsg;
 	uplBean._adminMsg = null;
 }
if (uplBean._adminConfirmationMsg != null && !uplBean._adminConfirmationMsg.isEmpty()) {
 	confirmationMsg = uplBean._adminConfirmationMsg;
 	uplBean._adminConfirmationMsg = null;
 }
%>
<html>
<head>
   <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
   <title><ivu:int key="UploadWahkreiswahldaten"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
      <style type="text/css">
      td {line-height: 15px}
      </style>
</head>
<body class="hghell">
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <%@include file="/jsp/fragments/help_row.jspf"%>
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20">Upload</td>
            </tr>
            <tr>
               <td colspan="3" class="hgschwarz"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
            </tr>
<% if (appBean.isInputDisabled()){ // %>
            <tr>
               <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
            </tr>
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau">
   					<ivu:int key="UploadNichtMoeglich_Eingabesperre"/>
               </td>
            </tr>
<% } else {
		if (!appBean.isInputDisabled()){
			%>
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
                           <legend><b><ivu:int key="ErfassungsgebietHochladen"/></b></legend><br /><% 
                           	if (errorMsg != null){%>
											<p style="color:red;"><b><%= ClientHelper.forHTML(errorMsg) %></b></p><% 
										}
                           	if (confirmationMsg != null){%>
											<p style="color:green;"><b><%= ClientHelper.forHTML(confirmationMsg) %></b></p><% 
										}
                              if (admBean.getProperty(Konstanten.PROP_UPLOADDIR) != null){
                                 String url = "/osv?cmd=upl_Upload_EE&" + ClientHelper.getParametersDoNotStartWith(request, ApplicationBeanKonstanten.PREFIX);
                                 %>
                                 <ivu:form target="_top" action="<%=url%>" enctype="multipart/form-data" method="post">
                                    <table border="0" width="<%= breite %>" cellspacing="0" cellpadding="0">
                                       <tr>
                                          <td>
                                             <ivu:int key="ErgebnisdateiHochladenInfo"/>
                                          </td>
                                       </tr>
                                       <tr>
                                          <td>
                                             &nbsp;
                                          </td>
                                       </tr>
                                       <tr>
                                          <td>
                                             <ivu:int key="Uploaden"/>
                                          </td>
                                       </tr>
                                       <tr valign="top">
                                          <td>
                                             <ol>
                                                <li><ivu:int key="DateiAuswaehlen"/><br />
                                                <li><ivu:int key="ErgebnisdateiHochladen"/><br />
                                                <li><ivu:int key="VorgangZeitInAnspruchNehmen"/><br />
                                             </ol>
                                          </td>
                                      </tr>
                                      <tr>
                                          <td>
                                             <table border="0">
                                                <tr>
                                                   <td width="30">
                                                      &nbsp;
                                                   </td>
                                                   <td>
                                                      <input type="file" name="filename" size="25" style="font-size:11px" accept="*"/>
                                                   </td>
                                                   <td width="30">
                                                      &nbsp;
                                                   </td>
                                                   <td>
                                                      <input id="box2" style="cursor:pointer" type="submit" name="<%=ApplicationBeanKonstanten.PREFIX + "hochladen"%>" value="<%= BundleHelper.getBundleString("Hochladen") %>" />
                                                   </td>
                                                </tr>
                                             </table>
                                          </td>
                                       </tr>
                                    </table>
                                 </ivu:form>
                              <%
                              } else { //
                              %>
                                 <ivu:int key="KeinZielverzeichnisAngegeben"></ivu:int>
                              <%
                              }
                              %>

                           </fieldset>
                        </td>
                     </tr>
                  </table>
               </td>
               <td width="10">&nbsp;</td>
            </tr>
<%
} 
}
%>
         </table>
      </td>
   </tr>
</table>
</body>
</html>
