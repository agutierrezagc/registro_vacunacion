package com.krugercorp.ec.vacunacion.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnathorizedException extends RuntimeException {
    public UnathorizedException(String message){
        super(message);
    }
}
