<%--
 *******************************************************************************
 * naivgation zu einem Wahlkreis
 * Teil der Navigation zu einem bestimmten Wahlkreis, der über Eingabe der
 * Wahlkreisnummer erreicht werden kann
 *
 * Besonderheit: Überprüfung der Eingabe mit JavaScript
 *
 * author:  mur@ivu.de  Copyright (c) 2002-7 Statistisches Bundesamt und IVU Traffic Technologies AG
 *******************************************************************************
 --%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.dataimport.AbstractImportEML"%>
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page errorPage="/jsp/MainErrorPage.jsp"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
 <jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
 
<%
String newLocation = "/osv?" + ClientHelper.getSuffix(request);
boolean isEkP4Hsb = (WahlInfo.getWahlInfo().isEK() && SystemInfo.getSystemInfo().getWahlModus() == AbstractImportEML.MODE_DB_P4 && SystemInfo.getSystemInfo().getWahlEbene() == GebietModel.EBENE_HSB);
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <title><ivu:int key="Direktadressierung"/></title>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <style>
   input {
         background-color : #F5F5F5;
         font-size : 1em;
         color : #000000;
   }
   </style>
</head>
<script language="JavaScript">
<!--
function validate() {

<%= appBean.getGebietsBaum().getJavaScriptForNaviPart()%>
	var val = document.wahlkreis.<%=ApplicationBeanKonstanten.GEBIETNR%>.value;
	if (val == ""){
	   alert(<%= BundleHelper.getBundleString("FeldMussMitWertGefuelltSein2") %>);
	   document.wahlkreis.<%=ApplicationBeanKonstanten.GEBIETNR%>.value = '';
	   return false;
	}
	// Führende Nullen werden abgeschnitten
	while (val != "0" &&  val.indexOf("0") == 0){
	   val = val.substr(val.indexOf("0")+1);
	}
	// Roman numerals to arabic
	val = romanToArabic(val);
	if ( document.wahlkreis.<%=ApplicationBeanKonstanten.GEBIETNR%>.type == "text"  && val != parseInt(val)){
	   alert(<%= BundleHelper.getBundleString("WertIstKeineZahl2") %>);
      document.wahlkreis.<%=ApplicationBeanKonstanten.GEBIETNR%>.value = '';
      return false;
	}
	for (var j = 0; j < intervalle.length; j++) {
	   var interval = intervalle[j];
	   if (val >= interval[0] && val <= interval[1]){
	      document.wahlkreis.<%=ApplicationBeanKonstanten.LEVEL%>.value = interval[2];
	      document.wahlkreis.<%=ApplicationBeanKonstanten.GEBIETNR%>.value = val;
	      return true;
	   }
	}
   alert(<%= BundleHelper.getBundleString("GebietnummerKeineErfassungseinheit") %>);
   document.wahlkreis.<%=ApplicationBeanKonstanten.GEBIETNR%>.value = '';
   return false;
}

function romanToArabic(value) {
   var result = value;
   switch (value) {
      case "I":
      case "i":
         result = 1;
         break;
      case "II":
      case "ii":
         result = 2;
         break;
      case "III":
      case "iii":
         result = 3;
         break;
      case "IV":
      case "iv":
         result = 4;
         break;
      case "V":
      case "v":
         result = 5;
         break;
      case "VI":
      case "vi":
         result = 6;
         break;
      case "VII":
      case "vii":
         result = 7;
         break;
      case "VIII":
      case "viii":
         result = 8;
         break;
      case "IX":
      case "ix":
         result = 9;
         break;
      case "X":
      case "x":
         result = 10;
         break;
      case "XI":
      case "xi":
         result = 11;
         break;
      case "XII":
      case "xii":
         result = 12;
         break;
      case "XIII":
      case "xiii":
         result = 13;
         break;
      case "XIV":
      case "xiv":
         result = 14;
         break;
      case "XV":
      case "xv":
         result = 15;
         break;
      case "XVI":
      case "xvi":
         result = 16;
         break;
      case "XVII":
      case "xvii":
         result = 17;
         break;
      case "XVIII":
      case "xviii":
         result = 18;
         break;
      case "XIX":
      case "xix":
         result = 19;
         break;
      case "XX":
      case "xx":
         result = 20;
         break;
   }
   return result;
}
//-->
</script>
<body onload="document.wahlkreis.<%=ApplicationBeanKonstanten.GEBIETNR%>.focus()" class="hgdunkel" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<ivu:form onsubmit="return validate() " name="wahlkreis" action="<%=newLocation%>">
   <input type='hidden' name='<%=ApplicationBeanKonstanten.LEVEL%>' value='-1'></input>
   <table border="0" cellpadding="0" cellspacing="0">
      <tr>
         <td width="1"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" width="1" height="1"></td>
      <% if (!isEkP4Hsb) { %>
         <td height="40"><img src="<%= request.getContextPath() %>/img/icon/blind.gif" border="0" width="8" height="8">
            <span class="linkhell"><ivu:int key="Erfassungseinheit_Nr"/>:&nbsp;&nbsp;</span>
         </td>
         <td>
            <input type="text" size="6" title="<%=BundleHelper.getBundleString("Erfassungseinheit_Nr_eingeben") %>" name="<%=ApplicationBeanKonstanten.GEBIETNR%>" />
         </td>
         <td>
            <input type="image" src="<%= request.getContextPath() %>/img/icon/pfeil_right_breit.gif" border="0" alt="los!"/>
         </td>
      <% } %>
      </tr>
   </table>
</ivu:form>
</body>
</html>
