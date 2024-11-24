package SalchiLuna.controller;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.config.MySQL;
import SalchiLuna.view.PantallasSesion.PantallaInicioSesion;
import SalchiLuna.view.PantallasSesion.PantallaRegistrar;
import SalchiLuna.view.PantallasUsuarioCliente.PantallaCompra;
import SalchiLuna.controller.database.ProductoController;
import SalchiLuna.model.generic.Tupla;

@SpringBootApplication
public class Aplicacion {
    private static Libreria lib = new Libreria();
    public static void main(String[] args) {
        MySQL sql = new MySQL();
        sql.abrirConexion();
        if (sql.estaConectado()){
            lib.MostrarConfirmacion("Se conecto a la base de datos");
            // PantallaInicioSesion pantalla = new PantallaInicioSesion();
            // PantallaRegistrar pantalla = new PantallaRegistrar();
            PantallaCompra pantalla = new PantallaCompra(223946895);
            pantalla.setLocationRelativeTo(null);
            pantalla.setVisible(true);
        }else{
            lib.MostrarError("Error: Error en la conexion la db");
        }
    }
}
