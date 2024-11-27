package SalchiLuna.controller;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.config.MySQL;
import SalchiLuna.view.PantallasDeRedirecion.PantallaInicio;
@SpringBootApplication
public class Aplicacion {
    private static Libreria lib = new Libreria();
    public static void main(String[] args) {
        MySQL sql = new MySQL();
        sql.abrirConexion();
        if (sql.estaConectado()){
            lib.MostrarConfirmacion("Se conecto a la base de datos");
            PantallaInicio pantalla = new PantallaInicio();
            pantalla.setLocationRelativeTo(null);
            pantalla.setVisible(true);
        }else{
            lib.MostrarError("Error: Error en la conexion la db");
        }
    }
}
