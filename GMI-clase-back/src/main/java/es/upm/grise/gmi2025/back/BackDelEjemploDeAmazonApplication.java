package es.upm.grise.gmi2025.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackDelEjemploDeAmazonApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BackDelEjemploDeAmazonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackDelEjemploDeAmazonApplication.class, args);
	}

	@Bean
	CommandLineRunner inicializaProductos(RepositorioProductos repositorioProducto) {

		return args -> {
			log.warn("Cargando " + repositorioProducto.save(
					new Producto("PK12 LAPIZ LYRECO C/GOMA N.2 HB")));
			log.warn("Cargando " + repositorioProducto.save(
					new Producto("BOLIGRAFO BIC CRISTAL ESCRITURA NORMAL (AZUL)")));
		};
	}
	
	
	
	
}
