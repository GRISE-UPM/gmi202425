package es.upm.grise.gmi2025.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoRestController {

	RepositorioProductos repositorioProductos;
	private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);

	public ProductoRestController(RepositorioProductos repositorioProductos) {
		this.repositorioProductos = repositorioProductos;
	}

	// Todos los productos
	@GetMapping("/obtenerProductos")
	ListaDeProductos obtenerProductos() {
		
		ListaDeProductos matches = new ListaDeProductos(repositorioProductos.findAll());
		
		log.warn("Se han solicitado todos los productos. Se ha devuelto: " + matches.getLista().toString());
		return matches;
	}
	
}
