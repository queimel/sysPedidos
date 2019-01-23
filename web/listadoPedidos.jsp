<%@ include file="header.jsp" %>
    <div class="container">
        <%@ page import="controlador.ListadoPedidos" %>
        <%@ page import="java.util.ArrayList" %>
        
        <!-- creo arrayList con todas las mesas -->
        <%@ page import="modelo.Pedido" %>
        <%
        ArrayList<Pedido> listaPedidos = null;
        listaPedidos= (ArrayList<Pedido>)request.getAttribute("Pedidos");
        %>
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
                <h1 class="text-leftr">Tus pedidos</h1>
                <h4 class="text-left mb-4">Haz click sobre un item para editar el pedido.</h4>
                <div class="list-group mb-5">
                    <%
                        for (Pedido pedido : listaPedidos) {
                    %>
                    <a href="<%=request.getContextPath()%>/editarPedido?pedido=<%=pedido.getIdPedido() %>" class="list-group-item list-group-item-action" >
                        Pedido <span class="small text-monospace text-muted"><%=pedido.getIdPedido() %></span>  
                        
                        <span class="badge badge-info float-right"> Mesa <%=pedido.getIdMesa() %></span>
                      
                    </a>
                    <% } %>
                </div>
                <div class="d-flex justify-content-end"> 
                    <a class="btn btn-outline-primary">Volver</a>
                </div>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>