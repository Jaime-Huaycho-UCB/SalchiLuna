package SalchiLuna.view.PantallasUsuarioAdmin.GestionMenu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.controller.database.ProductoController;
import SalchiLuna.model.generic.Tupla;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaAgregarProducto extends JFrame {
	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EntradaNombre;
	private JTextField EntradaDescripcion;
	private JTextField EntradaPrecio;

	public PantallaAgregarProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		EntradaNombre = new JTextField();
		EntradaNombre.setBounds(169, 83, 130, 26);
		contentPane.add(EntradaNombre);
		EntradaNombre.setColumns(10);
		
		JLabel lbb = new JLabel("Nombre");
		lbb.setBounds(67, 88, 90, 16);
		contentPane.add(lbb);
		
		EntradaDescripcion = new JTextField();
		EntradaDescripcion.setColumns(10);
		EntradaDescripcion.setBounds(169, 154, 130, 60);
		contentPane.add(EntradaDescripcion);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(67, 159, 90, 16);
		contentPane.add(lblDescripcion);
		
		EntradaPrecio = new JTextField();
		EntradaPrecio.setColumns(10);
		EntradaPrecio.setBounds(169, 221, 130, 26);
		contentPane.add(EntradaPrecio);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(67, 226, 90, 16);
		contentPane.add(lblPrecio);
		
		JButton BotonVolver = new JButton("Volver");
		BotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lib.abrirPantalla(new PantallaGestionMenu());
				dispose();
			}
		});
		BotonVolver.setBounds(79, 400, 117, 29);
		contentPane.add(BotonVolver);
		
		JButton BotonAgregar = new JButton("Agregar");
		BotonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionAgregar();
			}
		});
		BotonAgregar.setBounds(236, 400, 117, 29);
		contentPane.add(BotonAgregar);
	}

	public void AccionAgregar(){
		String nombre = EntradaNombre.getText();
		String descripcion = EntradaDescripcion.getText();
		Double precio = Double.parseDouble(EntradaPrecio.getText());
		ProductoController productoController = new ProductoController();
		Tupla respuesta = productoController.agregarProducto(nombre, descripcion, precio);
		if (respuesta.getObjetoBoolean("salida")){
			lib.MostrarConfirmacion(respuesta.getObjetoString("mensaje"));
			lib.abrirPantalla(new PantallaGestionMenu());
			dispose();
		}else{
			lib.MostrarError(respuesta.getObjetoString("mensaje"));
		}
	}
}
