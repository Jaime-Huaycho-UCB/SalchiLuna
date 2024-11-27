package SalchiLuna.controller.database;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.model.classes.CarritoCompra;
import SalchiLuna.model.database.Carrito;
import SalchiLuna.model.generic.Tupla;

public class CarritoController {
    private Libreria lib = new Libreria();

    public Tupla realizarCompra(CarritoCompra carritoCompra){
        Tupla salida = new Tupla();
        CarritoProductoController carritoProductoController = new CarritoProductoController();
        PedidoController pedidoController = new PedidoController();
        try {
            
            Carrito carrito = new Carrito();
            int idCarrito = lib.obtenerId();
            carrito.agregarParametros("id",idCarrito);
            carrito.agregarParametros("cantidad", carritoCompra.getCarrito().size());
            carrito.guardar();
            for (int i=0;i<carritoCompra.getCarrito().size();i++){
                carritoProductoController.guardarProductoCarrito(idCarrito, carritoCompra.getCarrito().get(i).getId(),carritoCompra.getCantidades().get(i));
            }

            pedidoController.agregarPedido(carritoCompra.getIdUsuario(), idCarrito, 1);

            salida.agregarObjeto("salida", true);
            salida.agregarObjeto("mensaje", "Se realizo la compra exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            salida.agregarObjeto("salida", false);
            salida.agregarObjeto("mensaje", "Error: "+e.getMessage());
        }
        return salida;
    }

    
}
