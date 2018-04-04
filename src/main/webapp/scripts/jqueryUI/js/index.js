var grid = null;

$(function(){
	grid = $('#datagrid').datagrid({
		idField:'id' ,
		title:'学生信息' , 
		fitColumns: true  ,
		url:basePath+'student/list.do' ,
		striped: true ,					
		loadMsg: '数据正在加载,请耐心的等待...' ,
		rownumbers:true ,
		pagination :true ,
		fit : true ,
		pageList : [1,2,5],
		columns:[[
			{
				field:'id' ,
				title:'id' ,
				width:100 ,
				align:'center' 
			},
			{
				field:'name' ,
				title:'用户名' ,
				width:100 ,
				align:'center' 
			},
			{
				field:'birth' ,
				title:'生日' ,
				width:100 ,
				align:'center' ,
				formatter:function(value){
					if(value){
						return new Date(value).format('yyyy-MM-dd HH:mm:ss');
					}
				}
			}
		]]
	});
});

