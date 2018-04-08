var api = frameElement.api, W = api.opener;
var addForm;
$(function(){
		addForm = $('#addForm').form({    
	    url:basePath +'student/add.do',    
	    success:function(data){    
	    	if(data.success){
	    		W.refreshGrid();
	    	}
	    }    
	}); 
});


function save(){
//	addForm.submit(); 
	return {
		name :$("input[name=name]").val(),
		birth :$("input[name=birth]").val()
	};
}