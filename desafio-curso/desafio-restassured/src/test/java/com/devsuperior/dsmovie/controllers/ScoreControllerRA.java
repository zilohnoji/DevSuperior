package com.devsuperior.dsmovie.controllers;

import com.devsuperior.dsmovie.factory.ScoreFactory;
import com.devsuperior.dsmovie.util.TokenUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ScoreControllerRA {

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
    public void saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist() throws Exception {
        given()
                .header("Authorization", "Bearer " + adminToken)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(ScoreFactory.createScoreDTOFromMovieID(0L))
                .when().put("/scores").then().statusCode(404);
    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId() throws Exception {
        given()
                .header("Authorization", "Bearer " + adminToken)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(ScoreFactory.createScoreDTOFromMovieID(null))
                .when().put("/scores").then().statusCode(422);
    }

    @Test
    public void saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero() throws Exception {
        given()
                .header("Authorization", "Bearer " + adminToken)
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(ScoreFactory.createScoreDTOFromScore(-1D))
                .when().put("/scores").then().statusCode(422);
    }
}
