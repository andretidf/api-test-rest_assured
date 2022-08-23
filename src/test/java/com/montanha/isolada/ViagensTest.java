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

        String token = given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                        "  \"email\": \"admin@email.com\",\n" +
                        "  \"senha\": \"654321\"\n" +
                        "}")
        .when()
            .post("v1/auth")
        .then()
            .log()
                .all()//.log().all() -> É opcional para depug - p/verificar o retorno.
            .extract()
                .path("data.token");

        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "  \"acompanhante\": \"Luiz\",\n" +
                    "  \"dataPartida\": \"2022-07-27\",\n" +
                    "  \"dataRetorno\": \"2022-08-01\",\n" +
                    "  \"localDeDestino\": \"Osasco\",\n" +
                    "  \"regiao\": \"Sul\"\n" +
                    "}")
            .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
            .assertThat()
                .statusCode(201)
                .body("data.localDeDestino", equalTo("Osasco"))
                .body("data.acompanhante", equalToIgnoringCase("Luiz"));

    }



}
