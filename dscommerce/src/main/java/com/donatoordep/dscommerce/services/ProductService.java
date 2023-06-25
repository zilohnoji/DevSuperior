package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.dto.ProductDTO;
import com.donatoordep.dscommerce.entities.Product;
import com.donatoordep.dscommerce.mapper.ProductMapper;
import com.donatoordep.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return repository.findById(id).map(ProductDTO::new).get();
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ProductDTO::new);
    }

    @Transactional(readOnly = false)
    public ProductDTO insert(ProductDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    @Transactional(readOnly = false)
    public ProductDTO update(Long id, ProductDTO dto) {
        return (repository.getReferenceById(id)).getId().equals(dto.getId())
                ? mapper.toDto(repository.save(mapper.toEntity(dto))) : null;
    }


}
