package com.example.Controle_de_Assinaturas.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AssinaturasExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404 (EntityNotFoundException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    public ResponseEntity erro400 (IllegalArgumentException exception) {
        return ResponseEntity.status(400).body(exception.getMessage());
    }
}
