package com.devsuperior.dscommerce;

import com.devsuperior.dscommerce.entities.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DscommerceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DscommerceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var categoryGames = new Category(1L, "Jogos");
        var categoryBooks = new Category(1L, "Livros");

        System.out.println(categoryBooks.hashCode());
        System.out.println(categoryGames.hashCode());
    }
}