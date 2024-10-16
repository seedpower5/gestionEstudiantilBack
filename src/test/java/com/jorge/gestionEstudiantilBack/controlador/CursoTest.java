package com.jorge.gestionEstudiantilBack.controlador;

import com.jorge.gestionEstudiantilBack.excepciones.CursoNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Curso;
import com.jorge.gestionEstudiantilBack.servicio.CursoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CursoTest {

    @InjectMocks
    private CursoControlador cursoControlador; // Cambiar de CursoServicio a CursoControlador

    @Mock
    private CursoServicio cursoServicio;

    private Curso curso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        curso = new Curso();
        curso.setId(1L);
        curso.setNombreCurso("Matemáticas");
        curso.setDuracion(60);
        curso.setProfesor("Profesor A");
        curso.setDescripcion("Curso de Matemáticas");
        curso.setFechaInicio(LocalDate.now());
        curso.setFechaFinal(LocalDate.now().plusMonths(2));
    }

    @Test
    void testCrearCurso() {
        when(cursoServicio.agregarCurso(any(Curso.class))).thenReturn(curso);

        ResponseEntity<Curso> response = cursoControlador.crearCurso(curso);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(curso, response.getBody());
        verify(cursoServicio, times(1)).agregarCurso(any(Curso.class));
    }

    @Test
    void testListarCursos() {
        List<Curso> listaCursos = new ArrayList<>();
        listaCursos.add(curso);

        when(cursoServicio.obtenerTodosLosCursos()).thenReturn(listaCursos);

        List<Curso> cursos = cursoControlador.listarCursos();
        assertFalse(cursos.isEmpty());
        assertEquals(1, cursos.size());
        assertEquals("Matemáticas", cursos.get(0).getNombreCurso());
        verify(cursoServicio, times(1)).obtenerTodosLosCursos();
    }

    @Test
    void testEliminarCursoExistente() {
        when(cursoServicio.existsById(1L)).thenReturn(true);
        doNothing().when(cursoServicio).eliminarCurso(1L);

        ResponseEntity<Void> response = cursoControlador.eliminarCurso(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cursoServicio, times(1)).eliminarCurso(1L);
    }

    @Test
    void testEliminarCursoNoExistente() {
        when(cursoServicio.existsById(2L)).thenReturn(false);

        Exception exception = assertThrows(CursoNoEncontradoException.class, () -> {
            cursoControlador.eliminarCurso(2L);
        });

        assertEquals("Curso no encontrado con id: 2", exception.getMessage());
    }

    @Test
    void testObtenerCursoPorIdExistente() {
        when(cursoServicio.obtenerCursoPorId(1L)).thenReturn(curso);

        Curso cursoEncontrado = cursoServicio.obtenerCursoPorId(1L);
        assertNotNull(cursoEncontrado);
        assertEquals("Matemáticas", cursoEncontrado.getNombreCurso());
        verify(cursoServicio, times(1)).obtenerCursoPorId(1L);
    }

    @Test
    void testObtenerCursoPorIdNoExistente() {
        when(cursoServicio.obtenerCursoPorId(2L)).thenThrow(new CursoNoEncontradoException("Curso no encontrado con id: 2"));

        Exception exception = assertThrows(CursoNoEncontradoException.class, () -> {
            cursoServicio.obtenerCursoPorId(2L);
        });

        assertEquals("Curso no encontrado con id: 2", exception.getMessage());
    }
}
