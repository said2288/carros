package com.wswork.carros.controller;

import com.wswork.carros.dtos.requisicao.CarroRequisicaoDTO;
import com.wswork.carros.dtos.requisicao.CarroRequisicaoModificacaoDTO;

import com.wswork.carros.enums.CarroEnum;
import com.wswork.carros.enums.MarcaEnum;
import com.wswork.carros.services.CarroService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Controlador de Carro")
@RestController
@RequestMapping("/carro")
public class CarroController {

    private final CarroService carroService;

    CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @ApiOperation("Adiciona um novo carro")
    @PostMapping
    public ResponseEntity adicionarCarro(@RequestBody CarroRequisicaoDTO carro) {

        CarroEnum carroEnum = carroService.adicionarCarro(carro);
        if (carroEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", carroEnum.getDescricao()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(String.format("%s", carroEnum.getDescricao()), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation("Lista todos os carros")
    @GetMapping
    public ResponseEntity listarCarros() {

        if(carroService.listarCarros().isEmpty()) {
            return new ResponseEntity("Não há carros para exibir!",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(carroService.listarCarros(), HttpStatus.OK);
        }

    }

    @ApiOperation("Edita um carro")
    @PutMapping
    public ResponseEntity modificarCarro(@RequestBody CarroRequisicaoModificacaoDTO carroDTO) {

        CarroEnum carroEnum = carroService.modificarCarro(carroDTO);
        if(carroEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", carroEnum.getDescricao()), HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("%s", carroEnum.getDescricao()), HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation("Deleta um carro")
    @DeleteMapping
    public ResponseEntity deletarCarro(@RequestParam Long id) {

        CarroEnum carroEnum = carroService.deletarCarro(id);
        if(carroEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", carroEnum.getDescricao()), HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("%s", carroEnum.getDescricao()), HttpStatus.NOT_MODIFIED);
        }

    }

}
