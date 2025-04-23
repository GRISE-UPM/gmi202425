package es.upm.grise.gmi2025.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LineaProducto {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long pedidoId;
	private Long productoId;
	private String nombre;
	private int unidades;
	private double precio;
	
	public LineaProducto() {}
	
	public LineaProducto(Long productoId, String nombre, int unidades, double precio) {
		this.productoId = productoId;
		this.nombre = nombre;
		this.unidades = unidades;
		this.precio = precio;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	@Override
	public String toString() {
		return "Linea de Producto {id=" + id + ", productoID=" + productoId + ", nombre=" + nombre + ", unidades =" + unidades + ", precio=" + precio + "}";
	}

}
