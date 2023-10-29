package menus;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

public class BoletaFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblTotal;
	private JLabel lbl3;
	private JLabel lbl2;
	private JLabel lbl1;
	private JLabel lblSubTotal;
	private JLabel lblIgv;


	public BoletaFrame(Object[][] data, Double total) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BoletaFrame.class.getResource("/Imagenes/oceanoChico.jpeg")));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 331, 513);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("OCEANO ROCK CAFE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setBounds(96, 10, 131, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Calle Los Cerezos #105- Huanchaco");
		lblNewLabel_1.setForeground(new Color(0, 100, 0));
		lblNewLabel_1.setBounds(73, 35, 210, 14);
		contentPane.add(lblNewLabel_1);
		lbl3 = new JLabel("Total");
		lbl3.setForeground(new Color(0, 100, 0));
		lbl3.setBounds(155, 436, 46, 14);
		contentPane.add(lbl3);
		
		lbl2 = new JLabel("I.G.V");
		lbl2.setForeground(new Color(0, 100, 0));
		lbl2.setBounds(155, 404, 46, 14);
		contentPane.add(lbl2);
		
		lbl1 = new JLabel("SubTotal");
		lbl1.setForeground(new Color(0, 100, 0));
		lbl1.setBounds(143, 379, 58, 14);
		contentPane.add(lbl1);
		
		table = new JTable();
		table.setBackground(new Color(0, 0, 0));
		table.setForeground(new Color(0, 100, 0));
		table.setFont(new Font("Source Serif Pro Semibold", Font.BOLD, 12));
		DefaultTableModel model = new DefaultTableModel(data,new String[] {
				"Producto", "Precio", "Cantidad"
				});
		table.setModel(model);
		table.setBounds(29, 115, 254, 237);
		contentPane.add(table);
		
		lblTotal = new JLabel();
		lblTotal.setForeground(new Color(0, 100, 0));
		lblTotal.setBounds(211, 436, 58, 14);
		contentPane.add(lblTotal);
		
	
		lblSubTotal = new JLabel();
		lblSubTotal.setForeground(new Color(0, 100, 0));
		lblSubTotal.setBounds(211, 379, 58, 14);
		contentPane.add(lblSubTotal);
		
		lblIgv = new JLabel();
		lblIgv.setForeground(new Color(0, 100, 0));
		lblIgv.setBounds(211, 404, 58, 14);
		contentPane.add(lblIgv);
		Double subTotal = total*0.82;
		Double igv = total*0.18;
		lblTotal.setText(total.toString());
		lblSubTotal.setText(subTotal.toString());
		lblIgv.setText(igv.toString());
	}
}
