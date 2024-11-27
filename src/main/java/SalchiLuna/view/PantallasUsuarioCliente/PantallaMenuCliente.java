package SalchiLuna.view.PantallasUsuarioCliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.view.PantallasSesion.PantallaInicioSesion;
import SalchiLuna.view.constants.DimencionesPantalla;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PantallaMenuCliente extends JFrame implements DimencionesPantalla{
	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaMenuCliente(int idCliente) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_1_ANCHO, PANTALLA_1_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BontonRealizarCompra = new JButton("Realizar una compra");
		BontonRealizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaCompra pantallaCompra = new PantallaCompra(idCliente);
				lib.abrirPantalla(pantallaCompra);
				dispose();
			}
		});
		BontonRealizarCompra.setBounds(100, 98, 182, 29);
		contentPane.add(BontonRealizarCompra);
		
		JButton BotonVerNotificacion = new JButton("Ver notificaciones");
		BotonVerNotificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BotonVerNotificacion.setBounds(100, 163, 182, 29);
		contentPane.add(BotonVerNotificacion);
		
		JButton BotonCerrarSesion = new JButton("Cerrar sesion");
		BotonCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaInicioSesion pantallaInicioSesion = new PantallaInicioSesion();
				lib.abrirPantalla(pantallaInicioSesion);
				dispose();
			}
		});
		BotonCerrarSesion.setBounds(132, 234, 117, 29);
		contentPane.add(BotonCerrarSesion);
		
		JLabel lblNewLabel = new JLabel("Menu cliente");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 6, 368, 70);
		contentPane.add(lblNewLabel);
	}

}
