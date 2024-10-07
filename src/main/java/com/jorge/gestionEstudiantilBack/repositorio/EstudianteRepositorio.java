package com.jorge.gestionEstudiantilBack.repositorio;

import com.jorge.gestionEstudiantilBack.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long> {
    // Método para encontrar estudiantes por ID de curso
    List<Estudiante> findByCursoId(Long cursoId);
}
