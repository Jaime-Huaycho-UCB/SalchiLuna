package SalchiLuna.model.database;

import SalchiLuna.model.generic.Modelo;

public class Pedido extends Modelo{
    
    public Pedido(){
        String[] columnas = {"id","usuario","carrito","fechaPedido","estado","metodoPago"};
        this.COLUMNAS=columnas;
        this.TABLA="PEDIDO";
    }

    private int id;
    private int usuario;
    private int carrito;
    private String fechaPedido;
    private int estado;
    private int metodoPago;

    public int getCarrito() {
        return carrito;
    }
    public int getEstado() {
        return estado;
    }
    public String getFechaPedido() {
        return fechaPedido;
    }
    public int getId() {
        return id;
    }
    public int getMetodoPago() {
        return metodoPago;
    }
    public int getUsuario() {
        return usuario;
    }
    public Pedido(int id, int usuario, int carrito, String fechaPedido, int estado, int metodoPago) {
        this.id = id;
        this.usuario = usuario;
        this.carrito = carrito;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.metodoPago = metodoPago;
    }

    

}
