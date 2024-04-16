package com.wswork.carros.services;

import com.wswork.carros.dtos.CarroDTO;
import com.wswork.carros.dtos.ModeloDTO;
import com.wswork.carros.dtos.resposta.MarcaRespostaMarcaDTO;


import com.wswork.carros.entity.CarroEntity;
import com.wswork.carros.entity.MarcaEntity;
import com.wswork.carros.entity.ModeloEntity;

import com.wswork.carros.enums.MarcaEnum;
import com.wswork.carros.shared.utils.Timestamp;

import com.wswork.carros.repository.MarcaRepository;
import com.wswork.carros.repository.ModeloRepository;
import com.wswork.carros.repository.CarroRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;


@Service
@Transactional
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final CarroRepository carroRepository;

    MarcaService(MarcaRepository marcaRepository, ModeloRepository modeloRepository, CarroRepository carroRepository) {
        this.marcaRepository = marcaRepository;
        this.modeloRepository = modeloRepository;
        this.carroRepository = carroRepository;
    }

    public MarcaEnum adicionarMarca(MarcaEntity marca) {

        if (marcaRepository.existeMarcaBoleana(marca.getNomeMarca())) {
            for (ModeloEntity modelo : marca.getModelos()) {
                if (modeloRepository.existeModeloBoleana(modelo.getNome())) {
                    return MarcaEnum.MARCA_NAO_CRIADA;
                } else {
                    if (marca.getModelos() != null && !marca.getModelos().isEmpty()) {
                        for (ModeloEntity adicionarModelo : marca.getModelos()) {
                            if (!modeloRepository.existeModeloBoleana(adicionarModelo.getNome())) {
                                ModeloEntity novoModelo = new ModeloEntity();
                                novoModelo.setNome(adicionarModelo.getNome());
                                novoModelo.setValor_fipe(adicionarModelo.getValor_fipe());
                                novoModelo.setMarca(marca);
                                modeloRepository.save(novoModelo);

                                if (adicionarModelo.getCarros() != null && !adicionarModelo.getCarros().isEmpty()) {
                                    for (CarroEntity carro : adicionarModelo.getCarros()) {
                                        CarroEntity novoCarro = new CarroEntity();
                                        novoCarro.setTimestamp_Cadastro(new Timestamp().formatarTimesTamp());
                                        novoCarro.setModelo(novoModelo);
                                        novoCarro.setAno(carro.getAno());
                                        novoCarro.setCombustivel(carro.getCombustivel());
                                        novoCarro.setNum_Portas(carro.getNum_Portas());
                                        novoCarro.setCor(carro.getCor());
                                        carroRepository.save(novoCarro);
                                    }
                                }
                            }
                        }
                    }

                    return MarcaEnum.MARCA_CRIADA;
                }
            }
        }

        MarcaEntity novaMarca = new MarcaEntity();
        novaMarca.setNomeMarca(marca.getNomeMarca());
        marcaRepository.save(novaMarca);

        if (marca.getModelos() != null && !marca.getModelos().isEmpty()) {
            for (ModeloEntity modelo : marca.getModelos()) {
                if (!modeloRepository.existeModeloBoleana(modelo.getNome())) {
                    ModeloEntity novoModelo = new ModeloEntity();
                    novoModelo.setNome(modelo.getNome());
                    novoModelo.setValor_fipe(modelo.getValor_fipe());
                    novoModelo.setMarca(novaMarca);
                    modeloRepository.save(novoModelo);

                    if (modelo.getCarros() != null && !modelo.getCarros().isEmpty()) {
                        for (CarroEntity carro : modelo.getCarros()) {
                            CarroEntity novoCarro = new CarroEntity();
                            novoCarro.setTimestamp_Cadastro(new Timestamp().formatarTimesTamp());
                            novoCarro.setModelo(novoModelo);
                            novoCarro.setAno(carro.getAno());
                            novoCarro.setCombustivel(carro.getCombustivel());
                            novoCarro.setNum_Portas(carro.getNum_Portas());
                            novoCarro.setCor(carro.getCor());
                            carroRepository.save(novoCarro);
                        }
                    }
                }
            }
        }

        return MarcaEnum.MARCA_CRIADA;
    }

    public List listarMarcas() {

        List<MarcaEntity> marcas = marcaRepository.findAllByOrderByNomeMarcaAsc();
        List<MarcaRespostaMarcaDTO> marcaDTOs = new ArrayList<>();

        for (MarcaEntity marca : marcas) {
            MarcaRespostaMarcaDTO marcaDTO = new MarcaRespostaMarcaDTO();
            marcaDTO.setId(marca.getId());
            marcaDTO.setNome_marca(marca.getNomeMarca());

            List<ModeloDTO> modeloDTOs = new ArrayList<>();
            for (ModeloEntity modelo : marca.getModelos()) {
                List<CarroDTO> carroDTOs = new ArrayList<>();
                for (CarroEntity carro : modelo.getCarros()) {
                    CarroDTO carroDTO = new CarroDTO();
                    carroDTO.setTimestamp_Cadastro(carro.getTimestamp_Cadastro());
                    carroDTO.setAno(carro.getAno());
                    carroDTO.setCombustivel(carro.getCombustivel());
                    carroDTO.setNum_Portas(carro.getNum_Portas());
                    carroDTO.setCor(carro.getCor());
                    carroDTOs.add(carroDTO);
                }
                ModeloDTO modeloDTO = new ModeloDTO();
                modeloDTO.setId(modelo.getId());
                modeloDTO.setNome(modelo.getNome());
                modeloDTO.setValor_fipe(modelo.getValor_fipe());
                modeloDTO.setCarros(carroDTOs);
                modeloDTOs.add(modeloDTO);
            }
            marcaDTO.setModelos(modeloDTOs);
            marcaDTOs.add(marcaDTO);
        }

        return marcaDTOs;
    }

    public MarcaEnum modificarNomeMarca(String marcaAtual, String marcaAtualizada) {
        MarcaEntity entidadeMarca = marcaRepository.buscarNomeMarca(marcaAtual);

        if (entidadeMarca != null) {
            entidadeMarca.setNomeMarca(marcaAtualizada);
            marcaRepository.save(entidadeMarca);
            return MarcaEnum.MARCA_MODIFICADA;
        }

        return MarcaEnum.MARCA_NAO_CADASTRADA;
    }

    public MarcaEnum deletarMarca(String marca) {
        MarcaEntity marcaRecuperada = marcaRepository.buscarNomeMarca(marca);

        if (marcaRecuperada == null) {
            return MarcaEnum.MARCA_NAO_ENCONTRADA;
        } else {
            marcaRepository.delete(marcaRecuperada);
            return MarcaEnum.MARCA_DELETADA;
        }
    }

}