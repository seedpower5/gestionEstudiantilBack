package com.jorge.gestionEstudiantilBack.controlador;

import com.jorge.gestionEstudiantilBack.excepciones.EstudianteNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Estudiante;
import com.jorge.gestionEstudiantilBack.servicio.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/estudiantes") // Mapeo base para las solicitudes relacionadas con estudiantes
public class EstudianteControlador {

    private final EstudianteServicio estudianteServicio;

    @Autowired
    public EstudianteControlador(EstudianteServicio estudianteServicio)
    {
        this.estudianteServicio = estudianteServicio;
    }

    // Método para listar todos los estudiantes
    @GetMapping // Obtener todos los estudiantes
    public List<Estudiante> listarEstudiantes()
    {
        return estudianteServicio.obtenerTodosLosEstudiantes();
    }

    // Método para crear un nuevo estudiante
    @PostMapping // Crear un nuevo estudiante
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante)
    {
        Estudiante nuevoEstudiante = estudianteServicio.agregarEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante); // Devuelve 201 Created
    }

    // Método para obtener un estudiante por ID
    @GetMapping("/{id}") // Obtener estudiante por ID
    public ResponseEntity<Estudiante> obtenerEstudiante(@PathVariable Long id)
    {
        Estudiante estudiante = estudianteServicio.obtenerEstudiantePorId(id);
        return ResponseEntity.ok(estudiante); // Devuelve 200 OK
    }

    // Método para eliminar un estudiante por ID
    @DeleteMapping("/{id}") // Eliminar estudiante por ID
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id)
    {
        estudianteServicio.eliminarEstudiante(id);
        return ResponseEntity.noContent().build(); // Respuesta sin contenido (204 No Content)
    }

    // Manejo de excepciones para EstudianteNoEncontradoException
    @ExceptionHandler(EstudianteNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> manejarEstudianteNoEncontradoException(EstudianteNoEncontradoException e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
