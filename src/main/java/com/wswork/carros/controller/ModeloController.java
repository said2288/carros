package com.wswork.carros.controller;

import com.wswork.carros.dtos.requisicao.ModeloRequisicaoDTO;
import com.wswork.carros.enums.ModeloEnum;
import com.wswork.carros.services.ModeloService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Controladora de Modelo")
@RestController
@RequestMapping("/modelo")
public class ModeloController {

    private final ModeloService modeloService;

    ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @ApiOperation("Adiciona um novo modelo")
    @PostMapping
    public ResponseEntity adicionarModelo(@RequestBody ModeloRequisicaoDTO modelo) {

        ModeloEnum modeloEnum = modeloService.adicionarModelo(modelo);
        if (modeloEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", modeloEnum.getDescricao()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(String.format("%s", modeloEnum.getDescricao()), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation("Lista todos os modelos WS")
    @GetMapping(path = {"/modelosws"})
    public ResponseEntity listarModelosWS() {

        if(modeloService.listarModelosWS().isEmpty()) {
            return new ResponseEntity("Não há modelos para exibir!",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(modeloService.listarModelosWS(), HttpStatus.OK);
        }

    }

    @ApiOperation("Lista todos os modelos")
    @GetMapping
    public ResponseEntity listarModelos() {

        if(modeloService.listarModelos().isEmpty()) {
            return new ResponseEntity("Não há modelos para exibir!",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(modeloService.listarModelos(), HttpStatus.OK);
        }

    }

    @ApiOperation("Edita o nome do modelo")
    @PutMapping
    public ResponseEntity modificarNomeModelo(@RequestParam String modeloAtual, @RequestParam String modeloAtualizado) {

        ModeloEnum modeloEnum = modeloService.modificarNomeModelo(modeloAtual, modeloAtualizado);
        if(modeloEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", modeloEnum.getDescricao()), HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("%s", modeloEnum.getDescricao()), HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation("Deleta um modelo")
    @DeleteMapping
    public ResponseEntity deletarModelo(@RequestParam String modelo) {

        ModeloEnum modeloEnum = modeloService.deletarModelo(modelo);
        if(modeloEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", modeloEnum.getDescricao()), HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("%s", modeloEnum.getDescricao()), HttpStatus.NOT_MODIFIED);
        }

    }
}