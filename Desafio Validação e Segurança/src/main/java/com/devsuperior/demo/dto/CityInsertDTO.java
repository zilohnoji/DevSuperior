package com.devsuperior.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class CityInsertDTO {

    @NotBlank(message = "Campo requerido")
    private String name;

    public CityInsertDTO() {
    }

    public CityInsertDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}