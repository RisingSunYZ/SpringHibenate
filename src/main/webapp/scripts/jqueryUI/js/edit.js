var api = frameElement.api, W = api.opener;
var editForm;
var row = null;
$(function(){
	row = api.data.row;
	editForm = $('#editForm').form({    
	    url:basePath +'student/edit.do',    
	    success:function(data){    
	    	if(data.success){
	    		W.refreshGrid();
	    	}
	    }    
	}); 
	
	console.info(row);
	
	editForm.form("load",row);
});


function edit(){
	editForm.form("submit");
}