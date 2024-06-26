package com.devsuperior.demo.controllers.handler;

import com.devsuperior.demo.exceptions.ONBEntityNotFoundException;
import com.devsuperior.demo.exceptions.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ONBEntityNotFoundException.class)
    public ResponseEntity<StandardException> cityNotFoundException(ONBEntityNotFoundException exception, HttpServletRequest request) {
        return exceptionHandlerFactory(exception, HttpStatus.NOT_FOUND, request.getRequestURI());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardException> constraintViolationException(DataIntegrityViolationException exception, HttpServletRequest request) {
        return exceptionHandlerFactory(exception, HttpStatus.BAD_REQUEST, request.getRequestURI());
    }


    private ResponseEntity<StandardException> exceptionHandlerFactory(Exception exception, HttpStatus status, String path) {
        return ResponseEntity.status(status).body(new StandardException(exception.getMessage(), status.value(), path));
    }
}
