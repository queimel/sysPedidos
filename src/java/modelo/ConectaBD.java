package modelo;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBD {
    public static Connection conn=null;
    private static String bd = "syspedidos";
    public static String usuario = "root";
    public static String passw = "";
    public static String url = "jdbc:mysql://localhost:3306/"+bd;
    
    public static Connection abrir() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, passw);

        } catch (Exception e) {
            System.out.println("Error en la conexion...");
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void cerrar(){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (Exception e){
            System.out.println("Error: No se logro cerrar conexion:\n"+e);
        }
    }
}
