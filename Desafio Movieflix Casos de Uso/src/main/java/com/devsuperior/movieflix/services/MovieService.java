package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieDetailsDTO findById(Long id) {
        return this.toDto(movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("nenhum filme foi encontrado")));
    }

    public Page<MovieDetailsDTO> findByGenre(Long genreId, Pageable pageable) {
        PageRequest request = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
        return movieRepository.findByGenreId(genreId, request).map(this::toDto);
    }

    public Page<MovieDetailsDTO> findAll(Pageable pageable) {
        List<MovieDetailsDTO> orderedList = movieRepository.findAll(Sort.by("title")).stream().map(this::toDto).toList();
        return new PageImpl<>(orderedList, pageable, orderedList.size());
    }

    private MovieDetailsDTO toDto(Movie entity) {
        MovieDetailsDTO response = new MovieDetailsDTO();
        GenreDTO genreResponse = new GenreDTO();

        response.setId(entity.getId());
        genreResponse.setId(entity.getGenre().getId());
        genreResponse.setName(entity.getGenre().getName());
        response.setGenre(genreResponse);
        response.setImgUrl(entity.getImgUrl());
        response.setTitle(entity.getTitle());
        response.setYear(entity.getYear());
        response.setSynopsis(entity.getSynopsis());
        response.setSubTitle(entity.getSubTitle());

        return response;
    }
}