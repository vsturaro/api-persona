package com.digitalinovation.apipersona.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonFoundException extends Exception {
    public PersonFoundException(Long id) {
        super("Person not found whith ID "+ id);
    }
}
