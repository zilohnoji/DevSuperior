package com.devsuperior.dsmeta.controllers.handlers;

import com.devsuperior.dsmeta.services.exceptions.CustomizedException;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomizedException> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new CustomizedException(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI())
        );
    }
}
