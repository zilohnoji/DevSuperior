package com.devsuperior.uri2621;

import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2621.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Uri2621Application implements CommandLineRunner {

    @Autowired
    private ProductRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Uri2621Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<ProductMinProjection> projectionList = repository.findAllByFilter(10, 20, "P");
		List<ProductMinDTO> projectionList2 = repository.findAllByFilterDto("P");

        projectionList.forEach(object -> System.out.println(object.getName()));
		projectionList2.forEach(object -> System.out.println(object.getName()));
    }
}
