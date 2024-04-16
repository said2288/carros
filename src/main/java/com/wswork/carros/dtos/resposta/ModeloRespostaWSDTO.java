package com.wswork.carros.dtos.resposta;

import lombok.Data;

@Data
public class ModeloRespostaWSDTO {

    private Long id;
    private String timestamp_cadastro;
    private Long modelo_id;
    private int ano;
    private String combustivel;
    private int num_Portas;
    private String cor;
    private String nome_modelo;
    private String valor;

}




