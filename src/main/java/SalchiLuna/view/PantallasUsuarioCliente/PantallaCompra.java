package SalchiLuna.view.PantallasUsuarioCliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.controller.database.CarritoController;
import SalchiLuna.model.classes.CarritoCompra;
import SalchiLuna.model.classes.Menu;
import SalchiLuna.model.database.Producto;
import SalchiLuna.view.constants.DimencionesPantalla;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PantallaCompra extends JFrame implements DimencionesPantalla{

	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TablaCarrito;
	private JTable TablaMenu;
	private CarritoCompra carritoCompra;
	private DefaultTableModel modelo_1;
	private DefaultTableModel modelo_2;
	private JScrollPane scrollPane_1;

	private String[] columasTablaMenu = {"numero","nombre","descripcion","precio"};
	private String[] columasTablaCarrito = {"numero","nombre","sub total","cantidad"};


	public PantallaCompra(int idUsuario) {
		this.carritoCompra = new CarritoCompra(idUsuario);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_2_ANCHO, PANTALLA_2_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 170, 391, 202);
		contentPane.add(scrollPane);
		
		Menu menu = new Menu();
		modelo_1=new DefaultTableModel(menu.obtenerMenuTabla(),columasTablaMenu);
		TablaMenu = new JTable(modelo_1);
		scrollPane.setViewportView(TablaMenu);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(478, 170, 391, 202);
		contentPane.add(scrollPane_1);

		
		modelo_2=new DefaultTableModel(carritoCompra.obtenerTabla(),columasTablaCarrito);
		TablaCarrito = new JTable(modelo_2);
		scrollPane_1.setViewportView(TablaCarrito);
		
		JButton AgregarCarrito = new JButton("Agregar a carrito");
		AgregarCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionAgregarCarrito();
			}
		});
		AgregarCarrito.setBounds(149, 384, 166, 29);
		contentPane.add(AgregarCarrito);
		
		JButton EliminarCarrito = new JButton("Eliminar de carrito");
		EliminarCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionEliminarProducto();
			}
		});
		EliminarCarrito.setBounds(593, 384, 166, 29);
		contentPane.add(EliminarCarrito);
		
		JButton RealizarCompra = new JButton("Realizar compra");
		RealizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarCompra();
			}
		});
		RealizarCompra.setBounds(376, 384, 150, 29);
		contentPane.add(RealizarCompra);
		
		JButton BotonVolver = new JButton("Volver");
		BotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lib.abrirPantalla(new PantallaMenuCliente(idUsuario));
				dispose();
			}
		});
		BotonVolver.setBounds(6, 6, 117, 29);
		contentPane.add(BotonVolver);
		
		JLabel lblNewLabel = new JLabel("Realizar compras");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setBounds(6, 47, 888, 65);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Menu");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(33, 124, 391, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Carrito de compras");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(478, 124, 391, 34);
		contentPane.add(lblNewLabel_1_1);
	}

	public void AccionAgregarCarrito(){
		int filaSeleccionada = TablaMenu.getSelectedRow();
		if (filaSeleccionada != -1) { 
			int id = (int) TablaMenu.getValueAt(filaSeleccionada, 0);
			String nombre = (String) TablaMenu.getValueAt(filaSeleccionada, 1);
			String dsecripcion = (String) TablaMenu.getValueAt(filaSeleccionada, 2);
			double precio =(Double) TablaMenu.getValueAt(filaSeleccionada, 3);
			carritoCompra.agregarProducto(new Producto(id, nombre, dsecripcion, precio));
			ActualizarTablaCarrito();
		}
	}

	public void AccionEliminarProducto(){
		int filaSeleccionada = TablaCarrito.getSelectedRow();
		if (filaSeleccionada != -1) { 
			int id = (int) TablaCarrito.getValueAt(filaSeleccionada, 0);
			String nombre = (String) TablaMenu.getValueAt(filaSeleccionada, 1);
			String dsecripcion = (String) TablaMenu.getValueAt(filaSeleccionada, 2);
			double precio =(Double) TablaMenu.getValueAt(filaSeleccionada, 3);
			carritoCompra.eliminarProducto(new Producto(id, nombre, dsecripcion, precio));
			ActualizarTablaCarrito();
		}
	}

	public void ActualizarTablaCarrito(){
		modelo_2= new DefaultTableModel(carritoCompra.obtenerTabla(),columasTablaCarrito);
		TablaCarrito = new JTable(modelo_2);
		scrollPane_1.setViewportView(TablaCarrito);
	}

	public void realizarCompra(){
		CarritoController carritoController = new CarritoController();
		String[] botones = {"No","Si"};
		if (lib.EntradaBotones(
			botones,
			"Su compra tiene un total de "+carritoCompra.obtenerTotal()+"\nDesea realizar la compra?",
			"Confirmar compra"
			).equals("Si")){
				carritoController.realizarCompra(carritoCompra);
		}
	}
}
