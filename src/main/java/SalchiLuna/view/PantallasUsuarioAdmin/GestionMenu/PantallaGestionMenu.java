package SalchiLuna.view.PantallasUsuarioAdmin.GestionMenu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.controller.database.ProductoController;
import SalchiLuna.model.classes.Menu;
import SalchiLuna.model.generic.Tupla;
import SalchiLuna.view.PantallasUsuarioAdmin.PantallaMenuAdmin;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaGestionMenu extends JFrame {

	private Libreria lib = new Libreria();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TablaMenu;
	private String[] columnas = {"numero","nombre","descripcion","precio"};
	private Object[][] menuTabla;
	private DefaultTableModel modelo;
	private JScrollPane scrollPane;

	public PantallaGestionMenu() {

		Menu menu = new Menu();
		menuTabla = menu.obtenerMenuTabla();

		modelo = new DefaultTableModel(menuTabla, columnas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 82, 378, 305);
		contentPane.add(scrollPane);
		
		TablaMenu = new JTable(modelo);
		scrollPane.setViewportView(TablaMenu);
		
		JButton BotonVolver = new JButton("Volver");
		BotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lib.abrirPantalla(new PantallaMenuAdmin());
				dispose();
			}
		});
		BotonVolver.setBounds(6, 6, 117, 29);
		contentPane.add(BotonVolver);
		
		JButton BotonAgregar = new JButton("Agregar producto");
		BotonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lib.abrirPantalla(new PantallaAgregarProducto());
				dispose();
			}
		});
		BotonAgregar.setBounds(81, 399, 147, 29);
		contentPane.add(BotonAgregar);
		
		JButton BotonEliminar = new JButton("Eliminar producto");
		BotonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionEliminarProducto();
			}
		});
		BotonEliminar.setBounds(240, 399, 147, 29);
		contentPane.add(BotonEliminar);
	}

	public void ActualizarTablaMenu(){
		Menu menu = new Menu();
		Object[][] tabla = menu.obtenerMenuTabla();
		menuTabla=tabla;
		modelo= new DefaultTableModel(tabla, columnas);
		TablaMenu = new JTable(modelo);
		scrollPane.setViewportView(TablaMenu);
	}

	public void AccionEliminarProducto(){
		String[] opciones = {"No","Si"};
		String elejido = lib.EntradaBotones(opciones,"Seguro que desea eliminar el producto?","Confirmar eliminacion");
		if (elejido.equals(opciones[1])){
			ProductoController productoController = new ProductoController();
			int filaSeleccionada = TablaMenu.getSelectedRow();
			if (filaSeleccionada != -1) { 
				int numero = (int) TablaMenu.getValueAt(filaSeleccionada, 0);
				Tupla respuesta = productoController.eliminarProducto(numero);
				if (respuesta.getObjetoBoolean("salida")){
					ActualizarTablaMenu();
					lib.MostrarConfirmacion(respuesta.getObjetoString("mensaje"));
				}else{
					lib.MostrarError(respuesta.getObjetoString("mensaje"));
				}
			}else{
				lib.MostrarError("Elija un producto a eliminar");
			}
		}
	}

}
