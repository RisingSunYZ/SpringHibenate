<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'MyJsp.jsp' starting page</title>
    <jsp:include page="/WEB-INF/jsp/inc.jsp"></jsp:include>
    <script type="text/javascript" src="scripts/jqueryUI/js/index.js?<%=Math.random()%>"></script>
  </head>
  
  <body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">west content</div>
	<div data-options="region:'center',title:'Center'">
		
	<table id="datagrid"  title="Basic DataGrid" style="width:700px;height:250px">
	</table>
	
	</div>
</body>
</html>