package modelo;

import java.util.ArrayList;

public class Pedido {
    private String idPedido;
    private int idMesa;
    private int idMesero;
    private ArrayList<Producto> itemsPedido;

    public Pedido() {
    }

    public Pedido(String idPedido, int idMesa, int idMesero, ArrayList<Producto> itemsPedido) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.idMesero = idMesero;
        this.itemsPedido = itemsPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdPedido() {
        return idPedido;
    }
    
    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(int idMesero) {
        this.idMesero = idMesero;
    }

    public ArrayList<Producto> getItemsPedido() {
        return itemsPedido;
    }

    public void setItemsPedido(ArrayList<Producto> itemsPedido) {
        this.itemsPedido = itemsPedido;
    }
    
}
