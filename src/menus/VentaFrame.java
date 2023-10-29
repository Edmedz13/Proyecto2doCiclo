package menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.ConnectionDB;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class VentaFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;  // variable global que representa un Jtable
	private Double totalConsumo; // varaible global para pasarle el total a boletaFrame
	
	

	
	public VentaFrame() {
		ConnectionDB conexion = new ConnectionDB();   //instancia de ConnectionDB
		ArrayList<Producto> listaProductos = conexion.getProductos();   //asignamos en listaProductos los valores de la BD
		String[] listaNombres = new String [listaProductos.size()];  //creamos arrays para separar cada campo
		Double[] listaPrecios = new Double [listaProductos.size()];
		Integer[] listaCantidad = new Integer[listaProductos.size()]; 
		
		
		for(int i=0; i<listaProductos.size(); i++){   // recorremos cada array 
			listaNombres[i] = listaProductos.get(i).getNombre();   //get me retorna el dato que tenga en esa posición del array y lo asigno a listaNombres[i]
			listaPrecios[i] = listaProductos.get(i).getPrecio();
			listaCantidad[i] = listaProductos.get(i).getCantidad();
			//model.addRow(fila);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   // solo se cierra la ventana, mas no el programa
		setBounds(100, 100, 492, 608);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 180, 140));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Oc\u00E9ano Rock Caf\u00E9");
		lblNewLabel.setBounds(156, 11, 160, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("Producto");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNewLabel_1.setBounds(40, 71, 104, 14);
		contentPane.add(lblNewLabel_1);
		JLabel lblPrecioVenta = new JLabel("");
		lblPrecioVenta.setBounds(367, 71, 46, 14);
		contentPane.add(lblPrecioVenta);
		JLabel lblDisponible = new JLabel("-");
		lblDisponible.setBounds(124, 96, 46, 14);
		contentPane.add(lblDisponible);
		JLabel lblImpPagar = new JLabel("");
		lblImpPagar.setBounds(367, 121, 46, 14);
		contentPane.add(lblImpPagar);
		
		JComboBox comboBoxProducto = new JComboBox();
		comboBoxProducto.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 12));
		comboBoxProducto.setBackground(Color.LIGHT_GRAY);
		JSpinner spinnerCantidadVenta = new JSpinner();
		spinnerCantidadVenta.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		spinnerCantidadVenta.setBackground(Color.LIGHT_GRAY);
		
		
		spinnerCantidadVenta.addChangeListener(new ChangeListener() {    // se activa al usar el spinner
			public void stateChanged(ChangeEvent arg0) {
				int index = comboBoxProducto.getSelectedIndex();	// asignamos en index lo que se elige en el comboBox
				lblPrecioVenta.setText(Double.toString(listaPrecios[index]));  // mostramos en el lbl precio venta el precio obtenido del Array
				lblDisponible.setText(Integer.toString(listaCantidad[index])); // igual con el stock
				int cantidad = Integer.parseInt(spinnerCantidadVenta.getValue().toString());  //asigno a cantidad lo elegido en el spinner, transformando el valor del spinner en int
				Double importe = cantidad*listaPrecios[index];  // hacemos la operacion para obtener el importe a pagar
				lblImpPagar.setText(importe.toString());  // mostramos importe a pagar. 
				
			}
		});
		spinnerCantidadVenta.setBounds(124, 121, 55, 20);
		spinnerCantidadVenta.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		contentPane.add(spinnerCantidadVenta);
		
		
		
		comboBoxProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {   // accion del comboBox
				int index = comboBoxProducto.getSelectedIndex();   //obtenemos el indice seleccionado del comboBox
				int limite = listaCantidad[index]; // asignamos la cantidad de stock que tenemos de cada producto
				
				SpinnerNumberModel model = new SpinnerNumberModel(1,1,limite,1); // declaramos el modelo a mostrar para el spinner
				spinnerCantidadVenta.setModel(model);  // seteamos el spinner
				lblPrecioVenta.setText(Double.toString(listaPrecios[index]));  //seteamos el lbl precio venta 
				lblDisponible.setText(Integer.toString(listaCantidad[index])); // seteamos el lbl disponible
				int cantidad = Integer.parseInt(spinnerCantidadVenta.getValue().toString()); // asigno a cantidad lo elegido en el spinner
				Double importe = cantidad*listaPrecios[index]; //obtenemos el importe
				lblImpPagar.setText(importe.toString()); // seteamos el importe en el lbl
				
				
				
				
				
				
			}
		});
		comboBoxProducto.setBounds(124, 68, 124, 20);
		comboBoxProducto.setModel(new DefaultComboBoxModel(listaNombres));
		contentPane.add(comboBoxProducto);
		
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblCantidad.setBounds(40, 124, 74, 14);
		contentPane.add(lblCantidad);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNewLabel_2.setBounds(258, 71, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblINumero3 = new JLabel("Imp. Pagar");
		lblINumero3.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblINumero3.setBounds(258, 124, 86, 14);
		contentPane.add(lblINumero3);
		
		JLabel lblNewLabel_3 = new JLabel("S/.");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(346, 71, 29, 14);
		contentPane.add(lblNewLabel_3);
		
	
		
		
		
		JLabel lblNewLabel_4 = new JLabel("SubTotal");
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNewLabel_4.setBounds(269, 411, 67, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("I.G.V");
		lblNewLabel_5.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNewLabel_5.setBounds(290, 448, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Total");
		lblNewLabel_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(290, 484, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JButton btnVender = new JButton("Vender");
		btnVender.setBackground(Color.LIGHT_GRAY);
		
		btnVender.addActionListener(new ActionListener() {  // Boton Vender
			public void actionPerformed(ActionEvent arg0) {
				Double importe = Double.parseDouble(lblImpPagar.getText()); 
				Object[][] data = getTableData(table); // se actualiza la tabla que se muestra en la GUI
				//conexion.registrarVenta(data, monto );
				conexion.updateStock(data);			// se guarda el stock en la base de datos
				
				table.setModel(new DefaultTableModel(  // se crea tabla para mostrar en boletaFrame
						new Object[][] {
							
						},
						new String[] {
							"Descripción", "Cantidad", "P.Unidad", "P.Total"
						}
						
					));
				try {
					BoletaFrame frame = new BoletaFrame(data, totalConsumo); // se muestra la GUI boletaFrame
					conexion.registrarVenta(data);
					
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		});
		btnVender.setBounds(346, 522, 77, 23);
		contentPane.add(btnVender);
		
		
		
		
		
		JLabel lblTotal = new JLabel("0.00");
		lblTotal.setBounds(346, 484, 67, 14);
		contentPane.add(lblTotal);
		
		
		
		JLabel lblImportePagar = new JLabel("S/.");
		lblImportePagar.setBounds(346, 121, 29, 14);
		contentPane.add(lblImportePagar);
		
		JLabel lblIgv = new JLabel("0.00");
		lblIgv.setBounds(346, 448, 67, 14);
		lblIgv.setForeground(Color.BLACK);
		lblIgv.setBackground(Color.BLACK);
		contentPane.add(lblIgv);
		
		JLabel lblNewLabel_7 = new JLabel("Disponible");
		lblNewLabel_7.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblNewLabel_7.setBounds(40, 99, 74, 14);
		contentPane.add(lblNewLabel_7);
		
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		
		table.setModel(new DefaultTableModel(  // se crea la tabla donde se muestras los productos agregados a la compra de manera preliminar
			new Object[][] {
				
			},
			new String[] {
				"Descripción", "Cantidad", "P.Unidad", "P.Total"
			}
		));
		table.setBounds(55, 215, 367, 175);
		contentPane.add(table);
		
		
		
		JLabel lblNewLabel_8 = new JLabel("Descripci\u00F3n");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(66, 190, 78, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Cantidad");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(163, 190, 55, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("P. Unidad");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(248, 190, 68, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("P. Total");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_11.setBounds(343, 190, 55, 14);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblSubTotal = new JLabel("0.00");
		lblSubTotal.setBounds(346, 411, 67, 14);
		contentPane.add(lblSubTotal);
		
		JButton btnAgregarProductoVenta = new JButton("");   // Boton para agregar los productos a la tabla mostrada en el GUI
		btnAgregarProductoVenta.setBackground(Color.LIGHT_GRAY);
		btnAgregarProductoVenta.setIcon(new ImageIcon(VentaFrame.class.getResource("/Imagenes/signoSuma.png")));
		btnAgregarProductoVenta.setBounds(367, 146, 56, 33);
		btnAgregarProductoVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Object[] listaVentas = new Object[4];				// creamos un array con valor statico
			listaVentas[0] = comboBoxProducto.getSelectedItem().toString();	//llenamos el array con los datos de la venta ingresados por el usuario
			listaVentas[1]= spinnerCantidadVenta.getValue().toString();
			listaVentas[2] = lblPrecioVenta.getText();
			listaVentas[3]= lblImpPagar.getText();
			DefaultTableModel model = (DefaultTableModel) table.getModel();  // creamos una tabla llamada model y obtenemos el modelo de datos
			model.addRow(listaVentas);    // ahora el agregamos los datos 
			table.setModel(model);  //   mostamos los datos en la tabla   
			spinnerCantidadVenta.setValue(1); // despues de agregar un producto el spinner tiene que volver a 1
			actualizarLabelsVenta(lblTotal,lblSubTotal,lblIgv); // invocamos metodo para mostrar y actualizar los lbls total, subtotal y el igv
			
			}
		});
		contentPane.add(btnAgregarProductoVenta);
		
		
		JButton btnBorrarItem = new JButton("Borrar Producto"); // boton para borrar un producto dentro de la tabla de ventaFrame
		btnBorrarItem.setBackground(Color.LIGHT_GRAY);
		btnBorrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel(); // creamos una tabla y obtenemos el modelo de datos
				if(table.getSelectedRow() != -1) {  // valida que haya una fila seleccionada 
				model.removeRow(table.getSelectedRow()); // borrar la fila seleccionada
		               
				}
				actualizarLabelsVenta(lblTotal,lblSubTotal,lblIgv); // invoca el metodo para actualizar.......y el IGV
			}
		});
		btnBorrarItem.setBounds(55, 407, 140, 23);  
		contentPane.add(btnBorrarItem);
		JButton btnCancelarVenta = new JButton("Cancelar Venta");   // boton para borrar toda la tabla de la ventaframe, cancelando la venta
		btnCancelarVenta.setBackground(Color.LIGHT_GRAY);
		btnCancelarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(new DefaultTableModel(            // creamos una matriz vacía
						new Object[][] {
							
						},
						new String[] {
							"Descripción", "Cantidad", "P.Unidad", "P.Total" 
						}
						
					));
				actualizarLabelsVenta(lblTotal,lblSubTotal,lblIgv);  //invoca el metodo para actualizar .... y el IGV
			}
			
		});
		btnCancelarVenta.setBounds(55, 480, 140, 23);
		contentPane.add(btnCancelarVenta);
		
		
		
		
	}
	public static Object[][] getTableData (JTable table) {				
	    DefaultTableModel dtm = (DefaultTableModel) table.getModel();    // creamos una tabla
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();    	// asignamos el numero de filas y columnas
	    Object[][] tableData = new Object[nRow][nCol];                // creamos una matriz y le ponemos los valores antes obtenidos
	    for (int i = 0 ; i < nRow ; i++)
	        for (int j = 0 ; j < nCol ; j++)						// recorremos las filas y las columnas
	            tableData[i][j] = dtm.getValueAt(i,j);				// asignamos los valores a la nueva tabla creada
	    return tableData;										// retornamos la matriz
	}
	public void actualizarLabelsVenta(JLabel lblTotal, JLabel lblSubTotal, JLabel lblIgv){  //metodo para ir actualizando valores de total, subtotal y el IGV con cada modificacion
		Object[][] data = getTableData(table);   // obtiene los valores de la tabla
		Double total = 0.0;
		for(int i=0;i<data.length;i++){							// recorremos la matriz
			total += Double.parseDouble((String)data[i][3]);   // se va agregando al acumulador total , los datos que muestra la tabla en Precio Total
		}
		totalConsumo = total;						// variable global para pasar el total a la boletaFrame
		Double subTotal = total*0.82;			
		Double igv = total*0.18;
		DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();   // agregamos formato de dos decimales
		decimalFormatSymbols.setDecimalSeparator('.');
		total = Double.parseDouble(new DecimalFormat("0.00", decimalFormatSymbols).format(total));
		subTotal = Double.parseDouble(new DecimalFormat("0.00", decimalFormatSymbols).format(subTotal));
		igv = Double.parseDouble(new DecimalFormat("0.00", decimalFormatSymbols).format(igv));
		lblTotal.setText(total.toString());    // seteamos los valores en la GUI
		lblSubTotal.setText(subTotal.toString());
		lblIgv.setText(igv.toString());
		
		
	}
}
