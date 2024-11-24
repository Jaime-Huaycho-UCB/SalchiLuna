package SalchiLuna.view.PantallasSesion;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.controller.database.UsuarioController;
import SalchiLuna.model.generic.Tupla;
import SalchiLuna.view.constants.DimencionesPantalla;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaRegistrar extends JFrame implements DimencionesPantalla{

	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EntradaCorreo;
	private JTextField EntradaContrasena;
	private JTextField EntradaNombre;

	public PantallaRegistrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_1_ANCHO, PANTALLA_1_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb1 = new JLabel("Correo");
		lb1.setBounds(115, 196, 82, 16);
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("Contrasena");
		lb2.setBounds(115, 246, 82, 16);
		contentPane.add(lb2);
		
		EntradaCorreo = new JTextField();
		EntradaCorreo.setColumns(10);
		EntradaCorreo.setBounds(209, 191, 160, 26);
		contentPane.add(EntradaCorreo);
		
		EntradaContrasena = new JTextField();
		EntradaContrasena.setColumns(10);
		EntradaContrasena.setBounds(209, 241, 160, 26);
		contentPane.add(EntradaContrasena);
		
		JButton BotonRegistrar = new JButton("Iniciar sesion");
		BotonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrar(EntradaNombre.getText(), EntradaCorreo.getText(), EntradaContrasena.getText());
			}
		});
		BotonRegistrar.setBounds(191, 316, 117, 29);
		contentPane.add(BotonRegistrar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(115, 150, 82, 16);
		contentPane.add(lblNombre);
		
		EntradaNombre = new JTextField();
		EntradaNombre.setColumns(10);
		EntradaNombre.setBounds(209, 145, 160, 26);
		contentPane.add(EntradaNombre);
	}

	public void Registrar(String nombre,String correo,String contrasena){
		UsuarioController usuarioController = new UsuarioController();
		Tupla salida = usuarioController.RegistrarUsuario(nombre, correo, contrasena, 0);
		if (salida.getObjetoInt("salida")==1){
			lib.MostrarConfirmacion(salida.getObjetoString("mensaje"));
		}else{
			lib.MostrarError(salida.getObjetoString("mensaje"));
		}
	}
}
