package SalchiLuna.model.database;

import SalchiLuna.model.generic.Modelo;

public class Carrito extends Modelo{
    
    public Carrito(){
        String[] columnas = {"id","cantidad"};
        this.COLUMNAS=columnas;
        this.TABLA="CARRITO";
    }

}
