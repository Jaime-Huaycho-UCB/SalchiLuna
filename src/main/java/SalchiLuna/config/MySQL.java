package SalchiLuna.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements Credencial{
    private String url = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATA_BASE;
    private static Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void abrirConexion(){
        if (conexion==null){
            try {
                conexion = DriverManager.getConnection(url, USER, PASSWORD);
            } catch (SQLException e) {
                conexion=null;
                throw new RuntimeException("Error al intentar conectar con la base de datos: ", e);
            }
        }
    }
    public boolean estaConectado(){
        return (conexion!=null);
    }
    public void cerrarConexion(){
        if (estaConectado()) {
            try {
                conexion.close();
                conexion = null;
            } catch (SQLException e) {
                throw new RuntimeException("Error al intentar cerrar la conexión MySQL: ", e);
            }
        }
    }
}
