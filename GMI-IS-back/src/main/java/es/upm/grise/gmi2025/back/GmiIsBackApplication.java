package es.upm.grise.gmi2025.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import es.upm.grise.gmi2025.modelo.Cliente;
import es.upm.grise.gmi2025.modelo.Producto;
import es.upm.grise.gmi2025.modelo.RepositorioClientes;
import es.upm.grise.gmi2025.modelo.RepositorioProductos;

@SpringBootApplication
@EnableJpaRepositories("es.upm.grise.gmi2025.*")
@ComponentScan(basePackages = { "es.upm.grise.gmi2025.*" })
@EntityScan("es.upm.grise.gmi2025.*") 
public class GmiIsBackApplication {
	
	private static final Logger log = LoggerFactory.getLogger(GmiIsBackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GmiIsBackApplication.class, args);
	}
	
		
	@Bean 
	CommandLineRunner inicializaClientes(RepositorioClientes repositorioCliente) {

		return args -> { log.warn("Cargando " + repositorioCliente.save(new Cliente("Oscar Dieste"))); };
	}


	@Bean
	CommandLineRunner inicializaProductos(RepositorioProductos repositorioProducto) {

		return args -> {
			// Las imágenes son JPG codificadas en base64
			log.warn("Cargando " + repositorioProducto.save(
					new Producto("PK12 LAPIZ LYRECO C/GOMA N.2 HB", 
							"data:image/jpeg;base64,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",
							0.20,
							100)));
			log.warn("Cargando " + repositorioProducto.save(
					new Producto("BOLIGRAFO BIC CRISTAL ESCRITURA NORMAL (AZUL)", 
							"data:image/jpeg;base64,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",
							0.25,
							100)));
		};
	}
	
	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setMaxPayloadLength(64000);
	    return loggingFilter;
	}

}
