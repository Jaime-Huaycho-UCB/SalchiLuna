package SalchiLuna.controller.database;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.model.Constantes;
import SalchiLuna.model.database.Pedido;

public class PedidoController implements Constantes{
    private Libreria lib = new Libreria();
    
    public void agregarPedido(int idUsuario,int idCarrito,int metodoPago){
        try {
            int id = lib.obtenerId();
            String fechaActual = lib.fechaActual();
            Pedido pedido = new Pedido();
            pedido.agregarParametros("id", id);
            pedido.agregarParametros("usuario", idUsuario);
            pedido.agregarParametros("carrito", idCarrito);
            pedido.agregarParametros("fechaPedido", fechaActual);
            pedido.agregarParametros("estado", ESTADO_ESPERA);
            pedido.agregarParametros("metodoPago", metodoPago);
            pedido.guardar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
