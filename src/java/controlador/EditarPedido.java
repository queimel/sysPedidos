
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

@WebServlet(name = "EditarPedido", urlPatterns = {"/editarPedido"})
public class EditarPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            List<ArrayList<Producto>> categorias = new ArrayList<ArrayList<Producto>>();
            //ArrayList<Producto> productos = new ArrayList<Producto>();
            Pedido pedido = new Pedido();
            ArrayList<Producto> ProdSelecc = new ArrayList<Producto>();
            
            GestorBD gestorBD = new GestorBD();
            categorias = gestorBD.filtrarProdPorCategorias();
            //productos = gestorBD.listarProductos();
            String idPedido = request.getParameter("pedido");
            pedido = gestorBD.getPedido(idPedido);
            ProdSelecc = gestorBD.getProductosPedido(idPedido);
            
            ArrayList<Integer> idProds = new ArrayList<Integer>();
            
            for(Producto prod : ProdSelecc){
                idProds.add(prod.getIdProducto());
            }
            
            if (categorias != null) {
                request.setAttribute("ProductosPorCategoria", categorias);
                request.setAttribute("ProductosSeleccionados", idProds);
                //session.setAttribute("Productos", productos);
                //session.setAttribute("mesa", mesaSeleccionada);
                request.getRequestDispatcher("/editarPedido.jsp")
                        .forward(request, response);
            } else {
                request.getRequestDispatcher("/noEncontrado.jsp")
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
