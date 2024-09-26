package com.devsuperior.dscommerce.controllers;


import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.tests.ProductFactory;
import com.devsuperior.dscommerce.util.ApplicationConfigIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerIT extends ApplicationConfigIT {

    private String adminToken, clientToken, invalidToken;

    @BeforeEach
    void setup() throws Exception {
        adminToken = tokenUtil.obtainAccessToken(mockMvc, "alex@gmail.com", "123456");
        clientToken = tokenUtil.obtainAccessToken(mockMvc, "maria@gmail.com", "123456");
        invalidToken = tokenUtil.obtainAccessToken(mockMvc, "invalid@gmail.com", "123456");
    }


    @Test
    void findAllShouldReturnPageWhenParameterNotIsEmpty() throws Exception {
        mockMvc.perform(get(ProductController.PATH + "?name=Macbook").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content[0].id").value(3L))
                .andExpect(jsonPath("$.content[0].name").value("Macbook Pro"))
                .andExpect(jsonPath("$.content[0].price").value(1250.0D));
    }

    @Test
    void insertShouldReturnProductDTOWhenAdminHasLogged() throws Exception {
        ProductDTO requestDto = new ProductDTO(ProductFactory.createProduct());

        mockMvc.perform(post(ProductController.PATH).header("Authorization", "Bearer " + adminToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }
}