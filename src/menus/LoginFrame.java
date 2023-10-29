package menus;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private final JPanel panel_1 = new JPanel();
	


	public static void main(String[] args) {   // Este es el main, acá se corre el programa y empieza con el Login
		
    
		EventQueue.invokeLater(new Runnable() {    //Acá se corre el GUi, LoginFrame
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	
	public LoginFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/Imagenes/oceanoChico.jpeg")));
		setTitle("\tBienvenido!"); //Texto en la barra superior de la ventana GUI
		setBackground(new Color(178, 34, 34)); //Color de fondo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exit on close, hace que cuando cerremos la ventana se cierre esta y todas las ventanas abiertas
		setBounds(100, 100, 329, 451);
		contentPane = new JPanel();// configuraciones en el GUI
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		
		JPanel panel = new JPanel();  // configuraciones del panel marron del costado izquierdo
		panel.setBackground(new Color(210, 180, 140));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(-11, -11, 335, 434);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario    :");  // Jlabel que se muestran en la GUI, Usario
		lblUsuario.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		lblUsuario.setBounds(96, 126, 76, 14);
		panel.add(lblUsuario); 
		
		JLabel lblPassword = new JLabel("Password  :");  // Y contraseña
		lblPassword.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		lblPassword.setBounds(96, 184, 95, 14);
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();		//Nombre de el textField donde se ingresará la contraseña
		txtPassword.setBackground(new Color(204, 204, 204));
		txtPassword.setBounds(198, 181, 89, 20);
		panel.add(txtPassword);
		
		txtUsuario = new JTextField();         //Nombre de el textField donde se ingresará el Usuario
		txtUsuario.setBackground(new Color(204, 204, 204));
		txtUsuario.setBounds(198, 123, 86, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnNewButton = new JButton("Entrar");  // Boton para entrar, luego se le dará una action listener
		btnNewButton.setFont(new Font("Calibri Light", Font.BOLD, 14));
		btnNewButton.setBounds(201, 284, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.setBackground(new Color(139, 69, 19));
		btnNewButton.setForeground(new Color(240, 255, 255));
		panel_1.setBackground(new Color(204, 153, 102));
		panel_1.setBounds(10, 0, 76, 434);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("\t\t\t\t\t\t\t\tOc\u00E9ano Caf\u00E9 Rock"); // Titulo Oceano Rock Cafe
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(96, 24, 191, 73);
		panel.add(lblNewLabel);
		
		JLabel lblError = new JLabel("Usuario y/o contraseña incorrecta"); //Jlabel que muestra el error al poner mal el usuario y/o contraseña
		lblError.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		lblError.setVisible(false); 										// En este momento sale invisible, se hace visible al poner mal usuario y contra
		lblError.setForeground(new Color(220, 20, 60));
		lblError.setBounds(121, 370, 204, 20);
		panel.add(lblError);
		
		btnNewButton.addActionListener(new ActionListener() {    //Empieza la accion, el proceso al presionar el boton "Entrar"
		
			public void actionPerformed(ActionEvent arg0) {
				String usuario, password;  //  se declara las variables usuario y contraseña de tipo String
				usuario = txtUsuario.getText();  // se guarda en las variables lo ingresa en los txtfield
				password = String.valueOf(txtPassword.getPassword()); // el Jpassword field nos devuelve un char, así que lo pasamos a String con el metodo. valueof y lo guardamos en contraseña
				
				
				String usuarioGarca = "admin";  //Creamos variables que servirán para comparar con lo ingresado para saber si está bien o no el usuario y contraseña ingresado
				String passwordFake = "1234";
				
				if(usuario.equals(usuarioGarca) && password.equals(passwordFake)){  // hacemos la condicion, si cumple que lo ingresado en usuario es admin y en password 1234
					try {
						MenuDesplegable frame = new MenuDesplegable(); //Si ambos campos son correctos  el try hace visible la ventana del menu desplegable
						frame.setVisible(true);
						lblError.setVisible(false);//Se esconde el msj de error
						setVisible(false); // Se deja de mostrar el loginframe
					} catch (Exception e) {  
						e.printStackTrace();
					}
				}
				else{
					lblError.setVisible(true);//Si no, nos pedirá ingresemos valores válidos en usuario y password
					txtUsuario.setText("");
					txtPassword.setText("");
					
				}
					
					
				
				
				
				
				
			}
		});
	}
}
