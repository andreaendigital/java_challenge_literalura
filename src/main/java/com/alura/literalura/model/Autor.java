package com.alura.literalura.model;

// Clases que representará la tabla en la base de datos. Estas son entidades JPA.
// Un autor puede tener muchos libros, pero un libro solo tiene un autor (según la simplificación del desafío).
// Esta es una relación de uno a muchos (OneToMany).



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;

    // Cambiamos el CascadeType
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getFechaDeNacimiento() { return fechaDeNacimiento; }
    public Integer getFechaDeFallecimiento() { return fechaDeFallecimiento; }
    public List<Libro> getLibros() { return libros; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFechaDeNacimiento(Integer fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }
    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) { this.fechaDeFallecimiento = fechaDeFallecimiento; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaDeFallecimiento=" + fechaDeFallecimiento +
                '}';
    }
}

// @Entity y @Table: Estas anotaciones marcan la clase como una entidad JPA y le dan un nombre a la tabla en la base de datos.
// @Id y @GeneratedValue: Indican que id es la clave primaria de la tabla y que su valor será generado automáticamente por la base de datos (IDENTITY).
// @OneToMany: Define la relación de uno a muchos. Un Autor puede tener muchos Libros.
// mappedBy = "autor": Le dice a JPA que la relación es bidireccional y que el campo autor en la clase Libro es el dueño de la relación.