package com.wswork.carros.enums;

public enum ModeloEnum {

    MODELO_NAO_CRIADO("Modelo existente!", false),
    MODELO_CRIADO("Modelo adicionado com sucesso!", true),
    MODELO_MODIFICADO("Modelo modificado com sucesso!", true),
    MODELO_NAO_CADASTRADO("Modelo não cadastrado!", false),
    MODELO_NAO_ENCONTRADO("Modelo não encontrado na base de dados!", false),
    MODELO_DELETADO("Modelo deletado com sucesso!", true);

    private final String descricao;
    private final boolean ativo;

    ModeloEnum(String descricao, boolean ativo) {
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

