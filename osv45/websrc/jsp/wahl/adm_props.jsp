<%--
 *******************************************************************************
 * Grundeinstellung ändern
 * Arbeits-JSP für die Administration von Properties. Dies sind insbesondere auch die
 * für Ausgaben und Eingaben verwendeten Verzeichnisse.
 *
 * author:  klie@ivu.de mur@ivu.de cos@ivu.de  
 * Copyright (c) 2002-17 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: adm_props.jsp,v 1.25 2011/03/31 12:36:03 tdu Exp $
 *******************************************************************************
 --%>
<jsp:directive.page import="de.ivu.wahl.client.util.ClientHelper" />
<jsp:directive.page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" />
<jsp:directive.page import="de.ivu.wahl.Konstanten" />
<jsp:directive.page import="de.ivu.wahl.Basiseinstellung"/>
<jsp:directive.page import="de.ivu.wahl.client.util.GUICommand"/>
<jsp:directive.page import="java.util.List"/>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.i18n.Messages"%>
<%@ page import="de.ivu.wahl.i18n.MessageKeys"%>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/jsp/MainErrorPage.jsp"  %>
<jsp:useBean id="admBean" scope="session" class="de.ivu.wahl.client.beans.AdministrationBean" />
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<%
   String backgroundColor = appBean.getBackgroundColor(); // used in included jspf
   String breite = "100%"; //$NON-NLS-1$
   String prefix = ApplicationBeanKonstanten.PREFIX;
   int subwork = ClientHelper.getIntParameter(request.getParameter(prefix + "subwork"), 0); //$NON-NLS-1$
   SystemInfo systemInfo = SystemInfo.getSystemInfo();
   WahlInfo wahlInfo = appBean.getWahlInfo();
   boolean freigegeben = appBean.isFreigegeben(wahlInfo.getAktuelleWahlergebnisart());
   
   boolean isReferendum = wahlInfo.isReferendum();
   String helpKey = "admPropsInputMode";
   if (isReferendum) {
   	   helpKey = "admPropsInputModeRef";
   }
%>

<html>
<head>
   <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
   <style type="text/css">
        td {
         line-height: 15px;
        }
   
      .feld {
        min-width: 100%;
        font-size: 1em;
        height: 1.6em;
        border-style: solid;
        border-width: 1px;
      }
      
      .lr {
        border-width: 1px 0px 1px 1px; 
        color: graytext;
      }
      
      .r {
        border-width: 1px 1px 1px 0px; 
      }
      
      .fgrau {
       border-color: gray;
      }
      
      .frot {
       border-color: #D00;
      }
      
      .checkbox {
      }
      
      .checkbox_rot, div[class="checkbox_rot"] {
        border: 1px solid #D00;
      }
      
      .textrot {
       color: #D00;
       border: 1px solid #D00;
       padding: 0px 2px;
       font-weight: bold;
      }
   </style>
   <script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/sc.js"></script>
</head>

