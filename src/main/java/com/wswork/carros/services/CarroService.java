package com.wswork.carros.services;

import com.wswork.carros.dtos.requisicao.CarroRequisicaoDTO;
import com.wswork.carros.dtos.requisicao.CarroRequisicaoModificacaoDTO;
import com.wswork.carros.dtos.resposta.CarroRespostaCarroDTO;

import com.wswork.carros.entity.CarroEntity;
import com.wswork.carros.entity.ModeloEntity;

import com.wswork.carros.enums.CarroEnum;
import com.wswork.carros.shared.utils.Timestamp;

import com.wswork.carros.repository.CarroRepository;
import com.wswork.carros.repository.ModeloRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class CarroService {

    private final ModeloRepository modeloRepository;
    private final CarroRepository carroRepository;

    CarroService(ModeloRepository modeloRepository, CarroRepository carroRepository) {
        this.modeloRepository = modeloRepository;
        this.carroRepository = carroRepository;
    }

    public CarroEnum adicionarCarro(CarroRequisicaoDTO carroDTO) {

        try {
            CarroEntity carro = new CarroEntity();
            carro.setTimestamp_Cadastro(new Timestamp().formatarTimesTamp());
            carro.setAno(carroDTO.getAno());
            carro.setCombustivel(carroDTO.getCombustivel());
            carro.setNum_Portas(carroDTO.getNum_Portas());
            carro.setCor(carroDTO.getCor());

            Optional<ModeloEntity> modelo = modeloRepository.findById(carroDTO.getModelo_id());
            if (modelo.isPresent()) {
                carro.setModelo(modelo.get());
            }

            carroRepository.save(carro);
            return CarroEnum.CARRO_CRIADO;
        } catch (Exception e) {
            return CarroEnum.CARRO_NAO_CRIADO;
        }
    }

    public List<CarroRespostaCarroDTO> listarCarros() {
        List<CarroEntity> carros = carroRepository.findAll();
        List<CarroRespostaCarroDTO> carrosDTO = new ArrayList<>();

        for (CarroEntity carro : carros) {
            CarroRespostaCarroDTO carroDTO = new CarroRespostaCarroDTO();
            carroDTO.setId(carro.getId());
            carroDTO.setTimestamp_Cadastro(carro.getTimestamp_Cadastro());
            carroDTO.setAno(carro.getAno());
            carroDTO.setCombustivel(carro.getCombustivel());
            carroDTO.setNum_Portas(carro.getNum_Portas());
            carroDTO.setCor(carro.getCor());
            carroDTO.setModelo_id(carro.getModelo().getId());
            carrosDTO.add(carroDTO);
        }

        return carrosDTO;
    }

    public CarroEnum modificarCarro(CarroRequisicaoModificacaoDTO carroDTO) {
        Optional<CarroEntity> entidadeCarro = carroRepository.findById(carroDTO.getId());

        if (entidadeCarro.isPresent()) {
            entidadeCarro.get().setTimestamp_Cadastro(new Timestamp().formatarTimesTamp());
            entidadeCarro.get().setAno(carroDTO.getAno());
            entidadeCarro.get().setCombustivel(carroDTO.getCombustivel());
            entidadeCarro.get().setNum_Portas(carroDTO.getNum_Portas());
            entidadeCarro.get().setCor(carroDTO.getCor());
            carroRepository.save(entidadeCarro.get());
            return CarroEnum.CARRO_MODIFICADO;
        }

        return CarroEnum.CARRO_NAO_CADASTRADO;
    }

    public CarroEnum deletarCarro(Long id) {
        Optional<CarroEntity> carroRecuperado = carroRepository.findById(id);

        if (carroRecuperado.isPresent()) {
            carroRepository.delete(carroRecuperado.get());
            return CarroEnum.CARRO_DELETADO;
        } else {
            return CarroEnum.CARRO_NAO_ENCONTRADO;
        }
    }

}
