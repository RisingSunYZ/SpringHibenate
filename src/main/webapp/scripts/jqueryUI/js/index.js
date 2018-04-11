var grid = null;

$(function(){
	
	var column = [
	  			{
					field:'id' ,
					title:'id' ,
					width:100 ,
					align:'center' ,
					checkbox : true 
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
				var dialog = $.dialog({
					id : 'dialog',
					title : '添加学生',
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
						var temp = dialog.content.save();
						$.post(basePath +'student/add.do',{"name":temp.name,"birth":temp.birth},function(data){
//							data = eval("("+data+")");
							if(data.success){
								$.dialog.alert(data.msg);
					    		refreshGrid();
					    	}
						});
					},
					cancelVal : '关闭',
					cancel : true
				});
			}
		},'-',{
			text : '删除' ,
			iconCls: 'icon-remove',
			handler: function(){
				var rows = grid.datagrid("getChecked");
				var arr = [];
				if(rows.length<=0){
					$.dialog.alert("请至少选择一条明细进行删除");
					return ;
				}else{
					for(var i=0;i<rows.length;i++){
						arr.push(rows[i].id);
					}
					
					$.dialog.confirm("确认删除这"+rows.length+"条数据吗?",function(){
						$.post(basePath+'student/del/'+arr.join(",")+'.do',function(data){
						if(data.success){
							$.dialog.alert(data.msg);
				    		refreshGrid();
				    	}
					});
					});

				}
			}
		},'-',{
			text : '修改' ,
			iconCls: 'icon-edit',
			handler: function(){
				var rows = grid.datagrid("getChecked");
				if(rows.length!=1){
					$.dialog.alert("请选择一条明细进行修改");
					return ;
				}else{
					var dialog = $.dialog({
						id : 'dialog',
						title : '修改学生',
						content : 'url:'+basePath+'student/fdEdit.do',
						height : "500px",
						width : "1070px",
						top : '60%',
						fixed : false,
						lock : false,
						parent : this,
						max : false,
						min : false,
						cache : false,
						data : {"row":rows[0]} ,
						ok : function() {
							dialog.content.edit();
						},
						cancelVal : '关闭',
						cancel : true
					});
				}
			}
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
//		pageList : [2,5],
		columns:[column],
		toolbar: toolbar

	});
});


function refreshGrid(){
	grid.datagrid("reload");
}

