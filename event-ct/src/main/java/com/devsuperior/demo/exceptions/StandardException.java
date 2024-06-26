package com.devsuperior.demo.exceptions;

public record StandardException(
        String error,
        Integer status,
        String path
) {
}
