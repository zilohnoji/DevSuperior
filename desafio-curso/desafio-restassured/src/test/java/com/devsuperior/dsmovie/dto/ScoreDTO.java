package com.devsuperior.dsmovie.dto;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ScoreDTO {
    private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    private Long movieId;
    private Double score;

    public ScoreDTO(Long movieId, Double score) {
        this.movieId = movieId;
        this.score = Double.valueOf(df.format(score));
    }

    public Long getMovieId() {
        return movieId;
    }

    public Double getScore() {
        return score;
    }
}