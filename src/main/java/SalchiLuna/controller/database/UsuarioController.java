package SalchiLuna.controller.database;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.model.database.Usuario;
import SalchiLuna.model.generic.Condicion;
import SalchiLuna.model.generic.Tupla;

public class UsuarioController {
    private Libreria lib = new Libreria();
    
    public Tupla IniciarSesion(String correo,String contrasena){
        Tupla salida = new Tupla();
        try {
            Tupla usuario = existeUsuario(correo);

            if (usuario==null){
                salida.agregarObjeto("salida", false);
                salida.agregarObjeto("mensaje", "El usuario ingresado no esta registrado");
                return salida;
            }

            if (!(usuario.getObjetoString("contrasena").equals(contrasena))){
                salida.agregarObjeto("salida", 0);
                salida.agregarObjeto("mensaje", "Contrasena incorrecta");
                return salida;
            }

            salida.agregarObjeto("salida", true);
            salida.agregarObjeto("mensaje", "Se inicio sesion exitosamente");
            salida.agregarObjeto("idUsuario",Integer.parseInt(usuario.getObjetoString("id")));
            salida.agregarObjeto("tipo", Integer.parseInt(usuario.getObjetoString("tipo")));
            return salida;
        } catch (Exception e){
            e.printStackTrace();
            salida.agregarObjeto("salida", false);
            salida.agregarObjeto("mensaje", "Error: "+e.getMessage());
            return salida;
        }
    }

    public Tupla RegistrarUsuario(String nombre,String correo,String contrasena,int tipo){
        Tupla salida = new Tupla();
        try{
            if (existeUsuario(correo)!=null){
                salida.agregarObjeto("salida", 0);
                salida.agregarObjeto("mensaje", "El usuario ingresado ya existe");
                return salida;
            }

            int id = lib.obtenerId();
            Usuario usuario = new Usuario();
            usuario.agregarParametros("id", id);
            usuario.agregarParametros("nombre", nombre);
            usuario.agregarParametros("correo", correo);
            usuario.agregarParametros("contrasena", contrasena);
            usuario.agregarParametros("tipo", tipo);
            usuario.guardar();
            salida.agregarObjeto("salida", 1);
            salida.agregarObjeto("mensaje", "usuario registrado exitosamente");
            return salida;
        } catch (Exception e){
            e.printStackTrace();
            salida.agregarObjeto("salida", 0);
            salida.agregarObjeto("mensaje", "Error: "+e.getMessage());
            return salida;
        }
    }

    public Tupla existeUsuario(String correo){
        try{
            Usuario usuario = new Usuario();
            Condicion condicion = new Condicion();
            condicion.adicionarCondicion("correo", "=", correo);
            Tupla salida = usuario.whereOne(condicion);
            return salida;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
