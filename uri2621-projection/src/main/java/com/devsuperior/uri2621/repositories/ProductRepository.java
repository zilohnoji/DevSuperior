package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(nativeQuery = true, value = "SELECT products.name FROM products INNER JOIN providers ON providers.id = products.id_providers WHERE products.amount BETWEEN :min AND :max AND providers.name LIKE :containing%")
    List<ProductMinProjection> findAllByFilter(Integer min, Integer max, String containing);

    @Query("SELECT new com.devsuperior.uri2621.ProductMinDTO(u.name) FROM Product u WHERE u.amount >= 10 AND u.amount <= 20 AND u.provider.name LIKE 'P%'")
    List<ProductMinDTO> findAllByFilterDto(String containing);
}
