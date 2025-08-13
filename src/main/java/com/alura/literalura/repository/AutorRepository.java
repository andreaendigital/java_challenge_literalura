package com.alura.literalura.repository;

//Los repositorios son interfaces que extienden JpaRepository y permiten interactuar con las entidades en la base de datos
// sin escribir código SQL. Spring Data JPA se encarga de implementar estos métodos por nosotros.

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND a.fechaDeFallecimiento >= :anio")
    List<Autor> buscarAutoresVivosEnAnio(Integer anio);
}

// findByNombreContainsIgnoreCase(String nombre): Derived query para buscar un autor por su nombre.
//
// @Query: Aquí se usa una query JPQL (Java Persistence Query Language). JPQL es similar a SQL pero usa nombres de clases
// y propiedades de Java en lugar de nombres de tablas y columnas.
// La query SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :anio AND a.fechaDeFallecimiento >= :anio
// es la solución al requisito de encontrar autores vivos en un año. Le pasamos el año como parámetro (:anio).