package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.entities.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query(value = "SELECT obj FROM Seller obj JOIN FETCH obj.sales WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))",
    countQuery = "SELECT COUNT(obj) FROM Seller obj JOIN obj.sales")
    Page<Seller> findByName(String name, Pageable pageable);

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SellerMinDTO(seller.name, SUM(sale.amount)) " +
            "FROM Seller seller JOIN seller.sales sale WHERE sale.date BETWEEN :start AND :end " +
            "GROUP BY seller.name",
    countQuery = "SELECT COUNT(DISTINCT(seller)) " +
            "FROM Seller seller JOIN seller.sales sale WHERE sale.date BETWEEN :start AND :end")
    Page<SellerMinDTO> searchSellerSales(LocalDate start, LocalDate end, Pageable pageable);
}
