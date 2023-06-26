package com.donatoordep.lesson03.controllers.handler;

import com.donatoordep.lesson03.services.exceptions.CustomizedException;
import com.donatoordep.lesson03.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class AdviceHandlerExceptions {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomizedException> resourceNotFound(
            ResourceNotFoundException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomizedException(
                Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI()));
    }
}
