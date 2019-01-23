<%@ include file="header.jsp" %>
    <%@ page import="controlador.Login" %>
    <div class="container">
        <div class="row justify-content-center align-items-center" style="height: 90vh;">
            <div class="col-md-4">
                <h1 class="text-center">Login</h1>
                <div class="card">
                    <div class="card-body">
                        <form action="login" method="post">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Ingresa tu email" name="cuenta">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="clave">
                            </div>
                            <button type="submit" class="btn btn-primary">Login</button>
                        </form>
                    </div>
                </div>
            
            </div>
        </div>
    </div>
<%@ include file="footer.jsp" %>