package com.wswork.carros.enums;

public enum MarcaEnum {

    MARCA_NAO_CRIADA("Marca ou modelo existente!", false),
    MARCA_CRIADA("Marca, modelo e carro adicionados com sucesso", true),
    MARCA_MODIFICADA("Marca modificada com sucesso!", true),
    MARCA_NAO_CADASTRADA("Marca não cadastrada!", false),
    MARCA_NAO_ENCONTRADA("Marca não encontrada na base de dados!", false),
    MARCA_DELETADA("Marca deletada com sucesso!", true);


    private final String descricao;
    private final boolean ativo;

    MarcaEnum(String descricao, boolean ativo) {
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean getATivo() {
        return ativo;
    }

}

