package com.jorge.gestionEstudiantilBack.excepciones;

public class EstudianteNoEncontradoException extends RuntimeException
{

    public EstudianteNoEncontradoException(String mensaje)
    {
        super(mensaje);
    }

    public EstudianteNoEncontradoException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
}
