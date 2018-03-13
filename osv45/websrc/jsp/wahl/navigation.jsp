<%--
 *******************************************************************************
 * Navigation
 * Teil der Navigation zu Gebieten
 *
 * author:  mur@ivu.de, bae@ivu.de, cos@ivu.de  
 * Copyright (c) 2002-2007 Statistisches Bundesamt und IVU Traffic Technologies AG
 * $Id: navigation.jsp,v 1.25 2010/12/09 10:40:14 jon Exp $
 *******************************************************************************
--%>
<%@ page import="de.ivu.wahl.client.beans.ApplicationBeanKonstanten" %>
<%@ page import="de.ivu.wahl.client.beans.Command" %>
<%@ page import="de.ivu.wahl.client.util.ClientHelper" %>
<%@ page import="de.ivu.wahl.modell.GebietInfo" %>
<%@ page import="de.ivu.wahl.SystemInfo"%>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="javax.swing.tree.DefaultMutableTreeNode" %>
<%@ page import="de.ivu.wahl.AnwContext" %>
<%@ page import="de.ivu.wahl.GebietsBaum"%>
<%@ page import="de.ivu.wahl.WahlInfo"%>
<%@ page import="de.ivu.wahl.auswertung.erg.NavigationErgebnis" %>
<%@ page import="de.ivu.wahl.client.beans.InitGuiCommand"%>
<%@ page import="de.ivu.wahl.modell.ErgebniseingangModel" %>
<%@ page import="de.ivu.wahl.modell.ErgebniseingangKonstanten"%>
<%@ page import="de.ivu.wahl.modell.GebietModel"%>
<%@ page import="de.ivu.wahl.modell.ejb.GebietBean"%>
<%@ page import="de.ivu.wahl.util.BundleHelper"%>
<%@ page import="de.ivu.wahl.dataimport.AbstractImportEML"%>
<%@ page import="de.ivu.wahl.dataimport.ImportEML510"%>
<%@ taglib uri="http://www.ivu.de/taglibs/ivu-wahl-1.0" prefix="ivu" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<jsp:useBean id="appBean" scope="session" class="de.ivu.wahl.client.beans.ApplicationBean" />
<jsp:useBean id="navBean" scope="request" class="de.ivu.wahl.client.beans.NavigationBean" />
<jsp:useBean id="ergImpBean" scope="session" class="de.ivu.wahl.client.beans.ErgebnisImportBean" />
<%
 AnwContext context = appBean.getAnwContext();
 if (context == null) {//
  %><jsp:forward page="/jsp/SessionOver.jsp"/><%
 }

 SystemInfo systemInfo = SystemInfo.getSystemInfo();

 GebietsBaum gebietBaum = appBean.getGebietsBaum();
 GebietInfo rootInfo = (GebietInfo)gebietBaum.getWurzel().getUserObject();
 int gebietsArt = ClientHelper.getLevel(request, rootInfo.getGebietsart());
 int nr = ClientHelper.getGebietNr(request, rootInfo.getNummer());  
 DefaultMutableTreeNode node = gebietBaum.getGebietsNode(gebietsArt, nr);
 GebietInfo selectedGebietInfo = node != null ? (GebietInfo)node.getUserObject() : rootInfo;

 String aktuellerNodePath = request.getParameter(ApplicationBeanKonstanten.NAVI_ANKER);
 if (aktuellerNodePath == null) { 
   aktuellerNodePath = "0_" + selectedGebietInfo.getNodePath();
 }

 
 NavigationErgebnis navErgebnis = navBean.getNavigationTree(appBean, aktuellerNodePath);
 List<DefaultMutableTreeNode> treeList = navErgebnis.getList();
 InitGuiCommand gui = appBean.getInitGuiCommand();

 WahlInfo wahlInfo = WahlInfo.getWahlInfo();
 int rootLevel = wahlInfo.getGebietsartWurzelgebiet();
 int rootGebNr = wahlInfo.getGebietsnummerWurzelgebiet();
 ImportEML510 impDef = ergImpBean.getImportEML510(rootLevel, rootGebNr);
 boolean isEkP4Hsb = (WahlInfo.getWahlInfo().isEK() && systemInfo.getWahlModus() == AbstractImportEML.MODE_DB_P4 && systemInfo.getWahlEbene() == GebietModel.EBENE_HSB);
  
 
 
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
 <head>
  <title><ivu:int key="Navigation"/></title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
  <style type="text/css">
   body {
    overflow:auto;
    overflow-x:hidden;
   }
   .anzahl {
    text-align:right;
    padding-right:1px;
    white-space:nowrap;
    float:right;
   }
   .fl {
    float:left;
    vertical-align:bottom;
    height:15px;
   }
   img {
    margin:0;
    border:0;
    padding:0;
   }
   img.c5 {
    width:5px;
    height:13px;
    vertical-align:bottom;
   }
   img.c9 {
    width:9px;
    height:13px;
    vertical-align:bottom;
   }
   span.c0 {
    margin-left:0px;
   }
   span.c4 {
    margin-left:4px;
   }
   span.c9 {
    margin-left:9px;
   }
   span.c13 {
    margin-left:13px;
   }
   span.c18 {
    margin-left:18px;
   }
   a.adminMessage {
    background-image: url('/img/icon/pfeil_right.gif'); 
    background-repeat: no-repeat; 
    text-indent: 19px; 
    line-height: 17px;
    width: 100%; 
    border-bottom-color: #DDD !important;    
   }
   
   a.hgdunkel {
    border-bottom-color: #CCC !important;    
   }
   
   a:hover, a:active {
    color: #333 !important;
    background-color: #FFFFFF;
   }
	div.navi div.level2 span {
	 display:inline;
	}
  </style>
 </head>
 <body leftmargin='0' topmargin='0' marginwidth='0' marginheight='0' class='hggrau2' style='overflow-x: hidden;'>
  <div class="hgrot" style="border-bottom: 1px solid #999; line-height: 19px; text-indent: 8px;"><ivu:int key="Auswahl"/>:</div>
  <div class="navi"><%
  String   newLocation;
   
   if (gui.isAdminVorhanden()) {
    // Administration
    int level = ClientHelper.getLevel(request, ApplicationBeanKonstanten.LEVEL_ADMIN);
    newLocation =  ClientHelper.getSuffixLevel(request, ApplicationBeanKonstanten.LEVEL_ADMIN)+
                  "&" + ClientHelper.workIs(gui.getAdminWorkDefault());
%>
   <ivu:a href='<%="/osv?"+newLocation %>' clazz='<%= ApplicationBeanKonstanten.LEVEL_ADMIN != level ? "adminMessage":"hgweiss adminMessage"%>'>
     <ivu:int key="Administration"/>
   </ivu:a><%
   }

     // Root-Ebene
    GebietInfo gebiet = (GebietInfo)gebietBaum.getWurzel().getUserObject();
    String subInfo = " ("+gebiet.getAnzahlEingegangen()+" "+BundleHelper.getBundleString("Navigation_n_von_m")+" "+gebiet.getAnzahlGesamt()+")";
	
    int level = ClientHelper.getLevel(request, gebiet.getGebietsart());
    Command work = gui.getGebieteWorkDefault();
    if (systemInfo.getWahlModus() == AbstractImportEML.MODE_DB_P5
    	&& (impDef.getLastFileName() == null
		|| impDef.getLastImport() == null
		|| impDef.getLastGebietName() == null)) {
		// P5 default page result import, when no results are imported yet
    	work = Command.IMPORT_ERGEBNISSE;
    }
    newLocation =  ClientHelper.getSuffixLevel(request, gebiet.getGebietsart())+
                   "&" + ClientHelper.workIs(work) +
                   "&" + ApplicationBeanKonstanten.GEBIETNRIS + gebiet.getNummer();
