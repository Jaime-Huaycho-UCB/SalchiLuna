package SalchiLuna.model.classes;

import java.util.ArrayList;

import SalchiLuna.controller.database.ProductoController;
import SalchiLuna.model.database.Producto;
import SalchiLuna.model.generic.Tupla;

public class Menu {
    
    public Object[][] obtenerMenuTabla(){
        ProductoController productoController = new ProductoController();
        Tupla respuesta = productoController.obtenerMenu();
        ArrayList<Producto> productos = respuesta.getObjetoArrayTupla("productos");
        Object[][] salida = new Object[productos.size()][4];
        for (int i=0;i<productos.size();i++){
            salida[i][0]=productos.get(i).getId();
            salida[i][1]=productos.get(i).getNombre();
            salida[i][2]=productos.get(i).getDescripcion();
            salida[i][3]=productos.get(i).getPrecio();
        }
        return salida;
    }
}
