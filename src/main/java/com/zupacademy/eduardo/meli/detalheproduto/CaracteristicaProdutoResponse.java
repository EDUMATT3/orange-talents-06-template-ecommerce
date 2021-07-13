package com.zupacademy.eduardo.meli.detalheproduto;

import com.zupacademy.eduardo.meli.produto.CaracteristicaProduto;

public class CaracteristicaProdutoResponse {

    private String nome;
    private String descricao;

    public CaracteristicaProdutoResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
