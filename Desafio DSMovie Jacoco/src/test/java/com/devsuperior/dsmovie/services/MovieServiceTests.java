package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.services.exceptions.DatabaseException;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {

    MovieEntity entity;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    private MovieService service;


    @BeforeEach
    void setup() {
        this.entity = MovieFactory.createMovieEntity();
    }

    @Test
    public void findAllShouldReturnPagedMovieDTO() {
        List<MovieEntity> movieList = Arrays.asList(
                MovieFactory.createMovieEntity(1L, "The Man From Earth"),
                MovieFactory.createMovieEntity(2L, "Giant Man")
        );

        PageRequest pageRequest = PageRequest.of(0, 10);
        PageImpl<MovieEntity> pageImpl = new PageImpl<>(movieList);

        Mockito.when(this.movieRepository.searchByTitle("Man", pageRequest)).thenReturn(pageImpl);

        Page<MovieDTO> response = this.service.findAll("Man", pageRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(2, response.getSize());
        Assertions.assertEquals("The Man From Earth", response.getContent().getFirst().getTitle());
    }

    @Test
    public void findByIdShouldReturnMovieDTOWhenIdExists() {
        Mockito.when(this.movieRepository.findById(1L)).thenReturn(Optional.of(entity));

        MovieDTO response = this.service.findById(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getImage(), response.getImage());
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(this.movieRepository.findById(0L)).thenThrow(ResourceNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.service.findById(0L);
        });
    }

    @Test
    public void insertShouldReturnMovieDTO() {
        Mockito.when(this.movieRepository.save(ArgumentMatchers.any(MovieEntity.class))).thenReturn(entity);

        MovieDTO response = this.service.insert(MovieFactory.createMovieDTO());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getImage(), response.getImage());
    }

    @Test
    public void updateShouldReturnMovieDTOWhenIdExists() {
        MovieDTO updateData = MovieFactory.createMovieDTO("https://donatopedro.tech");
        entity.setImage(updateData.getImage());

        Mockito.when(this.movieRepository.getReferenceById(1L)).thenReturn(entity);
        Mockito.when(this.movieRepository.save(ArgumentMatchers.any(MovieEntity.class))).thenReturn(entity);

        MovieDTO response = this.service.update(entity.getId(), updateData);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(entity.getId(), response.getId());
        Assertions.assertEquals(entity.getImage(), response.getImage());

    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(this.movieRepository.getReferenceById(0L)).thenThrow(EntityNotFoundException.class);

        MovieDTO updateData = MovieFactory.createMovieDTO("https://donatopedro.tech");
        entity.setImage(updateData.getImage());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.service.update(0L, updateData);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        Mockito.when(this.movieRepository.existsById(1L)).thenReturn(true);
        Mockito.doNothing().when(this.movieRepository).deleteById(1L);

        Assertions.assertDoesNotThrow(() -> this.service.delete(1L));
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(this.movieRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            this.service.delete(1L);
        });
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        Mockito.when(this.movieRepository.existsById(1L)).thenReturn(true);
        Mockito.doThrow(DataIntegrityViolationException.class).when(this.movieRepository).deleteById(1L);

        Assertions.assertThrows(DatabaseException.class, () -> {
            this.service.delete(1L);
        });
    }
}
