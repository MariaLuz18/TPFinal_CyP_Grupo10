package module;

public class DetalleCompra {
	private int idDetalleCompra;
	private Articulo articulo;
	private int cantidad;

	public DetalleCompra(int idDetalleCompra, Articulo articulo, int cantidad) {
		super();
		this.idDetalleCompra = idDetalleCompra;
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public int getIdDetalleCompra() {
		return idDetalleCompra;
	}

	public void setIdDetalleCompra(int idDetalleCompra) {
		this.idDetalleCompra = idDetalleCompra;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "DetalleCompra [idDetalleCompra=" + idDetalleCompra + ", articulo=" + articulo + ", cantidad=" + cantidad
				+ "]";
	}

	// Metodos:
	public float calcularSubtotal() {
		return articulo.getPrecio() * cantidad;
	}

}
