package SalchiLuna.view.PantallasUsuarioAdmin;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import SalchiLuna.Librerias.Libreria;
import SalchiLuna.model.classes.Pedidos;
import SalchiLuna.view.constants.DimencionesPantalla;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class PantallaGestionPedidos extends JFrame implements DimencionesPantalla{
	private Libreria lib = new Libreria();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable TablaPedidosPendientes;
	private Pedidos pedidos = new Pedidos();
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;
	String[] nombreColumna = {"numero","usuario","carrito","fechaPedido","estado","metodoPago"};
	private JButton BotonVerDetalles;
	private JButton BotonVolver;

	public PantallaGestionPedidos() {
		pedidos.cargarPedidosPendientes();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_4_ANCHO, PANTALLA_4_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 126, 577, 209);
		contentPane.add(scrollPane);
		
		modelo = new DefaultTableModel(pedidos.obtenerTablaPedidosPendientes(),nombreColumna);
		TablaPedidosPendientes = new JTable(modelo);
		scrollPane.setViewportView(TablaPedidosPendientes);
		
		JButton BotonCompletarPedido = new JButton("Completar pedido");
		BotonCompletarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionCompletarPedido();
			}
		});
		BotonCompletarPedido.setBounds(383, 347, 156, 29);
		contentPane.add(BotonCompletarPedido);
		
		BotonVerDetalles = new JButton("Ver detalles");
		BotonVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccionVerDetalles();
			}
		});
		BotonVerDetalles.setBounds(158, 347, 156, 29);
		contentPane.add(BotonVerDetalles);
		
		BotonVolver = new JButton("Volver");
		BotonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaMenuAdmin pantallaMenuAdmin = new PantallaMenuAdmin();
				lib.abrirPantalla(pantallaMenuAdmin);
				dispose();
			}
		});
		BotonVolver.setBounds(6, 6, 117, 29);
		contentPane.add(BotonVolver);
		
		JLabel lblNewLabel = new JLabel("Gestion de pedidos");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setBounds(6, 47, 688, 67);
		contentPane.add(lblNewLabel);
	}

	public void AccionCompletarPedido(){
		int filaSeleccionada = TablaPedidosPendientes.getSelectedRow();
		if (filaSeleccionada != -1) { 
			int idPedido = (int) TablaPedidosPendientes.getValueAt(filaSeleccionada, 0);
			pedidos.completarPedido(idPedido);
			modelo = new DefaultTableModel(pedidos.obtenerTablaPedidosPendientes(),nombreColumna);
			TablaPedidosPendientes = new JTable(modelo);
			scrollPane.setViewportView(TablaPedidosPendientes);
			lib.MostrarConfirmacion("Se completo el pedido selecionado");
		}else{
			lib.MostrarError("Escoja un pedido a completar");
		}
	}

	public void AccionVerDetalles(){
		int filaSeleccionada = TablaPedidosPendientes.getSelectedRow();
		if (filaSeleccionada != -1) {
			int idPedido = (int) TablaPedidosPendientes.getValueAt(filaSeleccionada, 0);
			int idCarrito = (int) TablaPedidosPendientes.getValueAt(filaSeleccionada, 2);
			PantallaDetallesPedido pantallaDetallesPedido = new PantallaDetallesPedido(idPedido,idCarrito);
			lib.abrirPantalla(pantallaDetallesPedido);
		}
	}
}
