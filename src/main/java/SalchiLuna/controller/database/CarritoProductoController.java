package SalchiLuna.controller.database;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.model.database.CarritoProducto;

public class CarritoProductoController {
    private Libreria lib = new Libreria();
    
    public void guardarProductoCarrito(int idCarrito,int idProducto,int cantidad){
        try {
            int id = lib.obtenerId();
            CarritoProducto carritoProducto = new CarritoProducto();
            carritoProducto.agregarParametros("id", id);
            carritoProducto.agregarParametros("carrito", idCarrito);
            carritoProducto.agregarParametros("producto", idProducto);
            carritoProducto.agregarParametros("cantidad", cantidad);
            carritoProducto.guardar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
