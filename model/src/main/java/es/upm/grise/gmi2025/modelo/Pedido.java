package es.upm.grise.gmi2025.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue 
	private Long id;
	private Long clienteId;
	@OneToMany(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "pedidoId")
	private List <LineaProducto> lineaProductos = new ArrayList<LineaProducto>();
	
	public Pedido() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public List<LineaProducto> getLineaProductos() {
		return lineaProductos;
	}

	public void setLineaProductos(List<LineaProducto> lineaProductos) {
		this.lineaProductos = lineaProductos;
	}
		
	public void addLinea(LineaProducto lineaProductos) {
		this.lineaProductos.add(lineaProductos);
	}
	
	public String toString() {
		
		String salida = "Pedido:{lineaProductos:[";
		boolean anadirComa = false;
		
		for(LineaProducto linea : lineaProductos) {
			
			salida += (anadirComa?",":"") + linea.toString();
			anadirComa = true;
		}
		
		return salida+"]}";
	}
	
}
