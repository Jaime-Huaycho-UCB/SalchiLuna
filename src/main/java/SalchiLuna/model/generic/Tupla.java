package SalchiLuna.model.generic;
import java.util.HashMap;


public class Tupla {
    private HashMap<String,Objeto> tupla = new HashMap<>();
    private int cantidad = 0;
    public HashMap<String, Objeto> getTupla() {
        return tupla;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Tupla(){}

    public Objeto getObjeto(String atributo){
        return getTupla().get(atributo);
    }

    public void agregarObjeto(String atributo,Object valor){
        Objeto objeto = new Objeto(atributo, valor);
        tupla.put(atributo, objeto);
        this.cantidad+=1;
    }

    @Override
    public String toString(){
        String salida = "";
        int c=0;
        for (String clave : tupla.keySet()){
            c+=1;
            if (c==cantidad){
                salida+=getObjeto(clave).toString();
            }else{
                salida+=getObjeto(clave).toString()+", ";
            }
        }
        return salida;
    }
}
