package es.upm.grise.gmi2025.back.restController;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.upm.grise.gmi2025.back.exceptions.CarritoVacioException;
import es.upm.grise.gmi2025.back.exceptions.ProductoNoEncontradoException;
import es.upm.grise.gmi2025.back.exceptions.ProductosNoEncontradosException;
import es.upm.grise.gmi2025.modelo.LineaProducto;
import es.upm.grise.gmi2025.modelo.Pedido;
import es.upm.grise.gmi2025.modelo.Producto;
import es.upm.grise.gmi2025.modelo.RepositorioPedidos;
import es.upm.grise.gmi2025.modelo.RepositorioProductos;
import es.upm.grise.gmi2025.utils.Carrito;
import es.upm.grise.gmi2025.utils.ListaDeProductos;


@RestController
public class ComprarProductosRestController {
	
	private final RepositorioProductos repositorioProductos;
	private final RepositorioPedidos repositorioPedidos;
	private static final Logger log = LoggerFactory.getLogger(ComprarProductosRestController.class);


	ComprarProductosRestController(RepositorioProductos repositorioProductos, RepositorioPedidos repositorioPedidos) {
		    this.repositorioProductos = repositorioProductos;
			this.repositorioPedidos = repositorioPedidos;
	}

	// Todos los productos
	@GetMapping("/productos/todos")
	ListaDeProductos ObtenerTodos() {
		
		ListaDeProductos matches = new ListaDeProductos(repositorioProductos.findAll());
		
		log.warn("Se han solicitado todos los productos. Se ha devuelto: " + matches.getLista().toString());
		return matches;
	}

	// Buscar productos
	@GetMapping("/productos/buscar/{cadena}")
	ListaDeProductos buscarProductos(@PathVariable String cadena) throws ProductosNoEncontradosException {

		ListaDeProductos matches = new ListaDeProductos();
		for(Producto p : repositorioProductos.findAll())
			if(p.getNombre().contains(cadena))
				matches.addToLista(p);
		
		log.warn("Se han solicitado los coincidentes con '" + cadena + "'. Se ha devuelto: " + matches.getLista().toString());

		if(matches.isEmpty())
			throw new ProductosNoEncontradosException(cadena);
				
		return matches;
	}
	
	// Buscar producto
	@GetMapping("/productos/obtener/{id}")
	Optional<Producto> obtenerProductos(@PathVariable Long id) throws ProductoNoEncontradoException {
		
		Optional<Producto> producto = repositorioProductos.findById(id);
		log.warn("Se ha solicitado el producto " + id + ": " + producto.toString());

		producto.orElseThrow(() -> new ProductoNoEncontradoException(id));

		return producto;
	}
	
	// Buscar producto
	@PostMapping("/comprar")
	@ResponseStatus(HttpStatus.CREATED)
	Pedido comprar(@RequestBody Carrito carrito) throws CarritoVacioException, ProductoNoEncontradoException {
		
		if(carrito.esVacio())
			throw new CarritoVacioException();
		
		Pedido pedido = new Pedido();
		for(Producto producto: carrito.getProductos()) {
			LineaProducto lineaProducto = new LineaProducto(producto.getId(), producto.getNombre(), 1, producto.getPrecio());
			pedido.addLinea(lineaProducto);
		}
		
		Pedido pedidoEnBD = repositorioPedidos.save(pedido);
		
		log.warn("Se ha creado el pedido: " + pedidoEnBD.toString());
		
		// Habría que devolver una notificacion de envio, pero no voy tan allá
		return pedidoEnBD;
	}
	
}
