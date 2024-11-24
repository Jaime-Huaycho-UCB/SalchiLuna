package SalchiLuna.model.database;

import SalchiLuna.model.generic.Modelo;

public class Usuario extends Modelo{
    
    private String nombre;
    private String correo;
    private String contrsena;
    private String fechaRegistro;
    private int tipo;

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
    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public int getTipo(){
        return this.tipo;
    }
    public void setTipo(int tipo){
        this.tipo=tipo;
    }

    public Usuario(String nombre,String correo,String contrasena,String fechaRegistro,int tipo){
        this.nombre=nombre;
        this.correo=correo;;
        this.contrsena=contrasena;
        this.fechaRegistro=fechaRegistro;
        this.tipo=tipo;
    }

    public Usuario(){
        String[] columnas = {"id","nombre","correo","contrasena","fechaRegistro","tipo"};
        this.COLUMNAS = columnas;
        this.TABLA = "USUARIO";
    }
}
