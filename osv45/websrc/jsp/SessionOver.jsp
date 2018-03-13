<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<html>
	<head>
		<title><ivu:int key="Sitzung_abgelaufen"/></title>
		<style>
table {
	border: 2px solid #093C69;
}
td {
	font-family: Verdana, Arial, Helvetica;
	color: #093C69;
	font-size: 11px
}
</style>
		<link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico" />
		<link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico" />
	</head>
	<body class="std" onload="javascript:if (window != top) {top.location.replace(window.location.href)};">
		<br>
		<table width="60%" align="center" border="0" bgcolor="#F9FAFD"
			cellpadding="5" />
			<tr>
				<td colspan="2">
					<h3>
						<ivu:int key="Sitzung_abgelaufen"/>
					</h3>
				</td>
			</tr>
			<tr>
				<td>
					<img src="<%= request.getContextPath() %>/img/icon/icon_time_message.gif" border="0">
				</td>
				<td>
					<ivu:int key="Sitzung_abgelaufen_info_1"/> <%=request.getSession(true).getMaxInactiveInterval() / 60%> <ivu:int key="Sitzung_abgelaufen_info_2"/>
					<br>
				</td>
			</tr>
			<tr>
				<td></td>
				<td height="30">
				   <ivu:int key="Sitzung_abgelaufen_info_3" /> <a href='<%= request.getContextPath() + "/osv/logon?r=" + ClientHelper.getRandomString() %>' target='_top'> <ivu:int key="Sitzung_abgelaufen_info_4" /></a>
				</td>
			</tr>
		</table>
	</body>
</html>
