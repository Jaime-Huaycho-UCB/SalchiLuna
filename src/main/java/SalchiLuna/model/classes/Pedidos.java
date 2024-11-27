package SalchiLuna.model.classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Queue;

import SalchiLuna.config.MySQL;
import SalchiLuna.controller.database.PedidoController;
import SalchiLuna.model.database.Pedido;

public class Pedidos {
    
    private Queue<Pedido> pedidos = new LinkedList<>();
    private Queue<Pedido> temp = new LinkedList<>();

    public Pedidos(){
        
    }

    public void cargarPedidosPendientes(){
        MySQL sql = new MySQL();
        try {
            String query = "SELECT p.id, p.usuario, p.carrito, p.fechaPedido, p.estado, p.metodoPago "+
            "FROM PEDIDO p,CARRITO c,PRODUCTO_CARRITO pc "+
            "WHERE p.estado = 1 AND p.carrito = c.id AND pc.carrito = c.id GROUP BY p.id ORDER BY fechaPedido ASC";
            PreparedStatement consulta = sql.getConexion().prepareStatement(query);
            ResultSet ejecutar = consulta.executeQuery();
            while (ejecutar.next()) {
                int id = ejecutar.getInt("id");
                int usuario = ejecutar.getInt("usuario");
                int carrito = ejecutar.getInt("carrito");
                String fechaPedido = ejecutar.getString("fechaPedido");
                int estado = ejecutar.getInt("estado");
                int metodoPago = ejecutar.getInt("metodoPago");
                pedidos.add(new Pedido(id, usuario, carrito, fechaPedido, estado, metodoPago));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object[][] obtenerTablaPedidosPendientes(){
        Object[][] salida = new Object[pedidos.size()][6];
        int i = 0;
        for (Pedido pedido : pedidos) {
            salida[i][0] = pedido.getId();
            salida[i][1] = pedido.getUsuario();
            salida[i][2] = pedido.getCarrito();
            salida[i][3] = pedido.getFechaPedido();
            salida[i][4] = "Pendiente";
            salida[i][5] = pedido.getMetodoPago();
            i++;
        }
        return salida;
    }

    public void completarPedido(int idPedido){
        PedidoController pedidoController = new PedidoController();
        pedidoController.CompletarPedido(idPedido);
        temp.clear();
        temp.addAll(pedidos);
        pedidos.clear();
        for (Pedido pedido : temp){
            if (pedido.getId()!=idPedido){
                pedidos.add(pedido);
            }
        }
        temp.clear();
    }
}
