package modelo;
public class Usuario {
    private int idUsuario;
    private String email;
    private String nombre;
    private String clave;
    private int perfil;

    public Usuario() {
    }

    public Usuario(int idUsuario, String email, String nombre, String clave, int perfil) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nombre = nombre;
        this.clave = clave;
        this.perfil = perfil;
    }



    public int getIdUsuario() {
        return idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public int getPerfil() {
        return perfil;
    }
    
    
}
