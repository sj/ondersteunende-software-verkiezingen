<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu"%>
<%@ page import="de.ivu.wahl.modell.WahlModel"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<% if (appBean.getWahlInfo().getStatus() == WahlModel.STATE_CALCULATION_CONFLICT){ %><div style="background: lightGoldenRodYellow; padding-top: 6px; padding-bottom: 6px;">
		<center>
			<span class="rot"><ivu:int key="ACHTUNG_ZWISCHENSTAND"/></span>
		</center>
	</div>
	<div class="hgschwarz" style="height: 1px; line-height: 1px; width: 100%;">
		&nbsp;
	</div><% } %>