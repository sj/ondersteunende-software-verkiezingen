<%--
   Fehlerseite
--%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ page import="de.ivu.wahl.client.util.ClientHelper"%>
<%@ page import="de.ivu.wahl.client.LogoutException"%>
<%@ page import="java.io.CharArrayWriter"%>
<%@ page import="java.io.PrintWriter"%>
<html>
	<head>
		<title><ivu:int key="MainErrorPage.1"/></title>
		<style type="text/css">
         table {border:3px solid #CC3333;}
         td {font-family: Verdana, Arial, Helvetica; font-size: 11px;}
		</style>
		<link rel="SHORTCUT ICON" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico" />
		<link rel="icon" href="<%= request.getContextPath() %>/FAVICON.ICO" type="image/ico" />
	</head>
	<body class="std"
		onload="javascript:if (window != top) {top.location.replace(window.location.href)};">
		<br />
		<table width="60%" align="center" border="0" bgcolor="#FAFAFA" cellpadding="5">
			<tr>
				<td colspan="2">
					<h3><ivu:int key="MainErrorPage.2"/></h3>
				</td>
			</tr>
			<tr>
				<td><img src="<%= request.getContextPath() %>/img/icon/icon_mainError_message.gif" border="0" alt=""></td>
				<td><%
					if (exception instanceof LogoutException) {%>
						<ivu:int key="MainErrorPage.3"/> <%=exception.getMessage()%><%
            		try { // Custom Tags können Exceptions verursachen %>
							      <ivu:a href='/osv'><ivu:int key="MainErrorPage.4"/></ivu:a><%
						    } catch (NullPointerException npe) {
	            		%><a href='<%=request.getContextPath()+"/osv/logon"%>' target='_top'><ivu:int key="MainErrorPage.5"/></a><%
	            	}
	            } else { //%>
	            	<ivu:int key="MainErrorPage.6"/><%
     						if (exception == null) { //%>
     							<ivu:int key="MainErrorPage.7"/><%
     						} else {%>
     							<ivu:int key="MainErrorPage.8"/> <%=exception.getMessage()%><br /> <%
     						}%>
     						<p><a	href='<%= request.getContextPath() + "/osv/logon?r=" + ClientHelper.getRandomString() %>' target='_top'><ivu:int key="MainErrorPage.5"/></a></p><%
                 if (exception != null) {%>
   						    <p><ivu:int key="MainErrorPage.9"/></p>
		   			    	<pre><font color="red"><%
				    			CharArrayWriter cw = new java.io.CharArrayWriter();
            			PrintWriter pw = new java.io.PrintWriter(cw, true);
            			exception.printStackTrace(pw);
            			out.println(cw.toString());
            			System.out.println(cw.toString());%>
            		</font></pre><%
              }
	         }%>
				</td>
			</tr>
		</table>
	</body>
</html>