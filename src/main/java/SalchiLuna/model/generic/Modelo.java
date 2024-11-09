package SalchiLuna.model.generic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import SalchiLuna.config.MySQL;

public class Modelo {
    private int numeroAtributo =0;

    public String TABLA;
    public String ID;
    public String[] COLUMNAS;

    private HashMap<Integer,Objeto> atributosValores = new HashMap<>();

    private String getAtributos(int opcion){
        String salida ="";
        if (opcion==1){
            Objeto entrada=null;
            for(int i=0;i<numeroAtributo;i++){
                entrada = atributosValores.get(i+1);
                if (i==(numeroAtributo-1)){
                    salida+=entrada.getAtributo();
                }else{
                    salida+=entrada.getAtributo()+",";
                }
            }
        }else{
            for (int i=0;i<COLUMNAS.length;i++){
                if (i==(COLUMNAS.length-1)){
                    salida+=COLUMNAS[i];
                }else{
                    salida+=COLUMNAS[i]+",";
                }
            }
        }
        return salida;
    }

    private String numeroSignos(){
        String salida ="";
        for(int i=0;i<numeroAtributo;i++){
            if (i==(numeroAtributo-1)){
                salida+="?";
            }else{
                salida+="?,";
            }
        }
        return salida;
    }

    private String getQueryInsertar(){
        String salida = "";
        salida+="INSERT INTO "+TABLA+"("+getAtributos(1)+") VALUES ("+numeroSignos()+")";
        return salida;
    }

    public void guardar(){
            String query = getQueryInsertar();
            MySQL sql = new MySQL();
            Objeto entrada= null;
            try {
                PreparedStatement consulta = sql.getConexion().prepareStatement(query);
                for (int i=0;i<atributosValores.size();i++){
                    entrada = atributosValores.get(i+1);
                    switch (entrada.getTipo()) {
                        case "Integer":
                            consulta.setInt(i+1,(int) entrada.getValor());
                            break;
                        case "String":
                            consulta.setString(i+1,(String) entrada.getValor());
                            break;
                        case "Double":
                            consulta.setDouble(i+1,(Double) entrada.getValor());
                            break;   
                        default:
                            break;
                    }
                }
                int resultado = consulta.executeUpdate();
                if (resultado>0){
                    // System.out.println("Se ingreso exitosamente a la base de datos");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void agregarParametros(String atributo,Object valor){
        numeroAtributo+=1;
        atributosValores.put(numeroAtributo, new Objeto(atributo, valor));
    }

    private String getQuerySelect(){
        return "SELECT "+getAtributos(2)+" FROM "+TABLA;
    }

    public ArrayList<Tupla> obtener(String condicion){
        ArrayList<Tupla> salida = new ArrayList<>();
        String query = getQuerySelect()+condicion;
        MySQL sql = new MySQL();
        try {
            PreparedStatement consula = sql.getConexion().prepareStatement(query);
            ResultSet resultado = consula.executeQuery();
            while (resultado.next()) {
                Tupla tupla = new Tupla();
                for(int i=0;i<COLUMNAS.length;i++){
                    tupla.agregarObjeto(COLUMNAS[i], resultado.getString(COLUMNAS[i]));
                }
                salida.add(tupla);
            }
            if (salida.size()==0){
                return null;
            }
            return salida;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<Tupla> todo(){
        ArrayList<Tupla> respuesta = obtener("");
        return respuesta;
    }

    public ArrayList<Tupla> where(Condicion condiciones){
        String entrada =  " where "+condiciones.toString();
        return obtener(entrada);
    }
    public Tupla whereOne(Condicion condiciones){
        String entrada = " where "+condiciones.toString();
        ArrayList<Tupla> salida = obtener(entrada);
        if (salida==null){
            return null;
        }else{
            return salida.get(0);
        }
    }

    public Tupla update(Tupla actualizacion,Condicion condiciones){
        Tupla salida = new Tupla();
        String condicion = " WHERE "+condiciones.toString();
        String query = "UPDATE "+TABLA+" SET "+actualizacion.toString()+condicion;
        MySQL sql = new MySQL();
        try {
            PreparedStatement consulta = sql.getConexion().prepareStatement(query);
            int respuesta = consulta.executeUpdate();
            if (respuesta>0){
                salida.agregarObjeto("respuesta", respuesta);
            }else{
                salida.agregarObjeto("respuesta", "No se actualizo nada");
            }
            return salida;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Tupla delete(Condicion condiciones){
        Tupla salida = new Tupla();
        String query = "DELETE FROM "+TABLA+" WHERE "+condiciones.toString();
        MySQL sql = new MySQL();
        try {
            PreparedStatement consulta = sql.getConexion().prepareStatement(query);
            int respuesta = consulta.executeUpdate();
            if (respuesta>0){
                salida.agregarObjeto("respuesta", "Se eliminaro "+respuesta+" registros");
            }else{
                salida.agregarObjeto("respuesta", "No se elimino nada");
            }
            return salida;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}