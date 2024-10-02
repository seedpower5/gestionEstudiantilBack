package com.jorge.gestionEstudiantilBack.controlador;

import com.jorge.gestionEstudiantilBack.excepciones.CursoNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Curso;
import com.jorge.gestionEstudiantilBack.servicio.CursoServicio; // Asegúrate de tener esta clase de servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/cursos") // Mapeo base para las solicitudes relacionadas con cursos
public class CursoControlador
{

    private final CursoServicio cursoServicio;

    @Autowired
    public CursoControlador(CursoServicio cursoServicio)
    {
        this.cursoServicio = cursoServicio;
    }

    // Método para listar todos los cursos
    @GetMapping // Obtener todos los cursos
    public List<Curso> listarCursos()
    {
        return cursoServicio.obtenerTodosLosCursos();
    }

    // Método para crear un nuevo curso
    @PostMapping // Crear un nuevo curso
    public ResponseEntity<Curso> crearCurso(@RequestBody Curso curso)
    {
        Curso nuevoCurso = cursoServicio.agregarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso); // Devuelve 201 Created
    }

    // Método para eliminar un curso por ID
    @DeleteMapping("/{id}") // Eliminar curso por ID
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id)
    {
        cursoServicio.eliminarCurso(id);
        return ResponseEntity.noContent().build(); // Respuesta sin contenido (204 No Content)
    }

    // Manejador de excepciones para CursoNoEncontradoException
    @ExceptionHandler(CursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> manejarCursoNoEncontrado(CursoNoEncontradoException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
