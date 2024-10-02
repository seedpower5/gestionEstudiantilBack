package com.jorge.gestionEstudiantilBack.controlador;

import com.jorge.gestionEstudiantilBack.excepciones.InscripcionNoValidaException;
import com.jorge.gestionEstudiantilBack.modelo.Inscripcion;
import com.jorge.gestionEstudiantilBack.servicio.InscripcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/inscripciones") // Mapeo base para las solicitudes relacionadas con inscripciones
public class InscripcionControlador
{

    private final InscripcionServicio inscripcionServicio;

    @Autowired
    public InscripcionControlador(InscripcionServicio inscripcionServicio)
    {
        this.inscripcionServicio = inscripcionServicio;
    }

    // Método para listar todas las inscripciones
    @GetMapping // Obtener todas las inscripciones
    public List<Inscripcion> listarInscripciones()
    {
        return inscripcionServicio.obtenerTodasLasInscripciones();
    }

    // Método para crear una nueva inscripción
    @PostMapping // Crear una nueva inscripción
    public ResponseEntity<Inscripcion> crearInscripcion(@RequestBody Inscripcion inscripcion)
    {
        Inscripcion nuevaInscripcion = inscripcionServicio.agregarInscripcion(inscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInscripcion); // Devuelve 201 Created
    }

    // Método para eliminar una inscripción por ID
    @DeleteMapping("/{id}") // Eliminar inscripción por ID
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id)
    {
        inscripcionServicio.eliminarInscripcion(id);
        return ResponseEntity.noContent().build(); // Respuesta sin contenido (204 No Content)
    }

    // Método para obtener una inscripción por ID
    @GetMapping("/{id}") // Obtener inscripción por ID
    public ResponseEntity<Inscripcion> obtenerInscripcion(@PathVariable Long id)
    {
        Inscripcion inscripcion = inscripcionServicio.obtenerInscripcionPorId(id);
        return ResponseEntity.ok(inscripcion); // Devuelve 200 OK
    }

    // Manejo de excepciones para InscripcionNoValidaException
    @ExceptionHandler(InscripcionNoValidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> manejarInscripcionNoValidaException(InscripcionNoValidaException e)
    {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
