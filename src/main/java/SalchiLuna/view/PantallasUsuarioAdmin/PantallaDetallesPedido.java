package SalchiLuna.view.PantallasUsuarioAdmin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import SalchiLuna.controller.database.PedidoController;
import SalchiLuna.model.generic.Tupla;
import SalchiLuna.view.constants.DimencionesPantalla;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PantallaDetallesPedido extends JFrame implements DimencionesPantalla{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public PantallaDetallesPedido(int idPedido,int idCarrito) {
		PedidoController pedidoController = new PedidoController();
		Tupla datos = pedidoController.obtenerTablaCarrito(idPedido,idCarrito);
		String[] columnas = {"pedido","producto","precio","cantidad","subtotal"};
		Object[][] tabla = datos.getObjetoMatriz("tabla");
		DefaultTableModel model = new DefaultTableModel(tabla, columnas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, PANTALLA_4_ANCHO, PANTALLA_4_ALTO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 100, 571, 209);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton BotonCerrar = new JButton("Cerrar");
		BotonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		BotonCerrar.setBounds(291, 355, 117, 29);
		contentPane.add(BotonCerrar);
		
		JTextArea SalidaTotal = new JTextArea();
		SalidaTotal.setText(datos.getObjetoDouble("total").toString());
		SalidaTotal.setBounds(163, 321, 181, 22);
		contentPane.add(SalidaTotal);
		
		JLabel lblNewLabel = new JLabel("Detalle de pedido");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 31, 688, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setBounds(71, 321, 80, 22);
		contentPane.add(lblNewLabel_1);
	}
}
