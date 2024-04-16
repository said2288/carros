package com.wswork.carros.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MarcaEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_marca")
    private String nomeMarca;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private List<ModeloEntity> modelos;

}