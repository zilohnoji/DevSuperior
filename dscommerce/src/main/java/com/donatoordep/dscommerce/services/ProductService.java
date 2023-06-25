package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.dto.ProductDTO;
import com.donatoordep.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        return repository.findById(id).map(ProductDTO::new).get();
    }
}
