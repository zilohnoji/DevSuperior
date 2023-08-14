package com.donatoordep.dscommerce.services;

import com.donatoordep.dscommerce.dto.OrderDTO;
import com.donatoordep.dscommerce.dto.OrderItemDTO;
import com.donatoordep.dscommerce.entities.Order;
import com.donatoordep.dscommerce.entities.OrderItem;
import com.donatoordep.dscommerce.entities.OrderStatus;
import com.donatoordep.dscommerce.entities.Product;
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
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        OrderDTO order = repository.findById(id).map(OrderDTO::new).orElseThrow(NotFoundResourceException::new);
        authService.validateSelfOrAdmin(order.getClient().getId());
        return order;
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order entity = new Order();

        entity.setMoment(Instant.now());
        entity.setStatus(OrderStatus.WAITING_PAYMENT);
        entity.setClient(userService.authenticated());

        for (OrderItemDTO orderItemDTO : dto.getItems()) {
            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());
            OrderItem orderItem = new OrderItem(entity, product, orderItemDTO.getQuantity(), product.getPrice());
            entity.getItems().add(orderItem);
        }
        repository.save(entity);
        orderItemRepository.saveAll(entity.getItems());

        return new OrderDTO(entity);
    }

}
