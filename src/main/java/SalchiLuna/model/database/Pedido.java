package SalchiLuna.model.database;

import SalchiLuna.model.generic.Modelo;

public class Pedido extends Modelo{
    
    public Pedido(){
        String[] columnas = {"id","usuario","carrito","fechaPedido","estado","estadoPago"};
        this.COLUMNAS=columnas;
        this.TABLA="PEDIDO";
    }
}
