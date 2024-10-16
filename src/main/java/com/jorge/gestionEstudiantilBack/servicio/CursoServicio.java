package com.jorge.gestionEstudiantilBack.servicio;

import com.jorge.gestionEstudiantilBack.excepciones.CursoNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Curso;
import com.jorge.gestionEstudiantilBack.repositorio.CursoRepositorio; // Asegúrate de tener el repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServicio {

    private final CursoRepositorio cursoRepositorio;

    @Autowired
    public CursoServicio(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    // Obtener todos los cursos
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepositorio.findAll();
    }

    // Agregar un nuevo curso
    public Curso agregarCurso(Curso curso) {
        return cursoRepositorio.save(curso);
    }

    // Eliminar un curso por ID
    public void eliminarCurso(Long id) {
        if (!cursoRepositorio.existsById(id)) {
            throw new CursoNoEncontradoException("Curso no encontrado con id: " + id);
        }
        cursoRepositorio.deleteById(id);
    }

    // Obtener un curso por ID
    public Curso obtenerCursoPorId(Long id) {
        return cursoRepositorio.findById(id)
                .orElseThrow(() -> new CursoNoEncontradoException("Curso no encontrado con id: " + id));
    }

    // Verificar si un curso existe por ID
    public boolean existsById(Long id) {
        return cursoRepositorio.existsById(id);
    }
}
