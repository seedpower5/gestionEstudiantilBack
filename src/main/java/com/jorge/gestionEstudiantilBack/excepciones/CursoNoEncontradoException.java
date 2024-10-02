package com.jorge.gestionEstudiantilBack.excepciones;

public class CursoNoEncontradoException extends RuntimeException
{

    public CursoNoEncontradoException(String mensaje)
    {
        super(mensaje);
    }

    public CursoNoEncontradoException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
}
