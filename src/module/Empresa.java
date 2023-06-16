package module;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
	private List<Articulo> articulos;
	private List<Cliente> clientes;
	private List<Compra> compras;

	public Empresa() {
		super();
		this.articulos = new ArrayList<Articulo>();
		this.clientes = new ArrayList<Cliente>();
		this.compras = new ArrayList<Compra>();
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	@Override
	public String toString() {
		return "Empresa \n[Articulos=" + articulos + "\nClientes=" + clientes + "\nCompras=" + compras + "]";
	}

	/// Metodos:

	// Articulos:
	public boolean agregarArticulo(String nombre, String tipo, float precio) throws Exception {
		if (traerArticulo(nombre) != null) {
			throw new Exception("El articulo " + nombre + " ya está en la lista.");
		}
		int id = 1;
		if (!articulos.isEmpty()) {
			id = articulos.get(articulos.size() - 1).getIdArticulo() + 1;
		}

		return articulos.add(new Articulo(id, nombre, tipo, precio));
	}

	public Articulo traerArticulo(String nombre) {
		int i = 0;
		Articulo a = null;
		while (i < articulos.size() && a == null) {
			Articulo aux = articulos.get(i);
			if (aux.getNombre().equals(nombre)) {
				a = aux;
			}
			i++;
		}
		return a;
	}

	public Articulo traerArticulo(int idArticulo) {
		int i = 0;
		Articulo a = null;
		while (i < articulos.size() && a == null) {
			Articulo aux = articulos.get(i);
			if (idArticulo == aux.getIdArticulo()) {
				a = aux;
			}
			i++;
		}
		return a;
	}

	public boolean modificarArticulo(int idArticulo, String nombre, String tipo, float precio) throws Exception {
		Articulo a = traerArticulo(idArticulo);
		if (a == null) {
			throw new Exception("El articulo con id " + idArticulo + " no existe.");
		}
		a.setNombre(nombre);
		a.setTipo(tipo);
		a.setPrecio(precio);

		return true;
	}

	public boolean eliminarArticulo(int idArticulo) throws Exception {
		Articulo a = traerArticulo(idArticulo);
		if (a == null) {
			throw new Exception("El producto con id " + idArticulo + " no existe.");
		}
		if (existeArticuloEnCompra(a)) {
			throw new Exception("El producto " + idArticulo + "no se puede eliminar porque está en un carrito.");
		}

		return articulos.remove(a);
	}

	public boolean existeArticuloEnCompra(Articulo articulo) {
		boolean existe = false;
		int i = 0;
		while (i < compras.size() && !existe) {
			if (compras.get(i).existeArticuloEnDetalle(articulo)) {
				existe = true;
			}
			i++;
		}

		return existe;
	}

	// Clientes:
	public boolean agregarCliente(String nombre, String apellido, long dni) throws Exception {
		if (traerCliente(dni) != null) {
			throw new Exception("Error. El cliente con DNI " + dni + " ya fue agregado.");
		}

		int id = 1;
		if (!clientes.isEmpty()) {
			id = clientes.get(clientes.size() - 1).getIdCliente() + 1;
		}

		return clientes.add(new Cliente(id, nombre, apellido, dni));
	}

	public Cliente traerCliente(long dni) {
		int i = 0;
		Cliente c = null;
		while (i < clientes.size() && c == null) {
			if (clientes.get(i).getDni() == dni) {
				c = clientes.get(i);
			}
			i++;
		}
		return c;
	}

	public boolean eliminarCliente(long dni) throws Exception {
		Cliente c = traerCliente(dni);
		if (c == null) {
			throw new Exception("El dni " + dni + " no está registrado.");
		}
		return clientes.remove(c);
	}

	// Compras:
	public boolean agregarCompra(LocalDate fecha, Cliente cliente) {
		int id = 1;
		if (!compras.isEmpty()) {
			id = compras.get(compras.size() - 1).getIdCompra() + 1;
		}
		return compras.add(new Compra(id, fecha, cliente));
	}

	public Compra traerCompra(int idCompra) {
		Compra c = null;
		int i = 0;
		while (i < compras.size() && c == null) {
			if (idCompra == compras.get(i).getIdCompra()) {
				c = compras.get(i);
			}
			i++;
		}
		return c;
	}

	// Puntos del Trabajo:
	public List<Compra> traerComprasSegunFecha(LocalDate fecha) {
		List<Compra> lista = new ArrayList<Compra>();
		for (Compra c : compras) {
			if (c.getFecha().equals(fecha)) {
				lista.add(c);
			}
		}
		return lista;
	}

	public List<Compra> traerComprasAntesDeFecha(LocalDate fecha) {
		List<Compra> lista = new ArrayList<Compra>();
		for (Compra c : compras) {
			if (c.getFecha().isBefore(fecha)) {
				lista.add(c);
			}
		}
		return lista;
	}

	public List<Compra> traerComprasDespuesDeFecha(LocalDate fecha) {
		List<Compra> lista = new ArrayList<Compra>();
		for (Compra c : compras) {
			if (c.getFecha().isAfter(fecha)) {
				lista.add(c);
			}
		}
		return lista;
	}

	public List<Compra> traerComprasEntreFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
		List<Compra> lista = new ArrayList<Compra>();
		for (Compra c : compras) {
			if (!(c.getFecha().isBefore(fechaDesde) || c.getFecha().isAfter(fechaHasta))) {
				lista.add(c);
			}
		}
		return lista;
	}

	public Articulo traerArticuloMasVendido() {
		Articulo masVendido = null;
		int cantidadMaximaVendida = 0;
		for (Compra c : compras) {
			for (DetalleCompra dc : c.getDetalles()) {
				if (dc.getArticulo().getCantidadVendida() > cantidadMaximaVendida) {
					cantidadMaximaVendida = dc.getArticulo().getCantidadVendida();
					masVendido = dc.getArticulo();
				}
			}
		}

		// System.out.println("El articulo mas vendido fue: " + masVendido.toString()
		// +"\n\tcon una cantidad de: " + cantidadMaximaVendida + " unidades");
		return masVendido;
	}

	public Articulo traerArticuloMenosVendido() {
		Articulo menosVendido = null;
		int cantidadMinimaVendida = 0;
		for (Compra c : compras) {
			for (DetalleCompra dc : c.getDetalles()) {
				if (cantidadMinimaVendida == 0) {
					cantidadMinimaVendida = dc.getCantidad();
				}
				if (dc.getArticulo().getCantidadVendida() < cantidadMinimaVendida) {
					cantidadMinimaVendida = dc.getArticulo().getCantidadVendida();
					menosVendido = dc.getArticulo();
				}
			}
		}
		// System.out.println("El articulo menos vendido fue: " +
		// menosVendido.toString() +"\n\tcon una cantidad de: " + cantidadMinimaVendida
		// + " unidades");
		return menosVendido;
	}

}
