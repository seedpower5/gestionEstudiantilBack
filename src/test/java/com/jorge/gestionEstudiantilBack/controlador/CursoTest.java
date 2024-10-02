package com.jorge.gestionEstudiantilBack.controlador;


import com.jorge.gestionEstudiantilBack.excepciones.CursoNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Curso;
import com.jorge.gestionEstudiantilBack.repositorio.CursoRepositorio;
import com.jorge.gestionEstudiantilBack.servicio.CursoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CursoTest
{

    @InjectMocks
    private CursoServicio cursoServicio;

    @Mock
    private CursoRepositorio cursoRepositorio;

    private Curso curso;

    @BeforeEach
    void setUp()
    {
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
    void testAgregarCurso()
    {
        when(cursoRepositorio.save(any(Curso.class))).thenReturn(curso);

        Curso nuevoCurso = cursoServicio.agregarCurso(curso);
        assertNotNull(nuevoCurso);
        assertEquals("Matemáticas", nuevoCurso.getNombreCurso());
        verify(cursoRepositorio, times(1)).save(curso);
    }

    @Test
    void testListarCursos()
    {
        List<Curso> listaCursos = new ArrayList<>();
        listaCursos.add(curso);

        when(cursoRepositorio.findAll()).thenReturn(listaCursos);

        List<Curso> cursos = cursoServicio.obtenerTodosLosCursos();
        assertFalse(cursos.isEmpty());
        assertEquals(1, cursos.size());
        assertEquals("Matemáticas", cursos.get(0).getNombreCurso());
        verify(cursoRepositorio, times(1)).findAll();
    }

    @Test
    void testEliminarCursoExistente()
    {
        when(cursoRepositorio.existsById(1L)).thenReturn(true);
        doNothing().when(cursoRepositorio).deleteById(1L);

        cursoServicio.eliminarCurso(1L);
        verify(cursoRepositorio, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarCursoNoExistente()
    {
        when(cursoRepositorio.existsById(2L)).thenReturn(false);

        Exception exception = assertThrows(CursoNoEncontradoException.class, () -> {
            cursoServicio.eliminarCurso(2L);
        });

        assertEquals("curso no encontrado con id: 2", exception.getMessage());
    }

    @Test
    void testObtenerCursoPorIdExistente()
    {
        when(cursoRepositorio.findById(1L)).thenReturn(Optional.of(curso));

        Curso cursoEncontrado = cursoServicio.obtenerCursoPorId(1L);
        assertNotNull(cursoEncontrado);
        assertEquals("Matemáticas", cursoEncontrado.getNombreCurso());
        verify(cursoRepositorio, times(1)).findById(1L);
    }

    @Test
    void testObtenerCursoPorIdNoExistente()
    {
        when(cursoRepositorio.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CursoNoEncontradoException.class, () -> {
            cursoServicio.obtenerCursoPorId(2L);
        });

        assertEquals("curso no encontrado con id: 2", exception.getMessage());
    }
}
