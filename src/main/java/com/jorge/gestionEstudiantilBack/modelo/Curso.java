package com.jorge.gestionEstudiantilBack.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data // Genera getters, setters, toString, equals y hashCode
public class Curso
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCurso", length = 60, nullable = false)
    private String nombreCurso;

    @Column(name = "duracion", nullable = false)
    private float duracion;

    @Column(name = "profesor", length = 60, nullable = false)
    private String profesor;

    @Column(name = "descripcion", length = 60, nullable = true)
    private String descripcion;

    @Column(name = "fechaInicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fechaFinal", nullable = false)
    private LocalDate fechaFinal;

    // Relación One-to-Many con Estudiante
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Controla la serialización de los estudiantes
    private List<Estudiante> estudiantes;

    // Constructor vacío
    public Curso() {}

    // Constructor con parámetros
    public Curso(Long id, String nombreCurso, float duracion, String profesor, String descripcion, LocalDate fechaInicio, LocalDate fechaFinal)
    {
        this.id = id;
        this.nombreCurso = nombreCurso;
        this.duracion = duracion;
        this.profesor = profesor;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    // Getters y Setters adicionales (omitidos por Lombok)
}
