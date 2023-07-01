package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(sale.id, sale.date, sale.amount, sale.seller.name)" +
            "FROM Sale sale " +
            "WHERE UPPER(seller.name) LIKE UPPER(CONCAT('%', :name, '%')) AND sale.date BETWEEN :start AND :end",
            countQuery = "SELECT COUNT(*) " +
                    "FROM Sale sale " +
                    "WHERE UPPER(sale.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) AND sale.date BETWEEN :start AND :end")
    Page<SaleMinDTO> findByRangeDateAndNameSeller(LocalDate start, LocalDate end, String name, Pageable pageable);
}
