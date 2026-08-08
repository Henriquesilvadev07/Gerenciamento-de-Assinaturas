package com.example.Controle_de_Assinaturas.Exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AssinaturasExceptions {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity erro404 (EntityNotFoundException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity erro400 (IllegalArgumentException exception) {
        return ResponseEntity.status(400).body(exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity erro400Json (HttpMessageNotReadableException exception) {
        return ResponseEntity.status(400).body("Erro ao receber dados via json, verificar dados novamente");
    }
}
