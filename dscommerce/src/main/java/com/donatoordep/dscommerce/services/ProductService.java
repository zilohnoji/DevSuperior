package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.dto.ProductDTO;
import com.donatoordep.dscommerce.dto.ProductMinDTO;
import com.donatoordep.dscommerce.entities.Category;
import com.donatoordep.dscommerce.entities.Product;
import com.donatoordep.dscommerce.mapper.ProductMapper;
import com.donatoordep.dscommerce.repositories.ProductRepository;
import com.donatoordep.dscommerce.services.exceptions.DatabaseViolationReferentialException;
import com.donatoordep.dscommerce.services.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return repository.findById(id).map(ProductDTO::new)
                .orElseThrow(NotFoundResourceException::new);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(ProductMinDTO::new);
    }

    @Transactional(readOnly = false)
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        entity = repository.save(entity);
        copyToEntity(dto, entity);
        return new ProductDTO(entity);
    }

    @Transactional(readOnly = false)
    public ProductDTO update(Long id, ProductDTO dto) {
        if (repository.getReferenceById(id).getId().equals(dto.getId())) {
            return mapper.toDto(repository.save(mapper.toEntity(dto)));
        } else {
            throw new NotFoundResourceException();
        }
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findByName(String name, Pageable pageable) {
        return repository.searchByName(name, pageable).map(ProductDTO::new);
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundResourceException();
        } else {
            try {
                repository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DatabaseViolationReferentialException();
            }
        }
    }

    private void copyToEntity(ProductDTO dto, Product entity){
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.getCategories().clear();
        dto.getCategories().forEach(cat -> entity.getCategories().add(new Category(cat.getId())));
    }
}
