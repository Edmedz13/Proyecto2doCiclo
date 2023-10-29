package menus;

public class Producto {
	private String nombre;
	
	private double precio;
	private int cantidad;
	private String marca;
	private int codigo;
	private double pito;
	
	
	public Producto(String nombre, double precio, int cantidad) {     // es el constructor de la clase Producto con sus atributos (nombre,precio,cantidad)
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public Producto(String marca, int codigo, double pito){
		this.marca=marca;
		this.codigo = codigo;
		this.pito=pito;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * Esta funcion retorna la cantidad del producto
	 * @return esto retorna cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
