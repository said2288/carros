package com.wswork.carros.enums;

public enum CarroEnum {

    CARRO_CRIADO("Carro adicionado com sucesso!", true),
    CARRO_NAO_CRIADO("Carro existente!", false),
    CARRO_MODIFICADO("Carro modificado com sucesso!", true),
    CARRO_NAO_CADASTRADO("Carro não cadastrado!", false),
    CARRO_DELETADO("Carro deletado com sucesso!", true),
    CARRO_NAO_ENCONTRADO("Carro não encontrado na base de dados!", false);

    private final String descricao;
    private final boolean ativo;

    CarroEnum(String descricao, boolean ativo) {
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
