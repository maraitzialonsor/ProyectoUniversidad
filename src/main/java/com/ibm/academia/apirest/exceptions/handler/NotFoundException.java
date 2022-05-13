package com.ibm.academia.apirest.exceptions.handler;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mensaje){
        super(mensaje);
    }
    private static final long serialVersionUID = -4351926524540886169L;
}
