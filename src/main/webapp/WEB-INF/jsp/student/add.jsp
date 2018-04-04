<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="/WEB-INF/jsp/inc.jsp"></jsp:include>
	<script type="text/javascript" src="scripts/jqueryUI/js/add.js"></script>
  </head>
  
  <body>
  	<form id="addForm" method="post">   
    <div>   
        <label for="name">Name:</label>   
        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
    </div>   
    <div>   
        <label for="email">Birth:</label>   
        <input class="easyui-validatebox" type="text" name="birth" data-options="validType:'email'" />   
    </div>   
</form> 
  </body>
</html>
