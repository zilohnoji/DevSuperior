package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.dto.OrderDTO;
import com.donatoordep.dscommerce.repositories.OrderRepository;
import com.donatoordep.dscommerce.services.exceptions.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        return repository.findById(id).map(OrderDTO::new).orElseThrow(NotFoundResourceException::new);
    }

}
