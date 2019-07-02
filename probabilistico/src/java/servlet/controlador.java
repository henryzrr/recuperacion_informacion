/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import algoritmo.AlgoritmoProbabilistico;
import java.util.ArrayList;
/**
 *
 * @author linux
 */
@WebServlet(name = "controlador", urlPatterns = {"/controlador"})
public class controlador extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        String palabra;
        palabra = request.getParameter("cajabuscador");
        System.out.println(palabra);
        ArrayList<Integer> links =AlgoritmoProbabilistico.buscar(palabra);
        System.out.println(links);
        int tam = links.size();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>GUGLE</title>");
            out.println("<link rel=\"stylesheet\" href=\"cssIndex.css\" type=\"text/css\"><link>");
            out.println("</head>");
            out.println("<body>");
            out.println("<img src=\"gugle.jpg\" alt=\"asd\" width=\"200\" height=\"50\"> <br/>" );
            out.println("<form action=\"controlador\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"cajabuscador\">");
            out.println("<input type=\"submit\" value=\"buscar\"> </br> </br>");
            out.println(" </form>");
            for(int i=0;i<tam;i++){
                
                String dir = "Textos/" + links.get(i) + ".txt";
                out.println("<a href=\"" + dir + "\">" +links.get(i)+ ".txt</a> </br>");
            }
            out.println("</body>");
            out.println("</html>");
            
            //request.setAttribute("cajabuscador", "funciona");
            //System.out.println(palabra);
        }
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
