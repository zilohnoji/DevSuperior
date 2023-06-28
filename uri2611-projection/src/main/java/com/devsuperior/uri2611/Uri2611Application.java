package com.devsuperior.uri2611;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

    @Autowired
    private MovieRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Uri2611Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<MovieMinDTO> projectionList = repository.findByGenre("Action")
                .stream().map(x -> new MovieMinDTO(x, x))
                .collect(Collectors.toList());

        List<MovieMinDTO> projectionList2 = repository.findByGenreDto("Action");

        projectionList.forEach(x -> System.out.println(x.getId() + ": " + x.getName()));
        System.out.println("\n");
        projectionList2.forEach(x -> System.out.println(x.getId() + ": " + x.getName()));
    }
}
