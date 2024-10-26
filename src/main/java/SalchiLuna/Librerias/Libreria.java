package SalchiLuna.Librerias;

import javax.swing.JOptionPane;

public class Libreria {
    public String cadena(String repetir,int veces){
        String salida = "";
        for (int i=0;i<veces;i++){
            salida+=repetir;
        }
        return salida;
    }

    private int obtenerTipoMensaje(String tipoMensaje) {
        switch (tipoMensaje.toUpperCase()) {
            case "ERROR":
                return JOptionPane.ERROR_MESSAGE;
            case "INFORMATION":
                return JOptionPane.INFORMATION_MESSAGE;
            case "WARNING":
                return JOptionPane.WARNING_MESSAGE;
            case "PLAIN":
                return JOptionPane.PLAIN_MESSAGE;
            case "QUESTION":
                return JOptionPane.QUESTION_MESSAGE;
            default:
                return JOptionPane.PLAIN_MESSAGE; // Valor por defecto si no coincide
        }
    }

    // Inicio - Funciones mostrar
    public void Mostrar(Object entrada, String titulo, String tipoMensajeTexto) {JOptionPane.showMessageDialog(null, entrada.toString(), titulo, obtenerTipoMensaje(tipoMensajeTexto));}
    public void MostrarError(Object entrada) {JOptionPane.showMessageDialog(null, entrada.toString(), "Error", JOptionPane.ERROR_MESSAGE);}
    public void MostrarAdvertencia(Object entrada) {JOptionPane.showMessageDialog(null, entrada.toString(), "Advertencia", JOptionPane.WARNING_MESSAGE);}
    public void MostrarConfirmacion(Object entrada) {JOptionPane.showMessageDialog(null, entrada.toString(), "Confirmación", JOptionPane.QUESTION_MESSAGE);}
    public void Mostrar(Object entrada){JOptionPane.showMessageDialog(null, entrada.toString(), "Información", JOptionPane.INFORMATION_MESSAGE);}
    // Fin - Funciones mostrar

    // Inicio - Funciones ingresar cadena
    public String EntradaCadena(String mensaje, String tipoMensaje, String titulo) {
        String salida = "";
        while (true) {
            salida = JOptionPane.showInputDialog(null, mensaje, titulo, obtenerTipoMensaje(tipoMensaje), null, null, "").toString();
            if (salida.length() > 0) {
                return salida;
            }
            MostrarError("No ingrese texto vacío");
        }
    }    
    public String EntradaCadena(String mensaje) {
        return EntradaCadena(mensaje, "PLAIN", "Entrada de Texto");
    }
    // Fin - Funciones ingresar cadena

    // Inicio - Funciones ingresar entero
    public int EntradaEntero(String mensaje, String tipoMensaje, String titulo) {
        int numero = 0;
        while (true) {
            try {
                numero = Integer.parseInt(EntradaCadena(mensaje, tipoMensaje, titulo));
                return numero;
            } catch (Exception e) {
                MostrarError("Ingrese un número entero válido");
            }
        }
    }
    public int EntradaEntero(String mensaje) {
        return EntradaEntero(mensaje, "PLAIN", "Entrada");
    }
    // Fin - Funciones ingresar entero

    // Inicio - Funciones ingresar decimal
    public double EntradaDecimal(String mensaje, String tipoMensaje, String titulo) {
        double numero = 0.0;
        while (true) {
            try {
                numero = Double.parseDouble(EntradaCadena(mensaje, tipoMensaje, titulo));
                return numero;
            } catch (Exception e) {
                MostrarError("Ingrese un número decimal válido");
            }
        }
    }
    public double EntradaDecimal(String mensaje) {
        return EntradaDecimal(mensaje, "PLAIN", "Entrada");
    }
    // Fin - Funciones ingresar decimal  
    
    // Inicio - Funciones botones
    public String EntradaBotones(String[] opciones, String mensaje, String titulo) {
        int seleccion = JOptionPane.showOptionDialog(null, mensaje, titulo,
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        return ((seleccion>=0) ? (opciones[seleccion]) : "");
    }
    // Fin - Funciones botones
}
