package es.upm.grise.gmi2025.utils;

import java.util.ArrayList;
import java.util.List;

import es.upm.grise.gmi2025.modelo.Producto;

public class ListaDeProductos {
	
	private List<Producto> listaDeProductos;
	
	public ListaDeProductos() {
		this.listaDeProductos = new ArrayList<Producto>();
	}
	
	public ListaDeProductos(List<Producto> listaDeProductos) {
		this.listaDeProductos = listaDeProductos;
	}

	public List<Producto> getLista() {
		return listaDeProductos;
	}

	public void addToLista(Producto producto) {
		this.listaDeProductos.add(producto);
	}

	public boolean isEmpty() {
		return listaDeProductos.isEmpty();
	}
	
	

}
