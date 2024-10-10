package com.devsuperior.dsmovie.factory;

import com.devsuperior.dsmovie.dto.MovieDTO;

public class MovieFactory {

    private static final Long ID = 1L;
    private static final String TITLE = "The Witcher Shadow";
    private static final Double SCORE = 5D;
    private static final Integer COUNT = 5;
    private static final String IMAGE = "image.url";

    public static MovieDTO createMovieDTOFromTitle(String title) {
        return new MovieDTO(ID, title, SCORE, COUNT, IMAGE);
    }

    public static MovieDTO createMovieDTO() {
        return new MovieDTO(ID, TITLE, SCORE, COUNT, IMAGE);
    }
}
