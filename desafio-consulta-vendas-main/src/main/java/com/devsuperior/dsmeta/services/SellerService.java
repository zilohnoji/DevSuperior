package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SellerMinDTO;
import com.devsuperior.dsmeta.mappers.SellerMapper;
import com.devsuperior.dsmeta.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SellerService {

    @Autowired
    private SellerRepository repository;

    @Autowired
    private SellerMapper mapper;

    @Transactional(readOnly = true)
    public Page<SellerMinDTO> findByName(String name, Pageable pageable) {
        return repository.findByName(name, pageable).map(x -> mapper.toDto(x));
    }

    @Transactional(readOnly = true)
    public Page<SellerMinDTO> searchSellerSales(LocalDate start, LocalDate end, Pageable pageable) {
		return repository.searchSellerSales(start, end, pageable);
    }
}
