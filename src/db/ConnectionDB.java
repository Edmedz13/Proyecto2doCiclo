package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import menus.Producto;

public class ConnectionDB {
	private Statement statement;
	// Se crea el Constructor
	public ConnectionDB(){
		String jdbcUrl = "jdbc:sqlite:/C:\\Users\\ed_mu\\Downloads\\Sqlite\\sqlite-tools-win32-x86-3420000\\MiBaseDeDatos.db";
		
		try{
	        Connection connection = DriverManager.getConnection(jdbcUrl);  //Se conecta la base de datos
	        

	        this.statement = connection.createStatement();    // nos permite hacer consultas (queries) a la BD
	        }

	    catch (SQLException e) {
	        System.out.println("Error ");
	        e.printStackTrace();
	    }

	}
	/*public ArrayList<String> getNombres(){
		String peticion = "SELECT * FROM users";	
		
		ArrayList<String> salida = new ArrayList<>();
		try{
		ResultSet result = statement.executeQuery(peticion);
	        while (result.next()){
	            String name = result.getString("name");
	            salida.add(name);
	            
		}	
		catch (SQLException e) {
	        System.out.println("Error ");
	        e.printStackTrace();
	    }
		return salida;
	}*/
//Se empiezan a crear los metodos publicos de la clase ConnectionDB
	public void registrarProductos(String nombre, double precio, int cantidad) {   // definimos un metodo para registrar productos en la BD products , que no retorna nada
		String precioParseado = String.valueOf(precio); 
		String cantidadParseada = String.valueOf(cantidad); //parseo los datos a String para poder hacer el registro en la DB
		String peticion = String.format("insert into products (name, price,stock) values ('%s',%s,%s)",nombre,precioParseado,cantidadParseada);  // creamos el formato para hacer el query	
		try{
			statement.executeUpdate(peticion);  // este es un metodo de la clase Statement que permite actualizar la base de datos y le la query como argunmento
			
		      		            
			}	
			catch (SQLException e) {
		        System.out.println("Error ");
		        e.printStackTrace();
		    }
		
	}
	public ArrayList<Producto> getProductos(){     // Creamos un método para obtener valores de la BD y guardarlos en una Array de Producto(clase que tenemos definida aparte) llamado salida
		String peticion = "SELECT * FROM products";	
		
		ArrayList<Producto> salida = new ArrayList<>();
		try{
		ResultSet result = statement.executeQuery(peticion);
	        while (result.next()){
	            String nombre = result.getString("name");      //name,price y stock están definidos como campos en la tabla produts
	            double precio = result.getDouble("price");
	            int cantidad = result.getInt("stock");
	            Producto producto = new Producto(nombre,precio,cantidad);  // creamos una instancia de la clase Producto 
	            salida.add(producto);   // y le agregamos los datos al arrayList salida
	            	        }
		}	
		catch (SQLException e) {
	        System.out.println("Error ");
	        e.printStackTrace();
	    }
		return salida;    // este es un ArrayList de Producto
	}
	// Creamos el metodo updateStock para actualizar en la base de datos el stock, despues de hacer una venta en el programa
	public void updateStock(Object[][] data){     //tiene como parámetro una matriz bi dimensional
		String peticion;
		String nombre;
		Integer cantidad; 
		for(int i=0; i<data.length;i++){    //Hacemos un for para recorrer la matriz
			nombre = data[i][0].toString();  //  guardamos en nombre, cada nombre de producto que recorro de la venta
			cantidad=Integer.parseInt((data[i][1]).toString()); //guardamos en cantidad, cada cantidad de producto que se vendió
			peticion = String.format("select stock from products where name='%s'", nombre); // creamos la consulta(query) para selecionar el stock (la cantidad que hay de este producto en la BD)
			try{
				ResultSet result = statement.executeQuery(peticion);		//ejecutamos la consulta por cada iteración
				int cantidadBD = result.getInt("stock");					// asignamos a una variable el valor de stock que retorna el result
				String peticion2 = String.format("update products set stock = %s where name= '%s'",cantidadBD - cantidad,nombre); // creamos la petición para updatear el nuevo stock
				statement.executeUpdate(peticion2); // ejecutamos la query
			}
			catch(SQLException e){ 
			System.out.println("Error ");
	        e.printStackTrace();
			}
		}
		 
		
	}
	public void registrarVentaDB(String nombre,Integer cantidad, Double precio){
		String peticionExiste= "SELECT * FROM ventas "; //
		int cantidadActual;
		try{
			ResultSet result = statement.executeQuery(peticionExiste);
			while(result.next()){
				if(result.getString("name").equals(nombre)){
					cantidadActual = result.getInt("cantidad");
					String peticion2 = String.format("update ventas set cantidad = %s where name= '%s'",cantidadActual+cantidad,nombre);
					statement.executeUpdate(peticion2);
					return; 
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		String peticionInsertar = String.format("insert into ventas (name, price,cantidad) values ('%s',%s,%s)",nombre,precio,cantidad);
		try{
			statement.executeUpdate(peticionInsertar);  // este es un metodo de la clase Statement que permite actualizar la base de datos y le da query como argunmento
			}	
		catch (SQLException e) {
	        System.out.println("Error ");
	        e.printStackTrace();
		    }
	}
	public void registrarVenta(Object[][] data){
		String peticion;
		String nombre;
		Integer cantidad;
		Double precio;
		for(int i=0;i<data.length;i++){
			nombre = data[i][0].toString();
			cantidad = Integer.parseInt((data[i][1]).toString());
			precio = Double.parseDouble((data[i][2]).toString());
			registrarVentaDB(nombre, cantidad, precio);
			}
	}
	
	
	
	public Object[][] obtenerHistorial(){
		String peticion = "SELECT * FROM ventas";
		ArrayList<Object[]> array = new ArrayList<>();
		Object[][] salida;
		try{
			ResultSet result = statement.executeQuery(peticion);
			int contador =0;
			salida = new Object[contador][3];
			contador = 0;
			while(result.next()){
				Object[] venta = new Object[3];
				venta[0] = result.getString("name");
				venta[1] = result.getDouble("price");
				venta[2] = result.getInt("cantidad");
				array.add(venta);
			}
			salida = new Object[array.size()][3];
			for(int i=0; i<array.size();i++){
				salida[i] = array.get(i);
			}
			return salida;
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}
}		


