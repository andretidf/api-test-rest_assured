package com.montanha.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.montanha.pojo.Viagem;

import java.io.FileInputStream;
import java.io.IOException;

public class ViagemDataFactory {



    public static Viagem criarViagem() {
        ObjectMapper objectMapper = new ObjectMapper();

        Viagem viagem = null;
        try {
            viagem = objectMapper
                .readValue(new FileInputStream("src/test/resources/requestBody/postV1Viagens.json"), Viagem.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return viagem;
    }

    public static Viagem criarViagemValida() {
        Viagem viagemValida = criarViagem();

        return viagemValida;
    }

    public static Viagem criarViagemSemLocalDeDestino() {
        Viagem viagemSemLocalDeDestino = criarViagem();
        viagemSemLocalDeDestino.setLocalDeDestino("");

        return viagemSemLocalDeDestino;
    }
}
