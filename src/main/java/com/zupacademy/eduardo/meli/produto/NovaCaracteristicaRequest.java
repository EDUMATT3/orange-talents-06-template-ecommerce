package com.zupacademy.eduardo.meli.produto;

import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NovaCaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank  String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaProduto toModel(Produto produto) {
        Assert.state(Objects.nonNull(produto), "Produto não deveria ser nulo");
        return new CaracteristicaProduto(nome, descricao, produto);
    }
}
