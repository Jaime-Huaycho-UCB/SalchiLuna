package SalchiLuna.view.PantallasDeRedirecion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.config.MySQL;
import SalchiLuna.view.PantallasSesion.PantallaInicioSesion;
import SalchiLuna.view.PantallasSesion.PantallaRegistrar;
import SalchiLuna.view.constants.DimencionesPantalla;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaInicio extends JFrame implements DimencionesPantalla{
	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_3_ANCHO, PANTALLA_3_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BotonIniciarSesion = new JButton("Iniciar sesion");
		BotonIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaInicioSesion pantallaInicioSesion = new PantallaInicioSesion();
				lib.abrirPantalla(pantallaInicioSesion);
				dispose();
			}
		});
		BotonIniciarSesion.setBounds(123, 154, 154, 29);
		contentPane.add(BotonIniciarSesion);
		
		JButton BotonRegistrarse = new JButton("Registrarse");
		BotonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaRegistrar pantallaRegistrar = new PantallaRegistrar();
				lib.abrirPantalla(pantallaRegistrar);
				dispose();
			}
		});
		BotonRegistrarse.setBounds(123, 232, 154, 29);
		contentPane.add(BotonRegistrarse);
		
		JButton BotonCerrarPrograma = new JButton("Cerrar programa");
		BotonCerrarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySQL sql = new MySQL();
				sql.cerrarConexion();
				dispose();
			}
		});
		BotonCerrarPrograma.setBounds(123, 300, 154, 29);
		contentPane.add(BotonCerrarPrograma);
	}

}
