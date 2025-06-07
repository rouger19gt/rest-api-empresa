package com.empresa.excepciones;

public class RecursoSinEncontrarException extends  RuntimeException{
    public RecursoSinEncontrarException(String mensaje){
        super(mensaje);
    }
}
