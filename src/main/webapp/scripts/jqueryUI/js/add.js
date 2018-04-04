$(function(){
	$('#ff').form({    
	    url:basePath +'student',    
	    onSubmit: function(){    
	        // do some check    
	        // return false to prevent submit;    
	    },    
	    success:function(data){    
	        alert(data)    
	    }    
	});    
//	// submit the form    
//	$('#ff').submit(); 
});