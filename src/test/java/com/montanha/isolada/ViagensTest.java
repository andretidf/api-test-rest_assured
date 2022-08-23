package com.montanha.isolada;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ViagensTest {


    @Test
    public void testCadastroDeViagemValidaRetornaSucesso(){
        // configurações Rest-Assured
        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";

        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                        "  \"email\": \"admin@email.com\",\n" +
                        "  \"senha\": \"654321\"\n" +
                        "}")
        .when()
            .post("v1/auth")
        .then()
            .log()
                .all();


    }



}
