/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacion.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author rudy
 */
public class MiServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
           
        
            Object eleccionSugerencias    = request.getParameter("eleccionSugerencias");
            Object eleccionDesambiguacion = request.getParameter("eleccionDesambiguacion");
            Object btnBuscar              = request.getParameter("btnBuscar");
            
            System.out.println("eleccionDesambiguacion=" + eleccionDesambiguacion);
            System.out.println("eleccionSugerencia=" + eleccionSugerencias);
            System.out.println("BotonBuscar=" + btnBuscar);
            
            Contenedor contenedor;
            TreeSet<Tupla> listaOrdenada  = (TreeSet<Tupla>)request.getSession().getAttribute("historial");
            
            ArrayList<String> opcionesDesambiguacion = new ArrayList<>();    
            
            if(listaOrdenada!=null){
                contenedor = new Contenedor(listaOrdenada);
            }else{
                contenedor = new Contenedor();
            }
            
            
            String auxiliar=null;
            
            if(btnBuscar!=null)
                auxiliar = (String)request.getParameter("claves");
            else if(eleccionSugerencias != null)
                auxiliar = (String)eleccionSugerencias.toString();
            else if(eleccionDesambiguacion != null)
                auxiliar = (String)eleccionDesambiguacion.toString();
            
        
            //String texto = (String)request.getParameter("claves");
            String texto = auxiliar;
            String modelo = (String)request.getParameter("modelo");
            String op = request.getParameter("op");
            
            contenedor.aniadir(texto);
            
        // Codigo PRO
            AlgoritmoOptimizacion alg = new AlgoritmoOptimizacion();
            ArrayList<String> palabrasInput = Lector.separador(texto);
        
        //sugerencias palabras mal escritas
            ArrayList<String> sugerencias = alg.verificarPalabra(palabrasInput);
            palabrasInput = sugerencias;
        
        //desambiguar
            if(palabrasInput.size()==1){
                
                texto = palabrasInput.get(0);
                opcionesDesambiguacion = alg.getOpcionesDesambiguacion(texto);
                System.out.println(opcionesDesambiguacion);
                if(!opcionesDesambiguacion.isEmpty()){
/*                  String [] lsDesamb = opcionesDesambiguacion.toArray(new String[opcionesDesambiguacion.size()]);
                    int sel = JOptionPane.showOptionDialog(null, "selecciona opcion", "selecciona", 0, 0, null,lsDesamb,lsDesamb[0]);
                    texto = lsDesamb[sel];*/
                    palabrasInput = Lector.separador(texto);
                    
                }
            }
        
        //sinonimos
            alg.retornarSinonimos(palabrasInput);
        //PALABRAS RAIZ
            //alg.getRaizPalabra(palabrasInput);
        ////////////////////////////////////////////////////////////////Codigo Henrata
        
            texto="";
            for(String el : palabrasInput){
                texto= texto + " " + el;
            }
            
            TreeSet<Tupla> resultados = Principal.getResultados(texto.trim(), modelo, op, "C:\\Users\\rudy\\Documents\\NetBeansProjects\\Recuperacion\\web\\TEXTOS");
            TreeSet<Tupla> datalist   = contenedor.getLista();
            
            request.getSession().setAttribute("resultados", resultados);
            request.getSession().setAttribute("sugerencias", sugerencias);
            request.getSession().setAttribute("desambiguacion", opcionesDesambiguacion);
            request.getSession().setAttribute("historial", datalist);
            
            response.sendRedirect("index.jsp");
        
        //}
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
