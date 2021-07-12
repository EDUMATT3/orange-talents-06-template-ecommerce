package com.zupacademy.eduardo.meli.produto;

import com.zupacademy.eduardo.meli.categoria.Categoria;
import com.zupacademy.eduardo.meli.cliente.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidade;
    @Size(min = 3)
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<CaracteristicaProduto>();
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @ManyToOne
    private Usuario usuario;

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
                   @NotNull @Positive int quantidade, @Size(min = 3) Set<NovaCaracteristicaRequest> caracteristicas,
                   @NotBlank @Length(max = 1000) String descricao, @NotNull Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas.stream().map(carac -> carac.toModel(this)).collect(Collectors.toSet()));
        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 ou mais características");
    }
}
