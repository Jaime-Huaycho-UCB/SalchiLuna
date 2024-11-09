package SalchiLuna.controller;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.model.Cliente;
import SalchiLuna.model.generic.Condicion;
import SalchiLuna.model.generic.Tupla;

public class ClienteController {
    private Libreria lib = new Libreria();
    
    public Tupla IniciarSesion(String correo,String contrasena){
        Tupla salida = new Tupla();
        Tupla usuario = existeCliente(correo);
        if (usuario!=null){
            if (usuario.getObjetoString("contrasena").equals(contrasena)){
                salida.agregarObjeto("salida", 1);
                salida.agregarObjeto("idCliente",usuario.getObjeto("id"));
            }else{
                salida.agregarObjeto("salida", 0);
                salida.agregarObjeto("mensaje", "Contrasena incorrecta");
            }
        }else{
            salida.agregarObjeto("salida", 0);
            salida.agregarObjeto("mensaje", "El cliente ingresado no esta registrado");
        }
        return salida;
    }

    public Tupla RegistrarCliente(String nombre,String correo,String contrasena){
        Tupla salida = new Tupla();
        if (existeCliente(correo)==null){
            int id = lib.obtenerId();
            Cliente cliente = new Cliente();
            cliente.agregarParametros("id", id);
            cliente.agregarParametros("nombres", nombre);
            cliente.agregarParametros("correo", correo);
            cliente.agregarParametros("contrasena", contrasena);
            cliente.guardar();
            salida.agregarObjeto("salida", 1);
            salida.agregarObjeto("mensaje", "Cliente registrado exitosamente");
        }else{
            salida.agregarObjeto("salida", 0);
            salida.agregarObjeto("mensaje", "El cliente ingresado ya existe");
        }
        return salida;
    }

    public Tupla existeCliente(String correo){
        Cliente cliente = new Cliente();
        Condicion condicion = new Condicion();
        condicion.adicionarCondicion("correo", "=", correo);
        Tupla salida = cliente.whereOne(condicion);
        return salida;
    }
}
