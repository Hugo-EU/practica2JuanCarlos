package com.daw2.springprimero.exceptions;
public abstract class Exception extends RuntimeException {
    public Exception(String mensaje) {
        super(mensaje);
    }
}