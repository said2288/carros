package com.wswork.carros.dtos.resposta;

import lombok.Data;

@Data
public class CarroRespostaCarroDTO {

    private Long id;
    private String timestamp_Cadastro;
    private int ano;
    private String combustivel;
    private int num_Portas;
    private String cor;
    private Long modelo_id;

}
