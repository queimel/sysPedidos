package modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GestorBD {
    Connection conn = null;
    Statement stm=null;
    ResultSet usuarioResultSet;
    Usuario usuarioHallado;
    String emailR, nombreR, passw;
    private int perfil, idUsuario;
    ResultSet mesaResSet;
    ResultSet prodResSet;
    
    ResultSet results;
    
    public Usuario consultarUsuario(String email, String clave) {
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            usuarioResultSet = stm.executeQuery(
            "SELECT * FROM Usuario WHERE email ='"+email+"' and password='" + clave + "';"
            );
            if (!usuarioResultSet.next()) {
                System.out.println(" No se encontro el registro");
                ConectaBD.cerrar();
                return null;
            } else {
                System.out.println("Se encontró el registro");
                idUsuario = usuarioResultSet.getInt("idUsuario");
                emailR = usuarioResultSet.getString("email");
                nombreR = usuarioResultSet.getString("nombre");
                passw = usuarioResultSet.getString("password");
                perfil = usuarioResultSet.getInt("perfil");
                usuarioHallado = new Usuario(idUsuario,emailR, nombreR, passw, perfil);
                ConectaBD.cerrar();
                return usuarioHallado;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Mesa> listarMesas() {
        ArrayList<Mesa> mesas = new ArrayList<Mesa>();
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            mesaResSet = stm.executeQuery("select * from mesa");
            if (!mesaResSet.next()) {
                System.out.println(" No se encontraron mesas");
                ConectaBD.cerrar();
                return null;
            } else {
                do {                    
                    int idMesa = mesaResSet.getInt("idMesa");
                    int estado = mesaResSet.getInt("estado");
                    
                    Mesa mesaEncontrada = new Mesa (idMesa, estado);
                    mesas.add(mesaEncontrada);
                    
                } while (mesaResSet.next());
                ConectaBD.cerrar();
                return mesas;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    
    
    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            prodResSet = stm.executeQuery("SELECT a.idProducto, a.nombre,a.precio, b.idCategoria as idCat, b.nombre AS categoria FROM Producto a, Categoria b WHERE a.categoria = b.idCategoria;");
            
            if (!prodResSet.next()) {
                System.out.println(" No se encontraron productos");
                ConectaBD.cerrar();
                
                return null;
            } else {
                do {                                        
                    int idProducto= prodResSet.getInt("idProducto");
                    String nombre= prodResSet.getString("nombre");
                    int precio = prodResSet.getInt("precio");
                    int idCat = prodResSet.getInt("idCat");
                    String categoria = prodResSet.getString("categoria");
                    
                    Producto productoEncontrado = new Producto (idProducto, nombre, precio,idCat,categoria);
                    listaProductos.add(productoEncontrado);
                    
                } while (prodResSet.next());
                ConectaBD.cerrar();
                return listaProductos;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Producto> listarProductos(int cat) {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            prodResSet = stm.executeQuery("SELECT a.idProducto, a.nombre,a.precio, b.idCategoria as idCat, b.nombre AS categoria FROM Producto a, Categoria b WHERE a.categoria = b.idCategoria AND b.idCategoria ='" + cat + "';");
            
            if (!prodResSet.next()) {
                System.out.println(" No se encontraron productos");
                ConectaBD.cerrar();
                
                return null;
            } else {
                do {                                        
                    int idProducto= prodResSet.getInt("idProducto");
                    String nombre= prodResSet.getString("nombre");
                    int precio = prodResSet.getInt("precio");
                    int idCat = prodResSet.getInt("idCat");
                    String categoria = prodResSet.getString("categoria");
                    
                    Producto productoEncontrado = new Producto (idProducto, nombre, precio,idCat,categoria);
                    listaProductos.add(productoEncontrado);
                    
                } while (prodResSet.next());
                ConectaBD.cerrar();
                return listaProductos;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    
    public List<ArrayList<Producto>> filtrarProdPorCategorias() {
        
        List<ArrayList<Producto>> prodPorCat = new ArrayList<ArrayList<Producto>>();
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            results = stm.executeQuery("select * from categoria;");
            if (!results.next()) {
                System.out.println(" No se encontraron categorias");
                ConectaBD.cerrar();
                return null;
            } else {
                do {                    
                    int idCategoria = results.getInt("idCategoria");
                    
                    ArrayList<Producto> prods = this.listarProductos(idCategoria);
                    prodPorCat.add(prods);   
                    
                } while (results.next());
                ConectaBD.cerrar();
                return prodPorCat;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }

    public boolean ingresarPedido(Pedido pedido){
        Connection conn = null;
        Statement stm;
        ResultSet rs;
        int resultUpdate = 0;
        
        String idPedido = pedido.getIdPedido();
        int mesa = pedido.getIdMesa();
        int mesero = pedido.getIdMesero();
         ArrayList<Producto> listaProductos = pedido.getItemsPedido();

         // Inserta en tabla pedido
        try{
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            resultUpdate= stm.executeUpdate("INSERT INTO Pedido Values('"+idPedido+"',"+ mesa + "," + mesero + ");");
            crearDetalle(idPedido, listaProductos );
            if (resultUpdate != 0) {
                ConectaBD.cerrar();
                return true;
            } else {
                ConectaBD.cerrar();
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    public void crearDetalle(String idPedido, ArrayList<Producto> productos){
        
        Connection conn = null;
        Statement stm;
        
        int result[];
        
        try{
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            for(Producto producto: productos){
                String query = "INSERT INTO Detalle (idPedido, idProducto) values('"+idPedido+"', "+producto.getIdProducto()+")";
                stm.addBatch(query);
            }
            result = stm.executeBatch();
        }
        catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            
        }
    }
    
    
    public boolean borrarDetalle(String idPedido){
        
        Connection conn = null;
        Statement stm;
        
        boolean result;
        
        try{
            conn = ConectaBD.abrir();
            stm = conn.createStatement();

            String query = "DELETE FROM Detalle WHERE idPedido='"+idPedido+"')";

            result = stm.execute(query);
            return result;
        }
        catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Pedido> listarPedidos(int mesero) {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            results = stm.executeQuery("SELECT * FROM Pedido WHERE mesero="+mesero);
            if (!results.next()) {
                System.out.println(" No se encontraron mesas");
                ConectaBD.cerrar();
                return null;
            } else {
                do {
                    String idPedido = results.getString("idPedido");
                    int mesa = results.getInt("mesa");
                    //ArrayList<Producto> productosPedido = getProductosPedido(idPedido);
                    Pedido pedidoEncontrado = new Pedido();
                    pedidoEncontrado.setIdPedido(idPedido);
                    pedidoEncontrado.setIdMesa(mesa);
                    pedidoEncontrado.setIdMesero(mesero);
                    pedidos.add(pedidoEncontrado);

                } while (results.next());
                ConectaBD.cerrar();
                return pedidos;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    
    public Pedido getPedido(String idPedido) {
        Pedido pedidoEncontrado = new Pedido();
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            results = stm.executeQuery("SELECT * FROM Pedido WHERE idPedido='" + idPedido+"'");
            if (!results.next()) {
                System.out.println(" No se encontro el pedido");
                ConectaBD.cerrar();
                return null;
            } else {
                do {
                    
                    int mesa = results.getInt("mesa");
                    int mesero = results.getInt("mesero");
                    pedidoEncontrado.setIdPedido(idPedido);
                    pedidoEncontrado.setIdMesa(mesa);
                    pedidoEncontrado.setIdMesero(mesero);
                } while (results.next());
                ConectaBD.cerrar();
                return pedidoEncontrado;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Producto> getProductosPedido(String idPedido){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            results = stm.executeQuery("SELECT a.idPedido, b.idProducto, b.nombre, b.precio, b.categoria FROM Detalle a, Producto b WHERE a.idProducto = b.idProducto AND a.idPedido = '"+idPedido+"'; ");
            if (!results.next()) {
                System.out.println(" No se encontraron Productos");
                ConectaBD.cerrar();
                return null;
            } else {
                do {
                    int idProducto = results.getInt("idProducto");
                    String nombre = results.getString("nombre");
                    int precio = results.getInt("precio");
                    int categoria = results.getInt("categoria");

                    Producto productoPedido = new Producto();
                    productoPedido.setIdProducto(idProducto);
                    productoPedido.setNombre(nombre);
                    productoPedido.setPrecio(precio);
                    productoPedido.setIdCat(categoria);
                    productos.add(productoPedido);

                } while (results.next());
                ConectaBD.cerrar();
                return productos;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return null;
        }    
        
    }
    
    // cambiar estado a mesa
    public boolean modificarEstadoMesa(int mesa, int estado) {
        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            boolean results;
            results = stm.execute("UPDATE mesa SET estado ="+estado+" WHERE idMEsa ="+ mesa);
            if (results) {
                ConectaBD.cerrar();
                return true;
            } else {
                ConectaBD.cerrar();
                System.out.println("Estado actualizado");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }

}
