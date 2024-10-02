package com.jorge.gestionEstudiantilBack.controlador;

import com.jorge.gestionEstudiantilBack.excepciones.EstudianteNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Estudiante;
import com.jorge.gestionEstudiantilBack.servicio.EstudianteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EstudianteTest
{

    @InjectMocks
    private EstudianteControlador estudianteControlador;

    @Mock
    private EstudianteServicio estudianteServicio;

    private Estudiante estudiante;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Jorge");
        estudiante.setApellido("González");
        estudiante.setDni("12345678");
        estudiante.setTelefono(123456789);
        estudiante.setNotaMedia(9.5f);
    }

    @Test
    void listarEstudiantes_deberiaDevolverListaDeEstudiantes()
    {
        // Preparación
        List<Estudiante> estudiantes = Arrays.asList(estudiante);
        when(estudianteServicio.obtenerTodosLosEstudiantes()).thenReturn(estudiantes);

        // Ejecución
        List<Estudiante> resultado = estudianteControlador.listarEstudiantes();

        // Verificación
        assertEquals(1, resultado.size());
        assertEquals(estudiante.getNombre(), resultado.get(0).getNombre());
        verify(estudianteServicio, times(1)).obtenerTodosLosEstudiantes();
    }

    @Test
    void crearEstudiante_deberiaCrearNuevoEstudiante()
    {
        // Preparación
        when(estudianteServicio.agregarEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        // Ejecución
        ResponseEntity<Estudiante> response = estudianteControlador.crearEstudiante(estudiante);

        // Verificación
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
        verify(estudianteServicio, times(1)).agregarEstudiante(any(Estudiante.class));
    }

    @Test
    void obtenerEstudiante_deberiaDevolverEstudiantePorId()
    {
        // Preparación
        when(estudianteServicio.obtenerEstudiantePorId(1L)).thenReturn(estudiante);

        // Ejecución
        ResponseEntity<Estudiante> response = estudianteControlador.obtenerEstudiante(1L);

        // Verificación
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estudiante, response.getBody());
        verify(estudianteServicio, times(1)).obtenerEstudiantePorId(1L);
    }

    @Test
    void obtenerEstudiante_noEncontrado_deberiaLanzarExcepcion()
    {
        // Preparación
        when(estudianteServicio.obtenerEstudiantePorId(1L)).thenThrow(new EstudianteNoEncontradoException("Estudiante no encontrado"));

        // Ejecución y verificación
        Exception exception = assertThrows(EstudianteNoEncontradoException.class, () -> {
            estudianteControlador.obtenerEstudiante(1L);
        });
        assertEquals("Estudiante no encontrado", exception.getMessage());
    }

    @Test
    void eliminarEstudiante_deberiaEliminarEstudiantePorId()
    {
        // Preparación
        doNothing().when(estudianteServicio).eliminarEstudiante(1L);

        // Ejecución
        ResponseEntity<Void> response = estudianteControlador.eliminarEstudiante(1L);

        // Verificación
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(estudianteServicio, times(1)).eliminarEstudiante(1L);
    }
}
