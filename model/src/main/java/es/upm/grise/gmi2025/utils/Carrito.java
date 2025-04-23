package es.upm.grise.gmi2025.utils;

import java.util.ArrayList;
import java.util.List;

import es.upm.grise.gmi2025.modelo.Producto;

public class Carrito {
	
	List<Producto> productos = new ArrayList<Producto>();

	public List<Producto> getProductos() {
		return productos;
	}
	
	public void addProducto(Producto producto) {
		productos.add(producto);
	}

	public void vaciar() {
		productos = new ArrayList<Producto>();
		
	}

	public boolean esVacio() {
		return productos.isEmpty();
	}

	public double getTotal() {
		
		double total = 0;
		
		for(Producto producto : productos)
			total += producto.getPrecio();
		
		return total;
	}

}
