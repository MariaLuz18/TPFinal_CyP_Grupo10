package tests;

import java.time.LocalDate;

import module.*;

public class Tests {

	public static void main(String[] args) {

		Empresa s1 = new Empresa();
		try {
			s1.agregarArticulo("Cama Queen Size", "mueble", 220);
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
		}
		try {
			s1.agregarArticulo("Frutilla", "fruta", 800);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		try {
			s1.agregarArticulo("Cubo 3x3", "cuboRubik", 679.99f);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		try {
			s1.agregarCliente("Juan", "Vazquez", 34123789);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			s1.agregarCliente("Mariana", "García", 20567234);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// Compra 1
		s1.agregarCompra(LocalDate.of(2023, 06, 12), s1.traerCliente(20567234)); // Compra de Mariana García

		s1.traerCompra(1).agregarDetalleCompra(s1.traerArticulo(1), 4); // 4 camas queen size
		s1.traerCompra(1).agregarDetalleCompra(s1.traerArticulo("Frutilla"), 3); // 3 frutilla

		// Compra 2
		s1.agregarCompra(LocalDate.of(2023, 06, 14), s1.traerCliente(34123789)); // Compra de Juan Vazquez

		s1.traerCompra(2).agregarDetalleCompra(s1.traerArticulo(3), 2); // 2 cubo 3x3
		s1.traerCompra(2).agregarDetalleCompra(s1.traerArticulo("Cama Queen Size"), 1); // 1 cama queen size

//		System.out.println(s1 + "\n");

//		Articulo masVendido = s1.traerArticuloMasVendido();
//		System.out.println("El articulo mas vendido fue: " + masVendido.toString());
//		
//		Articulo menosVendido = s1.traerArticuloMenosVendido();
//		System.out.println("El articulo menos vendido fue: " + menosVendido.toString());

		System.out.println("Compras de la fecha 12/6/2023: \n" + s1.traerComprasSegunFecha(LocalDate.of(2023, 06, 12)));
		System.out.println(
				"\nCompras antes de la fecha 13/6/2023: \n" + s1.traerComprasAntesDeFecha(LocalDate.of(2023, 06, 13)));
		System.out.println("\nCompras despues de la fecha 13/6/2023: \n"
				+ s1.traerComprasDespuesDeFecha(LocalDate.of(2023, 06, 13)));
		System.out.println("\nCompras entre 12/6/2023 y 14/6/2023: \n"
				+ s1.traerComprasEntreFechas(LocalDate.of(2023, 06, 12), LocalDate.of(2023, 06, 14)));
	}
}
