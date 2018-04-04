<script type="text/javascript" src="scripts/jqueryUI/jquery-1.9.1.js"></script>
<link rel="stylesheet" type="text/css" href="scripts/jqueryUI/jquery-easyui-1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="scripts/jqueryUI/jquery-easyui-1.3.4/themes/icon.css" />
<script type="text/javascript" src="scripts/jqueryUI/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="scripts/jqueryUI/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>

<script type="text/javascript">
/**
 * 全局变量
 */
var basePath = '<%=basePath%>';

/**
 * 为date增加原型方法
 */
Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 //月份 
       "d+" : this.getDate(),                    //日 
       "h+" : this.getHours(),                   //小时 
       "m+" : this.getMinutes(),                 //分 
       "s+" : this.getSeconds(),                 //秒 
       "q+" : Math.floor((this.getMonth()+3)/3), //季度 
       "S"  : this.getMilliseconds()             //毫秒 
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}   

</script>