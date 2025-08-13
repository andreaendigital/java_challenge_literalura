package com.alura.literalura.repository;

//Los repositorios son interfaces que extienden JpaRepository y permiten interactuar con las entidades en la base de datos
// sin escribir código SQL. Spring Data JPA se encarga de implementar estos métodos por nosotros.

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    List<Libro> findByIdioma(String idioma);
}

// extends JpaRepository<Libro, Long>: Esto es lo que le da superpoderes a nuestra interfaz.
// Libro es la entidad con la que trabajaremos y Long es el tipo de dato de su clave primaria.
// Ahora, esta interfaz ya tiene métodos como save(), findAll(), findById(), etc.
//
// findByTituloContainsIgnoreCase(String titulo): Esta es una "derived query". Spring Data JPA es
// lo suficientemente inteligente para entender el nombre del metodo y construir la consulta SQL necesaria.
// Este metodo buscará un libro por su título, ignorando mayúsculas y minúsculas. El Optional es una buena práctica
// para manejar casos donde el libro no se encuentre.
//
// findByIdioma(String idioma): Otra derived query que permite buscar libros por idioma,
// cumpliendo con uno de los requisitos del challenge.