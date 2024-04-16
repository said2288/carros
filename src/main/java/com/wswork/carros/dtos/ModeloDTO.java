package com.wswork.carros.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ModeloDTO {

    private Long id;
    private String nome;
    private String valor_fipe;
    private List<CarroDTO> carros;

}