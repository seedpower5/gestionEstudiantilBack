package com.jorge.gestionEstudiantilBack.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Genera getters, setters, toString, equals y hashCode
public class Estudiante
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 60, nullable = false)
    private String apellido;

    @Column(name = "dni", length = 60, nullable = false)
    private String dni;

    @Column(name = "telefono", length = 60, nullable = false)
    private String telefono;

    @Column(name = "notaMedia", nullable = false)
    private float notaMedia;

    // Relación Many-to-One con Curso
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false) // Foreign Key
    @JsonBackReference // Evita la serialización recursiva del curso
    private Curso curso;

    // Constructor vacío
    public Estudiante() {}

    // Constructor con parámetros
    public Estudiante(Long id, String nombre, String apellido, String dni, String telefono, float notaMedia, Curso curso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.notaMedia = notaMedia;
        this.curso = curso; // Asignación del curso
    }

    // Getters y Setters adicionales (omitidos por Lombok)
}
