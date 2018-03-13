<%@ page import="de.ivu.wahl.client.beans.ApplicationBean"%>
<%@ page import="de.ivu.wahl.Konstanten"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
	String arch = ""; //$NON-NLS-1$
	String val = appBean.getPropertyHandling().getProperty(Konstanten.PROP_SYSTEM_ARCHITECTURE_BITS);
	if (val != null) {
		arch = " (" + val + BundleHelper.getBundleString("SystemArchitectureBits") + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
  <head>
    <title>Info.html</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/wahl2002.css">

  </head>
  
  <body>
    <table>
    	<tr>
    		<td valign="top">
    			<img src="<%= request.getContextPath() %>/img/logo/ivu.gif">
    		</td>
    		<td valign="top" style="max-width: 34em;">
    			Programma versie: <%=ApplicationBean.getVersionString() + arch%><br/><br/>
				IVU Traffic Technologies AG<br/>
				Bundesallee 88<br/>
				12161 Berlijn<br/>
				Duitsland<br/><br/>
				<a href="http://www.osv-support.nl" target="_blank">www.osv-support.nl</a>
				<br/><br/><br/><br/><br/>
				Speciale vermelding:<br/><br/>
				Meerdere iconen die in dit programma gebruikt worden, zijn gemaakt door Mark James, en gelicentieerd onder de Creative Commons Attribution License 2.5.<br/><br/>
				www.famfamfam.com/lab/icons
    		</td>
    	</tr>
    </table>
  </body>
</html>
