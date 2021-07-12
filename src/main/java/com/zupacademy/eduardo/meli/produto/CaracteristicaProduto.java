package com.zupacademy.eduardo.meli.produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public CaracteristicaProduto() {}

    public CaracteristicaProduto(@NotBlank String nome, @NotBlank String descricao, @NotNull Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }
}
