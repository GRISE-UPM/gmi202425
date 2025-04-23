package es.upm.grise.gmi2025.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Producto {

	@Id
	@GeneratedValue 
	private Long id;
	private String nombre;
	@Column(columnDefinition = "TEXT")
	private String imagen;
	private double precio;
	private int unidades;
	
	public Producto() {}

	public Producto(String nombre, String imagen, double precio, int unidades) {
		this.nombre = nombre;
		this.imagen = imagen;
		this.precio = precio;
		this.unidades = unidades;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getImagen() { 
		return imagen; 
	}

	public void setImagen(String imagen) { 
		this.imagen = imagen; 
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
		return "Producto [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
