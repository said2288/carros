package com.wswork.carros.dtos.requisicao;

import lombok.Data;

@Data
public class CarroRequisicaoModificacaoDTO {

    private Long id;
    private int ano;
    private String combustivel;
    private int num_Portas;
    private String cor;

}
