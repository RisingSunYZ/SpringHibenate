package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class inc_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<script type=\"text/javascript\" src=\"scripts/jqueryUI/jquery-1.9.1.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"scripts/jqueryUI/jquery-easyui-1.3.4/themes/default/easyui.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"scripts/jqueryUI/jquery-easyui-1.3.4/themes/icon.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"scripts/jqueryUI/jquery-easyui-1.3.4/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"scripts/jqueryUI/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js\"></script>\r\n");

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("/**\r\n");
      out.write(" * å¨å±åé\r\n");
      out.write(" */\r\n");
      out.write("var basePath = '");
      out.print(basePath);
      out.write("';\r\n");
      out.write("\r\n");
      out.write("/**\r\n");
      out.write(" * ä¸ºdateå¢å ååæ¹æ³\r\n");
      out.write(" */\r\n");
      out.write("Date.prototype.format = function(fmt) { \r\n");
      out.write("    var o = { \r\n");
      out.write("       \"M+\" : this.getMonth()+1,                 //æä»½ \r\n");
      out.write("       \"d+\" : this.getDate(),                    //æ¥ \r\n");
      out.write("       \"h+\" : this.getHours(),                   //å°æ¶ \r\n");
      out.write("       \"m+\" : this.getMinutes(),                 //å \r\n");
      out.write("       \"s+\" : this.getSeconds(),                 //ç§ \r\n");
      out.write("       \"q+\" : Math.floor((this.getMonth()+3)/3), //å­£åº¦ \r\n");
      out.write("       \"S\"  : this.getMilliseconds()             //æ¯«ç§ \r\n");
      out.write("   }; \r\n");
      out.write("   if(/(y+)/.test(fmt)) {\r\n");
      out.write("           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+\"\").substr(4 - RegExp.$1.length)); \r\n");
      out.write("   }\r\n");
      out.write("    for(var k in o) {\r\n");
      out.write("       if(new RegExp(\"(\"+ k +\")\").test(fmt)){\r\n");
      out.write("            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : ((\"00\"+ o[k]).substr((\"\"+ o[k]).length)));\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("   return fmt; \r\n");
      out.write("}   \r\n");
      out.write("\r\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
