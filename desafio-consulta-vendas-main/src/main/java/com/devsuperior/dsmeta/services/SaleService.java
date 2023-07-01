package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.mappers.SaleMapper;
import com.devsuperior.dsmeta.mappers.SellerMapper;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private SaleMapper mapper;

    @Transactional(readOnly = true)
    public SaleMinDTO findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Transactional(readOnly = true)
    public Page<SaleMinDTO> findByRangeDateAndNameSeller(LocalDate start, LocalDate end, String name, Pageable pageable) {
        return repository.findByRangeDateAndNameSeller(start, end, name, pageable);
    }
}
