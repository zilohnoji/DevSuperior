package com.devsuperior.dscommerce.util;

import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.AuthService;
import com.devsuperior.dscommerce.services.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public abstract class ApplicationConfigTest {

    @Mock
    protected OrderRepository repository;

    @Mock
    protected AuthService authService;

    @Mock
    protected ProductRepository productRepository;

    @Mock
    protected OrderItemRepository orderItemRepository;

    @Mock
    protected UserService userService;
}