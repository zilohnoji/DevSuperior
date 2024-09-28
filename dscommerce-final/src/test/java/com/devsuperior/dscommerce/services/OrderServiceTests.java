package com.devsuperior.dscommerce.services;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Optional;

import com.devsuperior.dscommerce.util.ApplicationConfigTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devsuperior.dscommerce.dto.OrderDTO;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbiddenException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscommerce.tests.OrderFactory;
import com.devsuperior.dscommerce.tests.ProductFactory;
import com.devsuperior.dscommerce.tests.UserFactory;

import jakarta.persistence.EntityNotFoundException;


public class OrderServiceTests extends ApplicationConfigTest {
	
	@InjectMocks
	private OrderService service;

	private Long existingOrderId, nonExistingOrderId;
	private Long existingProductId, nonExistingProductId;

	private User admin, client;
	private Product product;
	private Order order;

	private OrderDTO orderDTO;

	@BeforeEach
	void setUp() throws Exception {
		existingOrderId = 1L;
		nonExistingOrderId = 2L;
		
		existingProductId = 1L;
		nonExistingProductId = 2L;
		
		admin = UserFactory.createCustomAdminUser(1L, "Jef");
		client = UserFactory.createCustomClientUser(2L, "Bob");
		
		order = OrderFactory.createOrder(client);
		
		orderDTO = new OrderDTO(order);

		product = ProductFactory.createProductWithIdentifier(existingProductId);
	}
	
	@Test
	public void findByIdShouldReturnOrderDTOWhenIdExistsAndAdminLogged() {

		Mockito.when(repository.findById(existingOrderId)).thenReturn(Optional.of(order));
		Mockito.doNothing().when(authService).validateSelfOrAdmin(any());
		
		OrderDTO result = service.findById(existingOrderId);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingOrderId);
	}
	
	@Test
	public void findByIdShouldReturnOrderDTOWhenIdExistsAndSelfClientLogged() {

		Mockito.when(repository.findById(existingOrderId)).thenReturn(Optional.of(order));
		Mockito.doNothing().when(authService).validateSelfOrAdmin(any());
		
		OrderDTO result = service.findById(existingOrderId);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingOrderId);		
	}
	
	@Test
	public void findByIdShouldThrowsForbiddenExceptionWhenIdExistsAndOtherClientLogged() {

		Mockito.when(repository.findById(existingOrderId)).thenReturn(Optional.of(order));
		Mockito.doThrow(ForbiddenException.class).when(authService).validateSelfOrAdmin(any());

		Assertions.assertThrows(ForbiddenException.class, () -> {
			OrderDTO result = service.findById(existingOrderId);
		});
	}
	
	@Test
	public void findByIdShouldThrowsResourceNotFoundExceptionWhenIdDoesNotExist() {

		Mockito.when(repository.findById(nonExistingOrderId)).thenReturn(Optional.empty());
		Mockito.doNothing().when(authService).validateSelfOrAdmin(any());
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			OrderDTO result = service.findById(nonExistingOrderId);
		});
	}
	
	@Test
	public void insertShouldReturnOrderDTOWhenAdminLogged() {

		Mockito.when(productRepository.getReferenceById(existingProductId)).thenReturn(product);
		Mockito.when(userService.authenticated()).thenReturn(admin);
		
		OrderDTO result = service.insert(orderDTO);
		
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void insertShouldReturnOrderDTOWhenClientLogged() {

		Mockito.when(productRepository.getReferenceById(existingProductId)).thenReturn(product);
		Mockito.when(userService.authenticated()).thenReturn(client);
		
		OrderDTO result = service.insert(orderDTO);
		
		Assertions.assertNotNull(result);
	}
	
	@Test
	public void insertShouldThrowsUsernameNotFoundExceptionWhenUserNotLogged() {
		
		Mockito.doThrow(UsernameNotFoundException.class).when(userService).authenticated();
		
		order.setClient(new User());
		orderDTO = new OrderDTO(order);
		
		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			@SuppressWarnings("unused")
			OrderDTO result = service.insert(orderDTO);
		});
	}
	
	@Test
	public void insertShouldThrowsEntityNotFoundExceptionWhenOrderProductIdDoesNotExist() {

		Mockito.when(productRepository.getReferenceById(nonExistingProductId)).thenThrow(EntityNotFoundException.class);
		Mockito.when(productRepository.getReferenceById(existingProductId)).thenReturn(product);
		Mockito.when(userService.authenticated()).thenReturn(client);

		Product product = ProductFactory.createProductWithIdentifier(nonExistingProductId);

		OrderItem orderItem = new OrderItem(order, product, 2, 10.0);
		order.getItems().add(orderItem);

		orderDTO = new OrderDTO(order);

		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			@SuppressWarnings("unused")
			OrderDTO result = service.insert(orderDTO);
		});
	}

}
