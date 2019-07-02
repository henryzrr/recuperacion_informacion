package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.aplicacion.servlet.Tupla;
import java.util.TreeSet;
import java.util.ArrayList;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Gugle</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Gugle</h1>\n");
      out.write("        \n");
      out.write("        <form action=\"Operacion\" method=\"POST\">\n");
      out.write("        \n");
      out.write("            ");

                TreeSet<Tupla> historial         = (TreeSet<Tupla>)request.getSession().getAttribute("historial");
                TreeSet<Tupla> resultados        = (TreeSet<Tupla>)request.getSession().getAttribute("resultados");
                ArrayList<String> sugerencias    = (ArrayList<String>)request.getSession().getAttribute("sugerencias");
                ArrayList<String> desambiguacion = (ArrayList<String>)request.getSession().getAttribute("desambiguacion");
            
                boolean control=false;
                
                if(historial==null)
                    historial= new TreeSet<>();
                
                if(resultados==null){
                    resultados= new TreeSet<>();
                    control=true;
                }
                
                if(sugerencias==null)
                    sugerencias= new ArrayList<>();
                
                if(desambiguacion==null)
                    desambiguacion= new ArrayList<>();

            
      out.write("\n");
      out.write("            \n");
      out.write("            <!-- Crea el datalist con los valores obtenidos de contenedor  -->\n");
      out.write("            <!-- Los ordena de acuerdo al tamaño -->\n");
      out.write("            <input type=\"text\" name=\"claves\" list=\"claves\">\n");
      out.write("            <datalist id=\"claves\">\n");
      out.write("                ");
for(Tupla tupla : historial ){
      out.write("\n");
      out.write("                    <option> ");
      out.print(tupla.getCadena());
      out.write(" </option>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </datalist>\n");
      out.write("            \n");
      out.write("            <input type=\"submit\" name=\"btnBuscar\" value=\"Buscar\">\n");
      out.write("            \n");
      out.write("            <select name=\"modelo\">\n");
      out.write("                <option value=\"booleano\">Booleano</option>\n");
      out.write("                <option value=\"vectorial\" selected>Vectorial</option>\n");
      out.write("                <option value=\"probalistico\"> Probabilistico</option>\n");
      out.write("            </select>\n");
      out.write("            \n");
      out.write("             <select name=\"op\">\n");
      out.write("                <option value=\"and\">AND</option>\n");
      out.write("                <option value=\"or\" selected>OR</option>\n");
      out.write("            </select>\n");
      out.write("            \n");
      out.write("            ");

            /*<br>
            
            <input type="radio" name="modelo" value="booleano" /> Booleano <br>
            <input type="radio" name="modelo" value="vectorial" checked="checked" /> Vectorial <br>
            <input type="radio" name="modelo" value="probabilistico" /> Probabilistico <br>
            
            <br><br>
            
            <input type="radio" name="op" value="and" checked="checked" /> AND <br>
            <input type="radio" name="op" value="or" /> OR
            
            <br><br>*/
            
      out.write("\n");
      out.write("            \n");
      out.write("            <br><br>\n");
      out.write("            \n");
      out.write("            ");
if(!sugerencias.isEmpty()){
      out.write("\n");
      out.write("                Quizas quisiste decir: \n");
      out.write("                ");
for(String el : sugerencias){
      out.write("\n");
      out.write("                    <input type=\"submit\" name=\"eleccionSugerencias\" value=\"");
      out.print(el);
      out.write("\">\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            ");
}
      out.write("\n");
      out.write("            \n");
      out.write("            <br>\n");
      out.write("            \n");
      out.write("            ");
if(!desambiguacion.isEmpty()){
      out.write("\n");
      out.write("                Desambiguacion: \n");
      out.write("                ");
for(String el : desambiguacion){
      out.write("\n");
      out.write("                    <input type=\"submit\" name=\"eleccionDesambiguacion\" value=\"");
      out.print(el);
      out.write("\">\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            ");
}
      out.write("\n");
      out.write("            \n");
      out.write("            ");
if(!control){
      out.write("\n");
      out.write("                ");
if(!resultados.isEmpty()){
      out.write("\n");
      out.write("                    <p>Resultados para : </p><br>\n");
      out.write("                    ");
for(Tupla el : resultados){
      out.write("\n");
      out.write("                        ");
if(el.getNumero()!=0){
      out.write("\n");
      out.write("                            <p>");
      out.print(el.getCadena());
      out.write("</p>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    \n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                ");
}else{
      out.write("\n");
      out.write("                    <p>No se encontraron resultados para: </p>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            ");
}
      out.write("\n");
      out.write("            \n");
      out.write("            ");

                request.getSession().setAttribute("resultados", null);
                request.getSession().setAttribute("sugerencias", null);
                request.getSession().setAttribute("desambiguacion", null);
            
      out.write("\n");
      out.write("            \n");
      out.write("            \n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
