/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.42
 * Generated at: 2017-05-17 13:10:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class decrypt_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("<title>Crypto</title>\r\n");
      out.write("<!-- Latest compiled and minified CSS -->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css\"\r\n");
      out.write("\tintegrity=\"sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ\"\r\n");
      out.write("\tcrossorigin=\"anonymous\">\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.1.1.slim.min.js\"\r\n");
      out.write("\tintegrity=\"sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n\"\r\n");
      out.write("\tcrossorigin=\"anonymous\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js\"\r\n");
      out.write("\tintegrity=\"sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb\"\r\n");
      out.write("\tcrossorigin=\"anonymous\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js\"\r\n");
      out.write("\tintegrity=\"sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn\"\r\n");
      out.write("\tcrossorigin=\"anonymous\"></script>\r\n");
      out.write("<script src=\"https://use.fontawesome.com/778322d9ba.js\"></script>\r\n");
      out.write("<!-- Own style -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/global.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/crypto.css\">\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body class=\"middle-blue\">\r\n");
      out.write("\r\n");
      out.write("\t<header> <a href=\"#menu-toggle\"\r\n");
      out.write("\t\tclass=\"btn btn-default hidden-md-up\" id=\"menu-toggle\"> <span></span>\r\n");
      out.write("\t\t<span></span> <span></span>\r\n");
      out.write("\t</a> </header>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"wrapper\" class=\"toggled\">\r\n");
      out.write("\t\t<!-- Sidebar -->\r\n");
      out.write("\t\t<div id=\"sidebar-wrapper\" class=\"dark-blue\">\r\n");
      out.write("\t\t\t<ul class=\"sidebar-nav\">\r\n");
      out.write("\t\t\t\t<li><a href=\"index.jsp\"><img src=\"images/lock.svg\"\r\n");
      out.write("\t\t\t\t\t\talt=\"Encrypt message\" title=\"Encrypt message\" /> Encrypt message\r\n");
      out.write("\t\t\t\t</a></li>\r\n");
      out.write("\t\t\t\t<li><a href=\"#\" class=\"active\"><img src=\"images/key.svg\"\r\n");
      out.write("\t\t\t\t\t\talt=\"Messages\" title=\"Decrypt messages\" /> Decrypt message</a></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /#sidebar-wrapper -->\r\n");
      out.write("\t\t<!-- Page Content -->\r\n");
      out.write("\t\t<div id=\"page-content-wrapper\">\r\n");
      out.write("\t\t\t<div class=\"container-fluid\">\r\n");
      out.write("\t\t\t\t<img id=\"logo\" src=\"images/LogoWit.svg\" alt=\"Crypto logo\" />\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"col-lg-10 push-lg-1 clearfix\" id=\"message-container\">\r\n");
      out.write("\t\t\t\t\t\t<h1>Decrypt message</h1>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"photo\">\r\n");
      out.write("\t\t\t\t\t\t\t<img src=\"\" id=\"showImage\">\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div id=\"message\">\r\n");
      out.write("\t\t\t\t\t\t\t<form action=\"decryptServlet\" method=\"post\" name=\"form\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<fieldset>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label for=\"imagePicker\">File (.png or .wav) that\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tcontains a message: </label> <input type=\"file\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tname=\"encryptedPicture\" id=\"imagePicker\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\taccept=\"image/png, audio/wav\" onchange=\"showPicture(event)\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\trequired>\r\n");
      out.write("\t\t\t\t\t\t\t\t</fieldset>\r\n");
      out.write("\t\t\t\t\t\t\t\t<button class=\"button yellow text-uppercase\" type=\"submit\">Decrypt</button>\r\n");
      out.write("\t\t\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t\t\t\t<div id=\"decrypted\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<h3>Decrypted message:</h3>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<b>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</b>\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t<p style=\"color: red\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<b>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.hash}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</b>\r\n");
      out.write("\t\t\t\t\t\t\t\t</p>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- /#page-content-wrapper -->\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- /#wrapper -->\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- Menu Toggle Script -->\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t\t$(\"#menu-toggle\").click(function(e) {\r\n");
      out.write("\t\t\t\te.preventDefault();\r\n");
      out.write("\t\t\t\t$(\"#wrapper\").toggleClass(\"toggled\");\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\t$(\"#imagePicker\")\r\n");
      out.write("\t\t\t\t\t.change(\r\n");
      out.write("\t\t\t\t\t\t\tfunction() {\r\n");
      out.write("\t\t\t\t\t\t\t\tvar pathToInputPicture = document\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t.getElementById(\"imagePicker\").value;\r\n");
      out.write("\t\t\t\t\t\t\t\tif (pathToInputPicture) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar startIndex = (pathToInputPicture\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t.indexOf('\\\\') >= 0 ? pathToInputPicture\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t.lastIndexOf('\\\\')\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t: pathToInputPicture\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t.lastIndexOf('/'));\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar filename = pathToInputPicture\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t.substring(startIndex);\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif (filename.indexOf('\\\\') === 0\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t|| filename.indexOf('/') === 0) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfilename = filename.substring(1);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar extensionIndex = filename.indexOf('.');\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar extension = filename.substring(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\textensionIndex).toLowerCase();\r\n");
      out.write("\t\t\t\t\t\t\t\t\tif (extension != \".png\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t&& extension != \".wav\") {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\talert(extension\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t+ \" is not a valid file! Choose a .png or .wav file\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tdocument.getElementById(\"imagePicker\").value = \"\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\r\n");
      out.write("\t\t\tvar showPicture = function(event) {\r\n");
      out.write("\t\t\t\tvar image = document.getElementById('showImage');\r\n");
      out.write("\t\t\t\timage.style.height = \"auto\";\r\n");
      out.write("\t\t\t\timage.style.margin = \"0\";\r\n");
      out.write("\t\t\t\tvar photo = URL.createObjectURL(event.target.files[0]);\r\n");
      out.write("\t\t\t\tvar filename = event.target.files[0].name;\r\n");
      out.write("\t\t\t\tvar extensionIndex = filename.indexOf('.');\r\n");
      out.write("\t\t\t\tvar extension = filename.substring(extensionIndex)\r\n");
      out.write("\t\t\t\t\t\t.toLowerCase();\r\n");
      out.write("\t\t\t\tif (extension === \".png\") {\r\n");
      out.write("\t\t\t\t\timage.src = photo;\r\n");
      out.write("\t\t\t\t\timage.onload = function() {\r\n");
      out.write("\t\t\t\t\t\tvar div = document.getElementById('photo');\r\n");
      out.write("\t\t\t\t\t\tdiv.style.height = image.height + \"px\";\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\timage.src = \"images/music.svg\";\r\n");
      out.write("\t\t\t\t\timage.style.height = \"130px\";\r\n");
      out.write("\t\t\t\t\timage.style.margin = \"10px 0 0 0 \";\r\n");
      out.write("\t\t\t\t\timage.onload = function() {\r\n");
      out.write("\t\t\t\t\t\tvar div = document.getElementById('photo');\r\n");
      out.write("\t\t\t\t\t\tdiv.style.height = \"150px\";\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t<!-- Latest compiled and minified JavaScript -->\r\n");
      out.write("\t\t<script\r\n");
      out.write("\t\t\tsrc=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"\r\n");
      out.write("\t\t\tintegrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\"\r\n");
      out.write("\t\t\tcrossorigin=\"anonymous\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
