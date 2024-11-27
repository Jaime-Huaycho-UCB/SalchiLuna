package SalchiLuna.view.PantallasUsuarioAdmin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.view.PantallasSesion.PantallaInicioSesion;
import SalchiLuna.view.PantallasUsuarioAdmin.GestionMenu.PantallaGestionMenu;
import SalchiLuna.view.constants.DimencionesPantalla;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PantallaMenuAdmin extends JFrame implements DimencionesPantalla{
	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaMenuAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_1_ANCHO, PANTALLA_1_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BotonGestionPedidos = new JButton("Gestionar pedidos");
		BotonGestionPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaGestionPedidos pantallaGestionPedidos = new PantallaGestionPedidos();
				lib.abrirPantalla(pantallaGestionPedidos);
				dispose();
			}
		});
		BotonGestionPedidos.setBounds(101, 79, 179, 29);
		contentPane.add(BotonGestionPedidos);
		
		JButton BotonGestionUsuarios = new JButton("Gestionar usuarios");
		BotonGestionUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BotonGestionUsuarios.setBounds(101, 133, 179, 29);
		contentPane.add(BotonGestionUsuarios);
		
		JButton BotonCerrarSesion = new JButton("Cerrar sesion");
		BotonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaInicioSesion pantallaInicioSesion = new PantallaInicioSesion();
				lib.abrirPantalla(pantallaInicioSesion);
				dispose();
			}
		});
		BotonCerrarSesion.setBounds(131, 250, 117, 29);
		contentPane.add(BotonCerrarSesion);
		
		JButton BotonGestionMenu = new JButton("Gestionar menu");
		BotonGestionMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lib.abrirPantalla(new PantallaGestionMenu());
				dispose();
			}
		});
		BotonGestionMenu.setBounds(101, 194, 179, 29);
		contentPane.add(BotonGestionMenu);
		
		JLabel lblNewLabel = new JLabel("Menu administrador");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setBounds(6, 6, getWidth()-12, 61);
		contentPane.add(lblNewLabel);
	}

}
