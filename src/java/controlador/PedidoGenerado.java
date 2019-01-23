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
import java.util.UUID;

/**
 *
 * @author cristian.campos
 */
@WebServlet(name = "PedidoGenerado", urlPatterns = {"/pedidoGenerado"})
public class PedidoGenerado extends HttpServlet {

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
        try {
            GestorBD gestorBD = new GestorBD();
            HttpSession session = request.getSession();

            String idPedido = UUID.randomUUID().toString();
            int mesero = (int)session.getAttribute("idUsuario");
            int mesa = (int)session.getAttribute("mesa");

            ArrayList<Producto> listaProductos;
            listaProductos = (ArrayList)session.getAttribute("ProductosSeleccionados");
            
            if (listaProductos != null) {
                Pedido pedido = new Pedido(idPedido,mesa, mesero, listaProductos);
                
                if(gestorBD.ingresarPedido(pedido)){

                    session.removeAttribute("Productos");
                    session.removeAttribute("mesa");
                    gestorBD.modificarEstadoMesa(mesa, 1);
                
                    request.getRequestDispatcher("/pedidoGenerado.jsp")
                        .forward(request, response);
                }else {
                    request.getRequestDispatcher("/noEncontrado.jsp")
                            .forward(request, response);
                }                
                
            } else {
                request.getRequestDispatcher("/noEncontrado.jsp")
                        .forward(request, response);
            }
        }catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
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
