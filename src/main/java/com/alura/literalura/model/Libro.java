package com.alura.literalura.model;
// Clases que representará la tabla en la base de datos. Estas son entidades JPA.

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double numeroDeDescargas;

    @ManyToOne
    @JoinColumn(name = "autor_id") // Esta anotación es clave para definir la columna de la llave foránea
    private Autor autor;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma().get(0); // Se toma solo el primer idioma
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Double getNumeroDeDescargas() { return numeroDeDescargas; }
    public Autor getAutor() { return autor; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setNumeroDeDescargas(Double numeroDeDescargas) { this.numeroDeDescargas = numeroDeDescargas; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas +
                ", autor=" + (autor != null ? autor.getNombre() : "N/A") +
                '}';
    }
}

// @Column(unique = true): Esta anotación es clave para el requisito de evitar duplicados.
// Le dice a la base de datos que el campo titulo debe ser único, lo que evita que se inserte un libro con el mismo título dos veces.
// Si intentas insertar un libro con un título que ya existe, la base de datos lanzará una excepción que podemos capturar.

//@ManyToOne: Define la relación de muchos a uno. Muchos Libros pueden ser escritos por un solo Autor.

//datosLibro.idioma().get(0): Como se especificó, tomamos solo el primer idioma de la lista.