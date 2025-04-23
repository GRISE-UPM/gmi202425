package es.upm.grise.gmi2025.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import es.upm.grise.gmi2025.modelo.Pedido;
import es.upm.grise.gmi2025.modelo.Producto;
import es.upm.grise.gmi2025.utils.Carrito;
import es.upm.grise.gmi2025.utils.ListaDeProductos;

@Controller
public class ComprarProductosController {
	
	Carrito carrito = new Carrito();
	private static final Logger log = LoggerFactory.getLogger(ComprarProductosController.class);
	Producto producto;


	@GetMapping("/visualizar")
	public String visualizarProductosCoincidentes(@RequestParam(name="cadena", required=true) String cadena, Model modelo, RestTemplate restTemplate) {


		ListaDeProductos listaProductos;

		listaProductos = restTemplate.getForObject("http://localhost:8080/productos/buscar/" + cadena, ListaDeProductos.class);
		modelo.addAttribute("productos", listaProductos.getLista());
		
		log.warn("Se han recuperado los productos coincidentes con '" + cadena + "': " + listaProductos.getLista().toString());
		
		return "visualizar";

	}
	
	@GetMapping("/detalle")
	public String visualizarProducto(@RequestParam(name="id", required=true) Long id, Model modelo, RestTemplate restTemplate) {

		producto = restTemplate.getForObject("http://localhost:8080/productos/obtener/" + id, Producto.class);
		modelo.addAttribute("producto", producto);
		
		log.warn("Se ha recuperado el producto " + id + ": " + producto.toString());

		return "detalle";

	}
	
	@GetMapping("/anadir")
	public String anadirProducto(@RequestParam(name="id", required=true) Long id, RestTemplate restTemplate) {
		
		producto = restTemplate.getForObject("http://localhost:8080/productos/obtener/" + id, Producto.class);

		carrito.addProducto(producto);
		
		log.warn("Se ha añadido el producto: " + id + " al carrito");
		log.warn("El carrito ahora contiene los productos: " + carrito.getProductos().toString());
		
		return "confirmar";

	}
	
	@GetMapping("/decision")
	public String continuarOComprar(@RequestParam(name="decision", required=true) String decision, Model modelo) {

		switch(decision) {
			case "Continuar":
				log.warn("El usuario sigue comprando");
				return "forward:buscar.html";
			case "Ver":
				log.warn("El usuario quiere ver el carrito");
				modelo.addAttribute("carrito", carrito);
				modelo.addAttribute("total", carrito.getTotal());
				return "carrito";
			default: 
				return "error";
		}
	}
	
	@GetMapping("/comprar")
	public String comprarProductosEnCarrito(@RequestParam(name="accion", required=true) String decision, RestTemplate restTemplate) throws JsonProcessingException {
		   	    
		Pedido pedido = restTemplate.postForObject("http://localhost:8080/comprar", carrito, Pedido.class);
		carrito.vaciar();
		
		// Esta decisión es puramente de diseño, y no está exenta de riesgos
		producto = null;
		
		log.warn("Se ha creado el pedido: " + pedido.toString());
		log.warn("Se ha vaciado el carrito");

		return "forward:buscar.html";
	}

}
