package com.montanha.factory;

import com.montanha.pojo.Viagem;

public class ViagemDataFactory {

    public static Viagem criarViagemValida(){
        Viagem viagemValida = new Viagem();
        viagemValida.setAcompanhante("Luiz");
        viagemValida.setDataPartida("2022-07-27");
        viagemValida.setDataRetorno("2022-08-01");
        viagemValida.setLocalDeDestino("Osasco");
        viagemValida.setRegiao("Sul");

        return viagemValida;
    }
}
