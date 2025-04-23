package es.upm.grise.gmi2025.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue 
	private Long id;
	private String nombre;
	@OneToMany
	@JoinColumn(name = "ClienteId")
	private List <Pedido> pedidos;
	
	public Cliente() {}

	public Cliente(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
