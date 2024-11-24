package SalchiLuna.model.database;


import SalchiLuna.model.generic.Modelo;

public class Producto extends Modelo{
    
    private int id;
    private String nombre;
    private String descripcion;
    private Double precio;

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }

    public Producto(int id,String nombre,String descripcion,Double precio){
        this.id = id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
    }

    public Producto(){
        String[] columnas = {"id","nombre","descripcion","precio"};
        this.TABLA="PRODUCTO";
        this.COLUMNAS=columnas;
    }

    @Override
    public String toString(){
        return "id: "+getId()+"\n"+"Nombre: "+getNombre()+"\n"+"Descripcion: "+getDescripcion()+"\n"+"Precio: "+getPrecio()+"\n";
    }
}
