package com.alura.literalura.principal;
// Aquí estará la lógica principal de la aplicación, el menú y la interacción con el usuario.

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private Scanner teclado = new Scanner(System.in);
    private ObjectMapper objectMapper = new ObjectMapper();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    ********************************************************
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un año determinado
                    5 - Listar libros por idioma
                    0 - Salir
                    ********************************************************
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var titulo = teclado.nextLine();

        // Verificamos si el libro ya existe en nuestra base de datos para evitar duplicados.
        Optional<Libro> libroExistente = libroRepository.findByTituloContainsIgnoreCase(titulo);
        if (libroExistente.isPresent()) {
            System.out.println("El libro '" + libroExistente.get().getTitulo() + "' ya se encuentra registrado.");
            return;
        }

        // Si no existe, lo buscamos en la API.
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        try {
            DatosResultados datosResultados = objectMapper.readValue(json, DatosResultados.class);

            if (datosResultados.getResults().isEmpty()) {
                System.out.println("Libro no encontrado en la API.");
            } else {
                DatosLibro datosLibro = datosResultados.getResults().get(0);

                // Creamos el objeto Libro
                Libro libro = new Libro(datosLibro);

                // Manejamos la persistencia del autor
                DatosAutor datosAutor = datosLibro.autor().get(0);
                Autor autor = new Autor(datosAutor);

                // Buscamos si el autor ya existe en la base de datos.
                Optional<Autor> autorExistente = autorRepository.findByNombreContainsIgnoreCase(autor.getNombre());
                if (autorExistente.isPresent()) {
                    autor = autorExistente.get();
                } else {
                    // Si el autor no existe, lo guardamos.
                    autorRepository.save(autor);
                }

                // Asignamos el autor al libro
                libro.setAutor(autor);

                // Guardamos el libro en la base de datos
                libroRepository.save(libro);
                System.out.println("Libro guardado exitosamente: " + libro);
            }
        } catch (JsonProcessingException e) {
            System.out.println("Error al procesar el JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error al guardar el libro: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingresa el año para buscar autores vivos:");
        try {
            var anio = teclado.nextInt();
            teclado.nextLine();

            List<Autor> autoresVivos = autorRepository.buscarAutoresVivosEnAnio(anio);
            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio);
            } else {
                autoresVivos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Por favor, ingresa un número de año válido.");
            teclado.nextLine(); // Limpiar el buffer
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingresa el idioma para buscar los libros (ej: es, en, fr, pt):");
        var idioma = teclado.nextLine();

        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma.toLowerCase());

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma '" + idioma + "'");
        } else {
            librosPorIdioma.forEach(System.out::println);
        }
    }
}


// @Component: Marca esta clase para que Spring la gestione como un "bean", lo que permite que Spring
// le inyecte dependencias (como LibroRepository).

// Inyección de LibroRepository: Al pasar LibroRepository en el constructor, Spring se encarga de crear una instancia y dárnosla.
// Esto se llama inyección de dependencias y es una de las características más poderosas de Spring.

// ObjectMapper: Esta es la clase de Jackson que realiza el mapeo de JSON a objetos Java.
// Con objectMapper.readValue(json, DatosResultados.class), le decimos a Jackson que tome el json y lo convierta
// en un objeto de tipo DatosResultados.

// teclado.nextLine(): Es crucial después de teclado.nextInt() para consumir el salto de línea que queda en el buffer del Scanner.

// Decisión: Agregamos el AutorRepository al constructor. La clase Principal necesita acceder a la base de datos tanto
// para Libro como para Autor. La inyección de dependencias de Spring nos facilita esta tarea al proporcionar las instancias
// de los repositorios de forma automática.

// Antes de buscar en la API, verificamos si el libro ya está en la base de datos. Esto cumple con el requisito de "evitar duplicados".

// Manejar la persistencia del autor de manera condicional: Un libro no puede existir sin un autor. Sin embargo, varios libros
// pueden tener el mismo autor. Para evitar duplicar autores en la base de datos, primero verificamos si un autor con ese nombre
// ya existe. Si autorExistente.isPresent() es verdadero, reutilizamos esa instancia de autor para el nuevo libro. Si no,
// guardamos el nuevo autor en la base de datos y luego lo asociamos al libro. Esto garantiza que cada autor sea único en la tabla autores.