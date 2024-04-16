package com.wswork.carros.controller;

import com.wswork.carros.entity.MarcaEntity;
import com.wswork.carros.enums.MarcaEnum;
import com.wswork.carros.services.MarcaService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Controladora de Marca")
@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaService marcaService;

    MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @ApiOperation("Adiciona uma nova marca")
    @PostMapping
    public ResponseEntity adicionarMarca(@RequestBody MarcaEntity marca) {

        MarcaEnum marcaEnum = marcaService.adicionarMarca(marca);
        if(marcaEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", marcaEnum.getDescricao()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(String.format("%s", marcaEnum.getDescricao()), HttpStatus.BAD_REQUEST);
        }

    }

    @ApiOperation("Lista todas as marcas")
    @GetMapping
    public ResponseEntity listarMarcas() {

        if(marcaService.listarMarcas().isEmpty()) {
            return new ResponseEntity("Não há marcas para exibir!",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(marcaService.listarMarcas(), HttpStatus.OK);
        }

    }

    @ApiOperation("Edita o nome da marca")
    @PutMapping
    public ResponseEntity modificarNomeMarca(@RequestParam String marcaAtual, @RequestParam String marcaAtualizada) {

        MarcaEnum marcaEnum = marcaService.modificarNomeMarca(marcaAtual, marcaAtualizada);
        if(marcaEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", marcaEnum.getDescricao()), HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("%s", marcaEnum.getDescricao()), HttpStatus.NOT_FOUND);
        }

    }

    @ApiOperation("Deleta uma marca")
    @DeleteMapping
    public ResponseEntity deletarMarca(@RequestParam String marca) {

        MarcaEnum marcaEnum = marcaService.deletarMarca(marca);
        if(marcaEnum.getATivo()) {
            return new ResponseEntity(String.format("%s", marcaEnum.getDescricao()), HttpStatus.OK);
        } else {
            return new ResponseEntity(String.format("%s", marcaEnum.getDescricao()), HttpStatus.NOT_MODIFIED);
        }

    }

}
