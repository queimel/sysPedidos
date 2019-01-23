<%@ include file="header.jsp" %>
    <div class="container">
        <%@ page import="controlador.EditarPedido" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="java.util.List" %>
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

                <h1 class="text-leftr">Haz seleccionado la mesa <%= request.getParameter("mesa") %> </h1>
                <h4 class="text-left mb-4">Selecciona los items del pedido</h4>

                <form action="confirmarPedido" method="post">
                    
                    <%
                        List<ArrayList<Producto>> prodPorCat = null;
                        prodPorCat= (List<ArrayList<Producto>>)request.getAttribute("ProductosPorCategoria");

                        ArrayList<Integer> prodSelecc = null;
                        prodSelecc= (ArrayList<Integer>)request.getAttribute("ProductosSeleccionados");
                    %>
                    
                    <div class="accordion" id="accordionExample">
                        <%
                        for (int i = 0; i < prodPorCat.size(); i++) {
                        %>
                        <div class="card">
                            <div class="card-header" id="heading<%= prodPorCat.get(i).get(0).getIdCat()%>">
                                <h2 class="mb-0">
                                    
                                    <% if( prodPorCat.get(i).get(0).getIdCat() == 1){%>
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse<%= prodPorCat.get(i).get(0).getIdCat()%>"
                                            aria-expanded="true" aria-controls="collapseOne">
                                    <%}else {%>
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse<%= prodPorCat.get(i).get(0).getIdCat()%>"
                                            aria-expanded="false" aria-controls="collapseOne">
                                    <%}%>
                                        <%= prodPorCat.get(i).get(0).getCategoria()%>
                                    </button>
                                </h2>
                            </div>
                            <% if( prodPorCat.get(i).get(0).getIdCat() == 1){%>
                            <div id="collapse<%= prodPorCat.get(i).get(0).getIdCat()%>" class="collapse show" aria-labelledby="heading<%= prodPorCat.get(i).get(0).getIdCat()%>" data-parent="#accordionExample">
                            <%}else {%>
                            <div id="collapse<%= prodPorCat.get(i).get(0).getIdCat()%>" class="collapse" aria-labelledby="heading<%= prodPorCat.get(i).get(0).getIdCat()%>" data-parent="#accordionExample">
                            <%}%>
                                <div class="card-body">
                                    <ul class="list-group">
                                        <% 
                                            for (int j = 0; j < prodPorCat.get(i).size(); j++) { 
                                                int num = prodPorCat.get(i).get(j).getIdProducto();
                                        %>
                                        <% if(prodSelecc.contains(num)){ %>                     
                                        <li class="list-group-item active item-pedido">
                                        <% } else { %>
                                        <li class="list-group-item">
                                        <% } %>
                                            <div class="form-check">
                                                <% if(prodSelecc.contains(num)){ %>
                                                    <input class="form-check-input" type="checkbox" value="<%= prodPorCat.get(i).get(j).getIdProducto()%>" id="prodCheck<%= prodPorCat.get(i).get(j).getIdProducto()%>"  name="prodSelec" checked>
                                                <% } else { %>
                                                    <input class="form-check-input" type="checkbox" value="<%= prodPorCat.get(i).get(j).getIdProducto()%>" id="prodCheck<%= prodPorCat.get(i).get(j).getIdProducto()%>"  name="prodSelec">
                                                <% } %>
                                                
                                                <label class="form-check-label" for="prodCheck<%= prodPorCat.get(i).get(j).getIdProducto()%>">
                                                    <%= prodPorCat.get(i).get(j).getNombre() %>
                                                </label>                                                
                                                <span class="badge badge-info float-right">$<%= prodPorCat.get(i).get(j).getPrecio() %></span>
                                            </div>
                                        </li>
                                        <% } %>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <% } %>

                    </div><!--/ accordion -->
                    <br/>
                    <div class="form-group">
                      <label for="notas" name="notas">Notas del pedido</label>
                      <textarea class="form-control" id="notas" rows="5" name="notas"></textarea>
                    </div> 
                    <br/>
                    <div class="form-row justify-content-end">
                        <button class="btn btn-primary btn-lg" type="submit">Generar Pedido</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>
