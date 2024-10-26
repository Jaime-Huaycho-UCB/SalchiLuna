package SalchiLuna.model.generic;

public class Condicion {
    private int numeroCondiciones=0;

    private String condiciones = "";
    public String getCondiciones() {
        return condiciones;
    }

    public void adicionarCondicion(String columna, String signo, Object parametro){
        numeroCondiciones+=1;
        String parametroSalida = "";
        if (parametro instanceof String){
            parametroSalida = "\""+parametro+"\"";
        }else{
            parametroSalida=parametro+"";
        }
        if (numeroCondiciones==1){
            condiciones+=columna+" "+signo+" "+parametroSalida;
        }else{
            condiciones+=" and "+columna+" "+signo+" "+parametroSalida;
        }
    }

    @Override
    public String toString(){
        return condiciones;
    }
}
