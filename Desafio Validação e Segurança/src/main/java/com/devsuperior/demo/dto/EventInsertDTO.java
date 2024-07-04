package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.Event;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class EventInsertDTO {

    @NotBlank(message = "Campo requerido")
    private String name;

    @FutureOrPresent(message = "A data do evento não pode ser passada")
    private LocalDate date;

    private String url;

    @NotNull(message = "Campo requerido")
    private Long cityId;

    public EventInsertDTO() {
    }

    public EventInsertDTO(String name, LocalDate date, String url, Long cityId) {
        this.name = name;
        this.date = date;
        this.url = url;
        this.cityId = cityId;
    }

    public EventInsertDTO(Event entity) {
        name = entity.getName();
        date = entity.getDate();
        url = entity.getUrl();
        cityId = entity.getCity().getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}