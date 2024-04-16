package com.wswork.carros.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CarroEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String timestamp_Cadastro;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private ModeloEntity modelo;

    private int ano;
    private String combustivel;
    private int num_Portas;
    private String cor;

}
