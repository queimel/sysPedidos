/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;

@WebServlet(name = "ConfirmarPedido", urlPatterns = {"/confirmarPedido"})
public class ConfirmarPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        ArrayList<Producto> listaProductosSeleccionados = new ArrayList<Producto>();
        HttpSession session = request.getSession();
        session.setAttribute("ProductosSeleccionados", "");
        try (PrintWriter out = response.getWriter()){
            String[] prodSelec= request.getParameterValues("prodSelec");
            if (prodSelec != null) {

                GestorBD gestorBD = new GestorBD();
                listaProductos = gestorBD.listarProductos();
                Producto itemSeleccionado = new Producto();

                for (String producto: prodSelec) {
                    int index = Integer.parseInt(producto)-1;
                    itemSeleccionado = listaProductos.get(index);
                    listaProductosSeleccionados.add(itemSeleccionado);
                }
                
                request.setAttribute("ProductosSeleccionados", listaProductosSeleccionados);
                session.setAttribute("ProductosSeleccionados", listaProductosSeleccionados);
                request.getRequestDispatcher("/confirmarPedido.jsp")
                        .forward(request, response);
            } else {
                request.getRequestDispatcher("/noHayRegistros.jsp")
                        .forward(request, response);
            }
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
