package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.util.ApplicationConfigIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerIT extends ApplicationConfigIT {

    String clientToken, adminToken, invalidToken;

    @BeforeEach
    void setup() throws Exception {
        clientToken = tokenUtil.obtainAccessToken(mockMvc, "maria@gmail.com", "123456");
        adminToken = tokenUtil.obtainAccessToken(mockMvc, "alex@gmail.com", "123456");
        invalidToken = adminToken + "xpto";
    }

    @Test
    void findByIdShouldReturnPageWhenIdHasExistsAndClientLogged() throws Exception {
        mockMvc.perform(get(OrderController.PATH + "/1").header("Authorization", "Bearer " + clientToken)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void findByIdShouldReturnPageWhenIdHasExistsAndAdminLogged() throws Exception {
        mockMvc.perform(get(OrderController.PATH + "/1").header("Authorization", "Bearer " + adminToken)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Nested
    public class OrderControllerITFail {

        @Test
        void findByIdShouldReturnForbiddenWhenIdHasExistsAndClientLogged() throws Exception {
            mockMvc.perform(get(OrderController.PATH + "/2").header("Authorization", "Bearer " + clientToken)
                            .accept(MediaType.APPLICATION_JSON))

                    .andExpect(status().isForbidden())
                    .andDo(print());
        }

        @Test
        void findByIdShouldReturnNotFoundWhenIdNotExistsAndAdminLogged() throws Exception {
            mockMvc.perform(get(OrderController.PATH + "/0").header("Authorization", "Bearer " + adminToken)
                            .accept(MediaType.APPLICATION_JSON))

                    .andExpect(status().isNotFound())
                    .andDo(print());
        }

        @Test
        void findByIdShouldReturnNotFoundWhenIdNotExistsAndClientLogged() throws Exception {
            mockMvc.perform(get(OrderController.PATH + "/0").header("Authorization", "Bearer " + clientToken)
                            .accept(MediaType.APPLICATION_JSON))

                    .andExpect(status().isNotFound())
                    .andDo(print());
        }

        @Test
        void findByIdShouldReturnNotFoundWhenNotLogged() throws Exception {
            mockMvc.perform(get(OrderController.PATH + "/0")
                            .accept(MediaType.APPLICATION_JSON))

                    .andExpect(status().isUnauthorized())
                    .andDo(print());
        }

    }
}
