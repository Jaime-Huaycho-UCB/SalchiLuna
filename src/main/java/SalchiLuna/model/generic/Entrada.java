package SalchiLuna.model.generic;

public class Entrada {
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

    public Entrada(String atributo,Object valor){
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
}
