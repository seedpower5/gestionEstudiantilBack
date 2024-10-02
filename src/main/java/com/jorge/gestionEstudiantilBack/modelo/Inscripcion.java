package com.jorge.gestionEstudiantilBack.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Inscripcion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "estadoInscripcion", length = 30, nullable = false)
    private String estadoInscripcion; // Puede ser "Activo", "Completado", "Retirado"

    // Constructor
    public Inscripcion()
    {

    }
    // Constructor con parámetros
    public Inscripcion(Long id, Estudiante estudiante, Curso curso, String estadoInscripcion)
    {
        this.id = id;
        this.estudiante = estudiante;
        this.curso = curso;
        this.estadoInscripcion = estadoInscripcion;
    }
}
