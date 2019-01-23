<%@ include file="header.jsp" %>
    <div class="container">
        <%@ page import="controlador.ListadoMesas" %>
        <%@ page import="java.util.ArrayList" %>
        
        <!-- creo arrayList con todas las mesas -->
        <%@ page import="modelo.Mesa" %>
        <%
        ArrayList<Mesa> listaMesas = null;
        listaMesas= (ArrayList<Mesa>)request.getAttribute("Mesas");
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
                <h1 class="text-leftr">Selecciona una mesa</h1>
                <h4 class="text-left mb-4">Para hacer un pedido</h4>
                <div class="list-group">
                    <%
                        for (Mesa mesa : listaMesas) {
                            //?mesa=<%=mesa.getNumero()
                    %>
                    <a href="<%=request.getContextPath()%>/ingresoPedidos?mesa=<%=mesa.getNumero() %>" class="list-group-item list-group-item-action 
                       <% if(mesa.getEstado() == 1){%> disabled text-black-10<% }%>" >
                        Mesa <%=mesa.getNumero() %>  
                        
                        <% if(mesa.getEstado() == 1){%>
                            <span class="badge badge-danger float-right">Ocupada</span>
                        <% }%>
                    </a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>