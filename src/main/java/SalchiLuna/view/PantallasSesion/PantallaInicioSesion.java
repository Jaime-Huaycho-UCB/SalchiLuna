package SalchiLuna.view.PantallasSesion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.controller.database.UsuarioController;
import SalchiLuna.model.generic.Tupla;
import SalchiLuna.view.PantallasDeRedirecion.PantallaInicio;
import SalchiLuna.view.PantallasUsuarioAdmin.PantallaMenuAdmin;
import SalchiLuna.view.PantallasUsuarioCliente.PantallaMenuCliente;
import SalchiLuna.view.constants.DimencionesPantalla;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PantallaInicioSesion extends JFrame implements DimencionesPantalla{

	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EntradaCorreo;
	private JTextField EntradaContrasena;

	public PantallaInicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_1_ANCHO, PANTALLA_1_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb1 = new JLabel("Correo");
		lb1.setBounds(55, 130, 82, 16);
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("Contrasena");
		lb2.setBounds(55, 180, 82, 16);
		contentPane.add(lb2);
		
		EntradaCorreo = new JTextField();
		EntradaCorreo.setBounds(149, 125, 160, 26);
		contentPane.add(EntradaCorreo);
		EntradaCorreo.setColumns(10);
		
		EntradaContrasena = new JTextField();
		EntradaContrasena.setColumns(10);
		EntradaContrasena.setBounds(149, 175, 160, 26);
		contentPane.add(EntradaContrasena);
		
		JButton BotonIniciar = new JButton("Iniciar sesion");
		BotonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesion(EntradaCorreo.getText(),EntradaContrasena.getText());
			}
		});
		BotonIniciar.setBounds(133, 237, 117, 29);
		contentPane.add(BotonIniciar);
		
		JButton BotonVolver = new JButton("Volver");
		BotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lib.abrirPantalla(new PantallaInicio());
				dispose();
			}
		});
		BotonVolver.setBounds(6, 6, 117, 29);
		contentPane.add(BotonVolver);
		
		JLabel lblNewLabel = new JLabel("Inicio de sesion");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 47, 368, 55);
		contentPane.add(lblNewLabel);
	}

	public void IniciarSesion(String correo,String contrasena){
		UsuarioController usuarioController = new UsuarioController();
		Tupla salida = usuarioController.IniciarSesion(correo, contrasena);
		if (salida.getObjetoBoolean("salida")){
			lib.MostrarConfirmacion(salida.getObjetoString("mensaje"));
			if (salida.getObjetoInt("tipo")==0){
				PantallaMenuCliente pantallaMenuCliente = new PantallaMenuCliente(salida.getObjetoInt("idUsuario"));
				lib.abrirPantalla(pantallaMenuCliente);
				dispose();
			}else{
				PantallaMenuAdmin pantallaMenuAdmin = new PantallaMenuAdmin();
				lib.abrirPantalla(pantallaMenuAdmin);
				dispose();
			}
		}else{
			lib.MostrarError(salida.getObjetoString("mensaje"));
		}
	}
}
