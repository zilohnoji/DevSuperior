package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.tests.ProductFactory;
import com.devsuperior.dscommerce.tests.TokenUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductControllerRA {

    String adminToken, clientToken, invalidToken;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8081";
        adminToken = TokenUtil.obtainAccessToken("alex@gmail.com", "123456");
        clientToken = TokenUtil.obtainAccessToken("maria@gmail.com", "123456");
        invalidToken = clientToken + "xpto";
    }

    @Test
    void findByIdShouldReturnProductWhenIdHasExists() {
        RestAssured.get("/products/{id}", 1L).then().statusCode(200)
                .body("id", is(1))
                .body("name", equalTo("The Lord of the Rings"))
                .body("price", is(90.5F))
                .body("categories.id", hasItem(1))
                .body("categories.name", hasItem("Livros"));
    }

    @Test
    void findAllShouldReturnPageProductWhenParameterNameIsEmpty() {
        RestAssured.get("/products").then().statusCode(200)
                .body("content.id", hasItems(3, 9))
                .body("content.name", hasItems("Macbook Pro", "PC Gamer Tera"));
    }

    @Test
    void findAllShouldReturnPageProductWhenParameterNameNotIsEmpty() {
        RestAssured.get("/products?name={name}", "Macbook Pro").then().statusCode(200)
                .body("content[0].id", is(3))
                .body("content[0].name", equalTo("Macbook Pro"));
    }

    @Test
    void findAllShouldReturnPageProductWithPriceGreaterThan2000() {
        RestAssured.get("/products?size=25").then().statusCode(200)
                .body("content.findAll {it.price > 2000}.name", hasItems("Smart TV", "PC Gamer Weed"));
    }

    @Test
    void insertShouldReturnProductDTOWhenDataIsValidAndAdminLogged() {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .body(ProductFactory.createProductDto())
                .when().post("/products").then().statusCode(201)
                .body("name", equalTo("Console PlayStation 5"))
                .body("description", equalTo("consectetur adipiscing elit, sed"))
                .body("price", is(3999.0F))
                .body("categories.id", hasItems(1));
    }

    @Test
    void deleteShouldReturnNoContentWhenIdExistsAndAdminLogged() {
        given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .body(ProductFactory.createProductDto())
                .when().delete("/products/25").then().statusCode(204);
    }

    @Nested
    public class ProductControllerRAFail {

        @Test
        void insertShouldReturnUnprocessableEntityWhenNameIsInvalidAndAdminLogged() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDtoWithName("aa"))
                    .when().post("/products").then().statusCode(422)
                    .body("errors[0].fieldName", equalTo("name"))
                    .body("errors[0].message", equalTo("Nome precisa ter de 3 a 80 caracteres"));
        }

        @Test
        void insertShouldReturnUnprocessableEntityWhenPriceIsNegativeAndAdminLogged() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDtoWithPrice(-20.50D))
                    .when().post("/products").then().statusCode(422)
                    .body("errors[0].fieldName", equalTo("price"))
                    .body("errors[0].message", equalTo("O preço deve ser positivo"));
        }

        @Test
        void insertShouldReturnUnprocessableEntityWhenCategoryIsEmptyAndAdminLogged() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductWithCategoryEmpty())
                    .when().post("/products").then().statusCode(422)
                    .body("errors[0].fieldName", equalTo("categories"))
                    .body("errors[0].message", equalTo("Deve ter pelo menos uma categoria"));
        }

        @Test
        void insertShouldReturnForbiddenWhenClientLogged() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDto())
                    .when().post("/products").then().statusCode(403);
        }

        @Test
        void insertShouldReturnUnauthorizedWhenInvalidToken() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + invalidToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDto())
                    .when().post("/products").then().statusCode(401);
        }

        @Test
        void deleteShouldReturnNotFoundWhenIdNotExistsAndAdminLogged() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDto())
                    .when().delete("/products/0").then().statusCode(404);
        }

        @Test
        void deleteShouldReturnBadRequestWhenDependentIdAndAdminLogged() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + adminToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDto())
                    .when().delete("/products/1").then().statusCode(400);
        }

        @Test
        void deleteShouldReturnForbiddenWhenClientLogged() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + clientToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDto())
                    .when().delete("/products/25").then().statusCode(403);
        }

        @Test
        void deleteShouldReturnUnauthorizedWhenInvalidToken() {
            given()
                    .header("Content-type", "application/json")
                    .header("Authorization", "Bearer " + invalidToken)
                    .contentType(ContentType.JSON)
                    .body(ProductFactory.createProductDto())
                    .when().delete("/products/25").then().statusCode(401);
        }
    }
}