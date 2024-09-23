package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.util.ApplicationConfigIT;
import com.devsuperior.dscommerce.util.ProductFactory;
import com.devsuperior.dscommerce.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIT extends ApplicationConfigIT {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TokenUtil tokenUtil;

    private Product product;
    private String adminToken, clientToken, invalidToken;

    @BeforeEach
    void setup() throws Exception {
        clientToken = tokenUtil.obtainAccessToken(mockMvc, "maria@gmail.com", "123456");
        adminToken = tokenUtil.obtainAccessToken(mockMvc, "alex@gmail.com", "123456");
        invalidToken = tokenUtil.obtainAccessToken(mockMvc, "pedro@gmail.com", "123456");
        
        product = ProductFactory.createProductEntity();
    }

    @Test
    void findAllShouldReturnPageWhenNameParamNonEmpty() throws Exception {
        String productName = "Macbook";

        mockMvc.perform(get("/products?name={productName}", productName).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(3L));
    }

    @Test
    void findAllShouldReturnPageWhenNameParamIsEmpty() throws Exception {
        mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].name").value("The Lord of the Rings"));
    }

    @Test
    void insertShouldReturnProductDTOWhenAdminLogged() throws Exception {
        ProductDTO request = ProductFactory.createProductDTO();

        mockMvc.perform(post("/products")
                        .header("Authorization", "Bearer " + adminToken)
                        .content(mapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }
}