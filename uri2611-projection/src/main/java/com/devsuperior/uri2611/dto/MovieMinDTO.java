package com.devsuperior.uri2611.dto;

import com.devsuperior.uri2611.projections.MovieMinProjection;

public class MovieMinDTO {

    private Long id;
    private String name;

    public MovieMinDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MovieMinDTO(MovieMinProjection id, MovieMinProjection name) {
        this.id = id.getId();
        this.name = name.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
