package com.jorge.gestionEstudiantilBack.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Genera getters, setters, toString, equals y hashCode
public class Estudiante
{
    // Atributos de la clase
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
    private int telefono;

    @Column(name = "notaMedia", nullable = false)
    private float notaMedia;

    // Esta es la parte de la tabla compuesta
    @ManyToOne // Relación Many-to-One con Curso
    @JoinColumn(name = "curso_id", nullable = false) // Foreign Key
    private Curso curso;

    // Constructor vacío
    public Estudiante() {}

    // Constructor
    public Estudiante(Long id, String nombre, String apellido, String dni, int telefono, float notaMedia, Curso curso) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.notaMedia = notaMedia;
        this.curso = curso; // Asignación del curso
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public float getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
