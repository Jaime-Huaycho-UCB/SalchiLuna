package SalchiLuna.model;

import SalchiLuna.model.generic.Modelo;

public class Cliente extends Modelo{
    
    private String nombre;
    private String correo;
    private String contrsena;

    public Cliente(String nombre,String correo,String contrasena){
        this.nombre=nombre;
        this.correo=correo;;
        this.contrsena=contrasena;
    }

    public Cliente(){
        String[] columnas = {"id","nombres","correo","contrasena"};
        this.COLUMNAS = columnas;
        this.TABLA = "CLIENTE";
    }
    
    public String getContrsena() {
        return contrsena;
    }
    public void setContrsena(String contrsena) {
        this.contrsena = contrsena;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
