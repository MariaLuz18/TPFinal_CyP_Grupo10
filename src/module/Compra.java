package module;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Compra {
	private int idCompra;
	private LocalDate fecha;
	private List<DetalleCompra> detalles;
	private Cliente cliente;

	public Compra(int idCarrito, LocalDate fecha, Cliente cliente) {
		super();
		this.idCompra = idCarrito;
		this.fecha = fecha;
		this.detalles = new ArrayList<DetalleCompra>();
		this.cliente = cliente;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<DetalleCompra> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleCompra> detalles) {
		this.detalles = detalles;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "\n\tCompra [idCompra=" + idCompra + ", fecha=" + fecha + ", detalles=" + detalles + ", cliente="
				+ cliente + "]";
	}

	/// Metodos:
	public DetalleCompra traerDetalleCompra(Articulo articulo) {
		DetalleCompra item = null;
		int i = 0;

		if (detalles.size() == 0) {
			return item;
		}

		DetalleCompra actual;
		while (i < detalles.size() && item == null) {
			actual = detalles.get(i);
			if (actual.getArticulo().equals(articulo)) {
				item = actual;
			}
			i++;
		}

		return item;
	}

	public boolean agregarDetalleCompra(Articulo articulo, int cantidad) {
		DetalleCompra item = traerDetalleCompra(articulo);
		boolean retorno = false;
		if (item != null) {
			item.setCantidad(item.getCantidad() + cantidad);
		} else {
			int id = 1;
			if (!detalles.isEmpty()) {
				id = detalles.get(detalles.size() - 1).getIdDetalleCompra() + 1;
			}
			detalles.add(new DetalleCompra(id, articulo, cantidad));
			retorno = true;
		}
		articulo.aumentarCantidadVendida(cantidad);
		return retorno;
	}

	public boolean eliminarDetalleCompra(Articulo articulo, int cantidad) throws Exception {
		DetalleCompra item = traerDetalleCompra(articulo);
		if (item == null) {
			throw new Exception("El item del producto " + articulo.getNombre() + " no está en el carrito.");
		}
		if (cantidad >= item.getCantidad()) {
			detalles.remove(item);
		} else {
			item.setCantidad(item.getCantidad() - cantidad);
		}
		articulo.decrementarCantidadVendida(cantidad);
		return true;
	}

	public float calcularTotal() {
		float total = 0;
		for (DetalleCompra dc : detalles) {
			total += dc.calcularSubtotal();
		}
		return total;
	}

	public boolean existeArticuloEnDetalle(Articulo articulo) {
		boolean existe = false;

		int i = 0;
		while (i < detalles.size() && !existe) {
			if (detalles.get(i).getArticulo().equals(articulo)) {
				existe = true;
			}
			i++;
		}
		return existe;
	}
}
