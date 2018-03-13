<jsp:directive.page import="de.ivu.wahl.client.beans.OutputCommand"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%response.setBufferSize(128); %>
<html>
 <head>
  <title><ivu:int key="WahlimportInDatenbank"/></title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/wahl2002.css">
 </head>
 <script type="text/javascript">
  function scrollDown() {
   var y;
   var test1 = document.body.scrollHeight;
   var test2 = document.body.offsetHeight
   if (test1 > test2) // all but Explorer Mac
   {
    y = document.body.scrollHeight;
   }
   else // Explorer Mac;
        //would also work in Explorer 6 Strict, Mozilla and Safari
   {
    y = document.body.offsetHeight;
   }
   window.scrollTo(0, y);
  }
  
  var aktiv = window.setInterval("scrollDown()", 200);
 </script>
 <body class="hghell">
  <h1><ivu:int key="WahlimportInDatenbank"/></h1><% 
  out.println(request.getAttribute("output")); 
  ((OutputCommand)request.getAttribute("outputCommand")).execute(out); %>
  <script type="text/javascript">
   window.clearInterval(aktiv);
   scrollDown();
  </script>
 </body>
 </html>