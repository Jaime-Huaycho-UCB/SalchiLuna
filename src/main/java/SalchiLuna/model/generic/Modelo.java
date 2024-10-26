package SalchiLuna.model.generic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import SalchiLuna.config.MySQL;

public class Modelo {
    private int numeroAtributo =0;

    protected String TABLA;
    protected String ID;
    protected String[] COLUMNAS;

    private HashMap<Integer,Entrada> atributosValores = new HashMap<>();

    private String getAtributos(int opcion){
        String salida ="";
        
        if (opcion==1){
            Entrada entrada=null;
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
            Entrada entrada= null;
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
                    System.out.println("Se ingreso exitosamente a la base de datos");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }

    public void agregarParametros(String atributo,Object valor){
        numeroAtributo+=1;
        atributosValores.put(numeroAtributo, new Entrada(atributo, valor));
    }

    private String getQuerySelect(){
        return "SELECT "+getAtributos(2)+" FROM "+TABLA;
    }

    public ArrayList<HashMap<String,String>> todo(){
        
        ArrayList<HashMap<String,String>> salida = new ArrayList<>();
        String query = getQuerySelect();
        MySQL sql = new MySQL();
        try {
            PreparedStatement consula = sql.getConexion().prepareStatement(query);
            ResultSet resultado = consula.executeQuery();
            while (resultado.next()) {
                HashMap<String,String> tupla = new HashMap<>();
                for(int i=0;i<COLUMNAS.length;i++){
                    tupla.put(COLUMNAS[i], resultado.getString(COLUMNAS[i]));
                }
                salida.add(tupla);
            }
            return salida;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<HashMap<String,String>> where(){
        return null;
    }

}
