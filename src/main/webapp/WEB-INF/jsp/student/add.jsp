<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<jsp:include page="/WEB-INF/jsp/inc.jsp"></jsp:include>
	<script type="text/javascript" src="scripts/jqueryUI/js/add.js?<%=Math.random() %>"></script>
  </head>
  
  <body>
  	<form id="addForm">   
    <div>   
        <label for="name">Name:</label>   
        <input type="text" name="name" />   
    </div>   
    <div>   
        <label for="email">Birth:</label>   
        <input class= "easyui-datebox" type="text" name="birth"  />   
    </div>   
</form> 
  </body>
</html>
