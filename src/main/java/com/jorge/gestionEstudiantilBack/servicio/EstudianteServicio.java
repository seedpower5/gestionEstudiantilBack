package com.jorge.gestionEstudiantilBack.servicio;

import com.jorge.gestionEstudiantilBack.excepciones.CursoNoEncontradoException;
import com.jorge.gestionEstudiantilBack.excepciones.EstudianteNoEncontradoException;
import com.jorge.gestionEstudiantilBack.modelo.Estudiante;
import com.jorge.gestionEstudiantilBack.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstudianteServicio
{
    private final EstudianteRepositorio estudianteRepositorio;

    @Autowired
    public EstudianteServicio(EstudianteRepositorio estudianteRepositorio)
    {
        this.estudianteRepositorio= estudianteRepositorio;
    }
    //obtener todos los estudiantes
    public List<Estudiante> obtenerTodosLosEstudiantes()
    {
        return  estudianteRepositorio.findAll();
    }
    //agregar nuevo estudiante
    public Estudiante agregarEstudiante(Estudiante estudiante)
    {
        return  estudianteRepositorio.save(estudiante);
    }
    //eliminar estudiante por id
    public void  eliminarEstudiante(Long id)
    {
        if (!estudianteRepositorio.existsById(id))
        {
            throw new EstudianteNoEncontradoException("estudiante no encontrado con id: "+id);
        }
        estudianteRepositorio.deleteById(id);
    }
    public Estudiante obtenerEstudiantePorId(Long id)
    {
        return estudianteRepositorio.findById(id)
                .orElseThrow(() -> new EstudianteNoEncontradoException("estudiante  no encontrado con id: " + id));
    }
}
