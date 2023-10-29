package menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Window.Type;

public class HistorialFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public HistorialFrame(Object[][] data) {
		setTitle("\t\t\t\t\t\t\t\t\tREPORTES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 421);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBackground(new Color(192, 192, 192));
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Producto", "Cantidad", "Precio", "Total"
			}
		));
		table.setBounds(22, 79, 418, 223);
		contentPane.add(table);
		
		JLabel lblNewLabel = new JLabel("Producto");
		lblNewLabel.setBounds(32, 54, 62, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PrecioUnitario");
		lblNewLabel_1.setBounds(128, 54, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(254, 54, 62, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SubTotal");
		lblNewLabel_3.setBounds(342, 54, 62, 14);
		contentPane.add(lblNewLabel_3);
	}
}
