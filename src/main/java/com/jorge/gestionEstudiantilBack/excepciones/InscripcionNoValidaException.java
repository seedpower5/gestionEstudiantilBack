package com.jorge.gestionEstudiantilBack.excepciones;

public class InscripcionNoValidaException extends RuntimeException
{

    public InscripcionNoValidaException(String mensaje)
    {
        super(mensaje);
    }

    public InscripcionNoValidaException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
}
