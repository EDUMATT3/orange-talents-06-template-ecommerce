package com.zupacademy.eduardo.meli.detalheproduto;

import com.zupacademy.eduardo.meli.pergunta.Pergunta;
import com.zupacademy.eduardo.meli.produto.CaracteristicaProduto;
import com.zupacademy.eduardo.meli.produto.ImagemProduto;
import com.zupacademy.eduardo.meli.produto.Opinioes;
import com.zupacademy.eduardo.meli.produto.Produto;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

public class DetalheProdutoResponse {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Set<CaracteristicaProdutoResponse> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String,String>> opinioes;
    private double mediaNotas;
    private int total;

    public DetalheProdutoResponse(Produto produto) {
        Assert.state(Objects.nonNull(produto), "Produto não deveria ser nulo");

        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicaProdutoResponse::new);
        this.linksImagens = produto.mapeiaImagens(ImagemProduto::getLink);
        this.perguntas = produto.mapeiaPeguntas(Pergunta::getTitulo);

        Opinioes opinioes = produto.getOpinioes();

        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao()));

        this.mediaNotas = opinioes.getMedia();
        this.total = opinioes.getTotal();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotal() {
        return total;
    }
}
