package com.jorge.gestionEstudiantilBack.servicio;

import com.jorge.gestionEstudiantilBack.excepciones.InscripcionNoValidaException;
import com.jorge.gestionEstudiantilBack.modelo.Inscripcion;
import com.jorge.gestionEstudiantilBack.repositorio.InscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionServicio
{

    private final InscripcionRepositorio inscripcionRepositorio;

    @Autowired
    public InscripcionServicio(InscripcionRepositorio inscripcionRepositorio)
    {
        this.inscripcionRepositorio = inscripcionRepositorio;
    }

    // Obtener todas las inscripciones
    public List<Inscripcion> obtenerTodasLasInscripciones()
    {
        return inscripcionRepositorio.findAll();
    }

    // Agregar una nueva inscripción
    public Inscripcion agregarInscripcion(Inscripcion inscripcion)
    {
        // Validación de inscripción
        validarInscripcion(inscripcion);
        return inscripcionRepositorio.save(inscripcion);
    }

    // Eliminar una inscripción por ID
    public void eliminarInscripcion(Long id)
    {
        inscripcionRepositorio.deleteById(id);
    }

    // Obtener una inscripción por ID
    public Inscripcion obtenerInscripcionPorId(Long id)
    {
        return inscripcionRepositorio.findById(id)
                .orElseThrow(() -> new InscripcionNoValidaException("Inscripción no encontrada con id: " + id));
    }

    // Método para validar la inscripción
    private void validarInscripcion(Inscripcion inscripcion)
    {
        if (inscripcion.getEstudiante() == null || inscripcion.getCurso() == null)
        {
            throw new InscripcionNoValidaException("La inscripción debe tener un estudiante y un curso válidos.");
        }
        if (inscripcion.getEstadoInscripcion() == null || inscripcion.getEstadoInscripcion().isEmpty())
        {
            throw new InscripcionNoValidaException("El estado de la inscripción no puede ser nulo o vacío.");
        }
    }
}
