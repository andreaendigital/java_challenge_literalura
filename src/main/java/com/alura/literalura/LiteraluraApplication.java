package com.alura.literalura;


import com.alura.literalura.principal.Principal;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Creamos una instancia de la clase Principal, pasándole los repositorios
		// que Spring inyectó automáticamente a esta clase (LiteraluraApplication).
		Principal principal = new Principal(libroRepository, autorRepository);

		// Llamamos al metodo que muestra el menú e interactúa con el usuario.
		principal.muestraElMenu();
	}
}
// implements CommandLineRunner: Al implementar esta interfaz, se le indica a Spring que esta clase debe ejecutar
// su metodo run después de que la aplicación haya iniciado.

// @Autowired: Esta anotación le pide a Spring que inyecte las dependencias de LibroRepository y AutorRepository.
// Spring se encarga de crear instancias de estas interfaces y ponerlas a disposición.

// Principal principal = new Principal(libroRepository, autorRepository): Creamos una instancia de nuestra clase Principal.
//  Le pasamos los repositorios que Spring proporcionó a través de la inyección de dependencias.
//  De esta manera, la clase Principal tiene acceso a la base de datos sin tener que gestionar la creación de los repositorios por sí misma.
//
// principal.muestraElMenu(): Finalmente, llamamos al metodo muestraElMenu() para iniciar la interacción con el usuario.