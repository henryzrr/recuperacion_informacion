<%-- 
    Document   : index
    Created on : May 29, 2018, 10:28:45 PM
    Author     : rudy
--%>

<%@page import="com.aplicacion.servlet.Tupla"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gugle</title>
    </head>
    <body>
        <h1>Gugle</h1>
        
        <form action="Operacion" method="POST">
        
            <%
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

            %>
            
            <!-- Crea el datalist con los valores obtenidos de contenedor  -->
            <!-- Los ordena de acuerdo al tamaño -->
            <input type="text" name="claves" list="claves">
            <datalist id="claves">
                <%for(Tupla tupla : historial ){%>
                    <option> <%=tupla.getCadena()%> </option>
                <%}%>
            </datalist>
            
            <input type="submit" name="btnBuscar" value="Buscar">
            
            <select name="modelo">
                <option value="booleano">Booleano</option>
                <option value="vectorial" selected>Vectorial</option>
                <option value="probalistico"> Probabilistico</option>
            </select>
            
             <select name="op">
                <option value="and">AND</option>
                <option value="or" selected>OR</option>
            </select>
            
            <%
            /*<br>
            
            <input type="radio" name="modelo" value="booleano" /> Booleano <br>
            <input type="radio" name="modelo" value="vectorial" checked="checked" /> Vectorial <br>
            <input type="radio" name="modelo" value="probabilistico" /> Probabilistico <br>
            
            <br><br>
            
            <input type="radio" name="op" value="and" checked="checked" /> AND <br>
            <input type="radio" name="op" value="or" /> OR
            
            <br><br>*/
            %>
            
            <br><br>
            
            <%if(!sugerencias.isEmpty()){%>
                Quizas quisiste decir: 
                <%for(String el : sugerencias){%>
                    <input type="submit" name="eleccionSugerencias" value="<%=el%>">
                <%}%>
            <%}%>
            
            <br>
            
            <%if(!desambiguacion.isEmpty()){%>
                Desambiguacion: 
                <%for(String el : desambiguacion){%>
                    <input type="submit" name="eleccionDesambiguacion" value="<%=el%>">
                <%}%>
            <%}%>
            
            <%if(!control){%>
                <%if(!resultados.isEmpty()){%>
                    <p>Resultados para : </p><br>
                    <%for(Tupla el : resultados){%>
                        <%if(el.getNumero()!=0){%>
                            <p><%=el.getCadena()%></p>
                        <%}%>
                    
                    <%}%>
                <%}else{%>
                    <p>No se encontraron resultados para: </p>
                <%}%>
            <%}%>
            
            <%
                request.getSession().setAttribute("resultados", null);
                request.getSession().setAttribute("sugerencias", null);
                request.getSession().setAttribute("desambiguacion", null);
            %>
            
            
        </form>
    </body>
</html>
