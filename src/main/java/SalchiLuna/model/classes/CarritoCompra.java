package SalchiLuna.model.classes;

import java.util.LinkedList;

import SalchiLuna.model.database.Producto;

public class CarritoCompra {

    private int idUsuario;
    private LinkedList<Producto> carrito = new LinkedList<>();
    private LinkedList<Integer> cantidades = new LinkedList<>();
    public LinkedList<Producto> getCarrito() {
        return carrito;
    }
    public void setCarrito(LinkedList<Producto> carrito) {
        this.carrito = carrito;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CarritoCompra(int idUsuario){
        this.idUsuario=idUsuario;
    }
    public LinkedList<Integer> getCantidades() {
        return cantidades;
    }
    public void setCantidades(LinkedList<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    public void agregarProducto(Producto producto){
        int lugar = existeProducto(producto);
        if (lugar==-1){
            carrito.add(producto);
            cantidades.add(1);
        }else{
            cantidades.set(lugar, cantidades.get(lugar)+1);
        }
    }

    public void eliminarProducto(Producto producto){
        int lugar = existeProducto(producto);
        if (cantidades.get(lugar)==1){
            cantidades.remove(lugar);
            carrito.remove(lugar);
        }else{
            cantidades.set(lugar, cantidades.get(lugar)-1);
        }
    }

    public int existeProducto(Producto producto){
        int lugar =-1;
        int c=0;
        for (Producto temp : getCarrito()){
            if (temp.getId()==producto.getId()){
                lugar=c;
            }
            c+=1;
        }
        return lugar;
    }

    public Object[][] obtenerTabla(){
        Object[][] salida = new Object[carrito.size()][4];
        for (int i=0;i<carrito.size();i++){
            salida[i][0] = carrito.get(i).getId();
            salida[i][1] = carrito.get(i).getNombre();
            salida[i][2] = carrito.get(i).getPrecio()*cantidades.get(i);
            salida[i][3] = cantidades.get(i);
        }
        return salida;
    }

    public double obtenerTotal(){
        double salida = 0.00;
        for (int i=0;i<carrito.size();i++){
            salida+=((carrito.get(i).getPrecio()) * (cantidades.get(i)));
        }
        return salida;
    }
}
