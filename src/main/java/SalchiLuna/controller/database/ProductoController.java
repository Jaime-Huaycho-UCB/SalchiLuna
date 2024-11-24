package SalchiLuna.controller.database;

import java.util.ArrayList;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.model.database.Producto;
import SalchiLuna.model.generic.Condicion;
import SalchiLuna.model.generic.Tupla;

public class ProductoController {
    private Libreria lib = new Libreria();
    
    public Tupla obtenerMenu(){
        Tupla salida = new Tupla();
        try {
            ArrayList<Tupla> productos = null;
            
            Producto producto = new Producto();
            productos = producto.todo();

            if (productos==null){
                salida.agregarObjeto("salida", false);
                salida.agregarObjeto("mensaje", "No hay articulos disponibles");
                return salida;
            }

            ArrayList<Producto> productosFinal = obtenerObjetoProducto(productos);

            salida.agregarObjeto("salida", true);
            salida.agregarObjeto("productos", productosFinal);
            return salida;
        } catch (Exception e) {
            e.printStackTrace();
            salida.agregarObjeto("salida", false);
            salida.agregarObjeto("mensaje", e.getMessage());
        }
        return null;
    }

    public ArrayList<Producto> obtenerObjetoProducto(ArrayList<Tupla> entrada){
        ArrayList<Producto> salida = new ArrayList<>();
        Tupla tupla = null;
        Producto producto = null;
        int id = 0;
        String nombre = null;
        String descripcion = null;
        Double precio = 0.00;
        for (int i=0;i<entrada.size();i++){
            tupla = entrada.get(i);
            id = tupla.getObjetoInt("id");
            nombre = tupla.getObjetoString("nombre");
            descripcion = tupla.getObjetoString("descripcion");
            precio = tupla.getObjetoDouble("precio");
            producto = new Producto(id, nombre, descripcion, precio);
            salida.add(producto);
        }
        return salida;
    }

    public Tupla agregarProducto(String nombre,String descripcion,Double precio){
        Tupla salida = new Tupla();
        try {
            Producto producto = new Producto();
            int id = lib.obtenerId();
            producto.agregarParametros("id", id);
            producto.agregarParametros("nombre", nombre);
            producto.agregarParametros("descripcion", descripcion);
            producto.agregarParametros("precio", precio);
            producto.guardar();

            salida.agregarObjeto("salida", true);
            salida.agregarObjeto("mensaje", "Se agrego exitosamente el producto");
            salida.agregarObjeto("producto", new Producto(id, nombre, descripcion, precio));
            return salida;
        } catch (Exception e) {
            salida.agregarObjeto("salida", false);
            salida.agregarObjeto("mensaje", e.getMessage());
            e.printStackTrace();
            return salida;
        }
    }

    public Tupla eliminarProducto(int idProducto){
        Tupla salida = new Tupla();
        try {
            Producto producto = new Producto();
            Condicion condiciones = new Condicion();
            condiciones.adicionarCondicion("id", "=", idProducto);
            producto.delete(condiciones);

            salida.agregarObjeto("salida", true);
            salida.agregarObjeto("mensaje", "El producto se elimino exitosamente");
            return salida;
        } catch (Exception e) {
            salida.agregarObjeto("salida", false);
            salida.agregarObjeto("mensaje", e.getMessage());
            return salida;
        }
    }

    public Tupla actualizarProducto(int idProducto,String nombre,String descripcion,Double precio){
        Tupla salida = new Tupla();
        try {
            Condicion condicion = new Condicion();
            condicion.adicionarCondicion("id", "=", idProducto);
            Tupla aActualizar = new Tupla();
            if (nombre!=null){
                aActualizar.agregarObjeto("nombre", nombre);
            }
            if (descripcion!=null){
                aActualizar.agregarObjeto("descripcion", descripcion);
            }
            if (precio!=null){
                aActualizar.agregarObjeto("precio", precio);
            }
            
            Producto producto = new Producto();
            producto.update(aActualizar, condicion);

            salida.agregarObjeto("salida", true);
            salida.agregarObjeto("mensaje", "Se actualizo el producto exitosamente");
            return salida;
        } catch (Exception e) {
            salida.agregarObjeto("salida", false);
            salida.agregarObjeto("mensaje", e.getMessage());
            return salida;
        }
    }
}
