package com.alura.literalura.model;
//Mapea la información de cada libro.

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Double numeroDeDescargas
) {}


// Records:Son clases inmutables y concisas que nos permiten definir de manera rápida y elegante los datos que vamos a mapear.
// Son perfectos para DTOs (Data Transfer Objects).
// @JsonAlias: Esta anotación permite mapear un campo del JSON a un nombre de variable diferente en Java
// List<DatosAutor>: El JSON tiene una lista de autores. Nuestro record debe reflejar eso.
// En un paso posterior, siguiendo la especificación del challenge, tomaremos solo el primer autor de esta lista.