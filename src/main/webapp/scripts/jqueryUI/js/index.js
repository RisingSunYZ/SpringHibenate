var grid = null;

$(function(){
	
	var column = [
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
			];
	
	var toolbar = [{
			text : '添加' ,
			iconCls: 'icon-add',
			handler: function(){
				var dilog = $.dialog({
					id : 'dialog',
					title : '添加學生',
					content : 'url:'+basePath+'student/fdAdd.do',
					height : "500px",
					width : "1070px",
					top : '60%',
					fixed : false,
					lock : false,
					parent : this,
					max : false,
					min : false,
					cache : false,
					ok : function() {
						
					},
					cancelVal : '关闭',
					cancel : true
				});
			}
		},'-',{
			text : '清空' ,
			iconCls: 'icon-help',
			handler: function(){alert('帮助按钮')}
		}];
	
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
		columns:[column],
		toolbar: toolbar

	});
});

