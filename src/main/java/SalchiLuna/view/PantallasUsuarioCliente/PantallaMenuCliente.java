package SalchiLuna.view.PantallasUsuarioCliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SalchiLuna.view.constants.DimencionesPantalla;

public class PantallaMenuCliente extends JFrame implements DimencionesPantalla{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public PantallaMenuCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_3_ANCHO, PANTALLA_3_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
