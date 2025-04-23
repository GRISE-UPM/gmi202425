package es.upm.grise.gmi2025.modelo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPedidos extends JpaRepository<Pedido, Long> {

}
