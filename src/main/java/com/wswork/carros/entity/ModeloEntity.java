package com.wswork.carros.entity;

import java.util.List;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ModeloEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String valor_fipe;

    @JsonIgnoreProperties({"modelos"})
    @ManyToOne
    @JoinColumn(name="marca_id")
    private MarcaEntity marca;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private List<CarroEntity> carros;

}