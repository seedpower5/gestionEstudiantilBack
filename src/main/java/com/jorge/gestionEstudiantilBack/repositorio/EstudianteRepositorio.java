package com.jorge.gestionEstudiantilBack.repositorio;

import com.jorge.gestionEstudiantilBack.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un repositorio
public interface EstudianteRepositorio extends JpaRepository<Estudiante,Long>
{

}
