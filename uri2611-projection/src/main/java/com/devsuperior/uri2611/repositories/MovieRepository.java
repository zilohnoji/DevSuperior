package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value = "SELECT movies.id, movies.name FROM movies INNER JOIN genres ON movies.id_genres = genres.id WHERE genres.description = :genre")
    List<MovieMinProjection> findByGenre(String genre);

    @Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(u.id, u.name) FROM Movie u WHERE u.genre.description = :genre")
    List<MovieMinDTO> findByGenreDto(String genre);
}
