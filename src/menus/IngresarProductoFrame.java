package menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import db.ConnectionDB;

import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class IngresarProductoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreProducto;
	private JTextField txtPrecioProducto;
	private JTable table;
	private JTextField txtCantidadProducto;
	
	ArrayList<Producto> productos = new ArrayList<>();    // instanciamos un ArrayList de tipo Producto
	ConnectionDB conexion = new ConnectionDB();            //instanciamos un ConnectionDB 
	

	
	public IngresarProductoFrame() {			
		setIconImage(Toolkit.getDefaultToolkit().getImage(IngresarProductoFrame.class.getResource("/Imagenes/oceanoChico.jpeg")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // si se cierra la ventana, no se cierra todo el programa, solo esta
		setBounds(100, 100, 639, 537);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 180, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAgregarProductos = new JLabel("Ingresar Productos");
		lblAgregarProductos.setBounds(230, 11, 170, 35);
		lblAgregarProductos.setFont(new Font("Verdana", Font.BOLD, 15));
		contentPane.add(lblAgregarProductos);
		
		JLabel lblNewLabel = new JLabel("Nombre de Producto    :");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(59, 81, 139, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Precio");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(59, 131, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.setBounds(235, 78, 86, 20);
		contentPane.add(txtNombreProducto);
		txtNombreProducto.setColumns(10);
		
		txtPrecioProducto = new JTextField();
		txtPrecioProducto.setBounds(235, 128, 86, 20);
		contentPane.add(txtPrecioProducto);
		txtPrecioProducto.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNewLabel_2.setBounds(59, 188, 112, 14);
		contentPane.add(lblNewLabel_2);
		
		table = new JTable();   // creamos la tabla
		table.setBackground(Color.WHITE);
		table.setForeground(new Color(139, 69, 19));
		table.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		table.setBounds(59, 274, 504, 209);
		actualizarTabla(conexion);
		contentPane.add(table);
		
		JLabel lblError = new JLabel("Por favor ingresa datos v\u00E1lidos");  // un Jlabel que muestra un error
		lblError.setForeground(new Color(165, 42, 42));
		lblError.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblError.setBounds(218, 233, 194, 14);
		contentPane.add(lblError);
		lblError.setVisible(false);
		
		JButton btnIngresarProducto = new JButton("Ingresar Producto");     
		btnIngresarProducto.setIcon(new ImageIcon(IngresarProductoFrame.class.getResource("/Imagenes/databaseprocess_basededatos_12922.png")));
		btnIngresarProducto.setBackground(Color.LIGHT_GRAY);
		btnIngresarProducto.setBounds(383, 110, 180, 63);
		btnIngresarProducto.addActionListener(new ActionListener() {  // la accion que genera el presionar el boton
			public void actionPerformed(ActionEvent arg0) {
				String nombreProducto;
				int cantidadProducto;
				double precioProducto;    // creamos variables para asignar los valores ingresados el los valores ingresados por el usuario
				
				nombreProducto = txtNombreProducto.getText().trim().toUpperCase();   // le cambiamos el formato para que no tenga espacios ni al principio ni final y se cambie a mayúscula
				try{
					precioProducto = Double.parseDouble(txtPrecioProducto.getText());
					cantidadProducto = Integer.parseInt(txtCantidadProducto.getText());   // asignamos los valores ingresados a nuestras variables
					conexion.registrarProductos(nombreProducto, precioProducto, cantidadProducto);   //registramos los productos en la DB
					actualizarTabla(conexion);   // se actualiza la tabla
					lblError.setVisible(false);
					limpiarCampos();
					
					
				}
				catch(NumberFormatException e){
					lblError.setVisible(true);
					limpiarCampos();
					
				}
			}
		});
		
		
		contentPane.add(btnIngresarProducto);
		
		
		
		JLabel lblS = new JLabel("S/.");
		lblS.setBounds(206, 131, 19, 14);
		contentPane.add(lblS);
		
		txtCantidadProducto = new JTextField();
		txtCantidadProducto.setBounds(235, 185, 54, 20);
		contentPane.add(txtCantidadProducto);
		txtCantidadProducto.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204,153,102));
		panel.setBounds(0, 0, 633, 63);
		contentPane.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Producto");
		lblNewLabel_3.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblNewLabel_3.setBounds(82, 256, 116, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Precio");
		lblNewLabel_4.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblNewLabel_4.setBounds(251, 258, 86, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Stock");
		lblNewLabel_5.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel_5.setBounds(422, 256, 64, 14);
		contentPane.add(lblNewLabel_5);
		
		
	}
	public void actualizarTabla(ConnectionDB conexion){     // creamos un metodo que recibe la instancia de ConnectionDB
		DefaultTableModel model = new DefaultTableModel(    // creamos una tabla
				new Object[][] {
								
				},
				new String[] {
					"Producto", "Precio", "Cantidad"
					}
				);
		ArrayList<Producto> listaProductos = conexion.getProductos();  // traemos un arrayList de Producto obtenidos de la BD
		for(Producto producto : listaProductos){   // recorremos la longitud total de listaProductos con el fin de extraer los valores
			Object fila[]= new Object[3];   // creamos un array que tiene  una longitud estatica
			fila[0] = producto.getNombre();  // asignamos los valores en la tabla
			fila[1] = formatoMoneda(producto.getPrecio());
			fila[2] = producto.getCantidad();     
			model.addRow(fila);  // definimos el modelo utilizando fila
		}
		table.setModel(model);  // y ahora agregamos a la tabla del GUI
		
	}
	public void limpiarCampos(){   // metodo para limpiar campos
		txtNombreProducto.setText("");
		txtPrecioProducto.setText("");
		txtCantidadProducto.setText("");
	}
	public String formatoMoneda(Double precio){ // metodo formato moneda
		return "S/." + precio.toString();
		
	}
}
	