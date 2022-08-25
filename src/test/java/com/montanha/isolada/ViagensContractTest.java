package com.montanha.isolada;

import com.montanha.factory.UsuarioDataFactory;
import com.montanha.factory.ViagemDataFactory;
import com.montanha.pojo.Usuario;
import com.montanha.pojo.Viagem;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class ViagensContractTest {

    private String token;

    @Before
    public void setUp() throws Exception {
        // configurações Rest-Assured
        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";

        Usuario usuarioAdministrador = UsuarioDataFactory.criarUsuarioAdministrador();

        this.token = given()
            .contentType(ContentType.JSON)
            .body(usuarioAdministrador)
        .when()
            .post("v1/auth")
        .then()
            .extract()
                .path("data.token");
    }

    @Test
    public void testCadastroDeViagemValidaContrato(){

        Viagem viagemValida = ViagemDataFactory.criarViagemValida();

        given()
            .contentType(ContentType.JSON)
            .body(viagemValida)
            .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
            .assertThat()
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/postV1ViagensViagemValida.json"));

    }
}
