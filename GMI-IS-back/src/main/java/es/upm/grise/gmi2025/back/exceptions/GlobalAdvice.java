package es.upm.grise.gmi2025.back.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalAdvice.class);

	@ExceptionHandler(ProductoNoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String manejador(ProductoNoEncontradoException e) {

		log.error(e.getMessage());
		// Se podría crear un objeto JSON para devolver una respuesta ampliada
		return "";
	}

	@ExceptionHandler(ProductosNoEncontradosException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String manejador(ProductosNoEncontradosException e) {

		log.error(e.getMessage());
		// Se podría crear un objeto JSON para devolver una respuesta ampliada
		return "";
	}

	@ExceptionHandler(CarritoVacioException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	String manejador(CarritoVacioException e) {

		log.error(e.getMessage());
		// Se podría crear un objeto JSON para devolver una respuesta ampliada
		return "";
	}

}
