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
		lb1.setBounds(108, 181, 82, 16);
		contentPane.add(lb1);
		
		JLabel lb2 = new JLabel("Contrasena");
		lb2.setBounds(108, 231, 82, 16);
		contentPane.add(lb2);
		
		EntradaCorreo = new JTextField();
		EntradaCorreo.setBounds(202, 176, 160, 26);
		contentPane.add(EntradaCorreo);
		EntradaCorreo.setColumns(10);
		
		EntradaContrasena = new JTextField();
		EntradaContrasena.setColumns(10);
		EntradaContrasena.setBounds(202, 226, 160, 26);
		contentPane.add(EntradaContrasena);
		
		JButton BotonIniciar = new JButton("Iniciar sesion");
		BotonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciarSesion(EntradaCorreo.getText(),EntradaContrasena.getText());
			}
		});
		BotonIniciar.setBounds(190, 286, 117, 29);
		contentPane.add(BotonIniciar);
	}

	public void IniciarSesion(String correo,String contrasena){
		UsuarioController usuarioController = new UsuarioController();
		Tupla salida = usuarioController.IniciarSesion(correo, contrasena);
		if (salida.getObjetoInt("salida")==1){
			lib.MostrarConfirmacion(salida.getObjetoString("mensaje"));
			lib.MostrarConfirmacion(salida.getObjetoInt("idUsuario"));
		}else{
			lib.MostrarError(salida.getObjetoString("mensaje"));
		}
	}
}
