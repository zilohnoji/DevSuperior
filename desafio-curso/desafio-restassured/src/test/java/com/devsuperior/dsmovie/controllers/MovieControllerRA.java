package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.factory.MovieFactory;
import com.devsuperior.dsmovie.util.TokenUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MovieControllerRA {

    private String adminToken, clientToken, invalidToken;

    @BeforeAll
    static void setupAll() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @BeforeEach
    void setupEach() throws JSONException {
        adminToken = TokenUtil.obtainAccessToken("maria@gmail.com", "123456");
        clientToken = TokenUtil.obtainAccessToken("alex@gmail.com", "123456");
        invalidToken = clientToken + "xpto";
    }

    @Test
    public void findAllShouldReturnOkWhenMovieNoArgumentsGiven() {
        get("/movies").then().statusCode(200)
                .body("content.id", hasItem(1))
                .body("content.title", hasItem("The Witcher"));
    }

    @Test
    public void findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty() {
        get("/movies?title=aves").then().statusCode(200)
                .body("content.id", hasItem(9))
                .body("content.title", hasItem("Aves de Rapina: Arlequina e sua Emancipação Fantabulosa"));
    }

    @Test
    public void findByIdShouldReturnMovieWhenIdExists() {
        get("/movies/9").then().statusCode(200)
                .body("id", is(9))
                .body("title", equalTo("Aves de Rapina: Arlequina e sua Emancipação Fantabulosa"));
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() {
        get("/movies/0").then().statusCode(404);
    }

    @Test
    public void insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle() throws JSONException {
        given()
                .header("Authorization", "Bearer " + adminToken)
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .body(MovieFactory.createMovieDTOFromTitle(null))
                .when().post("/movies").then().statusCode(422)
                .body("errors[0].fieldName", equalTo("title"))
                .body("errors[0].message", equalTo("Campo requerido"));

    }

    @Test
    public void insertShouldReturnForbiddenWhenClientLogged() throws Exception {
        given()
                .header("Authorization", "Bearer " + clientToken)
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .body(MovieFactory.createMovieDTOFromTitle("Dora Aventureira"))
                .when().post("/movies").then().statusCode(403);
    }

    @Test
    public void insertShouldReturnUnauthorizedWhenInvalidToken() throws Exception {
        given()
                .header("Authorization", "Bearer " + invalidToken)
                .contentType(ContentType.JSON)
                .header("Content-type", "application/json")
                .body(MovieFactory.createMovieDTOFromTitle("Max Steel"))
                .when().post("/movies").then().statusCode(401);
    }
}
