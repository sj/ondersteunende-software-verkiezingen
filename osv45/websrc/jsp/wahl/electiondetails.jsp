<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten"%>
<%@ page import="de.ivu.wahl.modell.Wahldaten"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
 *******************************************************************************
 * Election details
 *
 * Shows details of the election definition file loaded.
 *
 * author:  tdu@ivu.de Copyright (c) 2010 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
   String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
   String helpKey = "gebietErg"; //$NON-NLS-1$
   
   WahlInfo wahlInfo = appBean.getWahlInfo();
   WahlModel wahl = wahlInfo.getWahl();

   String breite ="100%";

%>

<html>
<head>
   <title><ivu:int key="XXX_1"/> </title>
   <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/wahl2002.css">
      <style type="text/css">
      td {font-size:10px; line-height:15px}
      </style>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="hgeeeeee">
<%@include file="/jsp/fragments/print_and_help_row.jspf"%>
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <tr>
      <td colspan="3" class="hgschwarz"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td class="hggrau">&nbsp;</td>
      <td class="hggrau" height="19" colspan="2"><ivu:int key="ElectionDetails_headline" /></td>
   </tr>
   <tr>
      <td colspan="3" class="hgschwarz"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
   </tr>
   <tr>
      <td colspan="3" class="hghell"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
   </tr>
   <tr>
      <td width="10"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
      <td valign="top" colspan="2">
   		<table border="0" cellspacing="0" cellpadding="1" width="99%">
            <tr class="hgrot">
               <td valign="top">
                  <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hgweiss">
                     <tr>
                        <td colspan="3"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="5"></td>
                     </tr>
                     <tr>
                        <td width="5"><img alt="" src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                        <td valign="top">
                           <table border="0" width="<%= breite %>" cellspacing="2" cellpadding="4" align="center">
		                     <% int j = -1; %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="ElectionID" />:</b></td>
								 <td><%= ClientHelper.forHTML(wahl.getID_Wahl()) %></td>
							 </tr>
		                     <%
		                     	j = -j;
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="ElectionName" />:</b></td>
								 <td><%= ClientHelper.forHTML(wahl.getName()) %></td>
							 </tr>
		                     <%
		                     	j = -j;
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="ElectionCategory" />:</b></td>
								 <td><%= ClientHelper.forHTML(wahl.getWahlkategorie()) %></td>
							 </tr>
		                     <%
		                     	j = -j;
		                     	String electionDomain = wahl.getElectionDomain();
		                     	if (electionDomain != null && electionDomain.length() > 0) {
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="<%= "ElectionDomain" + wahl.getWahlkategorie().substring(0, 2) %>" />:</b></td>
								 <td><%= ClientHelper.forHTML(electionDomain) %></td>
							 </tr>
		                     <%
		                     		j = -j;
		                     	}
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="ElectionDate" />:</b></td>
								 <td><%= wahl.getTermin().toString().substring(0, 10) %></td>
							 </tr>
		                     <%
		                     	j = -j;
		                     	if (wahl.getDatumNominierung() != null) {
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="NominationDate" />:</b></td>
								 <td><%= wahl.getDatumNominierung().toString().substring(0, 10) %></td>
							 </tr>
		                     <%
		                     		j = -j;
		                     	}
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="NumberOfSeats" />:</b></td>
								 <td><%= wahl.getAnzahlSitze() %></td>
							 </tr>
		                     <%
		                     	j = -j;
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="PreferenceThreshold" />:</b></td>
								 <td><%= wahl.getVorrangschwelle() %></td>
							 </tr>
		                     <%
		                     	j = -j;
		                     %>
							 <tr class="<%= j>0 ?"hgweiss":"hgeeeeee" %>">
								 <td><b><ivu:int key="ElectionDefinitionCreationDateTime" />:</b></td>
								 <td><%= wahl.getStandMetadaten().toString().substring(0, 19) %></td>
							 </tr>
                           </table>
                        </td>
                        <td width="5">&nbsp;</td>
                     </tr>
                     <tr>
                        <td height="5" colspan="5"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
                     </tr>
                  </table>
               </td>
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