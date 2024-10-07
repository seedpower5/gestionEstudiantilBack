package com.jorge.gestionEstudiantilBack.servicio;

import com.jorge.gestionEstudiantilBack.excepciones.CursoNoEncontradoException;
import com.jorge.gestionEstudiantilBack.excepciones.EstudianteNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Estudiante;
import com.jorge.gestionEstudiantilBack.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServicio {
    private final EstudianteRepositorio estudianteRepositorio;

    @Autowired
    public EstudianteServicio(EstudianteRepositorio estudianteRepositorio) {
        this.estudianteRepositorio = estudianteRepositorio;
    }

    // Obtener todos los estudiantes
    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepositorio.findAll();
    }

    // Agregar nuevo estudiante
    public Estudiante agregarEstudiante(Estudiante estudiante) {
        return estudianteRepositorio.save(estudiante);
    }

    // Eliminar estudiante por ID
    public void eliminarEstudiante(Long id) {
        if (!estudianteRepositorio.existsById(id)) {
            throw new EstudianteNoEncontradoException("Estudiante no encontrado con ID: " + id);
        }
        estudianteRepositorio.deleteById(id);
    }

    // Obtener estudiante por ID
    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteRepositorio.findById(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException("Estudiante no encontrado con ID: " + id));
    }

    // **Nuevo método para obtener estudiantes por ID de curso**
    public List<Estudiante> obtenerEstudiantesPorCurso(Long cursoId) {
        return estudianteRepositorio.findByCursoId(cursoId); // Este método debe existir en el repositorio
    }
}
