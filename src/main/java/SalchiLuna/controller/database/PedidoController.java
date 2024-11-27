package SalchiLuna.controller.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.config.MySQL;
import SalchiLuna.model.Constantes;
import SalchiLuna.model.database.Carrito;
import SalchiLuna.model.database.Pedido;
import SalchiLuna.model.generic.Condicion;
import SalchiLuna.model.generic.Tupla;

public class PedidoController implements Constantes{
    private Libreria lib = new Libreria();
    
    public void agregarPedido(int idUsuario,int idCarrito,int metodoPago){
        try {
            int id = lib.obtenerId();
            Pedido pedido = new Pedido();
            pedido.agregarParametros("id", id);
            pedido.agregarParametros("usuario", idUsuario);
            pedido.agregarParametros("carrito", idCarrito);
            pedido.agregarParametros("estado", ESTADO_ESPERA);
            pedido.agregarParametros("metodoPago", metodoPago);
            pedido.guardar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CompletarPedido(int idPedido){
        Pedido pedido = new Pedido();
        Condicion condicion = new Condicion();
        condicion.adicionarCondicion("id", "=", idPedido);
        Tupla update = new Tupla();
        update.agregarObjeto("estado", ESTADO_COMPLETO);
        Tupla salida = pedido.update(update, condicion);
        System.out.println(salida);
    }

    public Tupla obtenerTablaCarrito(int idPedido,int idCarrito){
        Tupla tupla = new Tupla();
        Carrito carrito = new Carrito();
        Condicion condicion = new Condicion();
        condicion.adicionarCondicion("id", "=", idCarrito);
        Tupla carritoSalida = carrito.whereOne(condicion);

        String query = "SELECT p.id as numero, pr.nombre as nombre, pr.precio as precio, pc.cantidad as cantidad "+
                        "FROM PEDIDO p,CARRITO c,PRODUCTO_CARRITO pc,PRODUCTO pr "+
                        "WHERE p.carrito = c.id AND pc.carrito=c.id AND pc.producto = pr.id AND p.id = "+idPedido;

        Object[][] salida = new Object[carritoSalida.getObjetoInt("cantidad")][5]; 
        int c=0;
        double total = 0.00;
        double subTotal = 0.00;
        try {
            MySQL sql =  new MySQL();
            PreparedStatement consulta = sql.getConexion().prepareStatement(query);
            ResultSet ejecutar = consulta.executeQuery();
            while (ejecutar.next()) {
                salida[c][0]=ejecutar.getInt("numero");
                salida[c][1]=ejecutar.getString("nombre");
                salida[c][2]=ejecutar.getDouble("precio");
                salida[c][3]=ejecutar.getInt("cantidad");
                Double aux = ejecutar.getInt("cantidad")+0.00;
                subTotal=(ejecutar.getDouble("precio"))*(aux);
                salida[c][4]=subTotal;
                total+=subTotal;
                c+=1;
            }
            tupla.agregarObjeto("tabla", salida);
            tupla.agregarObjeto("total", total);
            return tupla;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
