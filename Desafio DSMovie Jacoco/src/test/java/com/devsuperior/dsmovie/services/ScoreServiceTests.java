package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import com.devsuperior.dsmovie.tests.ScoreFactory;
import com.devsuperior.dsmovie.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {

    MovieEntity movieEntity;

    UserEntity userEntity;

    ScoreEntity scoreEntity;

    @Mock
    UserService userService;

    @Mock
    MovieRepository movieRepository;

    @Mock
    ScoreRepository scoreRepository;

    @InjectMocks
    private ScoreService service;


    @BeforeEach
    void setup() {
        this.movieEntity = MovieFactory.createMovieEntity();
        this.userEntity = UserFactory.createUserEntity();
        this.scoreEntity = ScoreFactory.createScoreEntity();
        this.movieEntity.getScores().add(this.scoreEntity);
    }

    @Test
    public void saveScoreShouldReturnMovieDTO() {
        Mockito.when(this.userService.authenticated()).thenReturn(userEntity);
        Mockito.when(this.movieRepository.findById(1L)).thenReturn(Optional.of(movieEntity));
        Mockito.when(this.scoreRepository.saveAndFlush(ArgumentMatchers.any(ScoreEntity.class))).thenReturn(scoreEntity);
        Mockito.when(this.movieRepository.save(ArgumentMatchers.any(MovieEntity.class))).thenReturn(movieEntity);

        MovieDTO response = this.service.saveScore(ScoreFactory.createScoreDTO());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(4.5, response.getScore());
    }

    @Test
    public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {
        Mockito.when(this.userService.authenticated()).thenReturn(userEntity);
        Mockito.when(this.movieRepository.findById(1L)).thenThrow(ResourceNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.service.saveScore(ScoreFactory.createScoreDTO());
        });
    }
}
