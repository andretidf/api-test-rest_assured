package com.montanha.isolada;

import com.montanha.pojo.Usuario;
import com.montanha.pojo.Viagem;
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

        Usuario usuarioAdministrador = new Usuario();
        usuarioAdministrador.setEmail("admin@email.com");
        usuarioAdministrador.setSenha("654321");

        String token = given()
            .contentType(ContentType.JSON)
            .body(usuarioAdministrador)
        .when()
            .post("v1/auth")
        .then()
            .log()
                .all()//.log().all() -> É opcional para depug - p/verificar o retorno.
            .extract()
                .path("data.token");

        Viagem viagemValida = new Viagem();
        viagemValida.setAcompanhante("Luiz");
        viagemValida.setDataPartida("2022-07-27");
        viagemValida.setDataRetorno("2022-08-01");
        viagemValida.setLocalDeDestino("Osasco");
        viagemValida.setRegiao("Sul");

        given()
            .contentType(ContentType.JSON)
            .body(viagemValida)
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
