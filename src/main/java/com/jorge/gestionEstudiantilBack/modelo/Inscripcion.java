package com.jorge.gestionEstudiantilBack.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Genera getters, setters, toString, equals y hashCode
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

    // Constructor vacío
    public Inscripcion() {}

    // Constructor con parámetros
    public Inscripcion(Long id, Estudiante estudiante, Curso curso, String estadoInscripcion) {
        this.id = id;
        this.estudiante = estudiante;
        this.curso = curso;
        this.estadoInscripcion = estadoInscripcion;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(String estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }
}
