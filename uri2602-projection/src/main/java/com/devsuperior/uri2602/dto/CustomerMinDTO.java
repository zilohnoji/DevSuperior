package com.devsuperior.uri2602.dto;

import com.devsuperior.uri2602.repositories.CustomerMinProjection;

public class CustomerMinDTO implements CustomerMinProjection {

    private String name;
    private String city;

    public CustomerMinDTO() {
    }

    public CustomerMinDTO(CustomerMinProjection name, CustomerMinProjection city) {
        this.name = name.getName();
        this.city = city.getCity();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