<body class="hghell" onload="sc()">
<table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" align="center" class="hghell">
   <%@include file="/jsp/fragments/help_row.jspf"%>
   <tr>
      <td valign="top">
         <table width="<%= breite %>" border="0" cellspacing="0" cellpadding="0" class="hghell">
            <tr>
               <td width="5" class="hggrau">&nbsp;</td>
               <td colspan="2" class="hggrau" height="20"><ivu:int key="Grundeinstellungen_aendern"/></td>
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
                  <% if (!appBean.getINPUT_MAP().isEmpty() || freigegeben){ %>
                  <table width="100%" cellspacing="0" cellpadding="1" border="0">
				          <tbody><tr>
				           <td valign="top">
				            <fieldset style="border: 1px solid rgb(9, 60, 105); padding: 15px;">
				             <legend><b><ivu:int key="Grundeinstellungen_aendern_info_4"/></b></legend><br/>
				
				             <table width="100%" cellspacing="0" cellpadding="0" border="0">
				              <tbody><tr>
				               <td colspan="3"><img height="10" width="1" src="/P4_PSB/img/icon/blind.gif"/></td>
				              </tr>
				              <tr>
				               <td width="10"><img height="1" width="1" src="/P4_PSB/img/icon/blind.gif"/></td>
				               <td valign="top">  
				    						<p>
				    							<%if (!appBean.getINPUT_MAP().isEmpty()){ %>
				    							<%= Messages.bind(MessageKeys.Error_GebietseingabeLaeuft) %>
				    							<% } else if (freigegeben){ %>
				    								<ivu:int key="Freigabe_bereits_erfolgt"/><br/><ivu:int key="Freigabe_bereits_erfolgt_KeineAdminstrationGrundeinstellung"/>
				    							<% } %>
				    						</p>
				                </td>
				               <td width="10"> </td>
				              </tr>
				             </tbody></table>
				            </fieldset>
				           </td>
				          </tr>
				         </tbody>
				       </table>
  	               <%} else { %>
	                  <table border="0" cellspacing="0" cellpadding="1" width="<%= breite %>">
	                     <tr>
	                       <td>
	                        <fieldset style="border: 1px solid #093C69; padding: 0em 1em 1em; ">
	                           <legend><b><ivu:int key="Grundeinstellungen_aendern_info_4"/></b></legend><br />
	                             	<% if (admBean._adminMsgProps != null && !admBean._adminMsgProps.isEmpty() ){%>
												<p style="color:red;"><b><%= admBean._adminMsgProps %></b></p>
											<% 
												admBean._adminMsgProps = null;											
	                             	 }
	                             	 if (admBean._adminMsgPropsConfirmation != null && !admBean._adminMsgPropsConfirmation.isEmpty() ){%>
												<p style="color:green;"><b><%= admBean._adminMsgPropsConfirmation %></b></p>
											<% 
												admBean._adminMsgPropsConfirmation = null;											
	                             	 }%>
											<br/>
	                             <table border="0" cellspacing="0" cellpadding="0" width="<%= breite %>">
	                              <tr>
	                               <td height="20" valign="top" class="guiBefehle"><%
	                                int buttonIdx = 0;
	                                String selectedKey = null;
	                                for (String buttonName : Konstanten.PROP_ALLG.keySet()) {
	                                  String url = ClientHelper.generateURL(request, ClientHelper.getWork(request), true)
	                                                   + "&" + ApplicationBeanKonstanten.PREFIX + "subwork=" + buttonIdx;
	                  
	                                  boolean selected = buttonIdx++ == subwork;
	                                  if (selected) {
	                                   selectedKey = buttonName;
	                                  }
	                                  String styleClass =  GUICommand.GUI_CLASS_1; %> 
	                                  <ivu:a clazz='<%=  styleClass%>' href='<%=url%>' ><%=buttonName%></ivu:a><%
	                                 }%>
	                                 </td>
	                                </tr><%
	                                boolean isBackgroundColor = false;
	                                if (selectedKey != null) {
	                                 String formurl = "/osv?cmd=adm_propEingabe&" + ApplicationBeanKonstanten.PREFIX + "subwork=" + subwork 
	                                  + "&" + ClientHelper.getParametersDoNotStartWith(request, ApplicationBeanKonstanten.PREFIX); %>
	                                 <tr>
	                                  <td style="border: 1px solid rgb(9, 60, 105); padding-left: 1em width: 100%;">
	                                   <ivu:form name="props" action="<%= formurl %>" >
	                                   <table border="0" cellspacing="4" cellpadding="1" width="<%= breite %>" style="padding: 1em;">
	                                    <%
	                                    List<Basiseinstellung> basiseinstellungen = Konstanten.PROP_ALLG.get(selectedKey);
	                                    for (Basiseinstellung basiseinstellung : basiseinstellungen) {
	                                     if (Konstanten.PROP_IS_INPUT_MODE_COMPLETE.equals(basiseinstellung.getProperty()) && isReferendum) {
	                                     	// necessary because on first program start Konstanten are defined before wahlInfo exists 
	                                     	continue;
	                                     }
	                                     boolean restart = basiseinstellung.isRestart();
	                                     String styleClass = restart ? "frot" : "fgrau";
	                                     String styleClassChb = restart ? "checkbox_rot" : "checkbox"; 
	                                     String styleChb = restart ? "border: 1px solid #D00;" : "";%>
	                                     <tr title="<%= basiseinstellung.getHinweis() %>">
	                                      <td><%= basiseinstellung.getBeschreibung() %></td>
	                                      <td>
	                                      <%
	                                       String property = basiseinstellung.getProperty();
	                                       if (Konstanten.PROP_BACKGROUND_COLOR_RED.equals(property) || Konstanten.PROP_BACKGROUND_COLOR_GREEN.equals(property) || Konstanten.PROP_BACKGROUND_COLOR_BLUE.equals(property)) {
	                                         isBackgroundColor = true;
	                                       }
	                                       String wert = admBean.getProperty(property);
	                                       session.setAttribute(property, wert);
	                                       property = ApplicationBeanKonstanten.PREFIX + property;
	                                       switch (basiseinstellung.getTyp()) {
	                                        case RelURL:
	                                         // Es können nicht gesetzte Werte als null kommen!
	                                         if (wert == null) {
	                                          wert = "";
	                                         }%>
	                                         <table border="0" cellspacing="0" cellpadding="0" width="100%" style="padding: 0px;">
	                                          <tr>
	                                           <td><input type="text" readonly="readonly" class="feld <%=styleClass%> lr" value="${admBean.baseURI}/"></td>
	                                           <td><input type="text" class="feld <%=styleClass%> r" style="border-left-width: 0px;" size="40" name="<%=property%>" value="<%=wert%>"></td>
	                                          </tr>
	                                         </table><%
	                                         break;
	                                         
	                                        case String:
	                                        case Date:
	                                        case ZIP:
	                                         // Es können nicht gesetzte Werte als null kommen!
	                                         if (wert == null) {
	                                          wert = "";
	                                         }%>
	                                         <input type="text" class="feld <%=styleClass%>" size="64" name="<%=property%>" value="<%=wert%>"><%
	                                         break;
	                                        case Textarea:
	                                         // Es können nicht gesetzte Werte als null kommen!
	                                         if (wert == null) {
	                                          wert = "";
	                                         }%>
	                                         <textarea class="feld <%=styleClass%>" cols="64" rows="10" name="<%=property%>"><%=wert%></textarea><%
	                                         break;
	                                         
	                                        case Integer:
	                                         // Es können nicht gesetzte Werte als null kommen!
	                                         if (wert == null) {
	                                            wert = "0";
	                                         }%>
	                                         <input type="text" class="feld <%=styleClass%>" size="10" name="<%=property%>" value="<%=wert%>"><%
	                                         break;
	                                         
	                                        case Boolean:
	                                         boolean bWert = "true".equals(wert); %>
	                                         <div class="<%=styleClassChb%>">
	                                          <input type="checkbox" style="<%=styleChb %>" <%=bWert ? "checked" : ""%> name="<%=property%>" value="x" />
	                                         </div><%
	                                         break;
	                                         
	                                        default: %>
	                                         <%= BundleHelper.getBundleString("FormatUnbekannt")%><%
	                                       } %>
	                                      </td>
	                                      <td>
	                                      	<%=basiseinstellung.getHinweis()%>
	                                      </td>
	                                     </tr><%
	                                    } %>
	                                   </table>
	                                   <div align="center" style="margin-top: 1em; margin-bottom: 1em;">
	                                     <input id="box2" type="submit" value="<%= BundleHelper.getBundleString("Uebernehmen") %>" name="<%=ApplicationBeanKonstanten.PREFIX%>propuebernehmen"><% 
	                                     if (isBackgroundColor)  { %>
	                                       <input id="box2" type="submit" value="<%= BundleHelper.getBundleString("Reset_color") %>" name="<%=ApplicationBeanKonstanten.PREFIX%>propuebernehmen"><%
	                                     } %>
	                                   </div>
	                                   </ivu:form>
	                                  </td>
	                                 </tr><%
	                                } %>
	                               </table>
	                               <c:if test="${restart == 'true'}">
	                                <div class="textrot"><ivu:int key="EinstellungGeaendertNeustart"/></div>
	                               </c:if>
	                         </fieldset>
	                        </td>
	                     </tr>
	                  </table>
                  <% } %>
                  
               </td>
               <td width="10">&nbsp;</td>
            </tr>
         </table>
      </td>
   </tr>
</table>
</body>
</html>
