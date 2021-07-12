package com.zupacademy.eduardo.meli.produto;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String link;

    @Valid
    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto() {}

    public ImagemProduto(@NotBlank String link, @NotNull @Valid Produto produto) {
        this.link = link;
        this.produto = produto;
    }
}
