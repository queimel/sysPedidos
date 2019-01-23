<%@ include file="header.jsp" %>
<%@ page import="java.util.ArrayList" %>
        <%@ page import="controlador.PedidoGenerado" %><%@ page import="modelo.Producto" %>
        
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

                <div class="jumbotron">
                    <h1 class="display-4">Pedido Ingresado!</h1>
                    <p class="lead">El pedido de la mesa 2 ha sido ingresado con exito</p>
                    <a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/listadoPedidos" role="button">Ver mis pedidos</a>
                </div>
                
            </div>
        </div>
<%@ include file="footer.jsp" %>