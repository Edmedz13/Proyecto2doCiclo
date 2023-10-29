package menus;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;

import db.ConnectionDB;

public class MenuDesplegable extends JFrame {
	private ConnectionDB conexion = new ConnectionDB();
	public MenuDesplegable() {
		setResizable(false); // No se puede modificar el tamaña por el usuario
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuDesplegable.class.getResource("/Imagenes/oceanoChico.jpeg"))); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // EXIT ON CLOSE, hace que si se cierra esta ventana se cierra todo el programa
		setBounds(100, 100, 486, 488);
		
		JMenuBar menuBar = new JMenuBar(); // Barra de menu en diseño
		setJMenuBar(menuBar);
		
		JMenu menuInventario = new JMenu("Inventario");  //menu inventario
		menuInventario.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/accessories_text_editor_16842.png"))); //icono de Inventario
		menuBar.add(menuInventario);
		
		JMenuItem subMenuIngresarProducto = new JMenuItem("Ingresar Producto");  //submenu Ingresar Producto, que nos lleva a la sigiuente ventana
		subMenuIngresarProducto.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/edit.png"))); // icono de "ingresar producto"
		subMenuIngresarProducto.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				try {
					IngresarProductoFrame frame = new IngresarProductoFrame();    //Se abre la Ventana Ingresasr Producto
					frame.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		menuInventario.add(subMenuIngresarProducto);
		
		
		JMenu mnNewMenu_1 = new JMenu("Venta");    //menu venta
		mnNewMenu_1.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/1486564172-finance-loan-money_81492 (1).png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem subMenuIngresarVenta = new JMenuItem("Ingresar Venta");    //submenu ingresar venta
		subMenuIngresarVenta.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/diagram-59_24458.png")));
		subMenuIngresarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  // se muestra la ventana ingresar venta
				try {
					VentaFrame frame = new VentaFrame();  
					frame.setVisible(true);
				} catch (Exception exx) {
					exx.printStackTrace();
				}
			}
		});
		mnNewMenu_1.add(subMenuIngresarVenta);
		
		JMenu mnRepositorio = new JMenu("Repositorio");  //menu del repositorio
		mnRepositorio.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/folder_saved_search_16890.png")));
		menuBar.add(mnRepositorio);


		
		JMenuItem subMenuGitHub = new JMenuItem("GitHub"); // menu que abre el github 
		subMenuGitHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirURL("https://github.com/Edmedz13/ProyectoFinal");
			}
		});
		subMenuGitHub.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/github_chico.png")));
		mnRepositorio.add(subMenuGitHub);
		
		JMenu mnHistorial = new JMenu("Reportes");
		mnHistorial.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/1486564180-finance-financial-report_81493.png")));
		menuBar.add(mnHistorial);
		
		JMenuItem mntmHistorial = new JMenuItem("Historial");
		mntmHistorial.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/edit.png")));
		mntmHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Object[][] data = conexion.obtenerHistorial();
					HistorialFrame frame = new HistorialFrame(data);
					frame.setVisible(true);
				} catch (Exception exx) {
					exx.printStackTrace();
				}
			}
		});
		mnHistorial.add(mntmHistorial);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 181));
		panel.setBounds(-11, 0, 585, 438);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 0, 575, 449);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(MenuDesplegable.class.getResource("/Imagenes/oceanoChico.jpeg")));
	}
			private static void abrirURL(String url) {    // un metodo para poder abrir un url
					if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.BROWSE)) {
						try {
                    desktop.browse(new URI(url));
						} catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
						}
					}
					}
			}
}
	

