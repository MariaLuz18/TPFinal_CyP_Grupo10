package module;

public class Articulo {
	private int idArticulo;
	private String nombre;
	private String tipo;
	private float precio;
	private int cantidadVendida;

	public Articulo(int idArticulo, String nombre, String tipo, float precio) throws Exception {
		super();
		this.idArticulo = idArticulo;
		this.nombre = nombre;
		this.setTipo(tipo);
		this.precio = precio;
		this.cantidadVendida = 0;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) throws Exception {
		if (tipo.equals("fruta") || tipo.equals("mueble") || tipo.equals("cuboRubik")) {
			this.tipo = tipo;
		} else {
			throw new Exception("ERROR: Solo hay 3 tipos de articulos: fruta, mueble o cuboRubik.");
		}
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public void aumentarCantidadVendida(int cantidad) {
		this.cantidadVendida += cantidad;
	}

	public void decrementarCantidadVendida(int cantidad) {
		this.cantidadVendida -= cantidad;
	}

	@Override
	public String toString() {
		return "\n\tArticulo [idArticulo=" + idArticulo + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio
				+ "]";
	}

	public boolean equals(Articulo articulo) {
		return articulo.getNombre().equals(this.getNombre());
	}

}
