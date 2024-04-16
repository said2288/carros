package com.wswork.carros.services;

import com.wswork.carros.dtos.requisicao.ModeloRequisicaoDTO;
import com.wswork.carros.dtos.resposta.ModeloRespostaModeloDTO;
import com.wswork.carros.dtos.resposta.ModeloRespostaWSDTO;

import com.wswork.carros.entity.CarroEntity;
import com.wswork.carros.entity.MarcaEntity;
import com.wswork.carros.entity.ModeloEntity;

import com.wswork.carros.enums.ModeloEnum;

import com.wswork.carros.repository.CarroRepository;
import com.wswork.carros.repository.MarcaRepository;
import com.wswork.carros.repository.ModeloRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class ModeloService {

    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final CarroRepository carroRepository;

    ModeloService(MarcaRepository marcaRepository,
                  ModeloRepository modeloRepository, CarroRepository carroRepository) {
        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
        this.carroRepository = carroRepository;
    }

    public ModeloEnum adicionarModelo(ModeloRequisicaoDTO modeloDTO) {

        try {
            ModeloEntity modelo = new ModeloEntity();
            modelo.setNome(modeloDTO.getNome());
            modelo.setValor_fipe(modeloDTO.getValorFipe());

            if (modeloRepository.existeModeloBoleana(modeloDTO.getNome())) {
                return ModeloEnum.MODELO_NAO_CRIADO;
            }

            Optional<MarcaEntity> marca = marcaRepository.findById(modeloDTO.getMarca_id());
            if (marca.isPresent()) {
                modelo.setMarca(marca.get());
            }

            modeloRepository.save(modelo);
            return ModeloEnum.MODELO_CRIADO;
        } catch (Exception e) {
            return ModeloEnum.MODELO_NAO_CRIADO;
        }
    }

    public List<ModeloRespostaWSDTO> listarModelosWS() {

        List<ModeloEntity> modelos = modeloRepository.findAll();
        List<ModeloRespostaWSDTO> modelosWSDTO = new ArrayList<>();

        for (ModeloEntity modelo : modelos) {
            for (CarroEntity carro : modelo.getCarros()) {
                ModeloRespostaWSDTO modeloDTO = new ModeloRespostaWSDTO();
                modeloDTO.setId(carro.getId());
                modeloDTO.setTimestamp_cadastro(carro.getTimestamp_Cadastro());
                modeloDTO.setModelo_id(modelo.getId());
                modeloDTO.setAno(carro.getAno());
                modeloDTO.setCombustivel(carro.getCombustivel());
                modeloDTO.setNum_Portas(carro.getNum_Portas());
                modeloDTO.setCor(carro.getCor());
                modeloDTO.setNome_modelo(modelo.getNome());
                modeloDTO.setValor(modelo.getValor_fipe());

                modelosWSDTO.add(modeloDTO);
            }
        }

        return modelosWSDTO;
    }

    public List<ModeloRespostaModeloDTO> listarModelos() {
        List<ModeloEntity> modelos = modeloRepository.findAll();
        List<ModeloRespostaModeloDTO> modelosDTO = new ArrayList<>();

        for (ModeloEntity modelo : modelos) {
            ModeloRespostaModeloDTO modeloDTO = new ModeloRespostaModeloDTO();
            modeloDTO.setId(modelo.getId());
            modeloDTO.setNome(modelo.getNome());
            modeloDTO.setValor_fipe(modelo.getValor_fipe());
            modeloDTO.setMarca_id(modelo.getMarca().getId());
            modelosDTO.add(modeloDTO);
        }

        return modelosDTO;
    }

    public ModeloEnum modificarNomeModelo(String modeloAtual, String modeloAtualizado) {
        ModeloEntity entidadeModelo = modeloRepository.buscarNomeModelo(modeloAtual);

        if (entidadeModelo != null) {
            entidadeModelo.setNome(modeloAtualizado);
            modeloRepository.save(entidadeModelo);
            return ModeloEnum.MODELO_MODIFICADO;
        }

        return ModeloEnum.MODELO_NAO_CADASTRADO;
    }

    public ModeloEnum deletarModelo(String modelo) {
        ModeloEntity modeloRecuperado = modeloRepository.buscarNomeModelo(modelo);

        if (modeloRecuperado == null) {
            return ModeloEnum.MODELO_NAO_ENCONTRADO;
        } else {
            modeloRepository.delete(modeloRecuperado);
            return ModeloEnum.MODELO_DELETADO;
        }
    }

}