%>
   <ivu:a href='<%="/osv?"+newLocation %>' clazz='<%= gebiet.getGebietsart() != level ? "adminMessage":"hgweiss adminMessage"%>'>
    <%=gebiet.getName() + (gebiet.hasChildren() && !isEkP4Hsb ? subInfo : "" )%>
   </ivu:a>
  </div>
  <% if (gebiet.hasChildren()){ %>
  <div class='navi'>
<%
   //Schleife ueber alle Trees, nicht über die TreeNodes:
   int counterTree = 0;
   boolean divBreak = true;

   for (Iterator<DefaultMutableTreeNode> treeIter = treeList.iterator(); treeIter.hasNext(); counterTree++) {
    DefaultMutableTreeNode rootNode = treeIter.next();
    if (rootNode != null){
     Enumeration<?> depth1stEnum = rootNode.preorderEnumeration();
     DefaultMutableTreeNode treeNode = null;
     if (depth1stEnum.hasMoreElements()) {
      treeNode = (DefaultMutableTreeNode) depth1stEnum.nextElement();
     }
     // Schleife ueber die treeNodes:
     while(treeNode != null){
     	GebietInfo gebietInfo = null;
     	String anzahlText = null;
      String naviAnker;
      newLocation = null;
      String nodeName;
      boolean isErfassungseinheit = false;
      if (treeNode.getUserObject() instanceof GebietInfo) {
        gebietInfo = (GebietInfo)treeNode.getUserObject();
        isErfassungseinheit = gebietInfo.isErfassungseinheit();
        anzahlText = !isErfassungseinheit ? "("+gebietInfo.getAnzahlEingegangen()+" "+BundleHelper.getBundleString("Navigation_n_von_m")+" "+gebietInfo.getAnzahlGesamt()+")" : null;
        naviAnker = counterTree + "_" + gebietInfo.getNodePath();

        Command newWork = ApplicationBeanKonstanten.INITIAL_COMMAND;
        if (isErfassungseinheit && gebietInfo.getStatusLetzterEingang() != ErgebniseingangKonstanten.STATE_OK) {
          newWork = gui.getErfassungseinheitUnvollstaendigWork(gebietInfo.getStatusLetzterEingang());
        } else if (gebietInfo.isWahleinheit()) {
          newWork = Command.GEB_ERG;
        }

        newLocation = ClientHelper.getSuffixLevel(request, gebietInfo.getGebietsart())
            + '&' + ApplicationBeanKonstanten.GEBIETNRIS+gebietInfo.getNummer()
            + '&' + ClientHelper.workIs(newWork)
            + '&' + ApplicationBeanKonstanten.NAVI_ANKERIS + naviAnker;
        if (isEkP4Hsb) {
          nodeName = BundleHelper.getBundleString("Navigation_eingeben");
        } else {
          nodeName = gebietInfo.getNodeBezeichnung();
        }
      } else {
        naviAnker = counterTree + "_1";
        newLocation = ClientHelper.getParametersDoNotStartWith(request, ApplicationBeanKonstanten.NAVI_ANKER, false)
            + '&' + ApplicationBeanKonstanten.NAVI_ANKERIS + naviAnker;
        nodeName = (String)treeNode.getUserObject();
      }
      newLocation = "/osv?" + newLocation;
      boolean isSelected = aktuellerNodePath != null && aktuellerNodePath.equals(naviAnker);
      level = treeNode.getLevel() + 1;
      String classText = "level" + level;
      String id = isSelected ? "current" : naviAnker;
      int fill = 18;
      
      if (divBreak) {//%>
     <div class='<%=classText%>'><%
      }
       // Icons Haken, Ausrufezeichen:
       // Test von gebietInfo auf null ist nicht nötig, aber der Compiler ist nicht so schlau
       if (isErfassungseinheit && gebietInfo != null){
         %>
         <ivu:a href="<%=newLocation%>" title="<%=ClientHelper.forHTML(nodeName)%>" name="<%=naviAnker%>" id="<%=id%>">
         <%
        int status = gebietInfo.getStatusLetzterEingang();
        %><div class='fl'><%
        if (gebietInfo.isVollstaendig() ){//
         %><img class='c9' src='<%=request.getContextPath() %>/img/icon/check_ja_schmal.gif' alt='OK' /><%
         fill = fill - 9;
        }
        if (status == ErgebniseingangKonstanten.STATE_FIRST_RESULT_OK) {//
          %><img class='c9' src='<%= request.getContextPath() %>/img/icon/acceptOrange.gif' alt='!' /><%
         fill = fill - 9;
        } else if (status == ErgebniseingangKonstanten.STATE_WARNING) {//
         %><img class='c5' src='<%= request.getContextPath() %>/img/icon/achtungGelb.gif' alt='!' /><%
         fill = fill - 5;
        } else if (status == ErgebniseingangKonstanten.STATE_ERROR) {//
         %><img class='c5' src='<%= request.getContextPath() %>/img/icon/achtungRot.gif' alt='!' /><%
         fill = fill - 5;
        }
        if (fill >= 18) {
          %><span class='c18'></span><%
        } else if (fill >= 13) {//
          %><span class='c13'></span><%
        } else if (fill >= 9) {//
          %><span class='c9'></span><%
        } else if (fill >= 4) {//
          %><span class='c4'></span><%
        } else {//
          %><span class='c0'></span><%
        }
        %></div><%
        if (anzahlText != null){ //
        		%><div class='anzahl'><%=anzahlText%></div><%
        }
	     %><%= ClientHelper.forHTML(ClientHelper.trimAreaName(nodeName)) %>
        </ivu:a>
        
      <% } else if (!isEkP4Hsb) {%>
	      <span> 
         	<% if (anzahlText != null){ //
	        		%><div class='anzahl'><%=anzahlText%></div><%
	       	}
	       	%><%=ClientHelper.forHTML(ClientHelper.trimAreaName(nodeName)) %>
	      </span>
      	<%
       }
      divBreak = true;
      if (depth1stEnum.hasMoreElements()) {
       treeNode = (DefaultMutableTreeNode) depth1stEnum.nextElement();
       divBreak = level != treeNode.getLevel() + 1;
      } else {
       treeNode = null;
      }
      if (divBreak) {//%>
     </div><%
      }
     } // while(treeNode != null)
    } //if 
   } /*for (Iterator<DefaultMutableTreeNode> treeIter = treeList.iterator(); treeIter.hasNext(); counterTree++)*/%>
  </div><%
  } // if (gebiet.hasChildren()) %>
  </body>
</html>