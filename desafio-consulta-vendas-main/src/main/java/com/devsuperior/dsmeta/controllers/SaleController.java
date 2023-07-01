package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.services.SaleService;
import com.devsuperior.dsmeta.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService serviceSale;

    @Autowired
    private SellerService serviceSeller;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceSale.findById(id));
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleMinDTO>> findByRangeDateAndNameSeller(
            @RequestParam(name = "minDate", defaultValue = "today") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "result") String maxDate,
            @RequestParam(name = "name", defaultValue = "") String name, Pageable pageable
    ) {
        LocalDate parsedMinDate = (minDate.equals("today"))
                ? LocalDate.now().minusYears(1L) : LocalDate.parse(minDate);

        LocalDate parsedMaxDate = (maxDate.equals("result"))
                ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);

        return ResponseEntity.ok()
                .body(serviceSale.findByRangeDateAndNameSeller(parsedMinDate, parsedMaxDate, name, pageable));
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<Page<SellerMinDTO>> getSummary(
            @RequestParam(name = "minDate", defaultValue = "today") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "result") String maxDate, Pageable pageable) {

        LocalDate parsedMinDate = (minDate.equals("today")) ? LocalDate.now().minusYears(1L)
                : LocalDate.parse(minDate);

        LocalDate parsedMaxDate = (maxDate.equals("result"))
                ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);

        return ResponseEntity.ok()
                .body(serviceSeller.searchSellerSales(parsedMinDate, parsedMaxDate, pageable));
    }
}
