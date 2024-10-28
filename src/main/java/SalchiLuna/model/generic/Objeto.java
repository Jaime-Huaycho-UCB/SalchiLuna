package SalchiLuna.model.generic;

public class Objeto {
    private String tipo;
    private String atributo;
    private Object valor;

    public String getAtributo() {
        return atributo;
    }
    public Object getValor() {
        return valor;
    }
    public String getTipo() {
        return tipo;
    }

    public Objeto(String atributo,Object valor){
        this.atributo=atributo;
        this.valor=valor;
        if (valor instanceof Integer){
            this.tipo="Integer";
        }else if (valor instanceof String){
            this.tipo="String";
        }else if (valor instanceof Double){
            this.tipo="Double";
        }
    }

    @Override
    public String toString(){
        if (valor instanceof String){
            return atributo+" = "+"\""+valor+"\"";
        }else{
            return atributo+" = "+valor;
        }
    }
}
