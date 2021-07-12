package com.zupacademy.eduardo.meli.produto;

import com.zupacademy.eduardo.meli.categoria.Categoria;
import com.zupacademy.eduardo.meli.cliente.Usuario;
import com.zupacademy.eduardo.meli.compartilhado.ExistsValue;
import com.zupacademy.eduardo.meli.compartilhado.UniqueValue;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NovoProdutoRequest {

    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidade;

    @Size(min = 3)
    @Valid
    private Set<NovaCaracteristicaRequest> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @ExistsValue(domainClass = Categoria.class)
    private Long categoriaId;

    public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                              @NotNull @Positive int quantidade, @Size(min = 3) Set<NovaCaracteristicaRequest> caracteristicas,
                              @NotBlank @Length(max = 1000) String descricao, @NotNull Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(EntityManager em, Usuario usuario) {
        Categoria categoria = em.find(Categoria.class, this.categoriaId);
        Assert.state(Objects.nonNull(categoria), "Categoria não encontrada com o id: "+ this.categoriaId);

        return new Produto(this.nome, this.valor, this.quantidade, this.caracteristicas, this.descricao, categoria, usuario);
    }
}
