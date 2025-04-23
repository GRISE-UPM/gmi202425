package es.upm.grise.gmi2025.back.exceptions;

public class ProductoNoEncontradoException extends Exception {
	
	public ProductoNoEncontradoException(Long id) {
		
		super("No se encuentra el producto " + id);
	}
}
