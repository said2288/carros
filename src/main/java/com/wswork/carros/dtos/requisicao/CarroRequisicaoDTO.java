package com.wswork.carros.dtos.requisicao;

import lombok.Data;

@Data
public class CarroRequisicaoDTO {

    private int ano;
    private String combustivel;
    private int num_Portas;
    private String cor;
    private Long modelo_id;

}
