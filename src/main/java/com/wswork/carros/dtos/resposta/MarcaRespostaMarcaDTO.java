package com.wswork.carros.dtos.resposta;

import com.wswork.carros.dtos.ModeloDTO;
import lombok.Data;
import java.util.List;

@Data
public class MarcaRespostaMarcaDTO {

    private Long id;
    private String nome_marca;
    private List<ModeloDTO> modelos;
}