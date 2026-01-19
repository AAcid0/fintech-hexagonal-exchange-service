package com.global66practice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.global66practice.domain.exceptions.IncompleteInformationException;
import com.global66practice.domain.exceptions.TraderNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IncompleteInformationException.class)
    public ResponseEntity<String> handleIncompleteInformationException(IncompleteInformationException ex) {
        return new ResponseEntity<>("Complete toda la información requerida.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("Error interno del servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TraderNotFoundException.class)
    public ResponseEntity<String> handleTraderNotFoundException(TraderNotFoundException ex) {
        return new ResponseEntity<>("El usuario no fue encontrado en el sistema de divisas.", HttpStatus.NOT_FOUND);
    }
}
