package com.wswork.carros.dtos;

import lombok.Data;

@Data
public class CarroDTO {

    private String timestamp_Cadastro;
    private int ano;
    private String combustivel;
    private int num_Portas;
    private String cor;

}
