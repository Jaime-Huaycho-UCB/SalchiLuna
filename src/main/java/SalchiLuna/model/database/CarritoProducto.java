package SalchiLuna.model.database;

import SalchiLuna.model.generic.Modelo;

public class CarritoProducto extends Modelo{
    
    public CarritoProducto(){
        String[] columnas = {"carrito","producto","cantidad"};
        this.COLUMNAS=columnas;
        this.TABLA="PRODUCTO_CARRITO";
    }
}
