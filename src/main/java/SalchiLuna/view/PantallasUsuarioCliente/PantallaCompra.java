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
		scrollPane.setBounds(38, 124, 391, 248);
		contentPane.add(scrollPane);
		
		Menu menu = new Menu();
		modelo_1=new DefaultTableModel(menu.obtenerMenuTabla(),columasTablaMenu);
		TablaMenu = new JTable(modelo_1);
		scrollPane.setViewportView(TablaMenu);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(483, 124, 372, 248);
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
		AgregarCarrito.setBounds(162, 398, 166, 29);
		contentPane.add(AgregarCarrito);
		
		JButton EliminarCarrito = new JButton("Eliminar de carrito");
		EliminarCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionEliminarProducto();
			}
		});
		EliminarCarrito.setBounds(638, 398, 166, 29);
		contentPane.add(EliminarCarrito);
		
		JButton RealizarCompra = new JButton("Realizar compra");
		RealizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				realizarCompra();
			}
		});
		RealizarCompra.setBounds(403, 398, 150, 29);
		contentPane.add(RealizarCompra);
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
