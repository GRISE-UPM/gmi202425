package es.upm.grise.gmi2025.back.exceptions;

public class ProductosNoEncontradosException extends Exception {
	
	public ProductosNoEncontradosException(String cadena) {
		
		super("No se encuentra un producto similar a '" + cadena + "'");
	  }
}
