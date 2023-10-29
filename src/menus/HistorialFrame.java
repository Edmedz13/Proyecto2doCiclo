package menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HistorialFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public HistorialFrame(Object[][] data) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"Producto", "Cantidad", "Precio"
			}
		));
		table.setBounds(23, 11, 372, 225);
		contentPane.add(table);
	}
}
