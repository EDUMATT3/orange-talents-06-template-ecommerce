package com.zupacademy.eduardo.meli.produto;

import com.zupacademy.eduardo.meli.categoria.Categoria;
import com.zupacademy.eduardo.meli.detalheproduto.CaracteristicaProdutoResponse;
import com.zupacademy.eduardo.meli.opniao.Opiniao;
import com.zupacademy.eduardo.meli.pergunta.Pergunta;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();

    @Deprecated
    public Produto() {}

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

    public boolean pertenceAoUsuario(Usuario usuario) {
        Assert.state(Objects.nonNull(usuario), "Usuario não deveria ser nulo");
        return Objects.equals(this.usuario, usuario);
    }

    public void adicionarImagens(Set<String> imagens) {
        Set<ImagemProduto> links = imagens.stream()
                .map(img -> new ImagemProduto(img, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(links);
    }

    public <T> Set<T> mapeiaCaracteristicas(Function<CaracteristicaProduto, T> function){
        return this.caracteristicas.stream().map(function).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> function){
        return this.imagens.stream().map(function).collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapeiaPeguntas(Function<Pergunta, T> function){
        return this.perguntas.stream().map(function).collect(Collectors.toCollection(TreeSet::new));
    }

    public String getNomeVendedor() {
        return this.usuario.getUsername();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }
}
