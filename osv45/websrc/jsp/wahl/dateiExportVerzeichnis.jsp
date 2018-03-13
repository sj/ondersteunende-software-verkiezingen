<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="uplBean" scope="session" class="de.ivu.wahl.client.beans.UploadBean" />
<%  
String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
String helpKey = "dateiExpVerzeichnis"; //$NON-NLS-1$

String breite = "100%";
%>
<html>
<head>
   <title>ExportDir</title>
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
               <td class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="19"><ivu:int key="ExportVerzeichnis"/></td>
            </tr>
            <tr>
               <td colspan="3" class="hgschwarz"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
            </tr>
	         <tr>
               <td colspan="3"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="10"></td>
            </tr>
            <tr>
               <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="10" height="1"></td>
               <td valign="top">
                  <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
                     <tr>
                        <td valign="top">
                             <% if (admBean.getProperty(Konstanten.PROP_EXPORT_FORMULAR_DIR) != null){ %>
                              	<iframe marginheight="0" marginwidth="0" frameborder="0" style="background-color: #CCCCCC;" align="top" src="/<%= SystemInfo.getSystemInfo().getModusklartext()+"_"+SystemInfo.getSystemInfo().getEbenenklartext()+SystemInfo.getSystemInfo().getInstallationSuffix()+"-" %>export-map" width="100%" height="600"/>
                             <% } else { %>
                           <fieldset style="border: 1px solid #093C69; padding: 15px">
                           <legend><b><ivu:int key="Export_Verzeichnis_titel"/></b></legend><br />
                                 <ivu:int key="KeinZielverzeichnisAngegeben"></ivu:int>
                           </fieldset>
                             <% } %>
                        </td>
                     </tr>
                  </table>
               </td>
               <td width="10"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="10" height="1"></td>
            </tr>
         </table>
      </td>
   </tr>
</table>
</body>
</html>