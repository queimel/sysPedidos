<%@ include file="header.jsp" %>
    <div class="container">
        <%@ page import="controlador.ConfirmarPedido" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="modelo.Producto" %>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <% 
                String usuario =(String) session.getAttribute ("nombre");
              %>
            <a class="navbar-brand" href="#">Bienvenido <%= usuario %></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText"
                aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarText">
                <ul class="navbar-nav mr-auto nav nav-pills flex-md-row-reverse w-100">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Mis pedidos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="row justify-content-center mt-5">
            <div class="col-md-8">

                <h1 class="text-leftr">Confirma el pedido </h1>
                <h4 class="text-left mb-4">de la mesa <%= session.getAttribute ("mesa") %></h4>
                <%
                ArrayList<Producto> listaProductos = null;
                listaProductos= (ArrayList<Producto>)request.getAttribute("ProductosSeleccionados");
                %>
                
                <ul class="list-group mb-5">
                    <%
                        for (Producto producto : listaProductos) {
                    %>
                   <li class="list-group-item"><%=producto.getNombre() %> <span class="badge badge-pill badge-info float-right">$<%=producto.getPrecio() %></span></li>
                   <% } %>
                </ul>
                    
               <div class="d-flex mt-5 mb-5 justify-content-end">
                   
                       <a href="<%=request.getContextPath()%>/pedidoGenerado" class="btn-lg btn btn-primary">Confirmar Pedido</a>
                   
                    
                </div>
            </div>
        </div>
    </div>
                                    
<%@ include file="footer.jsp" %>
