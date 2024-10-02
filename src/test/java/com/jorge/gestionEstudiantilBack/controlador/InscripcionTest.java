package com.jorge.gestionEstudiantilBack.controlador;


import com.jorge.gestionEstudiantilBack.excepciones.InscripcionNoValidaException;
import com.jorge.gestionEstudiantilBack.modelo.Curso;
import com.jorge.gestionEstudiantilBack.modelo.Estudiante;
import com.jorge.gestionEstudiantilBack.modelo.Inscripcion;
import com.jorge.gestionEstudiantilBack.repositorio.InscripcionRepositorio;
import com.jorge.gestionEstudiantilBack.servicio.InscripcionServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InscripcionTest
{

    @InjectMocks
    private InscripcionServicio inscripcionServicio;

    @Mock
    private InscripcionRepositorio inscripcionRepositorio;

    private Inscripcion inscripcion;
    private Estudiante estudiante;
    private Curso curso;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);

        // Inicializa un estudiante y un curso
        estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        estudiante.setApellido("Pérez");
        estudiante.setDni("12345678A");
        estudiante.setTelefono(123456789);
        estudiante.setNotaMedia(8.5f);

        curso = new Curso();
        curso.setId(1L);
        curso.setNombreCurso("Matemáticas");
        curso.setDuracion(60);
        curso.setProfesor("Profesor A");
        curso.setDescripcion("Curso de Matemáticas");

        // Inicializa la inscripción
        inscripcion = new Inscripcion();
        inscripcion.setId(1L);
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setEstadoInscripcion("Activo");
    }

    @Test
    void testAgregarInscripcion()
    {
        when(inscripcionRepositorio.save(any(Inscripcion.class))).thenReturn(inscripcion);

        Inscripcion nuevaInscripcion = inscripcionServicio.agregarInscripcion(inscripcion);
        assertNotNull(nuevaInscripcion);
        assertEquals("Activo", nuevaInscripcion.getEstadoInscripcion());
        verify(inscripcionRepositorio, times(1)).save(inscripcion);
    }

    @Test
    void testListarInscripciones()
    {
        List<Inscripcion> listaInscripciones = new ArrayList<>();
        listaInscripciones.add(inscripcion);

        when(inscripcionRepositorio.findAll()).thenReturn(listaInscripciones);

        List<Inscripcion> inscripciones = inscripcionServicio.obtenerTodasLasInscripciones();
        assertFalse(inscripciones.isEmpty());
        assertEquals(1, inscripciones.size());
        assertEquals("Activo", inscripciones.get(0).getEstadoInscripcion());
        verify(inscripcionRepositorio, times(1)).findAll();
    }

    @Test
    void testEliminarInscripcionExistente()
    {
        when(inscripcionRepositorio.existsById(1L)).thenReturn(true);
        doNothing().when(inscripcionRepositorio).deleteById(1L);

        inscripcionServicio.eliminarInscripcion(1L);
        verify(inscripcionRepositorio, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarInscripcionNoExistente()
    {
        when(inscripcionRepositorio.existsById(2L)).thenReturn(false);

        Exception exception = assertThrows(InscripcionNoValidaException.class, () -> {
            inscripcionServicio.eliminarInscripcion(2L);
        });

        assertEquals("Inscripción no válida con id: 2", exception.getMessage());
    }

    @Test
    void testObtenerInscripcionPorIdExistente()
    {
        when(inscripcionRepositorio.findById(1L)).thenReturn(Optional.of(inscripcion));

        Inscripcion inscripcionEncontrada = inscripcionServicio.obtenerInscripcionPorId(1L);
        assertNotNull(inscripcionEncontrada);
        assertEquals("Activo", inscripcionEncontrada.getEstadoInscripcion());
        verify(inscripcionRepositorio, times(1)).findById(1L);
    }

    @Test
    void testObtenerInscripcionPorIdNoExistente()
    {
        when(inscripcionRepositorio.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(InscripcionNoValidaException.class, () -> {
            inscripcionServicio.obtenerInscripcionPorId(2L);
        });

        assertEquals("Inscripción no válida con id: 2", exception.getMessage());
    }
}
