package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.dto.CategoryDTO;
import com.donatoordep.dscommerce.dto.OrderDTO;
import com.donatoordep.dscommerce.dto.OrderItemDTO;
import com.donatoordep.dscommerce.entities.Order;
import com.donatoordep.dscommerce.entities.OrderItem;
import com.donatoordep.dscommerce.entities.OrderStatus;
import com.donatoordep.dscommerce.entities.Product;
import com.donatoordep.dscommerce.repositories.CategoryRepository;
import com.donatoordep.dscommerce.repositories.OrderItemRepository;
import com.donatoordep.dscommerce.repositories.OrderRepository;
import com.donatoordep.dscommerce.repositories.ProductRepository;
import com.donatoordep.dscommerce.services.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
    }
}
