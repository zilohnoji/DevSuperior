package com.devsuperior.dsmovie.factory;

import com.devsuperior.dsmovie.dto.ScoreDTO;

public class ScoreFactory {

    private static final Long MOVIE_ID = 1L;
    private static final Double SCORE = 5D;

    public static ScoreDTO createScoreDTOFromMovieID(Long movieId){
        return new ScoreDTO(movieId, SCORE);
    }

    public static ScoreDTO createScoreDTOFromScore(Double score) {
        return new ScoreDTO(MOVIE_ID, score);
    }

    public static ScoreDTO createScoreDTO() {
        return new ScoreDTO(MOVIE_ID, SCORE);
    }
}
