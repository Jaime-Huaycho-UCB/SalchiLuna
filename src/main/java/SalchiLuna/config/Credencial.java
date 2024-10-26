package SalchiLuna.config;

public interface Credencial {
    public static final String HOST = "127.0.0.1";
    public static final String PORT = "3308";
    public static final String DATA_BASE = "Login";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public void abrirConexion();
    public boolean estaConectado();
    public void cerrarConexion();
}
