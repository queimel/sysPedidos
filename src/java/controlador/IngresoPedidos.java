package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;

@WebServlet(name = "IngresoPedidos", urlPatterns = {"/ingresoPedidos"})
public class IngresoPedidos extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            List<ArrayList<Producto>> categorias = new ArrayList<ArrayList<Producto>>();
            ArrayList<Producto> productos = new ArrayList<Producto>();
            GestorBD gestorBD = new GestorBD();
            categorias = gestorBD.filtrarProdPorCategorias();
            productos = gestorBD.listarProductos();
            int mesaSeleccionada = Integer.parseInt(request.getParameter("mesa"));
            
            if (categorias != null) {
                request.setAttribute("ProductosPorCategoria", categorias);
                session.setAttribute("Productos", productos);
                session.setAttribute("mesa", mesaSeleccionada);
                request.getRequestDispatcher("/ingresoPedidos.jsp")
                        .forward(request, response);
            } else {
                request.getRequestDispatcher("/noHayRegistros.jsp")
                        .forward(request, response);
            }
        } finally {
            out.close();
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
